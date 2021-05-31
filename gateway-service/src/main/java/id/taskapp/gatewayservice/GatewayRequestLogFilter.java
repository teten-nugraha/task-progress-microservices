package id.taskapp.gatewayservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GatewayRequestLogFilter implements GlobalFilter, Ordered {

    private static Logger logger = LoggerFactory.getLogger(GatewayRequestLogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        logger.info("path: " + request.getPath());
        logger.info("address: " + request.getRemoteAddress());
        logger.info("method: " + request.getMethodValue());
        logger.info("URI: " + request.getURI());
        logger.info("Headers: " + request.getHeaders());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}