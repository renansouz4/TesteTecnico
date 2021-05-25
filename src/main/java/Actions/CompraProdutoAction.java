package Actions;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import ActionFindElementSelenium.AcoesFindElement;
import ConstantsSuiteTests.ConstanteUrlBroweser;
import PageObjects.CompraProdutoPageObject;
import Report.ExtentReport;
import Report.Report;

public class CompraProdutoAction extends AcoesFindElement {

	String URL = ConstanteUrlBroweser.URL_SITE;
	private WebDriver driver;
	private ExtentReport reportHTML;
	private Report report;

	/**
	 * - Construtor da Class.
	 */
	public CompraProdutoAction(WebDriver driver, ExtentReport reportHTML, Report report) {
		this.driver = driver;
		this.reportHTML = reportHTML;
		this.report = report;
	}

	public void reportPass(String texto) throws InterruptedException {
		System.out.println(texto);
		reportHTML.test.log(LogStatus.PASS, texto);
		reportHTML.test.log(LogStatus.PASS, reportHTML.test.addScreenCapture(report.capturar()));
		report.capturarText(texto);
		sleep(1000);
	}
	

	public void reportFail(String texto) throws InterruptedException {
		System.out.println(texto);
		reportHTML.test.log(LogStatus.FAIL, texto);
		reportHTML.test.log(LogStatus.FAIL, reportHTML.test.addScreenCapture(report.capturar()));
		report.capturarText(texto);
		sleep(1000);
		Assert.fail();
	}

	public void AcessarSite() {
		openURL(driver, URL);
	}

	public void escolherProduto(String PRODUTO) throws InterruptedException {
		try {
			click(driver, CompraProdutoPageObject.produto(PRODUTO));
			reportPass("Escolhido produto: " + PRODUTO );
			
		} catch (Exception e) {
			reportFail("Falha ao tentar exibir ou escolher quantidade do produto: " + PRODUTO + ". " + e);
		}
	}
	
	public void addNoCarrinho() throws InterruptedException {
		try {
			click(driver, CompraProdutoPageObject.btnAddNoCarrinho);
			sleep(3000);
			reportPass("Item adicionado ao carrinho");
		} catch (Exception e) {
			reportFail("Falha ao adicionar item ao carrinho. " + e);
		}
	}
	
	public void fazerCheckout() throws InterruptedException {
		try {
			click(driver, CompraProdutoPageObject.btnFazerCheckout);
			sleep(2000);
			reportPass("Resumo do pedido");
			click(driver, CompraProdutoPageObject.btnContinuarCheckOut);
		} catch (Exception e) {
			reportFail("Falha ao exibir resumo do pedido. " + e);
		}
	}
	
	public void realizarLogin(String EMAIL, String SENHA) throws InterruptedException {
		try {
			input(driver, CompraProdutoPageObject.email, EMAIL);
			input(driver, CompraProdutoPageObject.senha, SENHA);
			click(driver, CompraProdutoPageObject.btnLogin);
			
		} catch (Exception e) {
			reportFail("Falha ao realizar login. " + e);
		}		
	}
	
	public void validaEndereco(String ENDERECO) throws InterruptedException {
		try {
			String enderecoEmTela = getText(driver, CompraProdutoPageObject.getEndereco);
			assertEquals(enderecoEmTela, ENDERECO);
			reportPass("Endereco " + enderecoEmTela + ", validado com sucesso!");			
			
		} catch (Exception e) {
			reportFail("Falha ao validar endereco. " + e);
		}
	}
	
	public void seguirComCheckout() throws InterruptedException {
		click(driver, CompraProdutoPageObject.btnSeguirComCheckout);		
		click(driver, CompraProdutoPageObject.checkTermoServico);
		click(driver, CompraProdutoPageObject.btnSeguirParaPagamento);
		sleep(2000);
	}
	
	public void Pagamento() throws InterruptedException {
		try {
			click(driver, CompraProdutoPageObject.btnTransBancaria);
			reportPass("Escolhido forma de pagamento: Transferencia bancaria.");			
			
		} catch (Exception e) {
			reportFail("Falha ao tentar escolher a forma de pagamento. " + e);
		}		
	}
	
	public void confirmaPedido() throws InterruptedException {
		try {
			click(driver, CompraProdutoPageObject.btnConfirmarPedido);
			reportPass("Dados do pedido.");
		} catch (Exception e) {
			reportFail("Falha ao tentar gerar pedido. " + e);
		}
	}
	
	public void realizarLogOut() throws InterruptedException {
		click(driver, CompraProdutoPageObject.logOut);
		sleep(2000);
	}		

}
