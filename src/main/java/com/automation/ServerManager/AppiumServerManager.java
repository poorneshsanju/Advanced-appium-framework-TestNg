package com.automation.ServerManager;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class AppiumServerManager {

    private static AppiumDriverLocalService appiumService;

    // Start Appium server with default or custom parameters
    public static void startServer() {
        if (!isAppiumServerRunning(4723)) {
            AppiumServiceBuilder builder = new AppiumServiceBuilder()
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723) // Default Appium port
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE) // To override any existing sessions
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                    .withLogFile(Paths.get("logs/appium.log").toFile()); // Log Appium output to a file

            appiumService = AppiumDriverLocalService.buildService(builder);
            appiumService.start();
            System.out.println("Appium Server started on port 4723");
        } else {
            System.out.println("Appium Server is already running on port 4723");
        }
    }

    // Stop Appium server
    public static void stopServer() {
        if (appiumService != null && appiumService.isRunning()) {
            appiumService.stop();
            System.out.println("Appium Server stopped.");
        } else {
            System.out.println("Appium Server is not running.");
        }
    }

    // Check if Appium server is running on the specified port
    private static boolean isAppiumServerRunning(int port) {
        boolean isServerRunning = false;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            // If no exception, port is available (server is not running)
        } catch (IOException e) {
            // Port is already in use (server is running)
            isServerRunning = true;
        }
        return isServerRunning;
    }

    // Forcibly stop Appium server by killing the process (if needed)
    public static void stopServerForcefully() {
        CommandLine command = new CommandLine("cmd");
        command.addArgument("/c");
        command.addArgument("taskkill /F /IM node.exe"); // Assuming Appium is running via Node.js
        DefaultExecutor executor = new DefaultExecutor();
        try {
            executor.execute(command);
            System.out.println("Appium Server process killed.");
        } catch (ExecuteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       stopServer();
    }
}
