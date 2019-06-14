---
services: iot-hub 
platforms: java
author: azure-iot-sdks
modified by croe10
---

# Azure IoT Samples for Java

azure-iot-samples-java provides a set of easy-to-understand, continuously-tested samples for connecting to Azure IoT Hub via Azure/azure-iot-sdk-java.

## Need support?
If you run into any issues with these samples, please file an issue on our main repository [azure-iot-sdk-java](https://github.com/Azure/azure-iot-sdk-java) for faster turnaround.

## Prerequisites

- Java SE 8 or later on your development machine.  You can download Java for multiple platforms from [Oracle](http://www.oracle.com/technetwork/java/javase/downloads/index.html).  You can verify the current version of Java on your development machine using 'java --version'.

- Maven 3 for building the sample.  You can download Maven for multiple platforms from [Apache Maven](https://maven.apache.org/download.cgi).  You can verify the current version of Maven on your development machine using 'mvn --version'

## Resources

- [azure-iot-sdk-java](https://github.com/Azure/azure-iot-sdk-java): contains the source code for Azure IoT Java SDK.
- [Azure IoT Hub Documentation](https://docs.microsoft.com/azure/iot-hub/)

## Running
- Change the variable 'iotHubConnectionString'   to the string obtained by running 'az iot hub show-connection-string --hub-name mg-test-hub' from the azure cloud console
- Change the variable 'deviceId' to the device name that you are sending to. 
eg.     'public static final String deviceId = "CamRaspPiEmulator";'

- build with 'mvn clean package'
- run with 'java -jar target/back-end-application-1.0.0-with-deps.jar'

- This sends a message to the iotHub with the method 'AutonomousVehicleMethod' and the payload
{method:nudge}

