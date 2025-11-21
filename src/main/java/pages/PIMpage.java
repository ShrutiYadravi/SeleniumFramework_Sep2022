package pages;

import helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMpage {

    private WebDriver driver;

    // ============================
    // Locators
    // ============================
    private By PIMMenu = By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM'])[1]");

    private By employeeName = By.xpath("//label[normalize-space()='Employee Name']/parent::*/following-sibling::div//input");
    private By employeeId = By.xpath("//label[normalize-space()='Employee Id']/parent::*/following-sibling::div//input");
    private By supervisorName = By.xpath("//label[normalize-space()='Supervisor Name']/parent::*/following-sibling::div//input");

    private By employmentStatusDropdown = By.xpath("//label[normalize-space()='Employment Status']/parent::*/following-sibling::div//div[contains(@class,'oxd-select-text-input')]");
    private By includeDropdown = By.xpath("//label[normalize-space()='Include']/parent::*/following-sibling::div//div[contains(@class,'oxd-select-text-input')]");
    private By jobTitleDropdown = By.xpath("//label[normalize-space()='Job Title']/parent::*/following-sibling::div//div[contains(@class,'oxd-select-text-input')]");
    private By subUnitDropdown = By.xpath("//label[normalize-space()='Sub Unit']/parent::*/following-sibling::div//div[contains(@class,'oxd-select-text-input')]");

    private By resetButton = By.xpath("//button[normalize-space()='Reset']");
    private By submitButton = By.xpath("//button[@type='submit']");

    // ============================
    // Constructor
    // ============================
    public PIMpage(WebDriver driver) {
        this.driver = driver;
    }

    // ============================
    // Basic Actions
    // ============================
    public void clickPIMMenu() {
        Utility.waitForElementClickable(driver, PIMMenu, 15);
        Utility.clickElement(driver, PIMMenu);
    }

    public void enterEmployeeName(String name) {
        if (name == null || name.isEmpty()) return;
        Utility.waitForElement(driver, employeeName, 15).clear();
        Utility.waitForElement(driver, employeeName, 15).sendKeys(name);
    }

    public void enterEmployeeID(String id) {
        if (id == null || id.isEmpty()) return;
        Utility.waitForElement(driver, employeeId, 15).clear();
        Utility.waitForElement(driver, employeeId, 15).sendKeys(id);
    }

    public void enterSupervisorName(String supName) {
        if (supName == null || supName.isEmpty()) return;
        Utility.waitForElement(driver, supervisorName, 15).clear();
        Utility.waitForElement(driver, supervisorName, 15).sendKeys(supName);
    }

    // ============================
    // Dropdown Methods (inside page)
    // ============================
    public void selectEmploymentStatusDropdown(String value) {
        if (value == null || value.isEmpty()) return;
        Utility.clickElement(driver, employmentStatusDropdown);
        By option = By.xpath("//div[@role='option' and normalize-space()='" + value + "']");
        Utility.clickElement(driver, option);
    }

    public void selectIncludeDropdown(String value) {
        if (value == null || value.isEmpty()) return;
        Utility.clickElement(driver, includeDropdown);
        By option = By.xpath("//div[@role='option' and normalize-space()='" + value + "']");
        Utility.clickElement(driver, option);
    }

    public void selectJobTitleDropdown(String value) {
        if (value == null || value.isEmpty()) return;
        Utility.clickElement(driver, jobTitleDropdown);
        By option = By.xpath("//div[@role='option' and normalize-space()='" + value + "']");
        Utility.clickElement(driver, option);
    }

    public void selectSubUnitDropdown(String value) {
        if (value == null || value.isEmpty()) return;
        Utility.clickElement(driver, subUnitDropdown);
        By option = By.xpath("//div[@role='option' and normalize-space()='" + value + "']");
        Utility.clickElement(driver, option);
    }

    // ============================
    // Buttons
    // ============================
    public void ClickOnResetButton() {
        Utility.waitForElementClickable(driver, resetButton, 15);
        Utility.clickElement(driver, resetButton);
    }

    public void ClickOnSubmitButton() {
        Utility.waitForElementClickable(driver, submitButton, 15);
        Utility.clickElement(driver, submitButton);
    }

    // ============================
    // Combined Search Method
    // ============================
    public void searchEmployee(String empName, String empId, String supName,
                               String employmentStatus, String include,
                               String jobTitle, String subUnit,
                               boolean resetBeforeSearch) {

        clickPIMMenu();

        if (resetBeforeSearch) {
            ClickOnResetButton();
        }

        enterEmployeeName(empName);
        enterEmployeeID(empId);
        enterSupervisorName(supName);

        selectEmploymentStatusDropdown(employmentStatus);
        selectIncludeDropdown(include);
        selectJobTitleDropdown(jobTitle);
        selectSubUnitDropdown(subUnit);

        ClickOnSubmitButton();
    }
}
