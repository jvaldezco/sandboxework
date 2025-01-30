package ework.factory.webdriver;


import org.openqa.selenium.virtualauthenticator.VirtualAuthenticatorOptions;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticatorOptions.Protocol;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticatorOptions.Transport;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.virtualauthenticator.HasVirtualAuthenticator;
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

		VirtualAuthenticatorOptions authOptions = new VirtualAuthenticatorOptions()
			.setProtocol(Protocol.CTAP2)
			.setTransport(Transport.USB)
			.setHasResidentKey(true)
			.setHasUserVerification(true)
			.setIsUserVerified(true);

		VirtualAuthenticator authenticator = ((HasVirtualAuthenticator) driver).addVirtualAuthenticator(authOptions);
		opt.setExperimentalOption("webauthn:extension:credBlob", true);
		opt.setExperimentalOption("webauthn:extension:largeBlob", true);
		opt.setExperimentalOption("webauthn:extension:minPinLength", true);
		opt.setExperimentalOption("webauthn:extension:prf", true);
		opt.setExperimentalOption("webauthn:virtualAuthenticators", true);

		return driver;
	}
}