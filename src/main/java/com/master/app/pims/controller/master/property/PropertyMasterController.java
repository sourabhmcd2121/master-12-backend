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
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeClassification;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeType;
import com.master.app.pims.entities.schemas.property.ManualReceiptSeries;
import com.master.app.pims.entities.schemas.property.OwnerCategory;
import com.master.app.pims.entities.schemas.property.OwnerType;
import com.master.app.pims.entities.schemas.property.PropertyAgeFactor;
import com.master.app.pims.entities.schemas.property.PropertyCategory;
import com.master.app.pims.entities.schemas.property.PropertyExemption;
import com.master.app.pims.entities.schemas.property.PropertyFloor;
import com.master.app.pims.entities.schemas.property.PropertyMasterRebate;
import com.master.app.pims.entities.schemas.property.PropertyMstSr;
import com.master.app.pims.entities.schemas.property.PropertyOccupancyFactor;
import com.master.app.pims.entities.schemas.property.PropertyOtherCharges;
import com.master.app.pims.entities.schemas.property.PropertyStructureFactor;
import com.master.app.pims.entities.schemas.property.PropertyTaxCategory;
import com.master.app.pims.entities.schemas.property.PropertyType;
import com.master.app.pims.entities.schemas.property.PropertyUseFactor;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.property.ManualReceiptSeriesRepo;
import com.master.app.pims.repositories.property.OwnerCategoryRepo;
import com.master.app.pims.repositories.property.OwnerTypeRepo;
import com.master.app.pims.repositories.property.PropertyAgeFactorRepo;
import com.master.app.pims.repositories.property.PropertyCategoryRepo;
import com.master.app.pims.repositories.property.PropertyExemptionRepo;
import com.master.app.pims.repositories.property.PropertyFloorRepo;
import com.master.app.pims.repositories.property.PropertyMasterRebateRepo;
import com.master.app.pims.repositories.property.PropertyMstSrRepo;
import com.master.app.pims.repositories.property.PropertyOccupancyFactorRepo;
import com.master.app.pims.repositories.property.PropertyOtherChargesRepo;
import com.master.app.pims.repositories.property.PropertyStructureFactorRepo;
import com.master.app.pims.repositories.property.PropertyTaxCategoryRepo;
import com.master.app.pims.repositories.property.PropertyTypeRepo;
import com.master.app.pims.repositories.property.PropertyUseFactorRepo;
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
	
	private Logger logger = LoggerFactory.getLogger(PropertyMasterController.class);
	
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
	  
	  @Autowired
	   	private PropertyTypeRepo propertyTypeRepo;
	 
	  @Autowired
	   	private PropertyStructureFactorRepo propertyStructureFactorRepo;
	  
	  @Autowired
	   	private PropertyTaxCategoryRepo propertyTaxCategoryRepo;
	
	  @Autowired
	   	private PropertyUseFactorRepo propertyUseFactorRepo;
	  
	  @Autowired
	   	private PropertyMasterRebateRepo propertyMasterRebateRepo;
	  
	  @Autowired
	   	private PropertyOtherChargesRepo propertyOtherChargesRepo;
	  
	  @Autowired
	   	private ManualReceiptSeriesRepo manualReceiptSeriesRepo;
	  
	  @Autowired
	   	private PropertyMstSrRepo propertyMstSrRepo;
	  
	  
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

////////////////////////////////////////////PropertyCategory Start //////////////////////////

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

/////////////////////////////////////PropertyType Start///////////////////////////////////

