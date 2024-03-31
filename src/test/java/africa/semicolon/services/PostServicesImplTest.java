package africa.semicolon.services;

import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.data.repository.PostRepository;
import africa.semicolon.data.repository.ViewRepository;
import africa.semicolon.dto.request.UserCommentPostRequest;
import africa.semicolon.dto.request.UserPostRequest;
import africa.semicolon.dto.request.UserRegisterRequest;
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
        postRepository.deleteAll();
        viewRepository.deleteAll();
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
        user.setUsername("Dayo");
        user.setId("87");
        UserViewPostRequest userViewPostRequest = new UserViewPostRequest();
        userViewPostRequest.setViewer(user);
        userViewPostRequest.setPostId(post.getId());

        postServices.viewPost(userViewPostRequest);
        assertEquals(1, viewRepository.count());
        assertEquals(1, postRepository.findAll().getFirst().getViews().size());
        post1 = postServices.findPostById(post.getId());
        assertEquals(1, post1.getViews().size());
    }

    @Test
    public void createOnePost_viewPostTwice_countIsTwo(){
        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setTitle("Title");
        userPostRequest.setContent("Content");

        Post post = postServices.createPost(userPostRequest);
        Post post1 = postServices.findPostById(post.getId());
        assertEquals(1, postRepository.count());
        assertEquals(0, post1.getViews().size());

        User user = new User();
        user.setId("87");
        UserViewPostRequest userViewPostRequest = new UserViewPostRequest();
        userViewPostRequest.setViewer(user);
        userViewPostRequest.setPostId(post.getId());

        postServices.viewPost(userViewPostRequest);
        postServices.viewPost(userViewPostRequest);
        assertEquals(2, viewRepository.count());
        assertEquals(2, postRepository.findAll().getFirst().getViews().size());
        post1 = postServices.findPostById(post.getId());
        assertEquals(2, post1.getViews().size());

        User user1 = new User();
        user1.setId("342");
        UserViewPostRequest userViewPostRequest1 = new UserViewPostRequest();
        userViewPostRequest1.setViewer(user);
        userViewPostRequest1.setPostId(post.getId());
    }

    @Test
    public void createOnePost_postIsViewed_postCanBeCommentedOn(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");
        userServices.register(userRegisterRequest);
        User user = userServices.findByUsername(userRegisterRequest.getUsername());

        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setTitle("Title");
        userPostRequest.setContent("Content");
        userPostRequest.setUsername(userRegisterRequest.getUsername());

        Post post = postServices.createPost(userPostRequest);
        Post post1 = postServices.findPostById(post.getId());
        assertEquals(1, postRepository.count());
        assertEquals(0, post1.getViews().size());

        UserViewPostRequest userViewPostRequest = new UserViewPostRequest();
        userViewPostRequest.setViewer(user);
        userViewPostRequest.setPostId(post.getId());
        postServices.viewPost(userViewPostRequest);

        post1 = postServices.findPostById(post.getId());
        assertEquals(1, post1.getViews().size());
        assertEquals(0, post1.getComments().size());

        UserCommentPostRequest userCommentPostRequest = new UserCommentPostRequest();
        userCommentPostRequest.setPostId(post.getId());
        userCommentPostRequest.setCommenter(user);
        userCommentPostRequest.setComment("This post is trash");
        postServices.commentPost(userCommentPostRequest);

        post1 = postServices.findPostById(post.getId());
        assertEquals(1, post1.getViews().size());
        assertEquals(1, post1.getComments().size());
        assertEquals("This post is trash", post1.getComments().getFirst().getComment());
    }
}