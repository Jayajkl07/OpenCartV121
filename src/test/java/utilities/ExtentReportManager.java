package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;  //UI of the report
	public ExtentReports extent;  //populate common info on the report
	public ExtentTest test; //creating test case entries in the report and update status of the test methods
	String repName;
	public void onstart(ITestContext testContext)
	{
	
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName ="Test-Report-"+ timeStamp + ".html";
		sparkReporter  = new ExtentSparkReporter(".\\reports\\"+ repName);
	
	
	sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
    sparkReporter.config().setReportName("OpenCart Functional Tseting");
    sparkReporter.config().setTheme(Theme.STANDARD);
	
    extent = new ExtentReports();
    extent.attachReporter(sparkReporter);
    
    extent.setSystemInfo("Application", "OpenCart");
    extent.setSystemInfo("Module", "Admin");
    extent.setSystemInfo("Sub Module", "Customers");
    extent.setSystemInfo("User Name", System.getProperty("user.name"));
    extent.setSystemInfo("Environment", "QA");
    
    String os=testContext.getCurrentXmlTest().getParameter("os");
    extent.setSystemInfo("Operating System", os);
    
    String browser=testContext.getCurrentXmlTest().getParameter("browser");
    extent.setSystemInfo("Browser", browser);
    
    List<String> includeGroups= testContext.getCurrentXmlTest().getIncludedGroups();
    if(!includeGroups.isEmpty())
    {
    	extent.setSystemInfo("Groups", includeGroups.toString());
    }
	}
	
	public  void onTestSuccess(ITestResult result) 
	  {
		  test=extent.createTest(result.getTestClass().getName()); //create new entry in the report
		  test.assignCategory(result.getMethod().getGroups());
		  test.log(Status.PASS,result.getName()+  "got successfully executed " ); //update status p/f/s
	  }
	  
	  public  void onTestFailure(ITestResult result)
	  {
		  test=extent.createTest(result.getTestClass().getName()); //create new entry in the report
		  test.assignCategory(result.getMethod().getGroups());
		  
		  test.log(Status.FAIL, result.getName()+ " got failed"); //update status p/f/s
		  test.log(Status.INFO, result.getThrowable().getMessage());
		  
		  try
		  {
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		  }
		  catch(IOException e1)
		  {
			  e1.printStackTrace();
		  }
	  }
	  
	  public  void onTestSkipped(ITestResult result) 
	  {
		  test=extent.createTest(result.getTestClass().getName());
		  test.assignCategory(result.getMethod().getGroups());
		  test.log(Status.SKIP,result.getName()+ "got skipped");
		  test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	  
	  public  void onFinish(ITestContext context) 
	  {
		  extent.flush();
		  
		  String PathOfExtentReport=System.getProperty("user.dir")+"\\reports"+repName;
		  File extentReport=new File(PathOfExtentReport);
		  try
		  {
			  Desktop.getDesktop().browse(extentReport.toURI());
		  }
		  catch(IOException e)
		  {
			  e.printStackTrace();
		  }
		  
		  
		  	/*try   //send automated email to team 
		  	{
		  		URL url =new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		  		
		  		//create email message
		  		ImageHtmlEmail email=new ImageHtmlEmail();
		  		email.
		  	}*/
	  }
}
