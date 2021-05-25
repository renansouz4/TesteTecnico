package ManagersDriver;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import EnumsDrivers.EnvironmentType;


public class WebDriverManager {
	ChromeDriver driver;
	//static WebDriver driver;
	//private static DriverType driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();

	
//	public static WebDriver getDriver(DriverType driverType) {
//		//if (driver == null)
//			driver = createDriver(driverType);
//		return driver;
//	}
	
	@SuppressWarnings("deprecation")
    public  ChromeDriver getDriver () {       
       
        Map prefs = new HashMap();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/resources/drivers/chromedriver.exe");
        prefs.put("binary", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("user-data-dir=C:\\Users\\renan.augusto.souza\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        return driver;
    }
	

//	private static WebDriver createDriver(DriverType driverType) {
//		switch (environmentType) {
//		case LOCAL:
//			driver = createLocalDriver( driverType);
//			break;
//		case REMOTE:
//			driver = createRemoteDriver();
//			break;
//		}
//		return driver;
//	}
//
//	private static WebDriver createRemoteDriver() {
//		throw new RuntimeException("RemoteWebDriver is not yet implemented");
//	}
//
//	@SuppressWarnings("deprecation")
//	private static <ChromeProfile> WebDriver createLocalDriver(DriverType driverType) {
//		switch (driverType) {
//		case FIREFOX:
//			System.setProperty(	FIREFOX_DRIVER_PROPERTY,
//					Constants_Framework.FIREFOX_DRIVER);
//		ProfilesIni profile = new ProfilesIni();
//			
//			FirefoxProfile fixProfile = profile.getProfile("default");
//			
//			FirefoxOptions optionsf = new FirefoxOptions();
//			optionsf.setProfile(fixProfile);
//			
//			driver = new FirefoxDriver(optionsf);
//			break;
//		case CHROME:
//			System.setProperty(CHROME_DRIVER_PROPERTY,
//				Constants_Framework.CHROME_DRIVER);
			//System.setProperty("webdriver.chrome.verboseLogging", "true");
			
			
			//ChromeOptions options1 = new ChromeOptions();
			//options1.addArguments("test-type");
			//options1.addArguments("disable-popup-blocking");
			//options1.addArguments("--disable-notifications");
			//options1.addArguments("chrome.switches","--disable-extensions"); 
			//options1.addArguments("--disable-save-password");
			//options1.addArguments("disable-infobars");
			
			
			//options1.addArguments("--disable-popup-blocking");
			//options1.addArguments("--disable-notifications");
			//options1.addArguments("permissions.default.desktop-notification");
		//	DesiredCapabilities capabilities1 = DesiredCapabilities.chrome();
		 //  capabilities1.setCapability(ChromeOptions.CAPABILITY, options1);
		  // capabilities1.setCapability("--disable-popup-blocking", true);
		   //capabilities1.setCapability("--disable-notifications", true);
		   //capabilities1.setJavascriptEnabled(true);
			
			
	
			
			/*	ChromeOptions options1 = new ChromeOptions();
				options1.addArguments("--disable-popup-blocking");
				options1.addArguments("--disable-notifications");
				options1.addArguments("--disable-web-security");
			//	options1.addArguments("headless"); 
				//options1.addArguments("-incognito");
				DesiredCapabilities capabilities1 = new DesiredCapabilities();
			    capabilities1.setCapability(ChromeOptions.CAPABILITY, options1);*/
			    
			    
				//JavascriptExecutor js = (JavascriptExecutor) driver;
				//js.executeScript("document.body.style.zoom='90%'");
				
				//options1.addArguments("--disable-notifications");
				//options1.addArguments("disable-extensions");
				
				//Map<String, Object> prefs = new HashMap<String, Object>();

				//prefs.put("profile.default_content_setting_values.notifications", 0);
				
				//options1.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
				//prefs.put("profile.default_content_setting_values.popups", 1);
				//options1.addArguments("--no-sandbox");
			
			
			//String chromeProfilePath = "C://Users//rodrigo.c.almeida//AppData//Local//Google//Chrome//User Data//";
			//String chromeProfilePath = "C://Program Files (x86)//Google//Chrome//Application";
			
	
			
			
//			ChromeOptions options = new ChromeOptions();
	
			//options.addArguments("--headless");
			//options.addArguments("--no-sandbox");
			//System.setProperty("webdriver.chrome.args", "--disable-logging");
			//System.setProperty("webdriver.chrome.silentOutput", "true");
			//options.setBinary("/pointing/downloaded/driver/path/in/automationsuite");
			//options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		//	options.addArguments("disable-infobars"); // disabling infobars
		//	options.addArguments("--disable-extensions"); // disabling extensions
		//	options.addArguments("--disable-gpu"); // applicable to windows os only
			//options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			//options.addArguments("window-size=1024,768"); // Bypass OS security model
		//	options.addArguments("--log-level=3"); // set log level
		//	options.addArguments("--silent");//
		   // options.setCapability("chrome.verbose", true); //disable logging
		  // options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome_proxy.exe");
			//driver = new ChromeDriver(options);
		  //  options.addArguments("user-data-dir=" + chromeProfilePath);
			// Here you specify the actual profile folder (Profile 2)
		  // options.addArguments("profile-directory=Default");
			
			//chromeProfile.addArguments("--start-maximized");
			
	
//		  driver =   new ChromeDriver(options);
		
			 
			 
			 
			 
				//JavascriptExecutor js = (JavascriptExecutor) driver;
				//js.executeScript("document.body.style.zoom='50%'");
		
//			break;
//		case INTERNETEXPLORER:
//			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//			//capabilities.setJavascriptEnabled(true);
//			capabilities.setCapability("requireWindowFocus", true);
//			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
//			capabilities.setCapability("ignoreZoomSetting", true);
//			System.setProperty(IE_DRIVER_PROPERTY,
//					Constants_Framework.IE_DRIVER);
//			driver = new InternetExplorerDriver(capabilities);
//			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//			break;
//		case CHROMEHEADLESS:
//			System.setProperty(CHROME_DRIVER_PROPERTY,
//					Constants_Framework.CHROME_DRIVER);
//			ChromeOptions options1 = new ChromeOptions();
//			options1.addArguments("headless");
//			options1.addArguments("window-size=1200x600");
//			driver = new ChromeDriver(options1);
//			break;
//		}
//
//		if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
//			driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(),
//				TimeUnit.SECONDS);
//		return driver;
//	}
//
//	public void closeDriver() {
//		driver.close();
//		driver.quit();
//	}
}