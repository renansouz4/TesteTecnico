package Report;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

import com.itextpdf.text.BadElementException;

import ConstantsFramework.Constants_Framework;
import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

public class Report {

	WebDriver driver;
	String pathPrint = Constants_Framework.PRINTS_PATH;
	ArrayList<String> prints;
	ArrayList<String> texts;

	/**
	 * Construtor da classe Report
	 * 
	 * @param Webdriver driver
	 * @author renan.augusto.souza
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Report() {
		this.prints = new ArrayList();
		this.texts = new ArrayList();
	}

	/**
	 * Método para capturar prints que serão utilizados nos relatórios DOC e PDF.
	 * 
	 * @return void
	 * @author renan.augusto.souza
	 * @return
	 * @return
	 * @throws AWTException
	 * @throws IOException
	 * @throws BadElementException
	 */
	public String capturar() {
		String dateDay = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime());
		String nomePrint = "print";

		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		try {
			boolean scrFile2 = ImageIO.write(screenShot, "JPG",	new File(pathPrint + "_" + dateDay + " " + nomePrint + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		prints.add(pathPrint + "_" + dateDay + " " + nomePrint + ".jpg");
		String nomeImagem = pathPrint + "_" + dateDay + " " + nomePrint + ".jpg";
		screenShot = null;
		dateDay = null;
		return nomeImagem;
	}


	public void capturarText(String txt) {
		texts.add(txt);

	}

	/**
	 * Método para gerar o relatório DOC..
	 * 
	 * @return void
	 * @author renan.augusto.souza
	 */
	public void createReportDoc(String nomeEvidencia) throws Exception {
				
		@SuppressWarnings("unused")
		Doc report = new Doc(prints, texts, nomeEvidencia);
	}

}

