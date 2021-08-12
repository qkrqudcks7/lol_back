package lol.demo.service;

import lol.demo.domain.CommentLike;
import lol.demo.domain.Comments;
import lol.demo.domain.User;
import lol.demo.repository.CommentLikeRepository;
import lol.demo.repository.CommentsRepository;
import lol.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentsLikeService {

    private final CommentsRepository commentsRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;

    public void addLikeToComments(Long id, String email) {
        List<CommentLike> all = commentLikeRepository.findAll();
        User user = userRepository.findByEmail(email).get();
        Comments comments = commentsRepository.findById(id).get();

        for (CommentLike i : all) {
            if (i.getComments().getId().equals(id) && i.getUser().getEmail().equals(email)) {
                commentLikeRepository.deleteById(i.getId());
                comments.minusLikeCount();
                return;
            }
        }
        CommentLike commentLike = new CommentLike(user,comments);
        commentLike.changeLike();
        commentLikeRepository.save(commentLike);
    }
}
