package PulseCheck.incident;
import PulseCheck.incident.IncidentNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentRepository incidentRepository;

    public IncidentController(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }
    @GetMapping("/{id}")
public Incident getIncidentById(
        @org.springframework.web.bind.annotation.PathVariable Long id) {

    return incidentRepository.findById(id)
            .orElseThrow(() -> new IncidentNotFoundException("Incident not found"));
}

    @PostMapping
    public Incident createIncident(@Valid @RequestBody Incident incident) {
        return incidentRepository.save(incident);
    }
    @org.springframework.web.bind.annotation.PutMapping("/{id}")
public Incident updateIncident(
        @org.springframework.web.bind.annotation.PathVariable Long id,
        @Valid @RequestBody Incident updatedIncident) {

    Incident incident = incidentRepository.findById(id)
            .orElseThrow(() -> new IncidentNotFoundException("Incident not found"));

    incident.setTitle(updatedIncident.getTitle());
    incident.setDescription(updatedIncident.getDescription());
    incident.setStatus(updatedIncident.getStatus());

    return incidentRepository.save(incident);
}
@org.springframework.web.bind.annotation.DeleteMapping("/{id}")
public void deleteIncident(
        @org.springframework.web.bind.annotation.PathVariable Long id) {

    Incident incident = incidentRepository.findById(id)
            .orElseThrow(() -> new IncidentNotFoundException("Incident not found"));

    incidentRepository.delete(incident);
}
}