package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ServiceDTO;

import java.util.List;

public interface ServiceService {
    int addService(ServiceDTO serviceDTO);
    ResponseDTO getAllServices();
    ResponseDTO getServiceById(int serviceId);
    int updateService(ServiceDTO serviceDTO);
    boolean hasAssociatedBookings(int serviceId);
    ResponseDTO deleteService(int serviceId);
    ResponseDTO getServicesByCategoryId(int categoryId);
}