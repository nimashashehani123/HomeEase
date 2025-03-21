package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.ServiceDTO;
import com.example.homeease.Entity.Service; // Import the Service entity
import com.example.homeease.Repo.ServiceRepository;
import com.example.homeease.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public ServiceDTO addService(ServiceDTO serviceDTO) {
        Service service = new Service(); // Use the simple class name
        service.setServiceName(serviceDTO.getServiceName());
        service.setDescription(serviceDTO.getDescription());
        service.setPrice(serviceDTO.getPrice());
        service.setImage(serviceDTO.getImage());
        // Set category and service provider (fetch from repository if needed)
        Service savedService = serviceRepository.save(service);
        return convertToServiceDTO(savedService);
    }

    @Override
    public ServiceDTO updateService(ServiceDTO serviceDTO) {
        Service service = serviceRepository.findById(serviceDTO.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        service.setServiceName(serviceDTO.getServiceName());
        service.setDescription(serviceDTO.getDescription());
        service.setPrice(serviceDTO.getPrice());
        service.setImage(serviceDTO.getImage());
        Service updatedService = serviceRepository.save(service);
        return convertToServiceDTO(updatedService);
    }

    @Override
    public void deleteService(int serviceId) {
        serviceRepository.deleteById(serviceId);
    }

    @Override
    public ServiceDTO getServiceById(int serviceId) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        return convertToServiceDTO(service);
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(this::convertToServiceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO> getServicesByCategory(int categoryId) {
        return serviceRepository.findByCategory_CategoryId(categoryId).stream()
                .map(this::convertToServiceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO> getServicesByProvider(int providerId) {
        return serviceRepository.findByServiceProvider_UserId(providerId).stream()
                .map(this::convertToServiceDTO)
                .collect(Collectors.toList());
    }

    private ServiceDTO convertToServiceDTO(Service service) {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId(service.getServiceId());
        serviceDTO.setServiceName(service.getServiceName());
        serviceDTO.setDescription(service.getDescription());
        serviceDTO.setPrice(service.getPrice());
        serviceDTO.setImage(service.getImage());
        serviceDTO.setCategoryId(service.getCategory().getCategoryId());
        serviceDTO.setServiceProviderId(service.getServiceProvider().getUserId());
        return serviceDTO;
    }

}