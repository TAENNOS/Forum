package com.example.forum.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String username;
    private char[] password;
    private String contents;

}
