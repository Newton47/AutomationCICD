package qatarairways.cucumber;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qatarairways.TestComponents.BaseTest;
import qatarairways.loginPage.loginPageObject;
import qatarairways.loginPage.orderConfirmedPage;
import qatarairways.loginPage.productcartPage;
import qatarairways.loginPage.productcatelogPageObject;
import qatarairways.loginPage.productconfirmPage;

public class stepDefinitionImplementation extends BaseTest{
	
	public loginPageObject landingPage;
	public productcatelogPageObject productCatelog;
	public productcartPage cartPage;
	public productconfirmPage confirmPage;
	public orderConfirmedPage confirmedPage;
	
	
	@Given("I landed on Ecommerge Page")
	public void I_landed_on_Ecommerge_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^I logged in with username(.+) and password(.+)$")
	public void I_logged_in_with_username_and_password(String username,String password)
	{
		 productCatelog = landingPage.loginApplication(username,password);
	}
	
	@When("^I add product productName(.+) to Cart$")
	public void I_add_product_to_Cart(String productName)
	{
		List<WebElement> products = productCatelog.getProductList();
		productCatelog.addProductToCart(productName);
	}
	
	@And("^And Checkout productName(.+) and submit the order$")
	public void Checkout_productName_and_submit_the_order(String productName)
	{
		cartPage = productCatelog.productCard();
        Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		confirmPage = cartPage.checkOut();
		confirmPage.countryConfirm();
		confirmedPage = confirmPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String message= confirmedPage.orderconfirmMessage();
        Assert.assertTrue(message.equalsIgnoreCase(string));
	}

}
