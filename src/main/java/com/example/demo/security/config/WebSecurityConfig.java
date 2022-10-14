package com.example.demo.security.config;

import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.Session;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
   /* @Autowired
    AuthenticationSuccessHandler successHandler;*/
   /* @Autowired
    protected FindByIndexNameSessionRepository<? extends Session> sessionRepository;*/

   /* @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("pactrick@gmail.com").password(bCryptPasswordEncoder.encode("pactrick@")).roles("USER")
                .and()
                .withUser("info@teratech.co.tz").password(bCryptPasswordEncoder.encode("tera@2011")).roles("ADMIN", "USER");

       *//* auth.authenticationProvider(daoAuthenticationProvider());*//*
    }*/
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("springuser").password(bCryptPasswordEncoder.encode("spring123")).roles("USER")
                .and()
                .withUser("springadmin").password(bCryptPasswordEncoder.encode("admin123")).roles("ADMIN", "USER");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/static/**","/features","/fleet-management", "/task-management","/aboutUs","/contactUs","/login","/register","/latra-vts","/equipment-tracking","/family-tracking","/customer","/school-bus-tracking","/Vehicle","/testimonies","/session").permitAll()
                .antMatchers("/userhome").authenticated()
              /*  .antMatchers("/admin**").hasRole("ADMIN")
                .antMatchers("/user**").hasRole("USER")*/
                .antMatchers("/api/v*/registration/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login?error = true")
                .defaultSuccessUrl("/tera-home-dashboard")
                /*.successHandler(successHandler).permitAll()*/
                .passwordParameter("password")
                .usernameParameter("email")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");


    }
  /*  @Bean
    protected SpringSessionBackedSessionRegistry sessionRegistry(){
        return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
    }*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**","/static/**","/images/**","/css/**","/js/**","/assets/**","/assets2/**");
    }



   /* @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }*/
}
