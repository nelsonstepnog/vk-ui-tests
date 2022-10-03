package com.vk.qa.helpers;

import com.codeborne.selenide.SelenideElement;

public class CommonHelper {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void uploadFile(SelenideElement element, String filePath) {
//        element.uploadFile(new File("src/test/resources/" + filePath));
        element.uploadFromClasspath(filePath);
    }
}
