package lol.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentLike {

    @Id @GeneratedValue
    @Column(name = "comment_like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comments_id", nullable = false)
    private Comments comments;

    private Boolean likeState = false;

    public CommentLike(User user, Comments comments) {
        this.user = user;
        this.comments = comments;
        this.likeState = false;
    }

    public void changeLike() {
        if (this.likeState == false){
            this.likeState = true;
            this.comments.plusLikeCount();
        }
        else {
            this.likeState = false;
            this.comments.minusLikeCount();
        }

    }
}
