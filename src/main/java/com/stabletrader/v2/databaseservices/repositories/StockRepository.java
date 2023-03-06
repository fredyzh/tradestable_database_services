package com.stabletrader.v2.databaseservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stabletrader.v2.databaseservices.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>{

}
