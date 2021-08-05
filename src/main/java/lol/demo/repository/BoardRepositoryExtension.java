package lol.demo.repository;

import lol.demo.domain.Board;

import java.util.List;

public interface BoardRepositoryExtension {
    List<Board> findByViewCount();

    List<Board> findByKeyword(String keyword);
}
