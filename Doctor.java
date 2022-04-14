package com.techelevator.model.Doctor;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

public class Doctor {
    private long doctorId;
    private long officeId;

    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Please provide some information about yourself. This will appear on your office summary.")
    private String specialty;
    private long userId;
//    private boolean officeSelect;

//    public boolean isOfficeSelect() {
//        return officeSelect;
//    }
//
//    public void setOfficeSelect(boolean officeSelect) {
//        this.officeSelect = officeSelect;
//    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(long officeId) {
        this.officeId = officeId;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

}
