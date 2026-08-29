package framework;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtils {

    Workbook workbook; //Excel Workbook Object
    Sheet sheet; //Excel Sheet Object

    FileOutputStream fos;

    public void writeDataToExcelSheet(String filePath, String sheetName, String columnName, String data) throws Exception
    {
        File f1=new File(filePath);

        //Step 1: Checking if the workbook exists or not, if not present create a new one
        if(!f1.exists()) //Checking if the file exists or not, if it does not exist create a new one
        {
            if(filePath.endsWith(".xlsx"))
                workbook = new XSSFWorkbook();

            else if(filePath.endsWith(".xls"))
                workbook = new HSSFWorkbook();

            fos=new FileOutputStream(filePath);
            workbook.write(fos); //Whatever operations we are performing on the workbook object shall be written to the file
            fos.close();
        }

        if(filePath.endsWith(".xlsx"))
            workbook = new XSSFWorkbook(new FileInputStream(filePath)); //Whatever data that is present in the file, will be loaded into the workbook object
        else if(filePath.endsWith(".xls"))
            workbook = new HSSFWorkbook(new FileInputStream(filePath));

        //Step 2: Checking if the given sheet is present or not, if not present create a new one

        sheet=workbook.getSheet(sheetName) == null ? workbook.createSheet(sheetName) : workbook.getSheet(sheetName);

        //Step 3: Checking if the given column is present or not, if it is not present create a new one at the end of the sheet

        //We are fetching the first row as the columns will be present in the first row
        Row row=sheet.getRow(0) == null ? sheet.createRow(0) : sheet.getRow(0);

        int noOfColumns=row.getPhysicalNumberOfCells(); //Fetches the total no of columns present in the first row
        int expectedColumnNumber=-1;

        for(int i=0;i<noOfColumns;i++)
        {
            //row.getCell(i).getStringCellValue() --> Fetches the data present in that particular cell
            if(row.getCell(i).getStringCellValue().equals(columnName))
            {
                expectedColumnNumber=i;
                break;
            }
        }

        if(expectedColumnNumber==-1) //Since the column does not exist, creating a new one at the end
        {
            expectedColumnNumber=noOfColumns;
            row.createCell(expectedColumnNumber).setCellValue(columnName); //Adding the column name at the end
        }

        //Step 4: Adding the data at the last row

        int noOfRows=sheet.getPhysicalNumberOfRows();

        //Writing the data to that particular row and column
        sheet.createRow(noOfRows).createCell(expectedColumnNumber).setCellValue(data);

        //Writing the data to the excel file
        fos=new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();

    }

    void main() throws Exception
    {
        writeDataToExcelSheet("Sample.xlsx","Sample_Data","Name","Bipassa");
    }
}
