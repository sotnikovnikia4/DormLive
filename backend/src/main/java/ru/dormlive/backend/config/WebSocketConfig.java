package ru.dormlive.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import ru.dormlive.backend.security.JwtChannelInterceptor;

import java.util.List;

/**
 * Конфигурация WebSocket для чата.
 * Этот класс настраивает WebSocket соединения, брокер сообщений и конвертеры сообщений.
 * Также добавляет JWT аутентификацию для WebSocket соединений.
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final JwtChannelInterceptor jwtChannelInterceptor;

    /**
     * Настраивает брокер сообщений для WebSocket.
     *
     * @param config Реестр конфигурации брокера сообщений
     */
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Включает встроенный брокер сообщений с префиксом "/chatRoom" для отправки сообщений клиентам
        config.enableSimpleBroker("/chatRoom");

        // Устанавливает префикс "/app" для сообщений, отправляемых клиентами на сервер
        config.setApplicationDestinationPrefixes("/app");

        // Устанавливает префикс "/chatRoom" для сообщений, адресованных конкретным пользователям
        config.setUserDestinationPrefix("/chatRoom");
    }

    /**
     * Регистрирует STOMP-эндпоинты для WebSocket соединений.
     *
     * @param registry Реестр STOMP-эндпоинтов
     */
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/chat")  // Добавляет эндпоинт "/chat" для WebSocket соединений
                .setAllowedOrigins("http://localhost:3000", "http://localhost:63342") // Разрешает соединения с указанных источников
                .withSockJS();  // Добавляет поддержку SockJS для браузеров, не поддерживающих WebSocket
    }

    /**
     * Настраивает конвертеры сообщений для преобразования между объектами Java и сообщениями WebSocket.
     *
     * @param messageConverters Список конвертеров сообщений
     * @return false, чтобы использовать стандартные конвертеры вместе с добавленными
     */
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        // Создает и настраивает резолвер типа контента для JSON
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);

        // Создает и настраивает конвертер Jackson для преобразования JSON в объекты Java и обратно
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);

        // Добавляет настроенный конвертер в список конвертеров
        messageConverters.add(converter);

        return false;  // Возвращает false, чтобы использовать стандартные конвертеры вместе с добавленными
    }

    /**
     * Настраивает входящий канал клиента, добавляя JWT перехватчик для аутентификации.
     *
     * @param registration Регистрация канала для настройки
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // Добавляет JWT перехватчик для проверки аутентификации входящих сообщений
        registration.interceptors(jwtChannelInterceptor);
    }
}
