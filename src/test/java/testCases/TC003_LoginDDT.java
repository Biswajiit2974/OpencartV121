package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven")
	public void verifyLogin(String email,String password,String result) {
		logger.info("Starting my second testcase");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		MyAccountPage mp=new MyAccountPage(driver);
		boolean status=mp.isMyAccountPageExists();
		//Assert.assertEquals(status,true,"Login Failed");
		// 1st 2 is compair if it is fail 3rd parameter msg is displyed
		
		
		
		if(result.equalsIgnoreCase("Valid")) {
			if(status==true) {
				Assert.assertTrue(true);
				mp.clickLogout();
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(result.equalsIgnoreCase("invalid")) {
		if(status==true) {
			mp.clickLogout();
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("Finish testcase");
	}	
}
