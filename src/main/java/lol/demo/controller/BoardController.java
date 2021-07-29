package lol.demo.controller;

import lol.demo.domain.Board;
import lol.demo.payload.request.BoardRequest;
import lol.demo.payload.response.BoardResponse;
import lol.demo.repository.BoardRepository;
import lol.demo.security.UserDetailsImpl;
import lol.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    public final BoardRepository boardRepository;
    public final BoardService boardService;

    @GetMapping("/allboard")
    public ResponseEntity<?> getAllBoard () {
        List<Board> all = boardRepository.findAll();
        List<BoardResponse> collect = all.stream()
                .map(m -> new BoardResponse(m.getId(), m.getUser().getUsername(), m.getSubject(), m.getText(),m.getImgUrl(), m.getViewCount(), m.getLocalDateTime()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    @PostMapping("/addboard")
    public ResponseEntity<?> addBoard(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @Valid @RequestBody BoardRequest boardRequest) {
        return boardService.addBoard(userDetails,boardRequest);
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
}