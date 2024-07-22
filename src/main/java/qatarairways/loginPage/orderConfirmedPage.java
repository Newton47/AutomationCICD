package qatarairways.loginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qatarairways.reusableUtilities.reusableComponents;

public class orderConfirmedPage extends reusableComponents{

	@FindBy(xpath="//tr/td/h1[@class='hero-primary']")
	WebElement orderConfirmed;
	
	WebDriver driver;
	public orderConfirmedPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String orderconfirmMessage() {
		return orderConfirmed.getText();
		
	}

}
