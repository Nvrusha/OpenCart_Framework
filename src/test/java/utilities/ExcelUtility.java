package utilities;

// Import I/O classes for reading/writing files
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Import Apache POI classes for Excel handling
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utility class for reading/writing Excel (.xlsx) files using Apache POI.
 */
public class ExcelUtility {

    // Streams for reading/writing files
    public FileInputStream fi;
    public FileOutputStream fo;

    // Apache POI Excel objects
    public XSSFWorkbook workbook;  // Excel workbook
    public XSSFSheet sheet;        // Excel sheet
    public XSSFRow row;            // Row
    public XSSFCell cell;          // Cell
    public CellStyle style;        // Cell style (color, formatting)

    String path; // Path to Excel file

    // Constructor to initialize Excel file path
    public ExcelUtility(String path) {
        this.path = path;
    }

    // Get total row count in a sheet
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path); // Open file
        workbook = new XSSFWorkbook(fi); // Load workbook
        sheet = workbook.getSheet(sheetName); // Get sheet
        int rowcount = sheet.getLastRowNum(); // Get last row index
        workbook.close(); // Close workbook
        fi.close(); // Close file
        return rowcount;
    }

    // Get total cell count in a specific row
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum(); // Number of cells in row
        workbook.close();
        fi.close();
        return cellcount;
    }

    // Read cell value as String
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        DataFormatter formatter = new DataFormatter(); // Formats all data types as String
        String data;
        try {
            data = formatter.formatCellValue(cell); // Get cell data
        } catch (Exception e) {
            data = ""; // Return empty if cell not found
        }

        workbook.close();
        fi.close();
        return data;
    }

    // Write data to a cell
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) { // If file doesn't exist
            workbook = new XSSFWorkbook(); // Create new workbook
            fo = new FileOutputStream(path); // Create file output stream
            workbook.write(fo); // Save empty workbook
            fo.close();
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if (workbook.getSheetIndex(sheetName) == -1) // Create sheet if not exists
            workbook.createSheet(sheetName);
        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rownum) == null) // Create row if not exists
            sheet.createRow(rownum);
        row = sheet.getRow(rownum);

        cell = row.createCell(colnum); // Create cell
        cell.setCellValue(data); // Set value

        fo = new FileOutputStream(path); // Open file to write
        workbook.write(fo); // Save changes

        workbook.close();
        fi.close();
        fo.close();
    }

    // Highlight cell green (Pass)
    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle(); // Create style
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex()); // Set green
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Solid fill
        cell.setCellStyle(style); // Apply style

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }

    // Highlight cell red (Fail)
    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex()); // Set red
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }
}
