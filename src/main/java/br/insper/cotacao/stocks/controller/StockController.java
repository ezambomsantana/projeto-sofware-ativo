package br.insper.cotacao.stocks.controller;

import br.insper.cotacao.stocks.dto.EditStockDTO;
import br.insper.cotacao.stocks.dto.StockDTO;
import br.insper.cotacao.stocks.service.Movimentacao;
import br.insper.cotacao.stocks.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public StockDTO create(@AuthenticationPrincipal Jwt jwt, @RequestBody StockDTO dto) {
       // String email = jwt.getClaimAsString("https://stocks-insper.com/email");
     //   List<String> roles = jwt.getClaimAsStringList("https://stocks-insper.com/roles");

      //  if (!roles.contains("ADMIN")) {
      //      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
//}
        String email = null;
        return stockService.create(dto, email);
    }

    @PutMapping("/{ticker}")
    public StockDTO changeValue(@PathVariable String ticker, @RequestBody EditStockDTO editStockDTO) {
        return stockService.changeValue(ticker, editStockDTO);
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
    public List<Movimentacao> getMovimentacoesByTicker(@RequestHeader(name = "Authorization") String token,
                                                       @PathVariable String ticker) {
        return stockService.listMovimentacao(token, ticker);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        stockService.delete(id);
    }

}
