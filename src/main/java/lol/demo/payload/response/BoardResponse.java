package lol.demo.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardResponse {
    private Long id;
    private String writer;
    private String subject;
    private String text;
    private String imgUrl;
    private Long viewCount;
    private String userId;
    private int likeCount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM월 dd일 HH시 mm분 ss초")
    private LocalDateTime localDateTime;
}
