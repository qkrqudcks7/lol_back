package lol.demo.controller;

import lol.demo.payload.request.CommentsRequest;
import lol.demo.payload.request.LikeRequest;
import lol.demo.repository.LikeRepository;
import lol.demo.security.UserDetailsImpl;
import lol.demo.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
public class LikeController {

    private final LikeRepository likeRepository;
    private final LikeService likeService;

    @PostMapping("/liketoboard/{id}/{email}")
    public void addLikeToBoard(@PathVariable("id") Long id,
                               @PathVariable String email) {
        log.info(email);
        likeService.addLikeToBoard(id,email);
    }
}
