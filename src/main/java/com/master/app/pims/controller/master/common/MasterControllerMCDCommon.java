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
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.EducationLevel;
import com.master.app.pims.entities.schemas.mst.MstChargeDetails;
import com.master.app.pims.entities.schemas.mst.OccupationType;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.mst.SubmittedRequestStage;
import com.master.app.pims.entities.schemas.mst.UnitArea;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.ApplicationMasterRepository;
import com.master.app.pims.repositories.AssessmentYearRepository;
import com.master.app.pims.repositories.AssociatedChargesInfoRepository;
import com.master.app.pims.repositories.mst.DocsSubmissionInfoRepository;
import com.master.app.pims.repositories.mst.EducationLevelRepository;
import com.master.app.pims.repositories.mst.MstChargeDetailsRepository;
import com.master.app.pims.repositories.mst.OccupationTypeRepository;
import com.master.app.pims.repositories.mst.RequestSubmissionTypeRepository;
import com.master.app.pims.repositories.mst.SubmittedRequestStageRepository;
import com.master.app.pims.repositories.mst.UnitAreaRepository;
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
	   
	   @Autowired
	    private DocsSubmissionInfoRepository docsSubmissionInfoRepository;
	   
	   @Autowired
	    private RequestSubmissionTypeRepository requestSubmissionTypeRepository;
	   
	   @Autowired
	    private SubmittedRequestStageRepository submittedRequestStageRepository;
	   
	   @Autowired
	    private UnitAreaRepository unitAreaRepository;
	   
	   @Autowired
	    private MstChargeDetailsRepository mstChargeDetailsRepository;
	   
	   @Autowired
	    private OccupationTypeRepository occupationTypeRepository;
	   
	   @Autowired
	    private EducationLevelRepository educationLevelRepository;
	   
	  
	  
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
				assessmentYear.setCreatedByGuid(request.getRemoteAddr());
