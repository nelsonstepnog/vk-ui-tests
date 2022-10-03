package com.vk.qa.vkontakte.login;

import com.vk.qa.pages.LoginPage;
import com.vk.qa.elements.MenuNavigation;
import com.vk.qa.vkontakte.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class Login extends BaseTest {
    private final String LOGIN_INCORRECT = "userWrongLogin";
    private final String PASSWORD_INCORRECT = "userWrongPasswd";
    private final String MESSAGE_LOGIN_INCORRECT = "Аккаунт не найден";
    private final String MESSAGE_PASSWORD_INCORRECT = "Неверный пароль";

    @BeforeMethod
    public void preparation() {
        open();
        new LoginPage().setLanguage(LANGUAGE, SEARCH_LABEL_WORD);
    }

    @Test(groups = {"dev", "stage", "pro"}, description = "Login And Logout Check Positive")
    @Owner("Tester Tester")
    @Description("Verify VK Login And Logout. Positive Checks.")
    public void loginAndLogoutCheckPositive() {
        LoginPage loginPage = new LoginPage();
        loginPage
                .setLogin(USER_LOGIN)
                .setPassword(USER_PASSWORD)
                .signIn()
                .checkSuccessLogin();
        new MenuNavigation()
                .logout();
        loginPage.checkNoAuth();
    }

    @Test(enabled = false, groups = {"dev", "stage", "pro"}, description = "Login With Incorrect Credentials Negative")
    @Owner("Tester Tester")
    @Description("Verify VK Login With Incorrect Credentials. Negative Checks.")
    public void loginCheckNegative() {
        new LoginPage()
                .setLogin(LOGIN_INCORRECT)
                .checkNotificationByMessage(MESSAGE_LOGIN_INCORRECT)
                .setLogin(USER_LOGIN)
                .setPassword(PASSWORD_INCORRECT)
                .signIn()
                .checkNotificationByMessage(MESSAGE_PASSWORD_INCORRECT)
                .setPassword(USER_PASSWORD)
                .signIn()
                .checkSuccessLogin();
    }
}
