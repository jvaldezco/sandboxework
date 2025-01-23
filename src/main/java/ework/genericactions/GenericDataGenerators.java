package ework.genericactions;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class GenericDataGenerators {
	public static String generateWords(int range1, int range2){
        Lorem lorem = LoremIpsum.getInstance();
        return lorem.getWords(range1,range2);
    }
	
	public static String generateCharacter(int range){
		 byte[] array = new byte[256]; 
	     new Random().nextBytes(array); 
	     
	     String randomString = new String(array, Charset.forName("UTF-8")); 
	     StringBuffer stringBuff = new StringBuffer();
	     for (int k = 0; k < randomString.length(); k++) { 
	    	  
	            char ch = randomString.charAt(k); 
	  
	            if (((ch >= 'a' && ch <= 'z') 
	                 || (ch >= 'A' && ch <= 'Z') 
	                 || (ch >= '0' && ch <= '9')) 
	                && (range > 0)) { 
	  
	            	stringBuff.append(ch); 
	                range--; 
	            } 
	        }
	     return stringBuff.toString(); 
	}
	
	public static int generateRandomInt(int range) {
		return new Random().nextInt(range); 	
	}
	
	public static String generateParagraph(){
	        Lorem lorem = LoremIpsum.getInstance();
	        return  lorem.getParagraphs(1,1);
	}
	
	public static String generateTitle(){
	        Lorem lorem = LoremIpsum.getInstance();
	        return lorem.getTitle(1).toUpperCase();
	}
	
	public static String generateRandomEmail(){
	    Lorem lorem = LoremIpsum.getInstance();
	    return lorem.getEmail();
	}
	
	public static String generateRandomName(){
	    Lorem lorem = LoremIpsum.getInstance();
	    return lorem.getFirstName();
	}
	
	public static String generateRandomLastName() {
		return LoremIpsum.getInstance().getLastName();
	}
	
	public static String generateRandomAddress() {
		return LoremIpsum.getInstance().getStateAbbr() 
				+ LoremIpsum.getInstance().getCity()
				+ LoremIpsum.getInstance().getWords(1);
	}
	
	public static String generateRandomCity() {
		return LoremIpsum.getInstance().getCity();
	}
	
	public static String generateRandomCountry() {
		return LoremIpsum.getInstance().getCountry();
	}
	
	public static String generateRandomStateProvince() {
		return LoremIpsum.getInstance().getStateFull();
	}
	
	public static String generateRandomPostalZip() {
		return LoremIpsum.getInstance().getZipCode();
	}
	
	public static String randomize(int max, int min) {
		Random rand = new Random();
		int x = rand.nextInt(max) + min;
		return Integer.toString(x);
	}
	
	public static Date parseStringToDate(String dateFormat, String date) throws ParseException {
		return new SimpleDateFormat(dateFormat).parse(date);
	}
	
	public static String formatDate(String dateFormat, Date date) throws ParseException {
		DateFormat newDateFormat = new SimpleDateFormat(dateFormat);
		return newDateFormat.format(date);
	}
	
	public static String replaceApostrophe(String text) {
		return text.replace("’", "'");
	}
	
	public static String getCurrentDate(String format) {
		return GenericDataGenerators.getCurrentDate(format, 0, 0, 0);
	}
	
	public static String getCurrentDate(String format, long addDays, long addMonths, long addYears) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(addDays != 0) {
			ldt = LocalDateTime.now().plusDays(addDays);
		} else if (addMonths != 0) {
			ldt = LocalDateTime.now().plusMonths(addMonths);
		} else if (addYears != 0) {
			ldt = LocalDateTime.now().plusYears(addYears);
		}
		
		return formatter.format(ldt);
	}
	
	public static String getOrdinalNumber(int num) {
		String ordNum = "";
		String[] ordNumArr = new String[] {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth", 
				"Eleventh", "Twelfth", "Thirteenth", "Fourteenth", "Fifteenth", "Sixteenth", "Seventeenth", "Eighteenth", "Nineteenth", "Twentieth", 
				"Twenty-first", "Twenty-second", "Twenty-third", "Twenty-fourth", "Twenty-fifth", "Twenty-sixth", "Twenty-seventh", "Twenty-eighth", "Twenty-ninth", "Thirtieth", 
				"Thirty-first", "Thirty-second", "Thirty-third", "Thirty-fourth", "Thirty-fifth", "Thirty-sixth", "Thirty-seventh", "Thirty-eighth", "Thirty-ninth", "Fourtieth", 
				"Fourty-first", "Fourty-second", "Fourty-third", "Fourty-fourth", "Fourty-fifth", "Fourty-sixth", "Fourty-seventh", "Fourty-eighth", "Fourty-ninth", "Fiftieth", };  
		
		if(num >= 1 && num < 51) {
			ordNum = ordNumArr[num - 1];
		}
		
		return ordNum;
	}
	
	public static boolean isDateSetInFuture (String dateFormat, String date) throws ParseException  {
		return isDateSetInFuture(parseStringToDate(dateFormat, date));
	}
	
	public static boolean isDateSetInFuture (Date date) {
		boolean flag = false;
		Date currentDate = new Date();  
		
		if (date.compareTo(currentDate) > 0) {
			flag = true;
		}
		
		return flag;
	}
	
	public static String convertRGBtoHex (WebElement elem, String cssValue){
		try {
			String element;
			element = elem.getCssValue(cssValue);
			String color[];
			element = element.replaceAll("[A-Za-z]",  "");
			color = element.replaceAll("[!\\()]",  "").split(",");
			String hexValue = String.format("#%02x%02x%02x", Integer.parseInt(color[0].trim()), Integer.parseInt(color[1].trim()), Integer.parseInt(color[2].trim()));
			return hexValue;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static String generateRandomString(int length) {

		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString;
	}
	
	public static String secureRandom(int max) throws Exception {
		SecureRandom rand = new SecureRandom();
		int x = rand.nextInt(max);
		return Integer.toString(x);
	}
}
