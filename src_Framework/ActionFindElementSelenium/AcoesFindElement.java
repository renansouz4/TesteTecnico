package ActionFindElementSelenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import Report.ExtentReport;
import Report.Report;

public class AcoesFindElement {
	
	/**
	 * Método responsável para a abertura de uma URL. Utiliza a url para abrir uma
	 * página web.
	 */

	public static void openURL(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	/**
	 * Recuperar o text da tela.
	 * 
	 * @return
	 */
	public static String getText(WebDriver driver, By by) {
		WebElement element = null;
		element = findElement(driver, by);
		String elementText = element.getText();

		return elementText;
	}
	
	public static String getInnerText(WebDriver driver, By by) {
        WebElement element = null;
        element = findElement(driver, by);
        String elementText = element.getAttribute("innerText");
        return elementText;
    }

	public static String getValue(WebDriver driver, By by) {
		WebElement element = null;
		element = findElement(driver, by);
		String elementText = element.getAttribute("value");

		return elementText;
	}
	
	public static String getAtributo(WebDriver driver, String atributo, By by) {
		WebElement element = null;
		element = findElement(driver, by);
		String elementText = element.getAttribute(atributo);

		return elementText;
	}	
	
	public void subirBarra(WebDriver driver, By by) throws InterruptedException {
		WebElement element = null;
		element = findElement(driver, by);

		element.sendKeys(Keys.ARROW_UP);
		element.sendKeys(Keys.ARROW_UP);
		Thread.sleep(1000);
	}
	
	public void descerBarra(WebDriver driver, int repeticao) throws InterruptedException {

		Robot rbt;
		
		try {
			rbt = new Robot();
			
			for (int i = 0; i <= repeticao ; i++) {
				
				rbt.keyPress(KeyEvent.VK_DOWN);
				rbt.keyRelease(KeyEvent.VK_DOWN);
			}
		
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método responsável pelo preenchimento de campos do tipo INPUT. Utiliza o By
	 * para localizar o componente.
	 * 
	 * @return Retorna o componente alterado.
	 */
	public WebElement input(WebDriver driver, By by, String valor) {

		int timeout = 3;
		while (!(timeout <= 0)) {
			timeout--;
			try {

				WebDriverWait wait = new WebDriverWait(driver, 10);

				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));

				WebElement element = findElement(driver, by);
				element.click();
				element.clear();
				element.sendKeys(valor.trim());
				return element;

			} catch (Exception e) {
				System.out.println("Erro na acao de input do framework");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	public WebElement inputClear(WebDriver driver, By by, String valor) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));

		WebElement element = findElement(driver, by);
		element.click();
		element.clear();
		element.sendKeys(valor);
		return element;

	}

	/**
	 * Método responsável pelo preenchimento de campos do tipo SELECT. Utiliza o By
	 * para localizar o componente.
	 * 
	 * @return
	 */
	public WebElement inputSelect(WebDriver driver, By by, String opcao) {
		int timeout = 10;
		while (!(timeout <= 0)) {
			timeout--;
			try {
				WebElement element = null;
				Select select = null;
				element = findElement(driver, by);
				select = new Select(element);
				select.selectByVisibleText(opcao);
				return element;

			} catch (Exception e) {
				System.out.println(e.getCause());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Método responsável pela execução do clique no componente. Utiliza o By
	 * informado para localizar o componente.
	 * 
	 * @return Retorna o componente clicado.
	 * @throws InterruptedException
	 */
	public static void click(WebDriver driver, By by) {

		int timeout = 3;
		while (!(timeout <= 0)) {
			try {
				WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
				Actions actions = new Actions(driver);
				WebElement mainMenu = element;
				actions.moveToElement(mainMenu).build().perform();
				element.click();
				return;
			} catch (Exception e) {
				System.err.println("Erro na acao de click");
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}

			timeout--;
		}
		Assert.fail("Erro na acao de click do framework");
	}		

	/**
	 * Método responsável pela localização do componente. Utiliza o By informado
	 * para localizar o componente. Capaz de ignorar erros do tipo
	 * NoSuchElementException e StaleElementReferenceException
	 * 
	 * @return O Elemento buscado
	 */
	public static WebElement findElement(WebDriver driver, final By by) {

		int i = 1;
		int Constantes_MAX_SEGUNDOS = 3;
		WebElement element = null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(TimeoutException.class);
		try {
			while (element == null && i < Constantes_MAX_SEGUNDOS) {
				try {
					if (null != ExpectedConditions.visibilityOfElementLocated(by)) {
						element = wait.until(new Function<WebDriver, WebElement>() {
							public WebElement apply(WebDriver driver) {
								return driver.findElement(by);
							}
						});
						if (element.getText().equals("Nenhum registro encontrado")) {
							element = null;
						}
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					i++;
					element = null;
					continue;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.assertNotNull("Elemento não foi encontrado", ex);
		}
		return element;
	}	

	public static void radio_Select(WebElement Radio) {
		boolean checkstatus;
		checkstatus = Radio.isSelected();
		if (checkstatus == true) {
			System.out.println("RadioButton is already checked");
		} else {
			Radio.click();
			System.out.println("Selected the Radiobutton");
		}
	}

	public static void checkbox_Unchecking(WebElement checkbox) {
		boolean checkstatus;
		checkstatus = checkbox.isSelected();
		if (checkstatus == true) {
			checkbox.click();
			System.out.println("Checkbox is unchecked");
		} else {
			System.out.println("Checkbox is already unchecked");
		}
	}

	public static void radio_Deselect(WebElement Radio) {
		boolean checkstatus;
		checkstatus = Radio.isSelected();
		if (checkstatus == true) {
			Radio.click();
			System.out.println("Radio Button is deselected");
		} else {
			System.out.println("Radio Button was already Deselected");
		}
	}
	
	public static void doubleClick(WebDriver driver, WebElement doubleclickonWebElement) throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.doubleClick(doubleclickonWebElement).perform();
		Thread.sleep(2000);
	}	

	public static void selectElementVisibleText(WebDriver driver, By by, String Name) {
		WebElement element = driver.findElement(by);
		Select selectitem = new Select(element);
		selectitem.selectByVisibleText(Name);
	}

	public static void selectElementVisibleTextS(WebDriver driver, By by, By by2, String Name) {
		WebElement element = driver.findElement(new ByChained(by, by2));
		Select selectitem = new Select(element);
		selectitem.selectByVisibleText(Name);
	}

	public static void selectElementByValueMethod(WebDriver driver, By by, String value) {
		WebElement element = driver.findElement(by);
		Select selectitem = new Select(element);
		selectitem.selectByValue(value);
	}

	public static void selectElementByIndexMethod(WebDriver driver, By by, int index) {
		WebElement elementIndex = driver.findElement(by);
		Select selectitem = new Select(elementIndex);
		selectitem.selectByIndex(index);
	}	

	
	public void scroll(WebDriver driver, int ammount) {
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("window.scrollBy(0," + ammount + ")", "");
	}

	public static void clickCheckbox(WebDriver driver, By by, String valorSouN) {

		List<WebElement> allCheckbox = driver.findElements(by);

		for (WebElement checkbox : allCheckbox) {
			if (checkbox.isSelected() == true) {

				System.out.println("RadioButton is already checked");

			} else {
				if (checkbox.getAttribute("value").equals(valorSouN)) {
					checkbox.click();
					System.out.println("Selected the Radiobutton");
				}
			}
		}
	}		
	
	/**
	 * Verifia se um elemento existe.
	 * @param driver
	 * @param by
	 * @return
	 */
	public int exist(WebDriver driver, By by) {
    	int element  = driver.findElements(by).size(); 	
    	
    	if (element == 0) {
    		System.out.println(by + ", elemento NAO detectado");
    	} else {
    		System.out.println(by + ", " + element + " elemento(s) detectado(s)");
    	}
	
    	return element;
    }
	
	/**
	 * Semelhante ao metodo exist(), porem, passando tambem um tempo em segundos para que o elemento apareça em tela.
	 * @param driver
	 * @param by
	 * @param time
	 * @return
	 */
	public int exist(WebDriver driver, By by, int time) {
		
		try {
			WebElement aguardaElement = (new WebDriverWait(driver, time)).until(ExpectedConditions.presenceOfElementLocated(by));			

			System.out.println("Elemento " + by + ", detectado");
			return 1;
		} catch (Exception e) {
			
	    	System.err.println("Elemento " + by + ",  NAO detectado");	    	
	    	return 0;
		}
	}
	
	/**
	 * Monta um xpath que contenha todos os nomes passado no parâmetro "texto", sendo necessário informar a tag desejada(ex: *, span, div, input) e o atributo desejato(ex: text, value, ng-click, name, id)    
	 * @param texto
	 * @param tag
	 * @param atributo
	 * @return
	 */
	public static String montarXpath(String texto, String tag, String atributo) {
    	String[] splitCoiso = texto.split(" ");
    	int tamanhoSplitCoiso = splitCoiso.length;
    	String xpathson = "//"+ tag +"[";
    	for (int i = 0; i <= tamanhoSplitCoiso-1; i++) {
			if (i == tamanhoSplitCoiso-1) {
				if(atributo.equalsIgnoreCase("text")) {
					xpathson = xpathson + "contains(text(), '"+ splitCoiso[i] +"')]";					
				} else {
					xpathson = xpathson + "contains(@"+ atributo +", '"+ splitCoiso[i] +"')]";
				}			
			
			} else {
				if(atributo.equalsIgnoreCase("text")) {
					xpathson = xpathson + "contains(text(), '"+ splitCoiso[i] +"')][";					
				} else {
					xpathson = xpathson + "contains(@"+ atributo +", '"+ splitCoiso[i] +"')][";
				}
			}
		}
    	return xpathson;
    }
	
	public static String getChecked(WebDriver driver, By by) {
		WebElement element = null;
		element = findElement(driver, by);
		String elementText = element.getAttribute("checked").toString();

		return elementText;
	}
	
	/**
	 * Converte a string em Double com o valor desejato e soma o segundo
	 * parâmetro(int) ao Double recem convertidos. retornando o valor final como
	 * String.
	 * 
	 * @param valor
	 * @param adicional
	 * @return
	 */
	public static String converteESoma(String valor, int adicional) {
		valor = valor.replace(",", ".");
		Double valorConvertido = Double.parseDouble(valor);
		valorConvertido = valorConvertido + adicional;
		valor = String.valueOf(valorConvertido);
		String valorFinal = valor.replace(".", ",");

		return valorFinal;
	}

	public static String converteESubtrai(String valor, int desconto) {
		valor = valor.replace(",", ".");
		Double valorConvertido = Double.parseDouble(valor);
		valorConvertido = valorConvertido - desconto;
		valor = String.valueOf(valorConvertido);
		String valorFinal = valor.replace(".", ",");

		return valorFinal;
	}

	public static String converteIntESoma(String valor, int adicional) {
		int valorConvertido = Integer.parseInt(valor);
		valorConvertido = valorConvertido + adicional;
		valor = Integer.toString(valorConvertido);
		return valor;
	}	
	
	public static void sleep(long tempo) throws InterruptedException {
		Thread.sleep(tempo);
	}
	

}
