package com.example.repos;

import com.example.model.Expeditions;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpeditionsRepository {
    private List<Expeditions> expeditions;

    public ExpeditionsRepository() {
        this.expeditions = new ArrayList<>();
        this.expeditions.add(new Expeditions(1, "Paseo del rio", "1A12345", "Amazonía", "parque", "Pablo Martinez", "2026-09-06", "2026-09-07","Active"));
        this.expeditions.add(new Expeditions(2, "Paseo del caño", "2A13456", "Sierra Nevada", "nevado", "Samuel Granda", "2026-09-06", "2026-09-07","Completed"));
        this.expeditions.add(new Expeditions(3, "Paseo del calocho", "3A14567", "Costa Pacífica", "playa", "Daniel Vareta", "2026-09-06", "2026-09-07","Cancelled"));
    }

    public void addExpeditions (Expeditions expedition){
        expeditions.add(expedition);      
    }

    public List<Expeditions> findAll(){
        return expeditions;
    }

    public Expeditions findById(Integer id){
        for (Expeditions expedition : expeditions) {
            if (expedition.getId().equals(id)) {
                return expedition;
            }
        }
        return null;
    }
}
