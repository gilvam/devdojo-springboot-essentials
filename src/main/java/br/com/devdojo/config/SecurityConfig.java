package br.com.devdojo.config;

import br.com.devdojo.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // usar @PreAuthorize("hasRole('ADMIN')") no endPoint para uma ação
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(httpServletRequest -> new CorsConfiguration().applyPermitDefaultValues()) // liberar todas as urls CORS
                .and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, SecurityConstants.SIGN_UP_URL).permitAll()
                .antMatchers("/*/students/**").hasRole("USER")
                .antMatchers("/*/admin/**").hasRole("ADMIN")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))    // gera token e retorna quando faz login
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService)) // verifica token e ROLE a toda rota REST consultada
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
//        @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) {
//        try {
//            auth.inMemoryAuthentication()
//                    .withUser("user").password("{noop}123").roles("USER")
//                    .and()
//                    .withUser("admin").password("{noop}123").roles("USER", "ADMIN");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
