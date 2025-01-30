package c1ework.PageActions.CambridgeOne;

import org.openqa.selenium.WebDriver;

import c1ework.PageObjects.CambridgeOne.C1LogInForm;
import ework.genericactions.GenericJSActions;
import ework.genericactions.GenericPageWaits;
import ework.utils.RunLogger;

public class C1SplashPageAction {
    public static void enterEmailAddress(WebDriver driver, RunLogger logger, String value) throws Exception {
		try {
			logger.setStepDesc("Validate that the user is able to enter an email address: " + value);
			logger.setExpectedResult("Email Address should be entered");

			GenericJSActions.scrollIntoView(driver, C1LogInForm.getEmailTextField(driver));
			C1LogInForm.getEmailTextField(driver).clear();
			C1LogInForm.getEmailTextField(driver).sendKeys(value);

			if (C1LogInForm.getEmailTextField(driver).getAttribute("value").equals(value)) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("Email Address is correctly entered");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("Email Address entered is incorrect: "
						+ C1LogInForm.getEmailTextField(driver).getAttribute("value"));
			}
		} catch (Exception e) {
			logger.setStepMark(RunLogger.FAIL);
			logger.setActualResult("Email Address is not entered");
		} finally {
			// logger.setPostStepScreenshot(driver);
			// logger.logStep();
		}
	}

    public static void enterPassword(WebDriver driver, RunLogger logger, String value) throws Exception {
		try {
			logger.setStepDesc("Validate that the user is able to enter a password");
			logger.setExpectedResult("Password should be entered");

			GenericJSActions.scrollIntoView(driver, C1LogInForm.getPWordTextField(driver));
			C1LogInForm.getPWordTextField(driver).clear();
			C1LogInForm.getPWordTextField(driver).sendKeys(value);

			if (C1LogInForm.getPWordTextField(driver).getAttribute("value").equals(value)) {
				logger.setStepMark(RunLogger.PASS);
				logger.setActualResult("Password was correctly entered");
			} else {
				logger.setStepMark(RunLogger.FAIL);
				logger.setActualResult("Password entered was incorrect: "
						+ C1LogInForm.getPWordTextField(driver).getAttribute("value"));
			}
		} catch (Exception e) {
			logger.setStepMark(RunLogger.FAIL);
			logger.setActualResult("Password was NOT entered");
		} finally {
			logger.setPostStepScreenshot(driver);
			// logger.logStep();
		}
	}

    public static void clickLogInSubmitBtn(WebDriver driver, RunLogger logger) throws Exception {
		try {
			logger.setStepDesc("Validate that the user is able to submit Log In form");
			logger.setExpectedResult("\"LOG IN\" button should be clicked");

			GenericJSActions.scrollIntoView(driver, C1LogInForm.getLogInBtn(driver));
			GenericJSActions.jsElementClick(driver, C1LogInForm.getLogInBtn(driver));
			GenericJSActions.scrollToTop(driver);
			GenericPageWaits.waitPageLoad(driver);

			GenericPageWaits.delay();
			logger.setStepMark(RunLogger.PASS);
			logger.setActualResult("\"LOG IN\" button is clicked");
		} catch (Exception e) {
			logger.setStepMark(RunLogger.FAIL);
			logger.setActualResult("\"LOG IN\" button is not clicked");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}
}