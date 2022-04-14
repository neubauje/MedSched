package com.techelevator.model.Patient;

import com.techelevator.authentication.PasswordHasher;
import com.techelevator.model.Doctor.JdbcDoctorDao;
import com.techelevator.model.TimeSlot.JdbcTimeSlotDao;
import com.techelevator.model.TimeSlot.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPatientDao implements PatientDao {

    private JdbcTemplate jdbcTemplate;
    private PasswordHasher passwordHasher;

    /**
     * Create a new Patient dao with the supplied data source and the password hasher
     * that will salt and hash all the passwords for Patients.
     *
     * @param dataSource an SQL data source
     * @param passwordHasher an object to salt and hash passwords
     */
    @Autowired
    public JdbcPatientDao(DataSource dataSource, PasswordHasher passwordHasher) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordHasher = passwordHasher;
    }

    @Autowired
    private JdbcDoctorDao doctorDao;
    @Autowired
    private JdbcTimeSlotDao timeSlotDao;

    public Patient savePatient(Long userId, String firstName, String lastName, LocalDate dateOfBirth, long phoneNumber, String email, long emergencyContact, String emergencyName, String insurance) {
        long newId = jdbcTemplate.queryForObject(
                "INSERT INTO patient(user_id, firstname, lastname, dob, phone, email, emergencyphone, emergencyname, insurance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING patient_id", Long.class,
                userId, firstName, lastName, dateOfBirth, phoneNumber, email, emergencyContact, emergencyName, insurance);

        Patient newPatient = new Patient();
        newPatient.setPatientId(newId);
        newPatient.setUserId(userId);
        newPatient.setFirstName(firstName);
        newPatient.setLastName(lastName);
        newPatient.setDateOfBirth(dateOfBirth);
        newPatient.setPhoneNumber(phoneNumber);
        newPatient.setEmail(email);
        newPatient.setEmergencyContact(emergencyContact);
        newPatient.setEmergencyName(emergencyName);
        newPatient.setInsurance(insurance);
        return newPatient;
    }

    /**
     * Look for a Patient with the given Username and Password. Since we don't
     * know the password, we will have to get the Patient's salt from the database,
     * hash the password, and compare that against the hash in the database.
     *
     * @param Username the user name of the Patient we are checking
     * @param Password the password of the Patient we are checking
     * @return true if the Patient is found and their password matches
     */

    /**
     * Get all of the Patient from the database.
     * @return a List of Patient objects
     */

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<Patient>();
        String sqlSelectAllUsers = "SELECT id, user_name, role FROM app_user where role is 'Patient' ;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllUsers);

        while (results.next()) {
            Patient patient = mapResultToPatient(results);
            patients.add(patient);
        }

        return patients;
    }

    private Patient mapResultToPatient(SqlRowSet results) {
        Patient patient = new Patient();
        patient.setPatientId(results.getLong("patient_id"));
        patient.setUserId(results.getLong("user_id"));
        patient.setFirstName(results.getString("firstname"));
        patient.setLastName(results.getString("lastname"));
        patient.setDateOfBirth(results.getDate("dob").toLocalDate());
        patient.setPhoneNumber(results.getLong("phone"));
        patient.setEmail(results.getString("email"));
        patient.setEmergencyContact(results.getLong("emergencyphone"));
        patient.setEmergencyName(results.getString("emergencyname"));
        patient.setInsurance(results.getString("insurance"));
        return patient;
    }

    public Patient getOnePatient(Long userId){
        Patient onePatient = null;
        String sql = "Select * from patient where user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while (result.next()){onePatient = mapResultToPatient(result);}
        return onePatient;
    }

    public Patient getPatientByPid(Long patientId){
        Patient onePatient = null;
        String sql = "Select * from patient where patient_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, patientId);
        while (result.next()){onePatient = mapResultToPatient(result);}
        return onePatient;
    }

    public List<Patient> getPatientsByDoctor(Long doctorId) {
        List<Patient> myPatients = new ArrayList<>();
        String sql = "SELECT * from patient join patient_doctor pd on patient.patient_id = pd.patient_id where doctor_id = ? order by lastname, firstname;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
        while (results.next()) {
            myPatients.add(mapResultToPatient(results));
        }
        return myPatients;
    }

    public void updatePatient(Patient patient) {
        String sqlUpdate = "UPDATE patient set firstname = ?, lastname = ?, dob = ?, phone = ?, email = ?, emergencyname = ?, emergencyphone = ?, insurance = ? where patient_id = ? or user_id = ?;";
        jdbcTemplate.update(sqlUpdate, patient.getFirstName(), patient.getLastName(), patient.getDateOfBirth(), patient.getPhoneNumber(), patient.getEmail(), patient.getEmergencyName(), patient.getEmergencyContact(), patient.getInsurance(), patient.getPatientId(), patient.getUserId());
    }

    public List<Patient> getPatientsByAppointments(Long doctorId){
        List<Long> bookedPatientIds = new ArrayList<>();
        String sql = "SELECT * from time_slot where doctor_id = ? and date >= current_date;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
        List<TimeSlot> ourAppointments = jdbcTemplate.query(sql, new JdbcTimeSlotDao.TimeSlotRowMapper(), doctorId);
        for (TimeSlot slot: ourAppointments) {
            if (!bookedPatientIds.contains(slot.getPatientId())){
                bookedPatientIds.add(slot.getPatientId());
            }
        }
        List<Patient> bookedPatients = new ArrayList<>();
        for (Long pid: bookedPatientIds) {
            bookedPatients.add(getPatientByPid(pid));
        }
        return bookedPatients;
    }


}
