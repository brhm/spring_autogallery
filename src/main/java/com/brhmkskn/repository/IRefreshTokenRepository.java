package com.brhmkskn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brhmkskn.model.RefreshToken;

@Repository
public interface IRefreshTokenRepository  extends JpaRepository<RefreshToken, Long>{

	@Query("SELECT r FROM RefreshToken r WHERE r.refreshToken = :refreshToken")
	Optional<RefreshToken> findByRefreshToken(@Param("refreshToken") String refreshToken);
}
