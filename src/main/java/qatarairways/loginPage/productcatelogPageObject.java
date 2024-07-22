package qatarairways.loginPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qatarairways.reusableUtilities.reusableComponents;

public class productcatelogPageObject extends reusableComponents{
	
	WebDriver driver;
	
	public productcatelogPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productLoad = By.cssSelector(".mb-3");
	By addToCart = By.xpath("(//div/button[@class='btn w-10 rounded'])");
	By addsToCart = By.cssSelector(".card-body button:last-of-type");//(//div/button[@class='btn w-10 rounded'])[2]
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() 
	{
		waitForElementToVisible(productLoad);
		return products;
		
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod =getProductList().stream().filter(s->s.findElement(By.cssSelector("b")).
				 getText().equals(productName)).findFirst().orElse(null);
		return prod;		
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addsToCart).click();
		waitForElementToVisible(toastMessage);
		waitForElementToInvisible(spinner);
		
	}
	
	

}
