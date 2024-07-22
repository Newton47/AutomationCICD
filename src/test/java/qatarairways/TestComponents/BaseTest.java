package qatarairways.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import qatarairways.loginPage.loginPageObject;

public class BaseTest {
	
	public WebDriver driver;
	public loginPageObject page;
	public WebDriver initializeDriver() throws IOException {
		
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\qatarairways\\resources\\GlobalData.properties");
		props.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): props.getProperty("browser");
		//String browserName = props.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
		ChromeOptions options = new ChromeOptions();	
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) {
		options.addArguments("headless");
		}	
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));

		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//firefox code
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//firefox code
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public loginPageObject launchApplication() throws IOException {
		driver = initializeDriver();
		page = new loginPageObject(driver);
		page.baseUrl();
		return page;		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
	
	public List<HashMap<String, String>> getJsonDatatoMap(String filePath) throws IOException {
		
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
	   //string to HashMap using jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
		}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot shot = (TakesScreenshot)driver;
		File destfile = new File(System.getProperty("user.dir")+"\\reports\\"+ testCaseName + ".png");
		File srcfile = shot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile,destfile);
		return System.getProperty("user.dir")+"\\reports\\"+ testCaseName + ".png";
	}

}
