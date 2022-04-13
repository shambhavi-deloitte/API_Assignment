import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;




public class ReadingExcel {
    static XSSFSheet sheet;
    static XSSFWorkbook wb;
    public static  String name ;
    public static String gender;
    public static String email ;
    public static String status;
    @Test

    public static HashMap ReadCells() throws IOException {
        String filepath = "C:\\Users\\shampriya\\Desktop\\Data.xlsx";
        File fileobj = new File(filepath);
        FileInputStream fstream = new FileInputStream(filepath);
        wb = new XSSFWorkbook(fstream);
        sheet = wb.getSheetAt(0);
        String sheetname = sheet.getSheetName();
        int colno = sheet.getRow(0).getLastCellNum();
        System.out.println("sheetname is " + sheetname);
        System.out.println("column total is " + colno);
        int row = sheet.getLastRowNum() + 1;

        for (int i = 1; i < row; i++) {
            name = sheet.getRow(i).getCell(0).getStringCellValue();
            gender = sheet.getRow(i).getCell(1).getStringCellValue();
            email = sheet.getRow(i).getCell(2).getStringCellValue();
            status = sheet.getRow(i).getCell(3).getStringCellValue();
        }
        HashMap hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("gender", gender);
        hashMap.put("email", email);
        hashMap.put("status", status);
        return hashMap;
    }
}