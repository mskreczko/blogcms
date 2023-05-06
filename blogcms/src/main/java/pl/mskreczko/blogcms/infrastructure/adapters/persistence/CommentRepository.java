package pl.mskreczko.blogcms.infrastructure.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;

@Component
@RequiredArgsConstructor
public class CommentRepository implements CommentPort {

    private final SpringDataCommentRepository repository;
}
