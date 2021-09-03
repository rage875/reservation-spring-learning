package com.startedspring.learning.web.controller;

import com.startedspring.learning.business.domain.RoomReservation;
import com.startedspring.learning.business.service.ReservationService;
import com.startedspring.learning.web.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/room-reservations")
public class RoomReservationRestController {
    private final ReservationService reservationService;

    @Autowired
    public RoomReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<RoomReservation> getRoomReservations(@RequestParam(name="date", required = false)String dateString){
        Date date = DateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsByDate(date);
    }
}
