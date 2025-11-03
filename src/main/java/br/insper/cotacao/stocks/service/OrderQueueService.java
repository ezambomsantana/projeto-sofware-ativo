package br.insper.cotacao.stocks.service;

import br.insper.cotacao.stocks.model.Order;
import br.insper.cotacao.stocks.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class OrderQueueService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private OrderRepository orderRepository;

    private static final String QUEUE_KEY = "orders:queue";

    private boolean running = true;

    @PostConstruct
    public void startWorker() {
        new Thread(this::processQueue).start();
    }

    private void processQueue() {
        while (running) {

            String orderId = redisTemplate.opsForList().leftPop(QUEUE_KEY, Duration.ofSeconds(5));
            if (orderId != null) {

                System.out.println("Processando order" + orderId);
                Order order = orderRepository.findById(Integer.parseInt(orderId)).get();
                try {
                    order.setStatus(Order.OrderStatus.COMPLETED);
                    orderRepository.save(order);
                } catch (Exception e) {
                    order.setStatus(Order.OrderStatus.CANCELED);
                    orderRepository.save(order);
                }
            } else {
                System.out.println("Fila vazia, aguardando...");
            }
        }
    }

    @PreDestroy
    public void stop() {
        running = false;
    }

}

