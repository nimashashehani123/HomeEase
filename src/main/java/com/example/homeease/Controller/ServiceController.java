package com.example.homeease.Controller;

import com.example.homeease.Dto.ServiceDTO;
import com.example.homeease.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping("/add")
    public ServiceDTO addService(@RequestBody ServiceDTO serviceDTO) {
        return serviceService.addService(serviceDTO);
    }

    @PutMapping("/update")
    public ServiceDTO updateService(@RequestBody ServiceDTO serviceDTO) {
        return serviceService.updateService(serviceDTO);
    }

    @DeleteMapping("/delete/{serviceId}")
    public void deleteService(@PathVariable int serviceId) {
        serviceService.deleteService(serviceId);
    }

    @GetMapping("/{serviceId}")
    public ServiceDTO getServiceById(@PathVariable int serviceId) {
        return serviceService.getServiceById(serviceId);
    }

    @GetMapping("/all")
    public List<ServiceDTO> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/by-category/{categoryId}")
    public List<ServiceDTO> getServicesByCategory(@PathVariable int categoryId) {
        return serviceService.getServicesByCategory(categoryId);
    }

    @GetMapping("/by-provider/{providerId}")
    public List<ServiceDTO> getServicesByProvider(@PathVariable int providerId) {
        return serviceService.getServicesByProvider(providerId);
    }
}