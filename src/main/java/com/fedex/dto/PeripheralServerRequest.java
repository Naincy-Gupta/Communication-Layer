package com.fedex.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is request class which contains ip address and port and is implemented by every peripheral
 * server request object
 * 
 * @author Malay Biswal [3860759]
 * @version 1.0.0
 * @since 31-Jan-2020
 */
@JsonIgnoreProperties({"ip", "port"})
public class PeripheralServerRequest implements Serializable {

    private static final long serialVersionUID = -4039507638362239174L;

    protected String ip;
    protected String port;

    protected PeripheralServerRequest(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PeripheralServerRequest [ip=").append(ip).append(", port=").append(port).append("]");
        return builder.toString();
    }

}
