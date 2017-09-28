package com.hkta.educentresystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hkta.educentresystem.entity.Transaction;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE ((t.tutorialCentre is not null and t.tutorialCentre.id = :centreId) or :centreId is null) and (month(t.recordTime) = :month or :month is null) and (year(t.recordTime) = :year or :year is null)")
	Page<Transaction> findByCentreIdMonthYear(@Param("centreId") Long centreId, @Param("month") Integer month, @Param("year") Integer year, Pageable p);

}