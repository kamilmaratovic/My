package DataDrivenTesting;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtilityFile {

    private FileInputStream fis;
    private FileOutputStream fos;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFCell cell;
    private XSSFRow row;
    private CellStyle style;
    String path = null;

    public static void main(String[] args) throws IOException {
        ExcelUtilityFile file = new ExcelUtilityFile("/Users/kamilmaratovic/IdeaProjects/My/dataStorage/credentials.xlsx");
        int row = file.getRowCount("Kamil");
        int cell = file.getCellCount("Kamil", row);
        System.out.println(row);
    }

    public ExcelUtilityFile(String path) {
        this.path = path;
    }

    public int getRowCount (String sheetName) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        workbook.close();
        fis.close();
        System.out.println(rowCount);
//        workbook = new XSSFWorkbook(path);
//        sheet = workbook.getSheet(sheetName);
//        int rowCount = sheet.getPhysicalNumberOfRows();

        return rowCount;
    }

    public int getCellCount (String sheetName, int rowNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getPhysicalNumberOfCells();
        workbook.close();
        fis.close();

        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        }catch (Exception e){
            data = "";
        }
        workbook.close();
        fis.close();
        return data;
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        cell.setCellValue(data);

        fos = new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
    }

    public void fillGreenColor (String sheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();

    }

    public void fillRedColor (String sheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();

    }


}
