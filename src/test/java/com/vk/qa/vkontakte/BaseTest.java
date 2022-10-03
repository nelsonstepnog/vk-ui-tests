package com.vk.qa.vkontakte;

import com.vk.qa.helpers.Config;
import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;
import static com.vk.qa.helpers.Constants.*;

public class BaseTest {
    public final String LANGUAGE = "Русский";
    public final String SEARCH_LABEL_WORD = "Поиск";
    public final String VK_URL = Config.get().getProperty("vk.url");
    public final String BROWSER = Config.get().getProperty("browser");
    public final String BROWSER_SIZE = Config.get().getProperty("browserSize");
    public final String USER_LOGIN = Config.get().getProperty("userLogin");
    public final String USER_PASSWORD = Config.get().getProperty("userPassword");

    @BeforeSuite(alwaysRun = true)
    public void initAllureListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeMethod(alwaysRun = true, description = "Browser initialization and open base page.")
    public void initBrowser() {
        Configuration.baseUrl = VK_URL;
        Configuration.browser = BROWSER;
        Configuration.browserSize = BROWSER_SIZE;
        Configuration.timeout = MEDIUM_TIMEOUT;
        Configuration.pageLoadTimeout = VERY_LARGE_TIMEOUT;
        open(VK_URL);
    }

    @AfterMethod(alwaysRun = true, description = "Close browser window after test.")
    public void tearDown() {
        closeWebDriver();
    }
}
