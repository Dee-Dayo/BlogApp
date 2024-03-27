package africa.semicolon.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("Users")
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String id;
    @DBRef
    private List<Post> posts = new ArrayList<>();
}
