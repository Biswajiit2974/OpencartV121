package baseClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;


public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups={"sanity","regression","master","datadriven"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws InterruptedException, IOException, MalformedURLException{
		
		//loading config.properties file
		  FileReader file=new FileReader(".//src//test//resources//config.properties");
		  // (.//represent current project location)
		  p=new Properties();
		  p.load(file);
			logger=LogManager.getLogger(this.getClass());
			
		  if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			  DesiredCapabilities cap=new DesiredCapabilities();
			  System.out.println("start execution");
			  //operating system
			  if(os.equalsIgnoreCase("windows")) {
				  cap.setPlatform(Platform.WIN11);
			  }
			  else if(os.equalsIgnoreCase("linux")) {
				  cap.setPlatform(Platform.LINUX);
			  }
			  else {
				  System.out.println("No matching os");
				  return;
			  }
		  
			  Thread.sleep(3000);
			  //browser
			  switch(br.toLowerCase()) {
			  case "chrome":cap.setBrowserName("chrome");break;
			  case "edge":cap.setBrowserName("MicrosoftEdge");break;
			  case "firefox":cap.setBrowserName("firefox");break;
			  default:System.out.println("invalid browser");return;
			  }
			  Thread.sleep(3000);
			  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			  System.out.println("login success");
		  }
		  
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		switch(br.toLowerCase()){
		case "chrome":driver=new ChromeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		case "edge":driver=new EdgeDriver();break;
		default:System.out.println("invalid browser");return;
		}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
	@AfterClass(groups={"sanity","regression","master","datadriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String generateString=RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}
	
	public String randomNumeric() {
		String generatenumber=RandomStringUtils.randomNumeric(10);
		return generatenumber;
	}
	
	public String randomAlphaNumeric() {
		String generateString=RandomStringUtils.randomAlphabetic(3);
		String generatenumber=RandomStringUtils.randomNumeric(3);
		return(generateString+"@"+generatenumber);
	}
	
	public String captureScreen(String tname) throws IOException {
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format( new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_" + timeStamp + ".png";
		File trg=new File(path);
		src.renameTo(trg);
//		String trg=System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_" +timeStamp;
//		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(src,new File(trg));
		return path;
	}
}
