package lol.demo.payload.response;

import lol.demo.domain.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ChampionResponse {

    private Long id;

    private String name;

    private Set<Position> position;

    private String passive;

    private String passive_text;

    private String passive_img;

    private String q;

    private String q_text;

    private String q_img;

    private String w;

    private String w_text;

    private String w_img;

    private String e;

    private String e_text;

    private String e_img;

    private String r;

    private String r_text;

    private String r_img;

    private String img;

    private String tier;
}
