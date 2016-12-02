package com.Suite;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	
	   public static WebDriver driver;
	Properties OR = null;
	Properties CONFIG = null;
	
	
	public BaseTest() {
		if (OR == null) {
			try {
				// initialize OR
				OR = new Properties();
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\main\\java\\Config\\OR.properties");
				OR.load(fs);

				// initialize CONFIG to corresponding env
				CONFIG = new Properties();
				fs = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\main\\java\\Config\\Config.properties");
				CONFIG.load(fs);

				// System.out.println(OR.getProperty("loginusername"));
				// System.out.println(CONFIG.getProperty("loginURL"));

			} catch (Exception e) {
				System.out.println("Error on intializing properties files");
			}

		}
		
		
		// log("Opening browser "+browserType);
		String browserType = CONFIG.getProperty("browser");
		if (browserType.equals("Mozilla")) {
			this.driver = new FirefoxDriver();
		} else if (browserType.equals("Chrome") ) {
			File file = new File("C:\\IEDriver\\chromedriver.exe");
			// File file= new File("C:\\Users\\solomon.adekunle\\OneDrive for
			// Business\\workspace\\BetVernons\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("no-sandbox");
			this.driver = new ChromeDriver();
		}else if (browserType.equals("IE")) {
			// set the IE server exe path and initialize
		}
		
		// max
		driver.manage().window().maximize();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//driver.get(CONFIG.getProperty("siteName"));
	}
	
}
