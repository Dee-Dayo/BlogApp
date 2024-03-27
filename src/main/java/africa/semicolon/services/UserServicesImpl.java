package africa.semicolon.services;

import africa.semicolon.data.model.User;
import africa.semicolon.data.repository.UserRepository;
import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.exceptions.UserAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.utils.Mapper.map;

@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        userRegisterRequest.setUsername(userRegisterRequest.getFirstName().toLowerCase());
        validate(userRegisterRequest.getUsername());

        User user = map(userRegisterRequest);


        userRepository.save(user);
    }

    private void validate(String username) {
        boolean userExist = userRepository.existsByUsername(username);
        if (userExist) throw new UserAlreadyExist(username + "already exists");
    }
}
