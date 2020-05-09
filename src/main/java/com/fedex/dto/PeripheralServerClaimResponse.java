package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is DTO contains claimId and idleTimeOut in seconds.
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerClaimResponse {

    private String claimId;
    private String idleTimeOutSeconds;

    private PeripheralServerClaimResponse() {}

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getIdleTimeOutSeconds() {
        return idleTimeOutSeconds;
    }

    public void setIdleTimeOutSeconds(String idleTimeOutSeconds) {
        this.idleTimeOutSeconds = idleTimeOutSeconds;
    }

    @Override
    public String toString() {
        return "PeripheralServerClaimResponse [claimId=" + claimId + ", idleTimeOutSeconds=" + idleTimeOutSeconds + "]";
    }

}
