package com.generic.code;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.config.BaseConfig;
import com.page.object.model.PropertyPage;
import com.util.ExplicitWait;
import com.util.Highlighter;
import com.util.ScreenShot;

public class PropertySelectionPage extends BaseLogin{
	public static void selectProperty() throws Throwable {
		getLogin();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyPage propertypf = new PropertyPage(driver);	
		
		new ExplicitWait().getExplicitWait(driver, propertypf.getLocationName());		
		propertypf.getLocationName().sendKeys(BaseConfig.getConfig("SearchLocationName"));
	//	propertypf.getLocationName().sendKeys(Keys.ARROW_DOWN);		
	//	propertypf.getLocationName().sendKeys(Keys.ENTER);		
		Highlighter.getcolor(driver, propertypf.getLocationName());
		Highlighter.getcolor(driver, propertypf.getSearchSubmit(), "blue");
		ScreenShot.getScreenShot(driver, "Search City");		
		propertypf.getSearchSubmit().click();
		
		String [] price;
		List<Integer> intPrice = new ArrayList<>();
		for(int i=0; i<propertypf.getHomePrices().size();i++) {
			
			price = propertypf.getHomePrices().get(i).getText().split(" ");
			intPrice.add(Integer.parseInt(price[0].replace("£","").replace(",","").trim()));
		}
		System.out.println("House Prices: "+intPrice);
		Collections.sort(intPrice);
		System.out.println("House Prices Sorted Asc: "+intPrice);
		Collections.reverse(intPrice);
		System.out.println("House Prices Sorted Desc: "+intPrice);
		
		// select 5th property		
		propertypf.getHomePrices().get(4).click();		
//		new ExplicitWait().getExplicitWait(driver, propertypf.getAgentPhoneNum());
		
		//check logo and agent contact info
		if(propertypf.getPropertyLogo().isDisplayed()) {
			System.out.println("Logo is present");
		} else {
			System.out.println("Logo is not present");
		}
		
		System.out.println("Agent Name is: "+propertypf.getAgentName().getText());
		System.out.println("Agent Phone#: "+propertypf.getAgentPhoneNum().getText());
		ScreenShot.getScreenShot(driver, "Agent Info");
		
		//Sign out
		Actions signOut = new Actions(driver);
		signOut.moveToElement(propertypf.getMyZooplaBtn()).build().perform();
		Highlighter.getcolor(driver,propertypf.getMyZooplaBtn(),"red");
		signOut.moveToElement(propertypf.getSignOutBtn()).build().perform();
		Highlighter.getcolor(driver, propertypf.getSignOutBtn(), "yellow");
		ScreenShot.getScreenShot(driver, "Sign Out");
		propertypf.getSignOutBtn().click();
		
		driver.quit();
	}
}
