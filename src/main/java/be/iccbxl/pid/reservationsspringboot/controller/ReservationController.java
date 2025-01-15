package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.Reservation;
import be.iccbxl.pid.reservationsspringboot.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService service;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return service.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        return service.getReservation(id);
    }

    @PostMapping
    public void addReservation(@RequestBody Reservation reservation) {
        service.addReservation(reservation);
    }

    @PutMapping("/{id}")
    public void updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        service.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        service.deleteReservation(id);
    }
}
