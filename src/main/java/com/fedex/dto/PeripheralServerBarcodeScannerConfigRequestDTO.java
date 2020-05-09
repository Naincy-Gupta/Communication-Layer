package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a Request DTO class for BarcodeScannerConfigRequest
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public final class PeripheralServerBarcodeScannerConfigRequestDTO extends PeripheralServerRequest {

    private static final long serialVersionUID = 4842471708386790082L;

    protected PeripheralServerBarcodeScannerConfigRequestDTO(String ip, String port) {
        super(ip, port);
    }

}
