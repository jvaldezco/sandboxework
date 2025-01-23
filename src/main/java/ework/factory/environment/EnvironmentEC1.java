package ework.factory.environment;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentEC1 extends Environment {
	private String url;
	private String env;
	
	public EnvironmentEC1(String url, String env) {
		this.url = url;
		this.env = env;
	}

	@Override
	public String initURL() throws Exception {
		System.out.println("Initializing URL...");
		
		Map<String, Runnable> urlHandler = new HashMap<>();
		urlHandler.put("AP", () -> {
			this.url = this.url.replace("qa", "c1-alpha");
		});
		
		urlHandler.put("HF", () -> {
			this.url = this.url.replace("qa", "hotfix");
		});
		
		urlHandler.put("RL", () -> {
			this.url = this.url.replace("qa", "release");
		});
		
		urlHandler.put("PD", () -> {
			this.url = this.url.replace("qa", "www");
		});
		
		if(urlHandler.containsKey(this.env)) {
			urlHandler.get(this.env).run();
		} else {
			throw new Exception("Environment value is not recognized...");
		}
		
		return this.url;
	}
}
