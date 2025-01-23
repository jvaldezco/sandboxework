package c1ework.PageActions.DesktopApp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import c1ework.PageObjects.DesktopApp.DesktopAppLogInPage;
import ework.genericactions.GenericJSActions;
import ework.genericactions.GenericPageWaits;
import ework.utils.RunLogger;

public class DesktopAppLogInPageActions {

	public static void click_logInWebBrowser_btn(WebDriver driver, RunLogger logger) throws Exception {
		try {
			logger.setStepDesc("Click Log In Submit form");
			logger.setExpectedResult("\"LOG IN\" button is clicked");
            
			GenericJSActions.scrollIntoView(driver, DesktopAppLogInPage.getLogiInWithWebBrowser_btn(driver));
			GenericJSActions.jsElementClick(driver, DesktopAppLogInPage.getLogiInWithWebBrowser_btn(driver));
            GenericPageWaits.delay();

			logger.setStepMark(RunLogger.PASS);
			logger.setActualResult("\"LOG IN\" button was clicked");
		} catch (Exception e) {
			logger.setStepMark(RunLogger.FAIL);
			logger.setActualResult("\"LOG IN\" button was not clicked");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}
}
