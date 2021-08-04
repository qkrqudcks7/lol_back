package lol.demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class BoardRequest {

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    private String imgUrl;
}
