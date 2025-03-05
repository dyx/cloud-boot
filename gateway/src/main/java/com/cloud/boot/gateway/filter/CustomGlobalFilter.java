package com.cloud.boot.gateway.filter;

import cn.dev33.satoken.util.SaTokenConsts;
import com.cloud.boot.common.core.constant.CommonConstant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lhd
 */
@Order(SaTokenConsts.ASSEMBLY_ORDER + 1)
@Component
public class CustomGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest().mutate().headers(httpHeaders -> {
            // 防止伪造内部请求，移除请求头中 Inner 参数
            httpHeaders.remove(CommonConstant.REQUEST_HEADER_INNER);
            // 设置请求开始时间
            httpHeaders.put(CommonConstant.REQUEST_HEADER_START_TIME, List.of(String.valueOf(System.currentTimeMillis())));
        }).build();

        // 重定向到子服务前，移除子服务请求路径的前缀
        String newPath = "/" + Arrays.stream(StringUtils.tokenizeToStringArray(request.getURI().getRawPath(), "/"))
                .skip(1L)
                .collect(Collectors.joining("/"));
        ServerHttpRequest newRequest = request.mutate().path(newPath).build();

        return chain.filter(exchange.mutate().request(newRequest).build());
    }
}