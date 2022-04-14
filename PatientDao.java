package com.techelevator.model.Patient;

import com.techelevator.model.Patient.Patient;

import java.util.List;

public interface PatientDao {



    /**
     * Get all of the Patients from the database.
     * @return a List of Patient objects
     */
    public List<Patient> getAllPatients();

}
