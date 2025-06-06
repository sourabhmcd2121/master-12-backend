package com.master.app.pims.controller.master.org;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.master.OrgPrimaryRepository;
import com.master.app.pims.repositories.master.OrgWrapperRepository;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class MasterOrgController {
	
	  @Autowired
	    private Validator validator;
	  
	  @Autowired
	    private CommonMasterService commonMasterService;
	  
	  @Autowired
	    private OrgPrimaryRepository orgPrimaryRepository;
	  
	  @Autowired
	    private OrgWrapperRepository orgWrapperRepository;
	  
	  
	  /////////////////////////////////////OrgPrimary Start///////////////////////////////////
	    //get all data from table
	    @GetMapping("/getOrgPrimaryList")
	    public ResponseEntity<BaseResponse> getOrgPrimaryList() {
	        BaseResponse response = new BaseResponse();
	        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        List<OrgPrimary> list = orgPrimaryRepository.findAll();
	        response.setMessage("success");
	        response.setStatus(true);
	        response.setTotalDataCount(list.size());
	        response.setOrgPrimary(list);
	        return ResponseEntity.ok(response);
	    }
	    
	    // Create New Data And Update
	    @PostMapping("/submitOrgPrimary")
	    public BaseResponse submitOrgPrimary(@RequestBody OrgPrimary orgPrimary, HttpServletRequest request) {
	        BaseResponse resultData = new BaseResponse();

	        // Check if guid is provided (indicating an update)
	        if (orgPrimary.getOrgPrimaryGuid() == null || orgPrimary.getOrgPrimaryGuid().isEmpty()) {
	            // Add new data
	        	orgPrimary.setCreaterIp(request.getRemoteAddr());
	        	orgPrimary.setOrgPrimaryGuid(UUID.randomUUID().toString());
	        	orgPrimary.setCreatedDate(new Date());
	        	orgPrimary.setModifierIp(null);
	        	orgPrimary.setModifiedByGuid(null);
	        	orgPrimary.setModifiedDate(null);
	        	orgPrimary.setCreatedByGuid(request.getRemoteAddr());

	            if (orgPrimary.getIsRecordActive() == null)
	            	orgPrimary.setIsRecordActive(false);
//				orgPrimary.setCreaterRemarks(userSessionParam.getUserFullName());
	            //orgPrimary.setCreaterMacId(HttpSessionHelper.getMacAddress());
	            //orgPrimary.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
	        		
	        	
	        } else {
	            // Update existing data
	        	OrgPrimary existingOrgPrimary = commonMasterService.getOrgPrimaryById(orgPrimary.getOrgPrimaryGuid());

	            if (existingOrgPrimary != null) {
	            	existingOrgPrimary.setOrgPrimaryCode(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryCode()) ? orgPrimary.getOrgPrimaryCode().toUpperCase().trim() : null);
	            	existingOrgPrimary.setOrgPrimaryNameEn(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryNameEn()) ? orgPrimary.getOrgPrimaryNameEn().toUpperCase().trim() : null);

	            	existingOrgPrimary.setOrgPrimaryNameHi(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryNameHi()) ? orgPrimary.getOrgPrimaryNameHi().toUpperCase().trim() : null);
	            	existingOrgPrimary.setOrgPrimaryNameRl(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryNameRl()) ? orgPrimary.getOrgPrimaryNameRl().trim() : null);
	            	existingOrgPrimary.setDescription(!Util.isNullOrEmpty(orgPrimary.getDescription()) ? orgPrimary.getDescription().trim() : null);
	            	existingOrgPrimary.setToDate(orgPrimary.getToDate());
	            	existingOrgPrimary.setFromDate(orgPrimary.getFromDate());
	             
	            	existingOrgPrimary.setIsRecordActive(orgPrimary.getIsRecordActive() != null ? orgPrimary.getIsRecordActive() : existingOrgPrimary.getIsRecordActive());

	            	existingOrgPrimary.setModifierIp(request.getRemoteAddr());
	            	existingOrgPrimary.setModifiedDate(new Date());
	                if (existingOrgPrimary.getIsRecordActive() == null)
	                	existingOrgPrimary.setIsRecordActive(false);
	                // for now setting some dummy value to test
	                existingOrgPrimary.setModifiedByGuid(UUID.randomUUID().toString());
	                existingOrgPrimary.setModifierMacId(UUID.randomUUID().toString());
	                orgPrimary = existingOrgPrimary; // Use the updated existing country object
	            } else {
	                log.error("Org Primary not found");
	                resultData.setStatus(false);
	                resultData.setMessage("Org Primary not found");
	                return resultData;
	            }
	        }

	        // Validation
	        resultData = validator.validateOrgPrimary(orgPrimary);
	        if (resultData != null && !resultData.getStatus()) {
	            log.error("Validation failed: {}", resultData.getMessage());
	            return resultData;
	        }

	        // If validation passes, proceed to save or update
	        if (orgPrimary.getIsRecordActive() == null) orgPrimary.setIsRecordActive(false);
	        orgPrimary.setOrgPrimaryCode(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryCode()) ? orgPrimary.getOrgPrimaryCode().toUpperCase().trim() : null);
	        orgPrimary.setOrgPrimaryNameEn(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryNameEn()) ? orgPrimary.getOrgPrimaryNameEn().toUpperCase().trim() : null);
	        orgPrimary.setOrgPrimaryNameHi(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryNameHi()) ? orgPrimary.getOrgPrimaryNameHi().trim() : null);
	        orgPrimary.setOrgPrimaryNameRl(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryNameRl()) ? orgPrimary.getOrgPrimaryNameRl().trim() : null);
	        orgPrimary.setDescription(!Util.isNullOrEmpty(orgPrimary.getDescription()) ? orgPrimary.getDescription().trim() : null);
