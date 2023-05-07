package pl.mskreczko.blogcms.application.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;
    private String content;
    private String title;

    public Post(UUID id, User author, String content, String title) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(author, post.author) && Objects.equals(title, post.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title);
    }
}
