package com.fedex.dto;

import com.fedex.cxs.dto.CXSOutput;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - PI20.5 - OTP Device Virtualization Implementation and Packaging<br>
 * Description - This class is a response DTO which contains Cash Drawer Status Response
 * 
 * @author Naincy Gupta [3777204]
 * @version 1.0.0
 * @since 31-March-2020
 */
public class PeripheralServerCashDrawerStatusResponseDTO extends CXSOutput {
    private PeripheralServerCashDrawerStatusResponse cashDrawerStatus;

    private PeripheralServerCashDrawerStatusResponseDTO() {}

    public PeripheralServerCashDrawerStatusResponse getCashDrawerStatus() {
        return cashDrawerStatus;
    }

    public void setCashDrawerStatus(PeripheralServerCashDrawerStatusResponse cashDrawerStatus) {
        this.cashDrawerStatus = cashDrawerStatus;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerCashDrawerStatusResponseDTO [peripheralServerCashDrawerStatusResponse=")
                        .append(cashDrawerStatus).append("]");
        return builder.toString();
    }
}
