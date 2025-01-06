package com.app.railway.dao;

import com.app.railway.model.TransitLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransitLineRepository extends JpaRepository<TransitLine, String> {

    @Query("SELECT t.transitLineName FROM TransitLine t")
    List<String> findAllTransitLineNames();
}
