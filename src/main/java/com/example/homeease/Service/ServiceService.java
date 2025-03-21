package com.example.homeease.Service;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.Service;

import java.util.List;

public interface ServiceService {
    Service addService(Service service);
    List<Service> getAllServices();
    Service getServiceById(int id) throws ResourceNotFoundException;
    Service updateService(int id, Service service) throws ResourceNotFoundException;
    void deleteService(int id) throws ResourceNotFoundException;
}