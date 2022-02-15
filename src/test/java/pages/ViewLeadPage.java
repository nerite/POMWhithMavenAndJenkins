package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import wrapper.ProjectSpecificWrapper;

public class ViewLeadPage extends ProjectSpecificWrapper {
	
	public ViewLeadPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public ViewLeadPage verifyFirstName(String data) {
		verifyExatText(locateSelector("xpath", prop.getProperty("viewl.verfname.xpath")), data);
		return this;
	}
	
	public ViewLeadPage verifyTitleOfPage(String data) {
		verifyTitle(data);
		return this;
	}
	
	public EditLeadPage clickEdit() {
		click(locateSelector("link", prop.getProperty("viewl.edit.link")));
		return new EditLeadPage(driver, test);
	}
	
	public MyLeadsPage clickDelete() {
		click(locateSelector("link", prop.getProperty("viewl.delete.link")));
		return new MyLeadsPage(driver, test);
	}
	
	public ViewLeadPage verifyCompanyName(String data) {
		verifyPartialText(locateSelector("id", prop.getProperty("viewl.vercname.id")), data);
		return this;
	}
	
	public FindLeadsPage clickFindLeads() {
		click(locateSelector("xpath", prop.getProperty("viewl.find.xpath")));
		return new FindLeadsPage(driver, test);
	}

}
