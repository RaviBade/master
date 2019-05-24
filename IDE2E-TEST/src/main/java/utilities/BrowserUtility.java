package utilities;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserUtility {

	public static WebDriver driver;
	public static Properties prop;

	
	public static WebDriver getBrowser(String browserName, String url) throws Exception {
		
		PropertiesUtility propUtility = new PropertiesUtility();
		prop = propUtility.getProperty();

		if(browserName.equals("Chrome")) {
			
//			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ravi\\WebDrivers\\Chrome74\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", prop.getProperty("webDriverPath"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			
			driver = new ChromeDriver(options);
//			driver.manage().window().maximize();
			driver.get(url);
			Thread.sleep(1000);
			return driver;
		}else if(browserName.equals("IE")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ravi\\WebDrivers\\MicrosoftWebDriver.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(url);
			Thread.sleep(1000);
			return driver;
		}else if(browserName.equals("Firefox")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ravi\\WebDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
			Thread.sleep(1000);
			return driver;
		}else {
			
			return null;
		}
		
	}
	
}
