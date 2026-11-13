package com.brhmkskn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brhmkskn.model.User;


public interface IUserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
}
