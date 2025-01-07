package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByLastname(String lastname);

    User findById(long id);

    User findByLogin(String login);

    User findByEmail(String email);
}
