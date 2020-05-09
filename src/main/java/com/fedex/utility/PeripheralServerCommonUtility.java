package com.fedex.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fedex.constants.EndpointConfigurationMappingConstants;
import com.fedex.constants.HttpMethod;
import com.fedex.constants.PeripheralServerClaimType;
import com.fedex.constants.PeripheralServerConstants;
import com.fedex.cxs.dto.CXSEnvelope;
import com.fedex.cxs.dto.CXSError;
import com.fedex.cxs.dto.CXSOutput;
import com.fedex.dto.PeripheralServerBinLabelRequestDTO;
import com.fedex.dto.PeripheralServerCashDrawerRequestDTO;
import com.fedex.dto.PeripheralServerClaimRequest;
import com.fedex.dto.PeripheralServerClaimRequestDTO;
import com.fedex.dto.PeripheralServerClaimResponse;
import com.fedex.dto.PeripheralServerClaimResponseDTO;
import com.fedex.dto.PeripheralServerEndpointConfig;
import com.fedex.dto.PeripheralServerObjectFactory;
import com.fedex.dto.PeripheralServerPNDLabelRequestDTO;
import com.fedex.dto.PeripheralServerReceiptDataDTO;
import com.fedex.dto.PeripheralServerReceiptPrinterXmlDTO;
import com.fedex.dto.PeripheralServerReleaseRequestDTO;
import com.fedex.dto.PeripheralServerRequest;
import com.fedex.dto.PeripheralServerResponse;
import com.fedex.dto.PeripheralServerShippingLabelRequestDTO;
import com.fedex.errors.CommunicationLayerErrorCodes;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is utility class of Communication Layer which communicates with peripheral server.
 * 
 * @author Namit Jain [3696360]
 * @version 1.0.0
 * @since 05-Feb-2020
 */
public class PeripheralServerCommonUtility {

    private static Logger logger = LogManager.getLogger(PeripheralServerCommonUtility.class);

    /**
     * private default constructor is made, as this class only contains static method.
     */
    private PeripheralServerCommonUtility() {}

    /**
     * This method sends request to the peripheral server and returns the fetched response wrapped in
     * {@link PeripheralServerResponse}
     * 
     * @param <T> Class to which response JSON fetched from peripheral response needs to be mapped
     * @param requestObject request object containing IP, port and request body to be sent.
     * @param responseType object containing class to which response JSON fetched from peripheral response needs to be
     *        mapped
     * @return the fetched response from peripheral server wrapped in {@link PeripheralServerResponse} and it sets
     *         status code as 0 if any error occurs while fetching response
     * @since 05-Feb-2020
     */
    public static <T extends CXSOutput> PeripheralServerResponse<T> sendRequestAndWaitForResponse(
                    PeripheralServerRequest requestObject, Class<T> responseType) {
        logger.info("\n*********************************************************** Request started ***********************************************************");
        PeripheralServerResponse<T> peripheralServerResponse = sendReqAndWaitForResp(requestObject, responseType);
        logger.info("Final response sent from Communication Layer: {}", beautify(peripheralServerResponse));
        logger.info("\n*********************************************************** Request ended *************************************************************\n");
        return peripheralServerResponse;
    }

