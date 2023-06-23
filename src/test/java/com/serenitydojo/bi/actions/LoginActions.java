package com.serenitydojo.bi.actions;

import com.serenitydojo.bi.pageobjects.SSOPage;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class LoginActions extends UIInteractions {

    SSOPage ssoPage;

    @Step("Login with a valid email")
    public void loginWithAValidEmail() {
        ssoPage.open();
        ssoPage.submitEmail("john.smart@wakaleo.com");
        ssoPage.waitForSSOSignOn();
        ssoPage.signonWithPassword("P[^tMy,?)+ig;Wo4)|n%");
    }

}
