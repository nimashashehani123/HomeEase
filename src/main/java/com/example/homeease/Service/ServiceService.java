package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ServiceDTO;

public interface ServiceService {
    int addService(ServiceDTO serviceDTO);
    ResponseDTO getAllServices();
    ResponseDTO getServiceById(int serviceId);
    ResponseDTO updateService(int serviceId, ServiceDTO serviceDTO);
    ResponseDTO deleteService(int serviceId);
}