package com.techelevator.model.Notification;


import com.techelevator.model.Doctor.Doctor;
import com.techelevator.model.Doctor.JdbcDoctorDao;
import com.techelevator.model.Office.Office;
import com.techelevator.model.Patient.JdbcPatientDao;
import com.techelevator.model.Patient.Patient;
import com.techelevator.model.Review.Review;
import com.techelevator.model.Review.ReviewDao;
import com.techelevator.model.TimeSlot.TimeSlot;
import com.techelevator.model.User.JdbcUserDao;
import com.techelevator.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcNotificationDao implements NotificationDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcNotificationDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Autowired
    private JdbcUserDao userDao;
    @Autowired
    private JdbcDoctorDao doctorDao;
    @Autowired
    private JdbcPatientDao patientDao;

    public List<Notification> getDoctorNewAlerts(Long doctorId) {
        List<Notification> myNewAlerts = new ArrayList<>();
        String sql = "Select * from notification where doctor_id = ? and acknowledged = false and intended_for_doctor = true order by date_time;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
        while (results.next()) {
            myNewAlerts.add(mapRowToNotification(results));
        }
        return myNewAlerts;
    }

    public List<Notification> getDoctorOldAlerts(Long doctorId) {
        List<Notification> myOldAlerts = new ArrayList<>();
        String sql = "Select * from notification where doctor_id = ? and acknowledged = true and intended_for_doctor = true order by date_time desc limit 50;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
        while (results.next()) {
            myOldAlerts.add(mapRowToNotification(results));
        }
        return myOldAlerts;
    }

    public List<Notification> getPatientNewAlerts(Long patientId) {
        List<Notification> myNewAlerts = new ArrayList<>();
        String sql = "Select * from notification where patient_id = ? and acknowledged = false and intended_for_doctor = false order by date_time;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
        while (results.next()) {
            myNewAlerts.add(mapRowToNotification(results));
        }
        return myNewAlerts;
    }

    public List<Notification> getPatientOldAlerts(Long patientId) {
        List<Notification> myOldAlerts = new ArrayList<>();
        String sql = "Select * from notification where patient_id = ? and acknowledged = true and intended_for_doctor = false order by date_time desc limit 50;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
        while (results.next()) {
            myOldAlerts.add(mapRowToNotification(results));
        }
        return myOldAlerts;
    }

    public Notification getNotifById(Long notifId){
        Notification alert = null;
        String sql = "SELECT * from notification where notif_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, notifId);
        while (result.next()){
            alert = mapRowToNotification(result);
        }
        return alert;
    }

    public void createNotif(Long patientId, Long doctorId, String message, Boolean forDoctor) {
        String sql = "Insert into notification (patient_id, doctor_id, contents, date_time, acknowledged, intended_for_doctor) " +
                "values (?, ?, ?, localtimestamp, false, ?)";
        jdbcTemplate.update(sql, patientId, doctorId, message, forDoctor);
    }


    public void acknowledgeAlert(Notification alert){
      String sql = "Update notification SET acknowledged = true where notif_id = ?";
      jdbcTemplate.update(sql, alert.getNotifId());
    }


    private Notification mapRowToNotification(SqlRowSet alert) {
        Notification notif = new Notification();
        notif.setNotifId(alert.getLong("notif_id"));
        notif.setDoctorId(alert.getLong("doctor_id"));
        notif.setPatientId(alert.getLong("patient_id"));
        notif.setContents(alert.getString("contents"));
        notif.setDateTime(alert.getTimestamp("date_time").toLocalDateTime());
        notif.setAcknowledged(alert.getBoolean("acknowledged"));
        notif.setIntendedForDoctor(alert.getBoolean("intended_for_doctor"));
        return notif;
    }

    public String generateContentForDoctorCreate(TimeSlot appointment, Patient patient){
        String content = "";
        content = content + patient.getFirstName() + " " + patient.getLastName();
        content = content + " booked an appointment with you for ";
        content = content + appointment.getDate() + " at " + appointment.getStartTime();
        return content;
    }

    public String generateContentForDoctorCancel(TimeSlot appointment, Patient patient){
        String content = "";
        content = content + patient.getFirstName() + " " + patient.getLastName();
        content = content + " cancelled their appointment with you from ";
        content = content + appointment.getDate() + " at " + appointment.getStartTime();
        return content;
    }

    public String generateForDoctorDrop(Patient patient){
        return patient.getFirstName() + " " + patient.getLastName() + " has decided not to be your patient anymore. Their appointments with you may need to be cancelled.";
    }

    public String generateContentForPatientCancel(TimeSlot appointment, Doctor doctor){
        String content = "";
        content = content + doctor.getFirstName() + " " + doctor.getLastName();
        content = content + " cancelled your appointment on ";
        content = content + appointment.getDate() + " at " + appointment.getStartTime();
        return content;
    }

    public String generateContentForPatientUpdate(TimeSlot appointment, Doctor doctor){
        String content = "";
        content = content + doctor.getFirstName() + " " + doctor.getLastName();
        content = content + " changed your appointment, it is now scheduled for ";
        content = content + appointment.getDate() + " at " + appointment.getStartTime();
        return content;
    }

    public String generateForPatientMoved(TimeSlot appointment, Doctor doctor, Office oldOffice, Office newOffice){
        String content = "";
        content = content + "Your doctor, " + doctor.getFirstName() + " " + doctor.getLastName();
        content = content + " has moved to a different location. Your appointment on " + appointment.getDate();
        content = content + " was previously located at " + oldOffice.getAddress() + ", but now it will be located at " + newOffice.getAddress() + ".";
        return content;
    }

    public String generateForPatientNameChange(TimeSlot appointment, Doctor oldDoctor, Doctor newDoctor){
        String content = "";
        content = content + "Your doctor for your appointment on " + appointment.getDate();
        content = content + " has changed their name from " + oldDoctor.getFirstName() + " " + oldDoctor.getLastName();
        content = content + " to " + newDoctor.getFirstName() + " " + newDoctor.getLastName() + ". Make note of this for your check-in.";
        return content;
    }

    public boolean hasUnreadNotifs(Long userId){
        User user = userDao.getUserById(userId);
        String role = user.getRole();
        List<Notification> myAlerts = null;
        if (role.equals("Doctor")){
            Doctor doctor = doctorDao.getOneDoctorByUserId(userId);
            myAlerts = getDoctorNewAlerts(doctor.getDoctorId());
        }
        else {
            Patient patient = patientDao.getOnePatient(userId);
           myAlerts = getPatientNewAlerts(patient.getPatientId());
        }
            if (myAlerts.size() > 0) {return true;}
            else {return false;}
    }
}
