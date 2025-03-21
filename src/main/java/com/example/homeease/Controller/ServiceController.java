package com.example.homeease.Controller;

import com.example.homeease.Entity.Service;
import com.example.homeease.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public Service addService(@RequestBody Service service) {
        return serviceService.addService(service);
    }

    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable int id) {
        return serviceService.getServiceById(id);
    }

    @PutMapping("/{id}")
    public Service updateService(@PathVariable int id, @RequestBody Service service) {
        return serviceService.updateService(id, service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable int id) {
        serviceService.deleteService(id);
    }
}