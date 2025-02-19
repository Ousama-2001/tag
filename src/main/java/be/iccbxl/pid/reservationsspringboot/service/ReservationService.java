package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Reservation;
import be.iccbxl.pid.reservationsspringboot.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        repository.findAll().forEach(reservations::add);
        return reservations;
    }

    public Reservation getReservation(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addReservation(Reservation reservation) {
        repository.save(reservation);
    }

    public void updateReservation(Long id, Reservation reservation) {
        repository.save(reservation);
    }

    public void deleteReservation(Long id) {
        repository.deleteById(id);
    }
}
