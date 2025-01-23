package ework.genericactions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

public class GenericPageObjects {
	public static WebElement getParentElement(SearchContext driver) throws Exception {
		return getParentElement(driver, 1);
	}
	
	public static WebElement getParentElement(SearchContext driver, int levels) throws Exception {
		WebElement elem = null;
		elem = driver.findElement(By.xpath(".."));
		
		levels--;
		for (int i = 0; i < levels; i++) {
			elem = elem.findElement(By.xpath(".."));
		}
		
		return elem;
	}
	
	public static WebElement getElemAbove(SearchContext driver, By by_from, By by_to) throws Exception {
		return getElemAbove(driver, null, by_from, by_to);
	}
	
	public static WebElement getElemAbove(SearchContext driver, WebElement elem_from, By by_to) throws Exception {
		return getElemAbove(driver, elem_from, null, by_to);
	}
	
	public static WebElement getElemAbove(SearchContext driver, WebElement elem_from, By by_from, By by_to) throws Exception {
		WebElement elem = null;
		
		if(elem_from == null) {
			elem = driver.findElement(RelativeLocator.with(by_to).above(by_from));
		} else if(by_from == null) {
			elem = driver.findElement(RelativeLocator.with(by_to).above(elem_from));
		}
		
		return elem;
	}
	
	public static Select getSelectElement(WebElement elem) {
		return new Select(elem);
	}
	
	public static WebElement getElemFromList(List<WebElement> elems, String param) {
		WebElement elem = null;
		
		for(WebElement elem1 : elems) {
			GenericJSActions.scrollIntoView(null, elem1);
			
			if(elem1.getText().trim().equals(param)) {
				elem = elem1;
				break;
			}
		}
		
		return elem;
	}
}
