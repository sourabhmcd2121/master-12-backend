package com.master.app.pims.controller.master.property;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.master.app.pims.controller.master.citizen.MasterControllerCitizen;
import com.master.app.pims.entities.schemas.citizenmaster.HelplineNumbers;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeClassification;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeType;
import com.master.app.pims.entities.schemas.property.OwnerCategory;
import com.master.app.pims.entities.schemas.property.OwnerType;
import com.master.app.pims.entities.schemas.property.PropertyAgeFactor;
import com.master.app.pims.entities.schemas.property.PropertyCategory;
import com.master.app.pims.entities.schemas.property.PropertyExemption;
import com.master.app.pims.entities.schemas.property.PropertyFloor;
import com.master.app.pims.entities.schemas.property.PropertyOccupancyFactor;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.property.OwnerCategoryRepo;
import com.master.app.pims.repositories.property.OwnerTypeRepo;
import com.master.app.pims.repositories.property.PropertyAgeFactorRepo;
import com.master.app.pims.repositories.property.PropertyCategoryRepo;
import com.master.app.pims.repositories.property.PropertyExemptionRepo;
import com.master.app.pims.repositories.property.PropertyFloorRepo;
import com.master.app.pims.repositories.property.PropertyOccupancyFactorRepo;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class PropertyMasterController {
	
	private Logger logger = LoggerFactory.getLogger(MasterControllerCitizen.class);
	
	@Autowired
	private Validator validator;

	@Autowired
	private CommonMasterService commonMasterService;
	
	@Autowired
	private PropertyAgeFactorRepo propertyAgeFactorRepo;
	
	 @Autowired
	   	private PropertyExemptionRepo propertyExemptionRepo;
	 
	  @Autowired
	   	private PropertyFloorRepo propertyFloorRepo;
	  
	  @Autowired
	   	private PropertyOccupancyFactorRepo propertyOccupancyFactorRepo;
	  
	  @Autowired
	   	private OwnerCategoryRepo ownerCategoryRepo;
	  
	  @Autowired
	   	private OwnerTypeRepo ownerTypeRepo;
	  
	  @Autowired
	   	private PropertyCategoryRepo propertyCategoryRepo;
	
	
	  /////////////////////////////////////PropertyAgeFactor Start///////////////////////////////////
    //get all data from table
    @GetMapping("/getPropertyAgeFactorList")
    public ResponseEntity<BaseResponse> getPropertyAgeFactorList() {
        BaseResponse response = new BaseResponse();
        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<PropertyAgeFactor> list = propertyAgeFactorRepo.findAll();
        response.setMessage("success");
        response.setStatus(true);
        response.setTotalDataCount(list.size());
        response.setPropertyAgeFactor(list);
        return ResponseEntity.ok(response);
    }
    
    // Create New Data And Update
    @PostMapping("/submitPropertyAgeFactor")
    public BaseResponse submitPropertyAgeFactor(@RequestBody PropertyAgeFactor propertyAgeFactor, HttpServletRequest request) {
        BaseResponse resultData = new BaseResponse();

        // Check if guid is provided (indicating an update)
        if (propertyAgeFactor.getAgeFactorGuid() == null || propertyAgeFactor.getAgeFactorGuid().isEmpty()) {
            // Add new data
        	propertyAgeFactor.setCreaterIp(request.getRemoteAddr());
        	propertyAgeFactor.setAgeFactorGuid(UUID.randomUUID().toString());
        	propertyAgeFactor.setCreatedDate(new Date());
        	propertyAgeFactor.setModifierIp(null);
        	propertyAgeFactor.setModifiedByGuid(null);
        	propertyAgeFactor.setModifiedDate(null);
        	propertyAgeFactor.setCreatedByGuid(request.getRemoteAddr());

            if (propertyAgeFactor.getIsActive() == null)
            	propertyAgeFactor.setIsActive(false);
//			propertyAgeFactor.setCreaterRemarks(userSessionParam.getUserFullName());
            //propertyAgeFactor.setCreaterMacId(HttpSessionHelper.getMacAddress());
            //propertyAgeFactor.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
        		
        	
        } else {
            // Update existing data
        	PropertyAgeFactor existingPropertyAgeFactor = commonMasterService.getPropertyAgeFactorById(propertyAgeFactor.getAgeFactorGuid());

            if (existingPropertyAgeFactor != null) {
            	existingPropertyAgeFactor.setAgeFactorCode(!Util.isNullOrEmpty(propertyAgeFactor.getAgeFactorCode()) ? propertyAgeFactor.getAgeFactorCode().toUpperCase().trim() : null);
            	existingPropertyAgeFactor.setAgeFactorDesc(!Util.isNullOrEmpty(propertyAgeFactor.getAgeFactorDesc()) ? propertyAgeFactor.getAgeFactorDesc().toUpperCase().trim() : null);
            	existingPropertyAgeFactor.setAgeFactorPeriodFrom(propertyAgeFactor.getAgeFactorPeriodFrom());
            	existingPropertyAgeFactor.setAgeFactorPeriodTo(propertyAgeFactor.getAgeFactorPeriodTo());
             
            	existingPropertyAgeFactor.setIsActive(propertyAgeFactor.getIsActive() != null ? propertyAgeFactor.getIsActive() : existingPropertyAgeFactor.getIsActive());

            	existingPropertyAgeFactor.setModifierIp(request.getRemoteAddr());
            	existingPropertyAgeFactor.setModifiedDate(new Date());
            	//existingPropertyAgeFactor.setCreatedDate(new Date());
                if (existingPropertyAgeFactor.getIsActive() == null)
                	existingPropertyAgeFactor.setIsActive(false);
                // for now setting some dummy value to test
                existingPropertyAgeFactor.setModifiedByGuid(UUID.randomUUID().toString());
                existingPropertyAgeFactor.setModifierMacId(UUID.randomUUID().toString());
                propertyAgeFactor = existingPropertyAgeFactor; // Use the updated existing country object
            } else {
                log.error("Property Age Factor not found");
                resultData.setStatus(false);
                resultData.setMessage("Property Age Factor not found");
                return resultData;
            }
        }

        // Validation
        resultData = validator.validatePropertyAgeFactor(propertyAgeFactor);
        if (resultData != null && !resultData.getStatus()) {
            log.error("Validation failed: {}", resultData.getMessage());
            return resultData;
        }

        // If validation passes, proceed to save or update
        if (propertyAgeFactor.getIsActive() == null) propertyAgeFactor.setIsActive(false);
        propertyAgeFactor.setAgeFactorCode(!Util.isNullOrEmpty(propertyAgeFactor.getAgeFactorCode()) ? propertyAgeFactor.getAgeFactorCode().toUpperCase().trim() : null);
        propertyAgeFactor.setAgeFactorDesc(!Util.isNullOrEmpty(propertyAgeFactor.getAgeFactorDesc()) ? propertyAgeFactor.getAgeFactorDesc().toUpperCase().trim() : null);
        propertyAgeFactor.setAgeFactorPeriodFrom(propertyAgeFactor.getAgeFactorPeriodFrom());
        propertyAgeFactor.setAgeFactorPeriodTo(propertyAgeFactor.getAgeFactorPeriodTo());


        try {
        	propertyAgeFactorRepo.save(propertyAgeFactor);
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
    @GetMapping("/getPropertyAgeFactorByGuid/{ageFactorGuid}")
    public ResponseEntity<PropertyAgeFactor> getPropertyAgeFactorByGuid(@PathVariable("ageFactorGuid") String ageFactorGuid) {
    	PropertyAgeFactor propertyAgeFactor = propertyAgeFactorRepo.findById(ageFactorGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with ageFactorGuid : " + ageFactorGuid));
        return new ResponseEntity<>(propertyAgeFactor, HttpStatus.OK);
    }
    


    /////////////////////////////////////PropertyAgeFactor  End///////////////////////////////////
    
////////////////////////////////////////////PropertyExemption Start //////////////////////////

//get all data from table
@GetMapping("/getPropertyExemptionList")
public ResponseEntity<BaseResponse> getPropertyExemptionList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyExemption> list = propertyExemptionRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyExemption(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyExemption")
public BaseResponse submitPropertyExemption(@RequestBody PropertyExemption propertyExemption,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyExemption.getExemptionGuid() == null || propertyExemption.getExemptionGuid().isEmpty()) {
//Add new data
	propertyExemption.setCreatedIpAddr(request.getRemoteAddr());
	propertyExemption.setExemptionGuid(UUID.randomUUID().toString());
	propertyExemption.setCreatedDate(new Date());
	propertyExemption.setModifiedIpAddr(null);
	propertyExemption.setModifiedByGuid(null);
	propertyExemption.setModifiedDate(null);
	propertyExemption.setCreatedByGuid(request.getRemoteAddr());

if (propertyExemption.getIsActive() == null)
	propertyExemption.setIsActive(false);
//propertyExemption.setCreatedRemarks(userSessionParam.getUserFullName());
//propertyExemption.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyExemption.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//propertyExemption.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	PropertyExemption existingPropertyExemption = commonMasterService
.getPropertyExemptionById(propertyExemption.getExemptionGuid());

if (existingPropertyExemption != null) {

	existingPropertyExemption.setExemptionCode(!Util.isNullOrEmpty(propertyExemption.getExemptionCode())? propertyExemption.getExemptionCode().toUpperCase().trim(): null);

	existingPropertyExemption.setExemptionNameEn(!Util.isNullOrEmpty(propertyExemption.getExemptionNameEn())? propertyExemption.getExemptionNameEn().toUpperCase().trim(): null);

	existingPropertyExemption.setExemptionNameHi(!Util.isNullOrEmpty(propertyExemption.getExemptionNameHi())? propertyExemption.getExemptionNameHi().toUpperCase().trim(): null);

	existingPropertyExemption.setExemptionNameRl(!Util.isNullOrEmpty(propertyExemption.getExemptionNameRl())? propertyExemption.getExemptionNameRl().trim(): null);
	
	existingPropertyExemption.setExemptionDesc(!Util.isNullOrEmpty(propertyExemption.getExemptionDesc())? propertyExemption.getExemptionDesc().trim(): null);

	existingPropertyExemption.setIsActive(propertyExemption.getIsActive() != null ? propertyExemption.getIsActive(): existingPropertyExemption.getIsActive());

	existingPropertyExemption.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyExemption.setModifiedDate(new Date());
if (existingPropertyExemption.getIsActive() == null)
	existingPropertyExemption.setIsActive(false);
//for now setting some dummy value to test
existingPropertyExemption.setModifiedByGuid(UUID.randomUUID().toString());
existingPropertyExemption.setModifiedMacAddr(UUID.randomUUID().toString());
propertyExemption = existingPropertyExemption; // Use the updated existing country object
} else {
log.error("Property Exemption not found");
resultData.setStatus(false);
resultData.setMessage("Property Exemption not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyExemption(propertyExemption);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyExemption.getIsActive() == null)
	propertyExemption.setIsActive(false);

propertyExemption.setExemptionCode(!Util.isNullOrEmpty(propertyExemption.getExemptionCode())? propertyExemption.getExemptionCode().toUpperCase().trim(): null);

propertyExemption.setExemptionNameEn(!Util.isNullOrEmpty(propertyExemption.getExemptionNameEn())? propertyExemption.getExemptionNameEn().toUpperCase().trim(): null);

propertyExemption.setExemptionNameHi(!Util.isNullOrEmpty(propertyExemption.getExemptionNameHi())? propertyExemption.getExemptionNameHi().toUpperCase().trim(): null);

propertyExemption.setExemptionNameRl(!Util.isNullOrEmpty(propertyExemption.getExemptionNameRl())? propertyExemption.getExemptionNameRl().trim(): null);

propertyExemption.setExemptionDesc(!Util.isNullOrEmpty(propertyExemption.getExemptionDesc())? propertyExemption.getExemptionDesc().trim(): null);


try {
	propertyExemptionRepo.save(propertyExemption);
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
@GetMapping("/getPropertyExemptionByGuid/{exemptionGuid}")
public ResponseEntity<PropertyExemption> getPropertyExemptionByGuid(
@PathVariable("exemptionGuid") String exemptionGuid) {
	PropertyExemption propertyExemption = propertyExemptionRepo.findById(exemptionGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with exemptionGuid : " + exemptionGuid));
return new ResponseEntity<>(propertyExemption, HttpStatus.OK);
}

////////////////////////////////////////////PropertyExemption End //////////////////////////
    
	
////////////////////////////////////////////PropertyFloor Start //////////////////////////

//get all data from table
@GetMapping("/getPropertyFloorList")
public ResponseEntity<BaseResponse> getPropertyFloorList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyFloor> list = propertyFloorRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyFloor(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyFloor")
public BaseResponse submitPropertyFloor(@RequestBody PropertyFloor propertyFloor,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyFloor.getFloorGuid() == null || propertyFloor.getFloorGuid().isEmpty()) {
//Add new data
	propertyFloor.setCreatedIpAddr(request.getRemoteAddr());
	propertyFloor.setFloorGuid(UUID.randomUUID().toString());
	propertyFloor.setCreatedDate(new Date());
propertyFloor.setModifiedIpAddr(null);
propertyFloor.setModifiedByGuid(null);
propertyFloor.setModifiedDate(null);
propertyFloor.setCreatedByGuid(request.getRemoteAddr());

if (propertyFloor.getIsActive() == null)
	propertyFloor.setIsActive(false);
//propertyFloor.setCreatedRemarks(userSessionParam.getUserFullName());
//propertyFloor.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyFloor.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//propertyFloor.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	PropertyFloor existingPropertyFloor = commonMasterService
.getPropertyFloorById(propertyFloor.getFloorGuid());

if (existingPropertyFloor != null) {

	existingPropertyFloor.setFloorCode(!Util.isNullOrEmpty(propertyFloor.getFloorCode())? propertyFloor.getFloorCode().toUpperCase().trim(): null);

	existingPropertyFloor.setFloorNameEn(!Util.isNullOrEmpty(propertyFloor.getFloorNameEn())? propertyFloor.getFloorNameEn().toUpperCase().trim(): null);

	existingPropertyFloor.setFloorNameHi(!Util.isNullOrEmpty(propertyFloor.getFloorNameHi())? propertyFloor.getFloorNameHi().toUpperCase().trim(): null);

	existingPropertyFloor.setFloorNameRl(!Util.isNullOrEmpty(propertyFloor.getFloorNameRl())? propertyFloor.getFloorNameRl().trim(): null);

	existingPropertyFloor.setFloorDesc(!Util.isNullOrEmpty(propertyFloor.getFloorDesc())? propertyFloor.getFloorDesc().trim(): null);

	existingPropertyFloor.setIsActive(propertyFloor.getIsActive() != null ? propertyFloor.getIsActive(): existingPropertyFloor.getIsActive());

	existingPropertyFloor.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyFloor.setModifiedDate(new Date());
if (existingPropertyFloor.getIsActive() == null)
	existingPropertyFloor.setIsActive(false);
//for now setting some dummy value to test
existingPropertyFloor.setModifiedByGuid(UUID.randomUUID().toString());
existingPropertyFloor.setModifiedMacAddr(UUID.randomUUID().toString());
propertyFloor = existingPropertyFloor; // Use the updated existing country object
} else {
log.error("Property Floor not found");
resultData.setStatus(false);
resultData.setMessage("Property Floor not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyFloor(propertyFloor);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyFloor.getIsActive() == null)
	propertyFloor.setIsActive(false);

propertyFloor.setFloorCode(!Util.isNullOrEmpty(propertyFloor.getFloorCode())? propertyFloor.getFloorCode().toUpperCase().trim(): null);

propertyFloor.setFloorNameEn(!Util.isNullOrEmpty(propertyFloor.getFloorNameEn())? propertyFloor.getFloorNameEn().toUpperCase().trim(): null);

propertyFloor.setFloorNameHi(!Util.isNullOrEmpty(propertyFloor.getFloorNameHi())? propertyFloor.getFloorNameHi().toUpperCase().trim(): null);

propertyFloor.setFloorNameRl(!Util.isNullOrEmpty(propertyFloor.getFloorNameRl())? propertyFloor.getFloorNameRl().trim(): null);

propertyFloor.setFloorDesc(!Util.isNullOrEmpty(propertyFloor.getFloorDesc())? propertyFloor.getFloorDesc().trim(): null);



try {
	propertyFloorRepo.save(propertyFloor);
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
@GetMapping("/getPropertyFloorByGuid/{floorGuid}")
public ResponseEntity<PropertyFloor> getPropertyFloorByGuid(
@PathVariable("floorGuid") String floorGuid) {
	PropertyFloor propertyFloor = propertyFloorRepo.findById(floorGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with floorGuid : " + floorGuid));
return new ResponseEntity<>(propertyFloor, HttpStatus.OK);
}

////////////////////////////////////////////PropertyFloor End //////////////////////////
	
////////////////////////////////////////////PropertyOccupancyFactor Start //////////////////////////

//get all data from table
@GetMapping("/getPropertyOccupancyFactorList")
public ResponseEntity<BaseResponse> getPropertyOccupancyFactorList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyOccupancyFactor> list = propertyOccupancyFactorRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyOccupancyFactor(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyOccupancyFactor")
public BaseResponse submitPropertyOccupancyFactor(@RequestBody PropertyOccupancyFactor propertyOccupancyFactor,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyOccupancyFactor.getOccupancyFactorGuid() == null || propertyOccupancyFactor.getOccupancyFactorGuid().isEmpty()) {
//Add new data
	propertyOccupancyFactor.setCreatedIpAddr(request.getRemoteAddr());
	propertyOccupancyFactor.setOccupancyFactorGuid(UUID.randomUUID().toString());
	propertyOccupancyFactor.setCreatedDate(new Date());
	propertyOccupancyFactor.setModifiedIpAddr(null);
	propertyOccupancyFactor.setModifiedByGuid(null);
propertyOccupancyFactor.setModifiedDate(null);
propertyOccupancyFactor.setCreatedByGuid(request.getRemoteAddr());

if (propertyOccupancyFactor.getIsActive() == null)
	propertyOccupancyFactor.setIsActive(false);
//propertyOccupancyFactor.setCreatedRemarks(userSessionParam.getUserFullName());
//propertyOccupancyFactor.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyOccupancyFactor.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//propertyOccupancyFactor.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	PropertyOccupancyFactor existingPropertyOccupancyFactor = commonMasterService
.getPropertyOccupancyFactorById(propertyOccupancyFactor.getOccupancyFactorGuid());

if (existingPropertyOccupancyFactor != null) {

	existingPropertyOccupancyFactor.setOccupancyFactorCode(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorCode())? propertyOccupancyFactor.getOccupancyFactorCode().toUpperCase().trim(): null);

	existingPropertyOccupancyFactor.setOccupancyFactorNameEn(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorNameEn())? propertyOccupancyFactor.getOccupancyFactorNameEn().toUpperCase().trim(): null);

	existingPropertyOccupancyFactor.setOccupancyFactorNameHi(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorNameHi())? propertyOccupancyFactor.getOccupancyFactorNameHi().toUpperCase().trim(): null);

	existingPropertyOccupancyFactor.setOccupancyFactorNameRl(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorNameRl())? propertyOccupancyFactor.getOccupancyFactorNameRl().trim(): null);

	existingPropertyOccupancyFactor.setOccupancyFactorType(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorType())? propertyOccupancyFactor.getOccupancyFactorType().trim(): null);

	existingPropertyOccupancyFactor.setOccupancyFactorDesc(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorDesc())? propertyOccupancyFactor.getOccupancyFactorDesc().trim(): null);
	
	existingPropertyOccupancyFactor.setIsActive(propertyOccupancyFactor.getIsActive() != null ? propertyOccupancyFactor.getIsActive(): existingPropertyOccupancyFactor.getIsActive());

	existingPropertyOccupancyFactor.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyOccupancyFactor.setModifiedDate(new Date());
if (existingPropertyOccupancyFactor.getIsActive() == null)
	existingPropertyOccupancyFactor.setIsActive(false);
//for now setting some dummy value to test
existingPropertyOccupancyFactor.setModifiedByGuid(UUID.randomUUID().toString());
existingPropertyOccupancyFactor.setModifiedMacAddr(UUID.randomUUID().toString());
propertyOccupancyFactor = existingPropertyOccupancyFactor; // Use the updated existing country object
} else {
log.error("Property Occupancy Factor not found");
resultData.setStatus(false);
resultData.setMessage("Property Occupancy Factor not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyOccupancyFactor(propertyOccupancyFactor);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyOccupancyFactor.getIsActive() == null)
	propertyOccupancyFactor.setIsActive(false);

propertyOccupancyFactor.setOccupancyFactorCode(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorCode())? propertyOccupancyFactor.getOccupancyFactorCode().toUpperCase().trim(): null);

propertyOccupancyFactor.setOccupancyFactorNameEn(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorNameEn())? propertyOccupancyFactor.getOccupancyFactorNameEn().toUpperCase().trim(): null);

propertyOccupancyFactor.setOccupancyFactorNameHi(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorNameHi())? propertyOccupancyFactor.getOccupancyFactorNameHi().toUpperCase().trim(): null);

propertyOccupancyFactor.setOccupancyFactorNameRl(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorNameRl())? propertyOccupancyFactor.getOccupancyFactorNameRl().trim(): null);

propertyOccupancyFactor.setOccupancyFactorType(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorType())? propertyOccupancyFactor.getOccupancyFactorType().trim(): null);

propertyOccupancyFactor.setOccupancyFactorDesc(!Util.isNullOrEmpty(propertyOccupancyFactor.getOccupancyFactorDesc())? propertyOccupancyFactor.getOccupancyFactorDesc().trim(): null);


try {
	propertyOccupancyFactorRepo.save(propertyOccupancyFactor);
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
@GetMapping("/getPropertyOccupancyFactorByGuid/{occupancyFactorGuid}")
public ResponseEntity<PropertyOccupancyFactor> getPropertyOccupancyFactorByGuid(
@PathVariable("occupancyFactorGuid") String occupancyFactorGuid) {
	PropertyOccupancyFactor propertyOccupancyFactor = propertyOccupancyFactorRepo.findById(occupancyFactorGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with floorGuid : " + occupancyFactorGuid));
return new ResponseEntity<>(propertyOccupancyFactor, HttpStatus.OK);
}

////////////////////////////////////////////PropertyOccupancyFactor End //////////////////////////

////////////////////////////////////////////OwnerCategory Start //////////////////////////

//get all data from table
@GetMapping("/getOwnerCategoryList")
public ResponseEntity<BaseResponse> getOwnerCategoryList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<OwnerCategory> list = ownerCategoryRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setOwnerCategory(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitOwnerCategory")
public BaseResponse submitOwnerCategory(@RequestBody OwnerCategory ownerCategory,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (ownerCategory.getOwnerCategoryGuid() == null || ownerCategory.getOwnerCategoryGuid().isEmpty()) {
//Add new data
	ownerCategory.setCreatedIpAddr(request.getRemoteAddr());
	ownerCategory.setOwnerCategoryGuid(UUID.randomUUID().toString());
	ownerCategory.setCreatedDate(new Date());
	ownerCategory.setModifiedIpAddr(null);
	ownerCategory.setModifiedByGuid(null);
	ownerCategory.setModifiedDate(null);
	ownerCategory.setCreatedByGuid(request.getRemoteAddr());

if (ownerCategory.getIsActive() == null)
	ownerCategory.setIsActive(false);
//ownerCategory.setCreatedRemarks(userSessionParam.getUserFullName());
//ownerCategory.setCreaterMacId(HttpSessionHelper.getMacAddress());
//ownerCategory.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//ownerCategory.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	OwnerCategory existingOwnerCategory = commonMasterService
.getOwnerCategoryById(ownerCategory.getOwnerCategoryGuid());

if (existingOwnerCategory != null) {

	existingOwnerCategory.setOwnerCategoryCode(!Util.isNullOrEmpty(ownerCategory.getOwnerCategoryCode())? ownerCategory.getOwnerCategoryCode().toUpperCase().trim(): null);

	existingOwnerCategory.setOwnerCategoryNameEn(!Util.isNullOrEmpty(ownerCategory.getOwnerCategoryNameEn())? ownerCategory.getOwnerCategoryNameEn().toUpperCase().trim(): null);

	existingOwnerCategory.setOwnerCategoryNameHi(!Util.isNullOrEmpty(ownerCategory.getOwnerCategoryNameHi())? ownerCategory.getOwnerCategoryNameHi().toUpperCase().trim(): null);

	existingOwnerCategory.setOwnerCategoryNameRl(!Util.isNullOrEmpty(ownerCategory.getOwnerCategoryNameRl())? ownerCategory.getOwnerCategoryNameRl().trim(): null);

	existingOwnerCategory.setOwnerCategoryDesc(!Util.isNullOrEmpty(ownerCategory.getOwnerCategoryDesc())? ownerCategory.getOwnerCategoryDesc().trim(): null);

	existingOwnerCategory.setIsActive(ownerCategory.getIsActive() != null ? ownerCategory.getIsActive(): existingOwnerCategory.getIsActive());

	existingOwnerCategory.setModifiedIpAddr(request.getRemoteAddr());
	existingOwnerCategory.setModifiedDate(new Date());
if (existingOwnerCategory.getIsActive() == null)
	existingOwnerCategory.setIsActive(false);
//for now setting some dummy value to test
existingOwnerCategory.setModifiedByGuid(UUID.randomUUID().toString());
existingOwnerCategory.setModifiedMacAddr(UUID.randomUUID().toString());
ownerCategory = existingOwnerCategory; // Use the updated existing country object
} else {
log.error("Owner Category not found");
resultData.setStatus(false);
resultData.setMessage("Owner Category not found");
return resultData;
}
}

//Validation
resultData = validator.validateOwnerCategory(ownerCategory);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (ownerCategory.getIsActive() == null)
	ownerCategory.setIsActive(false);

ownerCategory.setOwnerCategoryCode(!Util.isNullOrEmpty(ownerCategory.getOwnerCategoryCode())? ownerCategory.getOwnerCategoryCode().toUpperCase().trim(): null);

ownerCategory.setOwnerCategoryNameEn(!Util.isNullOrEmpty(ownerCategory.getOwnerCategoryNameEn())? ownerCategory.getOwnerCategoryNameEn().toUpperCase().trim(): null);

ownerCategory.setOwnerCategoryNameHi(!Util.isNullOrEmpty(ownerCategory.getOwnerCategoryNameHi())? ownerCategory.getOwnerCategoryNameHi().toUpperCase().trim(): null);

ownerCategory.setOwnerCategoryNameRl(!Util.isNullOrEmpty(ownerCategory.getOwnerCategoryNameRl())? ownerCategory.getOwnerCategoryNameRl().trim(): null);

ownerCategory.setOwnerCategoryDesc(!Util.isNullOrEmpty(ownerCategory.getOwnerCategoryDesc())? ownerCategory.getOwnerCategoryDesc().trim(): null);



try {
	ownerCategoryRepo.save(ownerCategory);
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
@GetMapping("/getOwnerCategoryByGuid/{ownerCategoryGuid}")
public ResponseEntity<OwnerCategory> getOwnerCategoryByGuid(
@PathVariable("ownerCategoryGuid") String ownerCategoryGuid) {
	OwnerCategory ownerCategory = ownerCategoryRepo.findById(ownerCategoryGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with floorGuid : " + ownerCategoryGuid));
return new ResponseEntity<>(ownerCategory, HttpStatus.OK);
}

////////////////////////////////////////////OwnerCategory End //////////////////////////

/////////////////////////////////////OwnerType Start///////////////////////////////////

//get all data from table
@GetMapping("/getOwnerTypeList")
public ResponseEntity<BaseResponse> getOwnerTypeList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<OwnerType> list = ownerTypeRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setOwnerType(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitOwnerType")
public BaseResponse submitOwnerType(@RequestBody OwnerType ownerType, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (ownerType.getOwnerTypeGuid() == null || ownerType.getOwnerTypeGuid().isEmpty()) {

//Add new data
	ownerType.setCreatedIpAddr(request.getRemoteAddr());
	ownerType.setOwnerTypeGuid(UUID.randomUUID().toString());
ownerType.setCreatedDate(new Date());
ownerType.setModifiedIpAddr(null);
ownerType.setModifiedByGuid(null);
ownerType.setModifiedDate(null);
ownerType.setCreatedByGuid(request.getRemoteAddr());

//for dropdown
ownerType.setOwnerCategory(ownerType.getOwnerCategoryGuid());

if (ownerType.getOwnerCategory() != null && !ownerType.getOwnerCategory().isEmpty()) {
	ownerType.setOwnerCategoryMaster(new OwnerCategory(ownerType.getOwnerCategory()));
}

if (ownerType.getHaveMultipleOwner() == null)
	ownerType.setHaveMultipleOwner(false);

if (ownerType.getIsActive() == null)
	ownerType.setIsActive(false);
//ownerType.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//ownerType.setCreaterRemarks(userSessionParam.getUserFullName());
//ownerType.setCreaterMacId(HttpSessionHelper.getMacAddress());
//ownerType.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	OwnerType existingOwnerType = commonMasterService.getOwnerTypeById(ownerType.getOwnerTypeGuid());

if (existingOwnerType != null) {
	existingOwnerType.setOwnerTypeCode(!Util.isNullOrEmpty(ownerType.getOwnerTypeCode()) ? ownerType.getOwnerTypeCode().toUpperCase().trim() : null);
	existingOwnerType.setOwnerTypeNameEn(!Util.isNullOrEmpty(ownerType.getOwnerTypeNameEn()) ? ownerType.getOwnerTypeNameEn().toUpperCase().trim() : null);

	existingOwnerType.setOwnerTypeNameHi(!Util.isNullOrEmpty(ownerType.getOwnerTypeNameHi()) ? ownerType.getOwnerTypeNameHi().toUpperCase().trim() : null);
	existingOwnerType.setOwnerTypeNameRl(!Util.isNullOrEmpty(ownerType.getOwnerTypeNameRl()) ? ownerType.getOwnerTypeNameRl() : null);
	existingOwnerType.setOwnerTypeDesc(!Util.isNullOrEmpty(ownerType.getOwnerTypeDesc()) ? ownerType.getOwnerTypeDesc().trim() : null);
	existingOwnerType.setHaveMultipleOwner(ownerType.getHaveMultipleOwner() != null ? ownerType.getHaveMultipleOwner() : existingOwnerType.getIsActive());
	existingOwnerType.setIsActive(ownerType.getIsActive() != null ? ownerType.getIsActive() : existingOwnerType.getIsActive());

	existingOwnerType.setModifiedIpAddr(request.getRemoteAddr());
	existingOwnerType.setModifiedDate(new Date());
	existingOwnerType.setModifiedByGuid("admin");

//dropdown
	existingOwnerType.setOwnerCategory(ownerType.getOwnerCategoryGuid());
if (existingOwnerType.getOwnerCategory() != null && !existingOwnerType.getOwnerCategory().isEmpty()) {
	existingOwnerType.setOwnerCategoryMaster(new OwnerCategory(existingOwnerType.getOwnerCategory()));
}




if (existingOwnerType.getHaveMultipleOwner() == null)
	existingOwnerType.setHaveMultipleOwner(false);

if (existingOwnerType.getIsActive() == null)
	existingOwnerType.setIsActive(false);

ownerType = existingOwnerType; // Use the updated existing country object
} else {
log.error("Owner Type not found");
resultData.setStatus(false);
resultData.setMessage("Owner Type not found");
return resultData;
}
}

//Validation
resultData = validator.validateOwnerType(ownerType);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (ownerType.getIsActive() == null) ownerType.setIsActive(false);
if (ownerType.getHaveMultipleOwner() == null) ownerType.setHaveMultipleOwner(false);
ownerType.setOwnerTypeCode(!Util.isNullOrEmpty(ownerType.getOwnerTypeCode()) ? ownerType.getOwnerTypeCode().toUpperCase().trim() : null);
ownerType.setOwnerTypeNameEn(!Util.isNullOrEmpty(ownerType.getOwnerTypeNameEn()) ? ownerType.getOwnerTypeNameEn().toUpperCase().trim() : null);

ownerType.setOwnerTypeNameHi(!Util.isNullOrEmpty(ownerType.getOwnerTypeNameHi()) ? ownerType.getOwnerTypeNameHi().toUpperCase().trim() : null);
ownerType.setOwnerTypeNameRl(!Util.isNullOrEmpty(ownerType.getOwnerTypeNameRl()) ? ownerType.getOwnerTypeNameRl() : null);
ownerType.setOwnerTypeDesc(!Util.isNullOrEmpty(ownerType.getOwnerTypeDesc()) ? ownerType.getOwnerTypeDesc().trim() : null);
if (ownerType.getIsActive() == null) ownerType.setIsActive(false);
try {
	ownerTypeRepo.save(ownerType);
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
@GetMapping("/getOwnerTypeByGuid/{ownerTypeGuid}")
public ResponseEntity<OwnerType> getOwnerTypeByGuid(@PathVariable("ownerTypeGuid") String ownerTypeGuid) {
	OwnerType ownerType = ownerTypeRepo.findById(ownerTypeGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with ownerTypeGuid : " + ownerTypeGuid));
return new ResponseEntity<>(ownerType, HttpStatus.OK);
}

/////////////////////////////////////OwnerType End///////////////////////////////////

////////////////////////////////////////////PropertyFloor Start //////////////////////////

//get all data from table
@GetMapping("/getPropertyCategoryList")
public ResponseEntity<BaseResponse> getPropertyCategoryList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyCategory> list = propertyCategoryRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyCategory(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyCategory")
public BaseResponse submitPropertyCategory(@RequestBody PropertyCategory propertyCategory,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyCategory.getPropertyCategoryGuid() == null || propertyCategory.getPropertyCategoryGuid().isEmpty()) {
//Add new data
	propertyCategory.setCreatedIpAddr(request.getRemoteAddr());
	propertyCategory.setPropertyCategoryGuid(UUID.randomUUID().toString());
	propertyCategory.setCreatedDate(new Date());
	propertyCategory.setModifiedIpAddr(null);
	propertyCategory.setModifiedByGuid(null);
	propertyCategory.setModifiedDate(null);
	propertyCategory.setCreatedByGuid(request.getRemoteAddr());

if (propertyCategory.getIsActive() == null)
	propertyCategory.setIsActive(false);
//propertyCategory.setCreatedRemarks(userSessionParam.getUserFullName());
//propertyCategory.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyCategory.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//propertyCategory.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	PropertyCategory existingPropertyCategory = commonMasterService
.getPropertyCategoryById(propertyCategory.getPropertyCategoryGuid());

if (existingPropertyCategory != null) {

	existingPropertyCategory.setPropertyCategoryCode(!Util.isNullOrEmpty(propertyCategory.getPropertyCategoryCode())? propertyCategory.getPropertyCategoryCode().toUpperCase().trim(): null);

	existingPropertyCategory.setPropertyCategoryNameEn(!Util.isNullOrEmpty(propertyCategory.getPropertyCategoryNameEn())? propertyCategory.getPropertyCategoryNameEn().toUpperCase().trim(): null);

	existingPropertyCategory.setPropertyCategoryNameHi(!Util.isNullOrEmpty(propertyCategory.getPropertyCategoryNameHi())? propertyCategory.getPropertyCategoryNameHi().toUpperCase().trim(): null);

	existingPropertyCategory.setPropertyCategoryNameRl(!Util.isNullOrEmpty(propertyCategory.getPropertyCategoryNameRl())? propertyCategory.getPropertyCategoryNameRl().trim(): null);

	existingPropertyCategory.setPropertyCategoryDesc(!Util.isNullOrEmpty(propertyCategory.getPropertyCategoryDesc())? propertyCategory.getPropertyCategoryDesc().trim(): null);

	existingPropertyCategory.setIsActive(propertyCategory.getIsActive() != null ? propertyCategory.getIsActive(): existingPropertyCategory.getIsActive());

	existingPropertyCategory.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyCategory.setModifiedDate(new Date());
if (existingPropertyCategory.getIsActive() == null)
	existingPropertyCategory.setIsActive(false);
//for now setting some dummy value to test
existingPropertyCategory.setModifiedByGuid(UUID.randomUUID().toString());
existingPropertyCategory.setModifiedMacAddr(UUID.randomUUID().toString());
propertyCategory = existingPropertyCategory; // Use the updated existing country object
} else {
log.error("Property Category not found");
resultData.setStatus(false);
resultData.setMessage("Property Category not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyCategory(propertyCategory);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyCategory.getIsActive() == null)
	propertyCategory.setIsActive(false);

propertyCategory.setPropertyCategoryCode(!Util.isNullOrEmpty(propertyCategory.getPropertyCategoryCode())? propertyCategory.getPropertyCategoryCode().toUpperCase().trim(): null);

propertyCategory.setPropertyCategoryNameEn(!Util.isNullOrEmpty(propertyCategory.getPropertyCategoryNameEn())? propertyCategory.getPropertyCategoryNameEn().toUpperCase().trim(): null);

propertyCategory.setPropertyCategoryNameHi(!Util.isNullOrEmpty(propertyCategory.getPropertyCategoryNameHi())? propertyCategory.getPropertyCategoryNameHi().toUpperCase().trim(): null);

propertyCategory.setPropertyCategoryNameRl(!Util.isNullOrEmpty(propertyCategory.getPropertyCategoryNameRl())? propertyCategory.getPropertyCategoryNameRl().trim(): null);

propertyCategory.setPropertyCategoryDesc(!Util.isNullOrEmpty(propertyCategory.getPropertyCategoryDesc())? propertyCategory.getPropertyCategoryDesc().trim(): null);



try {
	propertyCategoryRepo.save(propertyCategory);
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
@GetMapping("/getPropertyCategoryByGuid/{propertyCategoryGuid}")
public ResponseEntity<PropertyCategory> getPropertyCategorByGuid(
@PathVariable("propertyCategoryGuid") String propertyCategoryGuid) {
	PropertyCategory propertyCategory = propertyCategoryRepo.findById(propertyCategoryGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with propertyCategoryGuid : " + propertyCategoryGuid));
return new ResponseEntity<>(propertyCategory, HttpStatus.OK);
}

////////////////////////////////////////////PropertyCategory End //////////////////////////
}
