package pl.mskreczko.blogcms.application.services.post;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import pl.mskreczko.blogcms.application.ports.out.CommentPort;
import pl.mskreczko.blogcms.application.ports.out.PostPort;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.application.services.mappers.PostMapper;
import pl.mskreczko.blogcms.infrastructure.config.uuid.UUIDProvider;

@Configuration
@RequiredArgsConstructor
public class PostConfiguration {

    private final PostPort postPort;
    private final CommentPort commentPort;
    private final UserPort userPort;
    private final UUIDProvider uuidProvider;
    private final PostMapper postMapper;

    public PostService postService() {
        return new PostServiceImpl(postPort, commentPort, userPort, uuidProvider, postMapper);
    }
}
