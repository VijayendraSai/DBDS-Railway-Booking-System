package com.app.railway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainId; // Primary key

    private String name;

 // Corrected @JoinColumn to reference the correct field in TransitLine
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transit_line_name", referencedColumnName = "transitLineName", foreignKey = @ForeignKey(name = "fk_transit_line"))
    private TransitLine transitLine; // Foreign key to TransitLine

    // Default constructor
    public Train() {}

    // Constructor with all fields
    public Train(String name, TransitLine transitLine) {
        this.name = name;
        this.transitLine = transitLine;
    }

    public Train(int trainId) {
		super();
		this.trainId = trainId;
	}

	// Getters and Setters
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransitLine getTransitLine() {
        return transitLine;
    }

    public void setTransitLine(TransitLine transitLine) {
        this.transitLine = transitLine;
    }

    // Override toString(), equals(), and hashCode() if needed (optional)
}
