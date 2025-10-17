package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By user=By.name("username");
	
	By pass=By.name("password");
	
	By login=By.xpath("//button[normalize-space()='Login']");
	
	By loginHeader=By.xpath("//h5[normalize-space()='Login']");
	
	public void loginToApplication(String username,String password)
	{
		driver.findElement(user).sendKeys(username);
		driver.findElement(pass).sendKeys(password);
		driver.findElement(login).click();
		
	}
	
	public boolean isSignInPresent()
	{
		return driver.findElement(login).isDisplayed();
	}
	
	public String getWelcomeMsg1()
	{
		return driver.findElement(login).getText();
	}
	

}
