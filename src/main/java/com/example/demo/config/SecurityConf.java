package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.PrincipalDetailsService;
import com.example.demo.security.TokenProvider;
import com.example.demo.security.oauth2.CookieOAuth2AuthorizationRequestRepository;
import com.example.demo.security.oauth2.InMemoryRequestRepository;
import com.example.demo.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.example.demo.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity( prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@ComponentScan("com.example.demo.security.providers")
public class SecurityConf  extends WebSecurityConfigurerAdapter {


      private final PrincipalDetailsService principalDetailsService;

      private final CookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository;

      private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

      private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

      private final TokenProvider tokenProvider;

      private final ObjectMapper mapper;

      private final InMemoryRequestRepository inMemoryRequestRepository;


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationFilter authenticationFilter(){
        return new JwtAuthenticationFilter();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()

                     .antMatchers( "/auth/**","/cont/**","/serial/public/**","/oauth2/**","/login/**").permitAll()
                     .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository)
                .and()
                .successHandler( this::successHandler )
                .and()
                .exceptionHandling()
                .authenticationEntryPoint( this::authenticationEntryPoint )
                .and().logout(cust -> cust.addLogoutHandler( this::logout ).logoutSuccessHandler( this::onLogoutSuccess ));






        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    private void logout(HttpServletRequest request, HttpServletResponse response,
                        Authentication authentication) {
        // You can process token here
        System.out.println("Auth token is - " + request.getHeader( "Authorization" ));
    }

    void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                         Authentication authentication) throws IOException, ServletException {
        // this code is just sending the 200 ok response and preventing redirect
        response.setStatus( HttpServletResponse.SC_OK );
    }




    private void successHandler( HttpServletRequest request,
                                 HttpServletResponse response, Authentication authentication ) throws IOException {
        String token = "qwe222";
        response.getWriter().write(
                mapper.writeValueAsString( Collections.singletonMap( "accessToken", token ) )
        );
    }

    private void authenticationEntryPoint(HttpServletRequest request, HttpServletResponse response,
                                          AuthenticationException authException ) throws IOException {
        response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
        response.getWriter().write( mapper.writeValueAsString( Collections.singletonMap( "error", "Unauthenticated" ) ) );
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/static/js**","/v3/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/v2/api-docs");

    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired

    protected void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(principalDetailsService)
                .passwordEncoder(getEncoder());
    }

}
