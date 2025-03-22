package com.example.homeease.Controller;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ServiceDTO;
import com.example.homeease.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addService(@RequestBody ServiceDTO serviceDTO) {
        ResponseDTO response = serviceService.addService(serviceDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllServices() {
        ResponseDTO response = serviceService.getAllServices();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ResponseDTO> getServiceById(@PathVariable int serviceId) {
        ResponseDTO response = serviceService.getServiceById(serviceId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ResponseDTO> updateService(@PathVariable int serviceId, @RequestBody ServiceDTO serviceDTO) {
        ResponseDTO response = serviceService.updateService(serviceId, serviceDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<ResponseDTO> deleteService(@PathVariable int serviceId) {
        ResponseDTO response = serviceService.deleteService(serviceId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}