//	        orgPrimary.setToDate(orgPrimary.getToDate());
//	        orgPrimary.setFromDate(orgPrimary.getFromDate());

	     

	        try {
	            orgPrimaryRepository.save(orgPrimary);
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
	    @GetMapping("/getOrgPrimaryByGuid/{orgPrimaryGuid}")
	    public ResponseEntity<OrgPrimary> getOrgPrimaryByGuid(@PathVariable("orgPrimaryGuid") String orgPrimaryGuid) {
	    	OrgPrimary orgPrimary = orgPrimaryRepository.findById(orgPrimaryGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with orgPrimaryGuid : " + orgPrimaryGuid));
	        return new ResponseEntity<>(orgPrimary, HttpStatus.OK);
	    }
	    
	    // Delete data from table and return success message
	    @DeleteMapping("/deleteOrgPrimary/{orgPrimaryGuid}")
	    public ResponseEntity<BaseResponse> deleteOrgPrimary(@PathVariable("orgPrimaryGuid") String orgPrimaryGuid) {
	        BaseResponse response = new BaseResponse();
	        
	        OrgPrimary orgPrimary = orgPrimaryRepository.findById(orgPrimaryGuid)
	                .orElseThrow(() -> new ResourceNotFoundException("Data not found with orgPrimaryGuid: " + orgPrimaryGuid));
	        
	        orgPrimaryRepository.delete(orgPrimary);
	        
	        response.setMessage("Data deleted successfully");
	        response.setStatus(true);
	        response.setTotalDataCount(0);  // No data to return after delete
	        
	        return ResponseEntity.ok(response);
	    }


	  

	    /////////////////////////////////////OrgPrimary  End///////////////////////////////////
	    
	    
	    /////////////////////////////////////OrgWrapper Start///////////////////////////////////
	    //get all data from table
	    @GetMapping("/getOrgWrapperList")
	    public ResponseEntity<BaseResponse> getOrgWrapperList() {
	        BaseResponse response = new BaseResponse();
	        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        List<OrgWrapper> list = orgWrapperRepository.findAll();
	        response.setMessage("success");
	        response.setStatus(true);
	        response.setTotalDataCount(list.size());
	        response.setOrgWrapper(list);
	        return ResponseEntity.ok(response);
	    }
	    
	    // Create New Data And Update
	    @PostMapping("/submitOrgWrapper")
	    public BaseResponse submitOrgWrapper(@RequestBody OrgWrapper orgWrapper, HttpServletRequest request) {
	        BaseResponse resultData = new BaseResponse();

	        // Check if guid is provided (indicating an update)
	        if (orgWrapper.getWrapperGuid() == null || orgWrapper.getWrapperGuid().isEmpty()) {
	            // Add new data
	        	orgWrapper.setCreaterIp(request.getRemoteAddr());
	        	orgWrapper.setWrapperGuid(UUID.randomUUID().toString());
	        	orgWrapper.setCreatedDate(new Date());
	        	orgWrapper.setModifierIp(null);
	        	orgWrapper.setModifiedByGuid(null);
	        	orgWrapper.setModifiedDate(null);
	        	orgWrapper.setCreatedByGuid(request.getRemoteAddr());

	            if (orgWrapper.getIsRecordActive() == null)
	            	orgWrapper.setIsRecordActive(false);
//				orgWrapper.setCreaterRemarks(userSessionParam.getUserFullName());
	            //orgWrapper.setCreaterMacId(HttpSessionHelper.getMacAddress());
	            //orgWrapper.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
	        		
	        	
	        } else {
	            // Update existing data
	        	OrgWrapper existingOrgWrapper = commonMasterService.getOrgWrapperById(orgWrapper.getWrapperGuid());

	            if (existingOrgWrapper != null) {
	            	existingOrgWrapper.setWraperCode(!Util.isNullOrEmpty(orgWrapper.getWraperCode()) ? orgWrapper.getWraperCode().toUpperCase().trim() : null);
	            	existingOrgWrapper.setWraperNameEn(!Util.isNullOrEmpty(orgWrapper.getWraperNameEn()) ? orgWrapper.getWraperNameEn().toUpperCase().trim() : null);

	            	existingOrgWrapper.setWraperNameHi(!Util.isNullOrEmpty(orgWrapper.getWraperNameHi()) ? orgWrapper.getWraperNameHi().toUpperCase().trim() : null);
	            	existingOrgWrapper.setWraperNameRl(!Util.isNullOrEmpty(orgWrapper.getWraperNameRl()) ? orgWrapper.getWraperNameRl().trim() : null);
	            	existingOrgWrapper.setWraperDescription(!Util.isNullOrEmpty(orgWrapper.getWraperDescription()) ? orgWrapper.getWraperDescription().trim() : null);
	            	existingOrgWrapper.setToDate(orgWrapper.getToDate());
	            	existingOrgWrapper.setFromDate(orgWrapper.getFromDate());
	             
	            	existingOrgWrapper.setIsRecordActive(orgWrapper.getIsRecordActive() != null ? orgWrapper.getIsRecordActive() : existingOrgWrapper.getIsRecordActive());

	            	existingOrgWrapper.setModifierIp(request.getRemoteAddr());
	            	existingOrgWrapper.setModifiedDate(new Date());
	                if (existingOrgWrapper.getIsRecordActive() == null)
	                	existingOrgWrapper.setIsRecordActive(false);
	                // for now setting some dummy value to test
	                existingOrgWrapper.setModifiedByGuid(UUID.randomUUID().toString());
	                existingOrgWrapper.setModifierMacId(UUID.randomUUID().toString());
	                orgWrapper = existingOrgWrapper; // Use the updated existing country object
	            } else {
	                log.error("Org Wrapper not found");
	                resultData.setStatus(false);
	                resultData.setMessage("Org Wrapper not found");
	                return resultData;
	            }
	        }

	        // Validation
	        resultData = validator.validateOrgWrapper(orgWrapper);
	        if (resultData != null && !resultData.getStatus()) {
	            log.error("Validation failed: {}", resultData.getMessage());
	            return resultData;
	        }

	        // If validation passes, proceed to save or update
	        if (orgWrapper.getIsRecordActive() == null) orgWrapper.setIsRecordActive(false);
	        orgWrapper.setWraperCode(!Util.isNullOrEmpty(orgWrapper.getWraperCode()) ? orgWrapper.getWraperCode().toUpperCase().trim() : null);
	        orgWrapper.setWraperNameEn(!Util.isNullOrEmpty(orgWrapper.getWraperNameEn()) ? orgWrapper.getWraperNameEn().toUpperCase().trim() : null);
	        orgWrapper.setWraperNameHi(!Util.isNullOrEmpty(orgWrapper.getWraperNameHi()) ? orgWrapper.getWraperNameHi().trim() : null);
	        orgWrapper.setWraperNameRl(!Util.isNullOrEmpty(orgWrapper.getWraperNameRl()) ? orgWrapper.getWraperNameRl().trim() : null);
	        orgWrapper.setWraperDescription(!Util.isNullOrEmpty(orgWrapper.getWraperDescription()) ? orgWrapper.getWraperDescription().trim() : null);
//	        orgWrapper.setToDate(orgPrimary.getToDate());
//	        orgWrapper.setFromDate(orgPrimary.getFromDate());

	     

	        try {
	            orgWrapperRepository.save(orgWrapper);
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
	    @GetMapping("/getOrgWrapperByGuid/{wrapperGuid}")
	    public ResponseEntity<OrgWrapper> getOrgWrapperByGuid(@PathVariable("wrapperGuid") String wrapperGuid) {
	    	OrgWrapper orgWrapper = orgWrapperRepository.findById(wrapperGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with wrapperGuid : " + wrapperGuid));
	        return new ResponseEntity<>(orgWrapper, HttpStatus.OK);
	    }
	    
	    // Delete data from table and return success message
	    @DeleteMapping("/deleteOrgWrapper/{wrapperGuid}")
	    public ResponseEntity<BaseResponse> deleteOrgWrapper(@PathVariable("wrapperGuid") String wrapperGuid) {
	        BaseResponse response = new BaseResponse();
	        
	        OrgWrapper orgWrapper = orgWrapperRepository.findById(wrapperGuid)
	                .orElseThrow(() -> new ResourceNotFoundException("Data not found with wrapperGuid: " + wrapperGuid));
	        
	        orgWrapperRepository.delete(orgWrapper);
	        
	        response.setMessage("Data deleted successfully");
	        response.setStatus(true);
	        response.setTotalDataCount(0);  // No data to return after delete
	        
	        return ResponseEntity.ok(response);
	    }


	  

	    /////////////////////////////////////OrgWrapper  End///////////////////////////////////

}
