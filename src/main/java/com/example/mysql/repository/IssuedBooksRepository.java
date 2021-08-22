package com.example.mysql.repository;

import com.example.mysql.model.IssueStatus;
import com.example.mysql.model.IssuedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IssuedBooksRepository extends JpaRepository <IssuedBooks, Long> {
    //@Override
    //List<IssuedBooks> findAll();


    List<IssuedBooks> findIssuedBooksByUserIdEqualsOrderByIssueStartDateAsc(Long userId);

    List<IssuedBooks> findIssuedBooksByBookIdEquals (Long bookId);

    List<IssuedBooks> findIssuedBooksByBookIdEqualsAndIssueStatusEquals (Long bookId, IssueStatus issueStatus);

    List<IssuedBooks> findIssuedBooksByIssueStatusEquals (IssueStatus issueStatus);

}
    