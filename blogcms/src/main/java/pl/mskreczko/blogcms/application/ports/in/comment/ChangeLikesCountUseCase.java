package pl.mskreczko.blogcms.application.ports.in.comment;

import java.util.UUID;

public interface ChangeLikesCountUseCase {
    void changeThumbsUpCount(UUID commentId, Integer countChange);
}
