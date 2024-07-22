package qatarairways.reusableUtilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qatarairways.loginPage.productOrderPage;
import qatarairways.loginPage.productcartPage;

public class reusableComponents {
	
	WebDriver driver;
	
	public reusableComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
	WebElement cartHeader;
	
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement ordersButton;

	public void waitForElementToVisible(By waitBy) {
	WebDriverWait waits = new WebDriverWait(driver,Duration.ofSeconds(5));
	waits.until(ExpectedConditions.visibilityOfElementLocated(waitBy ));
	}
	
	public void waitForElementToVisibleBy(WebElement findBy) {
	WebDriverWait waits = new WebDriverWait(driver,Duration.ofSeconds(5));
	waits.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	public void waitForElementToInvisible(WebElement ele) {
		WebDriverWait waits = new WebDriverWait(driver,Duration.ofSeconds(5));
		waits.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForElementToBeClickable(WebElement waitFor) {
		WebDriverWait waits = new WebDriverWait(driver,Duration.ofSeconds(10));
		waits.until(ExpectedConditions.elementToBeClickable(waitFor));
	}
	
	public productcartPage productCard() {
		cartHeader.click();
		productcartPage cartPage = new productcartPage(driver);
		return cartPage;
	}
	
	public productOrderPage gotoproductOrderPage() {
		ordersButton.click();
		productOrderPage orderPage = new productOrderPage(driver);
		return orderPage;
	}
}
