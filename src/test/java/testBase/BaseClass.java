package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
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
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger;  //log4j




public class BaseClass {
	
	public static  WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@Parameters({"os","browser"})
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	
	public void setup(String os,String br) throws IOException {
		
		//Loading confid.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass()); //log4j2//log file will be stored into logger variable
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//If OS 
			if(os.equalsIgnoreCase("windows")) 
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);	
			}
			else {
				System.out.println("No matching OS");
				return; //it will exit from program
			}
			
			//Browser
			
			switch(br.toLowerCase()) {
			
			case "chrome": capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
			default: System.out.println("No matching browser"); return;
			
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch(br.toLowerCase())  
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;
			case "firefox" : driver=new FirefoxDriver(); break;
			default: System.out.println("Invalid browser name..");return;
			
			}
		}
		
		driver.manage().deleteAllCookies(); //it will delete all the cookies from web page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	//	driver.get("https://tutorialsninja.com/demo/");
		
		driver.get(p.getProperty("appURL")); //reading url from properties file
		
		driver.manage().window().maximize();
		
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
    public void tearDown() {
		
		driver.quit();
	}
	
    public String randomeString() {
		
		String generatedString=RandomStringUtils.randomAlphabetic(5); //it will internally generate one random string and return it
		return generatedString;
	}
	
    public String randomeNumber() {
		
		String generatedNumber=RandomStringUtils.randomNumeric(9); 
		return generatedNumber;
	}
    
    public String randomeAlphaNumeric() {
		
    	String generatedString=RandomStringUtils.randomAlphabetic(3);
		String generatedNumber=RandomStringUtils.randomNumeric(3); 
		return (generatedString+"@"+generatedNumber);
	}
    
    public String captureScreen(String tname) {
    	
    	String timeStamp=new SimpleDateFormat("yyyyMMDDhhmmss").format(new Date());
    	
    	TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
    	File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
    	
    	String targetFilePath=System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
    	File targetFile=new File(targetFilePath);
    	
    	sourceFile.renameTo(targetFile);
    	
    	return targetFilePath;
    	
    }

}
