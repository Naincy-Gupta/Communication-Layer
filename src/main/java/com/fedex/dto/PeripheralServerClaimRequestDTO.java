package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This is the Request DTO to Claim Peripheral Server contains
 * claimRequest.
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */

public final class PeripheralServerClaimRequestDTO extends PeripheralServerRequest {

    private static final long serialVersionUID = 9087332622441176596L;

    private PeripheralServerClaimRequest claimRequest;

    protected PeripheralServerClaimRequestDTO(String ip, String port) {
        super(ip, port);
    }

    public PeripheralServerClaimRequest getClaimRequest() {
        return claimRequest;
    }

    public void setClaimRequest(PeripheralServerClaimRequest claimRequest) {
        this.claimRequest = claimRequest;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerClaimRequestDTO [claimRequest=").append(claimRequest).append(", ip=").append(ip)
                        .append(", port=").append(port).append("]");
        return builder.toString();
    }

}
