package lol.demo.repository;

import lol.demo.domain.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion,Long> {
}
