package co.edu.unbosque.syscourier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config/appConfig.properties")
@SpringBootApplication
public class SysCourierBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysCourierBackendApplication.class, args);
    }

}
