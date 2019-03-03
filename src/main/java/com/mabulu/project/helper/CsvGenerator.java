package com.mabulu.project.helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mabulu.project.domain.CsvFileDomain;
import com.mabulu.project.util.CSVUtils;

@Component
public class CsvGenerator {

	@Value("${csvFileFolder}")
	private String csvFileFolder;

	private Logger log = LoggerFactory.getLogger(CsvGenerator.class);

	public void createFundNamesCsv(Set<String> portFolioNames, List<CsvFileDomain> csvFileDomains)
			throws InvalidFormatException, IOException {
		String FILE_NAME = csvFileFolder + "MyFunds.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
		int rowNum = 0;
		Row row = sheet.createRow(rowNum++);

		int portFolioRowNumber = 1;
		for (int i = 0; i < portFolioNames.toArray().length; i++) {
			String name = (String) portFolioNames.toArray()[i];
			Cell cell = row.createCell(i);
			cell.setCellValue(name);
			int counter = 0;

			for (CsvFileDomain csvFileDomain : csvFileDomains) {

				if (Objects.equals(name, csvFileDomain.getPortFolioName())) {
					if (counter == 0) {
						portFolioRowNumber = 1;
					}
					Row rowFundName = sheet.getRow(portFolioRowNumber + 1);
					if (rowFundName == null) {
						Row rowFundNameNew = sheet.createRow(portFolioRowNumber++);
						Cell cellFundNames = rowFundNameNew.createCell(i);
						cellFundNames.setCellValue(csvFileDomain.getFundName());
						counter++;
					} else {
						Row rowFundNameExisting = sheet.getRow(portFolioRowNumber++);
						Cell cellFundNames = rowFundNameExisting.createCell(i);
						cellFundNames.setCellValue(csvFileDomain.getFundName());
						counter++;
					}

				}

			}

			counter = 0;

		}

		FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
		workbook.write(outputStream);
		workbook.close();

		CSVUtils.convertExcelToCSV(FILE_NAME);

	}

	public void createFundQuantityCsv(Set<String> portFolioNames, List<CsvFileDomain> csvFileDomains)
			throws InvalidFormatException, IOException {
		String FILE_NAME = csvFileFolder + "MyQuant.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Datatypes in Java");

		int rowNum = 0;
		log.debug("Creating excel");

		Row row = sheet.createRow(rowNum++);

		int portFolioRowNumber = 1;
		for (int i = 0; i < portFolioNames.toArray().length; i++) {
			String name = (String) portFolioNames.toArray()[i];
			Cell cell = row.createCell(i);
			cell.setCellValue(name);
			int counter = 0;

			for (CsvFileDomain csvFileDomain : csvFileDomains) {

				if (Objects.equals(name, csvFileDomain.getPortFolioName())) {
					if (counter == 0) {
						portFolioRowNumber = 1;
					}
					Row rowFundName = sheet.getRow(portFolioRowNumber + 1);
					if (rowFundName == null) {
						Row rowFundNameNew = sheet.createRow(portFolioRowNumber++);
						Cell cellFundNames = rowFundNameNew.createCell(i);
						cellFundNames.setCellValue(csvFileDomain.getQuantity());
						counter++;
					} else {
						Row rowFundNameExisting = sheet.getRow(portFolioRowNumber++);
						Cell cellFundNames = rowFundNameExisting.createCell(i);
						cellFundNames.setCellValue(csvFileDomain.getQuantity());
						counter++;
					}

				}

			}

			counter = 0;

		}

		FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
		workbook.write(outputStream);
		workbook.close();
		CSVUtils.convertExcelToCSV(FILE_NAME);

	}

}
