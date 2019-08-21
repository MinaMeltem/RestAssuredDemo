package files;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReUsables {
	
	//from XML to String
	public String GenerateSringFromResource(String path) throws IOException{ 
		 return new String (Files.readAllBytes(Paths.get(path)));
	}
	
	
	public XmlPath rawToXML ( Response r) {		
		String resStr = r.asString();
		XmlPath x_path = new XmlPath(resStr);
		return x_path;			
	}
	
	public JsonPath rawToJson ( Response r) {		
		String resStr = r.asString();
		JsonPath j_path = new JsonPath(resStr);
		return j_path;
		
	}
	
	
		
		

}
