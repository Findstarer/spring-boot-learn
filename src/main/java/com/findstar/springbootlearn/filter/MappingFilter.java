package com.findstar.springbootlearn.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : findStar
 * @date : 2021/10/23 9:49 下午
 */
@Slf4j
@Component
public class MappingFilter extends OncePerRequestFilter {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    private final ObjectMapper mapper = new ObjectMapper();


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HandlerMethod method = handlerMapping.getHandlerMethods()
                .get(RequestMappingInfo
                        .paths(request.getServletPath())
                        .methods(RequestMethod.valueOf(request.getMethod()))
                        .build());
        String simpleName = method != null ? method.getMethod().getName() : "";
        log.info("uri : {}, ContextPath: {}, ServletPath: {}, method: {}",
                request.getRequestURI(), request.getContextPath(), request.getServletPath(), simpleName);
        filterChain.doFilter(request, response);
    }
}
