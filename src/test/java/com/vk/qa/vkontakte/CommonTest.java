package com.vk.qa.vkontakte;

import com.vk.qa.pages.LoginPage;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;

public class CommonTest extends BaseTest {

    @BeforeMethod(alwaysRun = true, description = "Common login in system before tests.")
    public void loginBeforeTests() {
        open(VK_URL);
        new LoginPage().setLanguage(LANGUAGE, SEARCH_LABEL_WORD).login(USER_LOGIN, USER_PASSWORD);
    }
}
