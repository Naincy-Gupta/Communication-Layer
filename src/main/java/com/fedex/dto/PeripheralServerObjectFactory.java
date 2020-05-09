package com.fedex.dto;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is the objectFactory implementation returning specific DTO request object
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 03-Feb-2020
 */
public final class PeripheralServerObjectFactory {

    private PeripheralServerObjectFactory() {}

    /**
     * This method gives claim request object
     * 
     * @param ip the IP address of the client
     * @param port the port of the client
     * @return instance of {@link PeripheralServerClaimRequestDTO} which also contains IP address and port provided
     */
    public static PeripheralServerClaimRequestDTO getClaimRequest(String ip, String port) {
        return new PeripheralServerClaimRequestDTO(ip, port);
    }

    /**
     * This method gives release request object
     * 
     * @param ip the IP address of the client
     * @param port the port of the client
     * @return instance of {@link PeripheralServerReleaseRequestDTO} which also contains IP address and port provided
     */
    public static PeripheralServerReleaseRequestDTO getReleaseRequest(String ip, String port) {
        return new PeripheralServerReleaseRequestDTO(ip, port);
    }

    /**
     * This method gives Barcode Scanner request object
     * 
     * @param ip the IP address of the client
     * @param port the port of the client
     * @return instance of {@link PeripheralServerBarcodeScannerConfigRequestDTO} which also contains IP address and
     *         port provided
     */
    public static PeripheralServerBarcodeScannerConfigRequestDTO getBarcodeScannerRequest(String ip, String port) {
        return new PeripheralServerBarcodeScannerConfigRequestDTO(ip, port);
    }

    /**
     * This method gives Cash Drawer request object
     * 
     * @param ip the IP address of the client
     * @param port the port of the client
     * @return instance of {@link PeripheralServerCashDrawerRequestDTO} which also contains IP address and port provided
     */
    public static PeripheralServerCashDrawerRequestDTO getCashDrawerRequset(String ip, String port) {
        return new PeripheralServerCashDrawerRequestDTO(ip, port);
    }

    /**
     * This method gives PND Label request object
     * 
     * @param ip the IP address of the client
     * @param port the port of the client
     * @return instance of {@link PeripheralServerPNDLabelRequestDTO} which also contains IP address and port provided
     */
    public static PeripheralServerPNDLabelRequestDTO getPNDLabelRequest(String ip, String port) {
        return new PeripheralServerPNDLabelRequestDTO(ip, port);
    }

    /**
     * This method gives Bin Label request object
     * 
     * @param ip the IP address of the client
     * @param port the port of the client
     * @return instance of {@link PeripheralServerBinLabelRequestDTO} which also contains IP address and port provided
     */
    public static PeripheralServerBinLabelRequestDTO getBinLabelRequest(String ip, String port) {
        return new PeripheralServerBinLabelRequestDTO(ip, port);
    }

    /**
     * This method gives Shipping Label request object
     * 
     * @param ip the IP address of the client
     * @param port the port of the client
     * @return instance of {@link PeripheralServerShippingLabelRequestDTO} which also contains IP address and port
     *         provided
     */
    public static PeripheralServerShippingLabelRequestDTO getShippingLabelRequest(String ip, String port) {
        return new PeripheralServerShippingLabelRequestDTO(ip, port);
    }

    /**
     * This method gives Receipt Printer JSON type request object
     * 
     * @param ip the IP address of the client
     * @param port the port of the client
     * @return instance of {@link PeripheralServerReceiptDataDTO} which also contains IP address and port provided
     */
    public static PeripheralServerReceiptDataDTO getReceiptPrinterRequest(String ip, String port) {
        return new PeripheralServerReceiptDataDTO(ip, port);
    }

    /**
     * This method gives Receipt Printer Xml type request object
     * 
     * @param ip the IP address of the client
     * @param port the port of the client
     * @return instance of {@link PeripheralServerReceiptPrinterXmlDTO} which also contains IP address and port provided
     */
    public static PeripheralServerReceiptPrinterXmlDTO getReceiptPrinterXmlRequest(String ip, String port) {
        return new PeripheralServerReceiptPrinterXmlDTO(ip, port);
    }

    /**
     * This method gives Cash Drawer Status request object
     * 
     * @param ip the IP address of the client
     * @param port the port of the client
     * @return instance of {@link PeripheralServerCashDrawerStatusRequestDTO} which also contains IP address and port
     *         provided
     */
    public static PeripheralServerCashDrawerStatusRequestDTO getCashDrawerStatusRequest(String ip, String port) {
        return new PeripheralServerCashDrawerStatusRequestDTO(ip, port);
    }
}
