package br.insper.cotacao.stocks.service;

import br.insper.cotacao.stocks.dto.StockDTO;
import br.insper.cotacao.stocks.exception.StockNotFoundException;
import br.insper.cotacao.stocks.model.Stock;
import br.insper.cotacao.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public StockDTO create(StockDTO dto) {
        Stock stock = Stock.fromDTO(dto);
        Stock saved = stockRepository.save(stock);
        return StockDTO.fromModel(saved);
    }

    public List<StockDTO> listAll() {
        return stockRepository.findAll()
                .stream()
                .map(StockDTO::fromModel)
                .toList();
    }

    public List<StockDTO> listAll2() {
        return stockRepository.findAll()
                .stream()
                .map(StockDTO::fromModel)
                .toList();
    }

    public void delete(Integer id) {
        if (!stockRepository.existsById(id)) {
            throw new StockNotFoundException("Stock not found");
        }
        stockRepository.deleteById(id);
    }
}
