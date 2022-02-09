package com.pdp.reactors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SpringBootTest
public class JsonComparatorTest {

    String jsonPath1 ="classpath:/json/request.json";
    String jsonPath2 ="classpath:/json/response.json";

    @Autowired
    ResourceLoader resourceLoader;


    @Test
    public void compareJsonTest() throws  Exception{

        Resource resource1 = resourceLoader.getResource(jsonPath1);
        Resource resource2 = resourceLoader.getResource(jsonPath2);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
       // JsonNode jsonNode1= mapper.readTree(resource1.getFile());
        //JsonNode jsonNode2= mapper.readTree(resource2.getFile());

        TypeReference<HashMap<String, Object>> type =  new TypeReference<HashMap<String, Object>>() {};

        Map<String, Object> leftMap = mapper.readValue(resource1.getInputStream(), type);
        Map<String, Object> rightMap = mapper.readValue(resource2.getInputStream(), type);
        Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
        Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

        MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

        System.out.println("Entries only on the left\n--------------------------");
        difference.entriesOnlyOnLeft()
                .forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries only on the right\n--------------------------");
        difference.entriesOnlyOnRight()
                .forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries differing\n--------------------------");
        difference.entriesDiffering()
                .forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public static void printAll(JsonNode node) {
        Iterator<String> fieldNames = node.fieldNames();
        while(fieldNames.hasNext()){
            String fieldName = fieldNames.next();
            JsonNode fieldValue = node.get(fieldName);
            if (fieldValue.isObject()) {
                System.out.println(fieldName + " :");
                printAll(fieldValue);
            } else {
                String value = fieldValue.asText();
                System.out.println(fieldName + " : " + value);
            }
        }
    }

}
