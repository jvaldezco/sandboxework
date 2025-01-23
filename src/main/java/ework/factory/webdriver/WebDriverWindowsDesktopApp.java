package ework.factory.webdriver;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import ework.utils.TestDriver;

public class WebDriverWindowsDesktopApp extends EworkDriver {
	//private WebDriver driver;
	
	@Override
	public WebDriver initWebDriver() throws Exception {
		String port = TestDriver.config.getDebugtronPort();
		
		System.out.println("PORT: " + port);
		
		if (port != null && !port.isEmpty()) {
			System.out.println(TestDriver.config.getDriverPath() + TestDriver.config.getChromeDriver_da());
			System.setProperty("webdriver.chrome.driver", TestDriver.config.getDriverPath() + TestDriver.config.getChromeDriver_da());
			
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--remote-allow-origins=*");
			
			// Ensure the path to the Chrome binary is correct
			String chromeBinaryPath = TestDriver.config.getDesktopAppPath();
			opt.setBinary(chromeBinaryPath);
			
			opt.setExperimentalOption("debuggerAddress", "localhost:" + port);
			
			URL remoteWebDriverUrl = new URL("http://localhost:" + port);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, opt);
			
			// Initialize the RemoteWebDriver with the capabilities
			WebDriver driver = new RemoteWebDriver(remoteWebDriverUrl, capabilities);
			return driver;
		} else {
			throw new Exception("Debugtron port is not set in the configuration file.");
		}
	}
}