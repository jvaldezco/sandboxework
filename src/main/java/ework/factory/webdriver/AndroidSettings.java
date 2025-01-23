package ework.factory.webdriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class AndroidSettings {
	public static Map<String, Integer> device_ports;
	
	private String appiumip, pfname, pfver, deviceid, devicename;
	private int port, timeout;
	
	public AndroidSettings() {
		this.port = 4723;
		this.appiumip = "127.0.0.1";
		this.pfname = "android";
		this.pfver = "";
		this.deviceid = "";
		this.devicename = "";
		this.timeout = 120;
	}
	
	public void setAppiumPort(int port) {
		this.port = port;
	}
	
	public void setAppiumIP(String ip) {
		this.appiumip = ip;
	}
	
	public void setPlatformName(String pfname) {
		this.pfname = pfname;
	}
	
	public void setPlatformVersion(String pfver) {
		this.pfver = pfver;
	}
	
	public void setDeviceID(String deviceid) {
		this.deviceid = deviceid;
	}
	
	public void setDeviceName(String devicename) {
		this.devicename = devicename;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void setCmdTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	@SuppressWarnings("deprecation")
	public URL getAppiumURL() throws MalformedURLException {
		return new URL("http://" + this.appiumip + ":" + this.port + "/wd/hub");
	}
	
	public String getPlatformName() {
		return this.pfname;
	}
	
	public String getPlatformVersion() {
		return this.pfver;
	}
	
	public String getDeviceID() {
		return this.deviceid;
	}
	
	public String getDeviceName() {
		return this.devicename;
	}
	
	public int getCmdTimeout() {
		return this.timeout;
	}
}
