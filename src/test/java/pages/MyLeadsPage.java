package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import wrapper.ProjectSpecificWrapper;

public class MyLeadsPage extends ProjectSpecificWrapper {
	
	public MyLeadsPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public CreateLeadPage clickCreateLead() {
		click(locateSelector("partiallink", prop.getProperty("myleads.create.partlink")));
		return new CreateLeadPage(driver, test);
	}
	
	public FindLeadsPage clickFindLeads() {
		click(locateSelector("partiallink", prop.getProperty("myleads.find.partlink")));
		return new FindLeadsPage(driver, test);
	}
	
	public MergeLeadsPage clickMergeLeads() {
		click(locateSelector("link", prop.getProperty("myleads.merge.link")));
		return new MergeLeadsPage(driver, test);
	}

}
