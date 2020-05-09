package com.fedex.dto;

import java.io.Serializable;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a DTO class for Receipt Text contains text and
 * alignment.
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerReceiptTextDTO implements Serializable {

    private static final long serialVersionUID = 2550712291160230363L;

    private String text;
    private String alignment;

    public PeripheralServerReceiptTextDTO() {}

    public PeripheralServerReceiptTextDTO(final String text, final String alignment) {
        this.text = text;
        this.alignment = alignment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReceiptText [text=").append(text).append(", alignment=").append(alignment).append("]");
        return builder.toString();
    }

}
