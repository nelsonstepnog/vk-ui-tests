package com.vk.qa.helpers;

import org.testng.TestException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config config;
    private final Properties properties = new Properties();

    private Config() {
        String[] filenames = System.getProperty("vk.config.file", "config.properties").split(",");
        String[] var2 = filenames;
        int var3 = filenames.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String filename = var2[var4];

            try {
                FileInputStream input = new FileInputStream(filename);

                try {
                    this.properties.load(input);
                } catch (Throwable var10) {
                    try {
                        input.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }

                    throw var10;
                }

                input.close();
            } catch (IOException var11) {
            }
        }

    }

    public static synchronized Config get() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    private String getSystemProperty(String name) {
        return System.getProperty(name);
    }

    private String getProperty(String name, boolean throwException) {
        String systemProperty = this.getSystemProperty(name);
        Object result = systemProperty == null ? this.properties.getProperty(name) : systemProperty;
        if (throwException && result == null) {
            throw new TestException("Variable '" + name + "' is missing!");
        } else {
            return String.valueOf(result);
        }
    }

    public String getProperty(String name) {
        return this.getProperty(name, true);
    }
}
