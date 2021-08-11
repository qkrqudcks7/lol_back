package lol.demo.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponse {

    private Long id;
    private String writer;
    private String comment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM월 dd일 HH시 mm분 ss초")
    private LocalDateTime localDateTime;
}
