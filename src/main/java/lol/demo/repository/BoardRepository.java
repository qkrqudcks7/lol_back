package lol.demo.repository;

import lol.demo.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardRepositoryExtension {
}