//get all data from table
@GetMapping("/getPropertyTypeList")
public ResponseEntity<BaseResponse> getPropertyTypeList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyType> list = propertyTypeRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyType(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyType")
public BaseResponse submitPropertyType(@RequestBody PropertyType propertyType, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyType.getPropertyTypeGuid() == null || propertyType.getPropertyTypeGuid().isEmpty()) {

//Add new data
	propertyType.setCreatedIpAddr(request.getRemoteAddr());
	propertyType.setPropertyTypeGuid(UUID.randomUUID().toString());
	propertyType.setCreatedDate(new Date());
	propertyType.setModifiedIpAddr(null);
	propertyType.setModifiedByGuid(null);
	propertyType.setModifiedDate(null);
	propertyType.setCreatedByGuid(request.getRemoteAddr());


if (propertyType.getIsHeadquarter() == null)
	propertyType.setIsHeadquarter(false);

if (propertyType.getIsActive() == null)
	propertyType.setIsActive(false);
//propertyType.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//propertyType.setCreaterRemarks(userSessionParam.getUserFullName());
//propertyType.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyType.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	PropertyType existingPropertyType = commonMasterService.getPropertyTypeById(propertyType.getPropertyTypeGuid());

if (existingPropertyType != null) {
	existingPropertyType.setPropertyTypeCode(!Util.isNullOrEmpty(propertyType.getPropertyTypeCode()) ? propertyType.getPropertyTypeCode().toUpperCase().trim() : null);
	existingPropertyType.setPropertyTypeNameEn(!Util.isNullOrEmpty(propertyType.getPropertyTypeNameEn()) ? propertyType.getPropertyTypeNameEn().toUpperCase().trim() : null);

	existingPropertyType.setPropertyTypeNameHi(!Util.isNullOrEmpty(propertyType.getPropertyTypeNameHi()) ? propertyType.getPropertyTypeNameHi().toUpperCase().trim() : null);
	existingPropertyType.setPropertyTypeNameRl(!Util.isNullOrEmpty(propertyType.getPropertyTypeNameRl()) ? propertyType.getPropertyTypeNameRl() : null);
	existingPropertyType.setPropertyTypeDesc(!Util.isNullOrEmpty(propertyType.getPropertyTypeDesc()) ? propertyType.getPropertyTypeDesc().trim() : null);
	existingPropertyType.setIsHeadquarter(propertyType.getIsHeadquarter() != null ? propertyType.getIsHeadquarter() : existingPropertyType.getIsActive());
	existingPropertyType.setIsActive(propertyType.getIsActive() != null ? propertyType.getIsActive() : existingPropertyType.getIsActive());

	existingPropertyType.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyType.setModifiedDate(new Date());
	existingPropertyType.setModifiedByGuid("admin");



if (existingPropertyType.getIsHeadquarter() == null)
	existingPropertyType.setIsHeadquarter(false);

if (existingPropertyType.getIsActive() == null)
	existingPropertyType.setIsActive(false);

propertyType = existingPropertyType; // Use the updated existing country object
} else {
log.error("Property Type not found");
resultData.setStatus(false);
resultData.setMessage("Property Type not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyType(propertyType);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyType.getIsActive() == null) propertyType.setIsActive(false);
if (propertyType.getIsHeadquarter() == null) propertyType.setIsHeadquarter(false);

propertyType.setPropertyTypeCode(!Util.isNullOrEmpty(propertyType.getPropertyTypeCode()) ? propertyType.getPropertyTypeCode().toUpperCase().trim() : null);
propertyType.setPropertyTypeNameEn(!Util.isNullOrEmpty(propertyType.getPropertyTypeNameEn()) ? propertyType.getPropertyTypeNameEn().toUpperCase().trim() : null);

propertyType.setPropertyTypeNameHi(!Util.isNullOrEmpty(propertyType.getPropertyTypeNameHi()) ? propertyType.getPropertyTypeNameHi().toUpperCase().trim() : null);
propertyType.setPropertyTypeNameRl(!Util.isNullOrEmpty(propertyType.getPropertyTypeNameRl()) ? propertyType.getPropertyTypeNameRl() : null);
propertyType.setPropertyTypeDesc(!Util.isNullOrEmpty(propertyType.getPropertyTypeDesc()) ? propertyType.getPropertyTypeDesc().trim() : null);

if (propertyType.getIsActive() == null) propertyType.setIsActive(false);
try {
	propertyTypeRepo.save(propertyType);
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
@GetMapping("/getPropertyTypeByGuid/{propertyTypeGuid}")
public ResponseEntity<PropertyType> getPropertyTypeGuid(@PathVariable("propertyTypeGuid") String propertyTypeGuid) {
	PropertyType propertyType = propertyTypeRepo.findById(propertyTypeGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with propertyTypeGuid : " + propertyTypeGuid));
return new ResponseEntity<>(propertyType, HttpStatus.OK);
}

/////////////////////////////////////PropertyType End///////////////////////////////////

////////////////////////////////////////////PropertyStructureFactor Start //////////////////////////

//get all data from table
@GetMapping("/getPropertyStructureFactorList")
public ResponseEntity<BaseResponse> getPropertyStructureFactorList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyStructureFactor> list = propertyStructureFactorRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyStructureFactor(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyStructureFactor")
public BaseResponse submitPropertyStructureFactor(@RequestBody PropertyStructureFactor propertyStructureFactor,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyStructureFactor.getStructureFactorGuid() == null || propertyStructureFactor.getStructureFactorGuid().isEmpty()) {
//Add new data
	propertyStructureFactor.setCreatedIpAddr(request.getRemoteAddr());
	propertyStructureFactor.setStructureFactorGuid(UUID.randomUUID().toString());
	propertyStructureFactor.setCreatedDate(new Date());
	propertyStructureFactor.setModifiedIpAddr(null);
	propertyStructureFactor.setModifiedByGuid(null);
	propertyStructureFactor.setModifiedDate(null);
	propertyStructureFactor.setCreatedByGuid(request.getRemoteAddr());

if (propertyStructureFactor.getIsActive() == null)
	propertyStructureFactor.setIsActive(false);
//propertyStructureFactor.setCreatedRemarks(userSessionParam.getUserFullName());
//propertyStructureFactor.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyStructureFactor.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//propertyStructureFactor.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	PropertyStructureFactor existingPropertyStructureFactor = commonMasterService
.getPropertyStructureFactorById(propertyStructureFactor.getStructureFactorGuid());

if (existingPropertyStructureFactor != null) {

	existingPropertyStructureFactor.setStructureFactorCode(!Util.isNullOrEmpty(propertyStructureFactor.getStructureFactorCode())? propertyStructureFactor.getStructureFactorCode().toUpperCase().trim(): null);

	existingPropertyStructureFactor.setStructureFactorNameEn(!Util.isNullOrEmpty(propertyStructureFactor.getStructureFactorNameEn())? propertyStructureFactor.getStructureFactorNameEn().toUpperCase().trim(): null);

	existingPropertyStructureFactor.setStructureFactorNameHi(!Util.isNullOrEmpty(propertyStructureFactor.getStructureFactorNameHi())? propertyStructureFactor.getStructureFactorNameHi().toUpperCase().trim(): null);

	existingPropertyStructureFactor.setStructureFactorNameRl(!Util.isNullOrEmpty(propertyStructureFactor.getStructureFactorNameRl())? propertyStructureFactor.getStructureFactorNameRl().trim(): null);

	existingPropertyStructureFactor.setStructureFactorDesc(!Util.isNullOrEmpty(propertyStructureFactor.getStructureFactorDesc())? propertyStructureFactor.getStructureFactorDesc().trim(): null);

	existingPropertyStructureFactor.setIsActive(propertyStructureFactor.getIsActive() != null ? propertyStructureFactor.getIsActive(): existingPropertyStructureFactor.getIsActive());

	existingPropertyStructureFactor.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyStructureFactor.setModifiedDate(new Date());
if (existingPropertyStructureFactor.getIsActive() == null)
	existingPropertyStructureFactor.setIsActive(false);
//for now setting some dummy value to test
existingPropertyStructureFactor.setModifiedByGuid(UUID.randomUUID().toString());
existingPropertyStructureFactor.setModifiedMacAddr(UUID.randomUUID().toString());
propertyStructureFactor = existingPropertyStructureFactor; // Use the updated existing country object
} else {
log.error("Structure Factor not found");
resultData.setStatus(false);
resultData.setMessage("Property Structure Factor  not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyStructureFactor(propertyStructureFactor);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyStructureFactor.getIsActive() == null)
	propertyStructureFactor.setIsActive(false);

propertyStructureFactor.setStructureFactorCode(!Util.isNullOrEmpty(propertyStructureFactor.getStructureFactorCode())? propertyStructureFactor.getStructureFactorCode().toUpperCase().trim(): null);

propertyStructureFactor.setStructureFactorNameEn(!Util.isNullOrEmpty(propertyStructureFactor.getStructureFactorNameEn())? propertyStructureFactor.getStructureFactorNameEn().toUpperCase().trim(): null);

propertyStructureFactor.setStructureFactorNameHi(!Util.isNullOrEmpty(propertyStructureFactor.getStructureFactorNameHi())? propertyStructureFactor.getStructureFactorNameHi().toUpperCase().trim(): null);

propertyStructureFactor.setStructureFactorNameRl(!Util.isNullOrEmpty(propertyStructureFactor.getStructureFactorNameRl())? propertyStructureFactor.getStructureFactorNameRl().trim(): null);

propertyStructureFactor.setStructureFactorDesc(!Util.isNullOrEmpty(propertyStructureFactor.getStructureFactorDesc())? propertyStructureFactor.getStructureFactorDesc().trim(): null);



try {
	propertyStructureFactorRepo.save(propertyStructureFactor);
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
@GetMapping("/getPropertyStructureFactorByGuid/{structureFactorGuid}")
public ResponseEntity<PropertyStructureFactor> getPropertyStructureFactorByGuid(
@PathVariable("structureFactorGuid") String structureFactorGuid) {
	PropertyStructureFactor propertyStructureFactor = propertyStructureFactorRepo.findById(structureFactorGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with structureFactorGuid : " + structureFactorGuid));
return new ResponseEntity<>(propertyStructureFactor, HttpStatus.OK);
}

////////////////////////////////////////////PropertyStructureFactor End //////////////////////////

////////////////////////////////////////////PropertyTaxCategory Start //////////////////////////

//get all data from table
@GetMapping("/getPropertyTaxCategoryList")
public ResponseEntity<BaseResponse> getPropertyTaxCategoryList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyTaxCategory> list = propertyTaxCategoryRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyTaxCategory(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyTaxCategory")
public BaseResponse submitPropertyTaxCategory(@RequestBody PropertyTaxCategory propertyTaxCategory,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyTaxCategory.getTaxCategoryGuid() == null || propertyTaxCategory.getTaxCategoryGuid().isEmpty()) {
//Add new data
	propertyTaxCategory.setCreatedIpAddr(request.getRemoteAddr());
	propertyTaxCategory.setTaxCategoryGuid(UUID.randomUUID().toString());
	propertyTaxCategory.setCreatedDate(new Date());
	propertyTaxCategory.setModifiedIpAddr(null);
	propertyTaxCategory.setModifiedByGuid(null);
	propertyTaxCategory.setModifiedDate(null);
	propertyTaxCategory.setCreatedByGuid(request.getRemoteAddr());

if (propertyTaxCategory.getIsActive() == null)
	propertyTaxCategory.setIsActive(false);
//propertyTaxCategory.setCreatedRemarks(userSessionParam.getUserFullName());
//propertyTaxCategory.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyTaxCategory.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//propertyTaxCategory.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	PropertyTaxCategory existingPropertyTaxCategory = commonMasterService
.getPropertyTaxCategoryById(propertyTaxCategory.getTaxCategoryGuid());

if (existingPropertyTaxCategory != null) {

	existingPropertyTaxCategory.setTaxCategoryCode(!Util.isNullOrEmpty(propertyTaxCategory.getTaxCategoryCode())? propertyTaxCategory.getTaxCategoryCode().toUpperCase().trim(): null);

	existingPropertyTaxCategory.setTaxCategoryNameEn(!Util.isNullOrEmpty(propertyTaxCategory.getTaxCategoryNameEn())? propertyTaxCategory.getTaxCategoryNameEn().toUpperCase().trim(): null);

	existingPropertyTaxCategory.setTaxCategoryNameHi(!Util.isNullOrEmpty(propertyTaxCategory.getTaxCategoryNameHi())? propertyTaxCategory.getTaxCategoryNameHi().toUpperCase().trim(): null);

	existingPropertyTaxCategory.setTaxCategoryNameRl(!Util.isNullOrEmpty(propertyTaxCategory.getTaxCategoryNameRl())? propertyTaxCategory.getTaxCategoryNameRl().trim(): null);

	existingPropertyTaxCategory.setTaxCategoryDesc(!Util.isNullOrEmpty(propertyTaxCategory.getTaxCategoryDesc())? propertyTaxCategory.getTaxCategoryDesc().trim(): null);

	existingPropertyTaxCategory.setIsActive(propertyTaxCategory.getIsActive() != null ? propertyTaxCategory.getIsActive(): existingPropertyTaxCategory.getIsActive());

	existingPropertyTaxCategory.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyTaxCategory.setModifiedDate(new Date());
if (existingPropertyTaxCategory.getIsActive() == null)
	existingPropertyTaxCategory.setIsActive(false);
//for now setting some dummy value to test
existingPropertyTaxCategory.setModifiedByGuid(UUID.randomUUID().toString());
existingPropertyTaxCategory.setModifiedMacAddr(UUID.randomUUID().toString());
propertyTaxCategory = existingPropertyTaxCategory; // Use the updated existing country object
} else {
log.error("Property Tax Category not found");
resultData.setStatus(false);
resultData.setMessage("Property Tax Category  not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyTaxCategory(propertyTaxCategory);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyTaxCategory.getIsActive() == null)
	propertyTaxCategory.setIsActive(false);

propertyTaxCategory.setTaxCategoryCode(!Util.isNullOrEmpty(propertyTaxCategory.getTaxCategoryCode())? propertyTaxCategory.getTaxCategoryCode().toUpperCase().trim(): null);

propertyTaxCategory.setTaxCategoryNameEn(!Util.isNullOrEmpty(propertyTaxCategory.getTaxCategoryNameEn())? propertyTaxCategory.getTaxCategoryNameEn().toUpperCase().trim(): null);

propertyTaxCategory.setTaxCategoryNameHi(!Util.isNullOrEmpty(propertyTaxCategory.getTaxCategoryNameHi())? propertyTaxCategory.getTaxCategoryNameHi().toUpperCase().trim(): null);

propertyTaxCategory.setTaxCategoryNameRl(!Util.isNullOrEmpty(propertyTaxCategory.getTaxCategoryNameRl())? propertyTaxCategory.getTaxCategoryNameRl().trim(): null);

propertyTaxCategory.setTaxCategoryDesc(!Util.isNullOrEmpty(propertyTaxCategory.getTaxCategoryDesc())? propertyTaxCategory.getTaxCategoryDesc().trim(): null);



try {
	propertyTaxCategoryRepo.save(propertyTaxCategory);
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
@GetMapping("/getPropertyTaxCategoryByGuid/{taxCategoryGuid}")
public ResponseEntity<PropertyTaxCategory> getPropertyTaxCategoryByGuid(
@PathVariable("taxCategoryGuid") String taxCategoryGuid) {
	PropertyTaxCategory propertyTaxCategory = propertyTaxCategoryRepo.findById(taxCategoryGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with taxCategoryGuid : " + taxCategoryGuid));
return new ResponseEntity<>(propertyTaxCategory, HttpStatus.OK);
}

////////////////////////////////////////////PropertyTaxCategory End //////////////////////////

/////////////////////////////////////PropertyUseFactor Start///////////////////////////////////

//get all data from table
@GetMapping("/getPropertyUseFactorList")
public ResponseEntity<BaseResponse> getPropertyUseFactorList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyUseFactor> list = propertyUseFactorRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyUseFactor(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyUseFactor")
public BaseResponse submitPropertyUseFactor(@RequestBody PropertyUseFactor propertyUseFactor, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyUseFactor.getUseFactorGuid() == null || propertyUseFactor.getUseFactorGuid().isEmpty()) {

//Add new data
	propertyUseFactor.setCreatedIpAddr(request.getRemoteAddr());
	propertyUseFactor.setUseFactorGuid(UUID.randomUUID().toString());
	propertyUseFactor.setCreatedDate(new Date());
	propertyUseFactor.setModifiedIpAddr(null);
	propertyUseFactor.setModifiedByGuid(null);
	propertyUseFactor.setModifiedDate(null);
	propertyUseFactor.setCreatedByGuid(request.getRemoteAddr());


if (propertyUseFactor.getIsHeadquarter() == null)
	propertyUseFactor.setIsHeadquarter(false);

if (propertyUseFactor.getIsActive() == null)
	propertyUseFactor.setIsActive(false);
//propertyUseFactor.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//propertyUseFactor.setCreaterRemarks(userSessionParam.getUserFullName());
//propertyUseFactor.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyUseFactor.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	PropertyUseFactor existingPropertyUseFactor = commonMasterService.getPropertyUseFactorById(propertyUseFactor.getUseFactorGuid());

if (existingPropertyUseFactor != null) {
	existingPropertyUseFactor.setUseFactorCode(!Util.isNullOrEmpty(propertyUseFactor.getUseFactorCode()) ? propertyUseFactor.getUseFactorCode().toUpperCase().trim() : null);
	existingPropertyUseFactor.setUseFactorNameEn(!Util.isNullOrEmpty(propertyUseFactor.getUseFactorNameEn()) ? propertyUseFactor.getUseFactorNameEn().toUpperCase().trim() : null);

	existingPropertyUseFactor.setUseFactorNameHi(!Util.isNullOrEmpty(propertyUseFactor.getUseFactorNameHi()) ? propertyUseFactor.getUseFactorNameHi().toUpperCase().trim() : null);
	existingPropertyUseFactor.setUseFactorNameRl(!Util.isNullOrEmpty(propertyUseFactor.getUseFactorNameRl()) ? propertyUseFactor.getUseFactorNameRl() : null);
	existingPropertyUseFactor.setUseFactorDesc(!Util.isNullOrEmpty(propertyUseFactor.getUseFactorDesc()) ? propertyUseFactor.getUseFactorDesc().trim() : null);
	existingPropertyUseFactor.setIsHeadquarter(propertyUseFactor.getIsHeadquarter() != null ? propertyUseFactor.getIsHeadquarter() : existingPropertyUseFactor.getIsActive());
	existingPropertyUseFactor.setIsActive(propertyUseFactor.getIsActive() != null ? propertyUseFactor.getIsActive() : existingPropertyUseFactor.getIsActive());

	existingPropertyUseFactor.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyUseFactor.setModifiedDate(new Date());
	existingPropertyUseFactor.setModifiedByGuid("admin");



if (existingPropertyUseFactor.getIsHeadquarter() == null)
	existingPropertyUseFactor.setIsHeadquarter(false);

if (existingPropertyUseFactor.getIsActive() == null)
	existingPropertyUseFactor.setIsActive(false);

propertyUseFactor = existingPropertyUseFactor; // Use the updated existing country object
} else {
log.error("Property Use Factor not found");
resultData.setStatus(false);
resultData.setMessage("Property Use Factor not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyUseFactor(propertyUseFactor);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyUseFactor.getIsActive() == null) propertyUseFactor.setIsActive(false);
if (propertyUseFactor.getIsHeadquarter() == null) propertyUseFactor.setIsHeadquarter(false);
propertyUseFactor.setUseFactorCode(!Util.isNullOrEmpty(propertyUseFactor.getUseFactorCode()) ? propertyUseFactor.getUseFactorCode().toUpperCase().trim() : null);
propertyUseFactor.setUseFactorNameEn(!Util.isNullOrEmpty(propertyUseFactor.getUseFactorNameEn()) ? propertyUseFactor.getUseFactorNameEn().toUpperCase().trim() : null);
propertyUseFactor.setUseFactorNameHi(!Util.isNullOrEmpty(propertyUseFactor.getUseFactorNameHi()) ? propertyUseFactor.getUseFactorNameHi().toUpperCase().trim() : null);
propertyUseFactor.setUseFactorNameRl(!Util.isNullOrEmpty(propertyUseFactor.getUseFactorNameRl()) ? propertyUseFactor.getUseFactorNameRl() : null);
propertyUseFactor.setUseFactorDesc(!Util.isNullOrEmpty(propertyUseFactor.getUseFactorDesc()) ? propertyUseFactor.getUseFactorDesc().trim() : null);
	
//if (propertyType.getIsActive() == null) propertyType.setIsActive(false);
try {
	propertyUseFactorRepo.save(propertyUseFactor);
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
@GetMapping("/getPropertyUseFactorByGuid/{useFactorGuid}")
public ResponseEntity<PropertyUseFactor> getPropertyUseFactorByGuid(@PathVariable("useFactorGuid") String useFactorGuid) {
	PropertyUseFactor propertyUseFactor = propertyUseFactorRepo.findById(useFactorGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with useFactorGuid : " + useFactorGuid));
return new ResponseEntity<>(propertyUseFactor, HttpStatus.OK);
}

/////////////////////////////////////PropertyUseFactor End///////////////////////////////////

////////////////////////////////////////////PropertyMasterRebate Start //////////////////////////

//get all data from table
@GetMapping("/getPropertyMasterRebateList")
public ResponseEntity<BaseResponse> getPropertyMasterRebateList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyMasterRebate> list = propertyMasterRebateRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyMasterRebate(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyMasterRebate")
public BaseResponse submitPropertyMasterRebate(@RequestBody PropertyMasterRebate propertyMasterRebate,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyMasterRebate.getRebateGuid() == null || propertyMasterRebate.getRebateGuid().isEmpty()) {
//Add new data
	propertyMasterRebate.setCreatedIpAddr(request.getRemoteAddr());
	propertyMasterRebate.setRebateGuid(UUID.randomUUID().toString());
	propertyMasterRebate.setCreatedDate(new Date());
	propertyMasterRebate.setModifiedIpAddr(null);
	propertyMasterRebate.setModifiedBy(null);
	propertyMasterRebate.setModifiedDate(null);
	propertyMasterRebate.setCreatedBy(request.getRemoteAddr());

if (propertyMasterRebate.getIsActive() == null)
	propertyMasterRebate.setIsActive(false);
//propertyMasterRebate.setCreatedRemarks(userSessionParam.getUserFullName());
//propertyMasterRebate.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyMasterRebate.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//propertyMasterRebate.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	PropertyMasterRebate existingPropertyMasterRebate = commonMasterService
.getPropertyMasterRebateById(propertyMasterRebate.getRebateGuid());

if (existingPropertyMasterRebate != null) {

	existingPropertyMasterRebate.setRebateCode(!Util.isNullOrEmpty(propertyMasterRebate.getRebateCode())? propertyMasterRebate.getRebateCode().toUpperCase().trim(): null);

	existingPropertyMasterRebate.setRebateNameEn(!Util.isNullOrEmpty(propertyMasterRebate.getRebateNameEn())? propertyMasterRebate.getRebateNameEn().toUpperCase().trim(): null);

	existingPropertyMasterRebate.setRebateNameHi(!Util.isNullOrEmpty(propertyMasterRebate.getRebateNameHi())? propertyMasterRebate.getRebateNameHi().toUpperCase().trim(): null);

	existingPropertyMasterRebate.setRebateNameRl(!Util.isNullOrEmpty(propertyMasterRebate.getRebateNameRl())? propertyMasterRebate.getRebateNameRl().trim(): null);

	existingPropertyMasterRebate.setRebateDescription(!Util.isNullOrEmpty(propertyMasterRebate.getRebateDescription())? propertyMasterRebate.getRebateDescription().trim(): null);

	existingPropertyMasterRebate.setIsActive(propertyMasterRebate.getIsActive() != null ? propertyMasterRebate.getIsActive(): existingPropertyMasterRebate.getIsActive());

	existingPropertyMasterRebate.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyMasterRebate.setModifiedDate(new Date());
if (existingPropertyMasterRebate.getIsActive() == null)
	existingPropertyMasterRebate.setIsActive(false);
//for now setting some dummy value to test
existingPropertyMasterRebate.setModifiedBy(UUID.randomUUID().toString());
existingPropertyMasterRebate.setModifiedMacAddr(UUID.randomUUID().toString());
propertyMasterRebate = existingPropertyMasterRebate; // Use the updated existing country object
} else {
log.error("Property Master Rebate not found");
resultData.setStatus(false);
resultData.setMessage("Property Master Rebate  not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyMasterRebate(propertyMasterRebate);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyMasterRebate.getIsActive() == null)
	propertyMasterRebate.setIsActive(false);

propertyMasterRebate.setRebateCode(!Util.isNullOrEmpty(propertyMasterRebate.getRebateCode())? propertyMasterRebate.getRebateCode().toUpperCase().trim(): null);

propertyMasterRebate.setRebateNameEn(!Util.isNullOrEmpty(propertyMasterRebate.getRebateNameEn())? propertyMasterRebate.getRebateNameEn().toUpperCase().trim(): null);

propertyMasterRebate.setRebateNameHi(!Util.isNullOrEmpty(propertyMasterRebate.getRebateNameHi())? propertyMasterRebate.getRebateNameHi().toUpperCase().trim(): null);

propertyMasterRebate.setRebateNameRl(!Util.isNullOrEmpty(propertyMasterRebate.getRebateNameRl())? propertyMasterRebate.getRebateNameRl().trim(): null);

propertyMasterRebate.setRebateDescription(!Util.isNullOrEmpty(propertyMasterRebate.getRebateDescription())? propertyMasterRebate.getRebateDescription().trim(): null);



try {
	propertyMasterRebateRepo.save(propertyMasterRebate);
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
@GetMapping("/getPropertyMasterRebateByGuid/{rebateGuid}")
public ResponseEntity<PropertyMasterRebate> getPropertyMasterRebateByGuid(
@PathVariable("rebateGuid") String rebateGuid) {
	PropertyMasterRebate propertyMasterRebate = propertyMasterRebateRepo.findById(rebateGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with rebateGuid : " + rebateGuid));
return new ResponseEntity<>(propertyMasterRebate, HttpStatus.OK);
}

////////////////////////////////////////////PropertyMasterRebate End //////////////////////////


////////////////////////////////////////////PropertyOtherCharges Start //////////////////////////

//get all data from table
@GetMapping("/getPropertyOtherChargesList")
public ResponseEntity<BaseResponse> getPropertyOtherChargesList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyOtherCharges> list = propertyOtherChargesRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyOtherCharges(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyOtherCharges")
public BaseResponse submitPropertyOtherCharges(@RequestBody PropertyOtherCharges propertyOtherCharges,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyOtherCharges.getOtherChargesGuid() == null || propertyOtherCharges.getOtherChargesGuid().isEmpty()) {
//Add new data
	propertyOtherCharges.setCreatedIpAddr(request.getRemoteAddr());
	propertyOtherCharges.setOtherChargesGuid(UUID.randomUUID().toString());
	propertyOtherCharges.setCreatedDate(new Date());
	propertyOtherCharges.setModifiedIpAddr(null);
	propertyOtherCharges.setModifiedBy(null);
	propertyOtherCharges.setModifiedDate(null);
	propertyOtherCharges.setCreatedBy(request.getRemoteAddr());

if (propertyOtherCharges.getIsActive() == null)
	propertyOtherCharges.setIsActive(false);
//propertyOtherCharges.setCreatedRemarks(userSessionParam.getUserFullName());
//propertyOtherCharges.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyOtherCharges.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//propertyOtherCharges.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	PropertyOtherCharges existingPropertyOtherCharges = commonMasterService
.getPropertyOtherChargesById(propertyOtherCharges.getOtherChargesGuid());

if (existingPropertyOtherCharges != null) {

	existingPropertyOtherCharges.setOtherChargesCode(!Util.isNullOrEmpty(propertyOtherCharges.getOtherChargesCode())? propertyOtherCharges.getOtherChargesCode().toUpperCase().trim(): null);

	existingPropertyOtherCharges.setOtherChargesNameEn(!Util.isNullOrEmpty(propertyOtherCharges.getOtherChargesNameEn())? propertyOtherCharges.getOtherChargesNameEn().toUpperCase().trim(): null);

	existingPropertyOtherCharges.setOtherChargesNameHi(!Util.isNullOrEmpty(propertyOtherCharges.getOtherChargesNameHi())? propertyOtherCharges.getOtherChargesNameHi().toUpperCase().trim(): null);

	existingPropertyOtherCharges.setOtherChargesNameRl(!Util.isNullOrEmpty(propertyOtherCharges.getOtherChargesNameRl())? propertyOtherCharges.getOtherChargesNameRl().trim(): null);

	existingPropertyOtherCharges.setOtherChargesDesc(!Util.isNullOrEmpty(propertyOtherCharges.getOtherChargesDesc())? propertyOtherCharges.getOtherChargesDesc().trim(): null);

	existingPropertyOtherCharges.setIsActive(propertyOtherCharges.getIsActive() != null ? propertyOtherCharges.getIsActive(): existingPropertyOtherCharges.getIsActive());

	existingPropertyOtherCharges.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyOtherCharges.setModifiedDate(new Date());
if (existingPropertyOtherCharges.getIsActive() == null)
	existingPropertyOtherCharges.setIsActive(false);
//for now setting some dummy value to test
existingPropertyOtherCharges.setModifiedBy(UUID.randomUUID().toString());
existingPropertyOtherCharges.setModifiedMacAddr(UUID.randomUUID().toString());
propertyOtherCharges = existingPropertyOtherCharges; // Use the updated existing country object
} else {
log.error("Property Other Charges not found");
resultData.setStatus(false);
resultData.setMessage("Property Other Charges  not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyOtherCharges(propertyOtherCharges);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyOtherCharges.getIsActive() == null)
	propertyOtherCharges.setIsActive(false);

propertyOtherCharges.setOtherChargesCode(!Util.isNullOrEmpty(propertyOtherCharges.getOtherChargesCode())? propertyOtherCharges.getOtherChargesCode().toUpperCase().trim(): null);

propertyOtherCharges.setOtherChargesNameEn(!Util.isNullOrEmpty(propertyOtherCharges.getOtherChargesNameEn())? propertyOtherCharges.getOtherChargesNameEn().toUpperCase().trim(): null);

propertyOtherCharges.setOtherChargesNameHi(!Util.isNullOrEmpty(propertyOtherCharges.getOtherChargesNameHi())? propertyOtherCharges.getOtherChargesNameHi().toUpperCase().trim(): null);

propertyOtherCharges.setOtherChargesNameRl(!Util.isNullOrEmpty(propertyOtherCharges.getOtherChargesNameRl())? propertyOtherCharges.getOtherChargesNameRl().trim(): null);

propertyOtherCharges.setOtherChargesDesc(!Util.isNullOrEmpty(propertyOtherCharges.getOtherChargesDesc())? propertyOtherCharges.getOtherChargesDesc().trim(): null);



try {
	propertyOtherChargesRepo.save(propertyOtherCharges);
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
@GetMapping("/getPropertyOtherChargesByGuid/{otherChargesGuid}")
public ResponseEntity<PropertyOtherCharges> getPropertyOtherChargesByGuid(
@PathVariable("otherChargesGuid") String otherChargesGuid) {
	PropertyOtherCharges propertyOtherCharges = propertyOtherChargesRepo.findById(otherChargesGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with otherChargesGuid : " + otherChargesGuid));
return new ResponseEntity<>(propertyOtherCharges, HttpStatus.OK);
}

////////////////////////////////////////////PropertyOtherCharges End //////////////////////////

/////////////////////////////////////ManualReceiptSeries Start///////////////////////////////////
//get all data from table
@GetMapping("/getManualReceiptSeriesList")
public ResponseEntity<BaseResponse> getManualReceiptSeriesList() {
    BaseResponse response = new BaseResponse();
    // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    List<ManualReceiptSeries> list = manualReceiptSeriesRepo.findAll();
    response.setMessage("success");
    response.setStatus(true);
    response.setTotalDataCount(list.size());
    response.setManualReceiptSeries(list);
    return ResponseEntity.ok(response);
}

// Create New Data And Update
@PostMapping("/submitManualReceiptSeries")
public BaseResponse submitManualReceiptSeries(@RequestBody ManualReceiptSeries manualReceiptSeries, HttpServletRequest request) {
    BaseResponse resultData = new BaseResponse();

    // Check if guid is provided (indicating an update)
    if (manualReceiptSeries.getReceiptSeriesGuid() == null || manualReceiptSeries.getReceiptSeriesGuid().isEmpty()) {
        // Add new data
    	manualReceiptSeries.setCreatedIpAddr(request.getRemoteAddr());
    	manualReceiptSeries.setReceiptSeriesGuid(UUID.randomUUID().toString());
    	manualReceiptSeries.setCreatedDate(new Date());
    	manualReceiptSeries.setModifiedIpAddr(null);
    	manualReceiptSeries.setModifiedBy(null);
    	manualReceiptSeries.setModifiedDate(null);
    	manualReceiptSeries.setCreatedBy(request.getRemoteAddr());

        if (manualReceiptSeries.getIsActive() == null)
        	manualReceiptSeries.setIsActive(false);
//		manualReceiptSeries.setCreaterRemarks(userSessionParam.getUserFullName());
        //manualReceiptSeries.setCreaterMacId(HttpSessionHelper.getMacAddress());
        //manualReceiptSeries.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
    		
    	
    } else {
        // Update existing data
    	ManualReceiptSeries existingManualReceiptSeries = commonMasterService.getManualReceiptSeriesById(manualReceiptSeries.getReceiptSeriesGuid());

        if (existingManualReceiptSeries != null) {
        	existingManualReceiptSeries.setReceiptSeriesCode(!Util.isNullOrEmpty(manualReceiptSeries.getReceiptSeriesCode()) ? manualReceiptSeries.getReceiptSeriesCode().toUpperCase().trim() : null);
        	existingManualReceiptSeries.setReceiptSeriesFrom(!Util.isNullOrZero(manualReceiptSeries.getReceiptSeriesFrom()) ? manualReceiptSeries.getReceiptSeriesFrom() : null);

        	existingManualReceiptSeries.setReceiptSeriesTo(!Util.isNullOrZero(manualReceiptSeries.getReceiptSeriesTo()) ? manualReceiptSeries.getReceiptSeriesTo() : null);
        	existingManualReceiptSeries.setReceiptSeriesDesc(!Util.isNullOrEmpty(manualReceiptSeries.getReceiptSeriesDesc()) ? manualReceiptSeries.getReceiptSeriesDesc().trim() : null);
        	existingManualReceiptSeries.setIssueDate(manualReceiptSeries.getIssueDate());
        
         
        	existingManualReceiptSeries.setIsActive(manualReceiptSeries.getIsActive() != null ? manualReceiptSeries.getIsActive() : existingManualReceiptSeries.getIsActive());

        	existingManualReceiptSeries.setModifiedIpAddr(request.getRemoteAddr());
        	existingManualReceiptSeries.setModifiedDate(new Date());
            if (existingManualReceiptSeries.getIsActive() == null)
            	existingManualReceiptSeries.setIsActive(false);
            // for now setting some dummy value to test
            existingManualReceiptSeries.setModifiedBy(UUID.randomUUID().toString());
            existingManualReceiptSeries.setModifiedMacAddr(UUID.randomUUID().toString());
            manualReceiptSeries = existingManualReceiptSeries; // Use the updated existing country object
        } else {
            log.error("Manual Receipt Series not found");
            resultData.setStatus(false);
            resultData.setMessage("Manual Receipt not found");
            return resultData;
        }
    }

    // Validation
    resultData = validator.validateManualReceiptSeries(manualReceiptSeries);
    if (resultData != null && !resultData.getStatus()) {
        log.error("Validation failed: {}", resultData.getMessage());
        return resultData;
    }

    // If validation passes, proceed to save or update
    manualReceiptSeries.setReceiptSeriesCode(!Util.isNullOrEmpty(manualReceiptSeries.getReceiptSeriesCode()) ? manualReceiptSeries.getReceiptSeriesCode().toUpperCase().trim() : null);
    manualReceiptSeries.setReceiptSeriesFrom(!Util.isNullOrZero(manualReceiptSeries.getReceiptSeriesFrom()) ? manualReceiptSeries.getReceiptSeriesFrom() : null);

    manualReceiptSeries.setReceiptSeriesTo(!Util.isNullOrZero(manualReceiptSeries.getReceiptSeriesTo()) ? manualReceiptSeries.getReceiptSeriesTo() : null);
    manualReceiptSeries.setReceiptSeriesDesc(!Util.isNullOrEmpty(manualReceiptSeries.getReceiptSeriesDesc()) ? manualReceiptSeries.getReceiptSeriesDesc().trim() : null);
    manualReceiptSeries.setIssueDate(manualReceiptSeries.getIssueDate());


 

    try {
    	manualReceiptSeriesRepo.save(manualReceiptSeries);
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
@GetMapping("/getManualReceiptSeriesByGuid/{receiptSeriesGuid}")
public ResponseEntity<ManualReceiptSeries> getManualReceiptSeriesByGuid(@PathVariable("receiptSeriesGuid") String receiptSeriesGuid) {
	ManualReceiptSeries manualReceiptSeries = manualReceiptSeriesRepo.findById(receiptSeriesGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with receiptSeriesGuid : " + receiptSeriesGuid));
    return new ResponseEntity<>(manualReceiptSeries, HttpStatus.OK);
}


//////////////////////////////////ManualReceiptSeries End////////////////////////////////////

////////////////////////////////////////////PropertyMstSr Start //////////////////////////

//get all data from table
@GetMapping("/getPropertyMstSrList")
public ResponseEntity<BaseResponse> getPropertyMstSrList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PropertyMstSr> list = propertyMstSrRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPropertyMstSr(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitPropertyMstSr")
public BaseResponse submitPropertyMstSr(@RequestBody PropertyMstSr propertyMstSr,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (propertyMstSr.getMstSrGuid() == null || propertyMstSr.getMstSrGuid().isEmpty()) {
//Add new data
	propertyMstSr.setCreatedIpAddr(request.getRemoteAddr());
	propertyMstSr.setMstSrGuid(UUID.randomUUID().toString());
	propertyMstSr.setCreatedDate(new Date());
propertyMstSr.setModifiedIpAddr(null);
propertyMstSr.setModifiedBy(null);
propertyMstSr.setModifiedDate(null);
propertyMstSr.setCreatedBy(request.getRemoteAddr());

if (propertyMstSr.getIsActive() == null)
	propertyMstSr.setIsActive(false);
//propertyMstSr.setCreatedRemarks(userSessionParam.getUserFullName());
//propertyMstSr.setCreaterMacId(HttpSessionHelper.getMacAddress());
//propertyMstSr.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//propertyMstSr.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	PropertyMstSr existingPropertyMstSr = commonMasterService
.getPropertyMstSrById(propertyMstSr.getMstSrGuid());

if (existingPropertyMstSr != null) {

	existingPropertyMstSr.setSrCode(!Util.isNullOrEmpty(propertyMstSr.getSrCode())? propertyMstSr.getSrCode().toUpperCase().trim(): null);

	existingPropertyMstSr.setSrName(!Util.isNullOrEmpty(propertyMstSr.getSrName())? propertyMstSr.getSrName().toUpperCase().trim(): null);

	existingPropertyMstSr.setIsActive(propertyMstSr.getIsActive() != null ? propertyMstSr.getIsActive(): existingPropertyMstSr.getIsActive());

	existingPropertyMstSr.setModifiedIpAddr(request.getRemoteAddr());
	existingPropertyMstSr.setModifiedDate(new Date());
if (existingPropertyMstSr.getIsActive() == null)
	existingPropertyMstSr.setIsActive(false);
//for now setting some dummy value to test
existingPropertyMstSr.setModifiedBy(UUID.randomUUID().toString());
existingPropertyMstSr.setModifiedMacAddr(UUID.randomUUID().toString());
propertyMstSr = existingPropertyMstSr; // Use the updated existing country object
} else {
log.error("property Mst Sr not found");
resultData.setStatus(false);
resultData.setMessage("property Mst Sr  not found");
return resultData;
}
}

//Validation
resultData = validator.validatePropertyMstSr(propertyMstSr);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (propertyMstSr.getIsActive() == null)
	propertyMstSr.setIsActive(false);

propertyMstSr.setSrCode(!Util.isNullOrEmpty(propertyMstSr.getSrCode())? propertyMstSr.getSrCode().toUpperCase().trim(): null);

propertyMstSr.setSrName(!Util.isNullOrEmpty(propertyMstSr.getSrName())? propertyMstSr.getSrName().toUpperCase().trim(): null);



try {
	propertyMstSrRepo.save(propertyMstSr);
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
@GetMapping("/getPropertyMstSrByGuid/{mstSrGuid}")
public ResponseEntity<PropertyMstSr> getPropertyMstSrByGuid(
@PathVariable("mstSrGuid") String mstSrGuid) {
	PropertyMstSr propertyMstSr = propertyMstSrRepo.findById(mstSrGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with mstSrGuid : " + mstSrGuid));
return new ResponseEntity<>(propertyMstSr, HttpStatus.OK);
}

////////////////////////////////////////////PropertyMstSr End //////////////////////////
}
