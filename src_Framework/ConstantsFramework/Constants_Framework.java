package ConstantsFramework;

public class Constants_Framework {

	
	//C:\Program Files (x86)\Google\Chrome\Application
   public static String CHROME_DRIVER =System.getProperty("user.dir") + "//resources//drivers//chromedriver.exe" ;
	//public static String CHROME_DRIVER ="C://Program Files (x86)//Google//Chrome//Application//chrome_proxy.exe" ;
	public static String FIREFOX_DRIVER = System.getProperty("user.dir") + "//resources//drivers//geckodriver.exe";
	public static String IE_DRIVER = System.getProperty("user.dir") + "//resources//drivers//IEDriverServer.exe";
	public static String PDF_PATH=System.getProperty("user.dir") + "//resources//reports//Relatorio_Pdf_";
	public static String DOC_PATH=System.getProperty("user.dir") + "//resources//reports//template//Template_automation";
	public static String EVIDENCIA_PATH=System.getProperty("user.dir") + "//resources//reports//relatorio//";
	
	
	public static String VIDEO_PATH=System.getProperty("user.dir") + "//resources//reports//relatorio//";
	public static String PRINTS_PATH=System.getProperty("user.dir") + "//resources//reports//prints//";
	public static String HTML_PATH=System.getProperty("user.dir") + "//resources//configs//extent-config.xml";
	public static String PROPERTY_PATH=System.getProperty("user.dir") + "//resources//configs//Configuration.properties";
	

}
