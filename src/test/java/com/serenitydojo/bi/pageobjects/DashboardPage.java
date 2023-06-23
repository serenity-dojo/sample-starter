package com.serenitydojo.bi.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.ui.Button;
import org.openqa.selenium.By;

import java.time.Duration;

public class DashboardPage extends PageObject {

    public void createNewReport() {
        $(Button.containingText("New report")).click();
    }

    public String getBannerText() {
        withTimeoutOf(Duration.ofSeconds(3))
                .waitForPresenceOf(By.cssSelector(".banner"));

        return $(".banner").getText();
    }
}
