package com.eriksdigital.example.exampleAuthManagmentService.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {


    private static int countCapitalLetter(String str) {
        int upper = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                upper++;
//            else if (str.charAt(i) >= 'a' && str.charAt(i)<= 'z')
//                lower++;
//            else if (str.charAt(i)>= '0' && str.charAt(i)<= '9')
//                number++;
//            else
//                special++;
        }
        return upper;
    }

    private static int countSpecialChar(String str) {
        int upper = 0, lower = 0, number = 0, special = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                upper++;
            else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
                lower++;
            else if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
                number++;
            else
                special++;
        }
        return special;
    }


    public static boolean isPasswordValid(String password) {

        boolean isValid = (countSpecialChar(password) > 0
                && countCapitalLetter(password) > 0
                && password.length() >= 8
                && password.length() <= 14) ? true : false;
        return isValid;
    }

    /**
     * checks if email is valid
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
//            final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        final String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean isValid = (matcher.matches()) ? true : false;
        return isValid;
    }

}

