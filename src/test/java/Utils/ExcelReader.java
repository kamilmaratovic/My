package Utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelReader {

    public static void main(String[] args) throws IOException {
        String projectPath = System.getProperty("user.dir");
        ExcelReader reader = new ExcelReader(projectPath+"/excel/excelFile.xlsx", "Kamil");
        reader.getColumnCount();


    }
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;

    public ExcelReader(String excelPath, String sheetName) throws IOException {
        workbook = new XSSFWorkbook(excelPath);
        sheet = workbook.getSheet(sheetName);
    }


    public int getRowCount() throws IOException {
        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println("Num of rows: "+rowCount);
         return rowCount;
    }

    public int getColumnCount() throws IOException {
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
        System.out.println("Num of columns: "+columnCount);
        return columnCount;
    }

    public String getDataString(int rowNum, int cellNum) {
        String data = sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
        return data;
    }

    public void getDataNumber() throws IOException {

        double number = sheet.getRow(1).getCell(2).getNumericCellValue();
        System.out.println(number);
    }

}
