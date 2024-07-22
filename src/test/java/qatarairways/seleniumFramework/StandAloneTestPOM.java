package qatarairways.seleniumFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import qatarairways.TestComponents.BaseTest;
import qatarairways.loginPage.loginPageObject;
import qatarairways.loginPage.orderConfirmedPage;
import qatarairways.loginPage.productOrderPage;
import qatarairways.loginPage.productcartPage;
import qatarairways.loginPage.productcatelogPageObject;
import qatarairways.loginPage.productconfirmPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StandAloneTestPOM extends BaseTest{
	//String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData",groups= {"PurchaseOrder"})
	public void submitsOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		productcatelogPageObject productCatelog = page.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = productCatelog.getProductList();
		productCatelog.addProductToCart(input.get("productName"));
		
		productcartPage cartPage = productCatelog.productCard();
        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		productconfirmPage confirmPage = cartPage.checkOut();
		confirmPage.countryConfirm();
		orderConfirmedPage confirmedPage = confirmPage.submitOrder();
		String message= confirmedPage.orderconfirmMessage();

		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	//To verify if product is visible in the orders page
	@Test(dependsOnMethods= {"submitsOrder"},dataProvider="getData")
	public void orderHistoryTest(HashMap<String,String> input) {
		productcatelogPageObject productCatelog = page.loginApplication(input.get("email"),input.get("password"));
		productOrderPage orderPage = productCatelog.gotoproductOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("productName")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//HashMap<String, String> map = new HashMap<String, String>();
		//map.put("email", "learnselenium123@gmail.com");
		//map.put("password", "GoodPassword!1");
		//map.put("productName", "ZARA COAT 3");
		
		//HashMap<String, String> map1 = new HashMap<String, String>();
		//map1.put("email", "learnselenium123@gmail.com");
		//map1.put("password", "GoodPassword!1");
		//map1.put("productName", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\qatarairways\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
