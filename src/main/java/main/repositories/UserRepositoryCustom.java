package main.repositories;

import main.entites.Role;
import main.entites.User;

import java.util.List;

/**
 * Created by Michal
 * 2015-06-08.
 */
public interface UserRepositoryCustom {
    List<Role> rolesForUser(User user);
}
