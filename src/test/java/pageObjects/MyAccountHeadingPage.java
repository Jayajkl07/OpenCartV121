package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountHeadingPage extends BasePage
{
WebDriver driver;
	
	//constructor
	public MyAccountHeadingPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	//Action methods
	public boolean isMyAccountPageExists()
	{
		try
		{
			return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void ClickLogout()
	{
		lnkLogout.click();
	}
}
