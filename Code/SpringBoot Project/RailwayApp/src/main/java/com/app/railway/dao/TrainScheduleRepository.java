package com.app.railway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.railway.dto.StationScheduleDTO;
import com.app.railway.model.Schedule;
import com.app.railway.model.ScheduleId;
import com.app.railway.model.Train;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainScheduleRepository extends JpaRepository<Schedule, ScheduleId> {

	// search for train schedules by origin, destination, date of travel (5 points)
	// browse the resulting schedules (5 points)
	// see all the stops a train will make, fare etc.
    @Query("SELECT tl.transitLineName AS transitLineName, tr.name AS trainName, tr.trainId AS trainId, sc.arrivalTime AS originArrivalTime, sc2.arrivalTime AS destinationArrivalTime " +
           "FROM TransitLine tl " +
           "JOIN Train tr ON tr.transitLine.transitLineName = tl.transitLineName " +
           "JOIN Schedule sc ON sc.train.trainId = tr.trainId " +
           "JOIN Station st ON st.stationId = sc.station.stationId " +
           "JOIN Schedule sc2 ON sc.train.trainId = sc2.train.trainId " +
           "JOIN Station st2 ON st2.stationId = sc2.station.stationId " +
           "WHERE st.name = :originStationName " +
           "AND st2.name = :destinationStationName " +
           "AND sc.stopOrder < sc2.stopOrder " +
           "ORDER BY tr.trainId")
    List<Object[]> findTrainSchedulesByOriginAndDestination(@Param("originStationName") String originStationName, 
            @Param("destinationStationName") String destinationStationName);
    
    @Query("SELECT (tl.baseFare / tl.totalStops) * (sc2.stopOrder - sc1.stopOrder + 1) " +
            "FROM Schedule sc1 " +
            "JOIN sc1.station st1 " +
            "JOIN Schedule sc2 ON sc1.train.trainId = sc2.train.trainId " +
            "JOIN sc2.station st2 " +
            "JOIN Train tr ON sc1.train.trainId = tr.trainId " +
            "JOIN TransitLine tl ON tr.transitLine.transitLineName = tl.transitLineName " +
            "WHERE sc1.train.trainId = :trainId " +
            "AND st1.name = :originStationName " +
            "AND st2.name = :destinationStationName " +
            "AND sc1.stopOrder < sc2.stopOrder")
     Double findFareByTrainIdAndStations(@Param("trainId") int trainId,
                                         @Param("originStationName") String originStationName,
                                         @Param("destinationStationName") String destinationStationName);
    
    Optional<Schedule> findByStationIdAndTrainId(int stationId, int trainId);
    
    void deleteByStationIdAndTrainId(int stationId, int trainId);
    
    @Query(value = "SELECT t.transit_line_name AS transitLineName, " +
            "t.train_id AS trainId, " +
            "t.name AS trainName, " +
            "s.name AS stationName, " +
            "sch.arrival_time AS arrivalTime, " +
            "sch.departure_time AS departureTime " +
            "FROM schedule sch " +
            "JOIN train t ON sch.train_id = t.train_id " +
            "JOIN station s ON sch.station_id = s.station_id " +
            "WHERE s.name = :stationName", 
            nativeQuery = true)
    List<Object[]> findStationSchedulesByStationName(@Param("stationName") String stationName);
    
    @Query("SELECT tl.transitLineName AS transitLineName, tr.name AS trainName, tr.trainId AS trainId, sc.arrivalTime AS originArrivalTime, sc2.arrivalTime AS destinationArrivalTime " +
            "FROM TransitLine tl " +
            "JOIN Train tr ON tr.transitLine.transitLineName = tl.transitLineName " +
            "JOIN Schedule sc ON sc.train.trainId = tr.trainId " +
            "JOIN Station st ON st.stationId = sc.station.stationId " +
            "JOIN Schedule sc2 ON sc.train.trainId = sc2.train.trainId " +
            "JOIN Station st2 ON st2.stationId = sc2.station.stationId " +
            "WHERE st.name = :originStationName " +
            "AND st2.name = :destinationStationName " +
            "AND sc.stopOrder < sc2.stopOrder " +
            "ORDER BY sc.arrivalTime ASC")
     List<Object[]> findTrainSchedulesByOriginAndDestinationSortByOriginTime(@Param("originStationName") String originStationName, 
             @Param("destinationStationName") String destinationStationName);
     
     @Query("SELECT tl.transitLineName AS transitLineName, tr.name AS trainName, tr.trainId AS trainId, sc.arrivalTime AS originArrivalTime, sc2.arrivalTime AS destinationArrivalTime " +
             "FROM TransitLine tl " +
             "JOIN Train tr ON tr.transitLine.transitLineName = tl.transitLineName " +
             "JOIN Schedule sc ON sc.train.trainId = tr.trainId " +
             "JOIN Station st ON st.stationId = sc.station.stationId " +
             "JOIN Schedule sc2 ON sc.train.trainId = sc2.train.trainId " +
             "JOIN Station st2 ON st2.stationId = sc2.station.stationId " +
             "WHERE st.name = :originStationName " +
             "AND st2.name = :destinationStationName " +
             "AND sc.stopOrder < sc2.stopOrder " +
             "ORDER BY sc2.arrivalTime ASC")
      List<Object[]> findTrainSchedulesByOriginAndDestinationSortByDestTime(@Param("originStationName") String originStationName, 
              @Param("destinationStationName") String destinationStationName);
}

