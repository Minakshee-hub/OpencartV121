package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002LoginTest extends BaseClass {
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("***Starting TC002_LoginTest**... ");
		
		try {
		//1.HomePage
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		
		//2.LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//3.MyAccountPage
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists(); //it returns boolean value
		
		//Validation
		
		//Assert.assertEquals(targetPage, true, "Login Failed");
		//OR
		Assert.assertTrue(targetPage);
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("***Finished TC002_LoginTest**... ");
		
	}
}
