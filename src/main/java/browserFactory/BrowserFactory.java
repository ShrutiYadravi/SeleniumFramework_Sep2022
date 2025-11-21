package browserFactory;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

	// ThreadLocal ensures each thread has its own WebDriver instance
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver getBrowserInstance() {
		return driver.get();
	}

	public static WebDriver startBrowser(String browserName, String applicationURL) {
		WebDriver driverInstance;

		switch (browserName.toLowerCase()) {
			case "chrome":
			case "google chrome":
				ChromeOptions options = new ChromeOptions();
				if (browserName.contains("headless")) {
					options.addArguments("--headless");
					options.addArguments("--no-sandbox");
				}
				driverInstance = new ChromeDriver(options);
				break;

			case "firefox":
				driverInstance = new FirefoxDriver();
				break;

			case "safari":
				driverInstance = new SafariDriver();
				break;

			case "edge":
				driverInstance = new EdgeDriver();
				break;

			default:
				driverInstance = new ChromeDriver();
		}

		driverInstance.manage().window().maximize();
		driverInstance.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driverInstance.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driverInstance.get(applicationURL);

		// Set the ThreadLocal driver
		driver.set(driverInstance);

		return driverInstance;
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove(); // Remove driver from ThreadLocal to avoid memory leaks
		}
	}
}
