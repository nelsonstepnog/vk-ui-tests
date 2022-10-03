package com.vk.qa.elements;

import com.codeborne.selenide.SelenideElement;
import com.vk.qa.pages.AudioPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.vk.qa.helpers.Constants.*;

public class MenuNavigation {
    private final SelenideElement mainMenuMyPageItem;
    private final SelenideElement mainMenuNewsItem;
    private final SelenideElement mainMenuMessagesItem;
    private final SelenideElement mainMenuCallsItem;
    private final SelenideElement mainMenuFriendsItem;
    private final SelenideElement mainMenuGroupsItem;
    private final SelenideElement mainMenuPhotosItem;
    private final SelenideElement mainMenuMusicItem;
    private final SelenideElement mainMenuVideosItem;
    private final SelenideElement mainMenuShortVideosItem;
    private final SelenideElement mainMenuGamesItem;
    private final SelenideElement mainMenuStickersItem;
    private final SelenideElement mainMenuMarketItem;

    private final SelenideElement userTopMenu;
    private final SelenideElement userTopMenuLogoutItem;

    private final SelenideElement chatsPageAllChatsBlock;
    private final SelenideElement audioPagePlayerBlock;

    public MenuNavigation() {
        mainMenuMyPageItem = $("#l_pr");
        mainMenuNewsItem = $("#l_nwsf");
        mainMenuMessagesItem = $("#l_msg");
        mainMenuCallsItem = $("#l_ca");
        mainMenuFriendsItem = $("#l_fr");
        mainMenuGroupsItem = $("#l_gr");
        mainMenuPhotosItem = $("#l_ph");
        mainMenuMusicItem = $("#l_aud");
        mainMenuVideosItem = $("#l_vid");
        mainMenuShortVideosItem = $("#l_svd");
        mainMenuGamesItem = $("#l_ap");
        mainMenuStickersItem = $("#l_stickers");
        mainMenuMarketItem = $("#l_mk");

        userTopMenu = $("#top_profile_link");
        userTopMenuLogoutItem = $("#top_logout_link");

        chatsPageAllChatsBlock = $("#im_dialogs");
        audioPagePlayerBlock = $x("//*[contains(@class, 'audio_page_player2')]");
    }

    public MenuNavigation openChats() {
        mainMenuMessagesItem.click();
        chatsPageAllChatsBlock.should(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
        return this;
    }

    public MenuNavigation openMusic() {
        mainMenuMusicItem.click();
        new AudioPage().audioGeneralSectionBlock.should(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
        return this;
    }

    public MenuNavigation logout() {
        userTopMenu.click();
        userTopMenuLogoutItem.click();
        return this;
    }
}
