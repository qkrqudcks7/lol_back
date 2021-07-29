package lol.demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class CommentsRequest {

    private Long id;
    @NotBlank
    private String comment;
}
