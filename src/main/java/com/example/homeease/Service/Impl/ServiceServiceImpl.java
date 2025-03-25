package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ServiceDTO;
import com.example.homeease.Entity.Category;
import com.example.homeease.Entity.Service;
import com.example.homeease.Entity.User;
import com.example.homeease.Repo.CategoryRepository;
import com.example.homeease.Repo.ServiceRepository;
import com.example.homeease.Repo.UserRepository;
import com.example.homeease.Service.ServiceService;
import com.example.homeease.Utill.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int addService(ServiceDTO serviceDTO) {
        try {
            // Validate required fields
            if (serviceDTO.getServiceName() == null || serviceDTO.getServiceName().trim().isEmpty()) {
                return VarList.Bad_Request; // 400 - Missing name
            }

            // Fetch related entities
            Category category = categoryRepository.findById(serviceDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            User serviceProvider = userRepository.findById(serviceDTO.getServiceProviderId())
                    .orElseThrow(() -> new RuntimeException("Service provider not found"));

            // Create and populate service entity
            Service service = new Service();
            service.setServiceName(serviceDTO.getServiceName());
            service.setDescription(serviceDTO.getDescription()); // Fixed: was using getName() twice
            service.setFixedPrice(serviceDTO.getFixedPrice());
            service.setHourlyRate(serviceDTO.getHourlyRate());
            service.setImage(serviceDTO.getImage());

            // Set relationships with actual entities
            service.setCategory(category);
            service.setServiceProvider(serviceProvider);

            // Save and verify
            Service savedService = serviceRepository.save(service);

            if (savedService.getServiceId() != 0) { // Changed to null check for Long
                return VarList.Created; // 201 - Success
            } else {
                return VarList.Internal_Server_Error; // 500 - Save failed
            }

        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return VarList.Internal_Server_Error; // 500
        }
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