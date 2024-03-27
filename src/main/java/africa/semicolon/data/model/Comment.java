package africa.semicolon.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Comments")
public class Comment {
    private String id;
    @DBRef
    private User commenter;
    private String comment;
    private LocalDateTime timeOfComment;
}
