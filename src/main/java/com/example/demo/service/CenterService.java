package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Center;

import jakarta.validation.Valid;

public interface CenterService {

	Center saveCenter(@Valid Center center);

	List<Center> searchCenters(String detailedAddress, String city, String state, 
            String pincode, String coursesOffered, String centerName, 
            String contactEmail, String contactPhone);

}