//				assessmentYear.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
				//assessmentYear.setCreaterRemarks(userSessionParam.getUserFullName());
	        	//assessmentYear.setCreaterMacId(HttpSessionHelper.getMacAddress());	
				 if (assessmentYear.getIsActive() == null)
					 assessmentYear.setIsActive(false);
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
	            	
	            	  if (existingAssessmentYear.getIsActive() == null)
	            		  existingAssessmentYear.setIsActive(false);
	            	
	            	existingAssessmentYear.setModifiedByGuid(UUID.randomUUID().toString());
	            	existingAssessmentYear.setModifierMacId(UUID.randomUUID().toString());
	            
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
	    @PostMapping("/submitOrUpdateAssociatedChargesInfo")
	    public BaseResponse submitOrUpdateAssociatedChargesInfo(@RequestBody AssociatedChargesInfo associatedChargesInfo, HttpServletRequest request) {
	        BaseResponse resultData = new BaseResponse();

	        // Check if guid is provided (indicating an update)
	        if (associatedChargesInfo.getAssociatedChargesInfoGuid() == null || associatedChargesInfo.getAssociatedChargesInfoGuid().isEmpty()) {
	            // Add new data
	        	associatedChargesInfo.setCreaterIp(request.getRemoteAddr());
	        	associatedChargesInfo.setAssociatedChargesInfoGuid(UUID.randomUUID().toString());
	        	associatedChargesInfo.setCreatedDate(new Date());
	        	associatedChargesInfo.setModifierIp(null);
	        	associatedChargesInfo.setModifiedByGuid(null);
	        	associatedChargesInfo.setModifiedDate(null);
	        	associatedChargesInfo.setCreatedByGuid(request.getRemoteAddr());

	            if (associatedChargesInfo.getIsActive() == null)
	            	associatedChargesInfo.setIsActive(false);
//				colonyCategory.setCreaterRemarks(userSessionParam.getUserFullName());
	            //colonyCategory.setCreaterMacId(HttpSessionHelper.getMacAddress());
	            //colonyCategory.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
	        		
	        	
	        } else {
	            // Update existing data
	        	AssociatedChargesInfo existingChargeInfo = commonMasterService.getAssociatedChargesInfoById(associatedChargesInfo.getAssociatedChargesInfoGuid());

	            if (existingChargeInfo != null) {
	            	existingChargeInfo.setChargeCode(!Util.isNullOrEmpty(associatedChargesInfo.getChargeCode()) ? associatedChargesInfo.getChargeCode().toUpperCase().trim() : null);
	            	existingChargeInfo.setChargeNameEn(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameEn()) ? associatedChargesInfo.getChargeNameEn().toUpperCase().trim() : null);

	            	existingChargeInfo.setChargeNameHi(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameHi()) ? associatedChargesInfo.getChargeNameHi().toUpperCase().trim() : null);
	            	existingChargeInfo.setChargeNameRl(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameRl()) ? associatedChargesInfo.getChargeNameRl().trim() : null);
	            	existingChargeInfo.setChargeInfoDesc(!Util.isNullOrEmpty(associatedChargesInfo.getChargeInfoDesc()) ? associatedChargesInfo.getChargeInfoDesc().trim() : null);

	             
	            	existingChargeInfo.setIsActive(associatedChargesInfo.getIsActive() != null ? associatedChargesInfo.getIsActive() : existingChargeInfo.getIsActive());

	            	existingChargeInfo.setModifierIp(request.getRemoteAddr());
	            	existingChargeInfo.setModifiedDate(new Date());
	                if (existingChargeInfo.getIsActive() == null)
	                	existingChargeInfo.setIsActive(false);
	                // for now setting some dummy value to test
	                existingChargeInfo.setModifiedByGuid(UUID.randomUUID().toString());
	                existingChargeInfo.setModifierMacId(UUID.randomUUID().toString());
	                associatedChargesInfo = existingChargeInfo; // Use the updated existing country object
	            } else {
	                log.error("Associated Charges Info not found");
	                resultData.setStatus(false);
	                resultData.setMessage("Associated Charges Info not found");
	                return resultData;
	            }
	        }

	        // Validation
	        resultData = validator.validateAssociatedChargesInfo(associatedChargesInfo);
	        if (resultData != null && !resultData.getStatus()) {
	            log.error("Validation failed: {}", resultData.getMessage());
	            return resultData;
	        }

	        // If validation passes, proceed to save or update
	        if (associatedChargesInfo.getIsActive() == null) associatedChargesInfo.setIsActive(false);
	        associatedChargesInfo.setChargeCode(!Util.isNullOrEmpty(associatedChargesInfo.getChargeCode()) ? associatedChargesInfo.getChargeCode().toUpperCase().trim() : null);
	        associatedChargesInfo.setChargeNameEn(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameEn()) ? associatedChargesInfo.getChargeNameEn().toUpperCase().trim() : null);
	        associatedChargesInfo.setChargeNameHi(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameHi()) ? associatedChargesInfo.getChargeNameHi().trim() : null);
	        associatedChargesInfo.setChargeNameRl(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameRl()) ? associatedChargesInfo.getChargeNameRl().trim() : null);
	        associatedChargesInfo.setChargeInfoDesc(!Util.isNullOrEmpty(associatedChargesInfo.getChargeInfoDesc()) ? associatedChargesInfo.getChargeInfoDesc().trim() : null);


	     

	        try {
	        	associatedChargesInfoRepository.save(associatedChargesInfo);
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
	    @GetMapping("/getAssociatedChargesInfoByGuid/{associatedChargesInfoGuid}")
	    public ResponseEntity<AssociatedChargesInfo> getAssociatedChargesInfoByGuid(@PathVariable("associatedChargesInfoGuid") String associatedChargesInfoGuid) {
	    	AssociatedChargesInfo associatedChargesInfo = associatedChargesInfoRepository.findById(associatedChargesInfoGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with associatedChargesInfoGuid : " + associatedChargesInfoGuid));
	        return new ResponseEntity<>(associatedChargesInfo, HttpStatus.OK);
	    }

	   

	    
//////////////////////////////////////////////AssociatedChargesInfo Mst  End////////////////////////////
	    
//////////////////////////////////////////// DocSubmissionInfo Start //////////////////////////
	    
	    //get all data from table
	    @GetMapping("/getDocsSubmissionInfoList")
	    public ResponseEntity<BaseResponse> getDocsSubmissionInfoList() {
	        BaseResponse response = new BaseResponse();
	        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        List<DocsSubmissionInfo> list = docsSubmissionInfoRepository.findAll();
	        response.setMessage("success");
	        response.setStatus(true);
	        response.setTotalDataCount(list.size());
	        response.setDocsSubmissionInfo(list);
	        return ResponseEntity.ok(response);
	    }
	    
	    // Create New Data And Update
	    @PostMapping("/submitOrUpdateDocsSubmissionInfo")
	    public BaseResponse submitOrUpdateDocsSubmissionInfo(@RequestBody DocsSubmissionInfo docsSubmissionInfo, HttpServletRequest request) {
	        BaseResponse resultData = new BaseResponse();

	        // Check if guid is provided (indicating an update)
	        if (docsSubmissionInfo.getDocsSubmissionInfoGuid() == null || docsSubmissionInfo.getDocsSubmissionInfoGuid().isEmpty()) {
	            // Add new data
	        	docsSubmissionInfo.setCreaterIp(request.getRemoteAddr());
	        	docsSubmissionInfo.setDocsSubmissionInfoGuid(UUID.randomUUID().toString());
	        	docsSubmissionInfo.setCreatedDate(new Date());
	        	docsSubmissionInfo.setModifierIp(null);
	        	docsSubmissionInfo.setModifiedByGuid(null);
	        	docsSubmissionInfo.setModifiedDate(null);
	        	docsSubmissionInfo.setCreatedByGuid(request.getRemoteAddr());

	            if (docsSubmissionInfo.getIsActive() == null)
	            	docsSubmissionInfo.setIsActive(false);
//				docsSubmissionInfo.setCreaterRemarks(userSessionParam.getUserFullName());
	            //docsSubmissionInfo.setCreaterMacId(HttpSessionHelper.getMacAddress());
	            //docsSubmissionInfo.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
	        		
	        	
	        } else {
	            // Update existing data
	        	DocsSubmissionInfo existingDocsInfo = commonMasterService.getDocsSubmissionInfoById(docsSubmissionInfo.getDocsSubmissionInfoGuid());

	            if (existingDocsInfo != null) {
	            	existingDocsInfo.setDocsCode(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsCode()) ? docsSubmissionInfo.getDocsCode().toUpperCase().trim() : null);
	            	existingDocsInfo.setDocsNameEn(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameEn()) ? docsSubmissionInfo.getDocsNameEn().toUpperCase().trim() : null);

	            	existingDocsInfo.setDocsNameHi(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameHi()) ? docsSubmissionInfo.getDocsNameHi().toUpperCase().trim() : null);
	            	existingDocsInfo.setDocsNameRl(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameRl()) ? docsSubmissionInfo.getDocsNameRl().trim() : null);
	            	existingDocsInfo.setDocsInfoDesc(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsInfoDesc()) ? docsSubmissionInfo.getDocsInfoDesc().trim() : null);

	             
	            	existingDocsInfo.setIsActive(docsSubmissionInfo.getIsActive() != null ? docsSubmissionInfo.getIsActive() : existingDocsInfo.getIsActive());

	            	existingDocsInfo.setModifierIp(request.getRemoteAddr());
	            	existingDocsInfo.setModifiedDate(new Date());
	                if (existingDocsInfo.getIsActive() == null)
	                	existingDocsInfo.setIsActive(false);
	                // for now setting some dummy value to test
	                existingDocsInfo.setModifiedByGuid(UUID.randomUUID().toString());
	                existingDocsInfo.setModifierMacId(UUID.randomUUID().toString());
	                docsSubmissionInfo = existingDocsInfo; // Use the updated existing country object
	            } else {
	                log.error("Docs Submission Info not found");
	                resultData.setStatus(false);
	                resultData.setMessage("Docs Submission Info not found");
	                return resultData;
	            }
	        }

	        // Validation
	        resultData = validator.validateDocsSubmissionInfo(docsSubmissionInfo);
	        if (resultData != null && !resultData.getStatus()) {
	            log.error("Validation failed: {}", resultData.getMessage());
	            return resultData;
	        }

	        // If validation passes, proceed to save or update
	        if (docsSubmissionInfo.getIsActive() == null) docsSubmissionInfo.setIsActive(false);
	        docsSubmissionInfo.setDocsCode(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsCode()) ? docsSubmissionInfo.getDocsCode().toUpperCase().trim() : null);
	        docsSubmissionInfo.setDocsNameEn(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameEn()) ? docsSubmissionInfo.getDocsNameEn().toUpperCase().trim() : null);
	        docsSubmissionInfo.setDocsNameHi(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameHi()) ? docsSubmissionInfo.getDocsNameHi().trim() : null);
	        docsSubmissionInfo.setDocsNameRl(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameRl()) ? docsSubmissionInfo.getDocsNameRl().trim() : null);
	        docsSubmissionInfo.setDocsInfoDesc(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsInfoDesc()) ? docsSubmissionInfo.getDocsInfoDesc().trim() : null);


	     

	        try {
	            docsSubmissionInfoRepository.save(docsSubmissionInfo);
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
	    @GetMapping("/getDocsSubmissionInfoByGuid/{docsSubmissionInfoGuid}")
	    public ResponseEntity<DocsSubmissionInfo> getDocsSubmissionInfoByGuid(@PathVariable("docsSubmissionInfoGuid") String docsSubmissionInfoGuid) {
	    	DocsSubmissionInfo docsInfo = docsSubmissionInfoRepository.findById(docsSubmissionInfoGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with docsSubmissionInfoGuid : " + docsSubmissionInfoGuid));
	        return new ResponseEntity<>(docsInfo, HttpStatus.OK);
	    }
	    
	    
////////////////////////////////////////////DocSubmissionInfo End //////////////////////////

////////////////////////////////////////////RequestSubmissionType Start //////////////////////////
	    
//get all data from table
@GetMapping("/getRequestSubmissionTypeList")
public ResponseEntity<BaseResponse> getRequestSubmissionTypeList() {
BaseResponse response = new BaseResponse();
// Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RequestSubmissionType> list = requestSubmissionTypeRepository.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRequestSubmissionType(list);
return ResponseEntity.ok(response);
}

// Create New Data And Update
@PostMapping("/submitOrUpdatRequestSubmissionType")
public BaseResponse submitOrUpdatRequestSubmissionType(@RequestBody RequestSubmissionType requestSubmissionType, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

// Check if guid is provided (indicating an update)
if (requestSubmissionType.getRequestSubmissionTypeGuid() == null || requestSubmissionType.getRequestSubmissionTypeGuid().isEmpty()) {
// Add new data
	requestSubmissionType.setCreaterIp(request.getRemoteAddr());
	requestSubmissionType.setRequestSubmissionTypeGuid(UUID.randomUUID().toString());
	requestSubmissionType.setCreatedDate(new Date());
	requestSubmissionType.setModifierIp(null);
	requestSubmissionType.setModifiedByGuid(null);
	requestSubmissionType.setModifiedDate(null);
	requestSubmissionType.setCreatedByGuid(request.getRemoteAddr());

if (requestSubmissionType.getIsActive() == null)
	requestSubmissionType.setIsActive(false);
//docsSubmissionInfo.setCreaterRemarks(userSessionParam.getUserFullName());
//docsSubmissionInfo.setCreaterMacId(HttpSessionHelper.getMacAddress());
//docsSubmissionInfo.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));


} else {
// Update existing data
	RequestSubmissionType existingRequestType = commonMasterService.getRequestSubmissionTypeById(requestSubmissionType.getRequestSubmissionTypeGuid());

if (existingRequestType != null) {
	existingRequestType.setRequestSubmissionTypeCode(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeCode()) ? requestSubmissionType.getRequestSubmissionTypeCode().toUpperCase().trim() : null);
	existingRequestType.setRequestSubmissionTypeNameEn(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameEn()) ? requestSubmissionType.getRequestSubmissionTypeNameEn().toUpperCase().trim() : null);

	existingRequestType.setRequestSubmissionTypeNameHi(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameHi()) ? requestSubmissionType.getRequestSubmissionTypeNameHi().toUpperCase().trim() : null);
	existingRequestType.setRequestSubmissionTypeNameRl(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameRl()) ? requestSubmissionType.getRequestSubmissionTypeNameRl().trim() : null);
	existingRequestType.setRequestSubmissionTypeDesc(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeDesc()) ? requestSubmissionType.getRequestSubmissionTypeDesc().trim() : null);


	existingRequestType.setIsActive(requestSubmissionType.getIsActive() != null ? requestSubmissionType.getIsActive() : existingRequestType.getIsActive());

	existingRequestType.setModifierIp(request.getRemoteAddr());
	existingRequestType.setModifiedDate(new Date());
