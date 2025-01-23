package ework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.google.gson.Gson;

@SuppressWarnings("unused")
public class RunLogger {

	String formattedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss"));
	String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM.dd"));

	// for if condition kung yung run stream/release label matches the condition
	Boolean placeholderConfFlag = true;

	private static String testName;

	private DateTimeFormatter dtf;

	private int runID;
	private String runlabel, runtags = "DEV";

	private int runscenarioID, storypts, complexity;
	private String scenarioID, scenariodesc, pkgclassmethod, appmodule, wbrowser;

	private String stepdesc, expected, actual, stepmark;
	private long filesize, filesize2;
	private FileInputStream fstream, fstream2;

	public static final String PASS = "PASSED";
	public static final String FAIL = "FAILED";
	public static final String ERR = "ERROR";

	public static String base64Screenshot;

	ExtentReports extent = new ExtentReports();
	String fileName = formattedTime + " - " + TestDriver.getSuiteName();
	ExtentTest extentTest;
	private static ExtentTest parent;
	private static ExtentTest child;
	JsonFormatter jsonPath = path();
	ExtentSparkReporter spark = reportPath();

	public ExtentSparkReporter reportPath() {
		if (TestDriver.config.isRun_official()) {
			return new ExtentSparkReporter("Reports/" + TestDriver.config.getReleaseLabel() + "/" + fileName + ".html");
		} else {
			if (TestDriver.config.getFrontEnd()) {
				return new ExtentSparkReporter("Reports/" + formattedDate + "/" + fileName + ".html");
			} else {
				return new ExtentSparkReporter("Reports/API/" + formattedDate + "/" + fileName + ".html");
			}
		}
	}

	public JsonFormatter path() {
		return new JsonFormatter(
				"Reports/" + TestDriver.config.getReleaseLabel() + "/" + "ExtentJSON" + "/" + fileName + ".json");
	}

	public void config() {
		spark.config().thumbnailForBase64(true);
		spark.config().setReportName(TestDriver.getSuiteName());
	}

	public void attachReporter() {
		if (TestDriver.config.isRun_official()) {
			extent.attachReporter(spark, jsonPath);
		} else {
			extent.attachReporter(spark);
		}
		config();
		System.out.println("Reporter created...");
	}

	public void flush() {
		extent.flush();
		// System.out.println("Test updated");
	}

	private void reset() {
		this.dtf = DateTimeFormatter.ofPattern("MM-dd-yy HH:mm:ss");
		this.runscenarioID = 0;
		this.runID = 0;
		this.filesize = 0;
		this.filesize2 = 0;
	}

	public RunLogger(String runlabel) {
		this.reset();
		this.runlabel = runlabel;
	}

	public RunLogger(String runlabel, String runtags) {
		this(runlabel);
		this.runtags = runtags;
	}

	public RunLogger(int runID, String runlabel) {
		this(runlabel);
		this.runID = runID;
	}

	public RunLogger(int runID, String runlabel, String runtags) {
		this(runlabel, runtags);
		this.runID = runID;
	}

	public void logStartRun() throws SQLException {
	}

	// private void logRunning() throws SQLException {
	// }

	public void logStopRun() {
	}

	public void logScenario() {
	}

	public void logUpdateScenario() {
		System.out.println("==== Test Completed ====");
		flush();
	}

	public void logScenario(String scenarioID, String desc) {
		// attachReporter();
		parent = extentTest.createNode("<b>" + scenarioID + "</b>" + " : " + desc);
		System.out.println("===" + scenarioID + " : " + desc + "===");
		flush();
		this.setScenarioID(scenarioID);
		this.setScenarioDesc(desc);
		this.logScenario();
	}

	public void setScenarioID(String scenarioID) {
		this.scenarioID = scenarioID;
	}

	public void setScenarioDesc(String desc) {
		this.scenariodesc = desc;
	}

	public void setScenarioPkgClassMethod(String pkgclassmethod) {
		this.pkgclassmethod = pkgclassmethod;
	}

	public String getPkgClassMethod() {
		return this.pkgclassmethod;
	}

	public void setScenarioAppModule(String appmodule) {
		this.appmodule = appmodule;
	}

	public void setScenarioStoryPts(int storypts) {
		this.storypts = storypts;
	}

	public void setScenarioComplexity(int complexity) {
		this.complexity = complexity;
	}

	public void setBrowser(String wbrowser) {
		this.wbrowser = wbrowser;
	}

