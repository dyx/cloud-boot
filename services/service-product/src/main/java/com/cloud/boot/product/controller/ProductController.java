package com.cloud.boot.product.controller;

import com.cloud.boot.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhd
 */
@RequestMapping("product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
}
