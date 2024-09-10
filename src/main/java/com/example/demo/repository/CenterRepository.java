package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
	 @Query("SELECT DISTINCT c FROM Center c JOIN c.address a WHERE " +
	           "(:detailedAddress IS NULL OR LOWER(a.detailedAddress) LIKE LOWER(CONCAT('%', :detailedAddress, '%'))) AND " +
	           "(:city IS NULL OR LOWER(a.city) = LOWER(:city)) AND " +
	           "(:state IS NULL OR LOWER(a.state) = LOWER(:state)) AND " +
	           "(:pincode IS NULL OR a.pincode = :pincode) AND " +
	           "(:coursesOffered IS NULL OR EXISTS (SELECT 1 FROM c.coursesOffered co WHERE LOWER(co) LIKE LOWER(CONCAT('%', :coursesOffered, '%')))) AND " +
	           "(:centerName IS NULL OR LOWER(c.centerName) LIKE LOWER(CONCAT('%', :centerName, '%'))) AND " +
	           "(:contactEmail IS NULL OR LOWER(c.contactEmail) = LOWER(:contactEmail)) AND " +
	           "(:contactPhone IS NULL OR c.contactPhone = :contactPhone)")
	    List<Center> searchCenters(@Param("detailedAddress") String detailedAddress,
	                               @Param("city") String city,
	                               @Param("state") String state,
	                               @Param("pincode") String pincode,
	                               @Param("coursesOffered") String coursesOffered,
	                               @Param("centerName") String centerName,
	                               @Param("contactEmail") String contactEmail,
	                               @Param("contactPhone") String contactPhone);
}