    /**
     * This method sends request to the peripheral server and returns the fetched response wrapped in
     * {@link PeripheralServerResponse}
     * 
     * @param <T> Class to which response JSON fetched from peripheral response needs to be mapped
     * @param requestObject request object containing IP, port and request body to be sent.
     * @param responseType object containing class to which response JSON fetched from peripheral response needs to be
     *        mapped
     * @return the fetched response from peripheral server wrapped in {@link PeripheralServerResponse} and it sets
     *         status code as 0 if any error occurs while fetching response
     * @since 05-Feb-2020
     */
    private static <T extends CXSOutput> PeripheralServerResponse<T> sendReqAndWaitForResp(
                    PeripheralServerRequest requestObject, Class<T> responseType) {
        PeripheralServerResponse<T> peripheralServerResponse = null;
        PeripheralServerEndpointConfig peripheralServerEndpointConfig = getPeripheralEndpointConfig(requestObject);
        // if configuration of the request object type is found, proceed with the
        // request
        if (peripheralServerEndpointConfig != null) {
            peripheralServerResponse = sendRequestToPeripheralServer(peripheralServerEndpointConfig, responseType);
            // retry the request if peripheral server claim id is expired
            peripheralServerResponse = retryOnPeripheralServerTimeout(peripheralServerEndpointConfig, responseType,
                            peripheralServerResponse);
            // extract claim id from the PeripheralServerClaimResponseDTO response and set it to
            // PeripheralServerResponse
            String claimId;
            if (responseType == PeripheralServerClaimResponseDTO.class) {
                claimId = extractClaimIdFromPeripheralServerClaimResponse(peripheralServerResponse);
            } else {
                claimId = getAndSetClaimIdInRequestObject(requestObject, null);
            }
            peripheralServerResponse.setClaimId(claimId);
        }
        // configuration is not found, then return default peripheral server response
        else {
            String requestObjClassName = requestObject.getClass().getSimpleName();
            logger.info("Request is not being processed as requested DTO : '{}' has no endpoint configuration mapped",
                            requestObjClassName);
            peripheralServerResponse = new PeripheralServerResponse<>(
                            CommunicationLayerErrorCodes.REQUEST_TYPE_INVALID_EXCEPTION, requestObjClassName);
        }
        return peripheralServerResponse;
    }

    /**
     * This method sends request to the peripheral server and returns only errors if any, wrapped in
     * {@link PeripheralServerResponse}. This method should be used make request where no content HTTPResponse is
     * expected
     * 
     * @param peripheralRequest request object containing IP, port and request body to be sent.
     * @return response wrapped in {@link PeripheralServerResponse}
     * @since 05-Feb-2020
     */
    public static PeripheralServerResponse<CXSOutput> sendRequestAndWaitForResponse(
                    PeripheralServerRequest peripheralRequest) {
        return sendRequestAndWaitForResponse(peripheralRequest, CXSOutput.class);
    }

    /**
     * This method extract claim id from the {@link PeripheralServerResponse}
     * 
     * @param <T> output object class type in {@link PeripheralServerResponse} which should be
     *        {@link PeripheralServerClaimResponseDTO} to fetch claim id successfully
     * @param peripheralServerResponse the peripheral server claim response
     * @return claim Id extracted from peripheral server claim response<br>
     *         {@code null} if not able to fetch
     * @since 05-Feb-2020
     */
    private static <T extends CXSOutput> String extractClaimIdFromPeripheralServerClaimResponse(
                    PeripheralServerResponse<T> peripheralServerResponse) {
        if (peripheralServerResponse.getStatusCode() != 0 && peripheralServerResponse.getOutput() != null
                        && peripheralServerResponse.getOutput() instanceof PeripheralServerClaimResponseDTO) {
            PeripheralServerClaimResponseDTO peripheralServerClaimResponseDTO =
                            (PeripheralServerClaimResponseDTO) peripheralServerResponse.getOutput();
            PeripheralServerClaimResponse peripheralServerClaimResponse = peripheralServerClaimResponseDTO.getClaim();
            if (peripheralServerClaimResponse != null && peripheralServerClaimResponse.getClaimId() != null) {
                return peripheralServerClaimResponse.getClaimId();
            }
        }
        return null;
    }

