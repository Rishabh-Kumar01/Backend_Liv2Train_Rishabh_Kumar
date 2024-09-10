package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Center;
import com.example.demo.error.handler.InvalidCenterDataException;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CenterRepository;
import com.example.demo.service.CenterService;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    private CenterRepository centerRepository;
    
    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional
    public Center saveCenter(Center center) {
    	
    	if (center.getAddress() == null) {
            throw new InvalidCenterDataException("Center must have an address");
        }
    	
    	 center.setContactPhone(formatPhoneNumber(center.getContactPhone()));
    	 
    	 if (center.getContactEmail() != null) {
             center.setContactEmail(center.getContactEmail().toLowerCase());
         }
    	 
    	 Address savedAddress = addressRepository.save(center.getAddress());

         center.setAddress(savedAddress);
         Center savedCenter = centerRepository.save(center);

         return savedCenter;
    }

    @Override
    public List<Center> searchCenters(String detailedAddress, String city, String state, 
                                      String pincode, String coursesOffered, String centerName, 
                                      String contactEmail, String contactPhone) {
        return centerRepository.searchCenters(detailedAddress, city, state, pincode, 
                                              coursesOffered, centerName, contactEmail, contactPhone);
    }
    
    private String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return phoneNumber;
        }
        
        String digitsOnly = phoneNumber.replaceAll("\\D", "");
        
        if (digitsOnly.length() == 10) {
            return "+91" + digitsOnly;
        }
        
        if (digitsOnly.length() == 12 && digitsOnly.startsWith("91")) {
            return "+" + digitsOnly;
        }
        
        return phoneNumber;
    }
}
