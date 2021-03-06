package lol.demo.controller;

import lol.demo.domain.Board;
import lol.demo.payload.request.BoardRequest;
import lol.demo.payload.response.BoardResponse;
import lol.demo.repository.BoardRepository;
import lol.demo.repository.BoardRepositoryExtension;
import lol.demo.security.UserDetailsImpl;
import lol.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
public class BoardController {

    public final BoardRepository boardRepository;
    public final BoardService boardService;

    @GetMapping("/allboard")
    public ResponseEntity<?> getAllBoard () {
        List<Board> all = boardRepository.findAll();
        List<BoardResponse> collect = all.stream()
                .map(m -> new BoardResponse(m.getId(), m.getUser().getUsername(), m.getSubject(), m.getText(),m.getImgUrl(), m.getViewCount(),m.getUser().getEmail(),m.getLikeCount(), m.getLocalDateTime()))
                .collect(Collectors.toList());
        Collections.reverse(collect);
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    @PostMapping("/addboard")
    public ResponseEntity<?> addBoard(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @Valid BoardRequest boardRequest,
                                      MultipartFile multipartFile) throws IOException {
        return boardService.addBoard(userDetails,boardRequest,multipartFile);
    }

    @GetMapping("/oneboard/{id}")
    public ResponseEntity<?> getOneBoard(@PathVariable("id") Long id) {
        return boardService.getOneBoard(id);
    }

    @PutMapping("oneboard")
    public ResponseEntity<?> modifyBoard(@Valid @RequestBody BoardResponse boardResponse) {
        return boardService.modifyBoard(boardResponse);
    }

    @DeleteMapping("/oneboard/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        boardRepository.deleteById(id);
    }

    @GetMapping("/topviewcount")
    public ResponseEntity<?> top3ViewCount() {
        List<Board> result = boardRepository.findByViewCount();
        List<BoardResponse> collect = result.stream()
                .map(m -> new BoardResponse(m.getId(), m.getUser().getUsername(), m.getSubject(), m.getText(), m.getImgUrl(), m.getViewCount(),m.getUser().getEmail(),m.getLikeCount(), m.getLocalDateTime()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(collect,HttpStatus.OK);

    }

    @GetMapping("/findbykeyword/{keyword}")
    public ResponseEntity<?> findByKeyword(@PathVariable("keyword") String keyword) {
        List<Board> result = boardRepository.findByKeyword(keyword);
        List<BoardResponse> collect = result.stream()
                .map(m -> new BoardResponse(m.getId(), m.getUser().getUsername(), m.getSubject(), m.getText(), m.getImgUrl(), m.getViewCount(),m.getUser().getEmail(),m.getLikeCount(), m.getLocalDateTime()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(collect,HttpStatus.OK);
    }
}
