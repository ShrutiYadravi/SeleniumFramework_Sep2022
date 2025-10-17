package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;  // your existing BaseClass handles driver setup/teardown
import dataProvider.CustomDataProvider; // your existing data provider
import pages.HomePage;
import pages.LoginPage;

public class HomePageTest extends BaseClass {

    @Test(dataProvider = "loginDetails", dataProviderClass = CustomDataProvider.class)
    public void homePageSignOutTest(String uname, String pass) {
        // Step 1: Login using LoginPage POM
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApplication(uname, pass);

        // Step 2: Verify dashboard/home page
        HomePage homePage = new HomePage(driver);
        String dashboardMsg = homePage.getWelcomeMsg();
        System.out.println("Dashboard message: " + dashboardMsg);
        Assert.assertTrue(dashboardMsg.contains("Dashboard"), "Dashboard not displayed");

        // Step 3: Sign out
        homePage.clickOnSignOut();

        // Step 4: Verify back on login page
        Assert.assertTrue(loginPage.isSignInPresent(), "Login button not displayed after logout");
    }
}
