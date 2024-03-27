package africa.semicolon.data.repository;

import africa.semicolon.data.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataMongoTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void saveOneComment_countIsOne(){
        Comment comment = new Comment();
        comment.setId("id");
        commentRepository.save(comment);

        assertThat(commentRepository.count(), is(1l));
    }
}