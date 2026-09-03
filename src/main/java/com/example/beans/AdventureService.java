package com.example.beans;

import com.example.repos.*;
import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service: estereotipo de Spring para la capa de lógica de negocio. Igual que @Repository,
// hace que el component scan registre esta clase como bean, de modo que los Servlets puedan
// inyectarla con @Autowired en vez de instanciarla con "new".
@Service
public class AdventureService {
    // @Autowired de campo: le pide a Spring que inyecte automáticamente el bean de
    // tipo
    // ExpeditionsRepository (el único registrado con @Repository) en este campo,
    // resolviendo la
    // dependencia sin necesidad de un constructor ni de código de configuración
    // manual.
    @Autowired
    private ExpeditionsRepository expeditionsRepository;

    // Misma idea que arriba, pero para el repositorio de registros de entrega.
    @Autowired
    private SightingsRepository sightingsRepository;

    private boolean existExpeditionsValidation(List<Expeditions> expeditions, Expeditions expeditionToValidate) {
        for (Expeditions e : expeditions) {
            if (expeditionToValidate.getCode().equalsIgnoreCase(e.getCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean registerExpeditions(Expeditions expedition) {
        if (expedition != null && expedition.getName() != null && expedition.getCode().trim().length() >= 5
                && 20 >= expedition.getCode().trim().length()) {
            if (!existExpeditionsValidation(expeditionsRepository.findAll(), expedition)) {
                expeditionsRepository.addExpeditions(expedition);
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean existSightingsValidation(List<Sightings> sightings, Sightings sightingsToValidate) {
        for (Sightings dr : sightings) {
            if (sightingsToValidate.getSightingCode().equalsIgnoreCase(dr.getSightingCode())) {
                return true;
            }
        }
        return false;
    }

    private boolean existExpeditionsValidationById(List<Expeditions> expeditions, Integer expeditionToValidate) {
        for (Expeditions e : expeditions) {
            if (expeditionToValidate.equals(e.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean registerSightings(Sightings sightings) {
        if (sightings != null && existExpeditionsValidationById(getAllExpeditionss(), sightings.getExpeditionId())) {
            if (!existSightingsValidation(sightingsRepository.findAll(), sightings)) {
                sightingsRepository.addSightings(sightings);
                return true;
            }
            return false;
        }
        return false;
    }

    // Método de lectura simple (no forma parte de las 7 reglas de negocio): delega
    // directamente
    // en el repositorio. Los Servlets lo usan para listar vehículos sin acceder al
    // repository
    // directamente (siempre pasan por el Service).
    public List<Expeditions> getAllExpeditionss() {
        return expeditionsRepository.findAll();
    }

    // Análogo al anterior, pero para los registros de entrega.
    public List<Sightings> getAllSightingss() {
        return sightingsRepository.findAll();
    }
}
