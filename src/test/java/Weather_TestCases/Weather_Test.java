package Weather_TestCases;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Weather_Base.Weather_BaseClass;
import Weather_Utilities.JSONParser;
import Weather_Utilities.TestVagrantException;
import Web_Pages.City_Weather;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class Weather_Test extends Weather_BaseClass {

	public City_Weather Weather;
	public static JsonPath js;
	public String API_temperature;
	public String UI_Temperature;

	public Weather_Test() {
		super();
	}

//@Parameters({"URL"})
	@BeforeClass()
	public void setup() throws InterruptedException {
		Initialization();
		Weather = new City_Weather();
	}

	@Test(priority = 0)  
	public void Weather_UI_Test() {
		UI_Temperature = Weather.CityWeatherData(prop.getProperty("City"));

	}

// /data/2.5/weather?q - It should have been in Get method
	@Test(priority = 1)
	public void Weather_API_Test() {
		RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/weather?q";
		String Resp = given().log().all().relaxedHTTPSValidation().param("q", prop.getProperty("City"))
				.param("appid", "7fe67bf08c80ded756e598d6f8fedaea").param("units", "metric").when().get().then().log()
				.all().assertThat().statusCode(200).extract().response().asString();
		System.out.println("************************************************************************************");
		System.out.println(Resp);
		js = JSONParser.Json(Resp);
		API_temperature = js.getString("main.temp");
		System.out.println(")))))))))))");
		System.out.println("**********API Temperature -> " +API_temperature);

	}

	@Test(priority = 3)
	public void UI_API_ComparatorTest() {
		boolean t=Weather.UI_API_Teperature_Comparator(UI_Temperature, API_temperature,Float.parseFloat(prop.getProperty("expected_range")));
		    if(!t) 
		    {
		    	 try {
		    		   throw new TestVagrantException("This is Custom TestVagrant Exception for Failed testcase");
		    	   	           		
		            } catch(TestVagrantException e) {
		            	System.out.println("Error ->" +e.getMessage());
		            	e.printStackTrace();
		            	
		            }
		    }//else if (t) {
		
		Assert.assertEquals(t, true,"Temperature from UI and API does not match");
		   // }
	}

	@AfterMethod
	public void testcomplete() {
		System.out.println("Testcase complete");
	}

	@AfterClass()
	public void EndOfTest() throws InterruptedException {

		System.out.println("Weather test completed");
		driver.close();
	}

}
