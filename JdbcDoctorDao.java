package com.techelevator.model.Doctor;

import com.techelevator.authentication.PasswordHasher;
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
public class JdbcDoctorDao implements DoctorDao {

    private JdbcTemplate jdbcTemplate;
    private PasswordHasher passwordHasher;

    /**
     * Create a new Doctor dao with the supplied data source and the password hasher
     * that will salt and hash all the passwords for Doctors.
     *
     * @param dataSource an SQL data source
     * @param passwordHasher an object to salt and hash passwords
     */
    @Autowired
    public JdbcDoctorDao(DataSource dataSource, PasswordHasher passwordHasher) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordHasher = passwordHasher;
    }
    @Autowired
    public JdbcUserDao userDao;


    private Doctor mapResultToDoctor(SqlRowSet results) {
        Doctor doctor = new Doctor();
        doctor.setUserId(results.getLong("user_id"));
        doctor.setDoctorId(results.getLong("doctor_id"));
        doctor.setOfficeId(results.getLong("office_id"));
        doctor.setFirstName(results.getString("firstname"));
        doctor.setLastName(results.getString("lastname"));
        doctor.setSpecialty(results.getString("specialty"));
        return doctor;
    }

    public void saveDoctor(Long UserId, Long OfficeId, String firstName, String lastName, String specialty) {
        long newId = jdbcTemplate.queryForObject(
                "INSERT INTO doctor(user_id, office_id, firstname, lastname, specialty) VALUES (?, ?, ?, ?, ?) RETURNING doctor_id", Long.class,
                UserId, OfficeId, firstName, lastName, specialty);
        Doctor newDoctor = new Doctor();
        newDoctor.setDoctorId(newId);
        newDoctor.setUserId(UserId);
        newDoctor.setOfficeId(OfficeId);
        newDoctor.setFirstName(firstName);
        newDoctor.setLastName(lastName);
        newDoctor.setSpecialty(specialty);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<Doctor>();
        String sqlSelectAllUsers = "SELECT id, user_name, role FROM app_user where role = 'Doctor' ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllUsers);
        while (results.next()) {
            User user = userDao.mapResultToUser(results);
            Doctor doctor = getOneDoctorByUserId(user.getId());
            doctors.add(doctor);
        }
        return doctors;
    }

    public Doctor getOneDoctorByUserId(Long userId){
        Doctor oneDoctor = null;
        String sql = "Select * from doctor where user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while (result.next()){oneDoctor = mapResultToDoctor(result);}
        return oneDoctor;
    }

    public Doctor getDoctorByDoctorId(Long doctorId){
        Doctor oneDoctor = null;
        String sql = "Select * from doctor where doctor_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, doctorId);
        while (result.next()){oneDoctor = mapResultToDoctor(result);}
        return oneDoctor;
    }

    public List<Doctor> getDoctorsByOffice(Long officeId){
        List<Doctor> officeDoctors = new ArrayList<>();
        String sql = "SELECT * from doctor where office_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, officeId);
        while (results.next()){
            officeDoctors.add(mapResultToDoctor(results));
        }
        return officeDoctors;
    }

    public void linkDoctorPatient(Long doctorId, Long patientId){
        String sql = "INSERT into patient_doctor(doctor_id, patient_id) values (?,?)";
        jdbcTemplate.update(sql, doctorId, patientId);
    }

    public void unlinkDoctorPatient(Long doctorId, Long patientId){
        String sql = "DELETE from patient_doctor where doctor_id = ? and patient_id = ?;";
        jdbcTemplate.update(sql, doctorId, patientId);
    }

    public Boolean isLinked(Long doctorId, Long patientId){
        String sql = "SELECT * from patient_doctor where doctor_id = ? and patient_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, doctorId, patientId);
        if (result.next()){return true;}
        else {return false;}
    }

    public List<Doctor> getDoctorsByPatient(Long patientId){
        List<Doctor> myDoctors = new ArrayList<>();
        String sql = "SELECT * from doctor join patient_doctor on doctor.doctor_id = patient_doctor.doctor_id where patient_id = ? order by lastname, firstname;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
        while (results.next()){
            myDoctors.add(mapResultToDoctor(results));
        }
        return myDoctors;
    }

    public void updateDoctor(Long doctorId, String firstName, String lastName, String specialty, Long officeId){
        String sql = "UPDATE doctor set firstname = ?, lastname = ?, specialty = ?, office_id = ? where doctor_id = ?;";
        jdbcTemplate.update(sql, firstName, lastName, specialty, officeId, doctorId);
    }

    public void assignToOffice(Long doctorId, Long officeId){
        String sql = "UPDATE doctor set office_id = ? where doctor_id = ?";
        jdbcTemplate.update(sql, officeId, doctorId);
    }
}
