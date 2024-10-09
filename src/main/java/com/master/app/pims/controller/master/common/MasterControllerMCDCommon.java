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
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.ApplicationMasterRepository;
import com.master.app.pims.repositories.AssessmentYearRepository;
import com.master.app.pims.repositories.AssociatedChargesInfoRepository;
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
	   
	   @Autowired
	    private AssessmentYearRepository assessmentYearRepository;
	   
	   @Autowired
	    private AssociatedChargesInfoRepository associatedChargesInfoRepository;
	   
	  
	  
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
	        	appMaster.setCreatedBy(request.getRemoteAddr());
	        	if (appMaster.getIsActive() == null)
	        		appMaster.setIsActive(false);
	        	
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
	            	
	            	  if (existingAppMaster.getIsActive() == null)
	            		  existingAppMaster.setIsActive(false);
	            	  
	            	  // for now setting some dummy value to test
	            	  existingAppMaster.setModifiedBy(UUID.randomUUID().toString());
	            	  existingAppMaster.setModifiedMacAddr(UUID.randomUUID().toString());
	            	
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
	    
	    //////////////////////////////////////////////Assesment Year Mst  Start////////////////////////////
	    
	    //get all data from table
	    @GetMapping("/getAssessmentYearList")
	    public ResponseEntity<BaseResponse> getAssessmentYearList() {
	        BaseResponse response = new BaseResponse();
	        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        List<AssessmentYear> list = assessmentYearRepository.findAll();
	        response.setMessage("success");
	        response.setStatus(true);
	        response.setTotalDataCount(list.size());
	        response.setAssessmentYear(list);
	        return ResponseEntity.ok(response);
	    }
	    
	    
	    // Create New Data And Update
	    @PostMapping("/submitOrUpdateAssesmentYear")
	    public BaseResponse submitOrUpdateAssesmentYear(@RequestBody AssessmentYear assessmentYear, HttpServletRequest request) {
	        BaseResponse resultData = new BaseResponse();

	        // Check if guid is provided (indicating an update)
	        if (assessmentYear.getAssessmentYearGuid() == null || assessmentYear.getAssessmentYearGuid().isEmpty()) {
	            // Add new data
	        	assessmentYear.setCreaterIp(request.getRemoteAddr());
	        	assessmentYear.setAssessmentYearGuid(UUID.randomUUID().toString());
	        	assessmentYear.setCreatedDate(new Date());
	        	assessmentYear.setModifierIp(null);
				assessmentYear.setModifiedByGuid(null);
				assessmentYear.setModifiedDate(null);
//				assessmentYear.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
				//assessmentYear.setCreaterRemarks(userSessionParam.getUserFullName());
	        	//assessmentYear.setCreaterMacId(HttpSessionHelper.getMacAddress());	
				
	        } else {
	            // Update existing data
	        	AssessmentYear existingAssessmentYear = commonMasterService.getAssessmentYearById(assessmentYear.getAssessmentYearGuid());

	            if (existingAssessmentYear != null) {
	            	existingAssessmentYear.setAssessmentYearCode(!Util.isNullOrEmpty(assessmentYear.getAssessmentYearCode()) ? assessmentYear.getAssessmentYearCode().toUpperCase().trim() : null);
	            	existingAssessmentYear.setAssessmentYearDesc(!Util.isNullOrEmpty(assessmentYear.getAssessmentYearDesc()) ? assessmentYear.getAssessmentYearDesc().toUpperCase().trim() : null);
	            	existingAssessmentYear.setStartYear(assessmentYear.getStartYear());
	            	existingAssessmentYear.setEndYear(assessmentYear.getEndYear());

	            	existingAssessmentYear.setStartDate(assessmentYear.getStartDate());
	            	existingAssessmentYear.setEndDate(assessmentYear.getEndDate());
	            	existingAssessmentYear.setGeneralStartDate(assessmentYear.getGeneralStartDate());
	            	existingAssessmentYear.setGeneralEndDate(assessmentYear.getGeneralEndDate());
	            
	            	existingAssessmentYear.setIsActive(assessmentYear.getIsActive() != null ? assessmentYear.getIsActive() : existingAssessmentYear.getIsActive());

	            	existingAssessmentYear.setModifierIp(request.getRemoteAddr());
	            	existingAssessmentYear.setModifiedDate(new Date());
	                //existingAssessmentYear.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
	                //existingAssessmentYear.setModifierMacId(HttpSessionHelper.getMacAddress());
	            	assessmentYear = existingAssessmentYear; // Use the updated existing assessmentYear object
	            } else {
	                log.error("AssessmentYear not found");
	                resultData.setStatus(false);
	                resultData.setMessage("AssessmentYear not found");
	                return resultData;
	            }
	        }

	        // Validation
	        resultData = validator.validateAssessmentYear(assessmentYear);
	        if (resultData != null && !resultData.getStatus()) {
	            log.error("Validation failed: {}", resultData.getMessage());
	            return resultData;
	        }

	        // If validation passes, proceed to save or update
	        if (assessmentYear.getIsActive() == null) assessmentYear.setIsActive(false);
	        assessmentYear.setAssessmentYearCode(!Util.isNullOrEmpty(assessmentYear.getAssessmentYearCode()) ? assessmentYear.getAssessmentYearCode().toUpperCase().trim() : null);
	        assessmentYear.setAssessmentYearDesc(!Util.isNullOrEmpty(assessmentYear.getAssessmentYearDesc()) ? assessmentYear.getAssessmentYearDesc().toUpperCase().trim() : null);
	        assessmentYear.setStartDate(assessmentYear.getStartDate());
	        assessmentYear.setEndDate(assessmentYear.getEndDate());
	        assessmentYear.setGeneralStartDate(assessmentYear.getGeneralStartDate());
	        assessmentYear.setGeneralEndDate(assessmentYear.getGeneralEndDate());
	        assessmentYear.setStartYear(assessmentYear.getStartYear());
	        assessmentYear.setEndYear(assessmentYear.getEndYear());

	        try {
	            assessmentYearRepository.save(assessmentYear);
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
	    @GetMapping("/getAssessmentYearByGuid/{assessmentYearGuid}")
	    public ResponseEntity<AssessmentYear> getAssessmentYearByGuid(@PathVariable("assessmentYearGuid") String assessmentYearGuid) {
	    	AssessmentYear assessmentYear = assessmentYearRepository.findById(assessmentYearGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with assessmentYearGuid : " + assessmentYearGuid));
	        return new ResponseEntity<>(assessmentYear, HttpStatus.OK);
	    }
	    
	    //////////////////////////////////////////////Assesment Year Mst  End////////////////////////////
	  
//////////////////////////////////////////////AssociatedChargesInfo  Mst  Start////////////////////////////	    

	    //get all data from table
	    @GetMapping("/getAssociatedChargesInfoList")
	    public ResponseEntity<BaseResponse> getAssociatedChargesInfoList() {
	        BaseResponse response = new BaseResponse();
	        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        List<AssociatedChargesInfo> list = associatedChargesInfoRepository.findAll();
	        response.setMessage("success");
	        response.setStatus(true);
	        response.setTotalDataCount(list.size());
	        response.setAssociatedChargesInfo(list);
	        return ResponseEntity.ok(response);
	    }

	    // Create New Data And Update
//	    @PostMapping("/submitOrUpdateMstCountry")
//	    public BaseResponse submitOrUpdateMstCountry(@RequestBody GeoCountryMst country, HttpServletRequest request) {
//	        BaseResponse resultData = new BaseResponse();
//
//	        // Check if guid is provided (indicating an update)
//	        if (country.getCountryMstGuid() == null || country.getCountryMstGuid().isEmpty()) {
//	            // Add new data
//	            country.setCreaterIp(request.getRemoteAddr());
//	            country.setCountryMstGuid(UUID.randomUUID().toString());
//	            country.setCreatedDate(new Date());
////				country.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
////				country.setCreaterRemarks(userSessionParam.getUserFullName());
//	            //country.setCreaterMacId(HttpSessionHelper.getMacAddress());
//	            //obj.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
//	        } else {
//	            // Update existing data
//	            GeoCountryMst existingCountry = commonMasterService.getGeoCountryMstById(country.getCountryMstGuid());
//
//	            if (existingCountry != null) {
//	                existingCountry.setCountryMobileCode(!Util.isNullOrEmpty(country.getCountryMobileCode()) ? country.getCountryMobileCode().toUpperCase().trim() : null);
//	                existingCountry.setCountryCode(!Util.isNullOrEmpty(country.getCountryCode()) ? country.getCountryCode().toUpperCase().trim() : null);
//
//	                existingCountry.setCountryNameEn(!Util.isNullOrEmpty(country.getCountryNameEn()) ? country.getCountryNameEn().toUpperCase().trim() : null);
//	                existingCountry.setCountryNameHi(!Util.isNullOrEmpty(country.getCountryNameHi()) ? country.getCountryNameHi().trim() : null);
//	                existingCountry.setCountryNameRl(!Util.isNullOrEmpty(country.getCountryNameRl()) ? country.getCountryNameRl().trim() : null);
//	                existingCountry.setToDate(country.getToDate());
//	                existingCountry.setFromDate(country.getFromDate());
//	                existingCountry.setIsRecordActive(country.getIsRecordActive() != null ? country.getIsRecordActive() : existingCountry.getIsRecordActive());
//
//	                existingCountry.setModifierIp(request.getRemoteAddr());
//	                existingCountry.setModifiedDate(new Date());
//	                //existingCountry.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
//	                //existingCountry.setModifierMacId(HttpSessionHelper.getMacAddress());
//	                country = existingCountry; // Use the updated existing country object
//	            } else {
//	                log.error("Country not found");
//	                resultData.setStatus(false);
//	                resultData.setMessage("Country not found");
//	                return resultData;
//	            }
//	        }
//
//	        // Validation
//	        resultData = validator.validateMstCountry(country);
//	        if (resultData != null && !resultData.getStatus()) {
//	            log.error("Validation failed: {}", resultData.getMessage());
//	            return resultData;
//	        }
//
//	        // If validation passes, proceed to save or update
//	        if (country.getIsRecordActive() == null) country.setIsRecordActive(false);
//	        country.setCountryCode(!Util.isNullOrEmpty(country.getCountryCode()) ? country.getCountryCode().toUpperCase().trim() : null);
//	        country.setCountryNameEn(!Util.isNullOrEmpty(country.getCountryNameEn()) ? country.getCountryNameEn().toUpperCase().trim() : null);
//	        country.setCountryNameHi(!Util.isNullOrEmpty(country.getCountryNameHi()) ? country.getCountryNameHi().trim() : null);
//	        country.setCountryNameRl(!Util.isNullOrEmpty(country.getCountryNameRl()) ? country.getCountryNameRl().trim() : null);
//	        country.setCountryMobileCode(!Util.isNullOrEmpty(country.getCountryMobileCode()) ? country.getCountryMobileCode().trim() : null);
//
//	        try {
//	            geoCountryMstRepository.save(country);
//	            log.info("Record SaveOrUpdate Successfully");
//	            resultData.setStatus(true);
//	            resultData.setMessage("Record saved or updated successfully");
//	        } catch (Exception e) {
//	            log.error("Error saving or updating record: {}", e.getMessage());
//	            resultData.setStatus(false);
//	            resultData.setMessage("Error saving or updating record: " + e.getMessage());
//	        }
//
//	        return resultData;
//	    }

	    
	 


	    //get data by id
//	    @GetMapping("/getMstCountryByGuid/{countryMstGuid}")
//	    public ResponseEntity<GeoCountryMst> getMstCountryByGuid(@PathVariable("countryMstGuid") String countryMstGuid) {
//	        GeoCountryMst geoCountry = geoCountryMstRepository.findById(countryMstGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with countryMstGuid : " + countryMstGuid));
//	        return new ResponseEntity<>(geoCountry, HttpStatus.OK);
//	    }
	    

	  


	    
//////////////////////////////////////////////AssociatedChargesInfo Mst  End////////////////////////////

}
