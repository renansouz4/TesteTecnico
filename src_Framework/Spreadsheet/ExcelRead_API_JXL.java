package Spreadsheet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ConstantsFramework.Constants_Framework;
import ManagersDriver.FileReaderManager;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelRead_API_JXL {

	@SuppressWarnings({ "null", })
	public static Object[] getArray(final String EXCEL_FILE_LOCATION) {
		List<Object[]> array = new ArrayList<Object[]>();
		List<Object> aux = new ArrayList<Object>();

		Workbook workbook = null;

		try {
			workbook = Workbook.getWorkbook(new File(EXCEL_FILE_LOCATION));
			int linha = 1;
			int linha2 = 3;
			int coluna = 1;
			Sheet sheet = workbook.getSheet(0);
			Cell cell1 = sheet.getCell(coluna, linha);

			while (cell1.getContents() != "") {
				aux = new ArrayList<Object>();

				Cell cellExecutar = sheet.getCell(coluna, linha2);
				if (cellExecutar.getContents().equals("EXECUTAR")) {
					while (cell1.getContents() != "") {
						aux.add(cell1.getContents());
						linha++;
						cell1 = sheet.getCell(coluna, linha);
					}
					array.add(aux.toArray(new Object[0]));
				}
					coluna++;
					linha = 1;
					cell1 = sheet.getCell(coluna, linha);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				workbook.close();
			}
		}
		return array.toArray(new Object[0][0]);
	}

}