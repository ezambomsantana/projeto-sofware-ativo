package br.insper.cotacao.stocks.controller;

import br.insper.cotacao.stocks.dto.StockDTO;
import br.insper.cotacao.stocks.service.Movimentacao;
import br.insper.cotacao.stocks.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public StockDTO create(@RequestBody StockDTO dto) {
        return stockService.create(dto);
    }

    @GetMapping
    public List<StockDTO> listAll() {
        return stockService.listAll();
    }

    @GetMapping("/{ticker}")
    public StockDTO getByTicker(@PathVariable String ticker) {
        return stockService.getByTicker(ticker);
    }

    @GetMapping("/{ticker}/movimentacao")
    public List<Movimentacao> getMovimentacoesByTicker(@PathVariable String ticker) {
        return stockService.listMovimentacao(ticker);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        stockService.delete(id);
    }

}
