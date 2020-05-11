package com.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	
	public static String getScreenShot(WebDriver driver, String fileName) throws IOException {
//		File src;
//		src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(src,new File(fileName));
		File dir;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		DateFormat currentDay = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateTime = dateFormat.format(date.getTime());
		dir = new File("./ScreenShots");
		if(!dir.exists()) {
			dir.mkdir();
		}
		dir = new File("./ScreenShots/"+currentDay.format(date));
		if(!dir.exists()) {
			dir.mkdir();
		}
		String destination = currentDir+"//ScreenShots//"+currentDay.format(date)+"//"+dateTime+"_"+fileName+".png";
		try {
			com.google.common.io.Files.copy(source, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("Screen shot taken");
		return destination;
	}	
	
	
}
