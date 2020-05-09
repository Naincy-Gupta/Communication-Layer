package com.fedex.dto;

import com.fedex.cxs.dto.CXSOutput;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a response DTO which contains barcode Scanner
 * response
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerBarcodeScannerResponseDTO extends CXSOutput {

    private PeripheralServerBarcodeScannerResponse peripheralServerBarcodeScannerResponse;

    private PeripheralServerBarcodeScannerResponseDTO() {}

    public PeripheralServerBarcodeScannerResponse getBarcodeScannerResponse() {
        return peripheralServerBarcodeScannerResponse;
    }

    public void setBarcodeScannerResponse(
                    PeripheralServerBarcodeScannerResponse peripheralServerBarcodeScannerResponse) {
        this.peripheralServerBarcodeScannerResponse = peripheralServerBarcodeScannerResponse;
    }

    @Override
    public String toString() {
        return "PeripheralServerBarcodeScannerResponseDTO [peripheralServerBarcodeScannerResponse="
                        + peripheralServerBarcodeScannerResponse + "]";
    }

}
