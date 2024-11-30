package com.eventura.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventura.entity.WebsiteFeedback;

@Repository
public interface FeedbackRepository extends JpaRepository<WebsiteFeedback, Long> {

	@Query("FROM WebsiteFeedback wf WHERE wf.star >= 4")
	List<WebsiteFeedback> findSomeFeedback(Pageable pageable);

	
}
