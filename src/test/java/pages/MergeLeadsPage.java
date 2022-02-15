package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import wrapper.ProjectSpecificWrapper;

public class MergeLeadsPage extends ProjectSpecificWrapper{
	
	public MergeLeadsPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public FindMergeLeadsPage clickOnIconFromLead() {
		click(locateSelector("xpath", prop.getProperty("mergel.fromlead.xpath")));
		switchToWindow(1);
		return new FindMergeLeadsPage(driver, test);
	}
	
	public FindMergeLeadsPage clickOnIconToLead() {
		click(locateSelector("xpath", prop.getProperty("mergel.tolead.xpath")));
		switchToWindow(1);
		return new FindMergeLeadsPage(driver, test);
	}
	
	public MergeLeadsPage clickMergeButton() {
		click(locateSelector("class", prop.getProperty("mergel.mbutton.class")));
		return this;
	}
	
	public ViewLeadPage clickAcceptAlert() {
		acceptAlert();
		return new ViewLeadPage(driver, test);
	}
	
}
