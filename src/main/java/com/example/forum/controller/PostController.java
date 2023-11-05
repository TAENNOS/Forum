package com.example.forum.controller;

import com.example.forum.dto.PostRequestDto;
import com.example.forum.dto.PostResponseDto;
import com.example.forum.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class PostController {
    private final Map<Long, Post> postList = new HashMap<>();

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        // entity로 변환
        Post post = new Post(requestDto);

        // Post Max ID Check
        Long maxId = postList.size() > 0 ? Collections.max(postList.keySet()) + 1 : 1;
        post.setId(maxId);

        // DB 저장
        postList.put(post.getId(), post);

        // entity를 Dto로 담아서 반환
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPost() {
        // 맵 자료구조를 리스트로
        List<PostResponseDto> responseList = postList.values().stream().map(PostResponseDto::new).toList();

        return responseList;
    }
}
