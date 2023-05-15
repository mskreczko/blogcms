package pl.mskreczko.blogcms.application.ports.in.comment;

import java.util.UUID;

public interface ChangeDislikesCount {
    void changeDislikesCount(UUID commentId, Integer countChange);
}
