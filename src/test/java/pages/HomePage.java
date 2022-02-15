package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import wrapper.ProjectSpecificWrapper;

public class HomePage extends ProjectSpecificWrapper {
	
	public HomePage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public LoginPage clickLogOut() {
		click(locateSelector("class", prop.getProperty("home.logout.class")));
		return new LoginPage(driver, test); 
	}
	
	public MyHomePage clickCRMSFA(){
		click(locateSelector("partiallink", prop.getProperty("home.CRMSFA.partlink")));
		return new MyHomePage(driver, test);
	}

}
