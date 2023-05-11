package pl.mskreczko.blogcms.application.ports.in.comment;

import java.util.UUID;

public interface ChangeDislikesCountUseCase {
<<<<<<< HEAD
    void changeThumbsDownCount(UUID commentId, Integer countChange);
=======
    void changeDislikesCount(UUID commentId, Integer countChange);
>>>>>>> 96896ec (implement comment's likes and dislikes)
}
