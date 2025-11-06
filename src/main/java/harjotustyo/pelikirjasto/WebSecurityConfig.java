package harjotustyo.pelikirjasto;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(toH2Console()).permitAll()
                .requestMatchers(HttpMethod.GET, "/api/games", "/css/**").permitAll()
                .requestMatchers("/delete/{id}").hasAuthority("ADMIN")
                .requestMatchers("/addGame", "/saveGame").authenticated()
                .anyRequest().authenticated())
                .formLogin((form) -> form
                        .defaultSuccessUrl("/games", true)
                        .permitAll())
                .logout((logout) -> logout.permitAll());

        http.csrf((csrf) -> csrf.ignoringRequestMatchers(toH2Console()));
        http.headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()));

        return http.build();
    }

}
