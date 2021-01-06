package com.nopCommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	public Properties configProp;
	public Logger logger = LogManager.getLogger(this.getClass()) ;
	
	@BeforeClass
	@Parameters("browser")
	public void setUp(String br) throws IOException {
		
		configProp = new Properties();
		FileInputStream fis =new FileInputStream(".\\src\\test\\resources\\config.properties");
		configProp.load(fis);
		
		if(br.equalsIgnoreCase("chrome")) {
			logger.info("***Launched Chrome Browser****");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else if (br.equalsIgnoreCase("firefox")) {
			logger.info("***Launched Firefox Browser****");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}
		else if(br.equals("ie"))
		{	
			//WebDriverManager.iedriver().setup();//Not working
			logger.info("***Launched ie Browser****");
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Drivers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		else if(br.equals("edge"))
		{			
			logger.info("***Launched Edge Browser****");
			//WebDriverManager.edgedriver().setup(); //Not working
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\drivers\\msedgedriver.exe");
			logger.info("**passed next linein edge****");
			//driver = new EdgeDriver();
			WebDriverManager.edgedriver().setup();
			logger.info("**passed next linein edge1****");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		logger.info("****webBrowser is closed****");
	}
	
	public  void captureScreenshot(WebDriver driver,String sName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty(("user.dir")+"\\screenshots"+sName+".png"));
		FileUtils.copyFile(srcFile, destFile);
	}
	
	public String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	
	public int randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (Integer.parseInt(generatedString2));
	}

}
