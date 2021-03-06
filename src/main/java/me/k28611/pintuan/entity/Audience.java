package me.k28611.pintuan.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "audience")
@Data
public class Audience {
    private JwtProperty jwtProperty = new JwtProperty();
    private SftpProperty  sftpProperty = new SftpProperty();
    @Data
    public static class  JwtProperty{
        private String clientId;

        private String base64Secret;

        private String name;

        private Long expireSecond;
    }
    @Data
    public static class SftpProperty{

        private String host;

        private Integer port;

        private String protocol;

        private String username;

        private String password;

        private String root;

        private String privateKey;

        private String passphrase;

        private String sessionStrictHostKeyChecking;

        private Integer sessionConnectTimeout;

        private Integer channelConnectedTimeout;

    }


}
