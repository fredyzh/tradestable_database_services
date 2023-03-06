package com.stabletrader.v2.databaseservices.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stabletrader.v2.databaseservices.entities.DailyHistoricalStock;
import com.stabletrader.v2.databaseservices.entities.RealTimeStock;
import com.stabletrader.v2.databaseservices.entities.Stock;
import com.stabletrader.v2.databaseservices.entities.WeeklyHistoricalStock;
import com.stabletrader.v2.databaseservices.entities.YearlyHistoricalStock;
import com.stabletrader.v2.databaseservices.services.StockService;

@RestController
public class StockProcessController {
	private Logger logger = LogManager.getLogger();

	@Autowired
	private StockService stockService;

	@PostMapping("/addStock")
	public Stock addStock(@RequestBody Stock stock) {
		return stockService.saveStock(stock);
	}

	@PostMapping("/addStocks")
	public List<Stock> addStocks(@RequestBody List<Stock> stocks) {
		return stockService.saveStocks(stocks);
	}

	@GetMapping("/getStocks")
	public List<Stock> getStocks() {
		return stockService.getStocks();
	}

	@PostMapping("/saveRealTimeStocks")
	public List<RealTimeStock> saveRealTimeStocks(@RequestBody List<RealTimeStock> realTimeStocks) {
		return stockService.saveRealTimeStocks(realTimeStocks);
	}
	
	@PostMapping("/daily/saveHistoricalStocks")
	public List<DailyHistoricalStock> saveHistoricalStocks(@RequestBody List<DailyHistoricalStock> historicalStocks) {
		return stockService.saveDailyHistoricalStocks(historicalStocks);
	}
	
	@PostMapping("/daily/saveHistoricalStock")
	public DailyHistoricalStock saveDailyHistoricalStock(@RequestBody DailyHistoricalStock historicalStock) {
		return stockService.saveDailyHistoricalStock(historicalStock);
	}
	
	@GetMapping("/daily/getDailyStockBySymbol")
	public DailyHistoricalStock getDailyHistoricalStock(@PathVariable String symbol) {
		return null;
	}
	
	@PostMapping("/daily/getDailyStockByDurations")
	public List<DailyHistoricalStock> getDailyStockByDurations(@RequestBody DurationWithSymbolsRequest duration) {
		return stockService.findHisByDuration(duration.getSymbols(), duration.getStart(), duration.getEnd());
	}
	
	@PostMapping("/weekly/saveHistoricalStocks")
	public List<WeeklyHistoricalStock> saveWeeklyHistoricalStock(@RequestBody List<WeeklyHistoricalStock> historicalStocks) {
		return stockService.saveWeeklyHistoricalStocks(historicalStocks);
	}
	
	@PostMapping("/weekly/saveHistoricalStock")
	public WeeklyHistoricalStock saveWeeklyHistoricalStock(@RequestBody WeeklyHistoricalStock historicalStock) {
		return stockService.saveWeeklyHistoricalStock(historicalStock);
	}
	
	@PostMapping("/yearly/saveHistoricalStocks")
	public List<YearlyHistoricalStock> saveYealyHistoricalStocks(@RequestBody List<YearlyHistoricalStock> historicalStocks) {
		return stockService.saveYearlyHistoricalStocks(historicalStocks);
	}
	
	@PostMapping("/yearly/saveHistoricalStock")
	public YearlyHistoricalStock saveHistoricalStock(@RequestBody YearlyHistoricalStock historicalStock) {
		return stockService.saveYearlyHistoricalStock(historicalStock);
	}
}
