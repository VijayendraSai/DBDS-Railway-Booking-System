package com.app.railway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.railway.service.TransitLineService;

@RestController
@RequestMapping("transitLine")
public class TransitLineController {
	
	@Autowired
    private TransitLineService transitLineService;
	
	@GetMapping("/names")
    public ResponseEntity<List<String>> getAllTransitLineNames() {
        List<String> transitLineNames = transitLineService.getAllTransitLineNames();
        return ResponseEntity.ok(transitLineNames);
    }

}
