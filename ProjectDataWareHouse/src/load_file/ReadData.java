package load_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {
	public static final int C_STT = 0;
	public static final int C_LAST_NAME = 1;
	public static final int C_FIRST_NAME = 2;
	public static final int C_GENDER = 3;
	public static final int C_DAY_BIRTH = 4;
	public static final int C_PERSONAL = 5;
	public static final int C_ADRESS = 6;
	public static final int C_EMAIL = 7;
	public static final int C_PHONE = 8;
	public static final int C_CMND = 9;

	public static ArrayList<InformationPerson> readData(String dir) {
		ArrayList<InformationPerson> result = new ArrayList<>();
		try {

			InputStream input = new FileInputStream(new File(dir));

			Workbook wr = getWorkbook(input, dir);
			Sheet sheet = wr.getSheetAt(0);

			Iterator<Row> rowIt = sheet.iterator();
			while (rowIt.hasNext()) {
				Row row = rowIt.next();
				if (row.getRowNum() == 0) {
					continue;
				}

				Iterator<Cell> cellIt = row.iterator();
				InformationPerson inPerson = new InformationPerson();

				while (cellIt.hasNext()) {
					Cell cell = cellIt.next();
					Object cellValue = getCellValue(cell);

					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case C_STT:
						inPerson.setStt(new BigDecimal((double) cellValue).intValue());
						break;
					case C_LAST_NAME:
						inPerson.setlName((String) getCellValue(cell));
						break;
					case C_FIRST_NAME:
						inPerson.setfName((String) getCellValue(cell));
						break;
					case C_GENDER:
						inPerson.setGender((String) getCellValue(cell));
						break;
					case C_DAY_BIRTH:
//						inPerson.setDateBirth((String) getCellValue(cell));
						break;
					case C_PERSONAL:
						inPerson.setPersonal((String) getCellValue(cell));
						break;
					case C_ADRESS:
						inPerson.setAddress((String) getCellValue(cell));
						break;
					case C_EMAIL:
						inPerson.setEmail((String) getCellValue(cell));
						break;
					case C_PHONE:
						inPerson.setPhone(new BigDecimal((double) cellValue).intValue());
						break;
					case C_CMND:
						inPerson.setCMND(new BigDecimal((double) cellValue).intValue());
						break;
					default:
						break;
					}

				}
				result.add(inPerson);

			}
			wr.close();
			input.close();

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	private static Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellTypeEnum();
		Object cellValue = null;
		switch (cellType) {
		case BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case FORMULA:
			Workbook workbook = cell.getSheet().getWorkbook();
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			cellValue = evaluator.evaluate(cell).getNumberValue();
			break;
		case NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case _NONE:
		case BLANK:
		case ERROR:
			break;
		default:
			break;
		}

		return cellValue;
	}

	public static void main(String[] args) {

		ArrayList<InformationPerson> ls = readData(
				"E:\\Tai_Lieu\\HK2-----3\\DatawareHouse\\DataFeedSpecification_17130110\\DataFeedSpecification_17130110.xlsx");
		for (InformationPerson ifP : ls) {
			System.out.println(ifP);
		}

	}
}
