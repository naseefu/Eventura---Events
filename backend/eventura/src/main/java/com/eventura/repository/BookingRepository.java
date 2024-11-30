package com.eventura.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventura.entity.BookedMembers;

@Repository
public interface BookingRepository extends JpaRepository<BookedMembers, Long> {

	@Modifying
	@Transactional
	@Query(value="UPDATE booking b SET b.bookingdate = :bookingdate, b.bookingstatus=:bookingstatus, b.paymentstatus=:paymentstatus,b.paymentid=:paymentid WHERE b.id = :id",nativeQuery = true)
	void updateEventCount(@Param("bookingdate") LocalDateTime bookingdate,
					  @Param("bookingstatus") String bookingstatus,
					  @Param("paymentstatus") String paymentstatus,
					  @Param("paymentid") String paymentid,
	                  @Param("id") Long id);
	
	BookedMembers findByOrderIdAndUserIdAndEventId(String orderId,Long userId,Long eventId);

	List<BookedMembers> findAllByUserId(Long userId);

	Optional<BookedMembers> findByIdAndUserId(Long bookId, Long userId);

	List<BookedMembers> findAllByEventId(Long eventId);
	
}
