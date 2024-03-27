package africa.semicolon.data.repository;

import africa.semicolon.data.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void canCreatePost_countIsOne(){
        Post post = new Post();
        post.setId("1");
        post.setTitle("title");
        post.setContent("content");

        postRepository.save(post);

        assertEquals(1, postRepository.count());
    }

}