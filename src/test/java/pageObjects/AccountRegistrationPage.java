package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[contains(@id,'input-firstname')]")
	WebElement txt_Firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_Lastname;
	
	@FindBy(xpath="//input[contains(@id,'email')]")
	WebElement txt_Email;
	
	@FindBy(xpath="//input[contains(@id,'input-telephone')]")
	WebElement txt_Telephone;
	
	@FindBy(xpath="//input[contains(@id,'input-password')]")
	WebElement txt_password;
	
	@FindBy(xpath="//input[contains(@id,'input-confirm')]")
	WebElement txt_ConfirmPassword;
	
	@FindBy(xpath="//input[contains(@type,'checkbox')]")
	WebElement chkPolicy;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		txt_Firstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txt_Lastname.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txt_Email.sendKeys(email);
	}
	
	public void setTelePhone(String tel) {
		txt_Telephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd) {
		txt_password.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd) {
		txt_ConfirmPassword.sendKeys(pwd);
	}
	
	public void clickPolicy() {
		chkPolicy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();//suppose it is not click
		
	}
	
	public String getConfirmMsg() {
		try {
			return(msgConfirmation.getText());
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
}
