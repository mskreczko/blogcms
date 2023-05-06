package pl.mskreczko.blogcms.application.ports.out;

import pl.mskreczko.blogcms.application.domain.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentPort {
    void save(Comment comment);
    List<Comment> loadCommentByPostId(UUID postId);
}
