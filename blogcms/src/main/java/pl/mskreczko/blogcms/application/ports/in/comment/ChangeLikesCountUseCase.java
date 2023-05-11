package pl.mskreczko.blogcms.application.ports.in.comment;

import java.util.UUID;

public interface ChangeLikesCountUseCase {
<<<<<<< HEAD
    void changeThumbsUpCount(UUID commentId, Integer countChange);
=======
    void changeLikesCount(UUID commentId, Integer countChange);
>>>>>>> 96896ec (implement comment's likes and dislikes)
}
