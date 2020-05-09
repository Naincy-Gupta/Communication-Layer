package com.fedex.dto;

import com.fedex.cxs.dto.CXSOutput;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is response DTO which contains ClaimResponse.
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerClaimResponseDTO extends CXSOutput {

    private PeripheralServerClaimResponse peripheralServerClaimResponse;

    private PeripheralServerClaimResponseDTO() {}

    public PeripheralServerClaimResponse getClaim() {
        return peripheralServerClaimResponse;
    }

    public void setClaim(PeripheralServerClaimResponse peripheralServerClaimResponse) {
        this.peripheralServerClaimResponse = peripheralServerClaimResponse;
    }

    @Override
    public String toString() {
        return "PeripheralServerClaimResponseDTO [peripheralServerClaimResponse=" + peripheralServerClaimResponse + "]";
    }

}
