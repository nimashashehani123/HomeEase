package com.example.homeease.Service;

import com.example.homeease.Entity.Service;
import java.util.List;

public interface ServiceService {
    Service addService(Service service);
    List<Service> getAllServices();
    Service getServiceById(int id);
    Service updateService(int id, Service service);
    void deleteService(int id);
}