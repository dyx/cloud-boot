package com.cloud.boot.product.service;

import com.cloud.boot.product.model.dto.DeductStockDTO;

import java.util.List;

/**
 * @author lhd
 */
public interface InventoryService {

    void deductStockBatch(List<DeductStockDTO> dtoList);
}
