package br.insper.cotacao.stocks.controller;

import br.insper.cotacao.stocks.dto.StockDTO;
import br.insper.cotacao.stocks.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        stockService.delete(id);
    }

    // adcione as rotas para buscar uma ação GET /stocks/{id}
    // e para editar o lastValue de uma ação PUT /stocks/{id}
    // façaa o teste de 100% do codigo
}
