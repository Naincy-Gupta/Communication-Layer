package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is request DTO for PND label
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public final class PeripheralServerPNDLabelRequestDTO extends PeripheralServerLabelPrinterDTO {

    private static final long serialVersionUID = -4091667330815003286L;

    protected PeripheralServerPNDLabelRequestDTO(String ip, String port) {
        super(ip, port);
    }

}
