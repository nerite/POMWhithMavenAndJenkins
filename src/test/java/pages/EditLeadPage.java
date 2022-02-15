package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import wrapper.ProjectSpecificWrapper;

public class EditLeadPage extends ProjectSpecificWrapper{
	
	public EditLeadPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public EditLeadPage editCompanyName(String data) {
		clearAndType(locateSelector("id", prop.getProperty("editl.cname.id")), data);
		return this;
	}
	
	public ViewLeadPage clickUpdateButton() {
		click(locateSelector("xpath", prop.getProperty("editl.update.xpath")));
		return new ViewLeadPage(driver, test);
	}

}
