package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a DTO which contains scanned barcode data and its
 * data type
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerBarcodeScannerResponse {

    private String scannedValue;
    private String scannedDataType;

    private PeripheralServerBarcodeScannerResponse() {}

    public String getScannedValue() {
        return scannedValue;
    }

    public void setScannedValue(String scannedValue) {
        this.scannedValue = scannedValue;
    }

    public String getScannedDataType() {
        return scannedDataType;
    }

    public void setScannedDataType(String scannedDataType) {
        this.scannedDataType = scannedDataType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerBarcodeScannerResponse [scannedValue=").append(scannedValue)
                        .append(", scannedDataType=").append(scannedDataType).append("]");
        return builder.toString();
    }

}
