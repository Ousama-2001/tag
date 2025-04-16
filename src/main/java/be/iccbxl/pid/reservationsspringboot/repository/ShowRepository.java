package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.Location;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowRepository extends CrudRepository<Show, Long> {
    Show findBySlug(String slug);

    Show findByTitle(String title);

    List<Show> findByLocation(Location location);
    @Query("SELECT s FROM Show s JOIN s.tags t WHERE t.tag LIKE %:keyword%")
    List<Show> findByTagKeyword(@Param("keyword") String keyword);
}
