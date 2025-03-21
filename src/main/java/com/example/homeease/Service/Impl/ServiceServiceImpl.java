package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.Service;
import com.example.homeease.Repo.ServiceRepository;
import com.example.homeease.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Service addService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Service getServiceById(int id) throws ResourceNotFoundException {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + id));
    }

    @Override
    public Service updateService(int id, Service service) throws ResourceNotFoundException {
        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + id));

        // Update the existing service with new details
        existingService.setServiceName(service.getServiceName());
        existingService.setDescription(service.getDescription());
        existingService.setFixedPrice(service.getFixedPrice());
        existingService.setHourlyRate(service.getHourlyRate());
        existingService.setImage(service.getImage());

        return serviceRepository.save(existingService);
    }

    @Override
    public void deleteService(int id) throws ResourceNotFoundException {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + id));
        serviceRepository.delete(service);
    }
}