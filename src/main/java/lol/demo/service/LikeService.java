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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;

    private final UserRepository userRepository;

    private final BoardRepository boardRepository;

    public void addLikeToBoard(Long id, String email) {
        List<Like> all = likeRepository.findAll();
        User myUser = userRepository.findByEmail(email).get();
        Board myBoard = boardRepository.findById(id).get();

        for (Like i : all) {
            if (i.getBoard().getId().equals(id) && i.getUser().getEmail().equals(email)) {
                likeRepository.deleteById(i.getId());
                myBoard.minusLikeCount();

                return;
            }
        }

        Like like = new Like(myBoard,myUser);
        like.changeLike();
        likeRepository.save(like);
    }
}
