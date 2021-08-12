package lol.demo.controller;

import lol.demo.domain.Board;
import lol.demo.payload.request.CommentsRequest;
import lol.demo.repository.BoardRepository;
import lol.demo.repository.CommentsRepository;
import lol.demo.service.CommentsLikeService;
import lol.demo.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
public class CommentsConroller {

    private final CommentsService commentsService;

    private final CommentsRepository commentsRepository;

    private final CommentsLikeService commentsLikeService;

    @PostMapping("/comments/{id}")
    public ResponseEntity<?> comments(@PathVariable("id") Long id,
                                      @RequestBody CommentsRequest commentsRequest) {
        return commentsService.addComments(id, commentsRequest);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<?> findAllComments(@PathVariable("id") Long id) {

        return commentsService.getComments(id);
    }
    @DeleteMapping("/comments/{id}")
    public void deleteComments(@PathVariable("id") Long id) {
        commentsRepository.deleteById(id);
    }

    @PostMapping("/liketocomment/{id}/{email}")
    public void addLikeToBoard(@PathVariable("id") Long id,
                               @PathVariable String email) {
        commentsLikeService.addLikeToComments(id,email);
    }
}
