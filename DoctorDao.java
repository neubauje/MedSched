package com.techelevator.model.Doctor;

import com.techelevator.model.Doctor.Doctor;

import java.util.List;

public interface DoctorDao{

    /**
     * Save a new user to the database. The password that is passed in will be
     * salted and hashed before being saved. The original password is never
     * stored in the system. We will never have any idea what it is!
     *
     */


    /**
     * Look for a user with the given username and password. Since we don't
     * know the password, we will have to get the user's salt from the database,
     * hash the password, and compare that against the hash in the database.
     *
     * @param Username the user name of the user we are checking
     * @param Password the password of the user we are checking
     * @return true if the user is found and their password matches
     */

    /**
     * Get all of the Doctors from the database.
     * @return a List of Doctor objects
     */
    public List<Doctor> getAllDoctors();

}
