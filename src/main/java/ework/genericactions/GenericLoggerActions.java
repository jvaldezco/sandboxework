package ework.genericactions;

import org.openqa.selenium.WebDriver;

import ework.utils.RunLogger;

public class GenericLoggerActions {

	public static void logCoveredScenario(WebDriver driver, RunLogger logger, String userStory, String TSID) throws Exception {
		try {
			logger.setStepDesc("The scenarios from " + userStory + " are covered in " + TSID);
			logger.setExpectedResult("The AC's from " + userStory + " are covered in " + TSID);
			
			logger.setStepMark(RunLogger.PASS);
			logger.setActualResult("The AC's from " + userStory + " are covered in " + TSID);
		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Error");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}
	
	public static void logScenarioAsPassed(WebDriver driver, RunLogger logger, String userStory, String passedReason) throws Exception {
		try {
			logger.setStepDesc("The scenarios from " + userStory + " are tagged as passed.");
			logger.setExpectedResult("The scenarios from " + userStory + " are tagged as passed because of the following reason: " + passedReason);
			
			logger.setStepMark(RunLogger.PASS);
			logger.setActualResult("The scenarios from " + userStory + " are tagged as passed because of the following reason: " + passedReason);
		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Error");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}
	
	public static void logScenarioAsFailed(WebDriver driver, RunLogger logger, String userStory, String failedReason) throws Exception {
		try {
			logger.setStepDesc("The scenarios from " + userStory + " are tagged as failed.");
			logger.setExpectedResult("The scenarios from " + userStory + " are tagged as failed because of the following reason: " + failedReason);
			
			logger.setStepMark(RunLogger.FAIL);
			logger.setActualResult("The scenarios from " + userStory + " are tagged as failed because of the following reason: " + failedReason);
		} catch (Exception e) {
			logger.setStepMark(RunLogger.ERR);
			logger.setActualResult("Error");
		} finally {
			logger.setPostStepScreenshot(driver);
			logger.logStep();
		}
	}
}
