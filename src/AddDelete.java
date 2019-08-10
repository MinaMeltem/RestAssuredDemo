import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class AddDelete {
	
	/*
	 * Add a new place to API
	 * Delete the place just created from API
	 * Logic: POST new place and get response
	 * extract the ID value from response, 
	 * and DELETE the place by using the
	 * */
	
	@Test
	public void addDeletePlace() {
		
		String newPlace = "{\r\n" + 
				"    \"location\":{\r\n" + 
				"        \"lat\" : -38.383494,\r\n" + 
				"        \"lng\" : 33.427362\r\n" + 
				"    },\r\n" + 
				"    \"accuracy\":50,\r\n" + 
				"    \"name\":\"Frontline house\",\r\n" + 
				"    \"phone_number\":\"(+91) 983 893 3937\",\r\n" + 
				"    \"address\" : \"29, side layout, cohen 09\",\r\n" + 
				"    \"types\": [\"shoe park\",\"shop\"],\r\n" + 
				"    \"website\" : \"http://google.com\",\r\n" + 
				"    \"language\" : \"French-IN\"\r\n" + 
				"}";
		
		RestAssured.baseURI = "http://216.10.245.166";
		Response res = given().
				queryParam("key","qaclick123").
				body(newPlace).
				when().
				post("/maps/api/place/add/json").
				then().assertThat().statusCode(200).
				and().contentType(ContentType.JSON).
				body("status",equalTo("OK")).
				extract().response();	
		
		String response = res.asString();
		System.out.println(response);
						
	}
	
	
	

}