    /**
     * This method checks if response has {@code PERIPHERALSERVER.DEVICECLAIM.TIMEOUTEXCEPTION}, then reclaim peripheral
     * server and retry the request earlier made and send the updated {@link PeripheralServerResponse}
     * 
     * @param <T> Class to which response JSON fetched from peripheral response needs to be mapped
     * @param peripheralServerEndpointConfig endpoint config containing request url, method type and body.
     * @param responseType object containing class to which response JSON fetched from peripheral response needs to be
     *        mapped
     * @param peripheralServerResponse initial response of current request made to peripheral server
     * @return the fetched response from peripheral server wrapped in {@link PeripheralServerResponse} and it sets
     *         status code as 0 if any error occurs while fetching response
     * @since 05-Feb-2020
     */
    private static <T extends CXSOutput> PeripheralServerResponse<T> retryOnPeripheralServerTimeout(
                    PeripheralServerEndpointConfig peripheralServerEndpointConfig, Class<T> responseType,
                    PeripheralServerResponse<T> peripheralServerResponse) {
        PeripheralServerRequest reqObject = peripheralServerEndpointConfig.getRequest();
        List<CXSError> errors = peripheralServerResponse.getErrors();
        // if response is having error of claim id expired, then reclaim the peripheral
        // server and retry the request
        if (peripheralServerResponse.getStatusCode() == 408 && errors != null && !errors.isEmpty() && errors.get(0)
                        .getCode().equals(PeripheralServerConstants.PERIPHERALSERVER_DEVICECLAIM_TIMEOUTEXCEPTION)) {
            logger.info("Claim id is expired, Claiming Peripheral server again.");
            PeripheralServerResponse<PeripheralServerClaimResponseDTO> peripheralServerClaimResponse =
                            getClaimIdFromPeripheralServer(reqObject.getIp(), reqObject.getPort());
            String claimIdFetched = peripheralServerClaimResponse.getClaimId();
            // if peripheral server is reclaimed successfully, then claim id is fetched and
            // updated in request body to
            // proceed with actual request
            if (claimIdFetched != null) {
                logger.info("Replacing claim id fetched to the request object, and then retrying the request!");
                getAndSetClaimIdInRequestObject(reqObject, claimIdFetched);
                peripheralServerResponse = sendRequestToPeripheralServer(peripheralServerEndpointConfig, responseType);
            }
            // else error of claiming peripheral server is sent as peripheralServerResponse
            else {
                peripheralServerResponse = new PeripheralServerResponse(peripheralServerClaimResponse.getStatusCode(),
                                CXSEnvelope.error(peripheralServerClaimResponse.getErrors()));
            }
            // setting claim id fetched to peripheral server response
            peripheralServerResponse.setClaimId(claimIdFetched);
        }
        return peripheralServerResponse;
    }

    /**
     * This method claims peripheral server and returns {@link PeripheralServerResponse}
     * 
     * @param ip the IP address to be used in request URL
     * @param port the port to be used in request URL
     * @return {@link PeripheralServerResponse} containing {@link PeripheralServerClaimResponseDTO}
     * @since 05-Feb-2020
     */
    private static PeripheralServerResponse<PeripheralServerClaimResponseDTO> getClaimIdFromPeripheralServer(String ip,
                    String port) {
        PeripheralServerClaimRequestDTO peripheralServerClaimRequest =
                        PeripheralServerObjectFactory.getClaimRequest(ip, port);
        peripheralServerClaimRequest
                        .setClaimRequest(new PeripheralServerClaimRequest(PeripheralServerClaimType.PERIPHERAL_SERVER));
        return sendReqAndWaitForResp(peripheralServerClaimRequest, PeripheralServerClaimResponseDTO.class);
    }

    /**
     * This method takes request object and checks the object type and on basis of that, it returns instance of
     * {@link PeripheralServerEndpointConfig} containing URL, httpMethod, request object.
     * 
     * @param requestObject request object on basis of which URL and HTTP Method type is decided.
     * @return instance of {@link PeripheralServerEndpointConfig} containing URL, httpMethod, request object
     * @since 05-Feb-2020
     */
    private static PeripheralServerEndpointConfig getPeripheralEndpointConfig(PeripheralServerRequest requestObject) {
        String reqObjectClassName = requestObject.getClass().getSimpleName();
        logger.info("Searching endpoint configuration for request class : '{}'", reqObjectClassName);
        EndpointConfigurationMappingConstants endpointConfigured = extractRequetObjectMapping(reqObjectClassName);
        if (endpointConfigured == null) {
            return null;
        }

        // generate full URI for request
        StringBuilder urlBuilder = new StringBuilder(endpointConfigured.getRequestUrl());
        if (endpointConfigured.equals(EndpointConfigurationMappingConstants.PERIPHERALSERVERRELEASEREQUESTDTO)) {
            urlBuilder.append(((PeripheralServerReleaseRequestDTO) requestObject).getClaimId());
        }
        String url = generateURI(requestObject, urlBuilder);

        // request body needs to be sent over http request or not
        if (!endpointConfigured.isSendRequestBody()) {
            requestObject = null;
        }
        return new PeripheralServerEndpointConfig(url, endpointConfigured.getRequestType(), requestObject);
    }

