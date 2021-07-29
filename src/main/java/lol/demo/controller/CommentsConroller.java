package lol.demo.controller;

import lol.demo.payload.request.CommentsRequest;
import lol.demo.repository.BoardRepository;
import lol.demo.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class CommentsConroller {

    private final CommentsService commentsService;
    private final BoardRepository boardRepository;

    @PostMapping("/comments/{id}")
    public ResponseEntity<?> comments(@PathVariable("id") Long id,
                                      @RequestBody CommentsRequest commentsRequest) {
        return commentsService.addComments(id, commentsRequest);
    }
}
