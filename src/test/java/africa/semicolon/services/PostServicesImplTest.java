package africa.semicolon.services;

import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.data.repository.PostRepository;
import africa.semicolon.data.repository.ViewRepository;
import africa.semicolon.dto.request.UserPostRequest;
import africa.semicolon.dto.request.UserViewPostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServicesImplTest {

    @Autowired
    PostServices postServices;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserServices userServices;
    @Autowired
    ViewRepository viewRepository;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void createOnePost_viewPost_countIsOne(){
        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setTitle("Title");
        userPostRequest.setContent("Content");

        Post post = postServices.createPost(userPostRequest);
        Post post1 = postServices.findPostById(post.getId());
        assertEquals(1, postRepository.count());
        assertEquals(0, post1.getViews().size());

        User user = new User();
        user.setId("id");
        UserViewPostRequest userViewPostRequest = new UserViewPostRequest();
        userViewPostRequest.setViewer(user);
        userViewPostRequest.setPostId(post.getId());

        postServices.viewPost(userViewPostRequest);
        assertEquals(1, viewRepository.count());
        assertEquals(1, postRepository.findAll().getFirst().getViews().size());
        post1 = postServices.findPostById(post.getId());
        assertEquals(1, post1.getViews().size());
    }
}