package com.mabulu.project.util;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class CSVUtils {
	/*
	* <strong>
	* Convert Excel spreadsheet to CSV. 
	* Works for <strong>Excel 97-2003 Workbook (<em>.xls)
	* </strong> and <strong>Excel Workbook (</em>.xlsx)</strong>.
	* Does not work for the <strong>XML Spreadsheet 2003 (*.xml)</strong> 
	* format produced by BIRT.
	* @param fileName
	* @throws InvalidFormatException
	* @throws IOException
	*
	* @see http://bigsnowball.com/content/convert-excel-xlsx-and-xls-csv
	*/
	public static void convertExcelToCSV(String fileName) 
			throws InvalidFormatException, IOException {

		BufferedWriter output = new BufferedWriter(new FileWriter(fileName.substring(0, fileName.lastIndexOf(".")) + ".csv"));

		InputStream is = new FileInputStream(fileName);

		Workbook wb = WorkbookFactory.create(is);

		Sheet sheet = wb.getSheetAt(0);

		// hopefully the first row is a header and has a full compliment of
		// cells, else you'll have to pass in a max (yuck)
		int maxColumns = sheet.getRow(0).getLastCellNum();

		for (Row row : sheet) {

			// row.getFirstCellNum() and row.getLastCellNum() don't return the
			// first and last index when the first or last column is blank
			int minCol = 0; // row.getFirstCellNum()
			int maxCol = maxColumns; // row.getLastCellNum()

			for (int i = minCol; i < maxCol; i++) {

				Cell cell = row.getCell(i);
				String buf = "";
				if (i > 0) {
					buf = ",";
				}

				if (cell == null) {
					output.write(buf);
				} else {

					String v = null;

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						v = cell.getRichStringCellValue().getString();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							v = cell.getDateCellValue().toString();
						} else {
							v = String.valueOf(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						v = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						v = cell.getCellFormula();
						break;
					default:
					}

					if (v != null) {
						buf = buf + toCSV(v);
					}
					output.write(buf);
				}
			}

			output.write("\n");
		}
		is.close();
		output.close();
	}


	/*
	 * </strong>
	 * Escape the given value for output to a CSV file. 
	 * Assumes the value does not have a double quote wrapper.
	 * @return
	 */
	public static String toCSV(String value) {

		String v = null;
		boolean doWrap = false;

		if (value != null) {

			v = value;

			if (v.contains("\"")) {
				v = v.replace("\"", "\"\""); // escape embedded double quotes
				doWrap = true;
			}

			if (v.contains(",") || v.contains("\n")) {
				doWrap = true;
			}

			if (doWrap) {
				v = "\"" + v + "\""; // wrap with double quotes to hide the comma
			}
		}

		return v;
	}
}
