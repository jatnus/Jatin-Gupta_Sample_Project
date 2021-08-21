package Weather_Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

//import Weather_Utilities.WebEventListener;

public class Weather_BaseClass {

	public static WebDriver driver;
	public static Properties prop;
//	public static EventFiringWebDriver e_driver ;
//	public static WebEventListener eventListener ;

	public Weather_BaseClass() {
		try {
			prop = new Properties();

			FileInputStream fs = new FileInputStream(
					"C:\\Work\\Weather_Collection\\src\\main\\java\\Config_Properties\\Config.properties");

			prop.load(fs);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void Initialization() throws InterruptedException {

		if (prop.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Work\\ChromeDriver\\chromedriver.exe");
			ChromeOptions op = new ChromeOptions();
//		      disable notification parameter
			op.addArguments("--disable-notifications");
//		       configure options parameter to Chrome driver
//		    
			driver = new ChromeDriver(op);
		} else if (prop.getProperty("Browser").equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Work\\Firefoxdriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

//		e_driver=new EventFiringWebDriver(driver);
//		eventListener = new WebEventListener();
//		e_driver.register(eventListener);
//		driver = e_driver;
//		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("PageLoadTimeout")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("ImplicitWait")), TimeUnit.SECONDS);

		driver.get(prop.getProperty("URL"));
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("PageLoadTimeout")),
				TimeUnit.SECONDS);

		Thread.sleep(3000);

	}

}
