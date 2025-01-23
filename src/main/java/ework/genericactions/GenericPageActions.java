package ework.genericactions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.appium.java_client.android.AndroidDriver;

public class GenericPageActions {
	public static void clickByCoordinates(WebDriver driver, WebElement elem, int pixfromleft, int pixfromtop) {
		Actions builder = new Actions(driver);
		builder.moveToElement(elem, pixfromleft, pixfromtop)
			.click().build().perform();
	}
	
	public static void clickByElemCoordinates(WebDriver driver, WebElement element) throws Exception {
		Actions action = new Actions(driver);
		action.moveToElement(element)
		.click().build().perform();
	}
	
	public static void clickCheckbox(WebDriver driver, WebElement checkbox, boolean check) {
		if (checkbox.isSelected() != check) {
			Actions actions = new Actions(driver);
			actions.moveToElement(checkbox)
				.click().build().perform();
		}
	}
	
	public static void clickLinkToOpenNewTab(WebDriver driver,WebElement link) {
		Actions actions = new Actions(driver);
		actions.moveToElement(link).perform();
		actions.keyDown(Keys.CONTROL).click(link).keyUp(Keys.CONTROL).build().perform();
	}
	
	public static void doubleClick(WebDriver driver, WebElement elem) {
		Actions builder = new Actions(driver);
		builder.moveToElement(elem).doubleClick().build().perform();
	}
	
	//DEPRECATED == REVIEW
		public static void dragAndDrop(WebDriver driver, WebElement moveelem, WebElement toelem) {
			if(driver instanceof AndroidDriver) {
//				TouchActions builder = new TouchActions(driver);
//				builder.clickAndHold(moveelem)
//				.moveToElement(toelem)
//				.release(toelem)
//				.build()
//				.perform();
			} else {
				Actions builder = new Actions(driver);
				builder.clickAndHold(moveelem)
				   .moveToElement(toelem)
				   .release(toelem)
				   .build().perform();
			}
		}
		
		public static void dragAndDrop2(WebDriver driver, WebElement moveelem, WebElement toelem) {
			Actions builder = new Actions(driver);
			builder.dragAndDrop(moveelem, toelem);
		}
	
	public static void mouseHover(WebDriver driver, WebElement element) {
		 Actions actions = new Actions(driver);
		 actions.moveToElement(element).release().build().perform();
	}
	
	public static void sendKeysToPage (WebDriver driver, Keys key) throws Exception {
    	Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();
    }
	
	public static void clearInputBySendKeys(WebDriver driver, WebElement element) throws Exception {
		GenericJSActions.jsElementClick(driver, element);
		String crtlA = Keys.chord(Keys.CONTROL, "a");
		element.sendKeys(crtlA+Keys.DELETE);
	}
	
	public static String getCurrentDate(String format) {
		return GenericPageActions.getCurrentDate(format, 0, 0, 0);
	}
	
	public static String getCurrentDate(String format, long addDays, long addMonths, long addYears) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(addDays != 0) {
			ldt = LocalDateTime.now().plusDays(addDays);
		} 
		
		if (addMonths != 0) {
			ldt = LocalDateTime.now().plusMonths(addMonths);
		} 
		
		if (addYears != 0) {
			ldt = LocalDateTime.now().plusYears(addYears);
		}
		
		return formatter.format(ldt);
	}
	
	public static String getCurrentTime(String format) {
		return GenericPageActions.getCurrentTime(format, 0, 0, 0);
	}
	
	public static String getCurrentTime(String format, long hours, long minutes, long seconds) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(hours != 0) {
			ldt = LocalDateTime.now().plusHours(hours);
		} else if (minutes != 0) {
			ldt = LocalDateTime.now().plusMinutes(minutes);
		} else if (seconds != 0) {
			ldt = LocalDateTime.now().plusSeconds(seconds);
		}
		
		return formatter.format(ldt);
	}
	
	public static boolean checkElementHasClass (WebElement element,String className) {
		boolean flag = false;
	    String classes = element.getAttribute("class");
	    for (String c : classes.split(" ")) {
	        if (c.contains(className)) {
	            flag = true;
	        }
	    }
	   return flag;
	}
	
	public static boolean checkIfPageContainsText (WebDriver driver, String text) {
		return driver.getPageSource().contains(text);
	}
	
	public static String getPageCookies (WebDriver driver) {
		return driver.manage().getCookies().toString();
	}
	
    public static void enterTextWithMaxLength(WebDriver driver, WebElement elem, int length, String text) throws Exception {
        elem.sendKeys(Keys.CONTROL + "a");
        for (int i = 0; i <= length; i++) {
            elem.sendKeys(text);
        }
    }
    
    public static void backspace (WebDriver driver, WebElement elem) {
		GenericPageActions.backspace(driver, elem, 0);
	}
	
	/**
	 * This method utilizes the browsers pl
	 * <p>
	 * Will keep on navigating back until the specified element is visible/displayed
	 * 
	 * @param driver WebDriver
	 * @param selector WebElement indicator to stop the navigation
	 * @param waitseconds Seconds before the script times out
	 */
	public static void backspace (WebDriver driver, WebElement elem, int slength) {
		String tstring = elem.getAttribute("value");
		
		if(slength == 0) {
			slength = tstring.length();
		}
		
		for(int i=slength; i>=0; i--) {
			elem.sendKeys(Keys.BACK_SPACE);
		}
		
		elem.click();
		GenericPageWaits.delay(1);
	}
		
	public static void hoverTo(WebDriver driver, WebElement elem) {
		if (driver instanceof AndroidDriver) {
			//DO NOTHING
		} else {
			Actions builder = new Actions(driver);
			builder.moveToElement(elem).build().perform();
		}
		
	}
}
