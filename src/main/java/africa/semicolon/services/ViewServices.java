package africa.semicolon.services;

import africa.semicolon.data.model.User;
import africa.semicolon.data.model.View;
import africa.semicolon.dto.request.UserViewPostRequest;
import org.springframework.stereotype.Service;

public interface ViewServices {

    View saveView(UserViewPostRequest userViewPostRequest);
}
