package ework.genericactions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import ework.utils.RunLogger;

public class GenericPageVerifications {

    public static boolean validateDateFormat(String strDate) {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd MMMMMMMMM yyyy, HH:mm:ss 'UTC'");
        sdfrmt.setLenient(false);
        try {
            @SuppressWarnings("unused")
			Date javaDate = sdfrmt.parse(strDate);
            System.out.println(strDate + " is valid date format");
        } catch (ParseException e) {
            System.out.println(strDate + " is Invalid Date format");
            return false;
        }
        return true;
    }
    
    /** This method validates an element's attribute
	 * @param driver - holds the Webdriver
	 * @param logger - holds the result logs of this method
	 * @param element - holds the element to be validated
	 * @param attribute - holds the expected attribute
	 * @param value - holds the expected attribute value
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author ksamaniego
	 */
	public static void validateAttribute (WebDriver driver, RunLogger logger, WebElement element, String attribute, String value) throws Exception {
		try {
			logger.setStepDesc("Validates an element's attribute");
			logger.setExpectedResult("User should be able to view that, for " + element + ", its \"" + attribute + "\" is: \"" + value + "\"");

			//			GenericPageWaits.waitElementVisible(driver, selector);
			GenericJSActions.scrollIntoView(driver, element);
			String actualAttribute = element.getAttribute(attribute);

			if (actualAttribute.contains(value) || actualAttribute.equals(value)){
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("User is able to view that, for " + element + ", its \"" + attribute + "\" is: \"" + actualAttribute + "\"");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("For " + element + ", its actual \"" + attribute + "\" is: \"" + actualAttribute + "\"");
			}

		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}
	
	/** This method validates if an element doesn't have an attribute
	* @param driver - holds the Webdriver
	* @param logger - holds the result logs of this method
	* @param element - holds the element to be validated
	* @param attribute - holds the expected attribute
	* @param value - holds the expected attribute value
	* @throws Exception - throws exceptions when there's an error within this method
	* @author ksamaniego
	*/
	public static void validateAttributeNotVisible (WebDriver driver, RunLogger logger, WebElement element, String attribute, String value) throws Exception {
		try {
			logger.setStepDesc("Validates if an element doesn't have an attribute");
			logger.setExpectedResult("User should be able to view that, for " + element + ", its \"" + attribute + "\" doesn't have: \"" + value + "\"");
			
//			GenericPageAction.waitElementVisible(driver, selector);
			GenericJSActions.scrollIntoView(driver, element);
			String actualAttribute = element.getAttribute(attribute);
			
			if (!actualAttribute.contains(value) || !actualAttribute.equals(value)){
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("User is able to view that, for " + element + ", its \"" + attribute + "\" doesn't have: \"" + actualAttribute + "\"");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("For " + element + ", its actual \"" + attribute + "\" has: \"" + actualAttribute + "\"");
			}
			
		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}

	/** This method validates an element's UI
	 * @param driver - holds the Webdriver
	 * @param logger - holds the result logs of this method
	 * @param element - holds the element to be validated
	 * @param property - holds the expected css property
	 * @param value - holds the expected css value
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author ksamaniego
	 */
	public static void validateUI (WebDriver driver, RunLogger logger, WebElement element, String property, String value) throws Exception {
		try {
			logger.setStepDesc("Validates an element's UI");
			logger.setExpectedResult("User should be able to view that, for " + element + ", its \"" + property + "\" is: \"" + value + "\"");

			GenericJSActions.scrollIntoView(driver, element);
			String actualUI = element.getCssValue(property);

			if (actualUI.contains(value) || actualUI.equals(value)){
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("User is able to view that, for " + element + ", its \"" + property + "\" is: \"" + actualUI + "\"");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("For " + element + ", its actual \"" + property + "\" is: \"" + actualUI + "\"");
			}

		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}

	/** This method validates an element's color
	 * @param driver - holds the Webdriver
	 * @param logger - holds the result logs of this method
	 * @param element - holds the element to be validated
	 * @param property - holds the expected css property
	 * @param color - holds the expected color in hex
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author ksamaniego
	 */
	public static void validateColor (WebDriver driver, RunLogger logger, WebElement element, String property, String color) throws Exception {
		try {
			logger.setStepDesc("Validates an element's color");
			logger.setExpectedResult("User should be able to view that, for " + element + ", its \"" + property + "\" is: \"" + color + "\"");

			//			GenericPageWaits.waitElementVisible(driver, selector);
			GenericJSActions.scrollIntoView(driver, element);
			String actualBgColor = GenericDataGenerators.convertRGBtoHex(element, property);

			if (actualBgColor.contains(color) || actualBgColor.equalsIgnoreCase(color)){
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("User is able to view that, for " + element + ", its \"" + property + "\" is: \"" + actualBgColor + "\"");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("For " + element + ", its actual \"" + property + "\" is: \"" + actualBgColor + "\"");
			}

		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}

	/** This method validates if an element is visible
	 * @param driver - holds the Webdriver
	 * @param logger - holds the result logs of this method
	 * @param element - holds the element to be validated
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author ksamaniego
	 */
	public static void isVisible (WebDriver driver, RunLogger logger, WebElement element) throws Exception {

		try {
			logger.setStepDesc("Validate if an element is visible");
			logger.setExpectedResult("User should be able to view \"" + element + "\"");

			//			GenericPageWaits.waitElementVisible(driver, selector);
			GenericJSActions.scrollIntoView(driver, element);
			if (element.isDisplayed()) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("User is able to view \"" + element + "\"");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("User is NOT able to view \"" + element + "\"");
			}

		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}

	/** This method validates if an element is selectable
	 * @param driver - holds the Webdriver
	 * @param logger - holds the result logs of this method
	 * @param element - holds the element to be validated
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author ksamaniego
	 */
	public static void isSelectable (WebDriver driver, RunLogger logger, WebElement element) throws Exception {

		try {
			logger.setStepDesc("Validate if an element is selectable");
			logger.setExpectedResult("\"" + element + "\" should be selectable");

			//			GenericPageWaits.waitElementVisible(driver, selector);
			GenericJSActions.scrollIntoView(driver, element);
			if (element.isEnabled()) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("\"" + element + "\" is selectable");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("\"" + element + "\" is NOT selectable");
			}

		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}
	
	/** This method validates if an element is not selectable
	 * @param driver - holds the Webdriver
	 * @param logger - holds the result logs of this method
	 * @param element - holds the element to be validated
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author ksamaniego
	 */
	public static void isNotSelectable (WebDriver driver, RunLogger logger, WebElement element) throws Exception {
		
		try {
			logger.setStepDesc("Validate if an element is not selectable");
			logger.setExpectedResult("\"" + element + "\" should not be selectable");
			
//			GenericPageAction.waitElementVisible(driver, selector);
			GenericJSActions.scrollIntoView(driver, element);
			if (!element.isEnabled()) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("\"" + element + "\" is NOT selectable");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("\"" + element + "\" is selectable");
			}
	
		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}

	/** This method validates if an element is selected
	 * @param driver - holds the Webdriver
	 * @param logger - holds the result logs of this method
	 * @param element - holds the element to be validated
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author ksamaniego
	 */
	public static void isSelected(WebDriver driver, RunLogger logger, WebElement element) throws Exception {

		try {
			logger.setStepDesc("Validate if an element is selected");
			logger.setExpectedResult("\"" + element + "\" should be selected");

			//			GenericPageWaits.waitElementVisible(driver, selector);
			GenericJSActions.scrollIntoView(driver, element);
			if (element.isSelected()) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("\"" + element + "\" is selected");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("\"" + element + "\" is NOT selected");
			}

		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}

	/** This method validates if an element is selected
	 * @param driver - holds the Webdriver
	 * @param logger - holds the result logs of this method
	 * @param element - holds the element to be validated
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author ksamaniego
	 */
	public static void isNotSelected(WebDriver driver, RunLogger logger, WebElement element) throws Exception {

		try {
			logger.setStepDesc("Validate if an element is NOT selected");
			logger.setExpectedResult("\"" + element + "\" should NOT be selected");

			//			GenericPageWaits.waitElementVisible(driver, selector);
			GenericJSActions.scrollIntoView(driver, element);
			if (!element.isSelected()) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("\"" + element + "\" is NOT selected");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("\"" + element + "\" is selected");
			}

		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}

	/** This method validates if an element contains part of a text
	 * @param driver - holds the Webdriver
	 * @param logger - holds the result logs of this method
	 * @param element - holds the element to be validated
	 * @param text - holds the expected text
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author ksamaniego
	 */
	public static void isTextVisible (WebDriver driver, RunLogger logger, WebElement element, String text) throws Exception {

		try {
			logger.setStepDesc("Validate if an element contains part of a text");
			logger.setExpectedResult("User should be able to view that, for " + element + ", it contains the text: \"" + text + "\"");

			GenericJSActions.scrollIntoView(driver, element);
			String actualText = element.getText().trim();
			if (actualText.contains(text)) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("User is able to view that, for " + element + ", it contains the text: \"" + actualText + "\"");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("For " + element + ", its actual text is: \"" + actualText + "\"");
			}

		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}

	/** This method validates if an element has the expected exact text
	 * @param driver - holds the Webdriver
	 * @param logger - holds the result logs of this method
	 * @param element - holds the element to be validated
	 * @param text - holds the expected text
	 * @throws Exception - throws exceptions when there's an error within this method
	 * @author ksamaniego
	 */
	public static void isExactTextVisible (WebDriver driver, RunLogger logger, WebElement element, String text) throws Exception {

		try {
			logger.setStepDesc("Validate if an element has the expected exact text");
			logger.setExpectedResult("User should be able to view that, for " + element + ", its exact text is: \"" + text + "\"");

			GenericJSActions.scrollIntoView(driver, element);
			String actualText = element.getText().trim();
			if (actualText.equals(text)) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("User is able to view that, for " + element + ", its exact text is: \"" + actualText + "\"");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("For " + element + ", its actual text is: \"" + actualText + "\"");
			}

		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate \"" +  element +"\"");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
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

	public static void isPseudoElementVisible(WebDriver driver, RunLogger logger, WebElement element, String pseudoelement) throws Exception {
		try {
			logger.setStepDesc("Validate if a pseudo element is visible");
			logger.setExpectedResult("User should be able to view a pseudo element");

			String webElem = element.toString();
			webElem = webElem.substring(webElem.indexOf("css selector: ") + 14, webElem.length() - 1);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			String text = (String) jse.executeScript("return window.getComputedStyle(document.querySelector('" + webElem + "'),':" + pseudoelement + "').getPropertyValue('visibility')");
			if (text.equals("visible")) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("User is able to view the pseudo element");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("User is NOT able to view the pseudo element");
			}
		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Unable to locate pseudo element");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}
	
	/** Verifies web driver size
	 * 
	 * @param driver
	 * @param logger
	 * @param expectedWidth
	 * @param expectedHeight
	 * @author icon.vergara
	 */
	public static void verifyWebDriverSize(WebDriver driver, RunLogger logger, int expectedWidth, int expectedHeight) throws Exception {
		try {
			logger.setStepDesc("Verify webdriver size");
			logger.setExpectedResult("Web driver size is (wxh): " + expectedWidth + "x" + expectedHeight);


			Dimension expectedDimension = new Dimension(expectedWidth, expectedHeight);
		    
			Dimension actualDimension = driver.manage().window().getSize();
			if(actualDimension.equals(expectedDimension)) {
				logger.setStepMark(RunLogger.PASS);
			}else {
				logger.setStepMark(RunLogger.FAIL);
			}
			logger.setActualResult("Web driver size was (wxh): " + actualDimension.getWidth() + "x" + actualDimension.getHeight());

		} catch (Exception e) {
			String eMessage = String.format("I/O error: %s%n", e);
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Exception occured: " + eMessage);
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}

	public static Boolean verifyElementSize(WebElement elem, int expectedHeight, int expectedWidth) throws Exception {
		Boolean result = false;
		Integer actualHeight = elem.getSize().getHeight();
		Integer actualWidth = elem.getSize().getWidth();

		if(actualHeight.equals(expectedHeight) && actualWidth.equals(expectedWidth)) {
			result=true;
		}
		return result;
	}
	
	/** This method is used to validate the position of elements on x-axis
	 * @param elem1, elem2
	 * @throws Exception
	 * @return Boolean result
	 * @author ahmad.benito
	 */
	public static boolean checkElementAlignmentsX (WebElement elem1, WebElement elem2) {
		boolean result = false;

		Integer element1Location = elem1.getLocation().x;
		Integer element2Location = elem2.getLocation().x;

		if(element1Location.equals(element2Location)) {
			result=true;
		}

		return result;
	}
	
	public static boolean checkElementBGColor(WebElement element,String expectedHexColor) {
		boolean flag = false;
		flag = Color.fromString(element.getCssValue("background-color")).asHex().equals(expectedHexColor);
		return flag;
	}
	
	public static boolean checkElementBorderColor(WebElement element,String expectedHexColor) {
		boolean flag = false;
		flag = Color.fromString(element.getCssValue("border")).asHex().contains(expectedHexColor);
		return flag;
	}
	
	public static boolean checkElementColor(WebElement element,String expectedHexColor) {
		boolean flag = false;
		flag = Color.fromString(element.getCssValue("color")).asHex().equals(expectedHexColor);
		return flag;
	}
}
