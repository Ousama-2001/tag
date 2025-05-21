package be.iccbxl.pid.reservationsspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.iccbxl.pid.reservationsspringboot.model.Troupe;
import be.iccbxl.pid.reservationsspringboot.repository.TroupeRepository;

import java.util.List;

@Service
public class TroupeService {
    @Autowired
    private TroupeRepository troupeRepository;

    public List<Troupe> getAllTroupes() {
        return (List<Troupe>) troupeRepository.findAll();
    }

    public Troupe getTroupe(Long id) {
        return troupeRepository.findById(id).orElse(null);
    }
    
}
