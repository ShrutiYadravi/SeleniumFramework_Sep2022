package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import helper.Utility;

public class LoginPage {

    private WebDriver driver;

    // Locators
    private By user = By.name("username");
    private By pass = By.name("password");
    private By login = By.xpath("//button[normalize-space()='Login']");
    private By loginHeader = By.xpath("//h5[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[@role=\"alert\"]");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Login method using Utility
    public void loginToApplication(String username, String password) {
        Utility.enterText(driver, user, username);
        Utility.enterText(driver, pass, password);
        Utility.clickElement(driver, login);
    }

    //To check if SignIn is present
    public boolean isSignInPresent() {
        return driver.findElement(login).isDisplayed();
    }

    //To check welcome message

    public String getWelcomeMsg1() {
        return driver.findElement(login).getText();
    }


    //Login with invalid credentails
    public void inValidCrdentials(String username, String password) {
        Utility.enterText(driver, user, username);
        Utility.enterText(driver, pass, password);
        Utility.clickElement(driver, login);
    }

    //Error message for invalid credentials
    public String errorMessage() {
        return driver.findElement(errorMessage).getText();
    }


}
