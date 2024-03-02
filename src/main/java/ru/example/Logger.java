package ru.example;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    public static void log(String message, String logFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write("Date-Time: " + LocalDateTime.now() + "\n");
            writer.write("Message: " + message + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}