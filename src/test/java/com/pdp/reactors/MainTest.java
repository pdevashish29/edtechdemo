package com.pdp.reactors;

import com.github.javafaker.Faker;
import com.pdp.reactors.repo.PersonRepo;
import com.pdp.reactors.model.Person;
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
import java.util.List;
import java.util.function.Function;
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
    Faker faker = new Faker();

    @Test
    public void readFilesTest(){
        try {
            File directory = new ClassPathResource("/json").getFile();
            if(directory.exists() && directory.listFiles()!=null){
                System.out.println(directory.listFiles().length);
                for (File file:directory.listFiles()){
                    String requestFileName =file.getName();
                    String mainApiName=requestFileName.substring(0,requestFileName.indexOf("_"));
                    String subApiName=requestFileName.substring(requestFileName.indexOf("_")+1,requestFileName.lastIndexOf("_"));
                    System.out.println(mainApiName+" "+subApiName);
                    System.out.println(requestFileName);
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
   public void sendExcelInMail() throws  MessagingException {
        System.out.println();
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
        int size = 50000;
        List<Person> personList1 = getFakePersonList(size);
        List<Person> personList2 = getFakePersonList(size);
        Long t1 = System.currentTimeMillis();
        for (Person p : personList1) { Person save = personRepo.save(p); }
        Long t2 = System.currentTimeMillis();
        // FORK AND JOIN
        // THREADPOOL --
        // MAIN THREAD WILL WAIT FOR RESULT COLLECTION
        //

        personList2.stream().parallel().forEach(personRepo::save);
        Long t3 = System.currentTimeMillis();
        System.out.println("Classic Time of execution " + (t2 - t1));
        System.out.println("Parallel Stream Time of execution " + (t3 - t2));
        int noOfRecords = (int) personRepo.count();
       // List<Integer> personList1Ids= personList1.stream().map(getPersonIntegerFunction()).collect(Collectors.toList());
        //List<Integer> personList2Ids= personList2.stream().map(getPersonIntegerFunction()).collect(Collectors.toList());
       // System.out.println(personList1Ids);
        //System.out.println(personList2Ids);
        Assertions.assertEquals(noOfRecords, (size * 2));
    }


    private Function<Person, Integer> getPersonIntegerFunction() {
        return i -> i.getId();
    }


    private List<Person> getFakePersonList(Integer size) {
        return IntStream.range(0, size).mapToObj(new MainTest()::getPerson) .collect(Collectors.toList());
    }

    private  Person getPerson(int i ){
        Person person = new Person();
        person.setName(faker.name().fullName());
        person.setAddress(faker.address().fullAddress());
        person.setAge(faker.number().randomDigitNotZero());
        return person;
    }


}
