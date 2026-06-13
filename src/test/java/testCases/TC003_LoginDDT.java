package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
Data is valid - login success- test pass- logout

Data is valid - login failed- test fail
Data is invalid - login success- test fail-logout
Data is invalid-login failed-test pass
*/

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven") //getting data provider from different class
	public void verify_loginDDT(String email,String pwd,String exp) throws InterruptedException
	{
		
		logger.info("***Starting TC003_LoginDDT**");
		
		try {
		//1.HomePage
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		
		//2.LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//3.MyAccountPage
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();//it returns boolean value
		
		/* Data is valid -login success-test pass-logout
		                 -login failed-test fail
		 * 
		 * Data is invalid -login success-test pass-logout
		                   -login failed-test fail
		 */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true) { //Login is successful
				
				macc.clickLogout();
				Assert.assertTrue(true); //test is passed
			}
			else {
				
				Assert.assertTrue(false); //test is failed
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
			
        if(targetPage==true) { //Login is unsuccessful
				
				macc.clickLogout();
				Assert.assertTrue(false); //test is failed
			}
			else {
				
				Assert.assertTrue(true); //test is passed
			}
		}
		
	}
		catch(Exception e) {
			
			Assert.fail();
		}
		Thread.sleep(3000);
		
		logger.info("***Finished TC003_LoginDDT**");
	}
}
