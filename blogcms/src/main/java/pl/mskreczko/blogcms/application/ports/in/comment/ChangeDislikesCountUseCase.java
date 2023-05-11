package pl.mskreczko.blogcms.application.ports.in.comment;

import java.util.UUID;

public interface ChangeDislikesCountUseCase {
    void changeThumbsDownCount(UUID commentId, Integer countChange);
}
