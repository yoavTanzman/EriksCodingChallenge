package com.eriksdigital.example.exampleAuthManagmentService;

import com.eriksdigital.example.exampleAuthManagmentService.utils.AesEncryption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncryptionUnitTest {

    @Test
    public void encryptionTest(){
        String value = "This is a simple string";
        String encrypted = AesEncryption.encrypt(value);
        String decrypted = AesEncryption.decrypt(encrypted);

        System.out.println("String before encryption: "+ value);
        System.out.println("String after encryption: "+ encrypted);
        System.out.println("String after decryption"+ decrypted);
        Assertions.assertEquals(value,decrypted);
        Assertions.assertNotEquals(value,encrypted);

    }

}
