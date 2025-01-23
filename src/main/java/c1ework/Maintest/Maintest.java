package c1ework.Maintest;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;


public class Maintest {
    public static void main(String[] args) {
    	TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        Scanner scanner = new Scanner(System.in);
		
        suite.setSuiteFiles(Arrays.asList("xml/suites/desktop_app/C1POC_DesktopApp_Login.xml"));
        testNG.setXmlSuites(Arrays.asList(suite));
        testNG.run();
        scanner.close();
    }

    private static String fileChecker (Scanner scanner, String directoryPath) {
	 File directory = new File(directoryPath);
	 if (directory.exists() && directory.isDirectory()) {
		File[] files = directory.listFiles();
	     if (files != null && files.length > 0) {
		 System.out.println("Files in directory: " + directoryPath); 
		 String[] fileNames = new String[files.length];
		 for (int i = 0; i < files.length; i++) {
		     fileNames[i] = files[i].getName();
		     System.out.println((i + 1) + ". " + fileNames[i]);
		 }
		 System.out.print("Enter the index of the file you want to select: ");
		 int selectedIndex = scanner.nextInt();
		 if (selectedIndex >= 1 && selectedIndex <= files.length) { 
		     System.out.println("Selected file: " + fileNames[selectedIndex - 1]);
		     return fileNames[selectedIndex - 1];
		 } else {
		     System.out.println("Invalid selection. Please enter a valid index.");
		     return "none";
		 }
	     } else {
		 System.out.println("No files found in directory: " + directoryPath);
	     }
	 } else {
	     System.out.println("Directory does not exist: " + directoryPath);
	 }
	return directoryPath;
    }
}
