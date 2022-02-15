package testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrapper.ProjectSpecificWrapper;

public class TC003_EditLead extends ProjectSpecificWrapper{
	
	@BeforeTest
	public void setData() {
		testCaseName = "TC003_EditLead";
		testCaseDec = "Edit created lead in Opentaps";
		author = "Neringa";
		category = "Smoke";
		device = "Windows";
		dataSheet = "TC003";
		sheetName = "Sheet1";
	}
	
	@Test(dataProvider = "fetchData")
	public void editLead(String uname, String psw, String title, String cname, String fname) throws InterruptedException {
		new LoginPage(driver, test)
		.enterUserName(uname)
		.enterPassword(psw)
		.clickLogin()
		.clickCRMSFA()
		.clickLeads()
		.clickFindLeads()
		.enterFirstName(fname)
		.clickFindLeadsButton()
		.clickFirstResultingLead()
		.verifyTitleOfPage(title)
		.clickEdit()
		.editCompanyName(cname)
		.clickUpdateButton()
		.verifyCompanyName(cname);
	}

}
