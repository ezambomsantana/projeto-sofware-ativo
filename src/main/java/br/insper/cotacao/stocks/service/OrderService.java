package br.insper.cotacao.stocks.service;

import br.insper.cotacao.stocks.dto.StockDTO;
import br.insper.cotacao.stocks.model.Order;
import br.insper.cotacao.stocks.model.Stock;
import br.insper.cotacao.stocks.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final String QUEUE_KEY = "orders:queue";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockService stockService;

    @Qualifier("integerRedisTemplate")
    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    public Order createOrder(Order order) {
        StockDTO stock = stockService.getByTicker(order.getTicker());
        order.setStatus(Order.OrderStatus.CREATED);
        order.setValue(stock.lastValue() * order.getNumber());

        order = orderRepository.save(order);
        redisTemplate.opsForList().rightPush(QUEUE_KEY, order.getId());
        return order;
    }

    public Order getOrder(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
