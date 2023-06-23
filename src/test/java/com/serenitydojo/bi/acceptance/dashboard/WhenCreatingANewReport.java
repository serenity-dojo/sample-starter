package com.serenitydojo.bi.acceptance.dashboard;

import com.serenitydojo.bi.actions.LoginActions;
import com.serenitydojo.bi.pageobjects.DashboardPage;
import com.serenitydojo.bi.pageobjects.SSOPage;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
class WhenCreatingANewReport {

    LoginActions loginActions;

    DashboardPage dashboardPage;

    @Test
    void shouldBeAbleToAccessTheBuildReportPageFromTheHomePage() {
        loginActions.loginWithAValidEmail();

        dashboardPage.createNewReport();

        assertThat(dashboardPage.getBannerText()).contains("Build your first report");

    }
}
