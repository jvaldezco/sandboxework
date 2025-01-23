package ework.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import ework.genericactions.GenericFileActions;

public class ConfigFile {
	private String
	db_pool_name,
	run_tags,
	run_label,
	environment,
	platform,
	start_url,
	appium_ip,
	lt_platform,
	lt_browser,
	lt_browser_ver,
	lt_device,
	u, p,
	driver_path,
	grid_hub_ip,
	chrome_download_path,
	ff_download_path,
	ie_driver_exe,
	ie32_driver_exe,
	ff_driver_exe,
	chrome_driver_exe,
	chrome_driver_da_exe,
	edge_driver_exe,
	desktop_app_path,
	driver_host,
	data_tag,
	stream_label, 
	release_label,
	debugtron_port;
	private int 
	appium_cmd_timeout,
	grid_hub_port,
	pageload_timeout,
	implicit_wait,
	dbthreads;
	
	private int[] appium_ports;
	
	private String[]
	header_key,
	header_value;
	
	private boolean
	front_end,
	back_end,
	lambdatest_mode,
	lt_network,
	lt_image,
	lt_video,
	lt_console,
	appium_mode,
	grid_mode,
	relaunch_browser,
	proxy_mode,
	incognito,
	driver_recycle,
	run_recycle,
	run_official,
	codespace,
	screenshotOnPass;

	public String getDebugtronPort() {
		return this.debugtron_port;
	 }

	public boolean isCodespacemode() {
		return codespace;
	}

	public String getDb_pool_name() {
		return this.db_pool_name;
	}

	public void setDb_pool_name(String db_pool_name) {
		this.db_pool_name = db_pool_name;
	}

	public String getRun_tags() {
		return this.run_tags;
	}

	public void setRun_tags(String run_tags) {
		this.run_tags = run_tags;
	}

	public String getRun_label() {
		return this.run_label;
	}