if (existingRequestType.getIsActive() == null)
	existingRequestType.setIsActive(false);
// for now setting some dummy value to test
existingRequestType.setModifiedByGuid(UUID.randomUUID().toString());
existingRequestType.setModifierMacId(UUID.randomUUID().toString());
requestSubmissionType = existingRequestType; // Use the updated existing country object
} else {
log.error("Request Submission Type not found");
resultData.setStatus(false);
resultData.setMessage("Request Submission Type not found");
return resultData;
}
}

// Validation
resultData = validator.validateRequestSubmissionType(requestSubmissionType);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

// If validation passes, proceed to save or update
if (requestSubmissionType.getIsActive() == null) requestSubmissionType.setIsActive(false);
requestSubmissionType.setRequestSubmissionTypeCode(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeCode()) ? requestSubmissionType.getRequestSubmissionTypeCode().toUpperCase().trim() : null);
requestSubmissionType.setRequestSubmissionTypeNameEn(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameEn()) ? requestSubmissionType.getRequestSubmissionTypeNameEn().toUpperCase().trim() : null);
requestSubmissionType.setRequestSubmissionTypeNameHi(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameHi()) ? requestSubmissionType.getRequestSubmissionTypeNameHi().trim() : null);
requestSubmissionType.setRequestSubmissionTypeNameRl(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameRl()) ? requestSubmissionType.getRequestSubmissionTypeNameRl().trim() : null);
requestSubmissionType.setRequestSubmissionTypeDesc(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeDesc()) ? requestSubmissionType.getRequestSubmissionTypeDesc().trim() : null);




