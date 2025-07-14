package com.fitness.aiservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;

@Service
public class ActivityMessegeListener {
	
	private static final Logger log = LoggerFactory.getLogger(ActivityMessegeListener.class);
	
	@Autowired
	private ActivityAIService activityAiService;
	
	@Autowired
	private RecommendationRepository recommendationRepository;
	
	@RabbitListener(queues = "activity.queue")
	public void processActivity(Activity activity) {
		log.info("Received activity for processing: {}", activity.getId());
//		log.info("Generated Recommendation: {}", activityAiService.generateRecommendation(activity));
		Recommendation recommendation = activityAiService.generateRecommendation(activity);
		recommendationRepository.save(recommendation);
	}

}
