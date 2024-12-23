package com.mindbowser.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.mindbowser.constant.AppConstant;
import com.mindbowser.jwt.JwtVerifier;
import com.nimbusds.jwt.JWTClaimsSet;

import reactor.core.publisher.Mono;

/**
 * @author mindbowser | secure-gate team
 */
@Component
@ConditionalOnProperty(name = AppConstant.JWT_CONFIG_ENABLED, havingValue = AppConstant.TRUE)
public class AuthenticationFilter implements GlobalFilter, Ordered {

	private final JwtVerifier jwtVerifier;

	/**
	 * @author mindbowser | secure-gate team
	 */
	public AuthenticationFilter(JwtVerifier jwtVerifier) {
		this.jwtVerifier = jwtVerifier;
	}

	/**
	 * @author mindbowser | secure-gate team
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();

		String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

		if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);

			// verify the jwt token from jwk verifier
			JWTClaimsSet claimsSet = jwtVerifier.verifyAndGetClaims(token);

			// Mutate the request to add the custom header
			ServerHttpRequest mutatedRequest = request.mutate().header("X-User-Id", claimsSet.getSubject()).build();

			// Use the mutated request in the exchange
			return chain.filter(exchange.mutate().request(mutatedRequest).build());
		}

		return chain.filter(exchange);
	}

	/**
	 * @author mindbowser | secure-gate team
	 */
	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}
}
