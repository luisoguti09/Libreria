package com.libreria.Libreria.Seguridad;

import com.libreria.Libreria.Servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class Seguridad extends WebSecurityConfigurerAdapter {
    
    @Autowired 
    private ClienteServicio clinServ;

//    @Autowired
//    private BCryptPasswordEncoder encoder;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(clinServ).passwordEncoder(encoder);
//    }

  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()
                .and().formLogin()
                .loginPage("/login") // Que formulario esta mi login
                .loginProcessingUrl("/logincheck")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/inicio") // A que URL viaja
                .permitAll()
                .and().logout() // Aca configuro la salida
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loguin?/logout")
                .permitAll().and().csrf().disable();
    }
    
//     @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/css/*", "/js/*", "/img/*",
//                        "/**").permitAll()
//                .and().
//                formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/logincheck")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/inicio")
//                .permitAll()
//                .and().logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout")
//                .permitAll().
//                and().csrf().disable();
//    }

}
