package com.example.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/4
 */
@Component
@RefreshScope
public class AuthFilter implements GlobalFilter, Ordered {
    @Value("#{'${ignoreUrls}'.split(',')}")
    private List<String> ignoreUrls;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        if (matches(url, ignoreUrls)){
            return chain.filter(exchange);
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private static final String NULLSTR = "";
    public boolean matches(String str, List<String> strs)
    {
        if (isEmpty(str) || isEmpty(strs))
        {
            return false;
        }
        for (String pattern : strs)
        {
            if (isMatch(pattern, str))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    public boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    public boolean isNull(Object object)
    {
        return object == null;
    }

    public boolean isMatch(String pattern, String url)
    {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }
}