	public void setRun_label(String run_label) {
		this.run_label = run_label;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getStart_url() {
		return this.start_url;
	}

	public void setStart_url(String start_url) {
		this.start_url = start_url;
	}

	public String getAppium_ip() {
		return this.appium_ip;
	}

	public void setAppium_ip(String appium_ip) {
		this.appium_ip = appium_ip;
	}

	public String getLt_platform() {
		return this.lt_platform;
	}

	public void setLt_platform(String lt_platform) {
		this.lt_platform = lt_platform;
	}

	public String getLt_device() {
		return this.lt_device;
	}

	public void setLt_device(String lt_device) {
		this.lt_device = lt_device;
	}

	public String getU() {
		return this.u;
	}


	public String getP() {
		return this.p;
	}


	public String getDriver_path() {
		return this.driver_path;
	}

	public void setDriver_path(String driver_path) {
		this.driver_path = driver_path;
	}

	public String getGrid_hub_ip() {
		return this.grid_hub_ip;
	}

	public void setGrid_hub_ip(String grid_hub_ip) {
		this.grid_hub_ip = grid_hub_ip;
	}

	public String getChrome_download_path() {
		return this.chrome_download_path;
	}

	public void setChrome_download_path(String chrome_download_path) {
		this.chrome_download_path = chrome_download_path;
	}

	public String getFf_download_path() {
		return this.ff_download_path;
	}

	public void setFf_download_path(String ff_download_path) {
		this.ff_download_path = ff_download_path;
	}

	public String getIe_driver_exe() {
		return this.ie_driver_exe;
	}

	public void setIe_driver_exe(String ie_driver_exe) {
		this.ie_driver_exe = ie_driver_exe;
	}

	public String getIe32_driver_exe() {
		return this.ie32_driver_exe;
	}

	public void setIe32_driver_exe(String ie32_driver_exe) {
		this.ie32_driver_exe = ie32_driver_exe;
	}

	public String getFf_driver_exe() {
		return this.ff_driver_exe;
	}

	public void setFf_driver_exe(String ff_driver_exe) {
		this.ff_driver_exe = ff_driver_exe;
	}

	public String getChrome_driver_exe() {
		return this.chrome_driver_exe;
	}

	public void setChrome_driver_exe(String chrome_driver_exe) {
		this.chrome_driver_exe = chrome_driver_exe;
	}

	public String getChrome_driver_da_exe() {
		return this.chrome_driver_da_exe;
	}

	public void setChrome_driver_da_exe(String chrome_driver_da_exe) {
		this.chrome_driver_da_exe = chrome_driver_da_exe;
	}

	public String getEdge_driver_exe() {
		return this.edge_driver_exe;
	}

	public void setEdge_driver_exe(String edge_driver_exe) {
		this.edge_driver_exe = edge_driver_exe;
	}

	public String getDesktop_app_path() {
		return this.desktop_app_path;
	}

	public void setDesktop_app_path(String desktop_app_path) {
		this.desktop_app_path = desktop_app_path;
	}

	public String getDriver_host() {
		return this.driver_host;
	}

	public void setDriver_host(String driver_host) {
		this.driver_host = driver_host;
	}

	public String getData_tag() {
		return this.data_tag;
	}

	public void setData_tag(String data_tag) {
		this.data_tag = data_tag;
	}

	public String getStream_label() {
		return this.stream_label;
	}

	public void setStream_label(String stream_label) {
		this.stream_label = stream_label;
	}

	public String getRelease_label() {
		return this.release_label;
	}

	public void setRelease_label(String release_label) {
		this.release_label = release_label;
	}

	public int getAppium_cmd_timeout() {
		return this.appium_cmd_timeout;
	}

	public void setAppium_cmd_timeout(int appium_cmd_timeout) {
		this.appium_cmd_timeout = appium_cmd_timeout;
	}

	public int getGrid_hub_port() {
		return this.grid_hub_port;
	}

	public void setGrid_hub_port(int grid_hub_port) {
		this.grid_hub_port = grid_hub_port;
	}

	public int getPageload_timeout() {
		return this.pageload_timeout;
	}

	public void setPageload_timeout(int pageload_timeout) {
		this.pageload_timeout = pageload_timeout;
	}

	public int getImplicit_wait() {
		return this.implicit_wait;
	}

	public void setImplicit_wait(int implicit_wait) {
		this.implicit_wait = implicit_wait;
	}

	public int getDbthreads() {
		return this.dbthreads;
	}

	public void setDbthreads(int dbthreads) {
		this.dbthreads = dbthreads;
	}

	public int[] getAppium_ports() {
		return this.appium_ports;
	}

	public void setAppium_ports(int[] appium_ports) {
		this.appium_ports = appium_ports;
	}

	public String[] getHeader_key() {
		return this.header_key;
	}

	public void setHeader_key(String[] header_key) {
		this.header_key = header_key;
	}

	public String[] getHeader_value() {
		return this.header_value;
	}

	public void setHeader_value(String[] header_value) {
		this.header_value = header_value;
	}

	public boolean isFront_end() {
		return this.front_end;
	}

	public boolean getFront_end() {
		return this.front_end;
	}

	public void setFront_end(boolean front_end) {
		this.front_end = front_end;
	}

	public boolean isBack_end() {
		return this.back_end;
	}

	public boolean getBack_end() {
		return this.back_end;
	}

	public void setBack_end(boolean back_end) {
		this.back_end = back_end;
	}

	public boolean isLambdatest_mode() {
		return this.lambdatest_mode;
	}

	public boolean getLambdatest_mode() {
		return this.lambdatest_mode;
	}

	public void setLambdatest_mode(boolean lambdatest_mode) {
		this.lambdatest_mode = lambdatest_mode;
	}

	public boolean isLt_network() {
		return this.lt_network;
	}

	public boolean getLt_network() {
		return this.lt_network;
	}

	public void setLt_network(boolean lt_network) {
		this.lt_network = lt_network;
	}

	public boolean isLt_image() {
		return this.lt_image;
	}

	public boolean getLt_image() {
		return this.lt_image;
	}

	public void setLt_image(boolean lt_image) {
		this.lt_image = lt_image;
	}

	public boolean isLt_video() {
		return this.lt_video;
	}

	public boolean getLt_video() {
		return this.lt_video;
	}

	public void setLt_video(boolean lt_video) {
		this.lt_video = lt_video;
	}

	public boolean isLt_console() {
		return this.lt_console;
	}

	public boolean getLt_console() {
		return this.lt_console;
	}

	public void setLt_console(boolean lt_console) {
		this.lt_console = lt_console;
	}

	public boolean isAppium_mode() {
		return this.appium_mode;
	}

	public boolean getAppium_mode() {
		return this.appium_mode;
	}

	public void setAppium_mode(boolean appium_mode) {
		this.appium_mode = appium_mode;
	}

	public boolean isGrid_mode() {
		return this.grid_mode;
	}

	public boolean getGrid_mode() {
		return this.grid_mode;
	}

	public void setGrid_mode(boolean grid_mode) {
		this.grid_mode = grid_mode;
	}

	public boolean isRelaunch_browser() {
		return this.relaunch_browser;
	}

	public boolean getRelaunch_browser() {
		return this.relaunch_browser;
	}

	public void setRelaunch_browser(boolean relaunch_browser) {
		this.relaunch_browser = relaunch_browser;
	}

	public boolean isProxy_mode() {
		return this.proxy_mode;
	}

	public boolean getProxy_mode() {
		return this.proxy_mode;
	}

	public void setProxy_mode(boolean proxy_mode) {
		this.proxy_mode = proxy_mode;
	}

	public boolean getIncognito() {
		return this.incognito;
	}

	public void setIncognito(boolean incognito) {
		this.incognito = incognito;
	}

	public boolean isDriver_recycle() {
		return this.driver_recycle;
	}

	public boolean getDriver_recycle() {
		return this.driver_recycle;
	}

	public void setDriver_recycle(boolean driver_recycle) {
		this.driver_recycle = driver_recycle;
	}

	public boolean isRun_recycle() {
		return this.run_recycle;
	}

	public boolean getRun_recycle() {
		return this.run_recycle;
	}

	public void setRun_recycle(boolean run_recycle) {
		this.run_recycle = run_recycle;
	}

	public boolean getRun_official() {
		return this.run_official;
	}


	public boolean isScreenshotOnPass() {
		return this.screenshotOnPass;
	}

	public boolean getScreenshotOnPass() {
		return this.screenshotOnPass;
	}

	public void setScreenshotOnPass(boolean screenshotOnPass) {
		this.screenshotOnPass = screenshotOnPass;
	}

	public void setU(String u) {
		this.u = u;
	}
	
	public void setP(String p) {
		this.p = p;
	}
	
	public String getDBU() {
		return u; 
	}
	
	public String getDBP() {
		return p; 
	}

	public void setDBThreads(int threads) {
		this.dbthreads = threads;
	}
	
	public int getDBThreads() {
		return this.dbthreads;
	}
	
	public int getPoolTimeout() {
		return 180;
	}
	
	public int getConnectionTimeout() {
		return 2;
	}
	
	public String getDBUrl() {
		String dbu = !(TestDriver.config.getEnvironment().equalsIgnoreCase("PD") || 
				TestDriver.config.getEnvironment().equalsIgnoreCase("CN")) ?
				GenericFileActions.getEnvProperty("enp") :
				GenericFileActions.getEnvProperty("epd");
		
		return "jdbc:mariadb://" + dbu + "?verifyServerCertificate=true&ssLMode=true&requireSSL=true";
	}
	
	public String getDBDriver() {
		return "org.mariadb.jdbc.Driver";
	}
	
	public String getPoolName() {
		return this.db_pool_name;
	}
	
	public int getPageLoadTimeout() {
		return this.pageload_timeout;
	}
	
	public String getRunTags() {
		return this.run_tags;
	}
	
	public String getRunLabel() {
		return this.run_label;
	}
	
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	public String getEnvironment() {
		return this.environment;
	}
	
	public String getPlatform() {
		return this.platform;
	}
	
	public String getStartURL() {
		return this.start_url;
	}
	
	public String getDriverPath() {
		List<String> lparts = Arrays.asList(this.driver_path.split("/"));
		
		if (lparts.get(lparts.size() - 1).isEmpty()) {
			lparts.remove(lparts.size() - 1);
		}
		
		return String.join("/", lparts.toArray(new String[0])) + "/";
	}
	
	public int getImplicitWait() {
		return this.implicit_wait;
	}
	
//===========APPIUM CONFIG===========//	
	public boolean isAppium() {
		return this.appium_mode;
	}
	
	public String getAppiumIP() {
		return this.appium_ip;
	}
	
	public int[] getAppiumPorts() {
		return this.appium_ports;
	}
	
	public int getAppiumCmdTimeout() {
		return this.appium_cmd_timeout;
	}
//===================================//

//=============GRID CONFIG===========//	
	public boolean isGridMode() {
		return this.grid_mode;
	}
	
	public String getGridHubIP() {
		return this.grid_hub_ip;
	}
	
	public int getGridHubPort() {
		return this.grid_hub_port;
	}
	
	public String getGridHubURL() {
		return "http://" + this.getGridHubIP() + ":" + this.getGridHubPort() + "/wd/hub";
	}
//===================================//
	
	public boolean getFrontEnd() {
		return this.front_end;
	}
	
	public boolean getBackEnd() {
		return this.back_end;
	}
	
	public boolean isLambdaTestMode() {
		return this.lambdatest_mode;
	}
	
	public String getLambdaTestPlatform() {
		return this.lt_platform;
	}
	
	public String getLambdaTestBrowser() {
		return this.lt_browser;
	}
	
	public String getLambdaTestBrowserVersion() {
		return this.lt_browser_ver;
	}
	
	public String getLambdaTestDevice() {
		return this.lt_device;
	}
	
	public boolean isLambdaTestNetwork() {
		return this.lt_network;
	}
	
	public boolean isLambdaTestImage() {
		return this.lt_image;
	}
	
	public boolean isLambdaTestVideo() {
		return this.lt_video;
	}
	
	public boolean isLambdaTestConsole() {
		return this.lt_console;
	}
//===================================//


//===========PROXY CONFIG============//
	public boolean isProxyMode() {
		return this.proxy_mode;
	}
	
	public String[] getHeaderKey() {
		return this.header_key;
	}
	
	public String[] getHeaderValue() {
		return this.header_value;
	}
//===================================//
	
	
	//============ Extent Report Config ============ //
	
	
	public boolean isRun_official() {
		return run_official;
	}

	public void setRun_official(boolean run_official) {
		this.run_official = run_official;
	}


	//============================================== //

	public String getChromeDownloadPath() {
		List<String> lparts = Arrays.asList(this.chrome_download_path.split("/"));
		
		if (lparts.get(lparts.size() - 1).isEmpty()) {
			lparts.remove(lparts.size() - 1);
		}
		
		String path = String.join("/", lparts.toArray(new String[0]));
		new File(path).mkdirs();
		
		return path;
	}
	
	public String getFFDownloadPath() {
		List<String> lparts = Arrays.asList(this.ff_download_path.split("/"));
		
		if (lparts.get(lparts.size() - 1).isEmpty()) {
			lparts.remove(lparts.size() - 1);
		}
		
		String path = String.join("/", lparts.toArray(new String[0]));
		new File(path).mkdirs();
		
		return path;
	}
	
	public String getDownloadMimes() {
		return "text/csv,text/plain,text/html,application/plain,application/octet-stream,"
				+ "application/msword,application/vnd.ms-excel,"
				+ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,"
				+ "application/pdf,video/mp4,application/mp4,audio/mpeg,application/zip";
	}
	
	public boolean isRelaunchEnabled() {
		return this.relaunch_browser;
	}
	
	public boolean isIncognito() {
		return this.incognito;
	}
	
	public String getIEDriver() {
		return this.ie_driver_exe;
	}
	
	public String getIE32Driver() {
		return this.ie32_driver_exe;
	}
	
	public String getFFDriver() {
		return this.ff_driver_exe;
	}
	
	public String getChromeDriver() {
		return this.chrome_driver_exe;
	}
		
	public String getChromeDriver_da() {
		return this.chrome_driver_da_exe;
	}
	
	public String getEdgeDriver() {
		return this.edge_driver_exe;
	}
	
	public String getDesktopAppPath() {
		return this.desktop_app_path;
	}
	
	public boolean isRecycleMode() {
		return this.driver_recycle;
	}
	
	public boolean isRecycleModeReports() {
		return this.run_recycle;
	}
	
	public String getDriverHost() {
		return this.driver_host;
	}
	
	public String getDataTag() {
		return this.data_tag;
	}
	
	public String getStreamLabel() {
		return this.stream_label;
	}
	
	public String getReleaseLabel() {
		return this.release_label;
	}
}
