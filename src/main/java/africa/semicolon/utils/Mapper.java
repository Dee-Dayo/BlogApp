package africa.semicolon.utils;

import africa.semicolon.data.model.User;
import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.dto.response.UserRegisterResponse;

import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User map(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(userRegisterRequest.getPassword());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());

        return user;
    }

    public static UserRegisterResponse map(User user){
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setUsername(user.getUsername());
        userRegisterResponse.setId(user.getId());
        userRegisterResponse.setDateCreated(DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss").format(user.getDateCreated()));
        return userRegisterResponse;
    }
}
