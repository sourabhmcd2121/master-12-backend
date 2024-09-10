package com.master.app.pims.controller.master.common;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.master.GeoStateMasterRepository;
import com.master.app.pims.repositories.mst.GeoColonyCategoryRepository;
import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
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
    private Validator validator;

    @Autowired
    private CommonMasterService commonMasterService;


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

    @PostMapping("/submitOrUpdateMstCountry")
    public BaseResponse submitOrUpdateMstCountry(@RequestBody GeoCountryMst country, HttpServletRequest request) {
        BaseResponse resultData = new BaseResponse();

        // Check if guid is provided (indicating an update)
        if (country.getCountryMstGuid() == null || country.getCountryMstGuid().isEmpty()) {
            // Add new data
            country.setCreaterIp(request.getRemoteAddr());
            country.setCountryMstGuid(UUID.randomUUID().toString());
            country.setCreatedDate(new Date());
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
    @DeleteMapping("/deleteMstCountry/{countryMstGuid}")
    public ResponseEntity<Void> deleteMstCountry(@PathVariable("countryMstGuid") String countryMstGuid) {
        GeoCountryMst geoCountry = geoCountryMstRepository.findById(countryMstGuid).orElseThrow(() -> new ResourceNotFoundException("Data not found with countryMstGuid : " + countryMstGuid));
        geoCountryMstRepository.delete(geoCountry);
        return ResponseEntity.noContent().build();
    }

    //get data by id
    @GetMapping("/getMstCountryByGuid/{countryMstGuid}")
    public ResponseEntity<GeoCountryMst> getMstCountryByGuid(@PathVariable("countryMstGuid") String countryMstGuid) {
        GeoCountryMst geoCountry = geoCountryMstRepository.findById(countryMstGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with countryMstGuid : " + countryMstGuid));
        return new ResponseEntity<>(geoCountry, HttpStatus.OK);
    }
    /////////////////////////////////////GeoCountry Mst End///////////////////////////////////


    //////////////////////////////////////////MasterState Start/////////////////////////////////
    
    //get all data  according to page and size
    @GetMapping("/getGeoStateMasterByPage")
    public ResponseEntity<BaseResponse> getGeoStateMasterByPage(@RequestParam(required = true, name = "page") int page, @RequestParam(required = true, name = "size") int size, @RequestParam(defaultValue = "stateNameEn", required = false) String sortBy) {
        BaseResponse response = new BaseResponse();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<GeoStateMaster> statePage = geoStateMasterRepository.findAll(pageable);
        response.setMessage("success");
        response.setStatus(true);
        response.setTotalDataCount(geoStateMasterRepository.findAll().size());
        response.setMasterState(statePage.toList());
        return ResponseEntity.ok(response);
    }
    
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
    
    
    //////////////////////////////////////////////////GeoStateMaster End////////////////////////////////////////////
    
    ///////////////////////////////////////GeoColonyCategory Start//////////////////////////////////////////////
 
 	 //get all data from table
//    @GetMapping("/getGeoColonyCategoryList")
//    public ResponseEntity<BaseResponse> getGeoColonyCategoryList() {
//        BaseResponse response = new BaseResponse();
//        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        List<GeoColonyCategory> list = geoColonyCategoryRepository.findAll();
//        response.setMessage("success");
//        response.setStatus(true);
//        response.setTotalDataCount(list.size());
//        response.setColonyCategory(list);
//        return ResponseEntity.ok(response);
//    }
    
//    @PostMapping("/submitOrUpdateGeoColonyCategory")
//    public BaseResponse submitOrUpdateGeoColonyCategory(@RequestBody GeoColonyCategory colonyCategory, HttpServletRequest request) {
//        BaseResponse resultData = new BaseResponse();
//
//        // Check if guid is provided (indicating an update)
//        if (colonyCategory.getColonyCategoryGuid() == null || colonyCategory.getColonyCategoryGuid().isEmpty()) {
//            // Add new data
//        	colonyCategory.setCreaterIp(request.getRemoteAddr());
//        	colonyCategory.setColonyCategoryGuid(UUID.randomUUID().toString());
//        	colonyCategory.setCreatedDate(new Date());
//        	colonyCategory.setModifierIp(null);
//        	colonyCategory.setModifiedByGuid(null);
//        	colonyCategory.setModifiedDate(null);
////			colonyCategory.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
////			colonyCategory.setCreaterRemarks(userSessionParam.getUserFullName());
//            //colonyCategory.setCreaterMacId(HttpSessionHelper.getMacAddress());
//            //colonyCategory.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
//        		
//        	
//        } else {
//            // Update existing data
//        	GeoColonyCategory existingColony = commonMasterService.getGeoColonyCategoryById(colonyCategory.getColonyCategoryGuid());
//
//            if (existingColony != null) {
//            	existingColony.setColonyCategoryCode(!Util.isNullOrEmpty(existingColony.getColonyCategoryCode()) ? existingColony.getColonyCategoryCode().toUpperCase().trim() : null);
//            	existingColony.setColonyCategoryNameEn(!Util.isNullOrEmpty(existingColony.getColonyCategoryNameEn()) ? existingColony.getColonyCategoryNameEn().toUpperCase().trim() : null);
//
//            	existingColony.setColonyCategoryNameHi(!Util.isNullOrEmpty(existingColony.getColonyCategoryNameHi()) ? existingColony.getColonyCategoryNameHi().toUpperCase().trim() : null);
//                existingColony.setColonyCategoryNameRl(!Util.isNullOrEmpty(existingColony.getColonyCategoryNameRl()) ? existingColony.getColonyCategoryNameRl().trim() : null);
//                existingColony.setColonyCategoryDesc(!Util.isNullOrEmpty(existingColony.getColonyCategoryDesc()) ? existingColony.getColonyCategoryDesc().trim() : null);
//
//             
//                existingColony.setIsActive(existingColony.getIsActive() != null ? existingColony.getIsActive() : existingColony.getIsActive());
//
//                existingColony.setModifierIp(request.getRemoteAddr());
//                existingColony.setModifiedDate(new Date());
//                //existingCountry.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
//                //existingCountry.setModifierMacId(HttpSessionHelper.getMacAddress());
//                colonyCategory = existingColony; // Use the updated existing country object
//            } else {
//                log.error("Colony Category not found");
//                resultData.setStatus(false);
//                resultData.setMessage("Colony Category not found");
//                return resultData;
//            }
//        }
//
//        // Validation
//        resultData = validator.validateMstCountry(country);
//        if (resultData != null && !resultData.getStatus()) {
//            log.error("Validation failed: {}", resultData.getMessage());
//            return resultData;
//        }
//
//        // If validation passes, proceed to save or update
//        if (country.getIsRecordActive() == null) country.setIsRecordActive(false);
//        country.setCountryCode(!Util.isNullOrEmpty(country.getCountryCode()) ? country.getCountryCode().toUpperCase().trim() : null);
//        country.setCountryNameEn(!Util.isNullOrEmpty(country.getCountryNameEn()) ? country.getCountryNameEn().toUpperCase().trim() : null);
//        country.setCountryNameHi(!Util.isNullOrEmpty(country.getCountryNameHi()) ? country.getCountryNameHi().trim() : null);
//        country.setCountryNameRl(!Util.isNullOrEmpty(country.getCountryNameRl()) ? country.getCountryNameRl().trim() : null);
//        country.setCountryMobileCode(!Util.isNullOrEmpty(country.getCountryMobileCode()) ? country.getCountryMobileCode().trim() : null);
//
//        try {
//            geoCountryMstRepository.save(country);
//            log.info("Record SaveOrUpdate Successfully");
//            resultData.setStatus(true);
//            resultData.setMessage("Record saved or updated successfully");
//        } catch (Exception e) {
//            log.error("Error saving or updating record: {}", e.getMessage());
//            resultData.setStatus(false);
//            resultData.setMessage("Error saving or updating record: " + e.getMessage());
//        }
//
//        return resultData;
//    }

 	
 	
 	
 	//////////////////////////////////////////////////GeoColonyCategory End///////////////////////////////////////////
 	

  

}