try {
	requestSubmissionTypeRepository.save(requestSubmissionType);
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
@GetMapping("/getRequestSubmissionTypeGuidGuid/{requestSubmissionTypeGuid}")
public ResponseEntity<RequestSubmissionType> getRequestSubmissionTypeGuidGuid(@PathVariable("requestSubmissionTypeGuid") String requestSubmissionTypeGuid) {
	RequestSubmissionType reqType = requestSubmissionTypeRepository.findById(requestSubmissionTypeGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with requestSubmissionTypeGuid : " + requestSubmissionTypeGuid));
return new ResponseEntity<>(reqType, HttpStatus.OK);
}


////////////////////////////////////////////RequestSubmissionType End //////////////////////////

////////////////////////////////////////////SubmittedRequestStage Start //////////////////////////

//get all data from table
@GetMapping("/getSubmittedRequestStageList")
public ResponseEntity<BaseResponse> getSubmittedRequestStageList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<SubmittedRequestStage> list = submittedRequestStageRepository.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setSubmittedRequestStage(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitOrUpdateSubmittedRequestStage")
public BaseResponse submitSubmittedRequestStage(@RequestBody SubmittedRequestStage submittedRequestStage, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (submittedRequestStage.getSubmittedRequestStageGuid() == null || submittedRequestStage.getSubmittedRequestStageGuid().isEmpty()) {
//Add new data
	submittedRequestStage.setCreaterIp(request.getRemoteAddr());
	submittedRequestStage.setSubmittedRequestStageGuid(UUID.randomUUID().toString());
	submittedRequestStage.setCreatedDate(new Date());
	submittedRequestStage.setModifierIp(null);
	submittedRequestStage.setModifiedByGuid(null);
	submittedRequestStage.setModifiedDate(null);
	submittedRequestStage.setCreatedByGuid(request.getRemoteAddr());

if (submittedRequestStage.getIsActive() == null)
	submittedRequestStage.setIsActive(false);
//docsSubmissionInfo.setCreaterRemarks(userSessionParam.getUserFullName());
//docsSubmissionInfo.setCreaterMacId(HttpSessionHelper.getMacAddress());
//docsSubmissionInfo.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));


} else {
//Update existing data
SubmittedRequestStage existingSubmittedRequest = commonMasterService.getSubmittedRequestStageById(submittedRequestStage.getSubmittedRequestStageGuid());

if (existingSubmittedRequest != null) {
	existingSubmittedRequest.setSubmittedRequestStageCode(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageCode()) ? submittedRequestStage.getSubmittedRequestStageCode().toUpperCase().trim() : null);
	existingSubmittedRequest.setSubmittedRequestStageNameEn(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameEn()) ? submittedRequestStage.getSubmittedRequestStageNameEn().toUpperCase().trim() : null);

	existingSubmittedRequest.setSubmittedRequestStageNameHi(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameHi()) ? submittedRequestStage.getSubmittedRequestStageNameHi().toUpperCase().trim() : null);
	existingSubmittedRequest.setSubmittedRequestStageNameRl(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameRl()) ? submittedRequestStage.getSubmittedRequestStageNameRl().trim() : null);
	existingSubmittedRequest.setSubmittedRequestStageDesc(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageDesc()) ? submittedRequestStage.getSubmittedRequestStageDesc().trim() : null);


	existingSubmittedRequest.setIsActive(submittedRequestStage.getIsActive() != null ? submittedRequestStage.getIsActive() : existingSubmittedRequest.getIsActive());

	existingSubmittedRequest.setModifierIp(request.getRemoteAddr());
	existingSubmittedRequest.setModifiedDate(new Date());
if (existingSubmittedRequest.getIsActive() == null)
	existingSubmittedRequest.setIsActive(false);
//for now setting some dummy value to test
existingSubmittedRequest.setModifiedByGuid(UUID.randomUUID().toString());
existingSubmittedRequest.setModifierMacId(UUID.randomUUID().toString());
submittedRequestStage = existingSubmittedRequest; // Use the updated existing country object
} else {
log.error("Submitted Request Stage not found");
resultData.setStatus(false);
resultData.setMessage("Submitted Request Stage not found");
return resultData;
}
}

