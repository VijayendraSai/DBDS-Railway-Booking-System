package com.app.railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.railway.dto.StationScheduleDTO;
import com.app.railway.dto.TrainScheduleDTO;
import com.app.railway.service.TrainScheduleService;

import java.util.List;

@RestController
@RequestMapping("schedules")
public class TrainScheduleController {

    @Autowired
    private TrainScheduleService trainScheduleService;

    @GetMapping("/origin-dest-station")
    public ResponseEntity<List<TrainScheduleDTO>> getTrainSchedules(@RequestParam("origin") String originStationName,
                                                                     @RequestParam("destination") String destinationStationName) {
        List<TrainScheduleDTO> trainSchedules = trainScheduleService.getTrainSchedulesByOriginAndDestination(originStationName, destinationStationName);

        if (trainSchedules.isEmpty()) {
            return ResponseEntity.noContent().build();  // Return a 204 No Content status if no schedules found
        }

        return ResponseEntity.ok(trainSchedules);  // Return 200 OK with the list
    }
    
    @GetMapping("/by-station")
    public List<StationScheduleDTO> getTrainSchedulesByStation(@RequestParam String stationName) {
        return trainScheduleService.getSchedulesByStationName(stationName);
    }
    
    @GetMapping("/origin-dest-station-sort-origin-time")
    public ResponseEntity<List<TrainScheduleDTO>> getTrainSchedulesSortByOriginTime(@RequestParam("origin") String originStationName,
                                                                     @RequestParam("destination") String destinationStationName) {
        List<TrainScheduleDTO> trainSchedules = trainScheduleService.getTrainSchedulesByOriginAndDestinationSortByOriginlTime(originStationName, destinationStationName);

        if (trainSchedules.isEmpty()) {
            return ResponseEntity.noContent().build();  // Return a 204 No Content status if no schedules found
        }

        return ResponseEntity.ok(trainSchedules);  // Return 200 OK with the list
    }
    
    @GetMapping("/origin-dest-station-sort-dest-time")
    public ResponseEntity<List<TrainScheduleDTO>> getTrainSchedulesSortByDestTime(@RequestParam("origin") String originStationName,
                                                                     @RequestParam("destination") String destinationStationName) {
        List<TrainScheduleDTO> trainSchedules = trainScheduleService.getTrainSchedulesByOriginAndDestinationSortByDestime(originStationName, destinationStationName);

        if (trainSchedules.isEmpty()) {
            return ResponseEntity.noContent().build();  // Return a 204 No Content status if no schedules found
        }

        return ResponseEntity.ok(trainSchedules);  // Return 200 OK with the list
    }
    
    @GetMapping("/origin-dest-station-sort-fare")
    public ResponseEntity<List<TrainScheduleDTO>> getTrainSchedulesSortByFare(@RequestParam("origin") String originStationName,
                                                                     @RequestParam("destination") String destinationStationName) {
        List<TrainScheduleDTO> trainSchedules = trainScheduleService.getTrainSchedulesByOriginAndDestinationSortByFare(originStationName, destinationStationName);

        if (trainSchedules.isEmpty()) {
            return ResponseEntity.noContent().build();  // Return a 204 No Content status if no schedules found
        }

        return ResponseEntity.ok(trainSchedules);  // Return 200 OK with the list
    }
}

