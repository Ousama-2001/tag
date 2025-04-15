package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.Tag;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;
public interface TagRepository extends CrudRepository<Tag, Long> {
    Optional<Tag> findByTag(String tag);
}
