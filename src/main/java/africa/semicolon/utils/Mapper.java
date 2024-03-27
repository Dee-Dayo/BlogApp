package africa.semicolon.utils;

import africa.semicolon.data.model.User;
import africa.semicolon.dto.request.UserRegisterRequest;

public class Mapper {
    public static User map(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(userRegisterRequest.getPassword());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());

        return user;
    }
}
