package pl.mskreczko.blogcms.application.services.comment;

import pl.mskreczko.blogcms.application.ports.in.comment.*;

public interface CommentService extends CreateCommentUseCase, DeleteCommentUseCase, GetCommentsByPostUseCase,
        ChangeDislikesCountUseCase, ChangeLikesCountUseCase {
}
