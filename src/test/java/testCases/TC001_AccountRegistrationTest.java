package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups={"Regression","Master"}) //Master includes all test cases
    public void verify_account_registration1() throws InterruptedException {
		
		logger.info("*** Starting TC001_AccountRegistrationTest **");
    	
		try {
    	HomePage hp=new HomePage(driver);
    	hp.ClickMyAccount();
    	logger.info("Clicked on MyAccount Link");
    	hp.ClickRegister();
    	logger.info("Clicked on Register Link");
	
    	AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
    	logger.info("Providing customer details...");
    	
    	regpage.setFirstName(randomeString().toUpperCase());
    	
    	regpage.setLastName(randomeString().toUpperCase());
    	
    	regpage.setEmail(randomeString()+"@gmail.com"); //randomly generated the email
    	
    	regpage.setTelephone(randomeNumber());
    	
    	
    	String password=randomeAlphaNumeric();
    	
    	regpage.setPassword(password);
    	
    	regpage.setConfirmPassword(password);
    	
    	
    	regpage.setPrivacyPolicy();
    	
    	regpage.clickContinue();
    	
    	logger.info("Validating Expected Message...");
    	String confmsg=regpage.getConfirmationMsg();
    	
    	if(confmsg.equals("Your Account Has Been Created!")) {
    		
    		Assert.assertTrue(true);
    	}
    	else {
    		
    		logger.error("Test failed..");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
    	}
    	
    	//Validation
    	
    	Assert.assertEquals(confmsg,"Your Account Has Been Created!" );
		}
		catch(Exception e) {
			
			
			Assert.fail();
		}
		
		logger.info("*** Finished TC001_AccountRegistrationTest  *** ");
    	
}@Test(groups={"Regression","Master"}) //Master includes all test cases
public void verify_account_registration() throws InterruptedException {
	
	logger.info("*** Starting TC001_AccountRegistrationTest **");
	
	try {
	HomePage hp=new HomePage(driver);
	hp.ClickMyAccount();
	logger.info("Clicked on MyAccount Link");
	hp.ClickRegister();
	logger.info("Clicked on Register Link");

	AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
	logger.info("Providing customer details...");
	
	regpage.setFirstName(randomeString().toUpperCase());
	
	regpage.setLastName(randomeString().toUpperCase());
	
	regpage.setEmail(randomeString()+"@gmail.com"); //randomly generated the email
	
	regpage.setTelephone(randomeNumber());
	
	
	String password=randomeAlphaNumeric();
	
	regpage.setPassword(password);
	
	regpage.setConfirmPassword(password);
	
	
	regpage.setPrivacyPolicy();
	
	regpage.clickContinue();
	
	logger.info("Validating Expected Message...");
	String confmsg=regpage.getConfirmationMsg();
	
	if(confmsg.equals("Your Account Has Been Created!")) {
		
		Assert.assertTrue(true);
	}
	else {
		
		logger.error("Test failed..");
		logger.debug("Debug logs...");
		Assert.assertTrue(false);
	}
	
	//Validation
	
 Assert.assertEquals(confmsg,"Your Account Has Been Created!" );
	}
	catch(Exception e) {
		
		
		//Assert.fail();
	}
	
	logger.info("*** Finished TC001_AccountRegistrationTest  *** ");
	
}
}
