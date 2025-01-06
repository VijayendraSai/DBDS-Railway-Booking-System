package com.app.railway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.railway.dao.TransitLineRepository;

@Service
public class TransitLineService {
	
	@Autowired
    private TransitLineRepository transitLineRepository;
	
	public List<String> getAllTransitLineNames() {
        return transitLineRepository.findAllTransitLineNames();
    }

}
