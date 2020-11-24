package com.eriksdigital.example.exampleAuthManagmentService.controller;

import com.eriksdigital.example.exampleAuthManagmentService.authentication.jwtutils.JwtUtils;
import com.eriksdigital.example.exampleAuthManagmentService.db.dto.UserCredentialsDTO;
import com.eriksdigital.example.exampleAuthManagmentService.db.entity.UserCredentials;
import com.eriksdigital.example.exampleAuthManagmentService.db.repository.UserCredentialsRepository;
import com.eriksdigital.example.exampleAuthManagmentService.utils.AesEncryption;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authentication")
public class LogInController {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JwtUtils jwtUtils;


    /**
     * Use this endpoint to check your connection
     * @return
     */
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping(path="/hello",produces = "application/json")
    public ResponseEntity<?> hello(){
        return new ResponseEntity<>("hello port 7074", HttpStatus.OK);
    }

    /**
     * I have build that up for you so you will have some initials users to play with,
     * you will have to login with one of the users to get JWT so you will be able to check the other
     * service for the orders
     * @return responseEntity with a list of all users
     */
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping(path="/initDb",produces = "application/json")
    public ResponseEntity<?> initDatabase() {

        List<UserCredentialsDTO> userCredentialsDTOArrayList = new ArrayList<>();
        userCredentialsDTOArrayList.add(new UserCredentialsDTO("test@eriks.com","123!qwaszx",
                "test","test"));
        userCredentialsDTOArrayList.add(new UserCredentialsDTO("test1@eriks.com","1123!qwaszx",
                "test1","test1"));
        userCredentialsDTOArrayList.add(new UserCredentialsDTO("test2@eriks.com","11123!qwaszx",
                "test2","test2"));

        userCredentialsDTOArrayList.stream().forEach(user->{
            UserCredentials tempUserCredentials = modelMapper.map(user,UserCredentials.class);
            userCredentialsRepository.save(tempUserCredentials);
        });
        List<UserCredentials> userList = new ArrayList<>();
        userList = userCredentialsRepository.getAllUsers();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }


    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping(path="/get-all-users",produces = "application/json")
    public ResponseEntity<?> getAll(){

        List<UserCredentials> userList = new ArrayList<>();
        userList = userCredentialsRepository.getAllUsers();
        return new ResponseEntity<>(userList,HttpStatus.OK);

    }

    /**
     * this will allow you to add new users into the system, not required because I gave you a pre set user list
     * for this specific assignment
     * @param jsonString
     * @return
     * @throws URISyntaxException
     */

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/signup",produces ="application/json")
    public ResponseEntity<?> addNewUser(@RequestBody String jsonString) throws URISyntaxException {

        JSONObject jsonObject = new JSONObject(jsonString);
        String password = jsonObject.getString("password");
        String userEmail = jsonObject.getString("userEmail").toLowerCase();
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        String fullName =firstName+" "+lastName;
        if(! UserCredentialsDTO.isPasswordValid(password) && UserCredentialsDTO.isEmailValid(userEmail))
            return new ResponseEntity<>("Password is invalid"+password,HttpStatus.BAD_REQUEST);
        if(userCredentialsRepository.isUserExistsByEmail(AesEncryption.encrypt(userEmail))>0){
            return new ResponseEntity<>("User with name: "+fullName+" already exists ",HttpStatus.FOUND);
        }
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO(userEmail,password,firstName,lastName);
        UserCredentials userCredentials = modelMapper.map(userCredentialsDTO,UserCredentials.class);

        userCredentialsRepository.save(userCredentials);
        return new ResponseEntity<>("User Added : "+ fullName,HttpStatus.OK);

    }

    /**
     * login endpoint if user and password matches you will receive a token back
     * example json to send via postMan
     * {
     *     "userEmail":"test@eriks.com",
     *     "password":"123!qwaszx"
     * }
     *
     *
     * @param jsonString
     * @return ResponseEntity with token and with status
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/login",produces ="application/json")
    public ResponseEntity<?> login(@RequestBody String jsonString){

        JSONObject jsonObject = new JSONObject(jsonString);
        String password = AesEncryption.encrypt(jsonObject.getString("password"));
        String userEmail = AesEncryption.encrypt(jsonObject.getString("userEmail").toLowerCase());

        if(userCredentialsRepository.isUserExistsByEmailAndPassword(userEmail,password) == 0 )
            return new ResponseEntity<>("User Not Found please check your email and password",HttpStatus.NOT_FOUND);

        String token = jwtUtils.getJWTToken(AesEncryption.decrypt(userEmail));
//        token = jwtUtils.getTokenFormat(token);
        return new ResponseEntity<>(token,HttpStatus.OK);

    }


}
