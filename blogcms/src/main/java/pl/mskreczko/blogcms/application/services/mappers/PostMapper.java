package pl.mskreczko.blogcms.application.services.mappers;

import pl.mskreczko.blogcms.application.domain.Comment;
import pl.mskreczko.blogcms.application.domain.Post;
import pl.mskreczko.blogcms.application.exceptions.NoSuchEntityException;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.NewPostDto;
import pl.mskreczko.blogcms.infrastructure.adapters.web.dto.PostDto;

import java.util.List;

public interface PostMapper {
    Post newPostDtoToPost(NewPostDto newPostDto) throws NoSuchEntityException;
    PostDto postToPostDto(Post post, List<Comment> comments);
}