	public String getBrowser() {
		return this.wbrowser;
	}

//	public void setCurrent(int current) {
//		this.current = current;
//	}

	public void logStep() {
		System.out.println(this.stepmark + ": " + this.actual);
		flush();
	}

	public void logStep(String desc, String expected, String actual, String stepmark) throws IOException {
		this.setStepDesc(desc);
		this.setExpectedResult(expected);
		this.setActualResult(actual);
		this.setStepMark(stepmark);

		try {
			this.clearPreStepScreenshot();
			this.clearPostStepScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.logStep();
	}

	public void logStep(String desc, String expected, String actual, String stepmark, WebDriver poststepimg)
			throws SQLException, IOException {
		this.setStepDesc(desc);
		this.setExpectedResult(expected);
		this.setActualResult(actual);
		this.setStepMark(stepmark);
		this.setPostStepScreenshot(poststepimg);

		this.logStep();
	}

	public void setStepDesc(String desc) {
		child = parent.createNode(desc);
		flush();
		this.stepdesc = desc;
	}

	public void setExpectedResult(String expected) {
		this.expected = expected;
		child.info("Expected result: " + expected);
		// System.out.print(expected);
	}

	public static String CaptureScreen() {
		WebDriver driver = TestDriver.driver;
		TakesScreenshot newScreen = (TakesScreenshot) driver;
		String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
		return "data:image/jpg;base64, " + scnShot;
	}

	public static Media Screenshot() {
		return MediaEntityBuilder.createScreenCaptureFromBase64String(CaptureScreen()).build();
	}

	public void setActualResult(String actual) {
		try {
			this.actual = actual;
			if (TestDriver.driver != null) {
				switch (stepmark) {
				case RunLogger.PASS:
					child.pass(actual).assignCategory("Percentage");
					break;
				case RunLogger.FAIL:
					child.fail(actual).assignCategory("Percentage");
					break;
				case RunLogger.ERR:
					child.skip(actual).assignCategory("Percentage");
					break;
				}
			} else {
				switch (stepmark) {
				case RunLogger.PASS:
					child.pass(actual).assignCategory("Percentage");
					break;
				case RunLogger.FAIL:
					child.fail(actual).assignCategory("Percentage");
					break;
				case RunLogger.ERR:
					child.skip(actual).assignCategory("Percentage");
					break;
				}
			}
			//flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setStepMark(String stepmark) {
		this.stepmark = this.correctMark(stepmark);
	}

	public String getStepMark() {
		return stepmark;
	}

	public void setPreStepScreenshot(WebDriver prestepimg) {
//		File img = (File) ((TakesScreenshot) prestepimg).getScreenshotAs(OutputType.FILE);
//		this.filesize = img.length();
//
//		try {
//			this.fstream = new FileInputStream(img);
//		} catch (FileNotFoundException e) {
//			this.filesize = 0;
//			this.fstream = null;
//			e.printStackTrace();
//		}
	}

	public void setPostStepScreenshot(WebDriver poststepimg) throws SQLException {
//		if (!this.stepmark.equals(PASS)) {
//			File img = (File) ((TakesScreenshot) poststepimg).getScreenshotAs(OutputType.FILE);
//			this.filesize2 = img.length();
//
//			try {
//				this.fstream2 = new FileInputStream(img);
//			} catch (FileNotFoundException e) {
//				this.filesize2 = 0;
//				this.fstream2 = null;
//				e.printStackTrace();
//			}
//		}
		if (!this.stepmark.equals(PASS) || TestDriver.config.isScreenshotOnPass()) {
			child.addScreenCaptureFromBase64String(CaptureScreen());
		}
	}

	

	public void clearPreStepScreenshot() throws IOException {
		if (this.filesize > 0 || this.fstream != null) {
			this.fstream.close();
			this.fstream = null;
			this.filesize = 0;
		}
	}

	public void clearPostStepScreenshot() throws IOException {
		if (this.filesize2 > 0 || this.fstream2 != null) {
			this.fstream2.close();
			this.fstream2 = null;
			this.filesize2 = 0;
		}
	}

	private String correctMark(String mark) {
		String newmark = "";
		CharSequence cs = "pa", cs2 = "er";

		newmark = mark.toLowerCase().contains(cs) ? RunLogger.PASS : RunLogger.FAIL;
		newmark = mark.toLowerCase().contains(cs2) ? RunLogger.ERR : newmark;

		return newmark;
	}

	public int getRunID() {
		return this.runID;
	}

}