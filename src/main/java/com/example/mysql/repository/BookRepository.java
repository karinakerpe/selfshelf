package com.example.mysql.repository;

import com.example.mysql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

}

