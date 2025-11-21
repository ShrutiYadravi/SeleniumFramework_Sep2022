package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminPage {

    WebDriver driver;
    WebDriverWait wait;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Locators
    By adminMenu=By.xpath("//a[@class='oxd-main-menu-item active']");
    By userName = By.xpath("((//input[@class='oxd-input oxd-input--active'])[2]");
    By userRoleDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");

    //By userRoleDropdownValues = By.xpath("//div[@class='oxd-select-text-input'][normalize-space()='ESS']");
    By employeeName=By.xpath("//input[@placeholder='Type for hints...']");
   By statusDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
   // By statusDropdownValues = By.xpath("//div[normalize-space()='Enabled'][1]");
    By resetButton = By.xpath("//button[normalize-space()='Reset']");
    By searchButton = By.xpath("//button[@type='submit']");

    //Enter userName

    public void clickAdminMenu(){
        driver.findElement(adminMenu).click();


    }
    public void enterUserName(String userNameValues) {
        driver.findElement(userName).sendKeys(userNameValues);
    }

    //Userrole dropdown values
    public void selectUserRole(String role) {
        driver.findElement(userRoleDropdown).click();
       // List<WebElement> roles = driver.findElements(userRoleDropdownValues);
        List<WebElement> roles = driver.findElements(userRoleDropdown);
        for (WebElement r : roles) {
            if (r.getText().equalsIgnoreCase(role)) {
                r.click();
                break;
            }
        }
    }

    //Enter Employee name
    public void enterEmployeeName(String ename){
        driver.findElement(employeeName).sendKeys(ename);
    }

    //Status dropdownvalues
public void selectStatus(String status){
        //List<WebElement> statuses=driver.findElements(statusDropdown);
    List<WebElement> statuses=driver.findElements(statusDropdown);
        for(WebElement s:statuses){
            if(s.getText().equalsIgnoreCase(status)){
                s.click();
                break;
            }
        }
}

//click search
    public void resetButton(){
        driver.findElement(resetButton).click();
    }

    public void searchButton(){
        driver.findElement(searchButton).click();
    }

    //Combined method
    public void searchSystemUser(String username, String role, String empName, String status) {
        enterUserName(username);
        selectUserRole(role);
        enterEmployeeName(empName);
        selectStatus(status);
        searchButton();
    }
}
