package com.pdp.reactors.service;


import com.pdp.reactors.model.Person;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PersonService {

  List<Person> persons = new ArrayList<>();
    {
        persons.add(new Person("101","Parashar","delhi", 25));
        persons.add(new Person("102","Devashish","delhi", 25));
    }



    public List<Person> findAllPersons(){
        return  this.persons;
    }

    public Person findPersonById(String id){

        return this.persons.stream()
                .filter(item -> item.getId().equalsIgnoreCase(id)).collect(Collectors.toList())
                .get(0);

    }

    public Person savePerson(Person person){
         person.setId(new Random(100).nextInt()+"");
          this.persons.add(person);
          return person;
    }


    public void geenrateExcel() {

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet1 = workbook.createSheet("report");

            Row header = sheet1.createRow(0);
            Cell headerCell =  header.createCell(0);
            headerCell.setCellValue("Name");
           // headerCell.setCellStyle(null);

            headerCell= header.createCell(1);
            headerCell.setCellValue("Address");
            //headerCell.setCellStyle(null);


            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);

           for(int i=1; i<=10;i++){

               Row row = sheet1.createRow(++i);
               Cell cell = row.createCell(0);
               cell.setCellValue(new Random(1000)+"");
               cell.setCellStyle(style);

               cell = row.createCell(1);
               cell.setCellValue(new Random(1000)+"");
               cell.setCellStyle(style);
           }

            FileOutputStream outputStream = new FileOutputStream("tem.xlx");
            workbook.write(outputStream);
            workbook.close();


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
