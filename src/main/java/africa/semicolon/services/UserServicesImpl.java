package africa.semicolon.services;

import africa.semicolon.data.model.User;
import africa.semicolon.data.repository.UserRepository;
import africa.semicolon.dto.request.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername().toLowerCase());
        user.setPassword(userRegisterRequest.getPassword());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());

        userRepository.save(user);
    }
}
