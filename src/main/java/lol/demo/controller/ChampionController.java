package lol.demo.controller;

import lol.demo.domain.Champion;
import lol.demo.payload.response.ChampionResponse;
import lol.demo.repository.ChampionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ChampionController {

    public final ChampionRepository championRepository;

    @GetMapping("/allchampion")
    public ResponseEntity<?> getAllBoard() {
        List<Champion> all = championRepository.findAll();
        List<ChampionResponse> collect = all.stream()
                .map(m -> new ChampionResponse(m.getId(), m.getName(), m.getPosition(), m.getPassive(), m.getPassive_text(), m.getPassive_img(), m.getQ(), m.getQ_text(), m.getQ_img(), m.getW(), m.getW_text(), m.getW_img(), m.getE(), m.getE_text(), m.getE_img(), m.getR(), m.getR_text(), m.getR_img(), m.getImg(), m.getTier()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);

    }
}
