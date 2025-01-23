package c1ework.PageVerifications.DesktopApp;

import org.openqa.selenium.WebDriver;
import ework.genericactions.GenericPageWaits;
import ework.utils.RunLogger;

import c1ework.PageObjects.DesktopApp.DesktopAppDashboardPage;

public class DesktopAppDashboardVerifications {

	public static void verify_dashboard_visibility(WebDriver driver, RunLogger logger) throws Exception {
		try {
			logger.setStepDesc("Verify that the user is able to view Desktop App Dashboard");
			logger.setExpectedResult("Dashboard Page should be displayed");
		
			GenericPageWaits.waitElementVisible(driver, DesktopAppDashboardPage.getDashboardPage_cntr);

			if (DesktopAppDashboardPage.getDashboardPage_cntr(driver).isDisplayed()) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("Dashboard is displayed");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("Dashboard is NOT displayed");
			}
		
		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Dashboard was NOT displayed");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}
}