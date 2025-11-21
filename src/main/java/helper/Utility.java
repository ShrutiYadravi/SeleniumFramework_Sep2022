package helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	// =================== Waits ===================
	public static WebElement waitForElement(WebDriver driver, By locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// =================== Clicks & Text ===================
	public static void clickElement(WebDriver driver, By locator) {
		try {
			waitForElementClickable(driver, locator, 10).click();
		} catch (Exception e) {
			try {
				new Actions(driver).moveToElement(driver.findElement(locator)).click().build().perform();
			} catch (Exception e1) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(locator));
			}
		}
	}

	public static void enterText(WebDriver driver, By locator, String text) {
		WebElement element = waitForElement(driver, locator, 10);
		try {
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "'", element);
			} catch (Exception ex) {
				System.out.println("Unable to enter text: " + ex.getMessage());
			}
		}
	}

	// =================== Highlight ===================
	public static WebElement highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border:2px solid red;')", element);
		waitForSeconds(1);
		js.executeScript("arguments[0].setAttribute('style','border:2px solid white;')", element);
		return element;
	}

	public static WebElement highlightElement(WebDriver driver, By locator) {
		WebElement element = driver.findElement(locator);
		return highlightElement(driver, element);
	}

	// =================== Alerts ===================
	public static Alert waitForAlert(WebDriver driver) {
		return waitForAlert(driver, 15);
	}

	public static Alert waitForAlert(WebDriver driver, int time) {
		Alert alt = null;
		for (int i = 0; i <= time; i++) {
			try {
				alt = driver.switchTo().alert();
				break;
			} catch (NoAlertPresentException e) {
				waitForSeconds(1);
			}
		}
		return alt;
	}

	// =================== Thread sleep ===================
	public static void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) { }
	}

	// =================== Screenshots ===================
	public static String captureScreenshotInBase64(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BASE64);
	}

	public static void captureScreenshot(WebDriver driver) {
		try {
			FileHandler.copy(
					((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File("./screenshots/Screenshot_" + getCurrentTime() + ".png")
			);
		} catch (IOException e) {
			System.out.println("Error capturing screenshot: " + e.getMessage());
		}
	}

	public static String getCurrentTime() {
		return new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy").format(new Date());
	}

	// =================== Actions ===================
	public static void rightClick(WebDriver driver, WebElement element) {
		new Actions(driver).contextClick(element).build().perform();
	}

	public static void doubleClick(WebDriver driver, WebElement element) {
		new Actions(driver).doubleClick(element).build().perform();
	}

	public static void hoverOver(WebDriver driver, WebElement element) {
		new Actions(driver).moveToElement(element).build().perform();
	}

	public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		new Actions(driver).dragAndDrop(source, target).build().perform();
	}

	public static void sendKeysWithActions(WebDriver driver, WebElement element, CharSequence keys) {
		new Actions(driver).sendKeys(element, keys).build().perform();
	}

	// =================== Dropdowns ===================
	public static void selectDropdownByText(WebDriver driver, By locator, String visibleText) {
		waitForElementClickable(driver, locator, 10);
		try {
			Select dropdown = new Select(driver.findElement(locator));
			dropdown.selectByVisibleText(visibleText);
		} catch (Exception e) {
			System.out.println("Unable to select dropdown by text: " + e.getMessage());
		}
	}

	public static void selectDropdownByValue(WebDriver driver, By locator, String value) {
		waitForElementClickable(driver, locator, 10);
		try {
			Select dropdown = new Select(driver.findElement(locator));
			dropdown.selectByValue(value);
		} catch (Exception e) {
			System.out.println("Unable to select dropdown by value: " + e.getMessage());
		}
	}

	public static void selectDropdownByIndex(WebDriver driver, By locator, int index) {
		waitForElementClickable(driver, locator, 10);
		try {
			Select dropdown = new Select(driver.findElement(locator));
			dropdown.selectByIndex(index);
		} catch (Exception e) {
			System.out.println("Unable to select dropdown by index: " + e.getMessage());
		}
	}

	// =================== Window / Tab Handling ===================
	// Switch to newly opened window by title
	public static void switchToWindowByTitle(WebDriver driver, String windowTitle) {
		String currentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String win : allWindows) {
			driver.switchTo().window(win);
			if (driver.getTitle().equals(windowTitle)) {
				return;
			}
		}
		driver.switchTo().window(currentWindow);
		System.out.println("Window with title '" + windowTitle + "' not found.");
	}

	// Switch to newly opened window by index (0 = first, 1 = second)
	public static void switchToWindowByIndex(WebDriver driver, int index) {
		Set<String> allWindows = driver.getWindowHandles();
		if (index < allWindows.size()) {
			Iterator<String> iterator = allWindows.iterator();
			for (int i = 0; i <= index; i++) {
				String win = iterator.next();
				if (i == index) {
					driver.switchTo().window(win);
					return;
				}
			}
		}
		System.out.println("Window index " + index + " is invalid.");
	}

	// Close current window and switch to parent
	public static void closeCurrentWindowAndSwitchToParent(WebDriver driver) {
		String parent = driver.getWindowHandle();
		driver.close();
		driver.switchTo().window(parent);
	}

	//Scroll function

	// Scroll until element is in view
	public static void scrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	//Switch to child window
	public static String switchToChildWindow(WebDriver driver) {
		String parent = driver.getWindowHandle();
		for (String child : driver.getWindowHandles()) {
			if (!child.equals(parent)) {
				driver.switchTo().window(child);
			}
		}
		return parent;
	}

	//Switch to parent window
	public static void switchToParentWindow(WebDriver driver, String parentID) {
		driver.switchTo().window(parentID);
	}

	//Scroll multiple time

	public static void scrollToElementRepeatedly(WebDriver driver, WebElement element, int maxScrolls) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int scrollCount = 0;
		while (scrollCount < maxScrolls) {
			try {
				if (element.isDisplayed()) {
					break; // Element is now visible
				}
			} catch (Exception e) { }
			js.executeScript("window.scrollBy(0,250)"); // Scroll down 250px
			scrollCount++;
			waitForSeconds(1); // small pause after each scroll
		}
	}



}
