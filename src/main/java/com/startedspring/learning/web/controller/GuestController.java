package com.startedspring.learning.web.controller;

import com.startedspring.learning.data.entity.Guest;
import com.startedspring.learning.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guests")
public class GuestController {
    @Autowired
    private GuestRepository repository;

    @GetMapping
    public Iterable<Guest> getGuests() {
        return this.repository.findAll();
    }
}
