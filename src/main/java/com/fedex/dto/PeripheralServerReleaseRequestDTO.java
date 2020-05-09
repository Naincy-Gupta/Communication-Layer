package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This is the Request DTO to Release Peripheral Server
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 03-Jan-2020
 */
public final class PeripheralServerReleaseRequestDTO extends PeripheralServerRequest {

    private static final long serialVersionUID = 5373866978429853446L;

    private String claimId;

    protected PeripheralServerReleaseRequestDTO(String ipAddress, String port) {
        super(ipAddress, port);
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerReleaseRequestDTO [claimId=").append(claimId).append(", ip=").append(ip)
                        .append(", port=").append(port).append("]");
        return builder.toString();
    }

}
