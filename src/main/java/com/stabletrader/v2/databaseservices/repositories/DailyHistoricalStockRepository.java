package com.stabletrader.v2.databaseservices.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.stabletrader.v2.databaseservices.entities.DailyHistoricalStock;

import jakarta.persistence.TypedQuery;

public interface DailyHistoricalStockRepository extends ExtendedRepository<DailyHistoricalStock, Integer>{
	List <DailyHistoricalStock> findByStockId(Integer id);
	
	@Query("SELECT s FROM DailyHistoricalStock s WHERE s.stockId=?1 AND s.date BETWEEN ?2 AND ?3 ORDER BY s.date" )
	List <DailyHistoricalStock> findHisByDuration(Integer id, LocalDate start, LocalDate end);
	
	default List <DailyHistoricalStock> findAllByQuery(String querystr, List<Integer>symbols, LocalDate start, LocalDate end){
		return this.findAllByEm(em -> {
			TypedQuery<DailyHistoricalStock> query = em.createQuery(querystr, DailyHistoricalStock.class);
			query.setParameter(1, symbols);
			query.setParameter(2, start);
			query.setParameter(3, end);
			return (List<DailyHistoricalStock>)query.getResultList();
		});
	}
	
}
