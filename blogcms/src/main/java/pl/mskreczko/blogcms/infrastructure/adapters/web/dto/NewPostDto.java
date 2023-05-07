package pl.mskreczko.blogcms.infrastructure.adapters.web.dto;

import java.util.UUID;

public record NewPostDto(UUID authorId, String title, String content) {
}
