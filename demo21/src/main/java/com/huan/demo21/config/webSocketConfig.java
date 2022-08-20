package com.huan.demo21.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocketMessageBrokerConfigurer 实现此接口对WebSocket进行配置
 * @EnableWebSocketMessageBroker 开启WebSocket消息代理
 */
@Configuration
@EnableWebSocketMessageBroker
public class webSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * addEndpoint 定义一个endpoint并开启sockjs支持
         * sockjs 可以解决浏览器对WebSocket的兼容性问题，客户将通过这里配置url
         *        来建立WebSocket连接
         */
        registry.addEndpoint("/chat").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /**
         * enableSimpleBroker 设置消息代理的前缀
         * "/topic" 当前缀为此时，表示会将消息转发给消息代理broker,再由消息代理
         *          将消息广播给当前链接的客户端
         * setApplicationDestinationPrefixes 配置一个或多个前缀， 通过前缀
         *          过滤出需要被注解方法处理的消息，例如/app可通过@MessageMapping
         *          处理，其他会直接交给broker
         */
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
