package com.kirthisamson.votingsystem.repositories;

import com.kirthisamson.votingsystem.dao.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Integer> {

    // @Query("SELECT u FROM User u WHERE u.name = ?1")
    User findByName(String name);
    User findByEmail(String email);
}
