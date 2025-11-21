package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import helper.Utility;

public class AdminAddPage {

    private WebDriver driver;

    // Locators
    private By adminMenu = By.xpath("//span[normalize-space()='Admin']");
    private By addButton = By.xpath("//button[normalize-space()='Add']");
    private By userRoleDropdown = By.xpath("//label[text()='User Role']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')][1]");
    private By userStatusDropdown = By.xpath("(//label[text()='Status']/following::div[@class='oxd-select-text oxd-select-text--active'])[1]\n");
    private By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
    private By userNameField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
   // private By userStatusDropdown = By.xpath("(//div[text()='-- Select --'])[2]");
    private By passwordField = By.xpath("(//input[@type='password'])[1]");
    private By confirmPasswordField = By.xpath("(//input[@type='password'])[2]");
    private By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    private By submitButton = By.xpath("//button[@type='submit']");

    public AdminAddPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAdminMenu() {
        Utility.waitForElementClickable(driver, adminMenu, 15);
        Utility.clickElement(driver, adminMenu);
    }

    public void clickAddButton() {
        Utility.waitForElementClickable(driver, addButton, 10);
        Utility.clickElement(driver, addButton);
    }

    public void clickCancel() {
        Utility.waitForElementClickable(driver, cancelButton, 10);
        Utility.clickElement(driver, cancelButton);
    }

    public void clickSubmit() {
        Utility.waitForElementClickable(driver, submitButton, 10);
        Utility.clickElement(driver, submitButton);
    }

    public void selectUserRole(String role) {
        Utility.waitForElementClickable(driver, userRoleDropdown, 10);
        Utility.clickElement(driver, userRoleDropdown);
        By option = By.xpath("//div[@role='listbox']//span[normalize-space()='" + role + "']");
        Utility.clickElement(driver, option);
    }

    public void selectStatus(String status) {
        Utility.waitForElementClickable(driver, userStatusDropdown, 10);
        Utility.clickElement(driver, userStatusDropdown);
        By option = By.xpath("//div[@role='listbox']//span[normalize-space()='" + status + "']");
        Utility.clickElement(driver, option);
    }

    public void enterEmployeeName(String empName) {
        Utility.waitForElement(driver, employeeNameField, 10).sendKeys(empName);
    }

    public void enterUserName(String uname) {
        Utility.waitForElement(driver, userNameField, 10).sendKeys(uname);
    }

    public void enterPassword(String password) {
        Utility.waitForElement(driver, passwordField, 10).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmpass) {
        Utility.waitForElement(driver, confirmPasswordField, 10).sendKeys(confirmpass);
    }

    // Business method (does NOT click admin menu)
    public void addNewAdmin(String userRole, String status, String empName, String username, String password, String confirmPassword) {
        clickAddButton();
        selectUserRole(userRole);
        selectStatus(status);
        enterEmployeeName(empName);
        enterUserName(username);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickSubmit();
    }

    public List<WebElement> getAllUserRoles() {
        Utility.waitForElementClickable(driver, userRoleDropdown, 10);
        Utility.clickElement(driver, userRoleDropdown);
        return driver.findElements(By.xpath("//div[@role='listbox']//span"));
    }

    // Getter for test waits
    public By getAdminMenuLocator() { return adminMenu; }
    public By getUserRoleDropdownLocator() { return userRoleDropdown; }
}
