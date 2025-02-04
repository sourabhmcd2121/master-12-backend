package com.master.app.pims.controller.master.citizen;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.master.app.pims.entities.schemas.citizenmaster.AdminDetail;
import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.citizen.AdminDetailRepo;
import com.master.app.pims.service.master.CommonMasterServiceHandler;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class MasterControllerCitizen {
	@Autowired
	private Validator validator;

	@Autowired
	private CommonMasterService commonMasterService;
	
	@Autowired
	private CommonMasterServiceHandler serviceHandler;
	
	
	
	@Autowired
	private AdminDetailRepo adminDetailRepo;
	
/////////////////////////////////////AdminDetail Start///////////////////////////////////
//get all data from table
@GetMapping("/getAdminDetailList")
public ResponseEntity<BaseResponse> getAdminDetailList() {
BaseResponse response = new BaseResponse();
// Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<AdminDetail> list = adminDetailRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setAdminDetail(list);
return ResponseEntity.ok(response);
}

@PostMapping("/submitAdminDetail")
public BaseResponse submitAdminDetail(@RequestBody AdminDetail adminDetail, HttpServletRequest request) {
    BaseResponse resultData = new BaseResponse();

    try {
        // Check if GUID is provided (indicating an update)
        if (adminDetail.getAdminDetailGuid() == null || adminDetail.getAdminDetailGuid().isEmpty()) {
            // Add new data
            adminDetail.setCreatedIpAddr(request.getRemoteAddr());
            adminDetail.setAdminDetailGuid(UUID.randomUUID().toString());
            adminDetail.setCreatedDate(new Date());
            adminDetail.setModifiedIpAddr(null);
            adminDetail.setModifiedBy(null);
            adminDetail.setModifiedDate(null);
            adminDetail.setCreatedBy(request.getRemoteAddr());

            if (adminDetail.getIsActive() == null)
                adminDetail.setIsActive(false);

            // Image upload code start
            if (adminDetail.getUserImage1Base64() != null && !adminDetail.getUserImage1Base64().isEmpty()) {
                try {
                    // Decode the base64 image string
                    byte[] imageBytes = Base64.getDecoder().decode(adminDetail.getUserImage1Base64());
                    adminDetail.setUserImage1(imageBytes);
                } catch (IllegalArgumentException e) {
                    log.error("Invalid Base64 image data.", e);
                    resultData.setStatus(false);
                    resultData.setMessage("Invalid Base64 image data.");
                    return resultData;
                }
            }
            // Image upload code end

        } else {
            // Update existing data
            AdminDetail existingAdminDetail = commonMasterService.getAdminDetailById(adminDetail.getAdminDetailGuid());

            if (existingAdminDetail != null) {
                existingAdminDetail.setUserName(!Util.isNullOrEmpty(adminDetail.getUserName()) ? adminDetail.getUserName().toUpperCase().trim() : null);
                existingAdminDetail.setDepartmentName(!Util.isNullOrEmpty(adminDetail.getDepartmentName()) ? adminDetail.getDepartmentName().toUpperCase().trim() : null);
                existingAdminDetail.setEncryptedPwd(!Util.isNullOrEmpty(adminDetail.getEncryptedPwd()) ? adminDetail.getEncryptedPwd().toUpperCase().trim() : null);
                existingAdminDetail.setIpAddress(!Util.isNullOrEmpty(adminDetail.getIpAddress()) ? adminDetail.getIpAddress().trim() : null);
                existingAdminDetail.setActiveFromDate(adminDetail.getActiveFromDate());
                existingAdminDetail.setActiveTill(adminDetail.getActiveTill());
                existingAdminDetail.setIsActive(adminDetail.getIsActive() != null ? adminDetail.getIsActive() : existingAdminDetail.getIsActive());

                if (existingAdminDetail.getIsActive() == null)
                    existingAdminDetail.setIsActive(false);

                // If a new image is provided, decode and update the existing image start
                if (adminDetail.getUserImage1Base64() != null && !adminDetail.getUserImage1Base64().isEmpty()) {
                    try {
                        byte[] imageBytes = Base64.getDecoder().decode(adminDetail.getUserImage1Base64());
                        existingAdminDetail.setUserImage1(imageBytes);
                    } catch (IllegalArgumentException e) {
                        log.error("Invalid Base64 image data.", e);
                        resultData.setStatus(false);
                        resultData.setMessage("Invalid Base64 image data.");
                        return resultData;
                    }
                }
                // If a new image is provided, decode and update the existing image end

                // Update modification details
                existingAdminDetail.setModifiedIpAddr(request.getRemoteAddr());
                existingAdminDetail.setModifiedDate(new Date());
                existingAdminDetail.setModifiedBy(UUID.randomUUID().toString());
                existingAdminDetail.setModifiedMacAddr(UUID.randomUUID().toString());
                adminDetail = existingAdminDetail;  // Use the updated existing menu master object
            } else {
                log.error("AdminDetail Master not found");
                resultData.setStatus(false);
                resultData.setMessage("AdminDetail Master not found");
                return resultData;
            }
        }

        // Validation
        resultData = validator.validateAdminDetail(adminDetail);
        if (resultData != null && !resultData.getStatus()) {
            log.error("Validation failed: {}", resultData.getMessage());
            return resultData;
        }

        // Set some default values for empty fields
        if (adminDetail.getIsActive() == null) adminDetail.setIsActive(false);
        adminDetail.setUserName(!Util.isNullOrEmpty(adminDetail.getUserName()) ? adminDetail.getUserName().toUpperCase().trim() : null);
        adminDetail.setDepartmentName(!Util.isNullOrEmpty(adminDetail.getDepartmentName()) ? adminDetail.getDepartmentName().toUpperCase().trim() : null);
        adminDetail.setEncryptedPwd(!Util.isNullOrEmpty(adminDetail.getEncryptedPwd()) ? adminDetail.getEncryptedPwd().trim() : null);
        adminDetail.setIpAddress(!Util.isNullOrEmpty(adminDetail.getIpAddress()) ? adminDetail.getIpAddress().trim() : null);
        adminDetail.setActiveFromDate(adminDetail.getActiveFromDate());
        adminDetail.setActiveTill(adminDetail.getActiveTill());

        // Save or update the data
        try {
            adminDetailRepo.save(adminDetail);
            log.info("Record SaveOrUpdate Successfully");
            resultData.setStatus(true);
            resultData.setMessage("Record saved or updated successfully");
        } catch (Exception e) {
            log.error("Error saving or updating record: {}", e.getMessage(), e);
            resultData.setStatus(false);
            resultData.setMessage("Error saving or updating record: " + e.getMessage());
        }

    } catch (Exception e) {
        log.error("Unexpected error occurred: {}", e.getMessage(), e);
        resultData.setStatus(false);
        resultData.setMessage("Unexpected error occurred: " + e.getMessage());
    }

    return resultData;
}



//get data by id
@GetMapping("/getAdminDetailByGuid/{adminDetailGuid}")
public ResponseEntity<AdminDetail> getAdminDetailByGuid(@PathVariable("adminDetailGuid") String adminDetailGuid) {
	AdminDetail adminDetail = adminDetailRepo.findById(adminDetailGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with menuMasterGuid : " + adminDetailGuid));
return new ResponseEntity<>(adminDetail, HttpStatus.OK);
}

/////////////////////////////////////AdminDetail  End///////////////////////////////////
	
//Function to  convert image in Base64
public String convertImageToBase64(byte[] imageBytes) {
    return Base64.getEncoder().encodeToString(imageBytes);
}
    
}
