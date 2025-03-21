package com.cloud.boot.common.mybatis.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.boot.common.mybatis.base.BasePageQuery;

/**
 * @author lhd
 */
public class PageUtils {

    public final static String SORT_ORDER_ASC = "ascending";
    public final static String SORT_ORDER_DESC = "descending";

    public static <T> Page<T> fillPage(BasePageQuery pageQuery) {

        Page<T> page = new Page<>(pageQuery.getCurrent(), pageQuery.getSize());

        String sortOrder = pageQuery.getSortOrder();
        if (isValidSortField(pageQuery.getSortField()) && isValidSortOrder(pageQuery.getSortOrder())) {
        OrderItem orderItem = new OrderItem();
            orderItem.setColumn(camelToUnderline(pageQuery.getSortField()));
            orderItem.setAsc(sortOrder.equals(SORT_ORDER_ASC));
            page.addOrder(orderItem);
        }

        return page;
    }

    public static boolean isOrderBy(BasePageQuery pageQuery, String targetField) {
        String sortField = pageQuery.getSortField();
        if (sortField == null || sortField.isEmpty()) {
            return false;
        }

        return sortField.equalsIgnoreCase(targetField);
    }

    public static boolean isAsc(BasePageQuery pageQuery) {
        String sortOrder = pageQuery.getSortOrder();
        return isValidSortOrder(sortOrder) && SORT_ORDER_ASC.equals(sortOrder);
    }

    private static boolean isValidSortOrder(String sortOrder) {
        return sortOrder != null && (sortOrder.equals(SORT_ORDER_ASC) || sortOrder.equals(SORT_ORDER_DESC));
    }

    private static boolean isValidSortField(String sortField) {
        return sortField != null && !sortField.isEmpty();
    }

    private static String camelToUnderline(String camelName) {
        if (camelName == null || camelName.isEmpty()) {
            return camelName;
        }
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < camelName.length(); i++) {
            char c = camelName.charAt(i);
            if (Character.isUpperCase(c)) {
                strBuilder.append('_').append(Character.toLowerCase(c));
            } else {
                strBuilder.append(c);
            }
        }
        return strBuilder.toString();
    }
}
