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

import baseClass.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
    String repName;	

	public void onStart(ITestContext context) {
//		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//		Date dt=new Date();
//		String currentdatetimestamp=df.format(dt);
		
		
        //getProperty method get your root directory // add the sub folder /reports/rt.html
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//store time
		repName="Test-Report-"+ timestamp +".html";
		sparkReporter=new ExtentSparkReporter (".\\reports\\"+repName );
		
	    sparkReporter.config().setDocumentTitle("Automation Report");//title of the report
        sparkReporter.config().setReportName("Functional Testing");//name of the report
        sparkReporter.config().setTheme(Theme.DARK);
        

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub module", "Customer");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        
        String os=context.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("operating system", os);
        
        String browser=context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("browser Name", browser);
        
        List<String> includeGroups=context.getCurrentXmlTest().getIncludedGroups();
        if(!includeGroups.isEmpty()) {
        	extent.setSystemInfo("Groups", includeGroups.toString());
        }
	}
	  
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to displyed in groups in report
		test.log(Status.PASS,"Test case pass is :"+result.getName());
	
	}
	  
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());//create new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test case failed is: "+result.getName());//update status pass/fail/skip
		test.log(Status.INFO, "Test case failed cause is: "+result.getThrowable().getMessage());//throws correct things in report
		
		try {
			String imgpath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	  
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());//create new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test=extent.createTest(result.getName());
		//create new test entry in the report
		test.log(Status.SKIP, "Test case skipped is: "+result.getName());
		test.log(Status.INFO,result.getThrowable().getMessage());
		//if any @Test method is skipped ,it will appear in the html report in yellow with the skip lebel
	}
	  
	public void onFinish(ITestContext context) {
		extent.flush();//save everything in html report
		//flush is used to write all the collected test log data into the actual report file
		String pathExtentreport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathExtentreport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
