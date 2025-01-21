package com.master.app.pims.controller.master.common;

import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoColonyMCD;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.GeoWardMCD;
import com.master.app.pims.entities.schemas.mst.GeoZoneMCD;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.master.GeoStateMasterRepository;
import com.master.app.pims.repositories.mst.GeoColonyCategoryRepository;
import com.master.app.pims.repositories.mst.GeoColonyMCDRepo;
import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
import com.master.app.pims.repositories.mst.GeoWardMCDRepo;
import com.master.app.pims.repositories.mst.GeoZoneMCDRepository;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class MasterControllerMCDGeo {

    @Autowired
    private GeoCountryMstRepository geoCountryMstRepository;

    @Autowired
    private GeoStateMasterRepository geoStateMasterRepository;
    
    @Autowired
    private GeoColonyCategoryRepository geoColonyCategoryRepository;
    
    @Autowired
    private GeoZoneMCDRepository geoZoneMCDRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private CommonMasterService commonMasterService;

    @Autowired
    private GeoWardMCDRepo geoWardMCDRepo;
    
   @Autowired
    private GeoColonyMCDRepo geoColonyMCDRepo;
    
    /////////////////////////////////////GeoCountry Mst Start///////////////////////////////////
    //get all data  according to page and size
    @GetMapping("/getMstCountryByPage")
    public ResponseEntity<BaseResponse> getMstCountryByPage(@RequestParam(required = true, name = "page") int page, @RequestParam(required = true, name = "size") int size, @RequestParam(defaultValue = "countryNameEn", required = false) String sortBy) {
        BaseResponse response = new BaseResponse();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<GeoCountryMst> countryPage = geoCountryMstRepository.findAll(pageable);
        response.setMessage("success");
        response.setStatus(true);
        response.setTotalDataCount(geoCountryMstRepository.findAll().size());
        response.setData(countryPage.toList());
        return ResponseEntity.ok(response);
    }

    //get all data from table
    @GetMapping("/getGeoCountryMstList")
    public ResponseEntity<BaseResponse> getGeoCountryMstList() {
        BaseResponse response = new BaseResponse();
        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<GeoCountryMst> list = geoCountryMstRepository.findAll();
        response.setMessage("success");
        response.setStatus(true);
        response.setTotalDataCount(list.size());
        response.setData(list);
        return ResponseEntity.ok(response);
    }

    // Create New Data And Update
    @PostMapping("/submitOrUpdateMstCountry")
    public BaseResponse submitOrUpdateMstCountry(@RequestBody GeoCountryMst country, HttpServletRequest request) {
        BaseResponse resultData = new BaseResponse();

        // Check if guid is provided (indicating an update)
        if (country.getCountryMstGuid() == null || country.getCountryMstGuid().isEmpty()) {
        	
        	country.setMasterCountry(country.getCountryMasterGuid());
        	
        	
            // Add new data
            country.setCreaterIp(request.getRemoteAddr());
            country.setCountryMstGuid(UUID.randomUUID().toString());
            country.setCreatedDate(new Date());
            
            
        	if(country.getMasterCountry()!=null && !country.getMasterCountry().isEmpty()){
        		country.setMasterGeoCountryMaster(new GeoCountryMaster(country.getMasterCountry()));
			}
            
//			country.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//			country.setCreaterRemarks(userSessionParam.getUserFullName());
            //country.setCreaterMacId(HttpSessionHelper.getMacAddress());
            //obj.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
        } else {
            // Update existing data
            GeoCountryMst existingCountry = commonMasterService.getGeoCountryMstById(country.getCountryMstGuid());

            if (existingCountry != null) {
                existingCountry.setCountryMobileCode(!Util.isNullOrEmpty(country.getCountryMobileCode()) ? country.getCountryMobileCode().toUpperCase().trim() : null);
                existingCountry.setCountryCode(!Util.isNullOrEmpty(country.getCountryCode()) ? country.getCountryCode().toUpperCase().trim() : null);

                existingCountry.setCountryNameEn(!Util.isNullOrEmpty(country.getCountryNameEn()) ? country.getCountryNameEn().toUpperCase().trim() : null);
                existingCountry.setCountryNameHi(!Util.isNullOrEmpty(country.getCountryNameHi()) ? country.getCountryNameHi().trim() : null);
                existingCountry.setCountryNameRl(!Util.isNullOrEmpty(country.getCountryNameRl()) ? country.getCountryNameRl().trim() : null);
                existingCountry.setToDate(country.getToDate());
                existingCountry.setFromDate(country.getFromDate());
                existingCountry.setIsRecordActive(country.getIsRecordActive() != null ? country.getIsRecordActive() : existingCountry.getIsRecordActive());

                existingCountry.setModifierIp(request.getRemoteAddr());
                existingCountry.setModifiedDate(new Date());
                
                existingCountry.setMasterCountry(country.getCountryMasterGuid());
                
               	if(existingCountry.getMasterCountry()!=null && !existingCountry.getMasterCountry().isEmpty()){
               		existingCountry.setMasterGeoCountryMaster(new GeoCountryMaster(existingCountry.getMasterCountry()));
    			}
                
                //existingCountry.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
                //existingCountry.setModifierMacId(HttpSessionHelper.getMacAddress());
                country = existingCountry; // Use the updated existing country object
            } else {
                log.error("Country not found");
                resultData.setStatus(false);
                resultData.setMessage("Country not found");
                return resultData;
            }
        }

        // Validation
        resultData = validator.validateMstCountry(country);
        if (resultData != null && !resultData.getStatus()) {
            log.error("Validation failed: {}", resultData.getMessage());
            return resultData;
        }

        // If validation passes, proceed to save or update
        if (country.getIsRecordActive() == null) country.setIsRecordActive(false);
        country.setCountryCode(!Util.isNullOrEmpty(country.getCountryCode()) ? country.getCountryCode().toUpperCase().trim() : null);
        country.setCountryNameEn(!Util.isNullOrEmpty(country.getCountryNameEn()) ? country.getCountryNameEn().toUpperCase().trim() : null);
        country.setCountryNameHi(!Util.isNullOrEmpty(country.getCountryNameHi()) ? country.getCountryNameHi().trim() : null);
        country.setCountryNameRl(!Util.isNullOrEmpty(country.getCountryNameRl()) ? country.getCountryNameRl().trim() : null);
        country.setCountryMobileCode(!Util.isNullOrEmpty(country.getCountryMobileCode()) ? country.getCountryMobileCode().trim() : null);

        try {
            geoCountryMstRepository.save(country);
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

    //delete data from table
//    @DeleteMapping("/deleteMstCountry/{countryMstGuid}")
//    public ResponseEntity<Void> deleteMstCountry(@PathVariable("countryMstGuid") String countryMstGuid) {
//        GeoCountryMst geoCountry = geoCountryMstRepository.findById(countryMstGuid).orElseThrow(() -> new ResourceNotFoundException("Data not found with countryMstGuid : " + countryMstGuid));
//        geoCountryMstRepository.delete(geoCountry);
//        return ResponseEntity.noContent().build();
//    }
    
 // Delete data from table and return success message
    @DeleteMapping("/deleteMstCountry/{countryMstGuid}")
    public ResponseEntity<BaseResponse> deleteMstCountry(@PathVariable("countryMstGuid") String countryMstGuid) {
        BaseResponse response = new BaseResponse();
        
        GeoCountryMst geoCountry = geoCountryMstRepository.findById(countryMstGuid)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found with countryMstGuid: " + countryMstGuid));
        
        geoCountryMstRepository.delete(geoCountry);
        
        response.setMessage("Data deleted successfully");
        response.setStatus(true);
        response.setTotalDataCount(0);  // No data to return after delete
        
        return ResponseEntity.ok(response);
    }


    //get data by id
    @GetMapping("/getMstCountryByGuid/{countryMstGuid}")
    public ResponseEntity<GeoCountryMst> getMstCountryByGuid(@PathVariable("countryMstGuid") String countryMstGuid) {
        GeoCountryMst geoCountry = geoCountryMstRepository.findById(countryMstGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with countryMstGuid : " + countryMstGuid));
        return new ResponseEntity<>(geoCountry, HttpStatus.OK);
    }
    
 // Get data by ID with a custom response
//    @GetMapping("/getMstCountryByGuid/{countryMstGuid}")
//    public ResponseEntity<BaseResponse> getMstCountryByGuid(@PathVariable("countryMstGuid") String countryMstGuid) {
//        BaseResponse response = new BaseResponse();
//        
//        GeoCountryMst geoCountry = geoCountryMstRepository.findById(countryMstGuid)
//                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with countryMstGuid: " + countryMstGuid));
//        
//        response.setMessage("Data retrieved successfully");
//        response.setStatus(true);
//        response.setTotalDataCount(1);  // Since we are returning one record
//        response.setData(geoCountry);   // Set the data to the retrieved entity
//        
//        return ResponseEntity.ok(response);
//    }

    /////////////////////////////////////GeoCountry Mst End///////////////////////////////////


    //////////////////////////////////////////MasterState Start/////////////////////////////////
    
   
    
    //get all data from table
    @GetMapping("/getMasterStateList")
    public ResponseEntity<BaseResponse> getMasterStateList() {
        BaseResponse response = new BaseResponse();
        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<GeoStateMaster> list = geoStateMasterRepository.findAll();
        response.setMessage("success");
        response.setStatus(true);
        response.setTotalDataCount(list.size());
        response.setMasterState(list);
        return ResponseEntity.ok(response);
    }
    
   
    @PostMapping("/submitOrUpdateMasterState")
    public BaseResponse submitOrUpdateMasterState(@RequestBody GeoStateMaster state, HttpServletRequest request) {
        BaseResponse resultData = new BaseResponse();

        // Check if guid is provided (indicating an update)
        if (state.getStateMasterGuid() == null || state.getStateMasterGuid().isEmpty()) {
        	
        	
            // Add new data
        	state.setCreaterIp(request.getRemoteAddr());
        	state.setStateMasterGuid(UUID.randomUUID().toString());
        	state.setCreatedDate(new Date());
        	
        	//country dropdown
        	state.setMasterCountry(state.getCountryMasterGuid());
        	if(state.getMasterCountry()!=null && !state.getMasterCountry().isEmpty()){
        		state.setMasterGeoCountryMaster(new GeoCountryMaster(state.getMasterCountry()));
			}
        	
            if(state.getIsRecordActive()==null)
                state.setIsRecordActive(false);
//			state.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//			state.setCreaterRemarks(userSessionParam.getUserFullName());
//			state.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
//			state.setCreaterMacId(HttpSessionHelper.getMacAddress());
        } else {
            // Update existing data
        	GeoStateMaster existingState = commonMasterService.getGeoStateMasterById(state.getStateMasterGuid());

            if (existingState != null) {
            	existingState.setStateCode(!Util.isNullOrEmpty(state.getStateCode()) ? state.getStateCode().toUpperCase().trim() : null);
            	existingState.setStateNameEn(!Util.isNullOrEmpty(state.getStateNameEn()) ? state.getStateNameEn().toUpperCase().trim() : null);

            	existingState.setStateNameHi(!Util.isNullOrEmpty(state.getStateNameHi()) ? state.getStateNameHi().toUpperCase().trim() : null);
            	existingState.setStateNameRl(!Util.isNullOrEmpty(state.getStateNameRl()) ? state.getStateNameRl().trim() : null);
            	existingState.setStateDescription(!Util.isNullOrEmpty(state.getStateDescription()) ? state.getStateDescription().trim() : null);

            	existingState.setToDate(state.getToDate());
            	existingState.setFromDate(state.getFromDate());
            	existingState.setIsRecordActive(state.getIsRecordActive() != null ? state.getIsRecordActive() : existingState.getIsRecordActive());

            	existingState.setModifierIp(request.getRemoteAddr());
            	existingState.setModifiedDate(new Date());
            	existingState.setIsModified(true);
            	
            	//country dropdown
            	existingState.setMasterCountry(state.getCountryMasterGuid());
             	if(existingState.getMasterCountry()!=null && !existingState.getMasterCountry().isEmpty()){
             		existingState.setMasterGeoCountryMaster(new GeoCountryMaster(existingState.getMasterCountry()));
    			}
            	//existingState.setModifierMacId(HttpSessionHelper.getMacAddress());	
            	//existingState.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
                state = existingState; // Use the updated existing country object
            } else {
                log.error("State not found");
                resultData.setStatus(false);
                resultData.setMessage("State not found");
                return resultData;
            }
        }

        // Validation
        resultData = validator.validateMasterState(state);
        if (resultData != null && !resultData.getStatus()) {
            log.error("Validation failed: {}", resultData.getMessage());
            return resultData;
        }

        // If validation passes, proceed to save or update
        if (state.getIsRecordActive() == null) state.setIsRecordActive(false);
        state.setStateCode(!Util.isNullOrEmpty(state.getStateCode()) ? state.getStateCode().toUpperCase().trim() : null);
        
        state.setStateNameEn(!Util.isNullOrEmpty(state.getStateNameEn()) ? state.getStateNameEn().toUpperCase().trim() : null);
        state.setStateNameHi(!Util.isNullOrEmpty(state.getStateNameHi()) ? state.getStateNameHi().toUpperCase().trim() : null);
        state.setStateNameRl(!Util.isNullOrEmpty(state.getStateNameRl()) ? state.getStateNameRl().trim() : null);
        state.setStateDescription(!Util.isNullOrEmpty(state.getStateDescription()) ? state.getStateDescription().trim() : null);    
        
        
        try {
        	 geoStateMasterRepository.save(state);
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
    @GetMapping("/getMasterStateByGuid/{stateMasterGuid}")
    public ResponseEntity<GeoStateMaster> getMasterStateByGuid(@PathVariable("stateMasterGuid") String stateMasterGuid) {
    	GeoStateMaster masterState = geoStateMasterRepository.findById(stateMasterGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with stateMasterGuid : " + stateMasterGuid));
        return new ResponseEntity<>(masterState, HttpStatus.OK);
    }
    
    
    //////////////////////////////////////////////////GeoStateMaster End////////////////////////////////////////////
    
    ///////////////////////////////////////GeoColonyCategory Start//////////////////////////////////////////////
 
    //get all data from table
    @GetMapping("/getGeoColonyCategoryList")
    public ResponseEntity<BaseResponse> getGeoColonyCategoryList() {
        BaseResponse response = new BaseResponse();
        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<GeoColonyCategory> list = geoColonyCategoryRepository.findAll();
        response.setMessage("success");
        response.setStatus(true);
        response.setTotalDataCount(list.size());
        response.setColonyCategory(list);
        return ResponseEntity.ok(response);
    }
    
    // Create New Data And Update
    @PostMapping("/submitOrUpdateGeoColonyCategory")
    public BaseResponse submitOrUpdateGeoColonyCategory(@RequestBody GeoColonyCategory colonyCategory, HttpServletRequest request) {
        BaseResponse resultData = new BaseResponse();

        // Check if guid is provided (indicating an update)
        if (colonyCategory.getColonyCategoryGuid() == null || colonyCategory.getColonyCategoryGuid().isEmpty()) {
            // Add new data
        	colonyCategory.setCreaterIp(request.getRemoteAddr());
        	colonyCategory.setColonyCategoryGuid(UUID.randomUUID().toString());
        	colonyCategory.setCreatedDate(new Date());
        	colonyCategory.setModifierIp(null);
        	colonyCategory.setModifiedByGuid(null);
        	colonyCategory.setModifiedDate(null);
    		colonyCategory.setCreatedByGuid(request.getRemoteAddr());

            if (colonyCategory.getIsActive() == null)
                colonyCategory.setIsActive(false);
//			colonyCategory.setCreaterRemarks(userSessionParam.getUserFullName());
            //colonyCategory.setCreaterMacId(HttpSessionHelper.getMacAddress());
            //colonyCategory.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
        		
        	
        } else {
            // Update existing data
        	GeoColonyCategory existingColony = commonMasterService.getGeoColonyCategoryById(colonyCategory.getColonyCategoryGuid());

            if (existingColony != null) {
            	existingColony.setColonyCategoryCode(!Util.isNullOrEmpty(colonyCategory.getColonyCategoryCode()) ? colonyCategory.getColonyCategoryCode().toUpperCase().trim() : null);
            	existingColony.setColonyCategoryNameEn(!Util.isNullOrEmpty(colonyCategory.getColonyCategoryNameEn()) ? colonyCategory.getColonyCategoryNameEn().toUpperCase().trim() : null);

            	existingColony.setColonyCategoryNameHi(!Util.isNullOrEmpty(colonyCategory.getColonyCategoryNameHi()) ? colonyCategory.getColonyCategoryNameHi().toUpperCase().trim() : null);
                existingColony.setColonyCategoryNameRl(!Util.isNullOrEmpty(colonyCategory.getColonyCategoryNameRl()) ? colonyCategory.getColonyCategoryNameRl().trim() : null);
                existingColony.setColonyCategoryDesc(!Util.isNullOrEmpty(colonyCategory.getColonyCategoryDesc()) ? colonyCategory.getColonyCategoryDesc().trim() : null);

             
                existingColony.setIsActive(colonyCategory.getIsActive() != null ? colonyCategory.getIsActive() : existingColony.getIsActive());

                existingColony.setModifierIp(request.getRemoteAddr());
                existingColony.setModifiedDate(new Date());
                if (existingColony.getIsActive() == null)
                    existingColony.setIsActive(false);
                // for now setting some dummy value to test
                existingColony.setModifiedByGuid(UUID.randomUUID().toString());
                existingColony.setModifierMacId(UUID.randomUUID().toString());
                colonyCategory = existingColony; // Use the updated existing country object
            } else {
                log.error("Colony Category not found");
                resultData.setStatus(false);
                resultData.setMessage("Colony Category not found");
                return resultData;
            }
        }

        // Validation
        resultData = validator.validateColonyCategory(colonyCategory);
        if (resultData != null && !resultData.getStatus()) {
            log.error("Validation failed: {}", resultData.getMessage());
            return resultData;
        }

        // If validation passes, proceed to save or update
        if (colonyCategory.getIsActive() == null) colonyCategory.setIsActive(false);
        colonyCategory.setColonyCategoryCode(!Util.isNullOrEmpty(colonyCategory.getColonyCategoryCode()) ? colonyCategory.getColonyCategoryCode().toUpperCase().trim() : null);
        colonyCategory.setColonyCategoryNameEn(!Util.isNullOrEmpty(colonyCategory.getColonyCategoryNameEn()) ? colonyCategory.getColonyCategoryNameEn().toUpperCase().trim() : null);
        colonyCategory.setColonyCategoryNameHi(!Util.isNullOrEmpty(colonyCategory.getColonyCategoryNameHi()) ? colonyCategory.getColonyCategoryNameHi().trim() : null);
        colonyCategory.setColonyCategoryNameRl(!Util.isNullOrEmpty(colonyCategory.getColonyCategoryNameRl()) ? colonyCategory.getColonyCategoryNameRl().trim() : null);
        colonyCategory.setColonyCategoryDesc(!Util.isNullOrEmpty(colonyCategory.getColonyCategoryDesc()) ? colonyCategory.getColonyCategoryDesc().trim() : null);


     

        try {
            geoColonyCategoryRepository.save(colonyCategory);
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
    @GetMapping("/getColonyCategoryByGuid/{colonyCategoryGuid}")
    public ResponseEntity<GeoColonyCategory> getColonyCategoryByGuid(@PathVariable("colonyCategoryGuid") String colonyCategoryGuid) {
    	GeoColonyCategory colonyCategory = geoColonyCategoryRepository.findById(colonyCategoryGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with stateMasterGuid : " + colonyCategoryGuid));
        return new ResponseEntity<>(colonyCategory, HttpStatus.OK);
    }
    
 	
 	//////////////////////////////////////////////////GeoColonyCategory End///////////////////////////////////////////
 	
/////////////////////////////////////GeoZoneMcd Start///////////////////////////////////
    
    //get all data from table
    @GetMapping("/getGeoZoneMCDList")
    public ResponseEntity<BaseResponse> getGeoZoneMCDList() {
        BaseResponse response = new BaseResponse();
        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<GeoZoneMCD> list = geoZoneMCDRepository.findAll();
        response.setMessage("success");
        response.setStatus(true);
        response.setTotalDataCount(list.size());
        response.setGeoZoneMCD(list);
        return ResponseEntity.ok(response);
    }
    
    // Create New Data And Update
    @PostMapping("/submitGeoZoneMCD")
    public BaseResponse submitGeoZoneMCD(@RequestBody GeoZoneMCD geoZoneMCD, HttpServletRequest request) {
        BaseResponse resultData = new BaseResponse();

        // Check if guid is provided (indicating an update)
        if (geoZoneMCD.getZoneGuid() == null || geoZoneMCD.getZoneGuid().isEmpty()) {
        	
            // Add new data
        	geoZoneMCD.setCreaterIp(request.getRemoteAddr());
        	geoZoneMCD.setZoneGuid(UUID.randomUUID().toString());
        	geoZoneMCD.setCreatedDate(new Date());
        	geoZoneMCD.setModifierIp(null);
        	geoZoneMCD.setModifiedByGuid(null);
        	geoZoneMCD.setModifiedDate(null);
        	geoZoneMCD.setCreatedByGuid(request.getRemoteAddr());
        	
        	//for dropdown
        	geoZoneMCD.setOrgPrimary(geoZoneMCD.getOrgPrimaryGuid());
        	geoZoneMCD.setOrgWrapper(geoZoneMCD.getWrapperGuid());
        	if(geoZoneMCD.getOrgPrimary()!=null && !geoZoneMCD.getOrgPrimary().isEmpty()){
        		geoZoneMCD.setOrgPrimaryMaster(new OrgPrimary(geoZoneMCD.getOrgPrimary()));
			}
        	
        	if(geoZoneMCD.getOrgWrapper()!=null && !geoZoneMCD.getOrgWrapper().isEmpty()){
        		geoZoneMCD.setWrapperMaster(new OrgWrapper(geoZoneMCD.getOrgWrapper()));
			}
            
        	 if (geoZoneMCD.getIsActive() == null)
        		 geoZoneMCD.setIsActive(false);
//			geoZoneMCD.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//			geoZoneMCD.setCreaterRemarks(userSessionParam.getUserFullName());
            //geoZoneMCD.setCreaterMacId(HttpSessionHelper.getMacAddress());
            //geoZoneMCD.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
        } else {
            // Update existing data
        	GeoZoneMCD existingGeoZoneMCD = commonMasterService.getGeoZoneMCDById(geoZoneMCD.getZoneGuid());

            if (existingGeoZoneMCD != null) {
            	existingGeoZoneMCD.setZoneCode(!Util.isNullOrEmpty(geoZoneMCD.getZoneCode()) ? geoZoneMCD.getZoneCode().toUpperCase().trim() : null);
            	existingGeoZoneMCD.setZoneNameEn(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameEn()) ? geoZoneMCD.getZoneNameEn().toUpperCase().trim() : null);

            	existingGeoZoneMCD.setZoneNameHi(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameHi()) ? geoZoneMCD.getZoneNameHi().toUpperCase().trim() : null);
            	existingGeoZoneMCD.setZoneNameRl(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameRl()) ? geoZoneMCD.getZoneNameRl().trim() : null);
            	existingGeoZoneMCD.setZonalAddress(!Util.isNullOrEmpty(geoZoneMCD.getZonalAddress()) ? geoZoneMCD.getZonalAddress().trim() : null);
            	existingGeoZoneMCD.setZoneDesc(!Util.isNullOrEmpty(geoZoneMCD.getZoneDesc()) ? geoZoneMCD.getZoneDesc().trim() : null);
            	existingGeoZoneMCD.setIsActive(geoZoneMCD.getIsActive() != null ? geoZoneMCD.getIsActive() : existingGeoZoneMCD.getIsActive());

            	existingGeoZoneMCD.setModifierIp(request.getRemoteAddr());
            	existingGeoZoneMCD.setModifiedDate(new Date());
            	existingGeoZoneMCD.setModifiedByGuid("admin");
              
            	//dropdown
            	existingGeoZoneMCD.setOrgPrimary(geoZoneMCD.getOrgPrimaryGuid());
            	existingGeoZoneMCD.setOrgWrapper(geoZoneMCD.getWrapperGuid());
               	if(existingGeoZoneMCD.getOrgPrimary()!=null && !existingGeoZoneMCD.getOrgPrimary().isEmpty()){
               		existingGeoZoneMCD.setOrgPrimaryMaster(new OrgPrimary(existingGeoZoneMCD.getOrgPrimary()));
    			}
            	if(existingGeoZoneMCD.getOrgWrapper()!=null && !existingGeoZoneMCD.getOrgWrapper().isEmpty()){
            		existingGeoZoneMCD.setWrapperMaster(new OrgWrapper(existingGeoZoneMCD.getOrgWrapper()));
    			}
            	
            	 if (existingGeoZoneMCD.getIsActive() == null)
            		 existingGeoZoneMCD.setIsActive(false);
                
               	geoZoneMCD = existingGeoZoneMCD; // Use the updated existing country object
            } else {
                log.error("Zone not found");
                resultData.setStatus(false);
                resultData.setMessage("Zone not found");
                return resultData;
            }
        }

        // Validation
        resultData = validator.validateGeoZoneMCD(geoZoneMCD);
        if (resultData != null && !resultData.getStatus()) {
            log.error("Validation failed: {}", resultData.getMessage());
            return resultData;
        }

        // If validation passes, proceed to save or update
        if (geoZoneMCD.getIsActive() == null) geoZoneMCD.setIsActive(false);
        geoZoneMCD.setZoneCode(!Util.isNullOrEmpty(geoZoneMCD.getZoneCode()) ? geoZoneMCD.getZoneCode().toUpperCase().trim() : null);
        geoZoneMCD.setZoneNameEn(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameEn()) ? geoZoneMCD.getZoneNameEn().toUpperCase().trim() : null);
        geoZoneMCD.setZoneNameHi(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameHi()) ? geoZoneMCD.getZoneNameHi().trim() : null);
        geoZoneMCD.setZoneNameRl(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameRl()) ? geoZoneMCD.getZoneNameRl().trim() : null);
        geoZoneMCD.setZonalAddress(!Util.isNullOrEmpty(geoZoneMCD.getZonalAddress()) ? geoZoneMCD.getZonalAddress().trim() : null);
        geoZoneMCD.setZoneDesc(!Util.isNullOrEmpty(geoZoneMCD.getZoneDesc()) ? geoZoneMCD.getZoneDesc().trim() : null);

        try {
            geoZoneMCDRepository.save(geoZoneMCD);
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
    @GetMapping("/getGeoZoneMCDByGuid/{zoneGuid}")
    public ResponseEntity<GeoZoneMCD> getGeoZoneMCDByGuid(@PathVariable("zoneGuid") String zoneGuid) {
    	GeoZoneMCD geoZoneMCD = geoZoneMCDRepository.findById(zoneGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with zoneGuid : " + zoneGuid));
        return new ResponseEntity<>(geoZoneMCD, HttpStatus.OK);
    }
    
/////////////////////////////////////GeoZoneMcd End///////////////////////////////////


/////////////////////////////////////GeoWardMCD Start///////////////////////////////////
    
//get all data from table
@GetMapping("/getGeoWardMCDList")
public ResponseEntity<BaseResponse> getGeoWardMCDList() {
BaseResponse response = new BaseResponse();
// Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<GeoWardMCD> list = geoWardMCDRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setGeoWardMCD(list);
return ResponseEntity.ok(response);
}

// Create New Data And Update
@PostMapping("/submitGeoWardMCD")
public BaseResponse submitGeoWardMCD(@RequestBody GeoWardMCD geoWardMCD, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

// Check if guid is provided (indicating an update)
if (geoWardMCD.getWardGuid() == null || geoWardMCD.getWardGuid().isEmpty()) {

// Add new data
	geoWardMCD.setCreatedIpAddr(request.getRemoteAddr());
	geoWardMCD.setWardGuid(UUID.randomUUID().toString());
	geoWardMCD.setCreatedDate(new Date());
	geoWardMCD.setModifiedIpAddr(null);
	geoWardMCD.setModifiedBy(null);
	geoWardMCD.setModifiedDate(null);
	geoWardMCD.setCreatedBy(request.getRemoteAddr());
//	if(geoWardMCD.getLatLongInfo() == null) {
//		geoWardMCD.setLatLongInfo(null);
//		
//	}else {
//		
//		geoWardMCD.setLatLongInfo(geoWardMCD.getLatLongInfo());
//		
//	}

//for dropdown
	geoWardMCD.setZone(geoWardMCD.getZoneGuid());
if(geoWardMCD.getZone()!=null && !geoWardMCD.getZone().isEmpty()){
	geoWardMCD.setZoneMaster(new GeoZoneMCD(geoWardMCD.getZone()));
}

if (geoWardMCD.getIsActive() == null)
	geoWardMCD.setIsActive(false);
//geoWardMCD.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//geoWardMCD.setCreaterRemarks(userSessionParam.getUserFullName());
//geoWardMCD.setCreaterMacId(HttpSessionHelper.getMacAddress());
//geoWardMCD.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
// Update existing data
	GeoWardMCD existingGeoWardMCD = commonMasterService.getGeoWardMCDById(geoWardMCD.getWardGuid());

if (existingGeoWardMCD != null) {
	existingGeoWardMCD.setWardCode(!Util.isNullOrEmpty(geoWardMCD.getWardCode()) ? geoWardMCD.getWardCode().toUpperCase().trim() : null);
	existingGeoWardMCD.setWardNameEn(!Util.isNullOrEmpty(geoWardMCD.getWardNameEn()) ? geoWardMCD.getWardNameEn().toUpperCase().trim() : null);

	existingGeoWardMCD.setWardNameHi(!Util.isNullOrEmpty(geoWardMCD.getWardNameHi()) ? geoWardMCD.getWardNameHi().toUpperCase().trim() : null);
	existingGeoWardMCD.setWardNameRl(!Util.isNullOrEmpty(geoWardMCD.getWardNameRl()) ? geoWardMCD.getWardNameRl().trim() : null);
	existingGeoWardMCD.setWardDesc(!Util.isNullOrEmpty(geoWardMCD.getWardDesc()) ? geoWardMCD.getWardDesc().trim() : null);
	existingGeoWardMCD.setAreaCode(!Util.isNullOrEmpty(geoWardMCD.getAreaCode()) ? geoWardMCD.getAreaCode().trim() : null);
	existingGeoWardMCD.setWardNo(!Util.isNullOrEmpty(geoWardMCD.getWardNo()) ? geoWardMCD.getWardNo().trim() : null);
	//existingGeoWardMCD.setLatLongInfo(!Util.isNullOrEmpty(geoWardMCD.getLatLongInfo()) ? geoWardMCD.getLatLongInfo().trim() : null);

	existingGeoWardMCD.setIsActive(geoWardMCD.getIsActive() != null ? geoWardMCD.getIsActive() : existingGeoWardMCD.getIsActive());

	existingGeoWardMCD.setModifiedIpAddr(request.getRemoteAddr());
	existingGeoWardMCD.setModifiedDate(new Date());
	existingGeoWardMCD.setModifiedBy("admin");
//	if(geoWardMCD.getLatLongInfo() == null) {
//		geoWardMCD.setLatLongInfo(null);
//		
//	}else {
//		
//		geoWardMCD.setLatLongInfo(geoWardMCD.getLatLongInfo());
//		
//	}

//dropdown
	
	existingGeoWardMCD.setZone(geoWardMCD.getZoneGuid());

if(existingGeoWardMCD.getZone()!=null && !existingGeoWardMCD.getZone().isEmpty()){
	existingGeoWardMCD.setZoneMaster(new GeoZoneMCD(existingGeoWardMCD.getZone()));
}

if (existingGeoWardMCD.getIsActive() == null)
	existingGeoWardMCD.setIsActive(false);

geoWardMCD = existingGeoWardMCD; // Use the updated existing country object
} else {
log.error("Ward not found");
resultData.setStatus(false);
resultData.setMessage("Ward not found");
return resultData;
}
}

// Validation
resultData = validator.validateGeoWardMCD(geoWardMCD);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

// If validation passes, proceed to save or update
if (geoWardMCD.getIsActive() == null) geoWardMCD.setIsActive(false);
geoWardMCD.setWardCode(!Util.isNullOrEmpty(geoWardMCD.getWardCode()) ? geoWardMCD.getWardCode().toUpperCase().trim() : null);
geoWardMCD.setWardNameEn(!Util.isNullOrEmpty(geoWardMCD.getWardNameEn()) ? geoWardMCD.getWardNameEn().toUpperCase().trim() : null);
geoWardMCD.setWardNameHi(!Util.isNullOrEmpty(geoWardMCD.getWardNameHi()) ? geoWardMCD.getWardNameHi().trim() : null);
geoWardMCD.setWardNameRl(!Util.isNullOrEmpty(geoWardMCD.getWardNameRl()) ? geoWardMCD.getWardNameRl().trim() : null);
geoWardMCD.setWardDesc(!Util.isNullOrEmpty(geoWardMCD.getWardDesc()) ? geoWardMCD.getWardDesc().trim() : null);
geoWardMCD.setAreaCode(!Util.isNullOrEmpty(geoWardMCD.getAreaCode()) ? geoWardMCD.getAreaCode().trim() : null);
geoWardMCD.setWardNo(!Util.isNullOrEmpty(geoWardMCD.getWardNo()) ? geoWardMCD.getWardNo().trim() : null);
//geoWardMCD.setLatLongInfo(!Util.isNullOrEmpty(geoWardMCD.getLatLongInfo()) ? geoWardMCD.getLatLongInfo().trim() : null);

try {
geoWardMCDRepo.save(geoWardMCD);
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
@GetMapping("/getGeoWardMCDByGuid/{wardGuid}")
public ResponseEntity<GeoWardMCD> getGeoWardMCDByGuid(@PathVariable("wardGuid") String wardGuid) {
GeoWardMCD geoWardMCD = geoWardMCDRepo.findById(wardGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with wardGuid : " + wardGuid));
return new ResponseEntity<>(geoWardMCD, HttpStatus.OK);
}

/////////////////////////////////////GeoWardMCD End///////////////////////////////////

/////////////////////////////////////GeoColonyMCD Start///////////////////////////////////

//get all data from table
@GetMapping("/getGeoColonyMCDList")
public ResponseEntity<BaseResponse> getGeoColonyMCDList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<GeoColonyMCD> list = geoColonyMCDRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setGeoColonyMCD(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitGeoColonyMCD")
public BaseResponse submitGeoColonyMCD(@RequestBody GeoColonyMCD geoColonyMCD, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (geoColonyMCD.getColonyGuid() == null || geoColonyMCD.getColonyGuid().isEmpty()) {

//Add new data
	geoColonyMCD.setCreatedIpAddr(request.getRemoteAddr());
	geoColonyMCD.setColonyGuid(UUID.randomUUID().toString());
	geoColonyMCD.setCreatedDate(new Date());
	geoColonyMCD.setModifiedIpAddr(null);
	geoColonyMCD.setModifiedBy(null);
	geoColonyMCD.setModifiedDate(null);
	

	
	geoColonyMCD.setCreatedBy(request.getRemoteAddr());

//for dropdown
	geoColonyMCD.setWard(geoColonyMCD.getWardGuid());
if(geoColonyMCD.getWard()!=null && !geoColonyMCD.getWard().isEmpty()){
	geoColonyMCD.setWardMaster(new GeoWardMCD(geoColonyMCD.getWard()));
}

if (geoColonyMCD.getIsActive() == null)
	geoColonyMCD.setIsActive(false);
//geoColonyMCD.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//geoColonyMCD.setCreaterRemarks(userSessionParam.getUserFullName());
//geoColonyMCD.setCreaterMacId(HttpSessionHelper.getMacAddress());
//geoColonyMCD.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	GeoColonyMCD existingGeoColonyMCD = commonMasterService.getGeoColonyMCDById(geoColonyMCD.getColonyGuid());

if (existingGeoColonyMCD != null) {
	existingGeoColonyMCD.setColonyCode(!Util.isNullOrEmpty(geoColonyMCD.getColonyCode()) ? geoColonyMCD.getColonyCode().toUpperCase().trim() : null);
	existingGeoColonyMCD.setColonyNameEn(!Util.isNullOrEmpty(geoColonyMCD.getColonyNameEn()) ? geoColonyMCD.getColonyNameEn().toUpperCase().trim() : null);
	existingGeoColonyMCD.setColonyNameHi(!Util.isNullOrEmpty(geoColonyMCD.getColonyNameHi()) ? geoColonyMCD.getColonyNameHi().toUpperCase().trim() : null);
	existingGeoColonyMCD.setColonyNameRl(!Util.isNullOrEmpty(geoColonyMCD.getColonyNameRl()) ? geoColonyMCD.getColonyNameRl().trim() : null);
	existingGeoColonyMCD.setColonyDesc(!Util.isNullOrEmpty(geoColonyMCD.getColonyDesc()) ? geoColonyMCD.getColonyDesc().trim() : null);
	existingGeoColonyMCD.setColonyTypeOther(!Util.isNullOrEmpty(geoColonyMCD.getColonyTypeOther()) ? geoColonyMCD.getColonyTypeOther().trim() : null);
	existingGeoColonyMCD.setColonyTypeRuralUrban(!Util.isNullOrEmpty(geoColonyMCD.getColonyTypeRuralUrban()) ? geoColonyMCD.getColonyTypeRuralUrban().trim() : null);
	existingGeoColonyMCD.setNewColonyCode(!Util.isNullOrEmpty(geoColonyMCD.getNewColonyCode()) ? geoColonyMCD.getNewColonyCode().trim() : null);
	//existingGeoColonyMCD.setLongLatInfo(!Util.isNullOrEmpty(geoColonyMCD.getLongLatInfo()) ? geoColonyMCD.getLongLatInfo().trim() : null);

	existingGeoColonyMCD.setIsActive(geoColonyMCD.getIsActive() != null ? geoColonyMCD.getIsActive() : existingGeoColonyMCD.getIsActive());

	existingGeoColonyMCD.setModifiedIpAddr(request.getRemoteAddr());
	existingGeoColonyMCD.setModifiedDate(new Date());
	existingGeoColonyMCD.setModifiedBy("admin");
	
//dropdown

	existingGeoColonyMCD.setWard(geoColonyMCD.getWardGuid());

if(existingGeoColonyMCD.getWard()!=null && !existingGeoColonyMCD.getWard().isEmpty()){
	existingGeoColonyMCD.setWardMaster(new GeoWardMCD(existingGeoColonyMCD.getWard()));
}

if (existingGeoColonyMCD.getIsActive() == null)
	existingGeoColonyMCD.setIsActive(false);

geoColonyMCD = existingGeoColonyMCD; // Use the updated existing country object
} else {
log.error("Colony not found");
resultData.setStatus(false);
resultData.setMessage("Colony not found");
return resultData;
}
}

//Validation
resultData = validator.validateGeoColonyMCD(geoColonyMCD);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (geoColonyMCD.getIsActive() == null) geoColonyMCD.setIsActive(false);
geoColonyMCD.setColonyCode(!Util.isNullOrEmpty(geoColonyMCD.getColonyCode()) ? geoColonyMCD.getColonyCode().toUpperCase().trim() : null);
geoColonyMCD.setColonyNameEn(!Util.isNullOrEmpty(geoColonyMCD.getColonyNameEn()) ? geoColonyMCD.getColonyNameEn().toUpperCase().trim() : null);
geoColonyMCD.setColonyNameHi(!Util.isNullOrEmpty(geoColonyMCD.getColonyNameHi()) ? geoColonyMCD.getColonyNameHi().trim() : null);
geoColonyMCD.setColonyNameRl(!Util.isNullOrEmpty(geoColonyMCD.getColonyNameRl()) ? geoColonyMCD.getColonyNameRl().trim() : null);
geoColonyMCD.setColonyDesc(!Util.isNullOrEmpty(geoColonyMCD.getColonyDesc()) ? geoColonyMCD.getColonyDesc().trim() : null);
geoColonyMCD.setColonyTypeOther(!Util.isNullOrEmpty(geoColonyMCD.getColonyTypeOther()) ? geoColonyMCD.getColonyTypeOther().trim() : null);
geoColonyMCD.setColonyTypeRuralUrban(!Util.isNullOrEmpty(geoColonyMCD.getColonyTypeRuralUrban()) ? geoColonyMCD.getColonyTypeRuralUrban().trim() : null);
geoColonyMCD.setNewColonyCode(!Util.isNullOrEmpty(geoColonyMCD.getNewColonyCode()) ? geoColonyMCD.getNewColonyCode().trim() : null);
//geoColonyMCD.setLongLatInfo(!Util.isNullOrEmpty(geoColonyMCD.getLongLatInfo()) ? geoColonyMCD.getLongLatInfo().trim() : null);

try {
geoColonyMCDRepo.save(geoColonyMCD);
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
@GetMapping("/getGeoColonyMCDByGuid/{colonyGuid}")
public ResponseEntity<GeoColonyMCD> getGeoColonyMCDByGuid(@PathVariable("colonyGuid") String colonyGuid) {
GeoColonyMCD geoColonyMCD = geoColonyMCDRepo.findById(colonyGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with colonyGuid : " + colonyGuid));
return new ResponseEntity<>(geoColonyMCD, HttpStatus.OK);
}

/////////////////////////////////////GeoColonyMCD End///////////////////////////////////
}
