package pl.mskreczko.blogcms.infrastructure.adapters.web.dto;

import java.util.List;

public record PostDto(String author, String title, String content, List<CommentDto> comments) {
}
