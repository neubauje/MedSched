package com.techelevator.model.Patient;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Patient{
    private long userId;
    private long patientId;

//    @Past(message = "Must be a valid date in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

//    @email(message = "Must be a valid email address")
    @NotBlank(message = "Please enter an email address for communications")
    private String email;

    @NotNull(message = "Phone number is required")
    private long phoneNumber;

    @NotBlank(message = "Please provide an emergency contact")
    private String emergencyName;

    @NotNull(message = "We need some way to contact your emergency contact.")
    private long emergencyContact;

    @NotBlank(message = "Please provide the name of your insurance provider")
    @NotEmpty(message = "Insurance cannot be empty")
    @NotNull(message = "blah")
    private String insurance;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public long getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(long emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }


    /**
     * @return the patientId
     */
    public long getPatientId() {
        return patientId;
    }

    /**
     * @param PatientId the id to set
     */
    public void setPatientId(long PatientId) {
        this.patientId = PatientId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
