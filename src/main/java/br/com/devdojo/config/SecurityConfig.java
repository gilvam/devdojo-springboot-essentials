package br.com.devdojo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // usar @PreAuthorize("hasRole('ADMIN')") no endPoint para uma ação
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() // qualquer requisição deve ser autenticada
                .and().httpBasic() // manda no header a autorização
                .and().csrf().disable()
                ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.inMemoryAuthentication()
                    .withUser("user").password("{noop}123").roles("USER")
                    .and()
                    .withUser("admin").password("{noop}123").roles("USER", "ADMIN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
