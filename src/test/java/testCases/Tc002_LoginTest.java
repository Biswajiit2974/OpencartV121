package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import jdk.internal.org.jline.utils.Log;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class Tc002_LoginTest extends BaseClass{

	@Test(groups={"sanity","master"})
	public void verifyLogin() {
		logger.info("Starting my second testcase");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage mp=new MyAccountPage(driver);
		boolean status=mp.isMyAccountPageExists();
		//Assert.assertEquals(status,true,"Login Failed");
		// 1st 2 is compair if it is fail 3rd parameter msg is displyed
		Assert.assertTrue(status);
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("Finish testcase");
	}
	
	
}
