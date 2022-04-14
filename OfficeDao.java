package com.techelevator.model.Office;

import com.techelevator.model.Office.Office;

import java.util.List;

public interface OfficeDao {

    /**
     * Get an office from the database that has the given id.
     * If the id is not found, return null.
     *
     * @param officeName the office id to get from the datastore
     * @return an office
     */
    public Office getOffice(String officeName);

    public Office getOfficeById(Long id);
    public void updateOffice(Office updatedOffice);

    /**
     * Get all offices from the datastore.
     *
     * @return all offices as  objects in a List
     */
    public List<Office> getAllOffices();


}
