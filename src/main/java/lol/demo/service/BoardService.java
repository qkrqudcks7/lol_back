package lol.demo.service;

import lol.demo.S3.S3FileUploadService;
import lol.demo.domain.Board;
import lol.demo.domain.User;
import lol.demo.payload.request.BoardRequest;
import lol.demo.payload.response.BoardResponse;
import lol.demo.repository.BoardRepository;
import lol.demo.repository.UserRepository;
import lol.demo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    public final UserRepository userRepository;

    public final BoardRepository boardRepository;

    private final S3FileUploadService s3FileUploadService;


    public ResponseEntity<?> addBoard(UserDetailsImpl userDetails, BoardRequest boardRequest, MultipartFile multipartFile) throws IOException {
        if (multipartFile != null) {
            boardRequest.setImgUrl(s3FileUploadService.upload(multipartFile));
        }

        User user = userRepository.findById(userDetails.getId()).get();
        Board board = Board.builder()
                .user(user)
                .subject(boardRequest.getSubject())
                .text(boardRequest.getText())
                .imgUrl(boardRequest.getImgUrl())
                .localDateTime(LocalDateTime.now())
                .viewCount(0L)
                .build();
        boardRepository.save(board);

        return ResponseEntity.ok("글 작성 완료");
    }

    public ResponseEntity<?> getOneBoard(Long id) {
        Board m = boardRepository.findById(id).get();
        m.plusViewCount();
        boardRepository.save(m);
        BoardResponse boardResponse = new BoardResponse(m.getId(), m.getUser().getUsername(), m.getSubject(), m.getText(),m.getImgUrl(), m.getViewCount(),m.getUser().getEmail(), m.getLocalDateTime());

        return new ResponseEntity<>(boardResponse, HttpStatus.OK);
    }

    public ResponseEntity<String> modifyBoard(BoardResponse boardResponse) {
        User user = userRepository.findById(boardResponse.getId()).get();
        Board build = Board.builder()
                .id(boardResponse.getId())
                .subject(boardResponse.getSubject())
                .text(boardResponse.getText())
                .imgUrl(boardResponse.getImgUrl())
                .viewCount(boardResponse.getViewCount())
                .user(user)
                .localDateTime(boardResponse.getLocalDateTime()).build();
        boardRepository.save(build);

        return ResponseEntity.ok("수정 완료");
    }
}
