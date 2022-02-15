package testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrapper.ProjectSpecificWrapper;

public class TC001_LoginLogOut extends ProjectSpecificWrapper{
	
	@BeforeTest
	public void setData() {
		testCaseName = "TC001_LoginLogOut";
		testCaseDec = "To login and logout in Opentaps";
		author = "Neringa";
		category = "Smoke";
		device = "Windows";
		dataSheet = "TC001";
		sheetName = "Sheet2";
	}
	
	@Test(dataProvider = "fetchData")
	public void login(String uname, String psw) {
		new LoginPage(driver, test)
		.enterUserName(uname)
		.enterPassword(psw)
		.clickLogin()
		.clickLogOut();
	}

}
