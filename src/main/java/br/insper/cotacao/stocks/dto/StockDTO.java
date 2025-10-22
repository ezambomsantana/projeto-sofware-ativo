package br.insper.cotacao.stocks.dto;

import br.insper.cotacao.stocks.model.Stock;

import java.time.LocalDate;
import java.util.List;

public record StockDTO(
        Integer id,
        String ticker,
        String name,
        String description,
        String createdBy,
        Float lastValue,
        LocalDate dateLastValue,
        LocalDate dateRegister
) {

    public static StockDTO fromModel(Stock stock) {
        return new StockDTO(
                stock.getId(),
                stock.getTicker(),
                stock.getName(),
                stock.getDescription(),
                stock.getCreatedBy(),
                stock.getLastValue(),
                stock.getDateLastValue(),
                stock.getDateRegister()
        );
    }
}