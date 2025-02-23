package com.cloud.boot.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.product.mapper.InventoryMapper;
import com.cloud.boot.product.model.dto.DeductStockDTO;
import com.cloud.boot.product.model.entity.InventoryDO;
import com.cloud.boot.product.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lhd
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, InventoryDO> implements InventoryService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deductStockBatch(List<DeductStockDTO> dtoList) {

        if (CollectionUtil.isEmpty(dtoList)) {
            throw new BizException("参数不能为空");
        }

        List<Long> productIdList = dtoList.stream().map(DeductStockDTO::getProductId).collect(Collectors.toList());
        Map<Long, InventoryDO> inventoryMap = list(Wrappers.<InventoryDO>lambdaQuery().in(InventoryDO::getProductId, productIdList))
                .stream().collect(Collectors.toMap(InventoryDO::getProductId, inventory -> inventory));


        List<InventoryDO> updateList = new ArrayList<>();
        for (DeductStockDTO deductStockDTO : dtoList) {
            InventoryDO inventoryDO = inventoryMap.get(deductStockDTO.getProductId());
            if (inventoryDO == null) {
                throw new BizException("库存信息不存在");
            }

            int finalStock = inventoryDO.getStock() - deductStockDTO.getStock();
            if (finalStock < 0) {
                throw new BizException("库存不足，商品ID: " + deductStockDTO.getProductId());
            }

            InventoryDO entity = new InventoryDO();
            entity.setId(inventoryDO.getId());
            entity.setStock(finalStock);
            updateList.add(entity);
        }


        boolean updateFlag = updateBatchById(updateList);
        if (!updateFlag) {
            throw new BizException("库存扣减失败");
        }
    }
}
