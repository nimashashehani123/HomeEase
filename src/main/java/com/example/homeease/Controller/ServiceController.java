package com.example.homeease.Controller;

import com.example.homeease.Dto.CategoryDTO;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ServiceDTO;
import com.example.homeease.Service.ServiceService;
import com.example.homeease.Utill.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO> addService(  @RequestPart("serviceDTO") ServiceDTO serviceDTO,
                                                    @RequestPart("file") MultipartFile file) {


        System.out.println(serviceDTO);
        try {
            String imagePath = null;
            if (!file.isEmpty()) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String uploadDir = "FrontEnd/view/uploads/";

                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                imagePath = (filename);
            }
            // Set image path in DTO
            serviceDTO.setImage(imagePath);

            // Save to database
            int result = serviceService.addService(serviceDTO);

            if (result == VarList.Created) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseDTO(VarList.Created, "Service created", serviceDTO));
            } else {
                return ResponseEntity.status(HttpStatus.valueOf(result))
                        .body(new ResponseDTO(result, "Failed to create service", null));
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDTO(VarList.Internal_Server_Error,
                            "Error: " + e.getMessage(), null));
        }
    }


    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<ResponseDTO> getServicesByCategoryId(
            @PathVariable int categoryId) {
        try {
            ResponseDTO responseDTO = serviceService.getServicesByCategoryId(categoryId);
            return ResponseEntity.ok()
                    .body(new ResponseDTO(VarList.OK, "Success", responseDTO));
        }catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDTO(VarList.Internal_Server_Error,
                            "Error: " + e.getMessage(), null));
        }
    }



    @GetMapping
    public ResponseEntity<ResponseDTO> getAllServices() {
        ResponseDTO response = serviceService.getAllServices();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ResponseDTO> getServiceById(@PathVariable int serviceId) {
        try {
            ResponseDTO responseDTO = serviceService.getServiceById(serviceId);
            return ResponseEntity.ok()
                    .body(new ResponseDTO(VarList.OK, "Success", responseDTO));
        }catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDTO(VarList.Internal_Server_Error,
                            "Error: " + e.getMessage(), null));
        }
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