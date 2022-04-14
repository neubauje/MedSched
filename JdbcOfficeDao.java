package com.techelevator.model.Office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcOfficeDao implements OfficeDao {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOfficeDao(DataSource dataSource) {
       this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Office getOffice(String officeName) {
        Office office = null;
        String sqlGetOffice = "SELECT * FROM office WHERE office_name=?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetOffice, officeName);
        if (results.next()) {
            office = mapRowToOffice(results);
        }
        return office;
    }
    @Override
    public Office getOfficeById(Long id) {
        Office office = null;
        String sqlGetOffice = "SELECT * FROM office WHERE office_id=?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetOffice, id);
        if (results.next()) {
            office = mapRowToOffice(results);
        }
        return office;
    }
    @Override
    public List<Office> getAllOffices() {
        List<Office> offices = new ArrayList<>();
        String sqlGetAllOffices = "SELECT * FROM office;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllOffices);
        while (results.next()) {
            Office officeResult = mapRowToOffice(results);
            offices.add(officeResult);
        }
        return offices;
    }

    public List<Office> getAllRealOffices() {
        List<Office> offices = new ArrayList<>();
        String sqlGetAllOffices = "SELECT * FROM office where office_id > 0 order by cost_per_hour;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllOffices);
        while (results.next()) {
            Office officeResult = mapRowToOffice(results);
            offices.add(officeResult);
        }
        return offices;
    }

    @Override
    public void updateOffice(Office updatedOffice) {
        String sql = "UPDATE office SET address = ?, opening_hours = ?, closing_hours = ?, " +
                "phone_number = ?, cost_per_hour = ? WHERE office_id = ?;";
        jdbcTemplate.update(sql, updatedOffice.getAddress(), updatedOffice.getOpeningHours(),
                updatedOffice.getClosingHours(), updatedOffice.getPhoneNumber(),
                updatedOffice.getCostPerHour(), updatedOffice.getOfficeId());
    }

    public List<Office> getOfficesByPatientId(Long patientId){
        List<Office> myOffices = new ArrayList<>();
        String sql = "SELECT office.office_id, office_name, address, days_open, opening_hours, closing_hours, phone_number, cost_per_hour from office join doctor d on office.office_id = d.office_id join patient_doctor pd on d.doctor_id = pd.doctor_id join patient p on p.patient_id = pd.patient_id where p.patient_id = ? group by office.office_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
        while (results.next()){
            myOffices.add(mapRowToOffice(results));
        }
        return myOffices;
    }

    private Office mapRowToOffice(SqlRowSet results) {
        Office office = new Office();
        office.setOfficeId(results.getLong("office_id"));
        office.setOfficeName(results.getString("office_name"));
        office.setAddress(results.getString("address"));
        office.setDaysOpen(results.getString("days_open"));
        office.setOpeningHours(results.getTime("opening_hours").toLocalTime());
        office.setClosingHours(results.getTime("closing_hours").toLocalTime());
        office.setPhoneNumber(results.getString("phone_number"));
        office.setCostPerHour(results.getDouble("cost_per_hour"));
        return office;
    }

    public Office createNewOffice(Office office) {
        long officeId = jdbcTemplate.queryForObject("INSERT into office(office_name, address, days_open, opening_hours, closing_hours, phone_number, cost_per_hour) values (?, ?, ?, ?, ?, ?, ?) returning office_id", Long.class, office.getOfficeName(), office.getAddress(), office.getDaysOpen(), office.getOpeningHours(), office.getClosingHours(), office.getPhoneNumber(), office.getCostPerHour());
        office.setOfficeId(officeId);
        return office;
    }

}
