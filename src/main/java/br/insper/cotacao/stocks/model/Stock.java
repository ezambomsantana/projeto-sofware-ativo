package br.insper.cotacao.stocks.model;

import br.insper.cotacao.stocks.dto.StockDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ticker;
    private String name;
    private String description;
    private Float lastValue;
    private LocalDate dateLastValue;
    private LocalDate dateRegister;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockHistory> stockHistory;

    public static Stock fromDTO(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.id());
        stock.setTicker(dto.ticker());
        stock.setName(dto.name());
        stock.setDescription(dto.description());
        stock.setLastValue(dto.lastValue());
        stock.setDateLastValue(dto.dateLastValue());
        stock.setDateRegister(dto.dateRegister());

        if (dto.stockHistory() != null) {
            stock.setStockHistory(
                    dto.stockHistory().stream()
                            .map(StockHistory::fromDTO)
                            .peek(history -> history.setStock(stock))
                            .toList()
            );
        }

        return stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLastValue() {
        return lastValue;
    }

    public void setLastValue(Float lastValue) {
        this.lastValue = lastValue;
    }

    public LocalDate getDateLastValue() {
        return dateLastValue;
    }

    public void setDateLastValue(LocalDate dateLastValue) {
        this.dateLastValue = dateLastValue;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    public List<StockHistory> getStockHistory() {
        return stockHistory;
    }

    public void setStockHistory(List<StockHistory> stockHistory) {
        this.stockHistory = stockHistory;
    }
}
