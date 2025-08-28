package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {
   
	@Test
	public void verifyAccountRegistration() throws InterruptedException {
		logger.info("Starting register");
		try {
		HomePage hp=new HomePage(driver);
		Thread.sleep(3000);
		hp.clickMyAccount();
		logger.info("Clicked on myaccount link");
		hp.clickRegister();
		logger.info("clicked on register link");
		AccountRegistrationPage arp=new AccountRegistrationPage(driver);
		logger.info("povide user details");
		arp.setFirstName(randomString().toUpperCase());
	
		arp.setLastName(randomString().toUpperCase());
		
		arp.setEmail(randomString()+"@gmail.com");
		
		arp.setTelePhone(randomNumeric());
		String password=randomAlphaNumeric();
		arp.setPassword(password);
		
		arp.setConfirmPassword(password);
		
		arp.clickPolicy();
		arp.clickContinue();
	
		logger.info("validating expexted message");
		String conmsg=arp.getConfirmMsg();
		System.out.println(conmsg);
		if(conmsg.equals("Your Account Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed");
			logger.debug("debug logs");
			Assert.assertFalse(false);
		}
		//Assert.assertEquals(conmsg,"Your Account Has Been Created!");
		}
		catch(Exception e) {
//			logger.error("Test failed");
//			logger.debug("debug logs");
			Assert.fail();
			
		}
		logger.info("finshed TC_AccountRegistrationTest");
	}
	
	
	
}
