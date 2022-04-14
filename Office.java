package com.techelevator.model.Office;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class Office {

  private long officeId;
  @NotBlank(message = "Office Name is required")
  private String officeName;
  @NotBlank(message = "Office address is required")
  private String address;
  private String daysOpen;
  @NotNull(message = "Please enter the opening time")
  @DateTimeFormat(pattern="HH:mm")
  private LocalTime openingHours;
  @NotNull(message = "Please enter the closing time")
  @DateTimeFormat(pattern="HH:mm")
  private LocalTime closingHours;
  @NotBlank(message = "Phone number is required")
  private String phoneNumber;
  @NotNull(message = "Cost per hour is required. Enter 0 if pro bono.")
  private Double costPerHour;
  private int averageRating;

  public int getAverageRating() {
    return averageRating;
  }

  public void setAverageRating(int averageRating) {
    this.averageRating = averageRating;
  }

  public Office(long officeId, String officeName) {
    this.officeId = officeId;
    this.officeName = officeName;
  }
public Office(){

}

  public long getOfficeId() {
    return officeId;
  }

  public void setOfficeId(long officeId) {
    this.officeId = officeId;
  }


  public String getOfficeName() {
    return officeName;
  }

  public void setOfficeName(String officeName) {
    this.officeName = officeName;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getDaysOpen() {
    return daysOpen;
  }

  public void setDaysOpen(String daysOpen) {
    this.daysOpen = daysOpen;
  }


  public LocalTime getOpeningHours() {
    return openingHours;
  }

  public void setOpeningHours(LocalTime openingHours) {
    this.openingHours = openingHours;
  }


  public LocalTime getClosingHours() {
    return closingHours;
  }

  public void setClosingHours(LocalTime closingHours) {
    this.closingHours = closingHours;
  }


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  public Double getCostPerHour() {
    return costPerHour;
  }

  public void setCostPerHour(Double costPerHour) {
    this.costPerHour = costPerHour;
  }

}
