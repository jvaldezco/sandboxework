package c1ework.PageActions.DesktopApp;
import org.openqa.selenium.WebDriver;

import c1ework.PageObjects.DesktopApp.DesktopAppLogInPage;
import ework.genericactions.GenericJSActions;
import ework.genericactions.GenericPageWaits;
import ework.utils.RunLogger;

public class DesktopAppLogInPageActions {

	public static void click_logInWebBrowser_btn(WebDriver driver, RunLogger logger) throws Exception {
		try {
			logger.setStepDesc("Verify that login button is able click");
			logger.setExpectedResult("Login button should be clickable");
            
			GenericPageWaits.waitElementVisible(driver, DesktopAppLogInPage.getLogiInWithWebBrowser_btn);
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
