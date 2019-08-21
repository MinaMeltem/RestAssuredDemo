import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import files.*;



public class AddDelete {
	
	/*
	 * Add a new place to API
	 * Delete the place just created from API
	 * Logic: 
	 * 1 -POST new place and get response
	 * 2 -Extract the ID value from response, 
	 * 3 -DELETE the place by using extracted place_id
	 * Note: DELETE is also POST request
	 * */
	
	Properties prop = new Properties(); // prop object will read and pass the key value from properties file (Host etc..)
	Resources rcs = new Resources();
	Payload pld =  new Payload();
	ReUsables rusbl = new ReUsables();
	
	
	@BeforeTest
	
	public void getData() throws IOException {			
		FileInputStream propFile = new FileInputStream("C:\\Users\\MEL\\Desktop\\QA\\Selenium\\Exercises\\APIautomation\\RestAssuredDemo\\src\\files\\env.properties");//tell where the properties file is
		prop.load(propFile);   //integrating the file to FIS object		
		//prop.get("HOST");
	}
	
	
	@Test
	public void addDeletePlace() {		
		
		// Task 1 : Adding a place
		String newPlace =pld.getPost();
		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given().
				queryParam("key",prop.getProperty("KEY")).
				body(newPlace).
				when().
				post(rcs.postData()).
				then().assertThat().statusCode(200).
				and().contentType(ContentType.JSON).
				body("status",equalTo("OK")).
				extract().response(); // -----> Extracting response and putting it in Response type 'res' variable	 
		
		
		// Task 2		
		JsonPath js = rusbl.rawToJson(res); //Convert responseStr string into json tye
		String p_id = js.get("place_id");//extraxting place_id from json (test purpose)
		System.out.println(p_id); // test purpose
						
		
		// Task3 place the plac_id in the post request
		given().
		queryParam("key", prop.getProperty("KEY") ).
		body("{\n"
				+ "\"place_id\": \"" + p_id + "\"\n" 
		+"}").
		 
		when().
		post(rcs.deleteData()). //post delete request
		then().assertThat().statusCode(200).
		and().contentType(ContentType.JSON).
		body("status",equalTo("OK")); //succesfully deleted 		
		
	}

}
