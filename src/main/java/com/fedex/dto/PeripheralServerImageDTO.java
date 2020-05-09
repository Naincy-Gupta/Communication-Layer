package com.fedex.dto;

import java.io.Serializable;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a DTO class for Image
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerImageDTO implements Serializable {

    private static final long serialVersionUID = -2565469073900629614L;

    private String alignment;
    private String url;
    private String imageBase64;
    private String imageType;

    public PeripheralServerImageDTO() {}

    public PeripheralServerImageDTO(final String alignment, final String url, final String imageBase64,
                    final String imageType) {
        this.alignment = alignment;
        this.url = url;
        this.imageBase64 = imageBase64;
        this.imageType = imageType;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerImageDTO [alignment=").append(alignment).append(", url=").append(url)
                        .append(", imageBase64=").append(imageBase64).append(", extension=").append(imageType)
                        .append("]");
        return builder.toString();
    }
}
