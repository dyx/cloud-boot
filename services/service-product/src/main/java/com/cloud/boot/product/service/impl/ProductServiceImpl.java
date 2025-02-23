package com.cloud.boot.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.product.mapper.ProductMapper;
import com.cloud.boot.product.model.entity.ProductDO;
import com.cloud.boot.product.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author lhd
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements ProductService {
}
