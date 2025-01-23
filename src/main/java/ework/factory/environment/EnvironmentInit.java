package ework.factory.environment;

import java.util.Map;
import java.util.function.BiFunction;

public class EnvironmentInit {
	
	
	private static final BiFunction<String, String, Environment> CCP = (x, y) -> new EnvironmentEC1(x, y);
	private static final BiFunction<String, String, Environment> EC1 = (x, y) -> new EnvironmentEC1(x, y);
	private static Map<String, BiFunction<String, String, Environment>> MAP = Map.of(
			"CCP", CCP,
			"EC1", EC1
			);
	
	public static Environment get(String platform, String url, String env) throws Exception {
		if(MAP.containsKey(platform)) {
			return MAP.get(platform).apply(url, env);
		} else {
			throw new Exception("Platform value is not recognized...");
		}
		
		
	}
}
