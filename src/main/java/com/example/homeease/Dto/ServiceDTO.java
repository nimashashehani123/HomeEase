package com.example.homeease.Dto;
import lombok.Data;

@Data
public class ServiceDTO {
    private int serviceId;
    private String serviceName;
    private String description;
    private double price;
    private String image; // Path to the service image
    private int categoryId; // Associated category ID
    private int serviceProviderId; // Associated service provider ID
}