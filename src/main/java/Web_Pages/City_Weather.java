package Web_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Weather_Base.Weather_BaseClass;
import Weather_Utilities.TestVagrantException;

public class City_Weather extends Weather_BaseClass {

	@FindBy(xpath = "//input[@class='search-input']")
	WebElement SearchCity;

	@FindBy(xpath = "//span[@class='text' and text()='More Details']")
	WebElement MoreDetails;

	public City_Weather() {
		PageFactory.initElements(driver, this);
	}

	public String CityWeatherData(String City) {
		SearchCity.clear();
		SearchCity.click();
		
		SearchCity.sendKeys(City);
		SearchCity.sendKeys(Keys.ENTER);
		 
		String Temperature_UI=	driver.findElement(By.xpath("(//div[@class='temp'])[1]")).getText();
		System.out.println("*********UI Temperature -> "+Temperature_UI);
		
	     return Temperature_UI;
	    		 
			
	}

	public boolean UI_API_Teperature_Comparator(String UI_Temp,String API_Temp,float expected_range) {
	float API_temp=	Float.parseFloat(API_Temp);
	 float UI_temp= Integer.parseInt((UI_Temp.split("°"))[0]);	
	float Actual_diff  = Math.abs((API_temp-UI_temp));
	 System.out.println("API and UPI Temperature Difference -> " +Actual_diff);
	 
	 if( Actual_diff<expected_range) 
	 {
		 System.out.println("Temperature from UI and API matches correctly");
	         return true;
			 //"API_Temperature and UI Tepmerature are within a specified range"	;	    	   
	       }else {
	    	   
	    	
	    		 
 	            return false;
	}
	}
}
