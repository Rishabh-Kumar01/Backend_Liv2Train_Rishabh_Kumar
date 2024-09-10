package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Center;
import com.example.demo.service.CenterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class CenterController {
	
	@Autowired
    private CenterService centerService;
	
	@PostMapping
	@Operation(summary = "Create a new center")
    @ApiResponse(responseCode = "201", description = "Center created", 
                 content = @Content(schema = @Schema(implementation = Center.class)))    
	public ResponseEntity<Center> createCenter(@Valid @RequestBody Center center) {
        Center savedCenter = centerService.saveCenter(center);
        return new ResponseEntity<>(savedCenter, HttpStatus.CREATED);
    }

	@GetMapping("/search")
    @Operation(summary = "Get all centers")
    @ApiResponse(responseCode = "200", description = "List of centers retrieved", 
                 content = @Content(schema = @Schema(implementation = Center.class)))
    public ResponseEntity<List<Center>> searchCenters(
            @RequestParam(required = false) String detailedAddress,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String pincode,
            @RequestParam(required = false) String coursesOffered,
            @RequestParam(required = false) String centerName,
            @RequestParam(required = false) String contactEmail,
            @RequestParam(required = false) String contactPhone) {
        
        List<Center> centers = centerService.searchCenters(detailedAddress, city, state, 
                                                           pincode, coursesOffered, centerName, 
                                                           contactEmail, contactPhone);
        return new ResponseEntity<>(centers, HttpStatus.OK);
    }
}
