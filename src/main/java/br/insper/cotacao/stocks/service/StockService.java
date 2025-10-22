package br.insper.cotacao.stocks.service;

import br.insper.cotacao.stocks.dto.EditStockDTO;
import br.insper.cotacao.stocks.dto.StockDTO;
import br.insper.cotacao.stocks.exception.StockNotFoundException;
import br.insper.cotacao.stocks.model.Stock;
import br.insper.cotacao.stocks.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockCacheService stockCacheService;

    @Autowired
    private MovimentacaoService movimentacaoService;

    public StockDTO create(StockDTO dto, String email) {
        Stock stock = Stock.fromDTO(dto);
        stock.setDateLastValue(LocalDate.now());
        stock.setDateRegister(LocalDate.now());
        stock.setCreatedBy(email);
        Stock saved = stockRepository.save(stock);
        return StockDTO.fromModel(saved);
    }

    public List<StockDTO> listAll() {
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

    public StockDTO getByTicker(String ticker) {

        Stock stock = stockRepository.findByTicker(ticker)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return StockDTO.fromModel(stock);
    }
/**
    public StockDTO getByTicker(String ticker) {

        StockDTO stockDTO = stockCacheService.getByTicker(ticker);
        if (stockDTO == null) {
            Stock stock = stockRepository.findByTicker(ticker)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            stockDTO = StockDTO.fromModel(stock);
            stockCacheService.save(stockDTO);
        }
        return stockDTO;
    }*/

    public List<Movimentacao> listMovimentacao(String token, String ticker) {
        List<Movimentacao> movimentacaos = movimentacaoService.getMovimetacoes(token);
        return movimentacaos
                        .stream()
                        .filter(m -> m.getTicker().equals(ticker))
                        .toList();
    }

    public StockDTO changeValue(String ticker, EditStockDTO editStockDTO) {

        Stock stock = stockRepository.findByTicker(ticker)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        stock.setLastValue(editStockDTO.lastValue());
        stock.setDateLastValue(LocalDate.now());
        stock = stockRepository.save(stock);
        return StockDTO.fromModel(stock);
    }
}
