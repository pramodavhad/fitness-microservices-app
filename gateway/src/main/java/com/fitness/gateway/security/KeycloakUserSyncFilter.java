package com.fitness.gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.fitness.gateway.user.RegisterRequest;
import com.fitness.gateway.user.UserService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import reactor.core.publisher.Mono;

@Component
public class KeycloakUserSyncFilter implements WebFilter{
	
	private static Logger log = LoggerFactory.getLogger(KeycloakUserSyncFilter.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain){
		String token = exchange.getRequest().getHeaders().getFirst("Authorization");
		String userId = exchange.getRequest().getHeaders().getFirst("X-User-ID");
		
		RegisterRequest registerRequest = getUserDetails(token);
		
		if(userId == null) {
			userId = registerRequest.getKeycloakId();
		}
		
		if(userId != null && token != null) {
			String finalUserId = userId;
			return userService.validateUser(userId)
					.flatMap(exist -> {
						if(!exist) {
							//Register User
							
							if(registerRequest != null) {
								return userService.registerUser(registerRequest)
										.then(Mono.empty());
							} else {
								return Mono.empty();
							}
							
						} else {
							log.info("User already exist, Skipping sync..");
							return Mono.empty();
						}
					})
					.then(Mono.defer(() -> {
						ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
								.header("X-User-ID", finalUserId)
								.build();
						return chain.filter(exchange.mutate().request(mutatedRequest).build());
					}));
		}
		return chain.filter(exchange);
	}

	private RegisterRequest getUserDetails(String token) {
		try{
			String tokenWithoutBearer = token.replace("Bearer ", "").trim();
			SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
			JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
			
			RegisterRequest registerRequest = new RegisterRequest(); 
			registerRequest.setEmail(claims.getStringClaim("email"));
			registerRequest.setKeycloakId(claims.getStringClaim("sub"));
			registerRequest.setPassword("dummy@123");
			registerRequest.setFirstName(claims.getStringClaim("given_name"));
			registerRequest.setLastName(claims.getStringClaim("family_name"));
			return registerRequest;
		} catch(Exception e ) {
			e.printStackTrace();
			return null;
		}
	}

}
