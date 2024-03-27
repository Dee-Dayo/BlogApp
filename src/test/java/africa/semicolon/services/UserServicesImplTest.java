package africa.semicolon.services;

import africa.semicolon.data.repository.UserRepository;
import africa.semicolon.dto.request.UserRegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServicesImplTest {

    @Autowired
    UserServices userServices;
    @Autowired
    UserRepository userRepository;

    @Test
    public void registerOneUser_userIsOne(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");

        userServices.register(userRegisterRequest);
        assertEquals(1, userRepository.count());
    }
}