package framework;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    Workbook workbook; //Excel Workbook Object
    Sheet sheet; //Excel Sheet Object

    FileOutputStream fos;

    public String getDataFromExcel(String filePath, String sheetName, String columnName, String ...rowIdentifier) throws Exception
    {
        File f1=new File(filePath);

        //Step 1: Checking if the workbook exists or not, if not present create a new one
        if(!f1.exists()) //Checking if the file exists or not, if it does not exist create a new one
        {
            throw new GenericExceptions("File "+filePath+" does not exist");
        }

        if(filePath.endsWith(".xlsx"))
            workbook = new XSSFWorkbook(new FileInputStream(filePath)); //Whatever data that is present in the file, will be loaded into the workbook object
        else if(filePath.endsWith(".xls"))
            workbook = new HSSFWorkbook(new FileInputStream(filePath));

        //Step 2: Checking if the given sheet is present or not, if not present create a new one

        if(workbook.getSheet(sheetName) == null)
            throw new GenericExceptions("Sheet "+sheetName+" does not exist");

        else
            sheet = workbook.getSheet(sheetName);

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
            throw new GenericExceptions("Column "+columnName+" does not exist");
        }

        //Step 4: Adding the data to that row

        String data="";
        if(rowIdentifier.length!=0) //In which row we need to add the data
        {
            String expectedColumnName=rowIdentifier[0];
            String expectedRowName=rowIdentifier[1];

            noOfColumns=row.getPhysicalNumberOfCells();
            int expectedRowIdentifierColumnNumber=-1;

            for (int i=0;i<noOfColumns;i++)
            {
                if(row.getCell(i).getStringCellValue().equals(expectedColumnName))
                {
                    expectedRowIdentifierColumnNumber=i;
                    break;
                }
            }

            if(expectedRowIdentifierColumnNumber==-1)
                throw new GenericExceptions(expectedColumnName+" does not exist in the sheet");

            int noOfRows=sheet.getPhysicalNumberOfRows();

            for(int i=1;i<noOfRows;i++)
            {
                if(sheet.getRow(i).getCell(expectedRowIdentifierColumnNumber).getStringCellValue().equals(expectedRowName))
                {
                    data=sheet.getRow(i).getCell(expectedRowIdentifierColumnNumber).getStringCellValue();
                    break;
                }
            }
        }

        else {
            int noOfRows = sheet.getPhysicalNumberOfRows();

            //Writing the data to that particular row and column
            data=sheet.getRow(noOfRows).getCell(expectedColumnNumber).getStringCellValue();
        }

        return data;
    }

    public List<Map<String,String>> fetchDataFromSheet(String filePath, String sheetName) throws Exception
    {
        File f1=new File(filePath);

        //Step 1: Checking if the workbook exists or not, if not present create a new one
        if(!f1.exists()) //Checking if the file exists or not, if it does not exist create a new one
        {
            throw new GenericExceptions("File "+filePath+" does not exist");
        }

        if(filePath.endsWith(".xlsx"))
            workbook = new XSSFWorkbook(new FileInputStream(filePath)); //Whatever data that is present in the file, will be loaded into the workbook object
        else if(filePath.endsWith(".xls"))
            workbook = new HSSFWorkbook(new FileInputStream(filePath));

        //Step 2: Checking if the given sheet is present or not, if not present create a new one

        if(workbook.getSheet(sheetName) == null)
            throw new GenericExceptions("Sheet "+sheetName+" does not exist");

        else
            sheet = workbook.getSheet(sheetName);

        int noOfRows=sheet.getPhysicalNumberOfRows();
        int noOfColumns=sheet.getRow(0).getPhysicalNumberOfCells();

        List<Map<String,String>> recordsOfData=new ArrayList<Map<String,String>>();

        for(int i=1;i<noOfRows;i++)
        {
            Map<String,String> data=new HashMap<>();
            for(int j=0;j<noOfColumns;j++)
            {
                data.put(sheet.getRow(0).getCell(j).getStringCellValue(),sheet.getRow(i).getCell(j).getStringCellValue());
            }

            recordsOfData.add(data);
        }

        return recordsOfData;
    }

    public void writeDataToExcelSheet(String filePath, String sheetName, String columnName, String data, String ...rowIdentifier) throws Exception
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

        //Step 4: Adding the data to that row

        if(rowIdentifier.length!=0) //In which row we need to add the data
        {
            String expectedColumnName=rowIdentifier[0];
            String expectedRowName=rowIdentifier[1];

            noOfColumns=row.getPhysicalNumberOfCells();
            int expectedRowIdentifierColumnNumber=-1;

            for (int i=0;i<noOfColumns;i++)
            {
                if(row.getCell(i).getStringCellValue().equals(expectedColumnName))
                {
                    expectedRowIdentifierColumnNumber=i;
                    break;
                }
            }

            if(expectedRowIdentifierColumnNumber==-1)
                throw new GenericExceptions(expectedColumnName+" does not exist in the sheet");

            int noOfRows=sheet.getPhysicalNumberOfRows();

            for(int i=1;i<noOfRows;i++)
            {
                if(sheet.getRow(i).getCell(expectedRowIdentifierColumnNumber).getStringCellValue().equals(expectedRowName))
                {
                    sheet.getRow(i).createCell(expectedColumnNumber).setCellValue(data);
                    break;
                }
            }
        }

        else {
            int noOfRows = sheet.getPhysicalNumberOfRows();

            //Writing the data to that particular row and column
            sheet.createRow(noOfRows).createCell(expectedColumnNumber).setCellValue(data);
        }

        //Writing the data to the excel file
        fos=new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();

    }

    void main() throws Exception
    {
        writeDataToExcelSheet("Sample.xlsx","Sample_Data","Name","Bipassa");
        writeDataToExcelSheet("Sample.xlsx","Sample_Data","Country","India","Name","Bipassa");
        writeDataToExcelSheet("Sample.xlsx","Sample_Data","Profession","QA","Name","Bipassa");
    }
}
