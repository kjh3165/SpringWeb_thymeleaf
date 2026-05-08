package com.back.domain.controller;

import com.back.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
}
