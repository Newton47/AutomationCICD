package qatarairways.loginPage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qatarairways.reusableUtilities.reusableComponents;

public class productOrderPage extends reusableComponents{
	
	WebDriver driver;
	@FindBy(xpath="//tr/td[2]")
	private List<WebElement> orderProducts;
	
	@FindBy(xpath="//ul/li/button[@class='btn btn-primary']")
	WebElement checkOut;
	

	public productOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = orderProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public productconfirmPage checkOut() {
		checkOut.click();
		return new productconfirmPage(driver);
		
	}

	
	

}
