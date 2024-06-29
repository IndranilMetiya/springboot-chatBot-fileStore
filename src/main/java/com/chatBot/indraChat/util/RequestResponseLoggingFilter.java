package com.chatBot.indraChat.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Order(1) // Specifying the order of the filter I will implement multiple filters
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Checking if the request has already been processed
        if (request.getAttribute("logRequestAndResponse") == null) {
            // Marking the request as processed
            request.setAttribute("logRequestAndResponse", true);

            // Wrapping the request and response to cache their content
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

            try {
                // Proceeding with the request
                filterChain.doFilter(requestWrapper, responseWrapper);
            } finally {
                // Logging request and response after processing
                logRequest(requestWrapper);
                logResponse(responseWrapper);
                responseWrapper.copyBodyToResponse(); // Ensure the response is flushed
            }
        } else {
            // Proceeding without wrapping
            filterChain.doFilter(request, response);
        }
    }

    private void logRequest(ContentCachingRequestWrapper requestWrapper) throws IOException {
        String requestBody = new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        FileLogger.logRequest(requestBody);
    }

    private void logResponse(ContentCachingResponseWrapper responseWrapper) throws IOException {
        String responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        FileLogger.logResponse(responseBody);
    }
}
