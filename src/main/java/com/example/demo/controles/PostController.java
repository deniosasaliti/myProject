package com.example.demo.controles;

import com.example.demo.Dto.Comment.CommentDto;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;


    @PostMapping("/addComment")
    public HttpStatus addCommend(@RequestBody() CommentDto commentDto,
                                 Authentication authentication){
        postService.save(commentDto);
        return  HttpStatus.OK;
    }
}
