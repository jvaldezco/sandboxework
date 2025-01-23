package ework.genericactions;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import ework.factory.webdriver.AndroidSettings;
import ework.factory.webdriver.WebDriverFactory;
import ework.utils.TestDriver;

public class GenericBrowserActions {
	public static void closeBrowser(WebDriver driver) {
		driver.quit();
	}
	
	public static void refreshBrowser(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public static void openInNewTab(WebDriver driver, WebElement link) {
		driver.switchTo().newWindow(WindowType.TAB);
	}
	
	public static void openInNewWindow(WebDriver driver, WebElement link) {
		driver.switchTo().newWindow(WindowType.WINDOW);
	}
	
	//CURRENTLY NEED TO MANUALLY CLOSE BROWSER IN TEST FINALLY BLOCK
	public static WebDriver openDesktopBrowser(WebDriver driver, String browser) throws Exception {
		new WebDriverFactory();
		return driver = WebDriverFactory.getDriver(browser);
	}
	
	public static void switchFrame(WebDriver driver, WebElement frame) {
		driver.switchTo().frame(frame);
	}
	
	public static void switchFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public static void switchTab(WebDriver driver, String urlpart) {
		try {
			List<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			for (String tab : tabs) {
				driver.switchTo().window(tab);
				if (driver.getCurrentUrl().contains(urlpart)) {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void switchTab(WebDriver driver,int tabNumber ) {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabNumber));
	}
	
	public static void navigateToURL(WebDriver driver, String newurl) {
		navigateToURL(driver, newurl, TestDriver.config.getPageLoadTimeout());
	}
	
	public static void navigateToURL(WebDriver driver, String newurl, int seconds) {
		driver.get(newurl);
		GenericPageWaits.waitPageLoad(driver, seconds);
	}
	
    public static void navigateBackOnce(WebDriver driver) {
        driver.navigate().back();
        GenericPageWaits.delay();
    }
    
    @SuppressWarnings("deprecation")
	public static void navigateBackUntilElementVisible(WebDriver driver, SearchContext element, By selector, int waitseconds) {
		element = (element == null) ? driver : element;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		for (int i = 0; i < waitseconds; i++) {
			driver.navigate().back();
			GenericPageWaits.waitPageLoad(driver);
			
			try {
				if (element.findElement(selector).isDisplayed()) {
					i = waitseconds;
				}
			} catch (Exception e) {
			}
		}
		
		driver.manage().timeouts().implicitlyWait(TestDriver.config.getImplicitWait(), TimeUnit.SECONDS);
		GenericPageWaits.delay();
	}

	public static void navigateBackUntilElementVisible(WebDriver driver, By selector) {
		navigateBackUntilElementVisible(driver, null, selector, TestDriver.config.getPageLoadTimeout());
	}

	public static void navigateBackUntilElementVisible(WebDriver driver, SearchContext element, By selector) {
		navigateBackUntilElementVisible(driver, element, selector, TestDriver.config.getPageLoadTimeout());
	}
	
	public static void closeTab(WebDriver driver, String urlpart) {
		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    
		for (String tab : tabs) {
			driver.switchTo().window(tab);
			if (driver.getCurrentUrl().contains(urlpart)) {
				driver.close();
				GenericPageWaits.delay();
				break;
			}
		}
		
	    tabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(0));
	}
	
	public static void returnToDefaultTab (WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public static void acceptAlertPopUp(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public static void dismissAlertPopUp(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public static void openNewTab(WebDriver driver) {
		driver.switchTo().newWindow(WindowType.TAB);
	}
	
	public static void openNewWindow(WebDriver driver) {
		driver.switchTo().newWindow(WindowType.WINDOW);
	}
	
	public static void resizeWebDriver(WebDriver driver, int width, int height) throws Exception{

    	Dimension dimension = new Dimension(width, height);
    	driver.manage().window().setSize(dimension);
    }
	
	public static WebDriver openAndroidBrowser(WebDriver driver, AndroidSettings androidSettings) throws Exception {
		new WebDriverFactory();
		return driver = WebDriverFactory.getDriver(androidSettings.toString());
	}

	/**
	 * This method sets the custom screen size of the window
	 * @param driver  - holds the Webdriver
	 * @param width  - holds the custom width of the window
	 * @param height - holds the custom height of the window
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author adeguzman
	 */
	public static void setScreenSize(WebDriver driver, int width, int height) throws Exception {
		Dimension dimension = new Dimension(width, height);
		driver.manage().window().setSize(dimension);
	}
	
	/**
	 * This method sets the maximum screen size of the window
	 * @param driver  - holds the Webdriver
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author adeguzman
	 */
	public static void setScreenMaximize(WebDriver driver) throws Exception {
		driver.manage().window().maximize();
	}
	
	public static void zoomInBrowser(WebDriver driver, int zoomCount) throws Exception{
		Robot robot = new Robot();

		for (int i = 0; i<=zoomCount; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}

	}
	
	public static void zoomOutBrowser(WebDriver driver, int zoomCount) throws Exception{
		Robot robot = new Robot();

		for (int i = 0; i<=zoomCount; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
	}
}
