package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import wrapper.ProjectSpecificWrapper;

public class MyHomePage extends ProjectSpecificWrapper{
	
	public MyHomePage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public MyLeadsPage clickLeads() {
		click(locateSelector("link", prop.getProperty("myhome.leads.link")));
		return new MyLeadsPage(driver, test);
	}

}
