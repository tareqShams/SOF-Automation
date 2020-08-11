package fawry.sofAutomation.testsActions.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import fawry.sofAutomation.constants.login.Constants;
import fawry.sofAutomation.utils.PropertiesFilesHandler;
import fawry.sofAutomation.utils.strategy.TestDataStrategy;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BasicTest 
{
	public static ExtentHtmlReporter htmlReporter;	
	public static ExtentReports extent;
	public static ExtentTest test;

	public static String downloadpath = System.getProperty("user.dir")+"\\downloads";
	public static String allurepath = System.getProperty("user.dir")+"\\allure-results";
	public static String screenspath = System.getProperty("user.dir")+"\\screenshots";

	public static Logger log;

	@BeforeSuite
	public static void deleteOldFiles() {
		File dwnloadDir = new File(downloadpath);
		File[] downloadfiles = dwnloadDir.listFiles();
		for (File file : downloadfiles)
		{
			if (!file.delete())
			{
				System.out.println("Faild to delete file" + file);
			}
		}
		File allureDir = new File(allurepath);
		File[] allurefiles = allureDir.listFiles();
		for (File file : allurefiles)
		{
			if (!file.delete())
			{
				System.out.println("Faild to delete file" + file);
			}
		}
		File screensDir = new File(screenspath);
		File[] screensfiles = screensDir.listFiles();
		for (File file : screensfiles)
		{
			if (!file.delete())
			{
				System.out.println("Faild to delete file" + file);
			}
		}

	}

	@BeforeSuite
	public void setExtent() 
	{

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\ExtentRepeort.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}


	public static String getScreenShot(WebDriver driver, String screenshotName) throws Exception {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		//Screen Shots Folder will be Created
		String destination = System.getProperty("user.dir")+"\\screenshots\\"+screenshotName+timeStamp;

		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static ChromeOptions ChromeOption() 
	{
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> ChromePrefs = new HashMap<String, Object>();
		ChromePrefs.put("profile.default.content_settings.popups", 0);
		ChromePrefs.put("download.default_directory", downloadpath);
		options.setExperimentalOption("prefs", ChromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options;
	}

	public static FirefoxOptions FireFoxOption() 
	{
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderlist", 2);
		option.addPreference("browser.download.dir", downloadpath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		return option;
	}
	protected static WebDriver driver;

	@Parameters({"url","browserType"})
	@BeforeClass
	public void loadConfiguration(String url,String browserType) throws IOException
	{
		if(browserType.equalsIgnoreCase("CH"))
		{
		   WebDriverManager.chromedriver().setup();
		   driver = new ChromeDriver(ChromeOption());
		}
		else if(browserType.equalsIgnoreCase("FF"))
		{
		   WebDriverManager.firefoxdriver().setup();
		   driver = new FirefoxDriver(FireFoxOption());
		}
		else if(browserType.equalsIgnoreCase("IE"))
		{
		   WebDriverManager.iedriver().setup();
		   driver = new InternetExplorerDriver();
		}
		else if(browserType.equalsIgnoreCase("OPERA"))
		{
		   WebDriverManager.operadriver().setup();
		   driver = new OperaDriver();
		}
		else if(browserType.equalsIgnoreCase("EDGE"))
		{
		   WebDriverManager.edgedriver().setup();
		   driver = new EdgeDriver();
		}
			

		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		DOMConfigurator.configure("log4j.xml");

	}

	//take screenShot when test case fails and save in screenshots folder
	@AfterMethod
	
	public void screenshotOnFailure(ITestResult result) throws Exception 
	{
		if (result.getStatus() == ITestResult.FAILURE) 
		{
			/*			System.out.println("Taking ScreenShot");
			Helper.captureScreenshot(driver, result.getName());*/

			test.fail(MarkupHelper.createLabel(result.getName() + " Test Case Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
			String screenshotPath = getScreenShot(driver, result.getName());
			//logger.log(LogStatus.FAIL, logger.addScreencast(screenshotPath));
			test.addScreenCaptureFromPath(screenshotPath);
		}
		else if (result.getStatus() == ITestResult.SKIP) 
		{
			test.skip(MarkupHelper.createLabel(result.getName() +" Test Case Skipped is ", ExtentColor.YELLOW) );
			test.skip(result.getThrowable());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) 
		{
			test.pass(MarkupHelper.createLabel(result.getName() + " Test Case Passed", ExtentColor.GREEN) );
		}
	}

	@AfterClass
	public void closeDriver()
	{
		try {
			if(driver !=null)
				driver.quit();
		} catch (Exception e) {
			System.out.println("Error Occured"+e);
		}
	}

	@AfterSuite
	public void endTest() 
	{
		extent.flush();
	}

	protected ArrayList<ArrayList<Object>> provideTestData(String connectionProperties) {

		ArrayList<ArrayList<Object>> result = null;
		TestDataStrategy testData = null;
		String testDataType = "";
		String testDataTypeClassPath ="";

		try {

			PropertiesFilesHandler propLoader = new PropertiesFilesHandler();
			Properties prop = propLoader.loadPropertiesFile(Constants.TEST_DATA_CONFIG_FILE_NAME);
			//get test data type to connect to the proper test data source accordingly
			testDataType = prop.getProperty(Constants.TEST_DATA_TYPE);

			//get class path of the class that implements methods of proper class path
			testDataTypeClassPath = prop.getProperty(Constants.TEST_DATA_TYPE_CLASS_PATH + testDataType);

			//create instance from the proper class of specified data source
			testData = (TestDataStrategy) Class.forName(testDataTypeClassPath).newInstance();

			//load test data from the proper source
			result = testData.loadTestData(connectionProperties);

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		return result;

	}
	/*	@AfterTest
	public void allurerunner()
	{
		try {
			//run the process
			Process p = Runtime.getRuntime().exec("cmd /c D:\\Projects\\SOF_Testing_GIT\\Development\\SOFTestAutomation\\allure-2.8.0\\allurerunner.bat");
			//get input stream
			InputStream is = p.getInputStream();

			//read script execution results
			int i = 0;
			StringBuffer sb = new StringBuffer();
			while ((i = is.read()) != -1)
				sb.append((char)i);

			System.out.println(sb.toString());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/

}
