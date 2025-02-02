package edu.du.sb1118.controller;

import edu.du.sb1118.dto.PostsListResponseDto;
import edu.du.sb1118.dto.PostsResponseDto;
import edu.du.sb1118.dto.PostsSaveRequestDto;
import edu.du.sb1118.dto.PostsUpdateRequestDto;
import edu.du.sb1118.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        if (requestDto.getTitle() == null || requestDto.getTitle().isEmpty() || requestDto.getAuthor() == null || requestDto.getAuthor().isEmpty()) {
            return 0L;
        }
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}
