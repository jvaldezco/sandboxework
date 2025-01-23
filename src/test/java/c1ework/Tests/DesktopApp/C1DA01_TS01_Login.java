package c1ework.Tests.DesktopApp;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import c1ework.PageActions.DesktopApp.*;
import c1ework.PageVerifications.DesktopApp.DesktopAppDashboardVerifications;
import ework.utils.TestDriver;

public class C1DA01_TS01_Login extends TestDriver {

	String 
	username,
	password;
	
	@Test
	public void DA_LOGIN() {
		try {
			logger.logScenario("Desktop App Login", "User should be able to login via web app");
			DesktopAppLogInPageActions.click_logInWebBrowser_btn(driver, logger);
			DesktopAppDashboardVerifications.verify_dashboard_visibility(driver, logger);
			logger.logUpdateScenario();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			logger.logUpdateScenario();
		}
	}
}