//Validation
resultData = validator.validateSubmittedRequestStage(submittedRequestStage);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (submittedRequestStage.getIsActive() == null) submittedRequestStage.setIsActive(false);
submittedRequestStage.setSubmittedRequestStageCode(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageCode()) ? submittedRequestStage.getSubmittedRequestStageCode().toUpperCase().trim() : null);
submittedRequestStage.setSubmittedRequestStageNameEn(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameEn()) ? submittedRequestStage.getSubmittedRequestStageNameEn().toUpperCase().trim() : null);
submittedRequestStage.setSubmittedRequestStageNameHi(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameHi()) ? submittedRequestStage.getSubmittedRequestStageNameHi().trim() : null);
submittedRequestStage.setSubmittedRequestStageNameRl(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameRl()) ? submittedRequestStage.getSubmittedRequestStageNameRl().trim() : null);
submittedRequestStage.setSubmittedRequestStageDesc(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageDesc()) ? submittedRequestStage.getSubmittedRequestStageDesc().trim() : null);




try {
submittedRequestStageRepository.save(submittedRequestStage);
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
@GetMapping("/getSubmittedRequestStageByGuid/{submittedRequestStageGuid}")
public ResponseEntity<SubmittedRequestStage> getSubmittedRequestStageByGuid(@PathVariable("submittedRequestStageGuid") String submittedRequestStageGuid) {
	SubmittedRequestStage reqStage = submittedRequestStageRepository.findById(submittedRequestStageGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with submittedRequestStageGuid : " + submittedRequestStageGuid));
return new ResponseEntity<>(reqStage, HttpStatus.OK);
}


////////////////////////////////////////////SubmittedRequestStage End //////////////////////////

////////////////////////////////////////////Unit Area Start //////////////////////////

//get all data from table
@GetMapping("/getUnitAreaList")
public ResponseEntity<BaseResponse> getUnitAreaList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<UnitArea> list = unitAreaRepository.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setUnitArea(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitOrUpdateUnitArea")
public BaseResponse submitOrUpdateUnitArea(@RequestBody UnitArea unitArea, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (unitArea.getUnitAreaGuid() == null || unitArea.getUnitAreaGuid().isEmpty()) {
//Add new data
	unitArea.setCreaterIp(request.getRemoteAddr());
	unitArea.setUnitAreaGuid(UUID.randomUUID().toString());
	unitArea.setCreatedDate(new Date());
	unitArea.setModifierIp(null);
	unitArea.setModifiedByGuid(null);
	unitArea.setModifiedDate(null);
	unitArea.setCreatedByGuid(request.getRemoteAddr());

if (unitArea.getIsActive() == null)
	unitArea.setIsActive(false);
//unitArea.setCreaterRemarks(userSessionParam.getUserFullName());
//unitArea.setCreaterMacId(HttpSessionHelper.getMacAddress());
//unitArea.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));


} else {
//Update existing data
	UnitArea existingUnitArea = commonMasterService.getUnitAreaById(unitArea.getUnitAreaGuid());

if (existingUnitArea != null) {
	existingUnitArea.setUnitAreaCode(!Util.isNullOrEmpty(unitArea.getUnitAreaCode()) ? unitArea.getUnitAreaCode().toUpperCase().trim() : null);
	existingUnitArea.setUnitAreaNameEn(!Util.isNullOrEmpty(unitArea.getUnitAreaNameEn()) ? unitArea.getUnitAreaNameEn().toUpperCase().trim() : null);

	existingUnitArea.setUnitAreaNameHi(!Util.isNullOrEmpty(unitArea.getUnitAreaNameHi()) ? unitArea.getUnitAreaNameHi().toUpperCase().trim() : null);
	existingUnitArea.setUnitAreaNameRl(!Util.isNullOrEmpty(unitArea.getUnitAreaNameRl()) ? unitArea.getUnitAreaNameRl().trim() : null);
	existingUnitArea.setUnitAreaDesc(!Util.isNullOrEmpty(unitArea.getUnitAreaDesc()) ? unitArea.getUnitAreaDesc().trim() : null);


	existingUnitArea.setIsActive(unitArea.getIsActive() != null ? unitArea.getIsActive() : existingUnitArea.getIsActive());

	existingUnitArea.setModifierIp(request.getRemoteAddr());
	existingUnitArea.setModifiedDate(new Date());
if (existingUnitArea.getIsActive() == null)
	existingUnitArea.setIsActive(false);
//for now setting some dummy value to test
existingUnitArea.setModifiedByGuid(UUID.randomUUID().toString());
existingUnitArea.setModifierMacId(UUID.randomUUID().toString());
unitArea = existingUnitArea; // Use the updated existing country object
} else {
log.error("Unit Area not found");
resultData.setStatus(false);
resultData.setMessage("Unit Area not found");
return resultData;
}
}

//Validation
resultData = validator.validateUnitArea(unitArea);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (unitArea.getIsActive() == null) unitArea.setIsActive(false);
unitArea.setUnitAreaCode(!Util.isNullOrEmpty(unitArea.getUnitAreaCode()) ? unitArea.getUnitAreaCode().toUpperCase().trim() : null);
unitArea.setUnitAreaNameEn(!Util.isNullOrEmpty(unitArea.getUnitAreaNameEn()) ? unitArea.getUnitAreaNameEn().toUpperCase().trim() : null);
unitArea.setUnitAreaNameHi(!Util.isNullOrEmpty(unitArea.getUnitAreaNameHi()) ? unitArea.getUnitAreaNameHi().trim() : null);
unitArea.setUnitAreaNameRl(!Util.isNullOrEmpty(unitArea.getUnitAreaNameRl()) ? unitArea.getUnitAreaNameRl().trim() : null);
unitArea.setUnitAreaDesc(!Util.isNullOrEmpty(unitArea.getUnitAreaDesc()) ? unitArea.getUnitAreaDesc().trim() : null);




try {
unitAreaRepository.save(unitArea);
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
@GetMapping("/getUnitAreaByGuid/{unitAreaGuid}")
public ResponseEntity<UnitArea> getUnitAreaByGuid(@PathVariable("unitAreaGuid") String unitAreaGuid) {
	UnitArea unitArea = unitAreaRepository.findById(unitAreaGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with unitAreaGuid : " + unitAreaGuid));
return new ResponseEntity<>(unitArea, HttpStatus.OK);
}


////////////////////////////////////////////Unit Area End //////////////////////////

////////////////////////////////////////////MstChargeDetails Start //////////////////////////

//get all data from table
@GetMapping("/getMstChargeDetailsList")
public ResponseEntity<BaseResponse> getMstChargeDetailsList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<MstChargeDetails> list = mstChargeDetailsRepository.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setMstChargeDetails(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitOrUpdateMstChargeDetails")
public BaseResponse submitOrUpdateMstChargeDetails(@RequestBody MstChargeDetails mstChargeDetails, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (mstChargeDetails.getChargeDetailsGuid() == null || mstChargeDetails.getChargeDetailsGuid().isEmpty()) {
//Add new data
	mstChargeDetails.setCreatedIpAddr(request.getRemoteAddr());
	mstChargeDetails.setChargeDetailsGuid(UUID.randomUUID().toString());
	mstChargeDetails.setCreatedDate(new Date());
	mstChargeDetails.setModifiedIpAddr(null);
	mstChargeDetails.setModifiedBy(null);
	mstChargeDetails.setModifiedDate(null);
	mstChargeDetails.setCreatedBy(request.getRemoteAddr());

if (mstChargeDetails.getIsActive() == null)
	mstChargeDetails.setIsActive(false);
//mstChargeDetails.setCreatedRemarks(userSessionParam.getUserFullName());
//mstChargeDetails.setCreaterMacId(HttpSessionHelper.getMacAddress());
//mstChargeDetails.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//mstChargeDetails.setCreatedMacAddr(HttpSessionHelper.getMacAddress());


} else {
//Update existing data
	MstChargeDetails existingChargeDetail = commonMasterService.getMstChargeDetailsById(mstChargeDetails.getChargeDetailsGuid());

if (existingChargeDetail != null) {
	existingChargeDetail.setChargeDetailsCode(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsCode()) ? mstChargeDetails.getChargeDetailsCode().toUpperCase().trim() : null);
	existingChargeDetail.setChargeDetailsNameEn(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameEn()) ? mstChargeDetails.getChargeDetailsNameEn().toUpperCase().trim() : null);

	existingChargeDetail.setChargeDetailsNameHi(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameHi()) ? mstChargeDetails.getChargeDetailsNameHi().toUpperCase().trim() : null);
	existingChargeDetail.setChargeDetailsNameRl(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameRl()) ? mstChargeDetails.getChargeDetailsNameRl().trim() : null);
	existingChargeDetail.setChargeDetailsDesc(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsDesc()) ? mstChargeDetails.getChargeDetailsDesc().trim() : null);


	existingChargeDetail.setIsActive(mstChargeDetails.getIsActive() != null ? mstChargeDetails.getIsActive() : existingChargeDetail.getIsActive());

	existingChargeDetail.setModifiedIpAddr(request.getRemoteAddr());
	existingChargeDetail.setModifiedDate(new Date());
if (existingChargeDetail.getIsActive() == null)
	existingChargeDetail.setIsActive(false);
//for now setting some dummy value to test
existingChargeDetail.setModifiedBy(UUID.randomUUID().toString());
existingChargeDetail.setModifiedMacAddr(UUID.randomUUID().toString());
mstChargeDetails = existingChargeDetail; // Use the updated existing country object
} else {
log.error("Charge Details not found");
resultData.setStatus(false);
resultData.setMessage("Charge Details not found");
return resultData;
}
}

//Validation
resultData = validator.validateMstChargeDetails(mstChargeDetails);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (mstChargeDetails.getIsActive() == null) mstChargeDetails.setIsActive(false);
mstChargeDetails.setChargeDetailsCode(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsCode()) ? mstChargeDetails.getChargeDetailsCode().toUpperCase().trim() : null);
mstChargeDetails.setChargeDetailsNameEn(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameEn()) ? mstChargeDetails.getChargeDetailsNameEn().toUpperCase().trim() : null);
mstChargeDetails.setChargeDetailsNameHi(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameHi()) ? mstChargeDetails.getChargeDetailsNameHi().trim() : null);
mstChargeDetails.setChargeDetailsNameRl(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameRl()) ? mstChargeDetails.getChargeDetailsNameRl().trim() : null);
mstChargeDetails.setChargeDetailsDesc(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsDesc()) ? mstChargeDetails.getChargeDetailsDesc().trim() : null);




