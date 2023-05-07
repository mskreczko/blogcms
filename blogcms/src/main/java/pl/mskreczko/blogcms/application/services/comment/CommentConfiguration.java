package pl.mskreczko.blogcms.application.services.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

@Configuration
@RequiredArgsConstructor
public class CommentConfiguration {

    private final UserPort userPort;
    private final CommentPort commentPort;
    private final PostPort postPort;
    private final UUIDProvider uuidProvider;

    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl(commentPort, postPort, userPort, uuidProvider);
    }
}
