package com.techelevator.model.Notification;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Notification {

  private long notifId;
  private long doctorId;
  private long patientId;
  private String contents;
  private LocalDateTime dateTime;
  private Boolean acknowledged;
  private Boolean intendedForDoctor;

  public Boolean getIntendedForDoctor() {
    return intendedForDoctor;
  }

  public void setIntendedForDoctor(Boolean intendedForDoctor) {
    this.intendedForDoctor = intendedForDoctor;
  }

  public long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(long doctorId) {
    this.doctorId = doctorId;
  }

  public long getPatientId() {
    return patientId;
  }

  public void setPatientId(long patientId) {
    this.patientId = patientId;
  }

  public long getNotifId() {
    return notifId;
  }

  public void setNotifId(long notifId) {
    this.notifId = notifId;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public Boolean getAcknowledged() {
    return acknowledged;
  }

  public void setAcknowledged(Boolean acknowledged) {
    this.acknowledged = acknowledged;
  }
}
