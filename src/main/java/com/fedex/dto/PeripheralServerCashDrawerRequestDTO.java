package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a Request DTO class for CashDrawerRequest
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public final class PeripheralServerCashDrawerRequestDTO extends PeripheralServerRequest {

    private static final long serialVersionUID = -1821761662650118053L;

    private String claimId;

    protected PeripheralServerCashDrawerRequestDTO(String ip, String port) {
        super(ip, port);
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
        builder.append("PeripheralServerCashDrawerRequestDTO [claimId=").append(claimId).append(", ip=").append(ip)
                        .append(", port=").append(port).append("]");
        return builder.toString();
    }
}
