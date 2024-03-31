package africa.semicolon.services;

import africa.semicolon.data.model.Comment;
import africa.semicolon.data.repository.CommentRepository;
import africa.semicolon.dto.request.UserCommentPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.utils.Mapper.requestMap;

@Service
public class CommentServicesImpl implements CommentServices{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment saveComment(UserCommentPostRequest userCommentPostRequest) {
        Comment comment = requestMap(userCommentPostRequest);
        commentRepository.save(comment);
        return comment;
    }
}
