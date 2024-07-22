package qatarairways.loginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qatarairways.reusableUtilities.reusableComponents;

public class loginPageObject extends reusableComponents{
	
	WebDriver driver;
	
	public loginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="input[id='userEmail']")
	WebElement userEmail;
	
	@FindBy(css="input[id='userPassword']")
	WebElement password;
	
	@FindBy(css="input[id='login']")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public productcatelogPageObject loginApplication(String email, String pass) {
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		productcatelogPageObject productCatelog = new productcatelogPageObject(driver);
		return productCatelog;
	}
	
	public void baseUrl() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String grabErrorMessage() {
		waitForElementToVisibleBy(errorMessage);
		return errorMessage.getText();
	}
	
}
