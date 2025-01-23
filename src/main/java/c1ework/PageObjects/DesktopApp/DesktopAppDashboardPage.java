package c1ework.PageObjects.DesktopApp;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class DesktopAppDashboardPage {

    public static By getDashboardPage_cntr =  By.cssSelector("div.dashboard-container");
	public static WebElement getDashboardPage_cntr(SearchContext driver) throws NoSuchElementException {
		return driver.findElement(getDashboardPage_cntr);
	}
}