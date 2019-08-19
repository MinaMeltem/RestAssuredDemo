package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Resources {
	
	public String postData() {
		return "/maps/api/place/add/json";		
	}
	
	public String deleteData() {
		return "/maps/api/place/delete/json";
	}
	
	public String addXML() {
		return "/maps/api/place/add/xml";
	}
	
	//from XML to String
	public String GenerateSringFromResource(String path) throws IOException{ 
		 return new String (Files.readAllBytes(Paths.get(path)));
	}

}
