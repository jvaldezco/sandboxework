package ework.factory.webdriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.client.ClientUtil;

import ework.utils.TestDriver;

public class WebDriverChrome extends EworkDriver {
	private WebDriver driver;
	private Proxy proxy;
	private BrowserUpProxy bup = TestDriver.bup;
	
	@Override
	public WebDriver initWebDriver() throws Exception {
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		
		prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
		prefs.put("download.prompt_for_download", "false");
		prefs.put("download.extensions_to_open", "text/csv");
		prefs.put("profile.default_content_settings.popups", 1);
		prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
		prefs.put("plugins.always_open_pdf_externally", true);
		prefs.put("plugins.plugins_disabled", new String[] {
			    "Adobe Flash Player","Chrome PDF Viewer"
			    });
		DesiredCapabilities caps = new DesiredCapabilities();
		ChromeOptions copt = new ChromeOptions();
		
		copt.addArguments(Arrays.asList(
				"test-type",
				"disable-popup-blocking",
				"disable-default-apps",
				"auto-launch-at-startup",
				"always-authorize-plugins",
				"disable-infobars",
				"disable-infobar-for-protected-media-identifier",				
				"safebrowsing-disable-download-protection",
				"ignore-certificate-errors",
				"--disable-backgrounding-occluded-windows",
				"start-maximized",
				"--no-sandbox",
				"--disable-dev-shm-usage",
				"user-data-dir=C:\\Users\\jvaldezco\\AppData\\Local\\Google\\Chrome\\User Data"
				));
		
		if(bup != null) {
			proxy = ClientUtil.createSeleniumProxy(bup);
			copt.setCapability(CapabilityType.PROXY, proxy);
		}
		
		copt.setExperimentalOption("prefs", prefs);
		caps.setCapability(ChromeOptions.CAPABILITY, copt);
		
		driver = new ChromeDriver(copt);
		
		return driver;
	}
}
