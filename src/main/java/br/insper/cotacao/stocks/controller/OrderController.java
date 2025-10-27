package br.insper.cotacao.stocks.controller;

import br.insper.cotacao.stocks.model.Order;
import br.insper.cotacao.stocks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return orderService.getOrder(id);
    }

}
