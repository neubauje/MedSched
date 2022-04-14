package com.techelevator.model.TimeSlot;

import com.techelevator.model.Doctor.Doctor;
import com.techelevator.model.Doctor.JdbcDoctorDao;
import com.techelevator.model.Office.JdbcOfficeDao;
import com.techelevator.model.Office.Office;
import com.techelevator.model.Patient.JdbcPatientDao;
import com.techelevator.model.Patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcTimeSlotDao implements TimeSlotDao {
    private JdbcTemplate jdbcTemplate;
    TimeSlot timeSlot = new TimeSlot();

    @Autowired
    public JdbcDoctorDao doctorDao;
    @Autowired
    public JdbcPatientDao patientDao;
    @Autowired
    public JdbcOfficeDao officeDao;

    @Autowired
    public JdbcTimeSlotDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<TimeSlot> findFreeSlots(LocalDate date, Long doctorId) {
        String sqlGetAllTimeSlot = "Select *  from time_slot where (assigned = false AND doctor_id=? AND date=?) order by date, start_time;";
        List<TimeSlot> timeSlots = jdbcTemplate.query(sqlGetAllTimeSlot, new TimeSlotRowMapper(), doctorId, date);
        return timeSlots;
    }

    public List<TimeSlot> findFreeSlots(Long doctorId){
        String sqlGetAllTimeSlot = "Select *  from time_slot where (assigned = false AND doctor_id = ?) and date >= current_date order by date, start_time limit 50;";
        List<TimeSlot> timeSlots = jdbcTemplate.query(sqlGetAllTimeSlot, new TimeSlotRowMapper(), doctorId);
        return timeSlots;
    }

    public List<TimeSlot> seeMyAppointments(Long patientId) {
        String sqlGetAllTimeSlot = "Select *  from time_slot where assigned = true AND patient_id=? and date >= current_date order by date, start_time;";
        List<TimeSlot> timeSlots = jdbcTemplate.query(sqlGetAllTimeSlot, new TimeSlotRowMapper(), patientId);
        return timeSlots;
    }

    //Doctor will be able to see all the booked appointments by the using Doctor Id(As a Doctor)
    @Override
    public List<TimeSlot> seeMyAgenda(Long doctorId) {
        String sqlGetAllTimeSlot = "Select *  from time_slot where (assigned = true AND doctor_id=?) and date >= current_date ORDER BY date, start_time;";
        List<TimeSlot> timeSlot = jdbcTemplate.query(sqlGetAllTimeSlot, new TimeSlotRowMapper(), doctorId);

        return timeSlot;
    }

    public List<TimeSlot> seeMySchedule(Long doctorId){
        String sqlGetAllTimeSlot = "Select *  from time_slot where doctor_id = ? and date >= current_date ORDER BY date, start_time;";
        List<TimeSlot> timeSlot = jdbcTemplate.query(sqlGetAllTimeSlot, new TimeSlotRowMapper(), doctorId);
        return timeSlot;
    }

    public void createFreeSlot(long doctorId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        String sqlInsertTimeSlot = "Insert into time_slot( doctor_id, date, start_time, end_time, assigned) VALUES (?,?,?,?,false)";
        jdbcTemplate.update(sqlInsertTimeSlot, doctorId, date, startTime, endTime);
    }

    public TimeSlot updateTimeSlotByDoctor(LocalDate date, LocalTime startTime, LocalTime endTime) {
        String sql = "UPDATE time_slot SET date=?, start_time=?, end_time=?, description=? Where timeslot_id=?;";
        jdbcTemplate.update(sql, date, startTime, endTime);
        return timeSlot;

    }

    public TimeSlot getTimeSlot(Long timeSlotId) {
        String sql = "Select * from time_slot where timeslot_id=? ";
        TimeSlot timeSlot = jdbcTemplate.queryForObject(sql, new TimeSlotRowMapper(), timeSlotId);
        return timeSlot;
    }

    public TimeSlot updateAppointByPatient(TimeSlot timeslot) {
        String sql = "UPDATE time_slot SET patient_id = ?, assigned = true, description = ? where timeslot_id = ?";
        jdbcTemplate.update(sql, timeslot.getPatientId(), timeslot.getDescription(), timeslot.getTimeslotId());
        return timeSlot;
    }

    public void cancelAppointment(TimeSlot timeSlot){
        String sql = "UPDATE time_slot SET patient_id = null, assigned = false, description = null where timeslot_id = ?";
        jdbcTemplate.update(sql, timeSlot.getTimeslotId());
    }

    public TimeSlot deleteTimeSlot(long timeslotId) {

        String sql = "DELETE FROM time_slot WHERE timeslot_id = ?;";
        jdbcTemplate.update(sql, timeslotId);
        return timeSlot;


    }

    public static class TimeSlotRowMapper implements RowMapper<TimeSlot> {

        @Override
        public TimeSlot mapRow(ResultSet rs, int i) throws SQLException {
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setTimeslotId(rs.getLong("timeslot_id"));
            timeSlot.setDoctorId(rs.getLong("doctor_id"));
            timeSlot.setDate(rs.getDate("date").toLocalDate());
            timeSlot.setStartTime(rs.getTime("start_time").toLocalTime());
            timeSlot.setEndTime(rs.getTime("end_time").toLocalTime());
            timeSlot.setAssigned(rs.getBoolean("assigned"));
            timeSlot.setPatientId(rs.getLong("patient_id"));
            timeSlot.setDescription(rs.getString("description"));
            return timeSlot;
        }
    }
}
