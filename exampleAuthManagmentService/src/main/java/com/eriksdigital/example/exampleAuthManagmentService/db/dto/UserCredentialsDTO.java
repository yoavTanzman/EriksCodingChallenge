package com.eriksdigital.example.exampleAuthManagmentService.db.dto;


import com.eriksdigital.example.exampleAuthManagmentService.utils.StringUtils;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Component
public class UserCredentialsDTO {


    @NonNull
    private String userEmail;

    @NonNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String LastName;



    public static boolean isPasswordValid(String password){

        if(StringUtils.isPasswordValid(password)) return true;

        return false;
    }

    public static boolean isEmailValid(String email){
        if(StringUtils.isValidEmail(email)) return true;
        return false;
    }

}
