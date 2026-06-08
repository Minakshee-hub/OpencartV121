package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
	public ExtentReports extent;  //popolate common info on the report
	public ExtentTest test; //creating test case entries on the report and update status of test methods
	
	String repName;
	
	 public void onStart(ITestContext context) {
		 /*
		 SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 Date dt=new Date();
		 String currentdatetimestamp=df.format(dt);
		 */
		 String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //time stamp
		 
		 repName="Test-Report-"+timestamp+".html";
		 sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);   //loaction of report
		 
		 sparkReporter.config().setDocumentTitle("Automation Report");  //title  of report
		 sparkReporter.config().setReportName("Functional testing");  //name of report
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		 
		 extent=new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 
		 extent.setSystemInfo("Application Name", "Opencart");
		 extent.setSystemInfo("Module", "Admin");
		 extent.setSystemInfo("Sub Module", "Customers");
		 extent.setSystemInfo("User Name", System.getProperty("user.name"));
		 extent.setSystemInfo("Environment", "QA");	
		 
		 String os=context.getCurrentXmlTest().getParameter("os");
		 extent.setSystemInfo("Operating system", os);
		 
		 String browser=context.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browser", browser);
		 
		 List<String>includedGroups=context.getCurrentXmlTest().getIncludedGroups();
		 if(!includedGroups.isEmpty())
		 {
			 extent.setSystemInfo("Groups", includedGroups.toString());
		 }
		 
		 
		 
		 
		 
		 
	 }
	 public void onTestSuccess(ITestResult result) {
		 
		 test=extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.PASS, result.getName()+"got successfully excecuted");
		
	 }
	 
	 public void onTestFailure(ITestResult result) {
		 
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.FAIL, result.getName()+"got failed");
		 test.log(Status.INFO,result.getThrowable().getMessage());
		 
		try
		{
			 String imppath=new BaseClass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imppath);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	 
	 }
	 
	 public void onTestSkipped(ITestResult result) {
		 
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, result.getName()+"got skipped");
		 test.log(Status.INFO,result.getThrowable().getMessage());
	 }
	 
	 
	 public void onFinish(ITestContext context) {
		 
		 
		 extent.flush();
		 
		 String pathofextentreport=System.getProperty("user.dir")+"\\reports\\"+repName;
		 File extentReport=new File(pathofextentreport);
		 
		 try
		 {
			 Desktop.getDesktop().browse(extentReport.toURI());
			 
		 }
		 catch(IOException e)
		 {
			 e.printStackTrace();
		 }
		 
		 /* code for send email */
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	
	
	
	

}

