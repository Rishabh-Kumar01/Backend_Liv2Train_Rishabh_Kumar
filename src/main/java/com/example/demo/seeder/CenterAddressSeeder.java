package com.example.demo.seeder;

import com.example.demo.entity.Center;
import com.example.demo.entity.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CenterRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class CenterAddressSeeder implements CommandLineRunner {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        if (centerRepository.count() == 0 && addressRepository.count() == 0) {
            try {
                ClassPathResource resource = new ClassPathResource("data/centers.json");
                InputStream inputStream = resource.getInputStream();
                List<CenterData> centerDataList = objectMapper.readValue(inputStream, new TypeReference<List<CenterData>>() {});
                
                for (CenterData centerData : centerDataList) {
                    Address address = new Address();
                    address.setDetailedAddress(centerData.getAddress().getDetailedAddress());
                    address.setCity(centerData.getAddress().getCity());
                    address.setState(centerData.getAddress().getState());
                    address.setPincode(centerData.getAddress().getPincode());
                    
                    Center center = new Center();
                    center.setCenterName(centerData.getCenterName());
                    center.setCenterCode(centerData.getCenterCode());
                    center.setAddress(address);
                    center.setStudentCapacity(centerData.getStudentCapacity());
                    center.setCoursesOffered(centerData.getCoursesOffered());
                    center.setContactEmail(centerData.getContactEmail());
                    center.setContactPhone(centerData.getContactPhone());
                    
                    centerRepository.save(center);
                }
                
                System.out.println("Center and Address data seeded successfully!");
            } catch (Exception e) {
                System.out.println("Unable to seed center and address data: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Center and Address data already exists, skipping seeding.");
        }
    }

    // Inner classes to match the JSON structure
    private static class CenterData {
        private String centerName;
        private String centerCode;
        private AddressData address;
        private Integer studentCapacity;
        private List<String> coursesOffered;
        private String contactEmail;
        private String contactPhone;

        // Getters and setters
        // ...

        public String getCenterName() { return centerName; }
        public void setCenterName(String centerName) { this.centerName = centerName; }
        public String getCenterCode() { return centerCode; }
        public void setCenterCode(String centerCode) { this.centerCode = centerCode; }
        public AddressData getAddress() { return address; }
        public void setAddress(AddressData address) { this.address = address; }
        public Integer getStudentCapacity() { return studentCapacity; }
        public void setStudentCapacity(Integer studentCapacity) { this.studentCapacity = studentCapacity; }
        public List<String> getCoursesOffered() { return coursesOffered; }
        public void setCoursesOffered(List<String> coursesOffered) { this.coursesOffered = coursesOffered; }
        public String getContactEmail() { return contactEmail; }
        public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
        public String getContactPhone() { return contactPhone; }
        public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    }

    private static class AddressData {
        private String detailedAddress;
        private String city;
        private String state;
        private String pincode;

        // Getters and setters
        // ...

        public String getDetailedAddress() { return detailedAddress; }
        public void setDetailedAddress(String detailedAddress) { this.detailedAddress = detailedAddress; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getPincode() { return pincode; }
        public void setPincode(String pincode) { this.pincode = pincode; }
    }
}