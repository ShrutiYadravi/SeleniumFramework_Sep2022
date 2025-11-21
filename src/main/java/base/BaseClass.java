package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import browserFactory.BrowserFactory;
import dataProvider.ConfigReader;

public class BaseClass {

	public WebDriver driver;

	@BeforeMethod
	public void setupBrowser() {
		driver = BrowserFactory.startBrowser(
				ConfigReader.getProperty("browser"),
				ConfigReader.getProperty("url")
		);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	@BeforeSuite
	public void setupDB() {
		System.out.println("LOG:INFO - Setup DB if needed");
	}
}
