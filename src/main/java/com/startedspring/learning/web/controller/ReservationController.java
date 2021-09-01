package com.startedspring.learning.web.controller;

import com.startedspring.learning.data.entity.Reservation;
import com.startedspring.learning.data.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationRepository repository;

    @GetMapping
    public Iterable<Reservation> getRooms() {
        return this.repository.findAll();
    }
}
