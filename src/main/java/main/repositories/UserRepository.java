package main.repositories;

import main.entites.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michal
 * 2015-06-08.
 */
public interface UserRepository extends CrudRepository<User, Long>{

    User findByUsername(String username);

}
