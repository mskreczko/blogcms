package pl.mskreczko.blogcms.infrastructure.adapters.persistence.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mskreczko.blogcms.application.domain.Post;
import pl.mskreczko.blogcms.application.domain.User;
import pl.mskreczko.blogcms.application.ports.out.PostPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PostRepository implements PostPort {

    private final SpringDataPostRepository repository;

    @Override
    public void save(Post post) {
        repository.save(post);
    }

    @Override
    public Optional<Post> loadById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Post> loadByPostAuthor(User user) {
        return repository.findByAuthor(user);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
