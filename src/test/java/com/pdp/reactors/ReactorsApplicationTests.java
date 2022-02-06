package com.pdp.reactors;


import com.github.javafaker.Faker;
import lombok.Data;
import lombok.ToString;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.util.*;

@SpringBootTest
class ReactorsApplicationTests {



   @Test
    public void geenrateExcel() {

        try {
            Faker faker = new Faker();

            Workbook workbook = new XSSFWorkbook();

            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);

            //headerStyle
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            // headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(font);

            List<ExcelData> tabs=   generateContents();

            for (ExcelData tab : tabs){
                Sheet sheet = workbook.createSheet(tab.getSheetName());
                sheet.setColumnWidth(0, 6000);
                sheet.setColumnWidth(1, 6000);
                sheet.setColumnWidth(2, 6000);
                int nextRow =0;
                Row header = sheet.createRow(nextRow);

                List<String> headers =tab.getHeaders();

                for (int i =0; i< headers.size();i++ ){
                    Cell headerCell = header.createCell(i);
                    headerCell.setCellValue(headers.get(i));
                    headerCell.setCellStyle(headerStyle);
                }

                CellStyle style = workbook.createCellStyle();
                style.setWrapText(true);



              /*  for(int i=1; i<=10;i++){

                    Row row = sheet.createRow(i);

                    Cell cell = row.createCell(0);
                    cell.setCellValue(faker.name().firstName());
                    cell.setCellStyle(style);

                    cell = row.createCell(1);
                    cell.setCellValue(faker.address().city());
                    cell.setCellStyle(style);

                    cell = row.createCell(2);
                    cell.setCellValue(faker.address().city());
                    cell.setCellStyle(style);

                }*/

                List<List<String>> tabContents = tab.getContents();

                for(int j =1; j<=tabContents.size();j++){

                    Row row = sheet.createRow(j);

                    Cell cell = row.createCell(0);
                    cell.setCellValue(faker.name().firstName());
                    cell.setCellStyle(style);

                    cell = row.createCell(1);
                    cell.setCellValue(faker.address().city());
                    cell.setCellStyle(style);

                    cell = row.createCell(2);
                    cell.setCellValue(faker.address().city());
                    cell.setCellStyle(style);

                }

                 /*  List<List<String>> tabContents = tab.getContents();

                for(List<String> contents :tabContents){
                    nextRow =1;
                    Row row = sheet.createRow(nextRow);
                    for(String content : contents){
                        int nextCell =0;
                        Cell cell = row.createCell(nextCell);
                        cell.setCellValue(content);
                        cell.setCellStyle(style);
                        nextCell=nextCell+1;
                    }
                    nextRow=nextRow+1;
                }*/

            }


            FileOutputStream outputStream = new FileOutputStream("temp.xlsx");
            workbook.write(outputStream);
            workbook.close();


        }catch (Exception e){
            e.printStackTrace();
        }


    }



    List<ExcelData> generateContents(){
        List<ExcelData> tabsContent = new ArrayList<>();
        tabsContent.add(generateContent("report1"));
        tabsContent.add(generateContent("report2"));
        return tabsContent;
    }

    ExcelData generateContent(String sheetName) {
        Faker faker = new Faker();
        List<String> headers = Arrays.asList("Name", "Age","Address");
        List<List<String>> contents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<String> rowContent = new ArrayList<String>();
            for (int j = 0; j < headers.size(); j++) {
                rowContent.add(faker.name().firstName());
            }
            contents.add(rowContent);
        }
        ExcelData excelData = new ExcelData();
        excelData.setHeaders(headers);
        excelData.setContents(contents);
        excelData.setSheetName(sheetName);
        System.out.println(excelData);
        Assertions.assertNotNull(excelData.getHeaders());
        return excelData;

    }

}


@Data
@ToString
class ExcelData{

    private String sheetName;
    private List<String> headers;
    private List<List<String>> contents;

}



