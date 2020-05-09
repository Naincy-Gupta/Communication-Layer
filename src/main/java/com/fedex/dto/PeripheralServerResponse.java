package com.fedex.dto;

import java.util.Collections;
import java.util.List;

import com.fedex.cxs.dto.CXSEnvelope;
import com.fedex.cxs.dto.CXSError;
import com.fedex.cxs.dto.CXSOutput;
import com.fedex.errors.CommunicationLayerErrorCodes;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is a Response class from Communication Layer
 * 
 * @author Namit Jain [3696360]
 * @version 1.0.0
 * @since 30-Jan-2020
 */
public class PeripheralServerResponse<T extends CXSOutput> {

    private String claimId;
    private int statusCode;
    private CXSEnvelope<T> cxsEnvelope;

    public PeripheralServerResponse() {
        this(CommunicationLayerErrorCodes.PERIPHERAL_SERVER_UNREACHABLE);
    }

    public PeripheralServerResponse(CommunicationLayerErrorCodes communicationLayerErrorCodes) {
        this(communicationLayerErrorCodes, null);
    }

    public PeripheralServerResponse(CommunicationLayerErrorCodes communicationLayerErrorCodes, String messageData) {
        this.statusCode = communicationLayerErrorCodes.getStatusCode();
        String errorMessage = communicationLayerErrorCodes.getErrorMessage();
        if (messageData != null) {
            errorMessage = errorMessage.replace("{}", messageData);
        }
        this.cxsEnvelope = CXSEnvelope.error(new CXSError(communicationLayerErrorCodes.getErrorCode(), errorMessage));
    }

    public PeripheralServerResponse(int statusCode, CXSEnvelope<T> cxsEnvelope) {
        this.statusCode = statusCode;
        this.cxsEnvelope = cxsEnvelope;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setCxsEnvelope(CXSEnvelope<T> cxsEnvelope) {
        this.cxsEnvelope = cxsEnvelope;
    }

    public T getOutput() {
        if (cxsEnvelope != null) {
            return cxsEnvelope.getOutput();
        } else {
            return null;
        }
    }

    public List<CXSError> getErrors() {
        if (cxsEnvelope != null) {
            return cxsEnvelope.getErrors();
        } else {
            return Collections.emptyList();
        }
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
        builder.append("PeripheralServerResponse [claimId=").append(claimId).append(", statusCode=").append(statusCode)
                        .append(", output=").append(getOutput()).append(", errors=").append(getErrors()).append("]");
        return builder.toString();
    }

}
