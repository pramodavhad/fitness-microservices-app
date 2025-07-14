package com.fitness.activityservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.service.ActivityService;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@PostMapping
	public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request, @RequestHeader("X-User-ID") String userId) {
		if(userId != null) {
			request.setUserId(userId);
		}
		return ResponseEntity.ok(activityService.trackActivity(request));
	}
	
	@GetMapping
	public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader("X-User-ID") String userId){
		return ResponseEntity.ok(activityService.getUserActivities(userId));
	}
	
	@GetMapping("/{activityId}")
	public ResponseEntity<ActivityResponse> getActivity(@PathVariable String activityId){
		return ResponseEntity.ok(activityService.getActivityById(activityId));
	}
}
