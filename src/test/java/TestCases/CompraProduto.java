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
import ConstantsSuiteTests.ConstantsSpreadsheet;
import ManagersDriver.WebDriverManager;
import Report.ExtentReport;
import Report.Report;
import Spreadsheet.ObjectEnvironment;
import Spreadsheet.Spreadsheet;

@RunWith(Parameterized.class)
public class CompraProduto {

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
	CompraProdutoAction compra;
	ExtentReport reportHTML = new ExtentReport();
	Report report;
	ObjectEnvironment environment;
	String TEST_CASE_NAME = "Realiza compra de um produto";

	private String AMBIENTE;
	private String EMAIL;
	private String SENHA;
	private String PRODUTO;
	private String ENDERECO;

	public CompraProduto(String AMBIENTE, String EMAIL, String SENHA, String PRODUTO, String ENDERECO) {
		this.AMBIENTE = AMBIENTE;
		this.EMAIL = EMAIL;
		this.SENHA = SENHA;
		this.PRODUTO = PRODUTO;
		this.ENDERECO = ENDERECO;
	}

	@Before
	public void setUp() {
		WebDriverManager webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
		report = new Report();
		environment = new ObjectEnvironment(Arrays.asList(Spreadsheet.getStringSpreadsheetAmbiente()));
		compra = new CompraProdutoAction(driver, reportHTML, report);

		reportHTML.test = ExtentReport.extent.startTest(AMBIENTE);
		reportHTML.test.log(LogStatus.INFO, "Iniciando a execucao do teste: " + TEST_CASE_NAME + " ...");
	}

	@Parameterized.Parameters
	public static Collection<Object> input() {
		Object[] objeto = Spreadsheet.getStringSpreadsheetCenario(ConstantsSpreadsheet.EXCEL_CT01);
		return Arrays.asList(objeto);
	}

	@Test
	public void RodaTest() throws InterruptedException {

		compra.AcessarSite();
		compra.escolherProduto(PRODUTO);
		compra.addNoCarrinho();
		compra.fazerCheckout();
		compra.realizarLogin(EMAIL, SENHA);
		compra.validaEndereco(ENDERECO);
		compra.seguirComCheckout();
		compra.Pagamento();
		compra.confirmaPedido();
		compra.realizarLogOut();
		
	}

	@After
	public void fim() throws Exception {
		driver.close();
		ExtentReport.extent.flush();
		report.createReportDoc("CompraProdutoPergunta01");
	}
}
