package ework.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.BrowserUpProxyServer;
import com.google.gson.Gson;

import ework.factory.environment.Environment;
import ework.factory.environment.EnvironmentInit;
import ework.factory.webdriver.EworkDriver;
import ework.factory.webdriver.EworkDriverFactory;
import ework.genericactions.GenericBrowserActions;
import ework.genericactions.GenericFileActions;
import io.appium.java_client.android.AndroidDriver;
import io.restassured.RestAssured;

public class TestDriver {
	private static int runID = 0;
	public static WebDriver driver;
	public static RunLogger logger;
	public static ConfigFile config;
	public static String url;
	public static BrowserUpProxy bup;
	private Environment environment;
	private EworkDriver edriver;

	@BeforeSuite
	@Parameters({"browser"})
	public void setupSuite(@Optional("chrome") String browser) throws URISyntaxException {
		
		System.out.println("Setting up Suite configurations...");
		
        File jsonfile = new File("conf.json");
		
		String jsontext = "";
		
		try {
			jsontext = new String(Files.readAllBytes(jsonfile.toPath()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
		
		Gson gson = new Gson();
		config = gson.fromJson(jsontext, ConfigFile.class);
		
		System.out.println("Configurations ready...");
				
		logger = new RunLogger(config.getRunLabel(), config.getRunTags());
		
		logger.attachReporter();
		logger.extentTest = logger.extent.createTest(getSuiteName());

		System.out.println("DB connections ready...");
	}
	
	@BeforeTest
	@Parameters({"browser", "module", "url"})
	public void setupTest(@Optional("chrome") String browser, @Optional("DEBUG") String module, @Optional String url) throws Exception {
		try {
			
			if(url == null) {
				url = TestDriver.config.getStartURL();
			}
			
			System.out.println("Setting up Test Configurations...");
			//TestDriver.initializeLogger(browser, module);
			
			if(!TestDriver.config.getEnvironment().equals("QA")) {
				this.environment = EnvironmentInit.get(TestDriver.config.getPlatform(), url, TestDriver.config.getEnvironment());
				TestDriver.url = this.environment.initURL();
			} else {
				TestDriver.url = url;
			}
			
			System.out.println(TestDriver.url);
			
			if(!TestDriver.config.getFrontEnd() && !TestDriver.config.getBackEnd()) {
				throw new SQLException("Set at least one(1) test type (front-end/back-end) to true on the json file.");
			}
			
			if(TestDriver.config.getFrontEnd()) {
				if(TestDriver.config.isProxyMode() && !TestDriver.config.getEnvironment().equalsIgnoreCase("PD")) {
					System.out.println("Initializing Proxy server...");
					
					TestDriver.bup = TestDriver.initializeProxy();
					TestDriver.bup.start();
					
					System.out.println("Proxy server started...");
				}
				
				System.out.println("Executing tests for " + module + "...");
				System.out.println("Browser: " + browser);
				System.out.println(TestDriver.url);
				
				this.edriver = EworkDriverFactory.get(browser.toUpperCase(), TestDriver.config.isLambdaTestMode());
				driver = this.edriver.initWebDriver();
			
				
				// driver.manage().window().maximize();
				// GenericBrowserActions.navigateToURL(driver, TestDriver.url);
			}
			
			if(TestDriver.config.getEnvironment().equalsIgnoreCase("CN")) {
				TestDriver.config.setEnvironment("PD");
			}
			
			if(TestDriver.config.getBackEnd()) {
				RestAssured.useRelaxedHTTPSValidation();
			}
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	
	@AfterClass
	public void finalizeTest() {
		if((TestDriver.config.isProxyMode()) && !TestDriver.config.getEnvironment().equalsIgnoreCase("PD")) {
			if(TestDriver.bup.isStarted()) {
				TestDriver.bup.stop();
			}
		}
		
		if (driver instanceof AndroidDriver) {
			if (config.isRelaunchEnabled()) {
				driver.quit();
				driver = null;
			}
		} else {
			driver.quit();
			driver = null;
		}
		
		System.out.println("Finalized Test...");
	}
	
	public static int getRunID() {
		return runID;
	}
	
	public static void initializeBasicDB() throws Exception {
		try {
			DBConnectionPool.createSmallDBPool(config);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:");
			e.printStackTrace();
			throw e;
			
		} catch (SQLException e) {
			System.out.println("SQLException:");
			e.printStackTrace();
			throw e;
		} catch (InstantiationException e) {
			System.out.println("InstantiationException:");
			e.printStackTrace();
			throw e;
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException:");
			e.printStackTrace();
			throw e;
		}
	}
	
	public static String getSuiteName() {
		// Get the current test result using Reporter
		Object currentTestResult = Reporter.getCurrentTestResult();

		// Check if the currentTestResult is not null and is an instance of ITestResult
		if (currentTestResult != null && currentTestResult instanceof org.testng.internal.TestResult) {
			// Get the suite name from the ITestResult
			return ((org.testng.internal.TestResult) currentTestResult).getTestContext().getSuite().getName();
		}

		// Return null or handle the case where the suite name couldn't be retrieved
		return null;
	}
	
	// private static void initializeLogger(String browser, String module) {
	// 	logger = new RunLogger(TestDriver.getRunID(),
	// 			TestDriver.config.getRunLabel(),
	// 			TestDriver.config.getRunTags());
		
	// 	logger.setBrowser(browser);
	// 	logger.setScenarioAppModule(module);
	// 	logger.setScenarioComplexity(0);
	// 	logger.setScenarioPkgClassMethod("");
	// 	logger.setScenarioStoryPts(0);
	// }
	
	public static BrowserUpProxy initializeProxy() {
		BrowserUpProxy proxy = new BrowserUpProxyServer();
		String cf_id, cf_sc;
		Scanner sc = new Scanner(System.in);

		if(TestDriver.config.isCodespacemode()) {
			cf_id = System.getenv("CF_ACC_ID");
			cf_sc = System.getenv("CF_ACC_SC");
		} else {
			cf_id = GenericFileActions.getProperty("cf_id", "cf.properties");
			cf_sc = GenericFileActions.getProperty("cf_sc", "cf.properties");
		}

		sc.close();
		
		proxy.addRequestFilter((request, contents, messageInfo) -> {
			request.headers().add("CF-Access-Client-Id", cf_id);
			request.headers().add("CF-Access-Client-Secret", cf_sc);
			return null; 
		});
		
		return proxy;
	}
}
