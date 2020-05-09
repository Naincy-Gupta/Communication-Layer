package com.fedex.constants;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class has mapping of request DTOs to respective request URL, request HTTP Method type and a
 * boolean value which indicates if request body is needed or not
 * 
 * @author Namit Jain [3696360]
 * @version 1.0.0
 * @since 06-Feb-2020
 */
public enum EndpointConfigurationMappingConstants {

    PERIPHERALSERVERCLAIMREQUESTDTO("/peripherals/fedexoffice/v1/claims", HttpMethod.POST, true),
    PERIPHERALSERVERCASHDRAWERREQUESTDTO("/peripherals/fedexoffice/v1/opencashdrawer", HttpMethod.POST, true),
    PERIPHERALSERVERBARCODESCANNERCONFIGREQUESTDTO("/peripherals/fedexoffice/v1/scanbarcode", HttpMethod.GET, false),
    PERIPHERALSERVERRELEASEREQUESTDTO("/peripherals/fedexoffice/v1/claims/", HttpMethod.DELETE, false),
    PERIPHERALSERVERRECEIPTDATADTO("/peripherals/fedexoffice/v1/printjsonreceipt", HttpMethod.POST, true),
    PERIPHERALSERVERRECEIPTPRINTERXMLDTO("/peripherals/fedexoffice/v1/printxmlreceipt", HttpMethod.POST, true),
    PERIPHERALSERVERPNDLABELREQUESTDTO("/peripherals/fedexoffice/v1/printpndlabel", HttpMethod.POST, true),
    PERIPHERALSERVERBINLABELREQUESTDTO("/peripherals/fedexoffice/v1/printbinlabel", HttpMethod.POST, true),
    PERIPHERALSERVERSHIPPINGLABELREQUESTDTO("/peripherals/fedexoffice/v1/printshippinglabel", HttpMethod.POST, true),
    PERIPHERALSERVERCASHDRAWERSTATUSREQUESTDTO("/peripherals/fedexoffice/v1/cashdrawerstatus", HttpMethod.GET, false);

    private String requestUrl;
    private HttpMethod requestType;
    private boolean sendRequestBody;

    @SuppressWarnings("squid:UnusedPrivateMethod")
    private EndpointConfigurationMappingConstants(String requestUrl, HttpMethod requestType, boolean sendRequestBody) {
        this.requestUrl = requestUrl;
        this.requestType = requestType;
        this.sendRequestBody = sendRequestBody;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public HttpMethod getRequestType() {
        return requestType;
    }

    public boolean isSendRequestBody() {
        return sendRequestBody;
    }

}
