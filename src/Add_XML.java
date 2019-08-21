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
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import files.*;



public class Add_XML {
		
	Properties prop = new Properties();
	Resources rcs = new Resources();
	Payload pld =  new Payload();
	
	
	@BeforeTest
	
	public void getData() throws IOException {			
		FileInputStream propFile = new FileInputStream("C:\\Users\\MEL\\Desktop\\QA\\Selenium\\Exercises\\APIautomation\\RestAssuredDemo\\src\\files\\env.properties");//tell where the properties file is
		prop.load(propFile);  		
	}
	
	
	@Test
	public void addPlace() throws IOException {			
		// from xml to str
		String newPlace = rcs.GenerateSringFromResource("C:\\Users\\MEL\\Desktop\\QA\\Selenium\\Exercises\\APIautomation\\RestAssuredDemo\\src\\files\\payload.xml"); 
		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given().
				queryParam("key",prop.getProperty("KEY")).
				body(newPlace). // post body
				when().
				post(rcs.addXML()).//resource
				then().assertThat().statusCode(200).
				and().contentType(ContentType.XML).
				//body("status",equalTo("OK")).// status code is not equal to this, also this one is optional
				extract().response(); 	
		
				//To see the response format, so I can use proper assertion
				String resStr = res.asString();//from xml to string
				System.out.println(resStr);//display for test purpose
				XmlPath x_path = new XmlPath(resStr); //convert response from string to xml
				String p_id = x_path.get("response.place_id");//extract
				System.out.println(p_id);

	}

}
