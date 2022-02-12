//package com.pdp.reactors;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.FileOutputStream;
//import java.security.*;
//import java.security.spec.NamedParameterSpec;
//
//@SpringBootTest
//public class Javav11FeaturesTest {
//
//
//    @Test
//    void test2() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
//
//        KeyPairGenerator kpg = KeyPairGenerator.getInstance("XDH");
//        NamedParameterSpec parameterSpec = new NamedParameterSpec("X25519");
//        kpg.initialize(parameterSpec);
//        KeyPair keyPair = kpg.generateKeyPair();
//
//        System.out.println("-------public Key ........");
//
//
//        PublicKey publicKey = keyPair.getPublic();
//        String algorithm = publicKey.getAlgorithm();
//        String format = publicKey.getFormat();
//        System.out.println(algorithm);
//        System.out.println(format);
//        System.out.println(print(publicKey.getEncoded()));
//        try  {
//            FileOutputStream FOS = new FileOutputStream("publicKey.txt");
//            FOS.write(publicKey.getEncoded());
//            FOS.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public static String print(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("[ ");
//        for (byte b : bytes) {
//            sb.append(String.format("0x%02X ", b));
//        }
//        sb.append("]");
//        return sb.toString();
//    }
//
//    @Test
//    void test1() {
//
//        String codepoint = "U+1F92A";   // crazy face
//        System.out.println(convertCodePoints(codepoint));
//    }
//
//    // Java, UTF-16
//    // Convert code point to unicode
//    static char[] convertCodePoints(String codePoint) {
//        Integer i = Integer.valueOf(codePoint.substring(2), 16);
//        char[] chars = Character.toChars(i);
//        return chars;
//
//    }
//}
