package pl.mskreczko.blogcms.application.ports.out;

import pl.mskreczko.blogcms.application.domain.Post;
import pl.mskreczko.blogcms.application.domain.User;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostPort {
    void save(Post post);
    Optional<Post> loadById(UUID id) throws NoSuchEntityException;

    List<Post> loadByPostAuthor(User user) throws NoSuchEntityException;
    void deleteById(UUID id);
}
