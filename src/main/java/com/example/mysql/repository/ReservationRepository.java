package com.example.mysql.repository;

import com.example.mysql.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Long>{
    @Override
    List findAll();
}
