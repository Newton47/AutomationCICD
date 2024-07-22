package qatarairways.seleniumFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import qatarairways.loginPage.loginPageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		loginPageObject page = new loginPageObject(driver);
		driver.findElement(By.cssSelector("input[id='userEmail']")).sendKeys("learnselenium123@gmail.com");
		driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys("GoodPassword!1");
		driver.findElement(By.cssSelector("input[id='login']")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod =products.stream().filter(s->s.findElement(By.cssSelector("b")).
						 getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		
		prod.findElement(By.xpath("(//div/button[@class='btn w-10 rounded'])[2]")).click();
		
		WebDriverWait waits = new WebDriverWait(driver,Duration.ofSeconds(5));
		waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div/ul/li/div/div/h3"));
		Boolean match = cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//ul/li/button[@class='btn btn-primary']")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		
		//Select drpDwn = new Select(driver.findElement(By.cssSelector("//section[@class='ta-results list-group ng-star-inserted']")));
		//drpDwn.selectByValue("India");
		
		
		driver.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button[2]/span/i[@class='fa fa-search']")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"))).click().build().perform();
		//driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		//driver.findElement(By.cssSelector(".action__submit")).click();
		String userText = driver.findElement(By.xpath("//tr/td/h1[@class='hero-primary']")).getText();
		Assert.assertTrue(userText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

}
