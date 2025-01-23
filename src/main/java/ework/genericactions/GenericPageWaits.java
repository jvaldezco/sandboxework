package ework.genericactions;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ework.utils.TestDriver;

public class GenericPageWaits {
	public static void delay() {
		delay(1);
	}
	
	public static void delay(int seconds) {
		try {
        	Thread.sleep(seconds * 1000);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public static void waitElementVisible(WebDriver driver, SearchContext element, By selector, int waitseconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitseconds));
		
		if(selector != null && element == null) {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
		} else if (selector != null && element != null) {
			wait.until(ExpectedConditions.visibilityOf(element.findElement(selector)));
		} else {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(selector)));
		}
	}
	
	public static void waitElementVisible(WebDriver driver, By selector) {
		waitElementVisible(driver, null, selector, 180);
	}
	
	public static void waitElementVisible(WebDriver driver, SearchContext element, By selector) {
		waitElementVisible(driver, element, selector, 180);
	}
	
	public static void waitElementVisible(WebDriver driver, By selector, int waitseconds) {
		waitElementVisible(driver, null, selector, waitseconds);
	}
	
	public static void waitElementNotVisible(WebDriver driver, SearchContext element, By selector, int waitseconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitseconds));
		
		if(selector != null && element == null) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
		} else if (selector != null && element != null) {
			wait.until(ExpectedConditions.invisibilityOf(element.findElement(selector)));
		} else {
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(selector)));
		}
	}
	
	public static void waitElementNotVisible(WebDriver driver, By selector) {
		waitElementNotVisible(driver, null, selector, 180);
	}
	
	public static void waitElementNotVisible(WebDriver driver, SearchContext element, By selector) {
		waitElementNotVisible(driver, element, selector, 180);
	}
	
	public static void waitElementNotVisible(WebDriver driver, By selector, int waitseconds) {
		waitElementNotVisible(driver, null, selector, waitseconds);
	}
	
	public static void waiteElementToBeClickable(WebDriver driver, WebElement element, By selector, int waitseconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitseconds));
		
		if(selector != null) {
			wait.until(ExpectedConditions.elementToBeClickable(selector));
		} else if (element != null) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
	}
	
	public static void waiteElementToBeClickable(WebDriver driver, By selector) {
		waiteElementToBeClickable(driver, null, selector, 180);
	}
	
	public static void waiteElementToBeClickable(WebDriver driver, By selector, int waitseconds) {
		waiteElementToBeClickable(driver, null, selector, waitseconds);
	}
	
	public static void waiteElementToBeClickable(WebDriver driver, WebElement element) {
		waiteElementToBeClickable(driver, element, null, 180);
	}
	
	public static void waiteElementToBeClickable(WebDriver driver, WebElement element, int waitseconds) {
		waiteElementToBeClickable(driver, element, null, waitseconds);
	}
	
	public static void waitElementTextToBePresent(WebDriver driver, WebElement element, By selector, String text, int waitseconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitseconds));
		
		if(selector != null) {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(selector, text));
		} else if (element != null) {
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		}
	}
	
	public static void waitElementTextToBePresent(WebDriver driver, By selector, String text) {
		waitElementTextToBePresent(driver, null, selector, text, 180);
	}
	
	public static void waitElementTextToBePresent(WebDriver driver, By selector, String text, int waitseconds) {
		waitElementTextToBePresent(driver, null, selector, text, waitseconds);
	}
	
	public static void waitElementTextToBePresent(WebDriver driver, WebElement element, String text) {
		waitElementTextToBePresent(driver, element, null, text, 180);
	}
	
	public static void waitElementTextToBePresent(WebDriver driver, WebElement element, String text, int waitseconds) {
		waitElementTextToBePresent(driver, element, null, text, waitseconds);
	}
	
	public static void waitElementValueToBePresent(WebDriver driver, WebElement element, By selector, String value, int waitseconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitseconds));
		
		if(selector != null) {
			wait.until(ExpectedConditions.textToBePresentInElementValue(selector, value));
		} else if (element != null) {
			wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		}
	}
	
	public static void waitElementValueToBePresent(WebDriver driver, By selector, String value) {
		waitElementTextToBePresent(driver, null, selector, value, 180);
	}
	
	public static void waitElementValueToBePresent(WebDriver driver, By selector, String value, int waitseconds) {
		waitElementValueToBePresent(driver, null, selector, value, waitseconds);
	}
	
	public static void waitElementValueToBePresent(WebDriver driver, WebElement element, String value) {
		waitElementValueToBePresent(driver, element, null, value, 180);
	}
	
	public static void waitElementValueToBePresent(WebDriver driver, WebElement element, String value, int waitseconds) {
		waitElementValueToBePresent(driver, element, null, value, waitseconds);
	}
	
	public static void waitForWindowNumToBe(WebDriver driver, int tabs, int waitseconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitseconds));
		
		wait.until(ExpectedConditions.numberOfWindowsToBe(tabs));
	}
	
	public static void waitPageTitle(WebDriver driver, String title, int waitseconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitseconds));
		
		wait.until(ExpectedConditions.titleIs(title));
	}
	
	public static void waitPageLoad(WebDriver driver, int waitseconds) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
            	return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		
		GenericBrowserActions.switchFrame(driver);
		delay();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitseconds));
		wait.until(pageLoadCondition);
		delay();
		GenericBrowserActions.switchFrame(driver);
	}
	
	public static void setImplicitWait(WebDriver driver) {
		setImplicitWait(driver, TestDriver.config.getImplicitWait());
	}
	
	public static void setImplicitWait(WebDriver driver, int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	
	public static void waitPageLoad(WebDriver driver) {
		waitPageLoad(driver, TestDriver.config.getPageLoadTimeout());
	}
	
	//FOR REVIEW
	public static void waitElementTobeVisible(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitElementToNotBeVisible(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public static boolean waitElementTobeVisibleInSec(WebDriver driver ,WebElement element) {
//			while(!element.isDisplayed()) {
//				driver.manage().timeouts().implicitlyWait(Duration.ofNanos(1));
//			}
//			return element.isDisplayed();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Specify the maximum wait time (in seconds) here

	    try {
	        wait.until(ExpectedConditions.visibilityOf(element));
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	   
	}
}
