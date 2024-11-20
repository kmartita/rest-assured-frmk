package com.kmartita.tools;

import io.qameta.allure.Attachment;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

@UtilityClass
public class AllureUtil {

    private static final Logger logger = LoggerFactory.getLogger(AllureUtil.class);

    @Attachment(value = "Schema file:", type = "application/json", fileExtension = ".json")
    public static byte[] attachJsonFile(String filePath) {
        ClassLoader classLoader = AllureUtil.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            return readFileContent(inputStream, filePath);

        } catch (IOException e) {
            logger.error("Failed to attach JSON file: {}.", filePath, e);
            return null;
        }
    }

    private static byte[] readFileContent(InputStream inputStream, String filePath) throws IOException {
        if (inputStream == null) {
            logger.error("Resource not found: {}.", filePath);
            return null;
        }
        byte[] fileContent = inputStream.readAllBytes();
        logger.info("Attached file successfully.");
        return fileContent;
    }
}
