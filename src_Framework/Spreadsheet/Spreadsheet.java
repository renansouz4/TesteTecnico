package Spreadsheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ConstantsSuiteTests.ConstantsSpreadsheet;

public class Spreadsheet {

	@SuppressWarnings({ "null", })
	public static Object[] getStringSpreadsheetAmbiente() {

		List<Object[]> array = new ArrayList<Object[]>();
		List<Object> aux = new ArrayList<Object>();

		FileInputStream arquivo = null;
		try {
			arquivo = new FileInputStream(new File(ConstantsSpreadsheet.EXCEL_AMBIENTE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheetAlunos = workbook.getSheetAt(0);

		int totalColunas = sheetAlunos.getRow(0).getPhysicalNumberOfCells();
		int totalLinha = sheetAlunos.getPhysicalNumberOfRows() - 2;

		System.out.println("linha " + totalLinha + " coluna  " + totalColunas);
		int cont = 1;
		for (int ii = 1; ii < totalColunas; ii++) {
			Iterator<Row> rowIterator = sheetAlunos.iterator();
			Row row = rowIterator.next();
			Row row1 = rowIterator.next();
			Cell celulaAtual = row1.getCell(cont);
			System.out.println("AMBIENTE_USE :" + celulaAtual.getStringCellValue());

			if (celulaAtual.getStringCellValue().equals("SIM")) {

				aux = new ArrayList<Object>();
				Cell valorcelula;
				int cont1 = 0;
				for (int i = 2; i < totalLinha; i++) {
					Row row2 = rowIterator.next();

					java.util.Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row2.cellIterator();

					Cell cenariosPlanilha = row2.getCell(0);

					System.out.println("AMBIENTE : " + row2.getCell(cont).getStringCellValue());
					aux.add(row2.getCell(cont).getStringCellValue());
					cont1++;
				}
				array.add(aux.toArray());

			}
			cont++;
		}

		try {
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return aux.toArray();
	}

	@SuppressWarnings({ "null", })
	public static Object[] getStringSpreadsheetCenario(String planilha) {

		List<Object[]> array = new ArrayList<Object[]>();
		List<Object> aux = new ArrayList<Object>();

		FileInputStream arquivo = null;
		try {
			arquivo = new FileInputStream(new File(planilha));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheetAlunos = workbook.getSheetAt(0);

		int totalColunas = sheetAlunos.getRow(0).getPhysicalNumberOfCells();
		int totalLinha = sheetAlunos.getPhysicalNumberOfRows();

		System.out.println("linha " + totalLinha + " coluna  " + totalColunas);
		int cont = 1;
		for (int ii = 1; ii < totalColunas; ii++) {
			Iterator<Row> rowIterator = sheetAlunos.iterator();
			Row row = rowIterator.next();
			Row row1 = rowIterator.next();
			Cell celulaAtual = row1.getCell(cont);
			System.out.println("AMBIENTE_USE :" + celulaAtual.getStringCellValue());

			if (celulaAtual.getStringCellValue().equals("SIM")) {

				aux = new ArrayList<Object>();
				Cell valorcelula;
				int cont1 = 0;
				for (int i = 2; i < totalLinha; i++) {
					Row row2 = rowIterator.next();

					java.util.Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row2.cellIterator();

					Cell cenariosPlanilha = row2.getCell(0);

					System.out.println("AMBIENTE : " + row2.getCell(cont).getStringCellValue());
					aux.add(row2.getCell(cont).getStringCellValue());
					cont1++;
				}
				array.add(aux.toArray());
			}

			cont++;
		}

		try {
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array.toArray();
	}

	public void atualizarPlanilha(String planilha, String TesteCaseId, String statusTest) {
		FileInputStream arquivo = null;
		try {
			arquivo = new FileInputStream(new File(planilha));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HSSFSheet sheetAlunos = workbook.getSheetAt(0);

		int totallinhas = 1;
		int totalcolunas = sheetAlunos.getRow(0).getPhysicalNumberOfCells();

		System.out.println("linha " + totallinhas + " coluna  " + totalcolunas);

		java.util.Iterator<Row> rowIterator = sheetAlunos.iterator();

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			for (int ii = 0; ii < totallinhas; ii++) {

				int status = ii;
				org.apache.poi.ss.usermodel.Cell celulaAtualCT = row.getCell(ii);
				Row row2 = rowIterator.next();
				status = status + 1;
				org.apache.poi.ss.usermodel.Cell celulaAtualSTATUS = row2.getCell(status);

				System.out.println(celulaAtualCT.getStringCellValue());

				if (celulaAtualCT.getStringCellValue().equals("TEST_CASE_ID")) {

					System.out.println("status " + celulaAtualSTATUS.getStringCellValue());
					System.out.println("ct " + celulaAtualCT.getStringCellValue());

					for (int i = 0; i < totalcolunas; i++) {
						org.apache.poi.ss.usermodel.Cell celulaAtualCTs = row.getCell(i);
						org.apache.poi.ss.usermodel.Cell celulaAtualSTATUSs = row2.getCell(i);

						if (celulaAtualCTs.getStringCellValue().equals(TesteCaseId)) {
							celulaAtualSTATUSs.setCellValue(statusTest);
							FileOutputStream output = null;
							try {
								output = new FileOutputStream(new File((planilha)));
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							try {
								workbook.write(output);
							} catch (IOException e) {
								e.printStackTrace();
							}
							try {
								output.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
							return;

						}
					}
				}

			}
		}
		return;
	}

}
