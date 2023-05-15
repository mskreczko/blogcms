package pl.mskreczko.blogcms.application.services.comment;

import pl.mskreczko.blogcms.application.ports.in.comment.*;

public interface CommentService extends CreateComment, DeleteComment, GetCommentsByPost,
        ChangeDislikesCount, ChangeLikesCount {
}
