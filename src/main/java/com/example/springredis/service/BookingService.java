package com.example.springredis.service;

import com.example.springredis.controller.RedisController;
import com.example.springredis.dto.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private static final String HASH_KEY = "order";
    Logger logger = LoggerFactory.getLogger(RedisController.class);
    @Autowired
    private RedisTemplate template;

    public Booking saveOrder(Booking booking){
        template.opsForHash().put(HASH_KEY, booking.getId(), booking);
        return booking;
    }

    @Cacheable(cacheNames = "record")
    public List<Booking> getAllOrders(){
        logger.info("Fetching all order entries from redis db");
        return  template.opsForHash().values(HASH_KEY);
    }
    @Cacheable(cacheNames = "records",key = "#id")
    public Booking getOrderById(Integer id){
        logger.info("Fetching an order entry from redis db");
        return (Booking) template.opsForHash().get(HASH_KEY,id);
    }

    @CacheEvict(cacheNames = "records",key = "#id")
    public String delOrder(Integer id){
        logger.info("Deleting an order entry from redis db");
        template.opsForHash().delete(HASH_KEY,id);
        return "Order has been deleted successfully";
    }
}
