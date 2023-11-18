package co.edu.unbosque.syscourier.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "myapp")
public class JWTConfig {

    private String secretKey;

}
