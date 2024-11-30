package com.eventura.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventura.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	
	Page<Event> findAll(Pageable pageable);
	
	@Query(value = "SELECT * FROM event " +
	        "WHERE (:eventInput IS NULL OR " +
	        " (eventname LIKE CONCAT('%', :eventInput, '%') " +
	        " OR eventtype LIKE CONCAT('%', :eventInput, '%') " +
	        " OR eventmethod LIKE CONCAT('%', :eventInput, '%') " +
	        " OR category LIKE CONCAT('%', :eventInput, '%') " +
	        " OR eventcode LIKE CONCAT('%', :eventInput, '%') " +
	        " OR FIND_IN_SET(:eventInput, tag) > 0 " +
	        " OR FIND_IN_SET(:eventInput, eventveriety) > 0)) " +
	        "AND (:locationInput IS NULL OR " +
	        " location LIKE CONCAT('%', :locationInput, '%'))",
	    nativeQuery = true)
	List<Event> searchEvents(@Param("eventInput") String eventInput, 
	                         @Param("locationInput") String locationInput);
	
	@Query(value = "SELECT * FROM event ORDER BY id DESC LIMIT :eventNum",nativeQuery = true)
	List<Event> findSomeEvent(@Param("eventNum")Long eventNum);

	
	@Query(value = "SELECT * FROM event " +
	        "WHERE (:eventInput IS NULL OR " +
	        " (eventname LIKE CONCAT('%', :eventInput, '%') " +
	        " OR eventtype LIKE CONCAT('%', :eventInput, '%') " +
	        " OR eventmethod LIKE CONCAT('%', :eventInput, '%') " +
	        " OR category LIKE CONCAT('%', :eventInput, '%') " +
	        " OR eventcode LIKE CONCAT('%', :eventInput, '%') " +
	        " OR FIND_IN_SET(:eventInput, tag) > 0 " +
	        " OR FIND_IN_SET(:eventInput, eventveriety) > 0))"
	        + "AND id>:eventId LIMIT 4",
	    nativeQuery = true)
	List<Event> searchNextRecEvents(@Param("eventInput") String eventInput,Long eventId);
	
	@Query(value = "SELECT * FROM event " +
	        "WHERE (:eventInput IS NULL OR " +
	        " (eventname LIKE CONCAT('%', :eventInput, '%') " +
	        " OR eventtype LIKE CONCAT('%', :eventInput, '%') " +
	        " OR eventmethod LIKE CONCAT('%', :eventInput, '%') " +
	        " OR category LIKE CONCAT('%', :eventInput, '%') " +
	        " OR eventcode LIKE CONCAT('%', :eventInput, '%') " +
	        " OR FIND_IN_SET(:eventInput, tag) > 0 " +
	        " OR FIND_IN_SET(:eventInput, eventveriety) > 0))"
	        + "AND id<:eventId LIMIT 4",
	    nativeQuery = true)
	List<Event> searchPrevRecEvents(@Param("eventInput") String eventInput,Long eventId);

	List<Event> findByUserId(Long userId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE event e SET e.currentseats = :currentseats WHERE e.id = :id",nativeQuery = true)
	void updateEventCount(@Param("currentseats") Long currentseats,
	                      @Param("id") Long id);

	boolean existsByIdAndUserId(Long eventId, Long userId);


	
}
