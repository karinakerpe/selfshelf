package com.example.mysql.service;

import com.example.mysql.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;
}
