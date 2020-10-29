package com.github.tanisperez.recomendador.resource.http;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class HTTPFilter implements WebFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(HTTPFilter.class);

	@Override
	public Mono<Void> filter(final ServerWebExchange exchange, final WebFilterChain chain) {
		final String request = getRequest(exchange);
		LOGGER.info(request);

		return chain.filter(exchange);
	}

	private static String getRequest(final ServerWebExchange exchange) {
		final ServerHttpRequest httpRequest = exchange.getRequest();
		final URI uri = httpRequest.getURI();

		final StringBuilder request = new StringBuilder();
		request.append(httpRequest.getMethodValue());
		request.append(" ");
		request.append(uri.toString());
		return request.toString();
	}

}