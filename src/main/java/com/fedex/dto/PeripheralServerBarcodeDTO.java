package com.fedex.dto;

import java.io.Serializable;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a DTO class for Barcode
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerBarcodeDTO implements Serializable {

    private static final long serialVersionUID = -419299288463138410L;

    private String data;
    private String symbology;
    private Integer height;
    private Integer width;
    private String textPosition;
    private String alignment;

    public PeripheralServerBarcodeDTO() {}

    public PeripheralServerBarcodeDTO(final String data, final String symbology, final Integer height,
                    final Integer width, final String textPosition, final String alignment) {
        this.data = data;
        this.symbology = symbology;
        this.height = height;
        this.width = width;
        this.textPosition = textPosition;
        this.alignment = alignment;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSymbology() {
        return symbology;
    }

    public void setSymbology(String symbology) {
        this.symbology = symbology;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getTextPosition() {
        return textPosition;
    }

    public void setTextPosition(String textPosition) {
        this.textPosition = textPosition;
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
        builder.append("Barcode [data=").append(data).append(", symbology=").append(symbology).append(", height=")
                        .append(height).append(", width=").append(width).append(", textPosition=").append(textPosition)
                        .append(", alignment=").append(alignment).append("]");
        return builder.toString();
    }

}
