package com.eriksdigital.example.exampleAuthManagmentService.db.repository;


import com.eriksdigital.example.exampleAuthManagmentService.db.dto.UserCredentialsDTO;
import com.eriksdigital.example.exampleAuthManagmentService.db.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials,Long> {

    @Query(value = "SELECT COUNT(USER_CREDENTIALS_ID) FROM USER_CREDENTIALS_TABLE u " +
            "WHERE USER_EMAIL =:email AND " +
            "USER_PASSWORD =:password ",nativeQuery = true)
    int isUserExistsByEmailAndPassword(String email, String password);

    @Query(value = "SELECT COUNT(USER_CREDENTIALS_ID) FROM USER_CREDENTIALS_TABLE u " +
            "WHERE USER_EMAIL =:email",nativeQuery = true)
    int isUserExistsByEmail(String email);

    @Query(value = "SELECT * FROM USER_CREDENTIALS_TABLE u " +
            "WHERE USER_EMAIL =:email AND " +
            "USER_PASSWORD =:password ",nativeQuery = true)
    UserCredentialsDTO getUserByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM USER_CREDENTIALS_TABLE u " +
            "WHERE USER_EMAIL =:email",nativeQuery = true)
    UserCredentials getUserByEmail(String email);

    @Query(value = "SELECT USER_CONFIRMED FROM USER_CREDENTIALS_TABLE u " +
            "WHERE USER_EMAIL =:email",nativeQuery = true)
    boolean isUserConfirm(String email);


    @Query(value = "SELECT * FROM USER_CREDENTIALS_TABLE u ",nativeQuery = true)
    List<UserCredentials> getAllUsers();
}

