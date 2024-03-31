package africa.semicolon.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Views")
public class View {
    private LocalDateTime timeOfView = LocalDateTime.now();
    @DBRef
    private User viewer;
    private String id;
}
