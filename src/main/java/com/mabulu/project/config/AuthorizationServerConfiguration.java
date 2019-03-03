package com.mabulu.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/*@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private static String REALM = "MY_OAUTH_REALM";

	private static String CLIENT_PASSWORD = "$2a$10$gQVQ4cKvqabrywb8f.UNpOU7lEeI82KX5w1ZWo/g3z0uZQJmUvPie";

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	
	// Here you will specify about `ClientDetailsService` 
// i.e. information about OAuth2 clients & where their info is located - memory , DB , LDAP etc.
// Sample code below.

		clients.inMemory().withClient("my-trusted-client")
				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust")
				.secret(CLIENT_PASSWORD).accessTokenValiditySeconds(40000000).// Access token is only valid for 2
																				// minutes.
				refreshTokenValiditySeconds(60000000);// Refresh token is only valid for 10 minutes.
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	// This can be used to configure security of your authorization server itself
// i.e. which user can generate tokens , changing default realm etc - Sample code below.

    // we're allowing access to the token only for clients with  'ROLE_TRUSTED_CLIENT' authority.
    // There are few more configurations and changing default realm is one of those 
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.realm(REALM + "/client");
	}

}*/