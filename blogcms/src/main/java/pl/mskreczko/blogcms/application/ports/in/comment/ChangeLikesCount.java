package pl.mskreczko.blogcms.application.ports.in.comment;

import java.util.UUID;

public interface ChangeLikesCount {
    void changeLikesCount(UUID commentId, Integer countChange);
}
