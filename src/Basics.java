
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;


public class Basics {

	public static void main(String[] args) {
		
		//BasedURL (Host)
		
		RestAssured.baseURI = "https://api.nytimes.com";
		
		given().
				param("q","election").
				param("api-key","Mx9yFxTIte8DM4XP7gBFQlEWOn4H7WXY").
				
				/*header("", "").
				  cookie("","").
				  body("")  // POsT body */
				
				when().get("svc/search/v2/articlesearch.json").		
				then().assertThat().statusCode(200);		

	}

}
