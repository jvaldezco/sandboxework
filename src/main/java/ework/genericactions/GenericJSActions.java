package ework.genericactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericJSActions {
	public static void jsElementClick(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", element);
	}
	
	public static void pseudoElementClick(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0],':before'.click();", element);
	}

	/** Used to return the 'content' value of the psuedoelement
	 * @param driver
	 * @param element
	 * @param psuedoelement - :after, :before
	 * @return
	 * @throws Exception
	 */
	public static String getPsuedoElementContent(WebDriver driver, WebElement element, String psuedoelement) throws Exception {
		String webElem = element.toString();
		webElem = webElem.substring(webElem.indexOf("css selector: ") + 14, webElem.length() - 1);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		return (String) jse.executeScript("return window.getComputedStyle(document.querySelector('" + webElem + "'),':" + psuedoelement + "').getPropertyValue('content')");
	}

	/** Used to check if the psuedoelement is visible
	 * @param driver
	 * @param element
	 * @param psuedoelement - :after, :before
	 * @return
	 * @throws Exception
	 */
	public static boolean psuedoElementCheckVisibility(WebDriver driver, WebElement element, String psuedoelement) throws Exception {
		String webElem = element.toString();
		webElem = webElem.substring(webElem.indexOf("css selector: ") + 14, webElem.length() - 1);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String text = (String) jse.executeScript("return window.getComputedStyle(document.querySelector('" + webElem + "'),':" + psuedoelement + "').getPropertyValue('visibility')");
		if (text.equals("visible")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void changeElementText(WebDriver driver, WebElement element, String text) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].innerText = '" + text + "';", element);
	}
	
	public static void jsAudioPlay(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].play();", element);
	}
	
	public static void jsAudioPause(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].pause();", element);
	}
	
	public static void jsAudioMute(WebDriver driver, WebElement element, boolean mute) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].muted = " + Boolean.toString(mute).toLowerCase(), element);
	}
	
	public static void jsAudioVolume(WebDriver driver, WebElement element, String volume) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].volume = " + volume + ";", element);
	}
	
	public static boolean jsCheckAudioPauseState(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		return Boolean.valueOf(jse.executeScript("return arguments[0].paused", element).toString());
	}
	
	public static boolean jsCheckAudioMuteState(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		return Boolean.valueOf(jse.executeScript("return arguments[0].muted", element).toString());
	}
	
	public static void scrollIntoView(WebDriver driver, WebElement elem) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(false);", elem);
	}
	
	public static void scrollDownToDesiredLength(WebDriver driver, String height) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0, "+ height +");");
	}
	
	public static void scrollToBottom (WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
	
	public static void scrollToTop(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0, 0);");
	}
	
    public static void scrollElementToCenter(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
    }
	
	public static String JsRunner(WebDriver driver,String JsScript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		return (String) js.executeScript(JsScript);
	}
	
	/**
	 * @param driver
	 * @param JsScriptPercent - data in Shopfront Data Factory
	 * @return
	 */
	public static String jsZoom(WebDriver driver,String JsScriptPercent) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;  
		return (String) jse.executeScript("document.body.style.zoom='" + JsScriptPercent + "'");
	}
	
	/** This method will not forcely close the webdriver - for closing app  modal verifications
	 * @param driver
	 */
	public static void patiallyCloseCurrentWebDriver(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.close();");
	}
	
	public static boolean jsBoldFormatChecker(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		// Highlight and select the text in the input
		jse.executeScript("arguments[0].setSelectionRange(0, arguments[0].value.length)", element);
		
		// Get formatting
		String fontStyle = (String) (jse.executeScript("return window.getComputedStyle(window.getSelection().focusNode.parentElement).getPropertyValue('font-weight');"));
		
		// Validate the formatting
        return fontStyle.equals("bold");
	}
	
	public static boolean jsItalicFormatChecker(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		// Highlight and select the text in the input
		jse.executeScript("arguments[0].setSelectionRange(0, arguments[0].value.length)", element);
		
		// Get formatting
		String fontStyle = (String) (jse.executeScript("return window.getComputedStyle(window.getSelection().focusNode.parentElement).getPropertyValue('font-style');"));
			
		// Validate the formatting
        return fontStyle.equals("italic");
	}
	
	public static boolean jsUnderlinedFormatChecker(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		// Highlight and select the text in the input
		jse.executeScript("arguments[0].setSelectionRange(0, arguments[0].value.length)", element);
		
		// Get formatting
		String fontStyle = (String) (jse.executeScript("return window.getComputedStyle(window.getSelection().focusNode.parentElement).getPropertyValue('text-decoration');"));
		
		// Validate the formatting
        return fontStyle.contains("underline");
	}
	
	public static void mouseHover(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("var element = arguments[0];"
                + "var mouseEvent = document.createEvent('MouseEvents');"
                + "mouseEvent.initEvent('mouseover', true, true);"
                + "element.dispatchEvent(mouseEvent);", element);
	}
	
	public static void highlightText(WebDriver driver, WebElement element) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.backgroundColor='yellow';" +
                "arguments[0].style.fontWeight='bold';", element);
		Thread.sleep(1000);
	}
	
	public static void highlightTextRevert(WebDriver driver, WebElement element) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.backgroundColor='';" +
                "arguments[0].style.fontWeight='';", element);
		Thread.sleep(1000);
	}
	
	/** Used to return the 'color' value of the psuedoelement
     * @param driver
     * @param element
     * @param psuedoelement - :after, :before
     * @return
     * @throws Exception
     */
    public static String getPsuedoElementColor(WebDriver driver, WebElement element, String psuedoelement) throws Exception {
        String webElem = element.toString();
        webElem = webElem.substring(webElem.indexOf("css selector: ") + 14, webElem.length() - 1);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        return (String) jse.executeScript("return window.getComputedStyle(document.querySelector('" + webElem + "'),':" + psuedoelement + "').getPropertyValue('color')");
    }
    
    public static String rgbStringToHex(String rgbString) {
        rgbString = rgbString.replaceAll("rgb\\(", "").replaceAll("\\)", "");
        String[] rgbValues = rgbString.split(", ");

        if (rgbValues.length != 3) {
            throw new IllegalArgumentException("Invalid RGB format");
        }

        int red = Integer.parseInt(rgbValues[0]);
        int green = Integer.parseInt(rgbValues[1]);
        int blue = Integer.parseInt(rgbValues[2]);

        String hexRed = Integer.toHexString(red);
        String hexGreen = Integer.toHexString(green);
        String hexBlue = Integer.toHexString(blue);

        hexRed = padWithZeros(hexRed);
        hexGreen = padWithZeros(hexGreen);
        hexBlue = padWithZeros(hexBlue);

        String hexColor = "#" + hexRed + hexGreen + hexBlue;
        return hexColor;
    }

    public static String padWithZeros(String hexValue) {
        return hexValue.length() == 1 ? "0" + hexValue : hexValue;
    }
	
}
