package c1ework.PageObjects.CambridgeOne;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class C1LogInForm {

    public static WebElement getEmailTextField(SearchContext driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("form#gigya-login-form input[name=username].gigya-input-text"));
	}

    public static By getPWordTextField = By.id("gigya-password-56383998600152700");

	public static WebElement getPWordTextField(SearchContext driver) throws NoSuchElementException {
		return driver.findElement(getPWordTextField);
	}

    public static WebElement getLogInBtn(SearchContext driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("form#gigya-login-form input.gigya-input-submit"));
	}
    
}
