package pl.mskreczko.blogcms.infrastructure.adapters.web.dto;

import java.util.UUID;

public record NewCommentDto(UUID authorId, String content) {
}
