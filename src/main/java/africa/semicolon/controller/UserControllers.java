package africa.semicolon.controller;

import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.dto.response.UserApiResponse;
import africa.semicolon.dto.response.UserRegisterResponse;
import africa.semicolon.exceptions.BlogAppExceptions;
import africa.semicolon.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/Blog")
public class UserControllers {

    @Autowired
    private UserServices userServices;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest){
        try {
            UserRegisterResponse result = userServices.register(userRegisterRequest);
            return new ResponseEntity<>(new UserApiResponse(true, result), CREATED);
        } catch (BlogAppExceptions e){
            return new ResponseEntity<>(new UserApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
