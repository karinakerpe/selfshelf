package com.example.mysql.service;

import com.example.mysql.repository.IssuedBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class IssuedBookService {
    @Autowired
    private final IssuedBooksRepository issuedBooksRepository;

}
