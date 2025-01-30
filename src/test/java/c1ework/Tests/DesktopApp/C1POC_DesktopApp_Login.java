package c1ework.Tests.DesktopApp;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import c1ework.PageActions.CambridgeOne.C1SplashPageAction;
import c1ework.PageActions.DesktopApp.*;
import c1ework.PageVerifications.DesktopApp.DesktopAppDashboardVerifications;
import ework.genericactions.GenericBrowserActions;
import ework.genericactions.GenericPageWaits;
import ework.utils.TestDriver;

public class C1POC_DesktopApp_Login extends TestDriver {

	String 
	username,
	password;

	public void login() throws Exception {
		C1SplashPageAction.enterEmailAddress(driver, logger, "eqadatea01@eltqa.msdc.co");
		C1SplashPageAction.enterPassword(driver, logger, "passw0rd123");
		C1SplashPageAction.clickLogInSubmitBtn(driver, logger);
	}
	
	@Test
	@Parameters("browser")
	public void DA_LOGIN(String browser) {
		try {
			logger.logScenario("Desktop App Login", "User login test");
			if (browser.toUpperCase().equalsIgnoreCase("WINDOWSDA_V1")){
				DesktopAppLogInPageActions.click_logInWebBrowser_btn(driver, logger);
				login();
			} else if (browser.equalsIgnoreCase("WINDOWSDA_V2")) {
				DesktopAppDashboardVerifications.verify_dashboard_visibility(driver, logger);
			}
			logger.logUpdateScenario();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			logger.logUpdateScenario();
		}
	}
}
