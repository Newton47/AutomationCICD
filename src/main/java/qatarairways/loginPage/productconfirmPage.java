package qatarairways.loginPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qatarairways.reusableUtilities.reusableComponents;

public class productconfirmPage extends reusableComponents{
	
	WebDriver driver;	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(xpath="//section[@class='ta-results list-group ng-star-inserted']/button[2]/span/i[@class='fa fa-search']")
	WebElement confirmCountry;
	
	@FindBy(css=".action__submit")
	WebElement submitOrder;
	

	public productconfirmPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void countryConfirm() {
		selectCountry.sendKeys("Ind");	
		confirmCountry.click();
	}
	
	public orderConfirmedPage submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		waitForElementToBeClickable(submitOrder);
		submitOrder.click();
		return new orderConfirmedPage(driver);
	}

	
	

}
