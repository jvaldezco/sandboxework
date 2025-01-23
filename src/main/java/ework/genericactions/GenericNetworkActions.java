package ework.genericactions;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.Response;

import com.google.common.collect.ImmutableMap;

public class GenericNetworkActions {
	@SuppressWarnings("deprecation")
	public static void disableDesktopInternetConnection() throws Exception {
		Runtime.getRuntime().exec("cmd /c ipconfig /release");
		GenericPageWaits.delay(5);
	}
	
	@SuppressWarnings("deprecation")
	public static void enableDesktopInternetConnection() throws Exception {
		Runtime.getRuntime().exec("cmd /c ipconfig /renew");
		GenericPageWaits.delay(5);
	}

	@SuppressWarnings("unused")
	public static void setNetwork(WebDriver driver, boolean offline) throws Exception {

		Map<String, Object> net = new HashMap<String, Object>();
		net.put("offline", offline);
		net.put("latency", 5);
		net.put("download_throughput", 5500);
		net.put("upload_throughput", 5500);

		CommandExecutor ce = ((ChromeDriver) driver).getCommandExecutor();
		Response response = ce.execute(new Command(((ChromeDriver) driver).getSessionId(),"setNetworkConditions", ImmutableMap.of("network_conditions", ImmutableMap.copyOf(net))));
		GenericPageWaits.delay(10);

		if (offline) {
			System.out.println("OFFLINE");
		} else {
			System.out.println("ONLINE");
		}

	}

}
