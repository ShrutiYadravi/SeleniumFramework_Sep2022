package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

	WebDriver driver;
	WebDriverWait wait; // <-- declare wait here

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // initialize wait
	}

	// Locators
	By welcomeMsgDashboard = By.xpath("//h6[normalize-space()='Dashboard']");
	By userDropDown = By.xpath("//p[@class='oxd-userdropdown-name']"); // fixed xpath
	By logOut = By.xpath("//a[normalize-space()='Logout']");

	// Method to click sign out
	public void clickOnSignOut() {
		wait.until(ExpectedConditions.elementToBeClickable(userDropDown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(logOut)).click();
	}

	public String getWelcomeMsg() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMsgDashboard)).getText();
	}
}
