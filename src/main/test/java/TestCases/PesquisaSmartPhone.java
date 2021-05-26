package TestCases;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import Actions.CompraProdutoAction;
import Actions.PesquisaSmartPhoneAction;
import ConstantsSuiteTests.ConstantsSpreadsheet;
import ManagersDriver.WebDriverManager;
import Report.ExtentReport;
import Report.Report;
import Spreadsheet.ObjectEnvironment;
import Spreadsheet.Spreadsheet;

@RunWith(Parameterized.class)
public class PesquisaSmartPhone {

	@Rule
	public TestWatcher watchman = new TestWatcher() {
		@Override
		protected void failed(Throwable e, Description description) {
			reportHTML.test.log(LogStatus.FAIL, "TestCase finalizado com erro: " + e.getMessage());
		}

		@Override
		protected void succeeded(Description description) {
			reportHTML.test.log(LogStatus.PASS, "TestCase finalizado com sucesso");
		}
	};

	private WebDriver driver;
	PesquisaSmartPhoneAction pesquisa;
	CompraProdutoAction compra;
	ExtentReport reportHTML = new ExtentReport();
	Report report;
	ObjectEnvironment environment;
	String TEST_CASE_NAME = "Realiza pesquisa de smart phone";

	private String AMBIENTE;
	private String SMART_PHONE_1;
	private String SMART_PHONE_2;
	private String SMART_PHONE_3;
	private String ENDERECO;

	public PesquisaSmartPhone(String AMBIENTE, String SMART_PHONE_1, String SMART_PHONE_2, String SMART_PHONE_3, String ENDERECO) {
		this.AMBIENTE = AMBIENTE;
		this.SMART_PHONE_1 = SMART_PHONE_1;
		this.SMART_PHONE_2 = SMART_PHONE_2;
		this.SMART_PHONE_3 = SMART_PHONE_3;
		this.ENDERECO = ENDERECO;
	}

	@Before
	public void setUp() {
		WebDriverManager webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
		report = new Report();
		environment = new ObjectEnvironment(Arrays.asList(Spreadsheet.getStringSpreadsheetAmbiente()));
		pesquisa = new PesquisaSmartPhoneAction(driver, reportHTML, report);

		reportHTML.test = ExtentReport.extent.startTest(AMBIENTE);
		reportHTML.test.log(LogStatus.INFO, "Iniciando a execucao do teste: " + TEST_CASE_NAME + " ...");
	}

	@Parameterized.Parameters
	public static Collection<Object> input() {
		Object[] objeto = Spreadsheet.getStringSpreadsheetCenario(ConstantsSpreadsheet.EXCEL_CT02);
		return Arrays.asList(objeto);
	}

	@Test
	public void RodaTest() throws InterruptedException {

		pesquisa.AcessarSite();				
		pesquisa.pesquisarProduto(SMART_PHONE_1);
		pesquisa.pesquisarProduto(SMART_PHONE_2);
		pesquisa.pesquisarProduto(SMART_PHONE_3);			
	}

	@After
	public void fim() throws Exception {
		driver.close();
		ExtentReport.extent.flush();
		report.createReportDoc("PesquisaSmartPhone");
	}
}
