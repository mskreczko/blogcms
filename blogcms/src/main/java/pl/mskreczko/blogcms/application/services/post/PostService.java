package pl.mskreczko.blogcms.application.services.post;

import pl.mskreczko.blogcms.application.ports.in.post.CreatePostUseCase;
import pl.mskreczko.blogcms.application.ports.in.post.DeletePostUseCase;
import pl.mskreczko.blogcms.application.ports.in.post.GetPostUseCase;
import pl.mskreczko.blogcms.application.ports.in.post.GetPostsByAuthorUseCase;

public interface PostService extends CreatePostUseCase, DeletePostUseCase, GetPostUseCase, GetPostsByAuthorUseCase {
}
