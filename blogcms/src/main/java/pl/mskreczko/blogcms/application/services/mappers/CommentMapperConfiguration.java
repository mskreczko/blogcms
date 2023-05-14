package pl.mskreczko.blogcms.application.services.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import pl.mskreczko.blogcms.application.ports.out.UserPort;

@Configuration
@RequiredArgsConstructor
public class CommentMapperConfiguration {

    private final UserPort userPort;

    public CommentMapper commentMapper() {
        return new CommentMapperImpl(userPort);
    }
}
