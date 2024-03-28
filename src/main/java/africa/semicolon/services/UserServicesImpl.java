package africa.semicolon.services;

import africa.semicolon.data.model.User;
import africa.semicolon.data.repository.UserRepository;
import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.dto.response.UserRegisterResponse;
import africa.semicolon.exceptions.UserAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.utils.Mapper.map;

@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        userRegisterRequest.setUsername(userRegisterRequest.getUsername().toLowerCase());
        validate(userRegisterRequest.getUsername());

        User user = map(userRegisterRequest);
        userRepository.save(user);

        UserRegisterResponse result = map(user);

        return result;
    }

    private void validate(String username){
        boolean userExists = userRepository.existsByUsername(username);
        if(userExists) throw new UserAlreadyExist(username + " already exist");
    }

 
}
