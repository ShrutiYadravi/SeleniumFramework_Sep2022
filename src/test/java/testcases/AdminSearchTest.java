package testcases;

import base.AdminBaseClass;
import dataProvider.CustomDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;

public class AdminSearchTest extends AdminBaseClass {

    /**
     * Logs in once before running all AdminSearch tests.
     * Pulls the first row of login credentials from the Excel "Login" sheet via CustomDataProvider.
     */
    @BeforeClass
    public void loginOnce() {
        // Get login data from Excel
        Object[][] loginData = CustomDataProvider.getData(); // This calls your "loginDetails" data provider
        String username = (String) loginData[0][0]; // first row, first column
        String password = (String) loginData[0][1]; // first row, second column

        // Perform login using LoginPage POM
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApplication(username, password);

//        // Optional: verify login
//        HomePage homepage = new HomePage(driver);
//        homepage.getWelcomeMsg();
    }

    /**
     * Runs Admin search test for all rows from the "AdminSearch" Excel sheet.
     * Uses the same browser session created in @BeforeClass.
     */
    @Test(dataProvider = "AdminSearch", dataProviderClass = CustomDataProvider.class)
    public void verifyAdminPageSearch(String username, String role, String empName, String status) {
        // Use AdminPage POM to perform the search
        AdminPage adminPage = new AdminPage(driver);
        adminPage.clickAdminMenu();
       // adminPage.searchSystemUser(username, role, empName, status);
    }
}
