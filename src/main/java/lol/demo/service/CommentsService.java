package lol.demo.service;

import lol.demo.domain.Board;
import lol.demo.domain.Comments;
import lol.demo.domain.User;
import lol.demo.payload.request.CommentsRequest;
import lol.demo.payload.response.CommentResponse;
import lol.demo.repository.BoardRepository;
import lol.demo.repository.CommentsRepository;
import lol.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentsService {

    private final UserRepository userRepository;
    private final CommentsRepository commentsRepository;
    private final BoardRepository boardRepository;

    public ResponseEntity<?> addComments(Long id, CommentsRequest commentsRequest) {
        Board board = boardRepository.findById(id).get();
        User user = userRepository.findById(commentsRequest.getId()).get();
        Comments comments = Comments.builder()
                .board(board)
                .user(user)
                .comment(commentsRequest.getComment())
                .localDateTime(LocalDateTime.now())
                .build();
        commentsRepository.save(comments);

        return ResponseEntity.ok("댓글이 등록되었습니다.");

    }

    public ResponseEntity<?> getComments(Long id) {
        Board board = boardRepository.findById(id).get();
        List<Comments> comments = board.getComments();
        List<CommentResponse> collect = comments.stream()
                .map(m -> new CommentResponse(m.getId(),m.getUser().getUsername(), m.getComment(), m.getLocalDateTime()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }
}
