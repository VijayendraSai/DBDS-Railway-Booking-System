package com.app.railway.controller;

import com.app.railway.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("station")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllStationNames() {
        List<String> stationNames = stationService.getAllStationNames();
        return ResponseEntity.ok(stationNames);
    }
}
