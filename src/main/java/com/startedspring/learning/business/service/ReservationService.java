package com.startedspring.learning.business.service;

import com.startedspring.learning.business.domain.RoomReservation;
import com.startedspring.learning.data.entity.Guest;
import com.startedspring.learning.data.entity.Reservation;
import com.startedspring.learning.data.entity.Room;
import com.startedspring.learning.data.repository.GuestRepository;
import com.startedspring.learning.data.repository.ReservationRepository;
import com.startedspring.learning.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(
            RoomRepository roomRepository,
            GuestRepository guestRepository,
            ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsByDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();

        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());

            roomReservationMap.put(room.getId(), roomReservation);
        });

        Iterable<Reservation> reservations =
                this.reservationRepository.findReservationByDate(new java.sql.Date(date.getTime()));

        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);

            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getId());
        });

        List<RoomReservation> roomReservationList = new ArrayList<>();
        for(Long id: roomReservationMap.keySet()) {
            roomReservationList.add(roomReservationMap.get(id));
        }

        roomReservationList.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(
                    RoomReservation o1,
                    RoomReservation o2) {
                if (o1.getRoomName() == o2.getRoomName()) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });

        return roomReservationList;
    }
}
