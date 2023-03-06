package com.stabletrader.v2.databaseservices.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DurationWithSymbolsRequest {
	private List<String> symbols;
	private String start;
	private String end;
}
