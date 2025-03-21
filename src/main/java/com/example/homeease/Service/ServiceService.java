package com.example.homeease.Service;
import com.example.homeease.Dto.ServiceDTO;

import java.util.List;

public interface ServiceService {
    ServiceDTO addService(ServiceDTO serviceDTO);
    ServiceDTO updateService(ServiceDTO serviceDTO);
    void deleteService(int serviceId);
    ServiceDTO getServiceById(int serviceId);
    List<ServiceDTO> getAllServices();
    List<ServiceDTO> getServicesByCategory(int categoryId);
    List<ServiceDTO> getServicesByProvider(int providerId);
}