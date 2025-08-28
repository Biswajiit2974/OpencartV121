package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
@FindBy(xpath="//span[text()='My Account']/parent::a")
WebElement lnMyaccount;

@FindBy(xpath="//a[contains(text(),'Register')]")
WebElement lnRegister;

@FindBy(xpath="//a[contains(.,'Login')]")
WebElement lnkLogin;

public void clickMyAccount() {
	lnMyaccount.click();
}

public void clickRegister() {
	lnRegister.click();
}

public void clickLogin() {
	lnkLogin.click();
}
}
