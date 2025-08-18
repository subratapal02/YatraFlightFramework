package base;
import java.io.FileInputStream;
/*
 * Purpose:
Handles reading configuration properties from multiple files.
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
//Configuration handling (PropertiesOperation)
public class PropertiesOperation {
static Properties prop = new Properties();//Defines a static Properties object to store loaded properties.
//Reading Configuration Values
/*
 * Reads a property value from config.properties.
Uses FileInputStream to open the file.
Loads properties into prop.
Returns the value corresponding to the given key.
 */
public static String getPropertyValue(String key){
	String propFilePath = System.getProperty("user.dir")+ "/Properties/config.properties";
	FileInputStream fis;
	try {
		fis = new FileInputStream(propFilePath);
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String value = prop.get(key).toString();
	return value;
}
//Methods for Different Property Files/* Reads from application.properties */These methods follow the same pattern but read from different property files.

public static String getApplicationPropertyValue(String key){
	String propFilePath = System.getProperty("user.dir")+ "/Properties/applications.properties";
	FileInputStream fis;
	try {
		fis = new FileInputStream(propFilePath);
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String value = prop.get(key).toString();
	return value;
}

}
