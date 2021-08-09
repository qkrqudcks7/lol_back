package lol.demo.controller;

import lol.demo.domain.Champion;
import lol.demo.payload.response.ChampionResponse;
import lol.demo.repository.ChampionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lol")
public class ChampionController {

    public final ChampionRepository championRepository;
    private final String key = "RGAPI-80064fff-b604-4ae4-9f70-1a9065490f36";

    @GetMapping("/allchampion")
    public ResponseEntity<?> getAllBoard() {
        List<Champion> all = championRepository.findAll();
        List<ChampionResponse> collect = all.stream()
                .map(m -> new ChampionResponse(m.getId(), m.getName(), m.getPosition(), m.getPassive(), m.getPassive_text(), m.getPassive_img(), m.getQ(), m.getQ_text(), m.getQ_img(), m.getW(), m.getW_text(), m.getW_img(), m.getE(), m.getE_text(), m.getE_img(), m.getR(), m.getR_text(), m.getR_img(), m.getImg(), m.getTier()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);

    }

    @GetMapping("/champion/{name}")
    public ResponseEntity<?> getOneChampion(@PathVariable("name") String name) {
        Champion m = championRepository.findByName(name);
        ChampionResponse championResponse = new ChampionResponse(m.getId(), m.getName(), m.getPosition(), m.getPassive(), m.getPassive_text(), m.getPassive_img(), m.getQ(), m.getQ_text(), m.getQ_img(), m.getW(), m.getW_text(), m.getW_img(), m.getE(), m.getE_text(), m.getE_img(), m.getR(), m.getR_text(), m.getR_img(), m.getImg(), m.getTier());

        return new ResponseEntity<>(championResponse,HttpStatus.OK);
    }

    @GetMapping("/findid/{name}")
    public ResponseEntity<?> findId(@PathVariable("name") String name) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + name + "?api_key=" + key, String.class);

        return forEntity;
    }

    @GetMapping("/findleague/{id}")
    public ResponseEntity<?> findLeague(@PathVariable("id") String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + id + "?api_key=" + key, String.class);

        return forEntity;
    }

    @GetMapping("/findmatch/{accountId}")
    public ResponseEntity<?> findMatch(@PathVariable("accountId") String accountId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/" + accountId + "?api_key=" + key + "&beginIndex=" + 0 + "&endIndex=" +19, String.class);

        return forEntity;
    }

    @GetMapping("/finddetailmatch/{matchId}")
    public ResponseEntity<?> findDetailMatch(@PathVariable("matchId") String matchId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://kr.api.riotgames.com/lol/match/v4/matches/" + matchId + "?api_key=" + key, String.class);

        return forEntity;
    }

    @GetMapping("/findnowgame/{id}")
    public ResponseEntity<?> findNowGame(@PathVariable("id") String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://kr.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/" + id + "?api_key=" + key, String.class);

        return forEntity;

    }
}
