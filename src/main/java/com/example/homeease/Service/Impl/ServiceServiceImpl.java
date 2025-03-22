package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ServiceDTO;
import com.example.homeease.Entity.Service;
import com.example.homeease.Repo.ServiceRepository;
import com.example.homeease.Service.ServiceService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDTO addService(ServiceDTO serviceDTO) {
        if (serviceRepository.existsById(serviceDTO.getServiceId())) {
            return new ResponseDTO(400, "Service already exists with id: " + serviceDTO.getServiceId(), null);
        }
        Service service = modelMapper.map(serviceDTO, Service.class);
        serviceRepository.save(service);
        return new ResponseDTO(200, "Service added successfully", serviceDTO);
    }

    @Override
    public ResponseDTO getAllServices() {
        List<ServiceDTO> serviceList = modelMapper.map(serviceRepository.findAll(),
                new TypeToken<List<ServiceDTO>>() {}.getType());
        return new ResponseDTO(200, "Services retrieved successfully", serviceList);
    }

    @Override
    public ResponseDTO getServiceById(int serviceId) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + serviceId));
        ServiceDTO serviceDTO = modelMapper.map(service, ServiceDTO.class);
        return new ResponseDTO(200, "Service retrieved successfully", serviceDTO);
    }

    @Override
    public ResponseDTO updateService(int serviceId, ServiceDTO serviceDTO) {
        if (!serviceRepository.existsById(serviceId)) {
            return new ResponseDTO(404, "Service not found with id: " + serviceId, null);
        }
        Service service = modelMapper.map(serviceDTO, Service.class);
        service.setServiceId(serviceId); // Ensure the ID is preserved
        serviceRepository.save(service);
        return new ResponseDTO(200, "Service updated successfully", serviceDTO);
    }

    @Override
    public ResponseDTO deleteService(int serviceId) {
        if (!serviceRepository.existsById(serviceId)) {
            return new ResponseDTO(404, "Service not found with id: " + serviceId, null);
        }
        serviceRepository.deleteById(serviceId);
        return new ResponseDTO(200, "Service deleted successfully", null);
    }
}