package com.vk.qa.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.vk.qa.helpers.Constants.*;

public class LoginPage {
    private final SelenideElement userGallery;
    private final SelenideElement emailPhoneInput;
    private final SelenideElement passwordInput;
    private final SelenideElement signInBtn;
    private final SelenideElement successLoginCheckElement;
    private final SelenideElement incorrectDataNotification;

    public LoginPage() {
        userGallery = $x("//*[@class='index_user_ph']");
        emailPhoneInput = $x("//input[@name='login']");
        passwordInput = $x("//input[@name='password']");
        signInBtn = $x("//button[@type='submit']");
        successLoginCheckElement = $("#submit_post_box");
        incorrectDataNotification = $x("//*[@class='vkc__TextField__text']");
    }

    public LoginPage selectUser() {
        userGallery.shouldBe(visible, Duration.ofMillis(DEFAULT_TIMEOUT)).click();
        return this;
    }

    public LoginPage setLogin(String loginValue) {
        emailPhoneInput.should(visible, Duration.ofMillis(DEFAULT_TIMEOUT)).setValue(loginValue);
        signIn();
        return this;
    }

    public LoginPage setPassword(String passwordValue) {
        passwordInput.should(visible, Duration.ofMillis(DEFAULT_TIMEOUT)).setValue(passwordValue);
        return this;
    }

    public LoginPage signIn() {
        signInBtn.click();
        return this;
    }

    public LoginPage checkNoAuth() {
        signInBtn.should(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
        return this;
    }

    public LoginPage checkSuccessLogin() {
        successLoginCheckElement.should(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
        return this;
    }

    public LoginPage checkNotificationByMessage(String message) {
        incorrectDataNotification.shouldHave(text(message), Duration.ofMillis(DEFAULT_TIMEOUT));
        return this;
    }

    public LoginPage setLanguage(String languageValue, String SearchLabelWord) {
        SelenideElement searchLabelNeedLanguage = $x(String.format("//*[@aria-label='%s']", SearchLabelWord));
        if (!searchLabelNeedLanguage.is(visible)) {
            $x(String.format("//*[@class='footer_lang_link'][text()='%s']", languageValue)).click();
            searchLabelNeedLanguage.should(visible);
        }
        return this;
    }

    public LoginPage login(String login, String password) {
        setLogin(login);
        setPassword(password);
        signIn();
        checkSuccessLogin();
        return this;
    }
}
