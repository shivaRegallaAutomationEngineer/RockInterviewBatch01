package com.hrms.utils;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.hrms.BaseClass.BaseClass;

public class CommonMethods extends BaseClass {
	
	public static void SendData(WebElement ele,String data) {
		ele.clear();
		ele.sendKeys(data);
	}
	public static void clickAction(WebElement ele) {
		ele.click();
	}
	
	public static String takeScreenShot(String filename) {
		
		TakesScreenshot ts=(TakesScreenshot)BaseClass.driver;
		  File srcFile= ts.getScreenshotAs(OutputType.FILE);
		
		  String destinationFile=Constants.SCREENSHOT_PATH +filename+getTimeStemp()+".png";
		  try {
			FileUtils.copyFile(srcFile, new File(destinationFile));
		} catch (IOException e) {
			System.out.println("screen shot cannot be taken");
			
		}
		
		return destinationFile;
		
	}
	public static String getTimeStemp() {
		Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		return sdf.format(date.getTime());
	}
}
