package com.example.homeease.Controller;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.CategoryDTO;
import com.example.homeease.Service.CategoryService;
import com.example.homeease.Utill.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO> addCategory(
            @RequestPart("categoryDTO") CategoryDTO categoryDTO,
            @RequestPart("file") MultipartFile file) {  // Changed from @RequestParam to @RequestPart
        System.out.println("Received categoryDTO: " + categoryDTO);
        System.out.println("Received file: " + (file != null ? file.getOriginalFilename() : "null"));
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
            categoryDTO.setImage(imagePath);

            // Save to database
            int result = categoryService.addCategory(categoryDTO);

            if (result == VarList.Created) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseDTO(VarList.Created, "Category created", categoryDTO));
            } else if(result == VarList.Not_Acceptable){
                return ResponseEntity.status(HttpStatus.valueOf(result))
                        .body(new ResponseDTO(result, "Failed to create category", null));
            }else {
                return ResponseEntity.status(HttpStatus.valueOf(result))
                        .body(new ResponseDTO(result, "Failed to create category", null));
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDTO(VarList.Internal_Server_Error,
                            "Error: " + e.getMessage(), null));
        }
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllCategories() {
        try {
            ResponseDTO responseDTO = categoryService.getAllCategories();
            System.out.println(responseDTO);

            return ResponseEntity.ok()
                    .body(new ResponseDTO(VarList.OK, "Success", responseDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDTO(VarList.Internal_Server_Error,
                            "Error: " + e.getMessage(), null));
        }
    }


    @GetMapping("/allIds")
    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    public ResponseEntity<ResponseDTO> getAllCategoryIds() {
        try {
            ResponseDTO responseDTO = categoryService.getAllCategoryIds();
            System.out.println(responseDTO);
            return ResponseEntity.ok()
                    .body(new ResponseDTO(VarList.OK, "Success", responseDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDTO(VarList.Internal_Server_Error,
                            "Error: " + e.getMessage(), null));
        }
    }

    @GetMapping("/id-by-name")
    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    public ResponseEntity<ResponseDTO> getCategoryIdByName(
            @RequestParam String categoryName) {
try {
    ResponseDTO responseDTO = categoryService.getCategoryIdByName(categoryName);
    return ResponseEntity.ok()
            .body(new ResponseDTO(VarList.OK, "Success", responseDTO));
}catch (Exception e) {
    return ResponseEntity.internalServerError()
            .body(new ResponseDTO(VarList.Internal_Server_Error,
                    "Error: " + e.getMessage(), null));
}
    }



    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO> getCategoryById(@PathVariable int categoryId) {
        ResponseDTO response = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO> updateCategory(@PathVariable int categoryId, @RequestBody CategoryDTO categoryDTO) {
        ResponseDTO response = categoryService.updateCategory(categoryId, categoryDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable int categoryId) {
        ResponseDTO response = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}