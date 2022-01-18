package excelutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelUtils {
	public static String[][] readExcel(String fileName, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(new File(fileName));
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		int rows = 0;
		int columns = 0;

		for (int i = 0;; i++) {
			if(sheet.getRow(i) == null) {
				rows = i;
				break;
			}
		}
		for (int i = 0;; i++) {
			if(sheet.getRow(0).getCell(i) == null) {
				columns = i;
				break;
			}
		}

		String[][] array = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cell = sheet.getRow(i).getCell(j);
				if (cell.getCellTypeEnum() == CellType.STRING)
					array[i][j] = cell.getStringCellValue();
				else if (cell.getCellTypeEnum() == CellType.NUMERIC)
					array[i][j] = String.valueOf(cell.getNumericCellValue());
			}
		}

		workbook.close();
		return array;
	}
}
