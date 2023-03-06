package com.stabletrader.v2.databaseservices.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;



@NoRepositoryBean
public interface ExtendedRepository<T, ID extends Serializable> 
  extends JpaRepository<T, ID> {
 
   List<T> findAllByEm(QueryCallback<List<T>> callback);
}
