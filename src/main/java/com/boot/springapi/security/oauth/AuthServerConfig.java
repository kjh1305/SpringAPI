package com.boot.springapi.security.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * /oauth/token, /oauth/authorize
 *
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private TokenStore tokenStore;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private DataSource dataSource;

    //클라이언트에 대한 설정 DB, InMemory
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(dataSource)
        clients.inMemory()
                .withClient("iamclient")
                .secret(passwordEncoder.encode("iamsecret"))
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("read", "write")
                .accessTokenValiditySeconds(60*60)
                .refreshTokenValiditySeconds(6*60*60)
                .autoApprove(true);
    }

    //'/oauth/token', '/oauth/authorize' 등 엔드포인트의 대한 보안관련 설정
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
