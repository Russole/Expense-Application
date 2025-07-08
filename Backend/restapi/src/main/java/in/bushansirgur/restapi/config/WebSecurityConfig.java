package in.bushansirgur.restapi.config;

import in.bushansirgur.restapi.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                // /login 和 /register 是開放的，不需要 JWT，也不會被 SecurityContextHolder 驗證
                .authorizeHttpRequests(auth -> auth.requestMatchers("/login", "/register").permitAll().anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // UsernamePasswordAuthenticationFilter 默認行為是監聽 /login 請求，並檢查 request 中的登錄資訊。
                // AuthenticationManager 會調用 DaoAuthenticationProvider 來驗證用戶憑證
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class) // 讓請求在進行用戶名與密碼驗證之前先經過 JWT（JSON Web Token）驗證
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    // 根據已註冊的 AuthenticationProvider 來建立 AuthenticationManager。
    // 比對成功，將用戶信息存入 SecurityContext，返回 200 OK。
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService); // 調用 UserDetailsService（自訂的 customUserDetailsService）來 查詢用戶。
        authProvider.setPasswordEncoder(passwordEncoder()); // 使用 passwordEncoder() 來比對密碼。
        return authProvider;
    }

    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
