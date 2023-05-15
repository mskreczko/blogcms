package pl.mskreczko.blogcms.application.services.post;

import pl.mskreczko.blogcms.application.ports.in.post.CreatePost;
import pl.mskreczko.blogcms.application.ports.in.post.DeletePost;
import pl.mskreczko.blogcms.application.ports.in.post.GetPost;
import pl.mskreczko.blogcms.application.ports.in.post.GetPostsByAuthor;

public interface PostService extends CreatePost, DeletePost, GetPost, GetPostsByAuthor {
}
