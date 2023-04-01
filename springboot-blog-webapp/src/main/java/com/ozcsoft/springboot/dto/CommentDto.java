package com.ozcsoft.springboot.dto;

import com.ozcsoft.springboot.entity.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private long id;
    @NotEmpty(message = "Isim bos olamaz.")
    private String name;
    @NotEmpty(message = "Email bos olamaz.")
    @Email
    private String email;
    @NotEmpty(message = "Yorum kismi bos olamaz.")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
