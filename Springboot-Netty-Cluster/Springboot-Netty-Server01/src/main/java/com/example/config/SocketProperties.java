package com.example.config;

import com.example.protocol.Serializer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/27
 */
@Data
@Component
@ConfigurationProperties(prefix = "socket.server")
public class SocketProperties {
    private String host;
    private Integer port;
    private String serializer;

    public Serializer.Algorithm getSerializerAlgorithm(){
        if (StringUtils.isEmpty(serializer)){
            return Serializer.Algorithm.Java;
        }else {
            return Serializer.Algorithm.valueOf(serializer);
        }
    }
}
