package c1ework.PageObjects.DesktopApp;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class DesktopAppLogInPage {

    public static By getLogiInWithWebBrowser_btn =  By.cssSelector("div#login-container button.login-btn");
	public static WebElement getLogiInWithWebBrowser_btn(SearchContext driver) throws NoSuchElementException {
		return driver.findElement(getLogiInWithWebBrowser_btn);
	}
 
}