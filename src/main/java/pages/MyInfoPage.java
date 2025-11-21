package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyInfoPage {
    WebDriver driver;

    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By myInfoMenu = By.xpath("//span[normalize-space()='My Info']");
    By employeeFullName = By.name("firstName");
    By employeeID = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    By driverLicenseNumber = By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]");
   // By licenseExpiryDate = By.xpath("(//input[@placeholder='dd-mm-yyyy'])[1]");
    By nationality = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]");
    By maritalStatus = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]");

    // Actions
    public void clickOnMyInfoMenu() {
        driver.findElement(myInfoMenu).click();
    }

    // Getters
    public String getEmployeeFullName() {
        return driver.findElement(employeeFullName).getAttribute("value");
    }

    public String getEmployeeID() {
        return driver.findElement(employeeID).getAttribute("value");
    }

    public String getDriverLicenseNumber() {
        return driver.findElement(driverLicenseNumber).getAttribute("value");
    }

//    public String getLicenseExpiryDate() {
//        return driver.findElement(licenseExpiryDate).getAttribute("value");
//    }

    public String getNationality() {
        return driver.findElement(nationality).getText();
    }

    public String getMaritalStatus() {
        return driver.findElement(maritalStatus).getText();
    }
}
