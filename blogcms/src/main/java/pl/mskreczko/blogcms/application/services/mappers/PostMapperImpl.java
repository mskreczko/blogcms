package pl.mskreczko.blogcms.application.services.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.blogcms.application.domain.Comment;
import pl.mskreczko.blogcms.application.domain.Post;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.application.ports.out.UserPort;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewPostDto;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.PostDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class PostMapperImpl implements PostMapper{

    private final UserPort userPort;
    private final CommentMapper commentMapper;

    @Override
    public Post newPostDtoToPost(NewPostDto newPostDto) throws NoSuchEntityException {
        final var user = userPort.loadById(newPostDto.authorId()).orElseThrow(NoSuchEntityException::new);
        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(newPostDto.title());
        post.setContent(newPostDto.content());
        return post;
    }

    @Override
    public PostDto postToPostDto(Post post, List<Comment> comments) {
        return new PostDto(post.getAuthor().getUsername(), post.getTitle(), post.getContent(),
                comments.stream().map(commentMapper::commentToCommentDto).collect(Collectors.toList()));
    }
}
