package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a Request DTO class for Receipt Printer Xml
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public final class PeripheralServerReceiptPrinterXmlDTO extends PeripheralServerRequest {

    private static final long serialVersionUID = -7961089102261412583L;

    protected PeripheralServerReceiptPrinterXmlDTO(String ip, String port) {
        super(ip, port);
    }

    private String claimId;
    private String receiptData;

    public String getReceiptData() {
        return receiptData;
    }

    public void setReceiptData(String receiptData) {
        this.receiptData = receiptData;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerReceiptPrinterXmlDTO [receiptData=").append(receiptData).append(", ip=")
                        .append(ip).append(", port=").append(port).append("]");
        return builder.toString();
    }

}
