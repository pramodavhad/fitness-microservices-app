package com.fitness.activityservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserValidationService {
	
	private static final Logger log = LoggerFactory.getLogger(UserValidationService.class);
	
	private final WebClient userServiceWebClient;
	
	public boolean validateUser(String userId) {
		log.info("Calling User Validation API for userId: {}", userId);
        try {
            return userServiceWebClient
                    .get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("User Not Found: " + userId);
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RuntimeException("Invalid Request: " + userId);
            }
            throw new RuntimeException("Error calling USER-SERVICE: " + e.getMessage());
        }
    }

//	@Autowired
//  private RestTemplate restTemplate;
//
//  public boolean validateUser(String userId) {
//      String url = "http://USER-SERVICE/api/users/{userId}/validate";
//
//      try {
//          ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class, userId);
//          return Boolean.TRUE.equals(response.getBody());
//      } catch (HttpClientErrorException.NotFound e) {
//          throw new RuntimeException("User Not Found: " + userId);
//      } catch (HttpClientErrorException.BadRequest e) {
//          throw new RuntimeException("Invalid Request: " + userId);
//      } catch (Exception e) {
//          throw new RuntimeException("Error calling USER-SERVICE: " + e.getMessage());
//      }
//  }
}
