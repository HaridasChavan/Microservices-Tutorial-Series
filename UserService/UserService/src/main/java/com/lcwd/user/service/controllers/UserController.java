package com.lcwd.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	@PreAuthorize(" hasAuthority('Admin')")

	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}

	int retryCount = 1;

	@PreAuthorize("hasAuthority(SCOPE_internal) || hasAuthority('Admin')")

	@GetMapping("/{userId}")
	// @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod =
	// "ratingHotelFallback")
	// @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "uerRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		logger.info("Get Singl User Handler :UserController");
		logger.info("Retry Count:{}", retryCount);
		retryCount++;

		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);

	}

	// Creating Fallback method for circuit Breaker
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
		// logger.info("Fallback is executed because service is down : ",
		// ex.getMessage());
		ex.printStackTrace();
		User user = new User.Builder().userId("141234").name("Dummy").email("dummy@gmail.com")
				.about("This user is created dummy because some service is down")
				// Pass the ratings list if needed
				.build();
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@GetMapping()
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);

	}
}