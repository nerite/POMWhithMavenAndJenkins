package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import wrapper.ProjectSpecificWrapper;

public class FindLeadsPage extends ProjectSpecificWrapper {
	
	public FindLeadsPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public static String leadID;
		
	public FindLeadsPage enterFirstName(String data) {
		clearAndType(locateSelector("xpath", prop.getProperty("findl.fname.xpath")), data);
		return this;
	}
	
	public FindLeadsPage clickFindLeadsButton() throws InterruptedException {
		click(locateSelector("xpath", prop.getProperty("findl.fbutton.xpath")));
		Thread.sleep(2000);
		return this;
	}
	
	public ViewLeadPage clickFirstResultingLead() {
		leadID = getElementText(locateSelector("xpath", prop.getProperty("findl.firstresult.xpath")));
		click(locateSelector("xpath", prop.getProperty("findl.firstresult.xpath")));
		return new ViewLeadPage(driver, test);
	}
	
	public FindLeadsPage clickOnPhoneTab() {
		click(locateSelector("link", prop.getProperty("findl.phtab.link")));
		return this;
	}
	
	public FindLeadsPage enterPhoneNumber(String data) {
		clearAndType(locateSelector("xpath", prop.getProperty("findl.phnumber.xpath")), data);
		return this;
	}
	
	public FindLeadsPage enterLeadId(String data) {
		clearAndType(locateSelector("name", prop.getProperty("findl.leadid.name")), data);
		return this;
	}
	
	public FindLeadsPage verifyErrorMsg(String data) {
		verifyExatText(locateSelector("xpath", prop.getProperty("findl.errormsg.xpath")), data);
		return this;
	}

}
