package wrapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import utils.DataInputProvider;

public class ProjectSpecificWrapper extends GenericWrapper {
	
	public static Properties prop;
	public String dataSheet;
	public String sheetName;
	
	@BeforeSuite
	public void loadProp() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("./properties/english.properties"));
		} catch (Exception e) {
			
		}
	}
	
	
	@BeforeMethod
	public void login() {
		// Launch the browser
		launchBrowser("chrome", "http://iarchtaps.com:8080/opentaps/");
	}
	
	@AfterMethod
	public void closeApp() {
		// Close the browser
		close();
	}
	
	@DataProvider(name="fetchData")
	public String[][] getData() throws IOException {
			DataInputProvider dp = new DataInputProvider();
			String[][] data = dp.readExcel(dataSheet, sheetName);
		return data;
	}
	
	@DataProvider(name="editData")
	public String[][] editData(){
		String[][] data = new String[2][2];
		data[0][0] = "Neringa";
		data[0][1] = "Berry";
		
		data[1][0] = "Nerita";
		data[1][1] = "Clementoni";
		
		return data;
	}

}
