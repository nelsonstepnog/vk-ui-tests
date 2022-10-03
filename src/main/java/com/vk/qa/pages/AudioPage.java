package com.vk.qa.pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.vk.qa.helpers.Constants.*;

import com.codeborne.selenide.SelenideElement;
import com.vk.qa.helpers.CommonHelper;

import java.time.Duration;

public class AudioPage {
    private final SelenideElement uploadAudioBtn;
    private final SelenideElement uploadAudioModalBtn;
    private final SelenideElement uploadAudioModal;
    private final SelenideElement uploadAudioInput;
    private final SelenideElement removeAudioIcon;
    public final SelenideElement audioGeneralSectionBlock;

    public AudioPage() {
        uploadAudioBtn = $x("//button[contains(@class, 'add_audio_btn')]");
        uploadAudioModalBtn = $("#audio_add_upload_btn");
        uploadAudioModal = $x("//*[@class='box_layout']");
        uploadAudioInput = $x("//input[@class='file']");
        removeAudioIcon = $x("//*[@data-action='delete']");
        audioGeneralSectionBlock = $x("//*[contains(@class, 'audio_section__general')]");
    }

    public SelenideElement getAudioByTitle(String audioTitle) {
        return $x(String.format("//*[@class='audio_row__inner']//*[text()='%s']", audioTitle));
    }

    public AudioPage uploadAudio(String filePath, String audioTitle) {
        uploadAudioBtn.click();
        uploadAudioModalBtn.should(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
        CommonHelper.uploadFile(uploadAudioInput, filePath);
        uploadAudioModal.should(disappear, Duration.ofMillis(LARGE_TIMEOUT));
        refresh();  // TODO: Need to refresh page because new audio will not appeared in real time. Bug may be?
        getAudioByTitle(audioTitle).should(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
        return this;
    }

    public AudioPage removeAudio(String audioTitle) {
        SelenideElement audio = getAudioByTitle(audioTitle);
        audio.hover();
        removeAudioIcon.click();
        refresh();
        audioGeneralSectionBlock.should(visible, Duration.ofMillis(DEFAULT_TIMEOUT));
        audio.shouldNotBe(visible);
        return this;
    }
}
