package com.kmartita.tools;

import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class AllureUtils {

    private static final Logger logger = LoggerFactory.getLogger(AllureUtils.class);

    @Attachment(value = "Schema file:", type = "application/json", fileExtension = ".json")
    public static byte[] attachJsonFile(String filePath) {
        ClassLoader classLoader = AllureUtils.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            if (inputStream == null) {
                logger.error("Resource not found: {}", filePath);
                return null;
            }
            byte[] fileContent = inputStream.readAllBytes();
            logger.info("Attached File successfully");
            return fileContent;
        } catch (IOException e) {
            logger.error("Failed to attach JSON file: {}", filePath, e);
            return null;
        }
    }
}
