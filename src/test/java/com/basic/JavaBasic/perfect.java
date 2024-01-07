package com.basic.JavaBasic;
import org.testng.annotations.Test; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class perfect {
	WebDriver driver=null;
	@BeforeSuite
	public void start() {
		String browser=System.getProperty("browser","chrome");
		if(browser.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(browser.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
	}
	String url="https://lovepik.com/image-401217927/digital-collection-of-artistic-words.html";
		@Test
	public void implementation() throws Exception{
		driver.manage().window().maximize();
		driver.get(url);
		 String path= System.getProperty("user.dir")+"\\ss\\captcha.png";
		 WebElement imageElement = driver.findElement(By.xpath("//img[@id='photo']"));
	        // Get the image source URL
		 String imageUrl = imageElement.getAttribute("src");
		 try {
	            URL url = new URL(imageUrl);
	            Path destination = Path.of(path);
	            Files.copy(url.openStream(), destination, StandardCopyOption.REPLACE_EXISTING);
	            System.out.println("Image downloaded successfully to: " + path);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			Tesseract tesseract = new Tesseract(); 
			        try {
			            tesseract.setDatapath("D:\\Tess4J-3.4.8-src\\Tess4J\\tessdata"); 
			            // the path of your tess data folder 
			            // inside the extracted file 
			            String text = tesseract.doOCR(new File(path)); 
			            // path of your image file 
			            System.out.print("text: "+text); 
					Thread.sleep(2000);
			     }  catch (TesseractException e) { 
			            e.printStackTrace(); 
			        } 
	}

	@AfterSuite
	public void close() {
		driver.quit();
	}
}