try {
mstChargeDetailsRepository.save(mstChargeDetails);
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
@GetMapping("/getMstChargeDetailsByGuid/{chargeDetailsGuid}")
public ResponseEntity<MstChargeDetails> getMstChargeDetailsByGuid(@PathVariable("chargeDetailsGuid") String chargeDetailsGuid) {
	MstChargeDetails mstChargeDetails = mstChargeDetailsRepository.findById(chargeDetailsGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with chargeDetailsGuid : " + chargeDetailsGuid));
return new ResponseEntity<>(mstChargeDetails, HttpStatus.OK);
}


////////////////////////////////////////////MstChargeDetails End //////////////////////////

////////////////////////////////////////////OccupationType Start //////////////////////////

//get all data from table
@GetMapping("/getOccupationTypeList")
public ResponseEntity<BaseResponse> getOccupationTypeList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<OccupationType> list = occupationTypeRepository.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setOccupationType(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitOrUpdateOccupationType")
public BaseResponse submitOrUpdateOccupationType(@RequestBody OccupationType occupationType, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (occupationType.getOccupationGuid() == null || occupationType.getOccupationGuid().isEmpty()) {
//Add new data
	occupationType.setCreatedIpAddr(request.getRemoteAddr());
	occupationType.setOccupationGuid(UUID.randomUUID().toString());
	occupationType.setCreatedDate(new Date());
	occupationType.setModifiedIpAddr(null);
	occupationType.setModifiedBy(null);
	occupationType.setModifiedDate(null);
	occupationType.setCreatedBy(request.getRemoteAddr());

if (occupationType.getIsActive() == null)
	occupationType.setIsActive(false);
//occupationType.setCreatedRemarks(userSessionParam.getUserFullName());
//occupationType.setCreaterMacId(HttpSessionHelper.getMacAddress());
//occupationType.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//occupationType.setCreatedMacAddr(HttpSessionHelper.getMacAddress());


} else {
//Update existing data
	OccupationType existingOccupationType = commonMasterService.getOccupationTypeById(occupationType.getOccupationGuid());

if (existingOccupationType != null) {
	existingOccupationType.setOccupationCode(!Util.isNullOrEmpty(occupationType.getOccupationCode()) ? occupationType.getOccupationCode().toUpperCase().trim() : null);
	existingOccupationType.setOccupationNameEn(!Util.isNullOrEmpty(occupationType.getOccupationNameEn()) ? occupationType.getOccupationNameEn().toUpperCase().trim() : null);

	existingOccupationType.setOccupationNameHi(!Util.isNullOrEmpty(occupationType.getOccupationNameHi()) ? occupationType.getOccupationNameHi().toUpperCase().trim() : null);
existingOccupationType.setOccupationNameRl(!Util.isNullOrEmpty(occupationType.getOccupationNameRl()) ? occupationType.getOccupationNameRl().trim() : null);
existingOccupationType.setOccupationDesc(!Util.isNullOrEmpty(occupationType.getOccupationDesc()) ? occupationType.getOccupationDesc().trim() : null);


existingOccupationType.setIsActive(occupationType.getIsActive() != null ? occupationType.getIsActive() : existingOccupationType.getIsActive());

existingOccupationType.setModifiedIpAddr(request.getRemoteAddr());
existingOccupationType.setModifiedDate(new Date());
if (existingOccupationType.getIsActive() == null)
	existingOccupationType.setIsActive(false);
//for now setting some dummy value to test
existingOccupationType.setModifiedBy(UUID.randomUUID().toString());
existingOccupationType.setModifiedMacAddr(UUID.randomUUID().toString());
occupationType = existingOccupationType; // Use the updated existing country object
} else {
log.error("Occupation Type  not found");
resultData.setStatus(false);
resultData.setMessage("Occupation Type not found");
return resultData;
}
}

//Validation
resultData = validator.validateOccupationType(occupationType);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (occupationType.getIsActive() == null) occupationType.setIsActive(false);
occupationType.setOccupationCode(!Util.isNullOrEmpty(occupationType.getOccupationCode()) ? occupationType.getOccupationCode().toUpperCase().trim() : null);
occupationType.setOccupationNameEn(!Util.isNullOrEmpty(occupationType.getOccupationNameEn()) ? occupationType.getOccupationNameEn().toUpperCase().trim() : null);
occupationType.setOccupationNameHi(!Util.isNullOrEmpty(occupationType.getOccupationNameHi()) ? occupationType.getOccupationNameHi().trim() : null);
occupationType.setOccupationNameRl(!Util.isNullOrEmpty(occupationType.getOccupationNameRl()) ? occupationType.getOccupationNameRl().trim() : null);
occupationType.setOccupationDesc(!Util.isNullOrEmpty(occupationType.getOccupationDesc()) ? occupationType.getOccupationDesc().trim() : null);




try {
occupationTypeRepository.save(occupationType);
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
@GetMapping("/getOccupationTypeByGuid/{occupationGuid}")
public ResponseEntity<OccupationType> getOccupationTypeByGuid(@PathVariable("occupationGuid") String occupationGuid) {
	OccupationType occupationType = occupationTypeRepository.findById(occupationGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with occupationGuid : " + occupationGuid));
return new ResponseEntity<>(occupationType, HttpStatus.OK);
}


////////////////////////////////////////////OccupationType End //////////////////////////

////////////////////////////////////////////EducationLevel Start //////////////////////////

//get all data from table
@GetMapping("/getEducationLevelList")
public ResponseEntity<BaseResponse> getEducationLevelList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<EducationLevel> list = educationLevelRepository.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setEducationLevel(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitOrUpdateEducationLevel")
public BaseResponse submitOrUpdateEducationLevel(@RequestBody EducationLevel educationLevel, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (educationLevel.getEducationLevelGuid() == null || educationLevel.getEducationLevelGuid().isEmpty()) {
//Add new data
	educationLevel.setCreatedIpAddr(request.getRemoteAddr());
	educationLevel.setEducationLevelGuid(UUID.randomUUID().toString());
	educationLevel.setCreatedDate(new Date());
	educationLevel.setModifiedIpAddr(null);
	educationLevel.setModifiedBy(null);
educationLevel.setModifiedDate(null);
educationLevel.setCreatedBy(request.getRemoteAddr());

if (educationLevel.getIsActive() == null)
	educationLevel.setIsActive(false);
if (educationLevel.getIsModified() == null)
	educationLevel.setIsModified(false);
//educationLevel.setCreatedRemarks(userSessionParam.getUserFullName());
//educationLevel.setCreaterMacId(HttpSessionHelper.getMacAddress());
//educationLevel.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//educationLevel.setCreatedMacAddr(HttpSessionHelper.getMacAddress());


} else {
//Update existing data
	EducationLevel existingEducationLevel = commonMasterService.getEducationLevelById(educationLevel.getEducationLevelGuid());

if (existingEducationLevel != null) {
	existingEducationLevel.setEducationLevelCode(!Util.isNullOrEmpty(educationLevel.getEducationLevelCode()) ? educationLevel.getEducationLevelCode().toUpperCase().trim() : null);
	existingEducationLevel.setEducationLevelNameEn(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameEn()) ? educationLevel.getEducationLevelNameEn().toUpperCase().trim() : null);

	existingEducationLevel.setEducationLevelNameHi(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameHi()) ? educationLevel.getEducationLevelNameHi().toUpperCase().trim() : null);
	existingEducationLevel.setEducationLevelNameRl(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameRl()) ? educationLevel.getEducationLevelNameRl().trim() : null);
	existingEducationLevel.setEducationLevelDesc(!Util.isNullOrEmpty(educationLevel.getEducationLevelDesc()) ? educationLevel.getEducationLevelDesc().trim() : null);

	existingEducationLevel.setIsModified(educationLevel.getIsModified() != null ? educationLevel.getIsModified() : existingEducationLevel.getIsModified());

	existingEducationLevel.setIsActive(educationLevel.getIsActive() != null ? educationLevel.getIsActive() : existingEducationLevel.getIsActive());

	existingEducationLevel.setModifiedIpAddr(request.getRemoteAddr());
	existingEducationLevel.setModifiedDate(new Date());
if (existingEducationLevel.getIsActive() == null)
	existingEducationLevel.setIsActive(false);
if (existingEducationLevel.getIsModified() == null)
	existingEducationLevel.setIsModified(false);
//for now setting some dummy value to test
existingEducationLevel.setModifiedBy(UUID.randomUUID().toString());
existingEducationLevel.setModifiedMacAddr(UUID.randomUUID().toString());
educationLevel = existingEducationLevel; // Use the updated existing country object
} else {
log.error("Education Level  not found");
resultData.setStatus(false);
resultData.setMessage("Education Level not found");
return resultData;
}
}

//Validation
resultData = validator.validateEducationLevel(educationLevel);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (educationLevel.getIsActive() == null) educationLevel.setIsActive(false);
educationLevel.setEducationLevelCode(!Util.isNullOrEmpty(educationLevel.getEducationLevelCode()) ? educationLevel.getEducationLevelCode().toUpperCase().trim() : null);
educationLevel.setEducationLevelNameEn(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameEn()) ? educationLevel.getEducationLevelNameEn().toUpperCase().trim() : null);
educationLevel.setEducationLevelNameHi(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameHi()) ? educationLevel.getEducationLevelNameHi().trim() : null);
educationLevel.setEducationLevelNameRl(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameRl()) ? educationLevel.getEducationLevelNameRl().trim() : null);
educationLevel.setEducationLevelDesc(!Util.isNullOrEmpty(educationLevel.getEducationLevelDesc()) ? educationLevel.getEducationLevelDesc().trim() : null);




try {
educationLevelRepository.save(educationLevel);
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
@GetMapping("/getEducationLevelByGuid/{educationLevelGuid}")
public ResponseEntity<EducationLevel> getEducationLevelByGuid(@PathVariable("educationLevelGuid") String educationLevelGuid) {
	EducationLevel educationLevel = educationLevelRepository.findById(educationLevelGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with educationLevelGuid : " + educationLevelGuid));
return new ResponseEntity<>(educationLevel, HttpStatus.OK);
}


////////////////////////////////////////////EducationLevel End //////////////////////////


}
