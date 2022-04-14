package com.techelevator.model.Review;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

public class Review {

  private long reviewId;
  private long userId;
  private String message;

  @Max(value = 10, message = "maximum rating cannot exceed 10")
  @Min(value = 0, message = "minimum rating cannot be lower than 0")
  private long rating;


  private LocalDate dateSubmitted;
  private long officeId;
  private long doctorId;
  private String doctorResponse;

  public String getDoctorResponse() {
    return doctorResponse;
  }

  public void setDoctorResponse(String doctorResponse) {
    this.doctorResponse = doctorResponse;
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

  public long getReviewId() {
    return reviewId;
  }

  public void setReviewId(long reviewId) {
    this.reviewId = reviewId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public long getRating() {
    return rating;
  }

  public void setRating(long rating) {
    this.rating = rating;
  }


  public LocalDate getDateSubmitted() {
    return dateSubmitted;
  }

  public void setDateSubmitted(LocalDate dateSubmitted) {
    this.dateSubmitted = dateSubmitted;
  }

}
