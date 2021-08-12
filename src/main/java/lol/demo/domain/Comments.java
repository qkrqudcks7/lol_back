package lol.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Comments {

    @Id @GeneratedValue
    @Column(name = "comments_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "comments", cascade = CascadeType.ALL)
    private final List<CommentLike> likes = new ArrayList<>();

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime localDateTime;

    @Column(nullable = false)
    private int likeCount=0;

    public void plusLikeCount() {
        this.likeCount++;
    }
    public void minusLikeCount() {
        this.likeCount-=1;
    }
}
