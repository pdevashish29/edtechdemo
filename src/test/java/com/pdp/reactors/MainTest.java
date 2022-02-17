package com.pdp.reactors;

import com.github.javafaker.Faker;
import com.pdp.reactors.controller.repo.PersonRepo;
import com.pdp.reactors.model.Person;
import com.pdp.reactors.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class MainTest {

    @Autowired
    private PersonRepo personRepo;


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    public void readFilesTest(){
        try {
            File directory = new ClassPathResource("/json").getFile();
            if(directory.exists()){
                System.out.println(directory.listFiles().length);
                for (File file:directory.listFiles()){

                    String requestFileName =file.getName();
                    String mainApiName=requestFileName.substring(0,requestFileName.indexOf("_"));
                    String subApiName=requestFileName.substring(requestFileName.indexOf("_")+1,requestFileName.lastIndexOf("_"));
                    System.out.println(mainApiName+" "+subApiName);
                 //   System.out.println(requestFileName);
                }
            }else
                System.out.println("directory not found");
            Assertions.assertTrue(true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("devdewance@gmail.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");
        javaMailSender.send(msg);
    }

    @Test
   public void sendExcelInMail() throws MessagingException, IOException, MessagingException {
        log.info("mail sending with attachment");
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("devdewance@gmail.com");
        helper.setSubject("Functional test case Report ");
        helper.setText("<h1>PFA</h1>", true);
        helper.addAttachment("temp.xlsx", new FileSystemResource("/Users/pdevashish/IdeaProjects/edtechdemo/temp.xlsx"));
        javaMailSender.send(msg);
        log.info("mail sent with attachment");
    }


    @Test
    public void checkPerfomance() {

        personRepo.deleteAll();
        int size = 10;
        List<Person> personsList1 = getFakePersonList(size);
        List<Person> personList2 = getFakePersonList(size);


        Long t1 = System.currentTimeMillis();

        for (Person p : personsList1) {
            Person save = personRepo.save(p);
        }

        Long t2 = System.currentTimeMillis();

        personList2.stream().parallel().forEach(personRepo::save);

        Long t3 = System.currentTimeMillis();

        System.out.println("Classic Time of execution " + (t2 - t1));

        System.out.println("Parallel Stream Time of execution " + (t3 - t2));

        int noOfRecords = (int) personRepo.count();
        Assertions.assertEquals(noOfRecords, (size * 2));

    }


    private List<Person> getFakePersonList(Integer size) {
        Faker faker = new Faker();
        return IntStream.range(0, size)
                .mapToObj(i -> {
                    Person person = new Person();
                    person.setName(faker.name().fullName());
                    person.setAddress(faker.address().fullAddress());
                    person.setAge(faker.number().randomDigitNotZero());
                    return person;
                })
                .collect(Collectors.toList());
    }


}
