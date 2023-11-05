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

    // 게시물 생성
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

    // 게시물 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getPost() {
        // 맵 자료구조를 리스트로
        List<PostResponseDto> responseList = postList.values().stream().map(PostResponseDto::new).toList();

        return responseList;
    }

    // 게시물 수정
    @PutMapping("/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        // 해당 post가 DB에 존재하는지 확인
        if (postList.containsKey(id)) {
            //해당 게시물 가져오기
            Post post = postList.get(id);

            // post 수정
            post.update(requestDto);
            return post.getId();
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }

    //게시물 삭제
    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        // 해당 post가 DB에 존재하는지 확인
        if (postList.containsKey(id)) {
            postList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }
}
