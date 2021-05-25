package Actions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import ActionFindElementSelenium.AcoesFindElement;
import ConstantsSuiteTests.ConstanteUrlBroweser;
import PageObjects.PesquisaSmartPhonePageObject;
import Report.ExtentReport;
import Report.Report;

public class PesquisaSmartPhoneAction extends AcoesFindElement {
	
	String URL = ConstanteUrlBroweser.URL_SITE_PESQUISA;
	private WebDriver driver;
	private ExtentReport reportHTML;
	private Report report;

	/**
	 * - Construtor da Class.
	 */
	public PesquisaSmartPhoneAction(WebDriver driver, ExtentReport reportHTML, Report report) {
		this.driver = driver;
		this.reportHTML = reportHTML;
		this.report = report;
	}
	
	public void reportPass(String txt) throws InterruptedException {
		reportHTML.test.log(LogStatus.PASS, txt);
		reportHTML.test.log(LogStatus.PASS, reportHTML.test.addScreenCapture(report.capturar()));
		report.capturarText(txt);
		System.out.println(txt);
		sleep(1000);
	}
	

	public void reportFail(String txt) throws InterruptedException {
		reportHTML.test.log(LogStatus.FAIL, txt);
		reportHTML.test.log(LogStatus.FAIL, reportHTML.test.addScreenCapture(report.capturar()));
		report.capturarText(txt);
		System.out.println(txt);
		sleep(1000);
		Assert.fail();
	}
	
	public void AcessarSite() {
		openURL(driver, URL);
	}
	
	public void pesquisarProduto(String PRODUTO) throws InterruptedException {
		inputClear(driver, PesquisaSmartPhonePageObject.pesquisaProduto, PRODUTO);
		click(driver, PesquisaSmartPhonePageObject.btnPesquisar);
		scroll(driver, 300);
		reportPass("Realizado pesquisa pelo produto: " + PRODUTO);
		sleep(1000);
		
		try {
			click(driver, PesquisaSmartPhonePageObject.clicarProduto);
			
			String preco = getText(driver, PesquisaSmartPhonePageObject.preco);			
			String cashBack = getText(driver, PesquisaSmartPhonePageObject.cashBack);
			
			reportPass(PRODUTO + ", a vista por: " + preco + " e " + cashBack + " de cashBack.");
			
		} catch (Exception e) {
			reportFail("Falta ao buscar o produto: " + PRODUTO + " " + e);
		}
	}	

}
