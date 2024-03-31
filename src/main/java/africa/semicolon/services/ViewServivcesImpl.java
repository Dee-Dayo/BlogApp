package africa.semicolon.services;

import africa.semicolon.data.model.View;
import africa.semicolon.data.repository.ViewRepository;
import africa.semicolon.dto.request.UserViewPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.utils.Mapper.requestMap;

@Service
public class ViewServivcesImpl implements ViewServices{

    @Autowired
    ViewRepository viewRepository;


    @Override
    public View saveView(UserViewPostRequest userViewPostRequest) {
        View view = requestMap(userViewPostRequest);
        viewRepository.save(view);
        return view;
    }
}
