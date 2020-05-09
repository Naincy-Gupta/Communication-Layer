package com.fedex.dto;

import com.fedex.constants.HttpMethod;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class contains endpoint configuration like request URL
 * path, request method type and request body.
 * 
 * @author Namit Jain [3696360]
 * @version 1.0.0
 * @since 05-Feb-2020
 */
public class PeripheralServerEndpointConfig {

    private String url;
    private HttpMethod method;
    private PeripheralServerRequest request;

    public PeripheralServerEndpointConfig(String url, HttpMethod method, PeripheralServerRequest request) {
        this.url = url;
        this.method = method;
        this.request = request;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public PeripheralServerRequest getRequest() {
        return request;
    }

    public void setRequest(PeripheralServerRequest request) {
        this.request = request;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerEndpointConfig [url=").append(url).append(", method=").append(method)
                        .append(", request=").append(request).append("]");
        return builder.toString();
    }

}
