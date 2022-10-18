package com.vk.qa.vkontakte.additionals;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class EmptyTest {

    @Test(groups = {"dev", "stage", "pro"}, description = "Empty Test")
    @Owner("Tester Tester")
    @Description("Empty test.")
    public void emptyTest() {
        // TODO: Some code.
    }
}
