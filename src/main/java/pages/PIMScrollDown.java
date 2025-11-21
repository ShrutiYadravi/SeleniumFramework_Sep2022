package pages;

import helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PIMScrollDown {

    private WebDriver driver;

    private By PIMMenu = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']");
private By hyperLink=By.xpath("//a[@href='http://www.orangehrm.com']");
private PIMScrollDown(WebDriver driver){
    this.driver=driver;
}
    public void scrolldownToLink() {
        WebElement element=driver.findElement(PIMMenu);
        Utility.scrollToElement(driver,element);

    }

    public void clickOnHyperLink(){
    Utility.clickElement(driver,hyperLink);
    }
}
