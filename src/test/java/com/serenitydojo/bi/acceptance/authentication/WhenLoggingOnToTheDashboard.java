package com.serenitydojo.bi.acceptance.authentication;

import com.serenitydojo.bi.actions.LoginActions;
import com.serenitydojo.bi.pageobjects.DashboardPage;
import com.serenitydojo.bi.pageobjects.SSOPage;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
@DisplayName("When attempting to log on to the BI dashboard")
public class WhenLoggingOnToTheDashboard {

    SSOPage ssoPage;
    DashboardPage dashboardPage;

    @Nested
    class WhenEnteringAnEmail {
        @Test
        void withAnInvalidEmail() {
            ssoPage.open();
            ssoPage.submitEmail("not-a-valid-email");
            assertThat(ssoPage.getEmailErrorMessage())
                    .contains("not-a-valid-email isn't a valid email. Make sure you typed it correctly");
        }

        @Test
        void withAnEmptyEmail() {
            ssoPage.open();
            ssoPage.submitEmail("");
            assertThat(ssoPage.getEmailErrorMessage())
                    .contains("Enter valid email addresses, e.g. someone@example.com");
        }

        @Test
        void withAValidEmail() {
            ssoPage.open();
            ssoPage.submitEmail("john.smart@wakaleo.com");
            ssoPage.waitForSSOSignOn();
            ssoPage.password().shouldBeVisible();
        }
    }

    @Nested
    class WhenProvidingAUsernameAndPassword {

        @Steps
        LoginActions loginActions;

        @Test
        void withAnInvalidPassword() {
            ssoPage.open();
            ssoPage.submitEmail("john.smart@wakaleo.com");
            ssoPage.waitForSSOSignOn();
            ssoPage.enterPassword("not-a-valid-password");

            assertThat(ssoPage.getPasswordErrorMessage())
                    .contains("Your account or password is incorrect.");

        }

        @Test
        void withAValidPassword() {
            loginActions.loginWithAValidEmail();
            assertThat(dashboardPage.getTitle()).contains("Power BI");
        }

    }

}
