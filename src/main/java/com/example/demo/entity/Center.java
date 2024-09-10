package com.example.demo.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "centers")
public class Center {

	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Center name is required")
    @Size(max = 40, message = "Center name must be at most 40 characters")
    @Column(name = "center_name")
    private String centerName;

    @NotBlank(message = "Center code is required")
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center code must be exactly 12 alphanumeric characters")
    @Column(name = "center_code", unique = true)
    private String centerCode;

    @NotNull(message = "Address is required")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Min(value = 0, message = "Student capacity must be a positive number")
    @Column(name = "student_capacity")
    private Integer studentCapacity;

    @ElementCollection
    @CollectionTable(name = "center_courses", joinColumns = @JoinColumn(name = "center_id"))
    @Column(name = "course")
    private List<String> coursesOffered;

    @Column(name = "created_on", nullable = false, updatable = false)
    private Long createdOn;

    @Email(message = "Invalid email format")
    @Column(name = "contact_email")
    private String contactEmail;

    @Pattern(regexp = "^(\\+91|91)?[1-9]\\d{9}$", message = "Invalid phone number")
    @Column(name = "contact_phone")
    private String contactPhone;

    @PrePersist
    protected void onCreate() {
        createdOn = Instant.now().getEpochSecond();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getStudentCapacity() {
        return studentCapacity;
    }

    public void setStudentCapacity(Integer studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
