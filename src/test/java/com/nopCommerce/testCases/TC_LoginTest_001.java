package com.nopCommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopCommerce.pageObject.LoginPage;
import com.nopCommerce.testBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass{

	LoginPage lp;

	@Test
	public void loginTest() throws IOException {
		logger.info("***Entered into loginTest method****");
		driver.get(configProp.getProperty("baseURL"));
		lp = new LoginPage(driver);
		logger.info("***Entering Credentials****");
		lp.setUserName(configProp.getProperty("useremail"));
		lp.setPassword(configProp.getProperty("password"));
		lp.clickLogin();
		captureScreenshot(driver, "loginTest");

		String exp_title="Dashboard / nopCommerce administration123";
		String act_title=driver.getTitle();

		if(exp_title.equals(act_title))
		{
			logger.info("***Login Successfull****");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("****Failed Login*****");
			Assert.assertTrue(false);

		}

	}



}
