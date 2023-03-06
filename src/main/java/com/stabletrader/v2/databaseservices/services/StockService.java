package com.stabletrader.v2.databaseservices.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.stabletrader.v2.databaseservices.entities.DailyHistoricalStock;
import com.stabletrader.v2.databaseservices.entities.RealTimeStock;
import com.stabletrader.v2.databaseservices.entities.Stock;
import com.stabletrader.v2.databaseservices.entities.WeeklyHistoricalStock;
import com.stabletrader.v2.databaseservices.entities.YearlyHistoricalStock;
import com.stabletrader.v2.databaseservices.repositories.DailyHistoricalStockRepository;
import com.stabletrader.v2.databaseservices.repositories.RealTimeStockRepository;
import com.stabletrader.v2.databaseservices.repositories.StockRepository;
import com.stabletrader.v2.databaseservices.repositories.WeeklyHistoricalStockRepository;
import com.stabletrader.v2.databaseservices.repositories.YearlyHistoricalStockRepository;

import jakarta.annotation.PostConstruct;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private RealTimeStockRepository realTimeStockRepository;
	@Autowired
	private DailyHistoricalStockRepository dailyHistoricalStockRepository;
	@Autowired
	private WeeklyHistoricalStockRepository weeklyHistoricalStockRepository;
	@Autowired
	private YearlyHistoricalStockRepository yearlyHistoricalStockRepository;

	private Map<String, Integer> stockBySymbolMap = new HashMap<>();
	private Map<Integer, String> stockByIDMap = new HashMap<>();

	@PostConstruct
	public void init() {
		getStocks().stream().forEach(s -> {
			stockBySymbolMap.put(s.getStockSymbol(), s.getId());
			stockByIDMap.put(s.getId(), s.getStockSymbol());
		});
	}

	public Stock saveStock(Stock stock) {
		return stockRepository.save(stock);
	}

	public List<Stock> saveStocks(List<Stock> stocks) {
		return stockRepository.saveAll(stocks);
	}

	public List<Stock> getStocks() {
		return stockRepository.findAll();
	}

	////
	public RealTimeStock saveRealTimeStock(RealTimeStock realTimeStock) {
		return realTimeStockRepository.save(realTimeStock);
	}

	public List<RealTimeStock> saveRealTimeStocks(List<RealTimeStock> stocks) {
		return realTimeStockRepository.saveAll(stocks);
	}

	public List<RealTimeStock> getRealTimeStocks() {
		return realTimeStockRepository.findAll();
	}

	//
	public DailyHistoricalStock saveDailyHistoricalStock(DailyHistoricalStock historicalStock) {
		return dailyHistoricalStockRepository.save(historicalStock);
	}

	public List<DailyHistoricalStock> getDailyHistoricalStockBySymbol(String symbol) {
		return dailyHistoricalStockRepository.findByStockId(stockBySymbolMap.get(symbol));
	}

	public List<DailyHistoricalStock> findHisByDuration(List<String> symbols, String start, String end) {
		List<Integer> ids = symbols.stream().map(s -> stockBySymbolMap.get(s)).collect(Collectors.toList());

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT s FROM DailyHistoricalStock s WHERE ").append("s.stockId IN (?1)")
				.append(" AND s.date BETWEEN ").append("?2 AND ?3 ORDER BY s.date, s.stockId");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		LocalDate startDate = LocalDate.parse(start, formatter);
		LocalDate endDate = LocalDate.parse(end, formatter);

		return dailyHistoricalStockRepository.findAllByQuery(sb.toString(), ids, startDate, endDate);
	}

	public List<DailyHistoricalStock> findHisByDuration(String symbol, LocalDate start, LocalDate end) {
		return dailyHistoricalStockRepository.findHisByDuration(stockBySymbolMap.get(symbol), start, end);
	}

	public WeeklyHistoricalStock saveWeeklyHistoricalStock(WeeklyHistoricalStock historicalStock) {
		return weeklyHistoricalStockRepository.save((WeeklyHistoricalStock) historicalStock);
	}

	public YearlyHistoricalStock saveYearlyHistoricalStock(YearlyHistoricalStock historicalStock) {
		return yearlyHistoricalStockRepository.save((YearlyHistoricalStock) historicalStock);
	}

	public List<DailyHistoricalStock> saveDailyHistoricalStocks(List<DailyHistoricalStock> historicalStocks) {
		try {
			return dailyHistoricalStockRepository.saveAll(historicalStocks);
		} catch (DataIntegrityViolationException e) {
			return new ArrayList<DailyHistoricalStock>();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<WeeklyHistoricalStock> saveWeeklyHistoricalStocks(List<WeeklyHistoricalStock> historicalStocks) {
		return weeklyHistoricalStockRepository.saveAll(historicalStocks);
	}

	public List<YearlyHistoricalStock> saveYearlyHistoricalStocks(List<YearlyHistoricalStock> historicalStocks) {
		return yearlyHistoricalStockRepository.saveAll(historicalStocks);
	}

}
