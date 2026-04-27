package com.example.myblog.board;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardPersistRepository {

    private final EntityManager em;

    @Transactional
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    public List<Board> findAll() {
        String jpql = """
                SELECT b FROM Board b ORDER BY b.createdAt DESC
                """;
        List<Board> boardList = em.createQuery(jpql, Board.class).getResultList();
        return boardList;
    }

    public Board findById(Integer id) {

        String jpql = """
                SELECT b FROM Board b WHERE b.id = :id
                """;
        Board board = em.createQuery(jpql, Board.class)
        .setParameter("id", id) // 파리미터 설계
        .getSingleResult(); // Board 하나만 반환함 --> getSingleResult()
        // 이렇게 적어도 됨 하지만 jpql을 요구할 수 있음
//        Board board = em.find(Board.class, id);
        return board;
    }
}
