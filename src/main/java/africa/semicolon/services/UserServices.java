package africa.semicolon.services;

import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.dto.response.UserRegisterResponse;

public interface UserServices {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
}
