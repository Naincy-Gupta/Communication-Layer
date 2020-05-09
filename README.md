# Peripheral Server Communication Layer

The communication layer is a standalone java application which serves the purpose of communication bridge between peripheral server and legacy applications such as OTP or SSM to interact with peripheral devices.

Communication layer helps legacy application to communicate with Retail Peripheral Devices such as Receipt Printer, Barcode Scanner, Scales etc. and allow them to share the same set of devices between multiple applications.

The `Apache HTTPClient` is used to communicate with the peripheral server. Where the Client has to send a PeripheralServerRequest along with its IP Address and port number and will get a PeripheralServerResponse.

Some error scenarios are handled internally like : 
- Reclaim Scenario in case claim id expires
- Timeout Scenario when peripheral server is not reachable

Peripheral-otp-communication-layer is published at Nexus Repository Snapshot as under
 - Group ID  - eai3530951.com.fedex.peripherals
 - Artifact ID - rtl-peripheral-comm-layer
 
 # API - Getting Started
 - Make sure you have access to peripheral-otp-communication-layer project(https://gitlab.prod.fedex.com/APP3530951/peripheral-otp-communication-layer.git)
 - Clone the repository locally -- git clone https://gitlab.prod.fedex.com/APP3530951/peripheral-otp-communication-layer.git
 - Build the JAR file with maven command --  mvn install
 