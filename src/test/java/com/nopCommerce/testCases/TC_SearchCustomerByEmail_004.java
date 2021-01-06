package com.nopCommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopCommerce.pageObject.AddcustomerPage;
import com.nopCommerce.pageObject.LoginPage;
import com.nopCommerce.pageObject.SearchCustomerPage;
import com.nopCommerce.testBase.BaseClass;

public class TC_SearchCustomerByEmail_004 extends BaseClass {

	@Test
	public void searchCustomerbyEmail() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_SearchCustomerByEmail_004 *************");
		
		driver.get(configProp.getProperty("baseURL"));
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(configProp.getProperty("useremail"));
		lp.setPassword(configProp.getProperty("password"));
		lp.clickLogin();
		
		//Go to search page
		AddcustomerPage addcust=new AddcustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		
		//Email ID
		SearchCustomerPage searchcust=new SearchCustomerPage(driver);
		searchcust.setEmail("victoria_victoria@nopCommerce.com");
		searchcust.clickSearch();
		Thread.sleep(3000);
		
		boolean status=searchcust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		if(status==true)
		{
			logger.info("********* Search customer by email is passed *************");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("********* Search customer by email is failed*************");
			captureScreenshot(driver,"searchCustomerbyEmail");
			Assert.assertTrue(false);
		}
		logger.info("********* End of TC_SearchCustomerByEmail_004 *************");
	}
	
}
