package com.fedex.dto;

import com.fedex.constants.PeripheralServerLabelFormatType;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a DTO class for LabelPrinter which contains
 * labelData, claimId and labelFormat and is implemented by every LabelPrinter
 * Request DTO.
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerLabelPrinterDTO extends PeripheralServerRequest {

    private static final long serialVersionUID = 2480034509553900147L;

    private String labelData;
    private String claimId;
    private PeripheralServerLabelFormatType labelFormat;

    protected PeripheralServerLabelPrinterDTO(String ip, String port) {
        super(ip, port);
    }

    public String getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public PeripheralServerLabelFormatType getLabelFormat() {
        return labelFormat;
    }

    public void setLabelFormat(PeripheralServerLabelFormatType labelFormat) {
        this.labelFormat = labelFormat;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerLabelPrinterDTO [labeldata=").append(labelData).append(", claimID=")
                        .append(claimId).append(", labelFormat=").append(labelFormat).append(", ip=").append(ip)
                        .append(", port=").append(port).append("]");
        return builder.toString();
    }

}
