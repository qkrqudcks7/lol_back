package lol.demo.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lol.demo.domain.Board;
import lol.demo.domain.QBoard;

import java.util.List;

public class BoardRepositoryExtensionImpl implements BoardRepositoryExtension {
    private final JPAQueryFactory queryFactory;

    public BoardRepositoryExtensionImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Board> findByViewCount() {
        QBoard board = QBoard.board;

        QueryResults<Board> queryResults = (QueryResults<Board>) queryFactory.from(board)
                .orderBy(board.viewCount.desc()).limit(3).fetchResults();

        return queryResults.getResults();
    }

    @Override
    public List<Board> findByKeyword(String keyword) {
        QBoard board = QBoard.board;

        QueryResults<Board> where = (QueryResults<Board>) queryFactory.from(board).where(board.subject.contains(keyword).or(board.text.contains(keyword)
                .or(board.user.username.contains(keyword)))).fetchResults();

        return where.getResults();
    }
}
