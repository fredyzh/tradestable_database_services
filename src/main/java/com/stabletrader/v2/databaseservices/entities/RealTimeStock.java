package com.stabletrader.v2.databaseservices.entities;

import java.time.LocalDateTime;

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
@Table(name = "RealTimeStock", schema = "stockv2", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"stockId", "time" }) })
public class RealTimeStock {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	private Integer id;

	private Integer stockId;

	private LocalDateTime date;

	private Long time;

	private Double open;

	private Double high;

	private Double low;

	private Double close;

	private Double wap;

	private Long volume;

	private Long count;
}
