package edu.du.sb1030.service;

import edu.du.sb1030.entity.Shipment;
import edu.du.sb1030.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    public Shipment saveShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Optional<Shipment> getShipmentById(Long id) {
        return shipmentRepository.findById(id);
    }

    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }

    public void updateShipmentStatus(Long id, String status) {
        Optional<Shipment> shipmentOptional = shipmentRepository.findById(id);
        if (shipmentOptional.isPresent()) {
            Shipment shipment = shipmentOptional.get();
            shipment.setStatus(status);
            shipmentRepository.save(shipment);
        }
    }
}
