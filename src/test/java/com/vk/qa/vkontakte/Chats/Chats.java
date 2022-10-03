package com.vk.qa.vkontakte.Chats;

import com.vk.qa.elements.MenuNavigation;
import com.vk.qa.pages.AudioPage;
import com.vk.qa.pages.ChatsPage;
import com.vk.qa.vkontakte.CommonTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class Chats extends CommonTest {
    private final String MESSAGE_PHOTO_LIBRARY = "TEST UPLOAD PHOTO FROM LIBRARY.";
    private final String MESSAGE_PHOTO_COMPUTER = "TEST UPLOAD PHOTO FROM COMPUTER.";
    private final String MESSAGE_DOC_LIBRARY = "TEST UPLOAD DOCUMENT FROM LIBRARY.";
    private final String MESSAGE_DOC_COMPUTER = "TEST UPLOAD DOCUMENT FROM COMPUTER.";
    private final String MESSAGE_AUDIO = "TEST ATTACH UPLOADED AUDIO.";
    private final String UPLOAD_PHOTO_PATH = "attachments/aut-image.jpg";
    private final String UPLOAD_DOCUMENT_PATH = "attachments/aut-doc.pdf";
    private final String UPLOAD_AUDIO_PATH = "attachments/Linkin Park - With You(cut).mp3";
    private final String AUDIO_TITLE = "Без названия";

    private String chatName;

    private MenuNavigation menuNavigation;
    private ChatsPage chatsPage;
    private AudioPage audioPage;

    @BeforeClass(alwaysRun = true)
    public void initPages() {
        menuNavigation = new MenuNavigation();
        audioPage = new AudioPage();
        chatsPage = new ChatsPage();
    }

    @BeforeMethod(description = "Create new chat before test.")
    public void preparation() {
        chatName = "AUT_chat_" + RandomStringUtils.randomAlphanumeric(10);
        menuNavigation.openChats();
        chatsPage
                .createNewChat(chatName)
                .checkThatChatsContainsChat(chatName);
    }

    @Test(groups = {"dev", "stage", "pro"}, description = "Chats Photo Attachments Checks Positive")
    @Owner("Tester Tester")
    @Description("Verify VK Chats With Photo Attachments Positive Checks.")
    public void chatsPhotoAttachmentsCheckPositive() {
        new ChatsPage()
                .openChat(chatName)
                .attachPhotoToChat()
                .sendMessageInChat(MESSAGE_PHOTO_LIBRARY)
                .checkPhotoWithMessageInChat(MESSAGE_PHOTO_LIBRARY)
                .uploadPhotoToChat(UPLOAD_PHOTO_PATH)
                .sendMessageInChat(MESSAGE_PHOTO_COMPUTER)
                .checkPhotoWithMessageInChat(MESSAGE_PHOTO_COMPUTER);
    }

    @Test(groups = {"dev", "stage", "pro"}, description = "Chats Documents Attachments Checks Positive")
    @Owner("Tester Tester")
    @Description("Verify VK Chats With Documents Attachments Positive Checks.")
    public void chatsDocumentsAttachmentsCheckPositive() {
        new ChatsPage()
                .openChat(chatName)
                .attachDocumentToChat()
                .sendMessageInChat(MESSAGE_DOC_LIBRARY)
                .checkDocumentWithMessageInChat(MESSAGE_DOC_LIBRARY)
                .uploadDocumentToChat(UPLOAD_DOCUMENT_PATH)
                .sendMessageInChat(MESSAGE_DOC_COMPUTER)
                .checkDocumentWithMessageInChat(MESSAGE_DOC_COMPUTER);
    }

    @Test(groups = {"dev", "stage", "pro"}, description = "Chats Music Attachments With Music Upload Checks Positive")
    @Owner("Tester Tester")
    @Description("Verify VK Chats With Music Upload To Library And Attachments Positive Checks.")
    public void chatsMusicAttachmentsCheckPositive() {
        menuNavigation.openMusic();
        audioPage.uploadAudio(UPLOAD_AUDIO_PATH, AUDIO_TITLE);
        open("/");
        menuNavigation.openChats();
        chatsPage
                .checkThatChatsContainsChat(chatName)
                .openChat(chatName)
                .attachAudioToChat()
                .sendMessageInChat(MESSAGE_AUDIO)
                .checkAudioWithMessageInChat(MESSAGE_AUDIO, AUDIO_TITLE);
    }

    @AfterMethod(alwaysRun = true, description = "Delete chat after test.")
    public void afterMethods() {
        open("/");
        menuNavigation.openChats();
        if (chatsPage.getChatByName(chatName).isDisplayed()) {
            chatsPage.removeChat(chatName);
        }
        menuNavigation.openMusic();
        if (audioPage.getAudioByTitle(AUDIO_TITLE).isDisplayed()) {
            audioPage.removeAudio(AUDIO_TITLE);
        }
    }
}
