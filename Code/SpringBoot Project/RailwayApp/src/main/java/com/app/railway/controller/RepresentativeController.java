package com.app.railway.controller;

import com.app.railway.dto.ScheduleUpdateDTO;
import com.app.railway.model.Schedule;
import com.app.railway.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/representative")
public class RepresentativeController {

    @Autowired
    private RepresentativeService representativeService;

    // Edit train schedule (PUT method)
    @PutMapping("/edit-schedule/{stationId}/{trainId}")
    public ResponseEntity<Schedule> editTrainSchedule(
            @PathVariable int stationId,
            @PathVariable int trainId,
            @RequestBody ScheduleUpdateDTO scheduleUpdateDTO) {

        // Call service method to update the schedule
        Schedule updatedSchedule = representativeService.editTrainSchedule(stationId, trainId, scheduleUpdateDTO);

        if (updatedSchedule != null) {
            return ResponseEntity.ok(updatedSchedule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete train schedule (DELETE method)
    @DeleteMapping("/delete-schedule/{stationId}/{trainId}")
    public ResponseEntity<String> deleteTrainSchedule(
            @PathVariable int stationId,
            @PathVariable int trainId) {

        boolean isDeleted = representativeService.deleteTrainSchedule(stationId, trainId);
        
        if (isDeleted) {
            return ResponseEntity.ok("Schedule deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Schedule not found.");
        }
    }
}
