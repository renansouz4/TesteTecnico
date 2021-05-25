package PageObjects;

import org.openqa.selenium.By;

import ActionFindElementSelenium.AcoesFindElement;

public class PesquisaSmartPhonePageObject {
	public static final By pesquisaProduto = By.xpath("//*[@id='h_search-input']");
	public static final By btnPesquisar = By.xpath("//*[@id='h_search-btn']");
	
	public static final By clicarProduto = By.xpath("(//*[@class='src__Text-sc-154pg0p-0 src__Name-sc-1k0ejj6-3 dSRUrl'])[1]");
	public static final By preco = By.xpath("//*[@class='src__BestPrice-sc-1jvw02c-5 cBWOIB priceSales']");
	public static final By cashBack = By.xpath("(//*[@class='cashback__Green-j5qxid-0 knERWS'])[1]");
	
}
