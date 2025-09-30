package com.ridge.geervliet.novi.dekookassistent.repository;

import com.ridge.geervliet.novi.dekookassistent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String userName);
    boolean existsByEmail(String email);
    void deleteByUsername(String userName);

}