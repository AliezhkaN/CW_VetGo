package com.nahorniak.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Encoding filter.
 *
 * @author Oleh Nahorniak
 */

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    private static final Logger log = Logger.getLogger(EncodingFilter.class);
    private String encoding;


    /**
     * Destroy method.
     */

    public void destroy() {
        log.debug("Filter destruction starts");
        //-----------------
        log.debug("Filter destruction finished");
    }

    /**
     * doFilter method, set request encoding -> in my case 'UTF-8'
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        log.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        log.trace("Request uri --> " + httpRequest.getRequestURI());


        String requestEncoding = request.getCharacterEncoding();

        if (requestEncoding == null) {
            log.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        log.debug("Filter finished");
        chain.doFilter(request, response);
    }

    /**
     * Encoding filter initialization to get encoding from web.xml.
     */
    public void init(FilterConfig fConfig) throws ServletException {
        log.debug("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        log.trace("Encoding from web.xml --> " + encoding);
        log.debug("Filter initialization finished");
    }
}
