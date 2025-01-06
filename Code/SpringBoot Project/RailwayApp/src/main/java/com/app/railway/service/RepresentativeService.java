package com.app.railway.service;

import com.app.railway.dao.TrainScheduleRepository;
import com.app.railway.dto.ScheduleUpdateDTO;
import com.app.railway.model.Schedule;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepresentativeService {

    @Autowired
    private TrainScheduleRepository scheduleRepository;

    // Method to edit a train schedule
    public Schedule editTrainSchedule(int stationId, int trainId, ScheduleUpdateDTO scheduleUpdateDTO) {
        // Find the existing schedule by stationId and trainId
        Optional<Schedule> existingSchedule = scheduleRepository.findByStationIdAndTrainId(stationId, trainId);
        if (existingSchedule.isPresent()) {
            Schedule schedule = existingSchedule.get();

            // Update the fields with the provided DTO values, only if they're not null
            if (scheduleUpdateDTO.getArrivalTime() != null) {
                schedule.setArrivalTime(scheduleUpdateDTO.getArrivalTime());
            }
            if (scheduleUpdateDTO.getDepartureTime() != null) {
                schedule.setDepartureTime(scheduleUpdateDTO.getDepartureTime());
            }
            if (scheduleUpdateDTO.getStopOrder() != null) {
                schedule.setStopOrder(scheduleUpdateDTO.getStopOrder());
            }

            // Save and return the updated schedule
            return scheduleRepository.save(schedule);
        }
        return null;  // Return null if no schedule is found with the given stationId and trainId
    }

    // Method to delete a train schedule
    @Transactional
    public boolean deleteTrainSchedule(int stationId, int trainId) {
        // Try to find the schedule first
        Optional<Schedule> scheduleOptional = scheduleRepository.findByStationIdAndTrainId(stationId, trainId);
        
        if (scheduleOptional.isPresent()) {
            // If schedule exists, delete it
            scheduleRepository.delete(scheduleOptional.get());
            return true;
        } else {
            // If no schedule found, return false
            return false;
        }
    }
}
