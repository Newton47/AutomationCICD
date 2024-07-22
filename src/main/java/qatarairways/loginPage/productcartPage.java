package qatarairways.loginPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qatarairways.reusableUtilities.reusableComponents;

public class productcartPage extends reusableComponents{
	
	WebDriver driver;
	@FindBy(xpath="//div/ul/li/div/div/h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//ul/li/button[@class='btn btn-primary']")
	WebElement checkOut;
	

	public productcartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public productconfirmPage checkOut() {
		checkOut.click();
		return new productconfirmPage(driver);
		
	}

	
	

}
