package com.hussein.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>Title: WebSecurityConfiguration</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2020/1/6 11:36 AM
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Component
    class CustomUserDetailsServiceImpl implements UserDetailsService {

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            if ("admin".equals(username)) {
                return new SecurityUser("admin", "{noop}112233", "admin-role");
            } else if ("user".equals(username)) {
                return new SecurityUser("user", "{noop}112233", "user-role");
            }
            return null;
        }
    }

    @Data
    class SecurityUser implements UserDetails {

        private String username;

        private String password;

        private String role;

        public SecurityUser(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<>();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role);
            authorities.add(authority);
            return authorities;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
