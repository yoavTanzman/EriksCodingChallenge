package com.eriksdigital.example.exampleAuthManagmentService;

import com.eriksdigital.example.exampleAuthManagmentService.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilsUnitTest {

    @Test
    public void isPasswordValidTest(){

        // Should return false no UpperCase letter
        String password = "643287dsa3$3";
        boolean bool =StringUtils.isPasswordValid(password);
        Assertions.assertFalse(bool);

        // Should return false less than 8 char
        String password1 = "64Td3$3";
        boolean bool1 =StringUtils.isPasswordValid(password1);
        Assertions.assertFalse(bool1);

        // Should return false no special char
        String password2 = "64783Ysada";
        boolean bool2 = StringUtils.isPasswordValid(password2);
        Assertions.assertFalse(bool2);

        // Should return false too long
        String password3 = "Tdaddasest@@123das11";
        boolean bool3 =StringUtils.isPasswordValid(password3);
        Assertions.assertFalse(bool3);

        // Should return true
        String password4= "Test@@123das11";
        boolean bool4 =StringUtils.isPasswordValid(password4);
        Assertions.assertTrue(bool4);


    }
    @Test
    public void isEmailValid(){
        String email="alice@example.com";
        String email1="alice.bob@example.co.in";
        String email2="alice?bob@example.com";
        String email3="alice`bob@example.com";
        String email4="alice|bob@example.com";
        String email5="@example.com";
        String email6="alice@example.com.il.op";
        String email7="aliceexample.com";
        Assertions.assertTrue(StringUtils.isValidEmail(email));
        Assertions.assertTrue(StringUtils.isValidEmail(email1));
        Assertions.assertTrue(StringUtils.isValidEmail(email2));
        Assertions.assertTrue(StringUtils.isValidEmail(email3));
        Assertions.assertTrue(StringUtils.isValidEmail(email4));
        Assertions.assertFalse(StringUtils.isValidEmail(email5));
//    Assertions.assertFalse(StringUtils.isValidEmail(email6));
        Assertions.assertFalse(StringUtils.isValidEmail(email7));
    }



}
