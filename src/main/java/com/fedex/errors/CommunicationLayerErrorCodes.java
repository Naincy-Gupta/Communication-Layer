package com.fedex.errors;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This enum consists of Communication layer response related error details
 * 
 * @author Namit Jain [3696360]
 * @version 1.0.0
 * @since 12-Feb-2020
 */
public enum CommunicationLayerErrorCodes {
    // 406 Not Acceptable
    REQUEST_TYPE_INVALID_EXCEPTION(406, "REQUESTTYPE.INVALID.EXCEPTION",
                    "No endpoint configuration was found for request DTO: '{}'"),
    // The 504 Gateway Timeout error is an HTTP status code that means that one server did not receive a timely
    // response from another server that it was accessing while attempting to load the web page or fill another
    // request by the browser.
    PERIPHERAL_SERVER_UNREACHABLE(504, "PERIPHERAL.SERVER.UNREACHABLE",
                    "Peripheral Server is unreachable. Please try again."),
    // 406 Not Acceptable
    RESPONSE_TYPE_MISMATCH_EXCEPTION(406, "RESPONSETYPE.MISMATCH.EXCEPTION",
                    "Could not map response JSON received from Peripheral server to provided response class : '{}'. Please try again.");

    private int statusCode;
    private String errorMessage;
    private String errorCode;

    private CommunicationLayerErrorCodes(int statusCode, String errorCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
