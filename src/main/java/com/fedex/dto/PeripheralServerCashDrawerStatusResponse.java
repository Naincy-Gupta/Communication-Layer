package com.fedex.dto;

import com.fedex.constants.PeripheralServerCashDrawerStatusConstants;


/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - PI20.5 - OTP Device Virtualization Implementation and Packaging<br>
 * Description - This is a response class for Cash Drawer Status
 * 
 * @author Naincy Gupta [3777204]
 * @version 1.0.0
 * @since 31-March-2020
 */
public class PeripheralServerCashDrawerStatusResponse {

    private String peripheralName;

    private PeripheralServerCashDrawerStatusConstants status;

    private PeripheralServerCashDrawerStatusResponse() {}

    public String getPeripheralName() {
        return peripheralName;
    }

    public void setPeripheralName(String peripheralName) {
        this.peripheralName = peripheralName;
    }

    public PeripheralServerCashDrawerStatusConstants getStatus() {
        return status;
    }

    public void setStatus(PeripheralServerCashDrawerStatusConstants status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerCashDrawerStatusResponse [peripheralName=").append(peripheralName)
                        .append(", status=").append(status).append("]");
        return builder.toString();
    }


}
