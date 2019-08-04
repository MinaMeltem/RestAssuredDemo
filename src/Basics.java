
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;



public class Basics {

	public static void main(String[] args) {
		
		//BasedURL (Host)
		
		RestAssured.baseURI = "https://api.nytimes.com";
		
		given().
				param("q","election").
				param("api-key","Mx9yFxTIte8DM4XP7gBFQlEWOn4H7WXY").
				
				/*header("", "").
				  cookie("","").
				  body("")  // POST body */
				
				when().get("svc/search/v2/articlesearch.json").	//resource	
				then().assertThat().statusCode(200).contentType(ContentType.JSON).and(). //.contenttype --> header
				body("response.docs[0].abstract", equalTo("The Trump bump probably peaked too early."));

	}

}
