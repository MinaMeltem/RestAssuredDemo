
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;



public class Basics {
	
	@Test
	public void Test1() {		
		
		RestAssured.baseURI = "https://api.nytimes.com"; //BasedURL (Host)
		
		given().
				param("q","election").
				param("api-key","Mx9yFxTIte8DM4XP7gBFQlEWOn4H7WXY").
				
				/*header("", "").
				  cookie("","").
				  body("")  // POST body */
				
				when().get("svc/search/v2/articlesearch.json").	//resource	
				then().assertThat().statusCode(200).contentType(ContentType.JSON) //.contenttype --> header
				.and(). 
				body("response.docs[0].abstract", equalTo("The Trump bump probably peaked too early."))
				.and()
				.body("response.docs[3].keywords[1].value", equalTo("Presidential Election of 2020"))
				.and()
				.header("server", "nginx"); // value from header
		
		// GET REQUEST
		// Verify status code of the reponse --> 200
		// Verify Whether the content-type response is JSON or not
		// Putting assertion on response body
		// Putting assertion of header reposnse
						

	}

}
