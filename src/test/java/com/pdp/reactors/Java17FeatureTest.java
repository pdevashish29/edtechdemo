package com.pdp.reactors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@SpringBootTest
public class Java17FeatureTest {


    @Test
    void randomClassTest() {
        RandomGenerator randomGenerator = RandomGeneratorFactory.of("Xoshiro256PlusPlus").create(999);
        System.out.println(randomGenerator.getClass());
        int counter = 0;
        while (counter <= 10) {
            // 0-10
            int result = randomGenerator.nextInt(11);
            System.out.println(result);
            counter++;
        }
    }

    @Test
    void test2() {
        RandomGeneratorFactory.all()
                .map(fac -> fac.group() + " : " + fac.name())
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    void test3() {

        // BEFORE
        System.out.println(formatter("Java 17"));
        System.out.println(formatter(17));

// AFTER JDK17

        System.out.println(formatterJava17("Java 17"));
        System.out.println(formatterJava17(17));


    }

    @Test
    void test4() {

        testString("Java 16");  // Ok
        testString("Java 11");  // LTS
        testString("");         // Ok
        testString(null);       // Unknown!
    }

    static String formatter(Object o) {
        String formatted = "unknown";
        if (o instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (o instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (o instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (o instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }

    static String formatterJava17(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> o.toString();
        };
    }


    static void testString(String s) {
        if (s == null) {
            System.out.println("Unknown!");
            return;
        }
        switch (s) {
            case "Java 11", "Java 17"   -> System.out.println("LTS");
            default                     -> System.out.println("Ok");
        }
    }

}
