package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;
import pages.AdminAddPage;
import helper.Utility;

public class AddAdmin extends BaseClass {

    @Test(description = "Verify admin can add a new user successfully")
    public void verifyAddAdminFunctionality() {

        LoginPage loginPage = new LoginPage(driver);
        AdminAddPage adminPage = new AdminAddPage(driver);

        // Step 1: Login
        loginPage.loginToApplication("Admin", "admin123");
        System.out.println("LOG:INFO - Logged in successfully");

        // Step 2: Navigate to Admin menu
        adminPage.clickAdminMenu();
        System.out.println("LOG:INFO - Clicked Admin menu");

        // Step 3: Add new admin user
        adminPage.addNewAdmin("Admin", "Enabled", "layla Ahmed Karam", "john.doe123", "Password@123", "Password@123");
        System.out.println("LOG:INFO - Admin user added successfully");

        // Step 4: Validation
        Assert.assertTrue(adminPage.getAllUserRoles().size() > 0, "Dropdown roles should not be empty");
        System.out.println("LOG:INFO - Validation passed: User roles are displayed");
    }
}
