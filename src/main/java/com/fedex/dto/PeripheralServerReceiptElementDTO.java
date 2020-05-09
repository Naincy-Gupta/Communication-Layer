package com.fedex.dto;

import java.io.Serializable;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.
 * 
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a DTO class for Receipt Element contains barcode,
 * image, receiptText and cut.
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerReceiptElementDTO implements Serializable {

    private static final long serialVersionUID = -2068511076381962540L;

    private PeripheralServerBarcodeDTO barcode;
    private PeripheralServerImageDTO image;
    private PeripheralServerReceiptTextDTO receiptText;
    private PeripheralServerCutDTO cut;

    public PeripheralServerReceiptElementDTO() {}

    public PeripheralServerReceiptElementDTO(final PeripheralServerBarcodeDTO barcode,
                    final PeripheralServerImageDTO image, final PeripheralServerReceiptTextDTO receiptText,
                    final PeripheralServerCutDTO cut) {
        this.barcode = barcode;
        this.image = image;
        this.receiptText = receiptText;
        this.cut = cut;
    }

    public PeripheralServerBarcodeDTO getBarcode() {
        return barcode;
    }

    public void setBarcode(PeripheralServerBarcodeDTO barcode) {
        this.barcode = barcode;
    }

    public PeripheralServerImageDTO getImage() {
        return image;
    }

    public void setImage(PeripheralServerImageDTO image) {
        this.image = image;
    }

    public PeripheralServerReceiptTextDTO getReceiptText() {
        return receiptText;
    }

    public void setReceiptText(PeripheralServerReceiptTextDTO receiptText) {
        this.receiptText = receiptText;
    }

    public PeripheralServerCutDTO getCut() {
        return cut;
    }

    public void setCut(PeripheralServerCutDTO cut) {
        this.cut = cut;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerReceiptElementDTO [barcode=").append(barcode).append(", image=").append(image)
                        .append(", receiptText=").append(receiptText).append(", cut=").append(cut).append("]");
        return builder.toString();
    }

}
