package com.fedex.dto;

import java.io.Serializable;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a DTO class for Cut
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerCutDTO implements Serializable {

    private static final long serialVersionUID = 49947202960841238L;

    private Boolean cutReceipt;
    private int percentage;

    public PeripheralServerCutDTO() {
        this.cutReceipt = true;
        this.percentage = 100;
    }

    public PeripheralServerCutDTO(final Boolean cutReceipt, final int percentage) {
        this.cutReceipt = cutReceipt;
        this.percentage = percentage;
    }

    public Boolean isCutReceipt() {
        return cutReceipt;
    }

    public void setCutReceipt(Boolean cutReceipt) {
        this.cutReceipt = cutReceipt;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cut [cutReceipt=").append(cutReceipt).append(", percentage=").append(percentage).append("]");
        return builder.toString();
    }

}
