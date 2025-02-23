package com.cloud.boot.product.controller;

import com.cloud.boot.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhd
 */
@RequestMapping("product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
}
