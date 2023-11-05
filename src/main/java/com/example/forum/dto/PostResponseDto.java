package com.example.forum.dto;

import com.example.forum.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String username;
    private String contents;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.username = post.getUsername();
        this.contents = post.getContents();
    }
}
