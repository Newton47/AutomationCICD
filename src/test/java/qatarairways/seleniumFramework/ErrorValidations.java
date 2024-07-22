package qatarairways.seleniumFramework;

import io.github.bonigarcia.wdm.WebDriverManager;

import qatarairways.TestComponents.BaseTest;
import qatarairways.TestComponents.Retries;
import qatarairways.loginPage.loginPageObject;
import qatarairways.loginPage.orderConfirmedPage;
import qatarairways.loginPage.productcartPage;
import qatarairways.loginPage.productcatelogPageObject;
import qatarairways.loginPage.productconfirmPage;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retries.class)
	public void loginErrorValidation() throws IOException {
		page.loginApplication("learnselenium12@gmail.com", "GoodPassword!");
		Assert.assertEquals("Incorrect email orrr@ password.",page.grabErrorMessage());

	}
	@Test
	public void productErrorValidation() throws InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		productcatelogPageObject productCatelog = page.loginApplication("learnselenium123@gmail.com", "GoodPassword!1");
		List<WebElement> products = productCatelog.getProductList();
		productCatelog.addProductToCart(productName);
		
		productcartPage cartPage = productCatelog.productCard();
        Boolean match = cartPage.verifyProductDisplay("PADIDAS");
		Assert.assertTrue(match);
		
	}
}
