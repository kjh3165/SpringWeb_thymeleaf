package com.back.domain.controller;

import com.back.domain.entity.Post;
import com.back.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping("/posts/write")
    @ResponseBody
    public String showWrite() {
        return getWriteFormHtml("", "", "");
    }

    @PostMapping("/posts/doWrite")
    @ResponseBody
    @Transactional
    public String write(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content
    ) {
        if (title.isBlank()) return getWriteFormHtml("제목을 입력해주세요.", title, content);
        if( content.isBlank()) return getWriteFormHtml("내용을 입력해주세요.", title, content);

        Post post = postService.write(title, content);
        return "%d 번 글이 생성 되었습니다.".formatted(post.getId());
    }

    private String getWriteFormHtml(
            String errorMessage,
            String title,
            String content
    ) {
        return """
                <div style="color:red;">%s</div>
                <form action="/posts/doWrite" method="POST">
                  <input type="text" name="title" placeholder="제목" value="%s" autofocus>
                  <br>
                  <textarea name="content" placeholder="내용">%s</textarea>
                  <br>
                  <input type="submit" value="작성">
                </form>
                """.formatted(errorMessage, title, content);
    }
}
