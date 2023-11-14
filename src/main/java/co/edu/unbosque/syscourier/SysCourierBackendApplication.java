package co.edu.unbosque.syscourier;

import co.edu.unbosque.syscourier.filters.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@PropertySource("classpath:config/appConfig.properties")
@SpringBootApplication
public class SysCourierBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysCourierBackendApplication.class, args);
    }

    /**
     * Configuración de seguridad para la aplicación.
     */
    @EnableWebSecurity
    @Configuration
    static
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/loginMensajero").permitAll()
                    .anyRequest().authenticated();
        }

    }
}
