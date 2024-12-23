package com.mindbowser.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @author mindbowser | secure-gate team
 */
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

	private final Logger log = LogManager.getLogger();

	/**
	 * @author mindbowser | secure-gate team
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("Request method :: {} and url :: {}", exchange.getRequest().getMethod(),
				exchange.getRequest().getURI().getPath());

		return chain.filter(exchange);
	}

	/**
	 * @author mindbowser | secure-gate team
	 */
	@Override
	public int getOrder() {
		return -1;
	}
}
