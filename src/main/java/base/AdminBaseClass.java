package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AdminBaseClass extends BaseClass {

    @BeforeClass
    @Override
    public void setupBrowser() {
        System.out.println("LOG:INFO - Setting up browser once for AdminSearchTest");
        this.driver = browserFactory.BrowserFactory.startBrowser(
                dataProvider.ConfigReader.getProperty("browser"),
                dataProvider.ConfigReader.getProperty("url")
        );
    }

    @AfterClass
    @Override
    public void closeBrowser() {
        if(this.driver != null) {
            this.driver.quit();
            System.out.println("LOG:INFO - Closing browser after all tests");
        }
    }
}
