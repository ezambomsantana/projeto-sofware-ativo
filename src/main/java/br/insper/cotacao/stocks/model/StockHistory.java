package br.insper.cotacao.stocks.model;

import br.insper.cotacao.stocks.dto.StockHistoryDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class StockHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stock_val")
    private Float stockValue;
    private LocalDate dateValue;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;


    public static StockHistory fromDTO(StockHistoryDTO dto) {
        StockHistory history = new StockHistory();
        history.setId(dto.id());
        history.setStockValue(dto.value());
        history.setDateValue(dto.dateValue());
        return history;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getStockValue() {
        return stockValue;
    }

    public void setStockValue(Float stockValue) {
        this.stockValue = stockValue;
    }

    public LocalDate getDateValue() {
        return dateValue;
    }

    public void setDateValue(LocalDate dateValue) {
        this.dateValue = dateValue;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
