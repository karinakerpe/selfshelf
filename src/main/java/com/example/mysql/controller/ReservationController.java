package com.example.mysql.controller;

import com.example.mysql.service.BookRecordService;
import com.example.mysql.service.ReservationService;
import com.example.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reservation")
public class ReservationController {
    private final UserService userService;
    private final BookRecordService bookRecordService;
    private final ReservationService reservationService;

}
