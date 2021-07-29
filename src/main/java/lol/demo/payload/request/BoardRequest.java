package lol.demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class BoardRequest {

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    private String imgUrl;
}
