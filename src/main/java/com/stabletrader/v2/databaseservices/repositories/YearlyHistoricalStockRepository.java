package com.stabletrader.v2.databaseservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stabletrader.v2.databaseservices.entities.YearlyHistoricalStock;

public interface YearlyHistoricalStockRepository extends JpaRepository<YearlyHistoricalStock, Integer>{

}
