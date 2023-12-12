package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {
     
	@GetMapping("/hotels/{hotelId}")
	 Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
		
	
	