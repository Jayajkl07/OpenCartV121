package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountHeadingPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups={"Sanity","Master"})
	public void verify_account_registration()
	{
		logger.info(" <----------Starting testcase TC002_LoginTest-------->");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountHeadingPage mhp=new MyAccountHeadingPage(driver);
		boolean heading=mhp.isMyAccountPageExists();
		//Assert.assertEquals(heading, true, "Login Failed");
		Assert.assertTrue(heading);
		mhp.ClickLogout();
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info(" <----------Finished testcase TC002_LoginTest-------->");
	}
}
