package ework.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.google.gson.Gson;

public class ReportMerger {

	String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM.dd HH.mm.ss"));
	String opFolder;
	String despiredPath;
	File file = new File("Reports");
	ConfigFile config = initializeConfig();
	File jsonOPDirectory = new File("Reports/" + config.getReleaseLabel() + "/ExtentJson/");
	String mergedReportsFileName = formattedDateTime + " - " + config.getReleaseLabel() + " - MergedTest";
	JsonFormatter jsonPath = new JsonFormatter("Reports/ExtentJson/" + mergedReportsFileName + ".json");
	ExtentSparkReporter mergedSpark = new ExtentSparkReporter("Reports/Merged Test/" + mergedReportsFileName + ".html");
	ExtentReports extentMerged = new ExtentReports();

	public void mergeReleaseReports() {
		initializeConfig();
		if (jsonOPDirectory.exists()) {
			Arrays.stream(jsonOPDirectory.listFiles()).forEach(jsonFile -> {
				try {
					extentMerged.createDomainFromJsonArchive(jsonFile.getPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		extentMerged.attachReporter(mergedSpark);
		config();
		extentMerged.flush();
	}

	public void createFolder() {
		if (!file.exists()) {
			file.mkdir();
		}
		opFolder = file.getPath();
	}

	public void config() {
		mergedSpark.config().thumbnailForBase64(true);
		mergedSpark.config().setReportName(config.getReleaseLabel());
	}

	public ConfigFile initializeConfig() {

		System.out.println("Setting up Suite configurations...");

		File jsonfile = new File("conf.json");

		String jsontext = "";

		try {
			jsontext = new String(Files.readAllBytes(jsonfile.toPath()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		Gson gson = new Gson();
		return config = gson.fromJson(jsontext, ConfigFile.class);
	}

}
