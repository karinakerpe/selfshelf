package com.example.mysql.repository;

import com.example.mysql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByTitle(String title);

    List<Book> findAllByIdBetween(Long startId, Long endId);

    @Query(value = "select b from Book  b where b.title=:title AND b.author=:author")
    List<Book> findByTitleAndAuthor(@Param("title") String title, @Param("author") String firstName);

    @Query(nativeQuery = true, value = "SELECT * FROM books ORDER BY rand() limit 1")
    List<Book> findRandomBook();



}


