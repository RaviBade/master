package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtility {

	public Properties getProperty() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("./src/test/resources/properties/browser-config.properties"));
		}catch(Exception e){
			System.out.println("Exception :"+e.getMessage());
		}
		return properties;
	}


}
