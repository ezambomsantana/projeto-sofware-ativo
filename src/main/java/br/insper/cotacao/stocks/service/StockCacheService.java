package br.insper.cotacao.stocks.service;

import br.insper.cotacao.stocks.dto.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class StockCacheService {

    @Qualifier("stockRedisTemplate")
    @Autowired
    private RedisTemplate<String, StockDTO> redisTemplate;
    private static final Duration TTL = Duration.ofMinutes(10);

    private String keyByTicker(String ticker) {
        return "stock:ticker:" + ticker;
    }

    public StockDTO getByTicker(String ticker) {
        return redisTemplate
                .opsForValue()
                .get(keyByTicker(ticker));
    }

    public void save(StockDTO dto) {
        if (dto == null)
            return;
        if (dto.ticker() != null) {
            redisTemplate
                    .opsForValue()
                    .set(keyByTicker(dto.ticker()), dto, TTL);
        }
    }

    public void delete(String ticker) {
        if (ticker != null) redisTemplate.delete(keyByTicker(ticker));
    }
}
