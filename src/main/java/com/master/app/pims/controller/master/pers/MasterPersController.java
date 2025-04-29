package com.master.app.pims.controller.master.pers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.master.app.pims.controller.master.org.MasterOrgController;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.PersRelation;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.master.PersRelationRepo;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class MasterPersController {
	 @Autowired
	    private Validator validator;
	  
	  @Autowired
	    private CommonMasterService commonMasterService;
	  
	  @Autowired
	    private PersRelationRepo persRelationRepo;
	  
	  
	  
	  /////////////////////////////////////PersRelation Start///////////////////////////////////
	    //get all data from table
	    @GetMapping("/getPersRelationList")
	    public ResponseEntity<BaseResponse> getPersRelationList() {
	        BaseResponse response = new BaseResponse();
	        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        List<PersRelation> list = persRelationRepo.findAll();
	        response.setMessage("success");
	        response.setStatus(true);
	        response.setTotalDataCount(list.size());
	        response.setPersRelation(list);
	        return ResponseEntity.ok(response);
	    }
	    
	    // Create New Data And Update
	    @PostMapping("/submitPersRelation")
	    public BaseResponse submitPersRelation(@RequestBody PersRelation persRelation, HttpServletRequest request) {
	        BaseResponse resultData = new BaseResponse();

	        // Check if guid is provided (indicating an update)
	        if (persRelation.getPersRelationGuid() == null || persRelation.getPersRelationGuid().isEmpty()) {
	            // Add new data
	        	persRelation.setCreaterIp(request.getRemoteAddr());
	        	persRelation.setPersRelationGuid(UUID.randomUUID().toString());
	        	persRelation.setCreatedDate(new Date());
	        	persRelation.setModifierIp(null);
	        	persRelation.setModifiedByGuid(null);
	        	persRelation.setModifiedDate(null);
	        	persRelation.setCreatedByGuid(request.getRemoteAddr());

	            if (persRelation.getIsRecordActive() == null)
	            	persRelation.setIsRecordActive(false);
//				persRelation.setCreaterRemarks(userSessionParam.getUserFullName());
	            //persRelation.setCreaterMacId(HttpSessionHelper.getMacAddress());
	            //persRelation.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
	        		
	        	
	        } else {
	            // Update existing data
	        	PersRelation existingPersRelation = commonMasterService.getPersRelationById(persRelation.getPersRelationGuid());

	            if (existingPersRelation != null) {
	            	existingPersRelation.setRelationCode(!Util.isNullOrEmpty(persRelation.getRelationCode()) ? persRelation.getRelationCode().toUpperCase().trim() : null);
	            	existingPersRelation.setRelationName(!Util.isNullOrEmpty(persRelation.getRelationName()) ? persRelation.getRelationName().toUpperCase().trim() : null);

	            	existingPersRelation.setRelationDescription(!Util.isNullOrEmpty(persRelation.getRelationDescription()) ? persRelation.getRelationDescription().toUpperCase().trim() : null);
	            	
	            	existingPersRelation.setFromDate(persRelation.getFromDate());
	            	existingPersRelation.setToDate(persRelation.getToDate());
	            	existingPersRelation.setIsRecordActive(persRelation.getIsRecordActive() != null ? persRelation.getIsRecordActive() : existingPersRelation.getIsRecordActive());

	            	existingPersRelation.setModifierIp(request.getRemoteAddr());
	            	existingPersRelation.setModifiedDate(new Date());
	                if (existingPersRelation.getIsRecordActive() == null)
	                	existingPersRelation.setIsRecordActive(false);
	                // for now setting some dummy value to test
	                existingPersRelation.setModifiedByGuid(UUID.randomUUID().toString());
	                existingPersRelation.setModifierMacId(UUID.randomUUID().toString());
	                persRelation = existingPersRelation; // Use the updated existing country object
	            } else {
	                log.error("PersRelation not found");
	                resultData.setStatus(false);
	                resultData.setMessage("PersRelation not found");
	                return resultData;
	            }
	        }

	        // Validation
	        resultData = validator.validatePersRelation(persRelation);
	        if (resultData != null && !resultData.getStatus()) {
	            log.error("Validation failed: {}", resultData.getMessage());
	            return resultData;
	        }

	        // If validation passes, proceed to save or update
	        if (persRelation.getIsRecordActive() == null) persRelation.setIsRecordActive(false);
	        persRelation.setRelationCode(!Util.isNullOrEmpty(persRelation.getRelationCode()) ? persRelation.getRelationCode().toUpperCase().trim() : null);
	        persRelation.setRelationName(!Util.isNullOrEmpty(persRelation.getRelationName()) ? persRelation.getRelationName().toUpperCase().trim() : null);

	        persRelation.setRelationDescription(!Util.isNullOrEmpty(persRelation.getRelationDescription()) ? persRelation.getRelationDescription().toUpperCase().trim() : null);
        	
	        persRelation.setFromDate(persRelation.getFromDate());
	        persRelation.setToDate(persRelation.getToDate());
	     

	        try {
	        	persRelationRepo.save(persRelation);
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
	    @GetMapping("/getPersRelationByGuid/{persRelationGuid}")
	    public ResponseEntity<PersRelation> getPersRelationByGuid(@PathVariable("persRelationGuid") String persRelationGuid) {
	    	PersRelation persRelation = persRelationRepo.findById(persRelationGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with persRelationGuid : " + persRelationGuid));
	        return new ResponseEntity<>(persRelation, HttpStatus.OK);
	    }
	    
	    
	    /////////////////////////////////////PersRelation  End///////////////////////////////////
}
