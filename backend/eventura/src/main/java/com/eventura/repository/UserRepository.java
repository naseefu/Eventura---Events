package com.eventura.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventura.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String username);

	boolean existsByEmail(String email);

	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.firstname = :firstname, u.midname = :midname, u.lastname = :lastname, u.dob = :dob WHERE u.id = :id")
	void updateEdited(@Param("firstname") String firstname, 
	                  @Param("midname") String midname, 
	                  @Param("lastname") String lastname, 
	                  @Param("dob") LocalDate dob, 
	                  @Param("id") Long id);


	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.verified = :verified, u.otp = :otp WHERE u.id = :id")
	void updateVerification(@Param("verified") boolean verified, @Param("otp") String otp, @Param("id") Long id);

	
}
