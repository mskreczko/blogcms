package pl.mskreczko.blogcms.infrastructure.adapters.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PostDto(String author, String title, String content, List<CommentDto> comments, LocalDateTime createdAt) {
}
