package testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import pages.FindLeadsPage;
import pages.FindMergeLeadsPage;
import pages.LoginPage;
import wrapper.ProjectSpecificWrapper;

public class TC005_MergeLead extends ProjectSpecificWrapper{
	
		
	@BeforeTest
	public void setData() {
		testCaseName = "TC005_MergeLead";
		testCaseDec = "Merge two leads in Opentaps";
		author = "Neringa";
		category = "Smoke";
		device = "Windows";
		dataSheet = "TC005";
		sheetName = "Sheet1";
	}
	
	@Test(dataProvider = "fetchData")
	public void mergeLead(String uname, String psw, String fname, String error) throws InterruptedException {
		new LoginPage(driver, test)
		.enterUserName(uname)
		.enterPassword(psw)
		.clickLogin()
		.clickCRMSFA()
		.clickLeads()
		.clickMergeLeads()
		.clickOnIconFromLead()
		.enterFirstName(fname)
		.clickFindLeadsButton()
		.clickFirstResult()
		.clickOnIconToLead()
		.enterFirstName(fname)
		.clickFindLeadsButton()
		.clickSecondResult()
		.clickMergeButton()
		.clickAcceptAlert()
		.clickFindLeads()
		.enterLeadId(FindMergeLeadsPage.leadId)
		.clickFindLeadsButton()
		.verifyErrorMsg(error);
	}


}
