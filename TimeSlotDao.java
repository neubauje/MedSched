package com.techelevator.model.TimeSlot;

import com.techelevator.model.TimeSlot.TimeSlot;

import java.time.LocalDate;
import java.util.List;

public interface TimeSlotDao {
    public List<TimeSlot> findFreeSlots(LocalDate date, Long doctorId);
    public List<TimeSlot> findFreeSlots(Long doctorId);
    public List<TimeSlot> seeMyAgenda(Long doctorId);

}
