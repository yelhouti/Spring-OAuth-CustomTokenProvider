package com.elhouti.oauth2.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources)
			throws Exception {
		resources.resourceId("resource1");
	}
//
//	@Bean
//	public JwtTokenStore tokenStore() throws Exception {
//		JwtAccessTokenConverter enhancer = new JwtAccessTokenConverter();
//		enhancer.setSigningKey("signing_key");
//		// N.B. in a real system you would have to configure the verifierKey (or use JdbcTokenStore)
//		enhancer.afterPropertiesSet();
//		return new JwtTokenStore(enhancer);
//	}

}
