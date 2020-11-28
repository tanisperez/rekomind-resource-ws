package com.rekomind.resource.http;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;

import org.apache.commons.lang3.StringUtils;
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

	/** Unknown host */
	private static final String UNKNOWN_HOST = "unknown";

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
		request.append(StringUtils.SPACE);
		request.append(getHostAddressField(httpRequest));
		request.append(StringUtils.SPACE);
		request.append(uri.toString());
		return request.toString();
	}

	private static String getHostAddressField(final ServerHttpRequest httpRequest) {
		final StringBuilder host = new StringBuilder();
		host.append("(from ");
		host.append(getHostAddress(httpRequest));
		host.append(")");
		return host.toString();
	}

	private static String getHostAddress(final ServerHttpRequest httpRequest) {
		final InetSocketAddress remoteAddress = httpRequest.getRemoteAddress();
		if (remoteAddress != null) {
			final InetAddress address = remoteAddress.getAddress();
			if (address != null) {
				return address.getHostAddress();
			}
		}
		return UNKNOWN_HOST;
	}

}