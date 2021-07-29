package lol.demo.service;

import lol.demo.domain.Board;
import lol.demo.domain.Comments;
import lol.demo.domain.User;
import lol.demo.payload.request.CommentsRequest;
import lol.demo.repository.BoardRepository;
import lol.demo.repository.CommentsRepository;
import lol.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

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
}
