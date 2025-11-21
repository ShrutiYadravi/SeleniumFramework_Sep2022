package pages;

import helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HyperLinkPage {

    private WebDriver driver;

    private By bookAFreeDrmo= By.xpath("(//button[contains(text(),'Book a Free Demo')])[1]");
    private By FullName=By.xpath("//input[@placeholder='Full Name*']");
    private By workEmail=By.xpath("//input[@name='Email']");
    private By phoneNumber=By.xpath("//label[normalize-space()='Phone Number']");
    private By countryDropdown=By.xpath("//select[@name='Country']");

    private HyperLinkPage(WebDriver driver){
        this.driver=driver;
    }

    public void selectCountryByVisibleText(String countryName) {
        Utility.selectDropdownByText(driver, countryDropdown, countryName);
    }

    public void selectCountryByValue(String value) {
        Utility.selectDropdownByValue(driver, countryDropdown, value);
    }

    public void selectCountryByIndex(int index) {
        Utility.selectDropdownByIndex(driver, countryDropdown, index);
    }

}
