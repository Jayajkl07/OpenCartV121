package testCases;

	import org.testng.Assert;
import org.testng.annotations.Test;

//Data is valid -login success -test pass-logout
	//Data is valid -login failed- test failed
	//Data is invalid -login success-test failed- logout
	//Data is invalid -login failed- test pass

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountHeadingPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDriven extends BaseClass
{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups="Datadriven") //name of the data provider from different class
	public void Verify_LoginDDT(String email, String pwd, String exp)
	{
        logger.info(" <----------Starting testcase TC003_LoginDataDriven-------->");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountHeadingPage mhp=new MyAccountHeadingPage(driver);
		boolean heading=mhp.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid"))  //credential is valid
		{
			if(heading==true) //login success
			{
				mhp.ClickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))  //credential is valid
		{
			if(heading==true) //login success
			{
				mhp.ClickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info(" <----------Finished testcase TC003_LoginDataDriven-------->");
	}

}
