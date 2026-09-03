package com.example.model;

public class Sightings {

    private Integer id;
    private String sightingCode;
    private String name;
    private String description;
    private String scientificName;
    private String sightedAt;
    private String location;
    private Integer quantity;
    private Integer confidenceLevel;
    private Integer expeditionId;

    public Sightings() {

    }

    public Sightings(Integer id, String sightingCode, String name, String description, String scientificName,
            String sightedAt, String location, Integer quantity, Integer confidenceLevel, Integer expeditionId) {
        this.id = id;
        this.sightingCode = sightingCode;
        this.name = name;
        this.description = description;
        this.scientificName = scientificName;
        this.sightedAt = sightedAt;
        this.location = location;
        this.quantity = quantity;
        this.confidenceLevel = confidenceLevel;
        this.expeditionId = expeditionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSightingCode() {
        return sightingCode;
    }

    public void setSightingCode(String sightingCode) {
        this.sightingCode = sightingCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getSightedAt() {
        return sightedAt;
    }

    public void setSightedAt(String sightedAt) {
        this.sightedAt = sightedAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getConfidenceLevel() {
        return confidenceLevel;
    }

    public void setConfidenceLevel(Integer confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }

    public Integer getExpeditionId() {
        return expeditionId;
    }

    public void setExpeditionId(Integer expeditionId) {
        this.expeditionId = expeditionId;
    }

}
