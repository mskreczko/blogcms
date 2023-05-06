package pl.mskreczko.blogcms.infrastructure.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mskreczko.blogcms.application.ports.out.PostPort;

@Component
@RequiredArgsConstructor
public class PostRepository implements PostPort {

    private final SpringDataPostRepository repository;
}
