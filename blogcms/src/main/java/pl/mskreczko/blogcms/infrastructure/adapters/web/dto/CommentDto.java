package pl.mskreczko.blogcms.infrastructure.adapters.web.dto;

import java.time.LocalDateTime;

public record CommentDto(String author, String content, LocalDateTime createdAt, Integer likesCount, Integer dislikesCount) {
}
