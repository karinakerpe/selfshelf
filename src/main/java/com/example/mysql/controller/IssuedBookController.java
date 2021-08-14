package com.example.mysql.controller;

import com.example.mysql.service.BookRecordService;
import com.example.mysql.service.ReservationService;
import com.example.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Service
@RequestMapping("/issued")
public class IssuedBookController {
    private final UserService userService;
    private final BookRecordService bookRecordService;
    private final ReservationService reservationService;
}
