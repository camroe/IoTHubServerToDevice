// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

// This application uses the Azure IoT Hub service SDK for Java
// For samples see: https://github.com/Azure/azure-iot-sdk-java/tree/master/service/iot-service-samples

package com.microsoft.docs.iothub.samples;

import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceMethod;
import com.microsoft.azure.sdk.iot.service.devicetwin.MethodResult;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BackEndApplication {

    // Connection string for your IoT Hub
    // az iot hub show-connection-string --hub-name {your iot hub name}
    /*
    az iot hub show-connection-string --hub-name mg-test-hub
{
  "connectionString": "HostName=mg-test-hub.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=0khNfrd0/s0MCYZqu7KTM/3xqUzbN/xJ5JJ3yJY6QSY="
}
     */
    public static final String iotHubConnectionString = "HostName=mg-test-hub.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=0khNfrd0/s0MCYZqu7KTM/3xqUzbN/xJ5JJ3yJY6QSY=";
/*
az iot hub device-identity show-connection-string --hub-name mg-test-hub --device-id CamRaspPiEmulator
"HostName=mg-test-hub.azure-devices.net;DeviceId=CamRaspPiEmulator;SharedAccessKey=OYX5aoNd3c8phYHj1lNBClZzKefD+yj3KkgyYV40UV4="
 */
    // Device to call direct method on.
    public static final String deviceId = "CamRaspPiEmulator";

    // Name of direct method and payload.
    public static final String methodName = "AutonomousVehicleRequest";
    private static int payload = 10; // Number of seconds for telemetry interval.

    public static final Long responseTimeout = TimeUnit.SECONDS.toSeconds(30);
    public static final Long connectTimeout = TimeUnit.SECONDS.toSeconds(5);

    public static void main(String[] args) {
        int time;
        if (args.length > 0) {
            try {
                time = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Argument:" + args[0] + " must be an integer.");
                System.out.println("Using Default of 10 seconds for time.");
                time = 10;
            }
        } else {
            System.out.println("No Arguments given, using 10 seconds as the default time to set the telemetry time.");
            time = 10;
        }
        payload = time;
        try {
            System.out.println("Calling direct method...");

            // Create a DeviceMethod instance to call a direct method.
            DeviceMethod methodClient = DeviceMethod.createFromConnectionString(iotHubConnectionString);

            // Call the direct method.
            String payloadStr = "{method:" + "nudge}";
            System.out.println(payloadStr);
            MethodResult result = methodClient.invoke(deviceId, methodName, responseTimeout, connectTimeout, payloadStr);

            if (result == null) {
                throw new IOException("Direct method invoke returns null");
            }

            // Show the acknowledgement from the device.
            System.out.println("Status: " + result.getStatus());
            System.out.println("Response: " + result.getPayload());
        } catch (IotHubException e) {
            System.out.println("IotHubException calling direct method:");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException calling direct method:");
            System.out.println(e.getMessage());
        }
        System.out.println("Done!");
    }
}
