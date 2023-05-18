package pl.mskreczko.blogcms.application.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    private String content;

    private LocalDateTime createdAt;

    private Integer likesCount = 0;

    private Integer dislikesCount = 0;

    @Version
    private Long version;
    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void changeLikesCount(Integer countChange) throws IllegalArgumentException {
        if (countChange != -1 && countChange != 1) {
            throw new IllegalArgumentException("countChange value is -1 or 1");
        }
        likesCount += countChange;
    }

    public void changeDislikesCount(Integer countChange) throws IllegalArgumentException {
        if (countChange != -1 && countChange != 1) {
            throw new IllegalArgumentException("countChange value is -1 or 1");
        }
        dislikesCount += countChange;
    }

    public Comment(UUID id, User author, Post post, String content) {
        this.id = id;
        this.author = author;
        this.post = post;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
