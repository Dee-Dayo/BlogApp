package africa.semicolon.services;

import africa.semicolon.data.model.Comment;
import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.data.repository.CommentRepository;
import africa.semicolon.data.repository.PostRepository;
import africa.semicolon.data.repository.UserRepository;
import africa.semicolon.data.repository.ViewRepository;
import africa.semicolon.dto.request.*;
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
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentServices commentServices;
    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    public void setUp(){
        postRepository.deleteAll();
        viewRepository.deleteAll();
        userRepository.deleteAll();
        commentRepository.deleteAll();
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

    @Test
    public void create_onePost_twoCommentOnPost(){
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
        assertEquals(0, post.getComments().size());

        UserCommentPostRequest userCommentPostRequest = new UserCommentPostRequest();
        userCommentPostRequest.setPostId(post.getId());
        userCommentPostRequest.setCommenter(user);
        userCommentPostRequest.setComment("This post is trash");
        postServices.commentPost(userCommentPostRequest);

        post = postServices.findPostById(post.getId());
        assertEquals(1, post.getComments().size());
        assertEquals("This post is trash", post.getComments().getFirst().getComment());

        userCommentPostRequest.setComment("This post is ok");
        postServices.commentPost(userCommentPostRequest);

        post = postServices.findPostById(post.getId());
        assertEquals(2, post.getComments().size());
        assertEquals("This post is ok", post.getComments().get(1).getComment());
    }

    @Test
    public void createOnePost_addOneComment_DeleteCcomment(){
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
        assertEquals(0, post1.getComments().size());

        UserCommentPostRequest userCommentPostRequest = new UserCommentPostRequest();
        userCommentPostRequest.setPostId(post.getId());
        userCommentPostRequest.setCommenter(user);
        userCommentPostRequest.setComment("Good post");
        postServices.commentPost(userCommentPostRequest);

        post1 = postServices.findPostById(post1.getId());
        assertEquals(1, commentRepository.count());
        assertEquals(1, post1.getComments().size());
        assertEquals("Good post", post1.getComments().getFirst().getComment());

        UserDeleteCommentRequest userDeleteCommentRequest = new UserDeleteCommentRequest();
        Comment comment = commentServices.findById(post1.getComments().get(0).getId());
        userDeleteCommentRequest.setCommentId(comment.getId());
        userDeleteCommentRequest.setPostId(post1.getId());
        postServices.deleteComment(userDeleteCommentRequest);

        post1 = postServices.findPostById(post1.getId());
        assertEquals(0, post1.getComments().size());
        assertEquals(0, commentRepository.count());
    }
}