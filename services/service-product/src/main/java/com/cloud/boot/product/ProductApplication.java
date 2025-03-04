package com.cloud.boot.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lhd
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args) {

        SpringApplication.run(ProductApplication.class, args);
    }
}
