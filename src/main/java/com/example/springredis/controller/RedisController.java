package com.example.springredis.controller;

import com.example.springredis.service.BookingService;
import com.example.springredis.dto.Booking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RedisController {
    @Autowired
    private BookingService bookingService;


    @Operation(summary = "Get all the orders")
    @GetMapping("/allOrders")
    public List<Booking> getOrderDao() {
        return bookingService.getAllOrders();
    }

    @PostMapping("/saveOrder")
    public Booking saveOrder(@RequestBody Booking booking){
       return bookingService.saveOrder(booking);
    }

    @Operation(summary = "Get order by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200",description = "Found the order"),
            @ApiResponse(responseCode="404",description = "Order not found")
    })
    @GetMapping("/orderById/{id}")
    public Booking getOrderById(@PathVariable Integer id){
        return bookingService.getOrderById(id);
    }

    @DeleteMapping("/delOrder")
    public ResponseEntity<String> delOrder(@RequestParam Integer id){
        bookingService.delOrder(id);
       return ResponseEntity.ok("Order has been deleted successfully");
    }
}
