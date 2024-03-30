package africa.semicolon.services;

import africa.semicolon.data.repository.PostRepository;
import africa.semicolon.data.repository.UserRepository;
import africa.semicolon.dto.request.UserDeletePostRequest;
import africa.semicolon.dto.request.UserEditPostRequest;
import africa.semicolon.dto.request.UserPostRequest;
import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.exceptions.UserAlreadyExist;
import africa.semicolon.exceptions.UserDoesntExist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServicesImplTest {

    @Autowired
    UserServices userServices;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test
    public void registerOneUser_userIsOne(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");

        userServices.register(userRegisterRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void cantRegisterUserWithSameUsername(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");

        userServices.register(userRegisterRequest);
        assertThrows(UserAlreadyExist.class, ()->userServices.register(userRegisterRequest));
        assertEquals(1, userRepository.count());
    }

    @Test
    public void registerOneUser_UserCanPost(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");

        userServices.register(userRegisterRequest);

        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setUsername("username");
        userPostRequest.setTitle("First Title");
        userPostRequest.setContent("First Content");
        userServices.createPost(userPostRequest);
        assertEquals(1, postRepository.count());

        assertEquals(1, userServices.findByUsername("username").getPosts().size());
    }

    @Test
    public void userCantPostIfAccountNotRegistered(){
        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setUsername("username");
        userPostRequest.setTitle("First Title");
        userPostRequest.setContent("First Content");
        assertThrows(UserDoesntExist.class, ()->userServices.createPost(userPostRequest));
    }

    @Test
    public void registerOneUser_UserCanPostTwoTimes(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");

        userServices.register(userRegisterRequest);

        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setUsername("username");
        userPostRequest.setTitle("First Title");
        userPostRequest.setContent("First Content");
        userServices.createPost(userPostRequest);

        UserPostRequest userPostRequest2 = new UserPostRequest();
        userPostRequest2.setUsername("username");
        userPostRequest2.setTitle("Second Title");
        userPostRequest2.setContent("Second Content");
        userServices.createPost(userPostRequest);

        assertEquals(2, userServices.findByUsername("username").getPosts().size());
    }

    @Test
    public void userCreatePost_userCanEditCreatedPost(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");
        userServices.register(userRegisterRequest);

        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setUsername("username");
        userPostRequest.setTitle("First Title");
        userPostRequest.setContent("First Content");
        userServices.createPost(userPostRequest);

        assertEquals(1, userServices.findByUsername("username").getPosts().size());
        assertEquals("First Title", userServices.findByUsername("username").getPosts().get(0).getTitle());
        assertEquals("First Content", userServices.findByUsername("username").getPosts().get(0).getContent());


        UserEditPostRequest userEditPostRequest = new UserEditPostRequest();
        userEditPostRequest.setUsername("username");
        userEditPostRequest.setTitle("New Title");
        userEditPostRequest.setContent("Updated Content");
        String id = userServices.findByUsername("username").getPosts().get(0).getId();
        userEditPostRequest.setPostId(id);
        userServices.updatePost(userEditPostRequest);

        assertEquals(1, userServices.findByUsername("username").getPosts().size());
        assertEquals("New Title", userServices.findByUsername("username").getPosts().get(0).getTitle());
        assertEquals("Updated Content", userServices.findByUsername("username").getPosts().get(0).getContent());
    }

    @Test
    public void userCreatePost_anotherUserCantEditPost(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");
        userServices.register(userRegisterRequest);

        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setUsername("username");
        userPostRequest.setTitle("First Title");
        userPostRequest.setContent("First Content");
        userServices.createPost(userPostRequest);

        assertEquals(1, userServices.findByUsername("username").getPosts().size());
        assertEquals("First Title", userServices.findByUsername("username").getPosts().get(0).getTitle());


        UserEditPostRequest userEditPostRequest = new UserEditPostRequest();
        userEditPostRequest.setUsername("fakeUsername");
        userEditPostRequest.setTitle("First Title");
        userEditPostRequest.setContent("Updated Content");
        String id = userServices.findByUsername("username").getPosts().get(0).getId();
        userEditPostRequest.setPostId(id);
        assertThrows(UserDoesntExist.class,()-> userServices.updatePost(userEditPostRequest));
    }

    @Test
    public void userCreatePost_userCanDeletePost(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");
        userServices.register(userRegisterRequest);

        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setUsername("username");
        userPostRequest.setTitle("First Title");
        userPostRequest.setContent("First Content");
        userServices.createPost(userPostRequest);

        assertEquals(1, userServices.findByUsername("username").getPosts().size());

        UserDeletePostRequest userDeletePostRequest = new UserDeletePostRequest();
        userDeletePostRequest.setUsername("username");
        String id = userServices.findByUsername("username").getPosts().get(0).getId();
        userDeletePostRequest.setPostId(id);
        userServices.deletePost(userDeletePostRequest);

        assertEquals(0, userServices.findByUsername("username").getPosts().size());
    }

    @Test
    public void userCreatePost_userCanViewPost(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");
        userServices.register(userRegisterRequest);

        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setUsername("username");
        userPostRequest.setTitle("First Title");
        userPostRequest.setContent("First Content");
        userServices.createPost(userPostRequest);

        assertEquals(1, userServices.findByUsername("username").getPosts().size());

//        UserViewPostRequest userViewPostRequest = new UserViewPostRequest();
    }
}