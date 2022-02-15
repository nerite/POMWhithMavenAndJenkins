package testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.FindLeadsPage;
import pages.LoginPage;
import wrapper.ProjectSpecificWrapper;

public class TC004_DeleteLead extends ProjectSpecificWrapper{
	
	@BeforeTest
	public void setData() {
		testCaseName = "TC004_DeleteLead";
		testCaseDec = "Delete lead in Opentaps";
		author = "Neringa";
		category = "Smoke";
		device = "Windows";
		dataSheet = "TC004";
		sheetName = "Sheet1";
	}
	
	@Test(dataProvider = "fetchData")
	public void deleteLead(String uname, String psw, String phname, String error) throws InterruptedException {
		new LoginPage(driver, test)
		.enterUserName(uname)
		.enterPassword(psw)
		.clickLogin()
		.clickCRMSFA()
		.clickLeads()
		.clickFindLeads()
		.clickOnPhoneTab()
		.enterPhoneNumber(phname)
		.clickFindLeadsButton()
		.clickFirstResultingLead()
		.clickDelete()
		.clickFindLeads()
		.enterLeadId(FindLeadsPage.leadID)
		.clickFindLeadsButton()
		.verifyErrorMsg(error);
		
	}


}
