package com.example.mysql.repository;

import com.example.mysql.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface    ReservationRepository extends JpaRepository<Reservation, Long> {
    @Override
    List<Reservation> findAll();


    List<Reservation> findReservationsByUserIdEqualsOrderByReservationStartDateDesc(Long userId);


    List<Reservation> findReservationsByBookIdEqualsOrderByReservationStartDateDesc(Long bookId);

    List<Reservation> findReservationsByBookIdEqualsAndUserIdEquals (Long bookId, Long userId);


    @Query("select u from Reservation u where u.reservationEndDate >= ?1")
    List<Reservation> findReservationsByReservationEndDateAfter(LocalDate date);

    List<Reservation> findReservationsByReservationEndDateBefore(LocalDate date);

    @Override
    void deleteById(Long id);
}