package utilities;

import org.testng.annotations.DataProvider;
import java.io.IOException;

/**
 * DataProviders: Supplies test data from Excel files to TestNG test methods.
 * This class reads data from "LoginData.xlsx" using ExcelUtility and returns it
 * as a 2D String array for parameterized tests.
 */
public class DataProviders {

    /**
     * DataProvider method for login test data.
     * Reads credentials from an Excel file and supplies them to the test method.
     *
     * @return 2D array of String data (rows = test cases, columns = data fields)
     * @throws IOException if there is an issue reading the Excel file
     */
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        // Path to the Excel file (placed in src/test/resources/testData folder)
        String path = "./testData/Opencart_LoginData.xlsx";

        // Create an instance of ExcelUtility with the given file path
        ExcelUtility xlUtil = new ExcelUtility(path);

        // Get the total number of rows (excluding header row in logic)
        int totalRows = xlUtil.getRowCount("Sheet1");

        // Get the total number of columns from row 1 (header row)
        int totalCols = xlUtil.getCellCount("Sheet1", 1);

        // Create a 2D array to store Excel data
        // Rows = totalRows (excluding header), Columns = totalCols
        String loginData[][] = new String[totalRows][totalCols];

        // Loop through each row (start from 1 to skip header row)
        for (int i = 1; i <= totalRows; i++) {

            // Loop through each column
            for (int j = 0; j < totalCols; j++) {

                // Store each cell's data into the array
                // i-1 is used because array index starts from 0 but Excel rows start from 1
                loginData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);
            }
        }

        // Return the complete data set to TestNG
        return loginData;
    }
}
