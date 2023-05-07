package pl.mskreczko.blogcms.application.services.post;

import pl.mskreczko.blogcms.application.ports.in.post.CreatePostUseCase;
import pl.mskreczko.blogcms.application.ports.in.post.DeletePostUseCase;

public interface PostService extends CreatePostUseCase, DeletePostUseCase {
}
