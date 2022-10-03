package com.vk.qa.pages;

import com.codeborne.selenide.SelenideElement;
import com.vk.qa.helpers.CommonHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.vk.qa.helpers.Constants.*;

public class ChatsPage {
    private final SelenideElement createChatBtn;
    private final SelenideElement createChatConfirmBtn;
    private final SelenideElement createChatNameInput;
    private final SelenideElement chatOpenedBody;
    private final SelenideElement chatActionsDeleteItem;
    private final SelenideElement chatDeleteConfirmBtn;
    private final SelenideElement chatSendMessageBtn;
    private final SelenideElement chatAttachInMessageBtn;
    private final SelenideElement chatAttachPhotoMenuItem;
    private final SelenideElement chatAttachDocMenuItem;
    private final SelenideElement chatAttachAudioMenuItem;
    private final SelenideElement photoFromLibrary;
    private final SelenideElement docFromLibrary;
    private final SelenideElement audioFromLibrary;
    private final SelenideElement messageInput;
    private final SelenideElement uploadPhotoInput;
    private final SelenideElement uploadDocInput;

    private SelenideElement messageWithPhoto;
    private SelenideElement messageWithDoc;
    private SelenideElement messageWithAudio;

    public ChatsPage() {
        createChatBtn = $x("//button[contains(@class,'im_create_convo')]");
        createChatConfirmBtn = $x("//button[contains(@class, 'im_confirm_creation')]");
        createChatNameInput = $("#im_dialogs_creation_name");
        chatOpenedBody = $x("//*[@class='im-page--chat-body']");
        chatActionsDeleteItem = $x("//*[@data-action='clear']/span[text()='Очистить историю сообщений']");
        chatDeleteConfirmBtn = $x("//*[@class='FlatButton__content' and text()='Удалить']");
        chatSendMessageBtn = $x("//*[contains(@class, 'im-send-btn im-chat-input--send')]");
        chatAttachInMessageBtn = $x("//*[@class='ms_item_more']");
        chatAttachPhotoMenuItem = $x("//*[contains(@class, 'ms_item ms_item_photo')]");
        chatAttachDocMenuItem = $x("//*[contains(@class, 'ms_item ms_item_doc')]");
        chatAttachAudioMenuItem = $x("//*[contains(@class, 'ms_item ms_item_audio')]");
        photoFromLibrary = $x("//*[contains(@class, 'photo_row_img')]/parent::a");
        docFromLibrary = $x("//*[@class='docs_item _docs_item']");
        audioFromLibrary = $x("//*[contains(@class, 'ape_audio_item_wrap')]/div");
        messageInput = $x("//*[contains(@class, 'im_editable im-chat-input--text')]");
        uploadPhotoInput = $x("//*[contains(@class, 'im_upload_photo')]//input[@class='file']");
        uploadDocInput = $x("//*[contains(@class, 'im_upload_doc')]//input[@class='file']");
    }

    public SelenideElement getChatByName(String chatName) {
        return $x(String.format("//*[@class='_im_dialog_link'][text()='%s']", chatName));
    }

    public SelenideElement getChatMessageByText(String message) {
        return $x(String.format("//*[contains(@class, 'im-mess--text') and text()='%s']", message));
    }

    private SelenideElement getChatActionsIconByChatName(String chatName) {
        return $x(String.format("//*[@class='_im_dialog_link'][text()='%s']//ancestor::li//button[contains(@class, 'im_dialog_actions')]", chatName));
    }

    public ChatsPage checkThatChatsContainsChat(String chatName) {
        getChatByName(chatName).should(visible);
        return this;
    }

    public ChatsPage createNewChat(String chatName) {
        createChatBtn.hover().click();
        createChatNameInput.should(visible).setValue(chatName);
        createChatConfirmBtn.click();
        return this;
    }

    public ChatsPage openChat(String chatName) {
        getChatByName(chatName).should(visible).hover().click();
        chatOpenedBody.should(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
        return this;
    }

    public ChatsPage sendMessageInChat(String message) {
        messageInput.should(visible).setValue(message);
        chatSendMessageBtn.click();
        getChatMessageByText(message).should(visible);
        return this;
    }

    public ChatsPage attachPhotoToChat() {
        chatAttachInMessageBtn.should(visible).hover();
        chatAttachPhotoMenuItem.click();
        photoFromLibrary.click();
        return this;
    }

    public ChatsPage attachDocumentToChat() {
        chatAttachInMessageBtn.should(visible).hover();
        chatAttachDocMenuItem.click();
        docFromLibrary.click();
        return this;
    }

    public ChatsPage attachAudioToChat() {
        chatAttachInMessageBtn.should(visible).hover();
        chatAttachAudioMenuItem.click();
        audioFromLibrary.click();
        return this;
    }

    public ChatsPage uploadPhotoToChat(String filePath) {
        CommonHelper.uploadFile(uploadPhotoInput, filePath);
        return this;
    }

    public ChatsPage uploadDocumentToChat(String filePath) {
        CommonHelper.uploadFile(uploadDocInput, filePath);
        return this;
    }

    public ChatsPage checkPhotoWithMessageInChat(String message) {
        messageWithPhoto = $x(String.format("//*[contains(@class, 'im-mess--text') and text()='%s']//*[contains(@onclick, 'return showPhoto')]", message));
        messageWithPhoto.isDisplayed();
        return this;
    }

    public ChatsPage checkDocumentWithMessageInChat(String message) {
        messageWithDoc = $x(String.format("//*[contains(@class, 'im-mess--text') and text()='%s']//*[contains(@class, 'page_doc_title')]", message));
        messageWithDoc.isDisplayed();
        return this;
    }

    public ChatsPage checkAudioWithMessageInChat(String message, String audioTitle) {
        messageWithAudio = $x(String.format("//*[contains(@class, 'im-mess--text') and text()='%s']//*[contains(@class, 'audio_row__title_inner') and text()='%s']", message, audioTitle));
        messageWithAudio.isDisplayed();
        return this;
    }

    public ChatsPage removeChat(String chatName) {
        SelenideElement chat = getChatByName(chatName);
        chat.should(visible).click();
        chat.hover();
        getChatActionsIconByChatName(chatName).should(visible).hover();
        chatActionsDeleteItem.click();
        chatDeleteConfirmBtn.click();
        chat.should(disappear);
        return this;
    }
}