    /**
     * When claimId param is passed as {@code null} it returns the claim id present in the request object, else this
     * method replaces claim id in the request object if claim id key exists there.
     * 
     * @param requestObject request object having claim id.
     * @param claimId claim id which needs to be set in request object. It is ignored if claim id is provided as
     *        {@code null}.
     * @return claim id fetched from request object
     * @since 06-Feb-2020
     */
    private static String getAndSetClaimIdInRequestObject(PeripheralServerRequest requestObject, String claimId) {
        String claimIdToReturn;
        EndpointConfigurationMappingConstants endpointConfigured =
                        extractRequetObjectMapping(requestObject.getClass().getSimpleName());
        if (endpointConfigured == null) {
            return null;
        }
        switch (endpointConfigured) {
            case PERIPHERALSERVERCASHDRAWERREQUESTDTO:
                PeripheralServerCashDrawerRequestDTO peripheralServerCashDrawerRequestDTO =
                                (PeripheralServerCashDrawerRequestDTO) requestObject;
                if (claimId != null) {
                    peripheralServerCashDrawerRequestDTO.setClaimId(claimId);
                }
                claimIdToReturn = peripheralServerCashDrawerRequestDTO.getClaimId();
                break;
            case PERIPHERALSERVERRECEIPTDATADTO:
                PeripheralServerReceiptDataDTO peripheralServerReceiptDataDTO =
                                (PeripheralServerReceiptDataDTO) requestObject;
                if (claimId != null) {
                    peripheralServerReceiptDataDTO.setClaimId(claimId);
                }
                claimIdToReturn = peripheralServerReceiptDataDTO.getClaimId();
                break;
            case PERIPHERALSERVERRECEIPTPRINTERXMLDTO:
                PeripheralServerReceiptPrinterXmlDTO peripheralServerReceiptPrinterXmlDTO =
                                (PeripheralServerReceiptPrinterXmlDTO) requestObject;
                if (claimId != null) {
                    peripheralServerReceiptPrinterXmlDTO.setClaimId(claimId);
                }
                claimIdToReturn = peripheralServerReceiptPrinterXmlDTO.getClaimId();
                break;
            case PERIPHERALSERVERPNDLABELREQUESTDTO:
                PeripheralServerPNDLabelRequestDTO pndLabelRequestDTO =
                                (PeripheralServerPNDLabelRequestDTO) requestObject;
                if (claimId != null) {
                    pndLabelRequestDTO.setClaimId(claimId);
                }
                claimIdToReturn = pndLabelRequestDTO.getClaimId();
                break;
            case PERIPHERALSERVERBINLABELREQUESTDTO:
                PeripheralServerBinLabelRequestDTO peripheralServerBinLabelRequestDTO =
                                (PeripheralServerBinLabelRequestDTO) requestObject;
                if (claimId != null) {
                    peripheralServerBinLabelRequestDTO.setClaimId(claimId);
                }
                claimIdToReturn = peripheralServerBinLabelRequestDTO.getClaimId();
                break;
            case PERIPHERALSERVERSHIPPINGLABELREQUESTDTO:
                PeripheralServerShippingLabelRequestDTO peripheralServerShippingLabelRequestDTO =
                                (PeripheralServerShippingLabelRequestDTO) requestObject;
                if (claimId != null) {
                    peripheralServerShippingLabelRequestDTO.setClaimId(claimId);
                }
                claimIdToReturn = peripheralServerShippingLabelRequestDTO.getClaimId();
                break;
            case PERIPHERALSERVERRELEASEREQUESTDTO:
            case PERIPHERALSERVERCLAIMREQUESTDTO:
            case PERIPHERALSERVERBARCODESCANNERCONFIGREQUESTDTO:
            case PERIPHERALSERVERCASHDRAWERSTATUSREQUESTDTO:
            default:
                claimIdToReturn = null;
        }
        return claimIdToReturn;
    }

