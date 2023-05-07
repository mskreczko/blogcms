package pl.mskreczko.blogcms.application.services.comment;

import pl.mskreczko.blogcms.application.ports.in.comment.CreateCommentUseCase;
import pl.mskreczko.blogcms.application.ports.in.comment.DeleteCommentUseCase;
import pl.mskreczko.blogcms.application.ports.in.comment.GetCommentsByPostUseCase;

public interface CommentService extends CreateCommentUseCase, DeleteCommentUseCase, GetCommentsByPostUseCase {
}
