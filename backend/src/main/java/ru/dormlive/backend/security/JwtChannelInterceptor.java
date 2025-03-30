package ru.dormlive.backend.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtChannelInterceptor implements ChannelInterceptor {
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null) {
            if (StompCommand.CONNECT.equals(accessor.getCommand()) || StompCommand.SEND.equals(accessor.getCommand())) {
                String authorizationHeader = accessor.getFirstNativeHeader("Authorization");
                if (headerIsValid(authorizationHeader)) {
                    String token = authorizationHeader.substring(7);
                    checkJWTAndSetAuthentication(token, accessor);
                }else{
                    throw new JWTVerificationException("Invalid JWT token");
                }
            }
        }else{
            throw new RuntimeException();
        }
        return message;
    }

    private void checkJWTAndSetAuthentication(String token, StompHeaderAccessor accessor) throws JWTVerificationException {
        setSecurityContextIfItEmpty(validateTokenAndRetrieveClaim(token), accessor);
    }
    private boolean headerIsValid(String authHeader) {
        return authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ");
    }
    private String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        if (token.isBlank()) {
            throw new JWTVerificationException("Token is empty");
        }
        return jwtUtil.validateTokenAndRetrieveClaim(token);
    }
    private void setSecurityContextIfItEmpty(String username, StompHeaderAccessor accessor) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            accessor.setUser(authToken);
        }
    }
}
