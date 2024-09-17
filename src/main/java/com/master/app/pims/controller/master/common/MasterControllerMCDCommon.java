package com.master.app.pims.controller.master.common;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.ApplicationMasterRepository;
import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class MasterControllerMCDCommon {
	
	
	
	  @Autowired
	    private Validator validator;
	  
	  @Autowired
	    private CommonMasterService commonMasterService;

	  
	   @Autowired
	    private ApplicationMasterRepository applicationMasterRepository;

	  
	  
	  //////////////////////////////////////////////Application Master Start////////////////////////////
	  
	   //get all data from table
	    @GetMapping("/getApplicationMasterList")
	    public ResponseEntity<BaseResponse> getApplicationMasterList() {
	        BaseResponse response = new BaseResponse();
	        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        List<ApplicationMaster> list = applicationMasterRepository.findAll();
	        response.setMessage("success");
	        response.setStatus(true);
	        response.setTotalDataCount(list.size());
	        response.setApplicationMaster(list);
	        return ResponseEntity.ok(response);
	    }
	    
	    // Create New Data And Update
	    @PostMapping("/submitOrUpdateApplicationMaster")
	    public BaseResponse submitOrUpdateApplicationMaster(@RequestBody ApplicationMaster appMaster, HttpServletRequest request) {
	        BaseResponse resultData = new BaseResponse();

	        // Check if guid is provided (indicating an update)
	        if (appMaster.getApplicationMasterGuid() == null || appMaster.getApplicationMasterGuid().isEmpty()) {
	            // Add new data
	        	appMaster.setCreatedIpAddr(request.getRemoteAddr());
	        	appMaster.setApplicationMasterGuid(UUID.randomUUID().toString());
	        	appMaster.setCreatedDate(new Date());
	        	appMaster.setModifiedIpAddr(null);
	        	appMaster.setModifiedBy(null);
	        	appMaster.setModifiedDate(null);
	        	
				//appMaster.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
				//appMaster.setCreatedMacAddr(HttpSessionHelper.getMacAddress());  	
			//appMaster.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
				//appMaster.setCreatedRemarks(userSessionParam.getUserFullName());
			//appMaster.setCreatedBy(userSessionParam.getEmpBasicGUID());
//				appMaster.setCreaterRemarks(userSessionParam.getUserFullName());
	            //appMaster.setCreaterMacId(HttpSessionHelper.getMacAddress());
	            //appMaster.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
	        } else {
	            // Update existing data
	        	ApplicationMaster existingAppMaster = commonMasterService.getApplicationMasterById(appMaster.getApplicationMasterGuid());

	            if (existingAppMaster != null) {
	            	existingAppMaster.setApplicationMasterCode(!Util.isNullOrEmpty(appMaster.getApplicationMasterCode()) ? appMaster.getApplicationMasterCode().toUpperCase().trim() : null);

	            	existingAppMaster.setApplicationMasterName(!Util.isNullOrEmpty(appMaster.getApplicationMasterName()) ? appMaster.getApplicationMasterName().toUpperCase().trim() : null);
	            	existingAppMaster.setApplicationMasterIp4(!Util.isNullOrEmpty(appMaster.getApplicationMasterIp4()) ? appMaster.getApplicationMasterIp4().trim() : null);
	            	existingAppMaster.setApplicationMasterUrl(!Util.isNullOrEmpty(appMaster.getApplicationMasterUrl()) ? appMaster.getApplicationMasterUrl().trim() : null);
	            
	            	existingAppMaster.setIsActive(appMaster.getIsActive() != null ? appMaster.getIsActive() : existingAppMaster.getIsActive());

	            	existingAppMaster.setModifiedIpAddr(request.getRemoteAddr());
	            	existingAppMaster.setModifiedDate(new Date());
	            	
	            	//existingAppMaster.setModifiedIpAddr(HttpSessionHelper.getClientIPAddress(request));
					//existingAppMaster.setModifiedMacAddr(HttpSessionHelper.getMacAddress());
	            	//existingAppMaster.setModifiedBy(userSessionParam.getEmpBasicGUID());
	            	//existingAppMaster.setModifiedDate(new Date());
	            	
	                appMaster = existingAppMaster; // Use the updated existing country object
	            } else {
	                log.error("Application Master not found");
	                resultData.setStatus(false);
	                resultData.setMessage("Application Master not found");
	                return resultData;
	            }
	        }

	        // Validation
	        resultData = validator.validateApplicationMaster(appMaster);
	        if (resultData != null && !resultData.getStatus()) {
	            log.error("Validation failed: {}", resultData.getMessage());
	            return resultData;
	        }

	        // If validation passes, proceed to save or update
	        if (appMaster.getIsActive() == null) appMaster.setIsActive(false);
	        appMaster.setApplicationMasterCode(!Util.isNullOrEmpty(appMaster.getApplicationMasterCode()) ? appMaster.getApplicationMasterCode().toUpperCase().trim() : null);
	        appMaster.setApplicationMasterName(!Util.isNullOrEmpty(appMaster.getApplicationMasterName()) ? appMaster.getApplicationMasterName().toUpperCase().trim() : null);
	        appMaster.setApplicationMasterIp4(!Util.isNullOrEmpty(appMaster.getApplicationMasterIp4()) ? appMaster.getApplicationMasterIp4().trim() : null);
	        appMaster.setApplicationMasterUrl(!Util.isNullOrEmpty(appMaster.getApplicationMasterUrl()) ? appMaster.getApplicationMasterUrl().trim() : null);

	       
	        try {
	        	applicationMasterRepository.save(appMaster);
	            log.info("Record SaveOrUpdate Successfully");
	            resultData.setStatus(true);
	            resultData.setMessage("Record saved or updated successfully");
	        } catch (Exception e) {
	            log.error("Error saving or updating record: {}", e.getMessage());
	            resultData.setStatus(false);
	            resultData.setMessage("Error saving or updating record: " + e.getMessage());
	        }

	        return resultData;
	    }

	    
	 


	    //get data by id
	    @GetMapping("/getApplicationMasterByGuid/{applicationMasterGuid}")
	    public ResponseEntity<ApplicationMaster> getApplicationMasterByGuid(@PathVariable("applicationMasterGuid") String applicationMasterGuid) {
	    	ApplicationMaster appMaster = applicationMasterRepository.findById(applicationMasterGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with applicationMasterGuid : " + applicationMasterGuid));
	        return new ResponseEntity<>(appMaster, HttpStatus.OK);
	    }
	  
	  
	  //////////////////////////////////////////////Application Master End////////////////////////////
	  
	  

}
