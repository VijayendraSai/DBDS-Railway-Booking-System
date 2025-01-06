package com.app.railway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.railway.dao.StationRepository;
import com.app.railway.dao.TrainScheduleRepository;
import com.app.railway.dto.StationScheduleDTO;
import com.app.railway.dto.TrainScheduleDTO;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainScheduleService {

    @Autowired
    private TrainScheduleRepository trainScheduleRepository;
    
    @Autowired
    private StationRepository stationRepository;

    public List<TrainScheduleDTO> getTrainSchedulesByOriginAndDestination(String originStationName, String destinationStationName) {
        List<Object[]> results = trainScheduleRepository.findTrainSchedulesByOriginAndDestination(originStationName, destinationStationName);

     // Map the results to TrainScheduleDTO
        return results.stream()
                .map(result -> {
                    String transitLineName = (String) result[0];
                    String trainName = (String) result[1];
                    int trainId = (Integer) result[2];
                    LocalTime originArrivalTime = (LocalTime) result[3];
                    LocalTime destinationArrivalTime = (LocalTime) result[4];
                    
                    double calculatedFare = trainScheduleRepository.findFareByTrainIdAndStations(
                            trainId, originStationName, destinationStationName);

                    // Fetch station stops for this train
                    List<String> stops = getStationStopsForTrain(trainId);

                    return new TrainScheduleDTO(transitLineName, trainName, trainId, originArrivalTime, destinationArrivalTime, calculatedFare, stops);
                })
                .collect(Collectors.toList());
    }
    
    // Helper method to fetch station stops for a given trainId
    private List<String> getStationStopsForTrain(int trainId) {
        return stationRepository.findStationNamesByTrainId(trainId);
    }
    
    public List<StationScheduleDTO> getSchedulesByStationName(String stationName) {
        // Fetch the raw data as Object[]
        List<Object[]> results = trainScheduleRepository.findStationSchedulesByStationName(stationName);

        // Convert Object[] to StationScheduleDTO
        return results.stream().map(result -> {
            // Extract fields from the result array
            String transitLineName = (String) result[0];  // transitLineName
            Integer trainId = (Integer) result[1];  // trainId
            String trainName = (String) result[2];  // trainName
            String stationNameResult = (String) result[3];  // stationName
            Time arrivalTime = (Time) result[4];  // arrivalTime (as Time)
            Time departureTime = (Time) result[5];  // departureTime (as Time)

            // Convert Time to LocalTime
            LocalTime arrivalLocalTime = (arrivalTime != null) ? arrivalTime.toLocalTime() : null;
            LocalTime departureLocalTime = (departureTime != null) ? departureTime.toLocalTime() : null;

            // Return a new StationScheduleDTO
            return new StationScheduleDTO(
                    transitLineName, 
                    trainId, 
                    trainName, 
                    stationNameResult, 
                    arrivalLocalTime, 
                    departureLocalTime
            );
        }).collect(Collectors.toList());
    }
    
    public List<TrainScheduleDTO> getTrainSchedulesByOriginAndDestinationSortByOriginlTime(String originStationName, String destinationStationName) {
        List<Object[]> results = trainScheduleRepository.findTrainSchedulesByOriginAndDestinationSortByOriginTime(originStationName, destinationStationName);

        // Map the results to TrainScheduleDTO
        return results.stream()
                .map(result -> {
                    String transitLineName = (String) result[0];
                    String trainName = (String) result[1];
                    int trainId = (Integer) result[2];
                    LocalTime originArrivalTime = (LocalTime) result[3];
                    LocalTime destinationArrivalTime = (LocalTime) result[4];
                    
                    double calculatedFare = trainScheduleRepository.findFareByTrainIdAndStations(
                            trainId, originStationName, destinationStationName);

                    // Fetch station stops for this train
                    List<String> stops = getStationStopsForTrain(trainId);

                    return new TrainScheduleDTO(transitLineName, trainName, trainId, originArrivalTime, destinationArrivalTime, calculatedFare, stops);
                })
                .collect(Collectors.toList());
    }

    
    public List<TrainScheduleDTO> getTrainSchedulesByOriginAndDestinationSortByDestime(String originStationName, String destinationStationName) {
        List<Object[]> results = trainScheduleRepository.findTrainSchedulesByOriginAndDestinationSortByDestTime(originStationName, destinationStationName);

        // Map the results to TrainScheduleDTO
        return results.stream()
                .map(result -> {
                    String transitLineName = (String) result[0];
                    String trainName = (String) result[1];
                    int trainId = (Integer) result[2];
                    LocalTime originArrivalTime = (LocalTime) result[3];
                    LocalTime destinationArrivalTime = (LocalTime) result[4];
                    
                    double calculatedFare = trainScheduleRepository.findFareByTrainIdAndStations(
                            trainId, originStationName, destinationStationName);

                    // Fetch station stops for this train
                    List<String> stops = getStationStopsForTrain(trainId);

                    return new TrainScheduleDTO(transitLineName, trainName, trainId, originArrivalTime, destinationArrivalTime, calculatedFare, stops);
                })
                .collect(Collectors.toList());
    }
    
    public List<TrainScheduleDTO> getTrainSchedulesByOriginAndDestinationSortByFare(String originStationName, String destinationStationName) {
        List<Object[]> results = trainScheduleRepository.findTrainSchedulesByOriginAndDestination(originStationName, destinationStationName);

        // Map the results to TrainScheduleDTO
        return results.stream()
                .map(result -> {
                    String transitLineName = (String) result[0];
                    String trainName = (String) result[1];
                    int trainId = (Integer) result[2];
                    LocalTime originArrivalTime = (LocalTime) result[3];
                    LocalTime destinationArrivalTime = (LocalTime) result[4];
                    
                    double calculatedFare = trainScheduleRepository.findFareByTrainIdAndStations(
                            trainId, originStationName, destinationStationName);

                    // Fetch station stops for this train
                    List<String> stops = getStationStopsForTrain(trainId);

                    return new TrainScheduleDTO(transitLineName, trainName, trainId, originArrivalTime, destinationArrivalTime, calculatedFare, stops);
                })
                .sorted(Comparator.comparingDouble(TrainScheduleDTO::getCalculatedFare)) // Sort by fare
                .collect(Collectors.toList());
    }
    
}

