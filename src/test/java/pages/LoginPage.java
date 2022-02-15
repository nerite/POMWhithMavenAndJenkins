package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import wrapper.ProjectSpecificWrapper;

public class LoginPage extends ProjectSpecificWrapper {
	
	public LoginPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public LoginPage enterUserName(String data) {
		clearAndType(locateSelector("id", prop.getProperty("login.uname.id")), data);
		return this;
	}
	
	public LoginPage enterPassword(String data) {
		clearAndType(locateSelector("id", prop.getProperty("login.pwd.id")), data);
		return this;
	}
	
	public HomePage clickLogin() {
		click(locateSelector("class", prop.getProperty("login.login.class")));
		return new HomePage(driver, test);
	}

}
