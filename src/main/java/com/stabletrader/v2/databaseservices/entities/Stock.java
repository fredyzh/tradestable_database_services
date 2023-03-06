package com.stabletrader.v2.databaseservices.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock", schema = "stockv2", uniqueConstraints = { @UniqueConstraint(columnNames = { "stockSymbol" }) })
public class Stock {
	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String stockSymbol;
}


