package com.kmartita.tools;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnvManagerUtil {

    private static final Dotenv ENV = Dotenv
            .configure()
            .ignoreIfMissing()
            .load();

    public static String TOKEN = getEnvOption("TOKEN");
    public static final String USER_NAME = getEnvOption("USER_NAME");

    private static String getEnvOption(String option) {
        String value = ENV.get(option);
        return (value != null) ? value : System.getenv(option);
    }
}
