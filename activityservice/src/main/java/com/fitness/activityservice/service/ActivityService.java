package com.fitness.activityservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;

@Service
public class ActivityService {
	
	private static final Logger log = LoggerFactory.getLogger(ActivityService.class);

	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private UserValidationService userValidationService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.key}")
	private String routingKey;
	

	public ActivityResponse trackActivity(ActivityRequest request) {
		
		boolean isValidUser = userValidationService.validateUser(request.getUserId());
		
		if(!isValidUser) {
			throw new RuntimeException("Invalid User: " + request.getUserId());
		}
		
		Activity activity = Activity.builder()
				.userId(request.getUserId())
				.type(request.getType())
				.duration(request.getDuration())
				.caloriesBurned(request.getCaloriesBurned())
				.startTime(request.getStartTime())
				.additionalMetrics(request.getAdditionalMetrics())
				.build();

		Activity savedActivity = activityRepository.save(activity);
		
		// Publish to RabbitMq for AI Processing
		try {
			rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);
		} catch(Exception e) {
			log.error("Failed to publish activity to RabbitMQ: ", e);
		}
		
		return mapToResponse(savedActivity);
	}

	private ActivityResponse mapToResponse(Activity activity) {
		ActivityResponse activityResponse = new ActivityResponse();
		activityResponse.setId(activity.getId());
		activityResponse.setUserId(activity.getUserId());
		activityResponse.setType(activity.getType());
		activityResponse.setDuration(activity.getDuration());
		activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
		activityResponse.setStartTime(activity.getStartTime());
		activityResponse.setAdditionalMetrics(activity.getAdditionalMetrics());
		activityResponse.setCreatedAt(activity.getCreatedAt());
		activityResponse.setUpdatedAt(activity.getUpdatedAt());
		return activityResponse;
	}

	public List<ActivityResponse> getUserActivities(String userId) {
		List<Activity> activities = activityRepository.findByUserId(userId);

		return activities.stream()
				.map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	public ActivityResponse getActivityById(String activityId) {
		return activityRepository.findById(activityId)
				.map(this::mapToResponse)
				.orElseThrow(() -> new RuntimeException("Activity Not Found With ID: " + activityId));
	}

}
