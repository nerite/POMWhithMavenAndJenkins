package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import wrapper.ProjectSpecificWrapper;

public class CreateLeadPage extends ProjectSpecificWrapper{
	
	public CreateLeadPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public CreateLeadPage enterCompanyName(String data) {
		clearAndType(locateSelector("xpath", prop.getProperty("creatl.cname.xpath")), data);
		return this;
	}
	
	public CreateLeadPage enterFirstName(String data) {
		clearAndType(locateSelector("xpath", prop.getProperty("creatl.fname.xpath")), data);
		return this;
	}
	
	public CreateLeadPage enterLastName(String data) {
		clearAndType(locateSelector("xpath", prop.getProperty("creatl.lname.xpath")), data);
		return this;
	}
	
	public CreateLeadPage selectSource(String data) {
		selectDropDownUsingIndex(locateSelector("xpath", prop.getProperty("creatl.source.xpath")), Integer.parseInt(data));
		return this;
	}
	
	public CreateLeadPage selectMarketingCompaign(String data) {
		selectDropDownUsingValue(locateSelector("xpath", prop.getProperty("creatl.mark.xpath")), data);
		return this;
	}
	
	public CreateLeadPage enterPhoneNumber(String data) {
		clearAndType(locateSelector("xpath", prop.getProperty("creatl.phnum.xpath")), data);
		return this;
	}
	
	public CreateLeadPage enterEmailAddress(String data) {
		clearAndType(locateSelector("xpath", prop.getProperty("creatl.email.xpath")), data);
		return this;
	}
	
	public ViewLeadPage clickCreateLead() {
		click(locateSelector("xpath", prop.getProperty("creatl.clickcreat.xpath")));
		return new ViewLeadPage(driver, test);
	}

}
