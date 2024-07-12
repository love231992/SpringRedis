package com.example.springredis.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Order")
public class Booking implements Serializable {
    @Id
    private Integer id;
    @Size(min =0,max=5)
    private int qty;
    private String details;
    private long price;
}
