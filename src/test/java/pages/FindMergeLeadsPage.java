package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import wrapper.ProjectSpecificWrapper;

public class FindMergeLeadsPage extends ProjectSpecificWrapper{
	
	public FindMergeLeadsPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public static String leadId;
	
	public FindMergeLeadsPage enterFirstName(String data) {
		clearAndType(locateSelector("name", prop.getProperty("findmergel.fname.name")), data);
		return this;
	}
	
	public FindMergeLeadsPage clickFindLeadsButton() throws InterruptedException {
		click(locateSelector("xpath", prop.getProperty("findmergel.flbutton.xpath")));
		Thread.sleep(2000);
		return this;
	}
	
	public MergeLeadsPage clickFirstResult() {
		leadId = getElementText(locateSelector("xpath", prop.getProperty("findmergel.firstresult.xpath")));
		click(locateSelector("xpath", prop.getProperty("findmergel.firstresult.xpath")));
		switchToWindow(0);
		return new MergeLeadsPage(driver, test);
	}	
	
	public MergeLeadsPage clickSecondResult() {
		click(locateSelector("xpath", prop.getProperty("findmergel.secodresult.xpath")));
		switchToWindow(0);
		return new MergeLeadsPage(driver, test);
	}
	
}
