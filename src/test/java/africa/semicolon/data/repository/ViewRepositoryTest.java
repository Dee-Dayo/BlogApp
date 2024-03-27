package africa.semicolon.data.repository;

import africa.semicolon.data.model.View;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ViewRepositoryTest {

    @Autowired
    private ViewRepository viewRepository;

    @Test
    public void createOneView_countIsOne(){
        View view = new View();
        view.setId("1");

        viewRepository.save(view);

        assertThat(viewRepository.count(), is(1L));
    }
}