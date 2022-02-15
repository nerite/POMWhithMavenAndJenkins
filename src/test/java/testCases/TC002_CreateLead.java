package testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrapper.ProjectSpecificWrapper;

public class TC002_CreateLead extends ProjectSpecificWrapper{
	
	@BeforeTest
	public void setData() {
		testCaseName = "TC002_CreateLead";
		testCaseDec = "Create a new Lead in Opentaps";
		author = "Neringa";
		category = "Smoke";
		device = "Windows";
		dataSheet = "TC002";
		sheetName = "Sheet1";
	}
	
	@Test(dataProvider = "fetchData")
	public void createLead(String uname, String psw, String cname, String fname, String lname, String id, String value, String phnumber, String eaddress) {
		new LoginPage(driver, test)
		.enterUserName(uname)
		.enterPassword(psw)
		.clickLogin()
		.clickCRMSFA()
		.clickLeads()
		.clickCreateLead()
		.enterCompanyName(cname)
		.enterFirstName(fname)
		.enterLastName(lname)
		.selectSource(id)
		.selectMarketingCompaign(value)
		.enterPhoneNumber(phnumber)
		.enterEmailAddress(eaddress)
		.clickCreateLead()
		.verifyFirstName(fname);
	}

}
