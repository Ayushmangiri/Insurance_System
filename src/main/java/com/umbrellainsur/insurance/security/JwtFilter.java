package com.umbrellainsur.insurance.security;

import com.umbrellainsur.insurance.repository.BrokerRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtUtil jwtUtil;
    private final BrokerRepository brokerRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.startsWith("/api/auth/");
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {

            logger.debug("Processing request: {}", request.getServletPath());

            final String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.debug("No bearer token found, continuing filter chain");
                filterChain.doFilter(request, response);
                return;
            }

            final String jwt = authHeader.substring(7);
            logger.debug("JWT token extracted from header");

            if (!jwtUtil.validateToken(jwt)) {
                logger.warn("Invalid JWT token");
                filterChain.doFilter(request, response);
                return;
            }

            final String email = jwtUtil.getEmailFromToken(jwt);
            logger.debug("Email extracted from token: {}", email);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var brokerOpt = brokerRepository.findByEmail(email);

                if (brokerOpt.isPresent()) {
                    logger.debug("Broker found, setting authentication");
                    var broker = brokerOpt.get();
                    var authentication = new UsernamePasswordAuthenticationToken(
                            broker, null, Collections.emptyList()
                    );
                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    logger.warn("No broker found for email: {}", email);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}