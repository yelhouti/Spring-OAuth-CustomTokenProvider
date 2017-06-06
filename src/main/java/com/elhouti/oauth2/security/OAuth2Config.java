package com.elhouti.oauth2.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	private static Logger LOGGER = LoggerFactory.getLogger(OAuth2Config.class);

	@Autowired
	private AuthenticationManager authenticationManager;

//	@Autowired
//	private TokenStore tokenStore;
	@Autowired
	private CustomResourceOwnerPasswordTokenGranter customResourceOwnerPasswordTokenGranter;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		LOGGER.debug("authenticationManager: "+authenticationManager);
		//endpoints.tokenStore(tokenStore);
		endpoints.tokenStore(new InMemoryTokenStore());
		endpoints.tokenGranter(customResourceOwnerPasswordTokenGranter);

	}

	//
	@Override // [3]
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// @formatter:off
		clients.inMemory().withClient("client-with-resource-owner-password-flow")
			.secret("password").authorizedGrantTypes("password","refresh_token")
			.authorities("ROLE_CLIENT").scopes("read", "trust")
			.accessTokenValiditySeconds(60)
			.refreshTokenValiditySeconds(30*24*60)
			.resourceIds("resource1");
		// @formatter:on
	}
}
