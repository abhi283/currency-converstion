package com.currency.converstion.currencyconverstion.repository;

import com.currency.converstion.currencyconverstion.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String username);
}