    /**
     * This method check if request object class name has configuration mapped in
     * {@link EndpointConfigurationMappingConstants}
     * 
     * @param requestObjectClassName the request object class name
     * @return instance of {@link EndpointConfigurationMappingConstants} if configuration found<br>
     *         {@code null} if not found
     * @since 06-Feb-2020
     */
    private static EndpointConfigurationMappingConstants extractRequetObjectMapping(String requestObjectClassName) {
        try {
            return EndpointConfigurationMappingConstants.valueOf(requestObjectClassName.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    /**
     * This method creates the final URL to hit
     * 
     * @param requestObject request object containing IP, port.
     * @param requestEndpoint path of request URL
     * @return URL to hit which contain IP, port and the path
     * @since 05-Feb-2020
     */
    private static String generateURI(PeripheralServerRequest requestObject, StringBuilder requestEndpoint) {
        StringBuilder uriStringBuilder = new StringBuilder();
        uriStringBuilder.append(PeripheralServerConstants.PROTOCOL);
        uriStringBuilder.append(requestObject.getIp());
        uriStringBuilder.append(PeripheralServerConstants.COLON);
        uriStringBuilder.append(requestObject.getPort());
        uriStringBuilder.append(requestEndpoint);
        return uriStringBuilder.toString();
    }

    /**
     * This method sends request to peripheral server using Apache HTTP Client and map the status code and output
     * fetched to {@link PeripheralServerResponse} and returns it.
     * 
     * @param <U> response object class type
     * @param endpointConfig instance of {@link PeripheralServerEndpointConfig} containing request URL, method type and
     *        body.
     * @param responseType response type to which output is mapped
     * @return {@link PeripheralServerResponse} containing status code, output, errors if any.
     * @since 05-Feb-2020
     */
    private static <U extends CXSOutput> PeripheralServerResponse<U> sendRequestToPeripheralServer(
                    PeripheralServerEndpointConfig endpointConfig, Class<U> responseType) {
        String requestUrl = endpointConfig.getUrl();
        HttpMethod httpMethod = endpointConfig.getMethod();
        PeripheralServerRequest requestObj = endpointConfig.getRequest();
        logger.info("Processing request in Communication Layer");
        logger.debug("Request info received : [URL : {}, Type : {}, Body : {}]", requestUrl, httpMethod,
                        beautify(requestObj));
        // setting headers for the peripheral server HTTP request
        List<Header> headers = new ArrayList<>();
        Header headerContentType =
                        new BasicHeader(HttpHeaders.CONTENT_TYPE, PeripheralServerConstants.APPLICATION_JSON);
        Header headerAccept = new BasicHeader(HttpHeaders.CONTENT_TYPE, PeripheralServerConstants.APPLICATION_JSON);
        headers.add(headerContentType);
        headers.add(headerAccept);
        // create default peripheral server response
        PeripheralServerResponse<U> peripheralServerResponse = new PeripheralServerResponse<>();
        try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultHeaders(headers).build()) {
            ObjectMapper objectMapper = new ObjectMapper();
            RequestBuilder requestBuilder = RequestBuilder.create(httpMethod.name()).setUri(requestUrl);
            logger.info("Request is sent to Peripheral Server");
            // set request object in request body of HTTP request, if request object is
            // provided and HTTP method is not of GET type
            if (requestObj != null && !httpMethod.equals(HttpMethod.GET)) {
                String jsonInputString = objectMapper.writeValueAsString(requestObj);
                StringEntity claimEntity = new StringEntity(jsonInputString);
                requestBuilder.setEntity(claimEntity);
                logger.debug("Request info sent : [URL : {}, Type : {}, Body : {}]", requestUrl, httpMethod,
                                beautify(requestObj));
            } else {
                logger.debug("Request info sent : [URL : {}, Type : {}]", requestUrl, httpMethod);
            }
            HttpUriRequest httpRequest = requestBuilder.build();
            // do HTTP request and fetch response and set it to peripheral server response
            try (CloseableHttpResponse response = client.execute(httpRequest)) {
                peripheralServerResponse = extractAndSetResponseAndStatusCode(responseType, objectMapper, response);
            }
        } catch (IOException e) {
            logger.error("IOException has occured for request: [URL : {}, Type : {}] with exception: {}", requestUrl,
                            httpMethod.name(), ExceptionUtils.getStackTrace(e));
        } catch (Exception e) {
            logger.error("Unknown Exception has occured for request: [URL : {}, Type : {}] with exception: {}",
                            requestUrl, httpMethod.name(), ExceptionUtils.getStackTrace(e));
        }
        return peripheralServerResponse;
    }

    /**
     * This method extracts status code and output response from {@link CloseableHttpResponse} and sets it to
     * {@link PeripheralServerResponse}
     * 
     * @param <U> Response class type
     * @param responseType response type to which output is mapped
     * @param objectMapper instance of {@link ObjectMapper} which will be used to map response string to response class
     * @param response instance of {@link CloseableHttpResponse} containing status code and response in string format.
     * @return instance of {@link PeripheralServerResponse} where status code and output will be saved.
     * @throws IOException any Input Output Exception occurred while mapping or fetching response String
     * @since 05-Feb-2020
     */
    private static <U extends CXSOutput> PeripheralServerResponse<U> extractAndSetResponseAndStatusCode(
                    Class<U> responseType, ObjectMapper objectMapper, CloseableHttpResponse response)
                    throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("HTTP Response Status code received : {}", statusCode);
        // sets status code fetched from HTTP request to peripheral server response
        PeripheralServerResponse<U> peripheralServerResponse = new PeripheralServerResponse<>(statusCode, null);
        // converts response object to string formatted response
        HttpEntity responseEntity = response.getEntity();
        String responseString = responseToString(responseEntity);
        if (!responseString.equals(PeripheralServerConstants.EMPTY_STRING)) {
            String contentType = ContentType.getOrDefault(responseEntity).getMimeType();
            logger.info("HTTP Response type: {}, Response content received : {}", contentType,
                            beautify(responseString));
            if (contentType.equals(PeripheralServerConstants.APPLICATION_JSON) && responseType != null) {
                JavaType responseJavaType =
                                objectMapper.getTypeFactory().constructParametricType(CXSEnvelope.class, responseType);
                // converts response string to provided response class
                try {
                    peripheralServerResponse.setCxsEnvelope(objectMapper.readValue(responseString, responseJavaType));
                }
                // If response string is not successfully mapped to response class, set response type mismatch exception
                // as error to peripheral server response
                catch (JsonProcessingException e) {
                    peripheralServerResponse = new PeripheralServerResponse<>(
                                    CommunicationLayerErrorCodes.RESPONSE_TYPE_MISMATCH_EXCEPTION,
                                    responseType.getSimpleName());
                    logger.info(peripheralServerResponse.getErrors().get(0).getMessage());
                }
            } else {
                peripheralServerResponse = new PeripheralServerResponse<>();
                logger.info(peripheralServerResponse.getErrors().get(0).getMessage());
            }
        }
        return peripheralServerResponse;
    }

    /**
     * This method returns response string fetched from {@link HttpEntity} object
     * 
     * @param entity {@link HttpEntity} object containing {@link java.io.InputStream} which will be used to fetch
     *        response as String
     * @return String representation of response
     * @throws IOException any Input Output exception occurred
     * @since 05-Feb-2020
     */
    private static String responseToString(HttpEntity entity) throws IOException {
        // response has data, then convert it to string, else return empty string
        return entity != null
                        ? new BufferedReader(new InputStreamReader(entity.getContent())).lines()
                                        .collect(Collectors.joining(PeripheralServerConstants.DELIMITER_NEW_LINE))
                        : PeripheralServerConstants.EMPTY_STRING;
    }

    /**
     * This method returns formatted JSON string for an object
     * 
     * @param jsonObj Object to be beautified
     * @return beautified format JSON string of object
     * @since 10-Feb-2020
     */
    private static String beautify(Object jsonObj) {
        StringBuilder toReturn = new StringBuilder();
        if (jsonObj == null) {
            toReturn.append(PeripheralServerConstants.NULL_STRING);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                toReturn.append(PeripheralServerConstants.DELIMITER_NEW_LINE);
                toReturn.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj));
            } catch (JsonProcessingException e) {
                toReturn.append(jsonObj.toString());
            }
        }
        return toReturn.toString();
    }

    /**
     * This method returns formatted JSON string for a string
     * 
     * @param json String to be beautified
     * @return beautified format JSON string of string
     * @since 10-Feb-2020
     */
    private static String beautify(String json) {
        StringBuilder toReturn = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        try {
            toReturn.append(beautify(mapper.readValue(json, Object.class)));
        } catch (IOException e) {
            toReturn.append(json);
        }
        return toReturn.toString();
    }

}
