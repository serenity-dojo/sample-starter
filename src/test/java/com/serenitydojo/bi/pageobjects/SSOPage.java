package com.serenitydojo.bi.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import net.serenitybdd.screenplay.ui.Button;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

@DefaultUrl("https://app.powerbi.com/singleSignOn")
public class SSOPage extends PageObject {

    public static final String PASSWORD_FIELD = "[name='passwd']";

    public void submitEmail(String email) {
        $("#email").type(email);
        $("#submitBtn").click();
    }

    public String getEmailErrorMessage() {
        return $(".emailInputError").getText();
    }

    public String getPasswordErrorMessage() {
        withTimeoutOf(Duration.ofSeconds(3))
                .waitFor(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#passwordError")));
        return $("#passwordError").getText();
    }

    public void waitForSSOSignOn() {
        withTimeoutOf(Duration.ofSeconds(10))
                .waitFor(ExpectedConditions.titleIs("Sign in to your account"));
    }

    public WebElementFacade password() {
        return $(PASSWORD_FIELD);
    }

    public void enterPassword(String password) {
        password().type(password);
        $(Button.withText("Sign in")).click();
    }

    public void signonWithPassword(String password) {
        enterPassword(password);

        withTimeoutOf(Duration.ofSeconds(3))
                .waitFor(textToBePresentInElementLocated(By.cssSelector(".text-title"),
                        "Action Required"));

        if ($(Button.withText("Ask later")).isCurrentlyVisible()) {
            $(Button.withText("Ask later")).click();
        }

        withTimeoutOf(Duration.ofSeconds(3))
                .waitFor(textToBePresentInElementLocated(By.cssSelector(".text-title"),
                                                         "Stay signed in?"));

        $(Button.withText("No")).click();

        withTimeoutOf(Duration.ofSeconds(3))
                .waitFor(textToBePresentInElementLocated(By.cssSelector(".product-name"),
                                                         "Power BI"));
    }
}
