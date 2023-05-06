package pl.mskreczko.blogcms.infrastructure.adapters.web.dto;

import java.util.UUID;

public record NewCommentDto(UUID postId, UUID authorId, String content) {
}
