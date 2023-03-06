package com.stabletrader.v2.databaseservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stabletrader.v2.databaseservices.repositories.ExtendedRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories( repositoryBaseClass = ExtendedRepositoryImpl.class )
@EnableTransactionManagement
public class DatabaseservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseservicesApplication.class, args);
	}
}
