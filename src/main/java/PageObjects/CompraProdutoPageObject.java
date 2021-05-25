package PageObjects;

import org.openqa.selenium.By;

public class CompraProdutoPageObject {

	public static final By verificaCliente = By.xpath("//*[@id=\"table\"]/tbody/tr/td[1]/a");
	

	public static By produto(String produto) {
		return By.xpath("(//*[@title='"+ produto +"'][@itemprop='image'])[1]");
	}
	
	public static final By btnAddNoCarrinho = By.xpath("//button[@name='Submit']");
	public static final By btnFazerCheckout = By.xpath("//a[@class='btn btn-default button button-medium']");
	
	public static final By btnContinuarCheckOut = By.xpath("(//a[@title='Proceed to checkout'])[2]");
	
	public static final By email = By.xpath("//*[@id='email']");
	public static final By senha = By.xpath("//*[@id='passwd']");
	public static final By btnLogin = By.xpath("//*[@id='SubmitLogin']");
	
	public static final By getEndereco = By.xpath("(//*[@class='address_address1 address_address2'])[1]");
	
	public static final By btnSeguirComCheckout = By.xpath("//*[@name='processAddress']");
	public static final By checkTermoServico = By.xpath("//*[@id='uniform-cgv']");
	public static final By btnSeguirParaPagamento = By.xpath("//*[@name='processCarrier']");
	
	public static final By btnTransBancaria = By.xpath("//*[@class='bankwire']");
	
	public static final By btnConfirmarPedido = By.xpath("//*[@class='button btn btn-default button-medium']");
	
	public static final By logOut = By.xpath("//*[@class='logout']");

}
