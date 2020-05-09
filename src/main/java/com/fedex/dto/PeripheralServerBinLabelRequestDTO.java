package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is request DTO for Bin label
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public final class PeripheralServerBinLabelRequestDTO extends PeripheralServerLabelPrinterDTO {

    private static final long serialVersionUID = -331915273076867237L;

    protected PeripheralServerBinLabelRequestDTO(String ip, String port) {
        super(ip, port);
    }

}
