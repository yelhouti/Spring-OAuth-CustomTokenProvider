package com.elhouti.oauth2.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yeh on 06.06.2017.
 */
@Component
public class CustomResourceOwnerPasswordTokenGranter extends ResourceOwnerPasswordTokenGranter {

    @Autowired
    RestTemplate restTemplate;
    private static Logger LOGGER = LoggerFactory.getLogger(CustomResourceOwnerPasswordTokenGranter.class);

    public CustomResourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(authenticationManager, tokenServices, clientDetailsService, requestFactory);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        OAuth2Authentication auth = super.getOAuth2Authentication(client, tokenRequest);
        LOGGER.debug("scope:"+tokenRequest.getScope());
        if(tokenRequest.getScope().contains("trust")){
            if(restTemplate.exchange(null,String.class).getStatusCode().equals(HttpStatus.OK)){
                return auth;
            }
            else {
                throw new InvalidGrantException("Could not authenticate using two factor");
            }
        }else{
            return auth;
        }
    }
}
