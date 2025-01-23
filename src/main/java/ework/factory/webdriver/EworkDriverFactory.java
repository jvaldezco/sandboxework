package ework.factory.webdriver;

import java.util.Map;
import java.util.function.Supplier;

public class EworkDriverFactory {
	
	 private static Supplier<EworkDriver> WINDOWSDA = () -> {
		return new WebDriverWindowsDesktopApp();
	 };
	 
	 private static Map<String, Supplier<EworkDriver>> MAP;
  
	 public EworkDriverFactory() {
	 }
  
	 public static EworkDriver get(String browser, boolean lambdatestmode) throws Exception {
		if (lambdatestmode) {
		   return (EworkDriver)((Supplier)MAP.get("LAMBDATEST")).get();
		} else if (MAP.containsKey(browser)) {
		   return (EworkDriver)((Supplier)MAP.get(browser)).get();
		} else {
		   throw new Exception("Browser value is not recognized...");
		}
	 }
  
	 static {
		MAP = Map.of("WINDOWSDA", WINDOWSDA);
	 }
}
