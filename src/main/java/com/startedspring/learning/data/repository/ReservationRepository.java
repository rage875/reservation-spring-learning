package com.startedspring.learning.data.repository;

import com.startedspring.learning.data.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
