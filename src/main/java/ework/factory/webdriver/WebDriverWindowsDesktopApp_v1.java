package ework.factory.webdriver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import ework.utils.TestDriver;

public class WebDriverWindowsDesktopApp_v1 extends EworkDriver {
	private WebDriver driver;
	
	@Override
	public WebDriver initWebDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver", TestDriver.config.getChromeDriver_da());

		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		opt.setBinary(TestDriver.config.getDesktopAppPath());
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, opt);
		
		driver = new ChromeDriver(opt);
		return driver;
	}
}