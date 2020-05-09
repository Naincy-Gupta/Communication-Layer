package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is DTO which contains endpoint and channels.
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerBarcodeScannerConfigResponse {

    private String endpoint;
    private String channel;

    private PeripheralServerBarcodeScannerConfigResponse() {}

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerBarcodeScannerConfigResponse [endpoint=").append(endpoint).append(", channel=")
                        .append(channel).append("]");
        return builder.toString();
    }

}
