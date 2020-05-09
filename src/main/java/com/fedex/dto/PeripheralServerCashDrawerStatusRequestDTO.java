package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - PI20.5 - OTP Device Virtualization Implementation and Packaging<br>
 * Description - This is a request class for the cash Drawer Status
 * 
 * @author Naincy Gupta [3777204]
 * @version 1.0.0
 * @since 31-March-2020
 */
public class PeripheralServerCashDrawerStatusRequestDTO extends PeripheralServerRequest {

    private static final long serialVersionUID = -4091667330815003286L;

    protected PeripheralServerCashDrawerStatusRequestDTO(String ip, String port) {
        super(ip, port);
    }

}
