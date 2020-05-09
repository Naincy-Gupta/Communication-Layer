package com.fedex.dto;

import java.util.List;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a Request DTO class for Receipt Data which
 * contains claimId and receiptElements.
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public final class PeripheralServerReceiptDataDTO extends PeripheralServerRequest {

    private static final long serialVersionUID = -8076652115335527592L;

    private String claimId;
    private List<PeripheralServerReceiptElementDTO> receiptElements;

    public List<PeripheralServerReceiptElementDTO> getReceiptElements() {
        return receiptElements;
    }

    protected PeripheralServerReceiptDataDTO(String ip, String port) {
        super(ip, port);
    }

    public void setReceiptElements(List<PeripheralServerReceiptElementDTO> receiptElements) {
        this.receiptElements = receiptElements;
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
        builder.append("ReceiptData [ClaimID=").append(claimId).append(", receiptElements=").append(receiptElements)
                        .append(", ip=").append(ip).append(", port=").append(port).append("]");
        return builder.toString();
    }

}
