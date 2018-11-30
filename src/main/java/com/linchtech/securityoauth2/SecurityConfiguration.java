package com.linchtech.securityoauth2;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author 107
 * @date 2018-11-28 23:05
 **/
@Component
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                antMatcher("/**")
                // 所有请求都得经过认证和授权
                .authorizeRequests().anyRequest().authenticated()
                .and().authorizeRequests().antMatchers("/","/anon").permitAll()
                .and()
                // 这里之所以要禁用csrf，是为了方便。
                // 否则，退出链接必须要发送一个post请求，请求还得带csrf token
                // 那样我还得写一个界面，发送post请求
                .csrf().disable()
                // 退出的URL是/logout
                .logout().logoutUrl("/logout").permitAll()
                // 退出成功后，跳转到/路径。
                .logoutSuccessUrl("/");
    }
}
