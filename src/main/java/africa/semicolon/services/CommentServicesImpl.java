package africa.semicolon.services;

import africa.semicolon.data.model.Comment;
import africa.semicolon.data.model.Post;
import africa.semicolon.data.repository.CommentRepository;
import africa.semicolon.dto.request.UserCommentPostRequest;
import africa.semicolon.dto.request.UserDeleteCommentRequest;
import africa.semicolon.exceptions.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Comment findById(String postId) {
        Optional<Comment> comment = commentRepository.findById(postId);
        if (comment.isEmpty()) throw new CommentNotFoundException("Comment not found");
        return comment.get();
    }

    @Override
    public Comment deleteComment(UserDeleteCommentRequest userDeleteCommentRequest) {
        Comment comment = findById(userDeleteCommentRequest.getCommentId());
        commentRepository.delete(comment);
        return comment;
    }
}