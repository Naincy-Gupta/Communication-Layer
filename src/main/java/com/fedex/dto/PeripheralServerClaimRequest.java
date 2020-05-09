/**
 * 
 */
package com.fedex.dto;

import java.io.Serializable;

import com.fedex.constants.PeripheralServerClaimType;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This is the DTO which contains claimType.
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerClaimRequest implements Serializable {

    private static final long serialVersionUID = 4452875569120433242L;

    private PeripheralServerClaimType claimType;

    public PeripheralServerClaimRequest() {}

    public PeripheralServerClaimRequest(PeripheralServerClaimType claimType) {
        this.claimType = claimType;
    }

    public PeripheralServerClaimType getClaimType() {
        return claimType;
    }

    public void setClaimType(PeripheralServerClaimType claimType) {
        this.claimType = claimType;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("PeripheralServerClaimRequestDTO [claimType=").append(claimType).append("]")
                        .toString();
    }

}
