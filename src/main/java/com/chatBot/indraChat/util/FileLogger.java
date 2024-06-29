package com.chatBot.indraChat.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.chatBot.indraChat.constants.AppConstants;


public class FileLogger {
	
	
	private static Path path;

	 private static String getLogFileName() {
	        String timeStamp = new SimpleDateFormat("dd_MM_yyyy_HHmmss").format(new Date());
	        return "log_ChatBot_" + timeStamp + ".txt";
	    }

    static {
        // Ensures the log file is created on application startup
    	try {
            Path logDirPath = Paths.get(AppConstants.LOG_FILE_PATH);
            if (Files.notExists(logDirPath)) {
                Files.createDirectories(logDirPath);
            }
            
            // Creates the log file with a customized name
            path = logDirPath.resolve(getLogFileName());
            if (Files.notExists(path)) {
                Files.createFile(path);
            }

 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logRequest(String request) {
        log("REQUEST: " + request);
    }

    public static void logResponse(String response) {
        log("RESPONSE: " + response);
    }
    private static final String LOG_FILE_PATH_MAIN = path.toString();
    private static synchronized void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH_MAIN, true))) {
            writer.write(message);
            writer.newLine();
         
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
}

















