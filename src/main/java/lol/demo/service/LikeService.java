package lol.demo.service;

import lol.demo.domain.Board;
import lol.demo.domain.Like;
import lol.demo.domain.User;
import lol.demo.payload.request.LikeRequest;
import lol.demo.repository.BoardRepository;
import lol.demo.repository.LikeRepository;
import lol.demo.repository.UserRepository;
import lol.demo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {

    private final LikeRepository likeRepository;

    private final UserRepository userRepository;

    private final BoardRepository boardRepository;

    public void addLikeToBoard(Long id, String email) {
        User myUser = userRepository.findByEmail(email).get();
        Board myBoard = boardRepository.findById(id).get();
        Like like = Like.builder()
                .board(myBoard)
                .user(myUser)
                .likeState(false)
                .build();
        like.changeLike();
        likeRepository.save(like);
    }
}
