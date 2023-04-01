package com.ozcsoft.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty(message = "Bos Birakmayiniz")
    private String title;
    private String url;
    @NotEmpty
    private String content;
    @NotEmpty
    private String shortDescription;
    private LocalDateTime createOn;
    private LocalDateTime updatedOn;
    private Set<CommentDto> comments;
}
