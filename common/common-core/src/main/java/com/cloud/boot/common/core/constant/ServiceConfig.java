package com.cloud.boot.common.core.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhd
 * 微服务配置信息类
 */
public class ServiceConfig {

    public static class Auth {
        public static final String ID = "auth";
        public static final String NAME = "授权服务";
        public static final String CONTEXT_PATH = "/auth";
    }

    public static class User {
        public static final String ID = "service-user";
        public static final String NAME = "用户服务";
        public static final String CONTEXT_PATH = "/user";
    }

    public static class Product {
        public static final String ID = "service-product";
        public static final String NAME = "产品服务";
        public static final String CONTEXT_PATH = "/product";
    }

    public static class Order {
        public static final String ID = "service-order";
        public static final String NAME = "订单服务";
        public static final String CONTEXT_PATH = "/order";
    }

    public static final List<ServiceInfo> SERVICES = new ArrayList<>();
    static {
        SERVICES.add(new ServiceInfo(Auth.ID, Auth.NAME, Auth.CONTEXT_PATH));
        SERVICES.add(new ServiceInfo(User.ID, User.NAME, User.CONTEXT_PATH));
        SERVICES.add(new ServiceInfo(Product.ID, Product.NAME, Product.CONTEXT_PATH));
        SERVICES.add(new ServiceInfo(Order.ID, Order.NAME, Order.CONTEXT_PATH));
    }

    public record ServiceInfo(String id, String name, String contextPath) {
    }
}