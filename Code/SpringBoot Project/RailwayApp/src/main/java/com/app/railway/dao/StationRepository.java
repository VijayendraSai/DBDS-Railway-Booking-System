package com.app.railway.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.railway.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

    @Query("SELECT st.name FROM Schedule sc " +
           "JOIN Station st ON st.stationId = sc.station.stationId " +
           "WHERE sc.train.trainId = :trainId " +
           "ORDER BY sc.stopOrder")
    List<String> findStationNamesByTrainId(@Param("trainId") int trainId);
    
    @Query("SELECT s.name FROM Station s")
    List<String> findAllStationNames();
}

