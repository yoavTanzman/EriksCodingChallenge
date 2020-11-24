package com.eriksdigital.example.exampleAuthManagmentService.db.entity;

import com.eriksdigital.example.exampleAuthManagmentService.convertor.UserEntityConvertor;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="USER_CREDENTIALS_TABLE")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_CREDENTIALS_ID")
    private long id;

    @Column(name="USER_EMAIL")
    @NonNull
    @Convert(converter = UserEntityConvertor.class)
    private String userEmail;

    @Column(name="USER_PASSWORD")
    @NonNull
    @Convert(converter = UserEntityConvertor.class)
    private String password;

    @Column(name="USER_FIRST_NAME")
    @NonNull
    private String firstName;

    @Column(name="USER_LAST_NAME")
    @NonNull
    private String lastName;



}
