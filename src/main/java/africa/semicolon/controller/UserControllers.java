package africa.semicolon.controller;

import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.dto.response.UserRegisterResponse;
import africa.semicolon.exceptions.BlogAppExceptions;
import africa.semicolon.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllers {

    @Autowired
    private UserServices userServices;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest){
        try {
            UserRegisterResponse result = userServices.register(userRegisterRequest);
            return ResponseEntity.ok(result);
        } catch (BlogAppExceptions e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
