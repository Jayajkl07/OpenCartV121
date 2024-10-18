package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass
{
	
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info(" <----------Starting testcase TC001_AccountRegistrationTest-------->");
		
		try
		{
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info(" <----------clicked on myaccount link-------->");
		
		hp.clickRegister();
		logger.info(" <----------clicked on register link-------->");
		
		AccountRegistrationPage arp=new AccountRegistrationPage(driver);
		
		logger.info(" <----------providing customer details-------->");
		arp.setFirstName(randomString().toUpperCase());
		arp.setLastName(randomString().toUpperCase());
		arp.setEmail(randomString()+"@gmail.com");
		arp.setTelephone(randomNumber());
		
		String passwod=randomAlphanumeric();
		arp.setPassword(passwod);
		arp.setConfirmPassword(passwod);
		arp.setPrivacyPOlicy();
		arp.clickContinue();
		
		logger.info(" <----------validation message-------->");
		String confirmationmsg=arp.getConfirmationMsg();
		if(confirmationmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error(" <----------Test Failed-------->");
			logger.debug(" <----------debug logs-------->");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confirmationmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		logger.info(" <----------Finished testcase TC001_AccountRegistrationTest-------->");
	}
	
}
