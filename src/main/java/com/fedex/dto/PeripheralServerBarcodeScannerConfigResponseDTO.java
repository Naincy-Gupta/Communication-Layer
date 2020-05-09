package com.fedex.dto;

import com.fedex.cxs.dto.CXSOutput;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a response DTO which contains barcode scanner
 * config response.
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerBarcodeScannerConfigResponseDTO extends CXSOutput {

    private PeripheralServerBarcodeScannerConfigResponse peripheralServerBarcodeScannerConfigResponse;

    private PeripheralServerBarcodeScannerConfigResponseDTO() {}

    public PeripheralServerBarcodeScannerConfigResponse getBarcodeScannerConfigResponse() {
        return peripheralServerBarcodeScannerConfigResponse;
    }

    public void setBarcodeScannerConfigResponse(
                    PeripheralServerBarcodeScannerConfigResponse peripheralServerBarcodeScannerConfigResponse) {
        this.peripheralServerBarcodeScannerConfigResponse = peripheralServerBarcodeScannerConfigResponse;
    }

    @Override
    public String toString() {
        return "PeripheralServerBarcodeScannerConfigResponseDTO [peripheralServerBarcodeScannerConfigResponse="
                        + peripheralServerBarcodeScannerConfigResponse + "]";
    }

}
