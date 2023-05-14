package pl.mskreczko.blogcms.application.services.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import pl.mskreczko.blogcms.application.ports.out.UserPort;

@Configuration
@RequiredArgsConstructor
public class PostMapperConfiguration {

    private final UserPort userPort;
    private final CommentMapper commentMapper;

    public PostMapper postMapper() {
        return new PostMapperImpl(userPort, commentMapper);
    }
}
