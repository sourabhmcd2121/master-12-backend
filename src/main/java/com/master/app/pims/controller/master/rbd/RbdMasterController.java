package com.master.app.pims.controller.master.rbd;

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
import com.master.app.pims.entities.schemas.hospital.HospitalInfo;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.PersRelation;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeClassification;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeType;
import com.master.app.pims.entities.schemas.mst.DocsCategoryInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.EducationLevel;
import com.master.app.pims.entities.schemas.mst.GeoZoneMCD;
import com.master.app.pims.entities.schemas.mst.OccupationType;
import com.master.app.pims.entities.schemas.mst.RefDocsCategoryMap;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.property.OwnerCategory;
import com.master.app.pims.entities.schemas.property.OwnerType;
import com.master.app.pims.entities.schemas.property.PropertyAgeFactor;
import com.master.app.pims.entities.schemas.property.PropertyCategory;
import com.master.app.pims.entities.schemas.property.PropertyExemption;
import com.master.app.pims.entities.schemas.property.PropertyFloor;
import com.master.app.pims.entities.schemas.property.PropertyOccupancyFactor;
import com.master.app.pims.entities.schemas.property.PropertyType;
import com.master.app.pims.entities.schemas.rbd.RbdFeeRelaxationList;
import com.master.app.pims.entities.schemas.rbd.RbdMstCommonList;
import com.master.app.pims.entities.schemas.rbd.RbdMstDocsCategory;
import com.master.app.pims.entities.schemas.rbd.RbdRefBirthDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefChargeMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefDeathDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefEducationMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefOccupationMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefRegistrationNumber;
import com.master.app.pims.entities.schemas.rbd.RbdRefRelationMap;
import com.master.app.pims.entities.schemas.usr.RefUserDocsMap;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.helper.HttpSessionHelper;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.property.OwnerCategoryRepo;
import com.master.app.pims.repositories.property.OwnerTypeRepo;
import com.master.app.pims.repositories.property.PropertyAgeFactorRepo;
import com.master.app.pims.repositories.property.PropertyCategoryRepo;
import com.master.app.pims.repositories.property.PropertyExemptionRepo;
import com.master.app.pims.repositories.property.PropertyFloorRepo;
import com.master.app.pims.repositories.property.PropertyOccupancyFactorRepo;
import com.master.app.pims.repositories.property.PropertyTypeRepo;
import com.master.app.pims.repositories.rbd.RbdFeeRelaxationListRepo;
import com.master.app.pims.repositories.rbd.RbdMstCommonListRepo;
import com.master.app.pims.repositories.rbd.RbdMstDocsCategoryRepo;
import com.master.app.pims.repositories.rbd.RbdRefBirthDocsMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefChargeMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefDeathDocsMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefDocsMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefEducationMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefOccupationMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefRegistrationNumberRepo;
import com.master.app.pims.repositories.rbd.RbdRefRelationMapRepo;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class RbdMasterController {
private Logger logger = LoggerFactory.getLogger(RbdMasterController.class);
	
	@Autowired
	private Validator validator;

	@Autowired
	private CommonMasterService commonMasterService;
	
	  @Autowired
	   	private RbdMstCommonListRepo rbdMstCommonListRepo;
	  
	  @Autowired
	   	private RbdMstDocsCategoryRepo rbdMstDocsCategoryRepo;
	  
	  @Autowired
	   	private RbdRefBirthDocsMapRepo rbdRefBirthDocsMapRepo;
	  
	  @Autowired
	   	private RbdRefDeathDocsMapRepo rbdRefDeathDocsMapRepo;
	  
	  @Autowired
	   	private RbdRefChargeMapRepo rbdRefChargeMapRepo;
	  
	  @Autowired
	   	private RbdRefRelationMapRepo rbdRefRelationMapRepo;
	  
	  @Autowired
	   	private RbdRefOccupationMapRepo rbdRefOccupationMapRepo;
	  
	  @Autowired
	   	private RbdRefEducationMapRepo rbdRefEducationMapRepo;
	  
	  @Autowired
	   	private RbdRefRegistrationNumberRepo rbdRefRegistrationNumberRepo;
	  
	  @Autowired
	   	private RbdRefDocsMapRepo rbdRefDocsMapRepo;
	  
	  @Autowired
	   	private RbdFeeRelaxationListRepo rbdFeeRelaxationListRepo;
	  
	  ///////////////////////////////RbdMstCommonList Start///////////////////////////////////////
	//get all data from table
	  @GetMapping("/getRbdMstCommonList")
	  public ResponseEntity<BaseResponse> getRbdMstCommonList() {
	  BaseResponse response = new BaseResponse();
	  //Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	  List<RbdMstCommonList> list = rbdMstCommonListRepo.findAll();
	  response.setMessage("success");
	  response.setStatus(true);
	  response.setTotalDataCount(list.size());
	  response.setRbdMstCommonList(list);
	  return ResponseEntity.ok(response);
	  }

	  //Create New Data And Update
	  @PostMapping("/submitRbdMstCommonList")
	  public BaseResponse submitRbdMstCommonList(@RequestBody RbdMstCommonList rbdMstCommonList, HttpServletRequest request) {
	  BaseResponse resultData = new BaseResponse();

	  //Check if guid is provided (indicating an update)
	  if (rbdMstCommonList.getCommonListGuid() == null || rbdMstCommonList.getCommonListGuid().isEmpty()) {

	  //Add new data
		  rbdMstCommonList.setCreatedIpAddr(request.getRemoteAddr());
		  rbdMstCommonList.setCommonListGuid(UUID.randomUUID().toString());
		  rbdMstCommonList.setCreatedDate(new Date());
		  rbdMstCommonList.setModifiedIpAddr(null);
	  	rbdMstCommonList.setCreatedBy("admin");
	
	  	rbdMstCommonList.setModifiedDate(null);
	  	rbdMstCommonList.setModifiedBy(null);
	

	  //for dropdown
	  	rbdMstCommonList.setAssessmentYear(rbdMstCommonList.getAssessmentYearGuid());
	  	rbdMstCommonList.setRequestSubmissionType(rbdMstCommonList.getRequestSubmissionTypeGuid());

	  //AssessmentYear
	  if(rbdMstCommonList.getAssessmentYear()!=null && !rbdMstCommonList.getAssessmentYear().isEmpty()){
		  rbdMstCommonList.setAssessmentYearMaster(new AssessmentYear(rbdMstCommonList.getAssessmentYear()));
	  }

	  //RequestSubmissionType
	  if(rbdMstCommonList.getRequestSubmissionType()!=null && !rbdMstCommonList.getRequestSubmissionType().isEmpty()){
		  rbdMstCommonList.setRequestSubmissionTypeMaster(new RequestSubmissionType(rbdMstCommonList.getRequestSubmissionType()));
	  }

	 

	  if (rbdMstCommonList.getIsMandatory() == null)
		  rbdMstCommonList.setIsMandatory(false);

	  if (rbdMstCommonList.getIsActive() == null)
		  rbdMstCommonList.setIsActive(false);

	  //rbdMstCommonList.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
	  //rbdMstCommonList.setCreaterRemarks(userSessionParam.getUserFullName());
	  //rbdMstCommonList.setCreaterMacId(HttpSessionHelper.getMacAddress());
	  //rbdMstCommonList.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
	  } else {
	  //Update existing data
		  RbdMstCommonList existingRbdMstCommonList = commonMasterService.getRbdMstCommonListById(rbdMstCommonList.getCommonListGuid());

	  if (existingRbdMstCommonList != null) {
		  existingRbdMstCommonList.setCommonListCode(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListCode()) ? rbdMstCommonList.getCommonListCode().toUpperCase().trim() : null);
		  existingRbdMstCommonList.setCommonListNameEn(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListNameEn()) ? rbdMstCommonList.getCommonListNameEn().toUpperCase().trim() : null);
		  existingRbdMstCommonList.setCommonListNameHi(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListNameHi()) ? rbdMstCommonList.getCommonListNameHi().toUpperCase().trim() : null);
		  existingRbdMstCommonList.setCommonListNameRl(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListNameRl()) ? rbdMstCommonList.getCommonListNameRl().toUpperCase().trim() : null);
		  existingRbdMstCommonList.setCommonListDesc(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListDesc()) ? rbdMstCommonList.getCommonListDesc().toUpperCase().trim() : null);
		  existingRbdMstCommonList.setSubmittedForType(!Util.isNullOrEmpty(rbdMstCommonList.getSubmittedForType()) ? rbdMstCommonList.getSubmittedForType().toUpperCase().trim() : null);
		  existingRbdMstCommonList.setCommonListRequireType(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListRequireType()) ? rbdMstCommonList.getCommonListRequireType().toUpperCase().trim() : null);
		 
		  existingRbdMstCommonList.setIsMandatory(rbdMstCommonList.getIsMandatory() != null ? rbdMstCommonList.getIsMandatory() : existingRbdMstCommonList.getIsMandatory());	
		  existingRbdMstCommonList.setIsActive(rbdMstCommonList.getIsActive() != null ? rbdMstCommonList.getIsActive() : existingRbdMstCommonList.getIsActive());

		  existingRbdMstCommonList.setModifiedIpAddr(request.getRemoteAddr());
		  existingRbdMstCommonList.setModifiedDate(new Date());
		  existingRbdMstCommonList.setModifiedBy("admin");
	  //existingRefUserDocsMap.setModifiedByGuid("admin");

	  //dropdown
		  existingRbdMstCommonList.setAssessmentYear(rbdMstCommonList.getAssessmentYearGuid());
		  existingRbdMstCommonList.setRequestSubmissionType(rbdMstCommonList.getRequestSubmissionTypeGuid());

	  //AssessmentYear
	  if(existingRbdMstCommonList.getAssessmentYear()!=null && !existingRbdMstCommonList.getAssessmentYear().isEmpty()){
		  existingRbdMstCommonList.setAssessmentYearMaster(new AssessmentYear(existingRbdMstCommonList.getAssessmentYear()));
	  }
	  //RequestSubmissionType
	  if(existingRbdMstCommonList.getRequestSubmissionType()!=null && !existingRbdMstCommonList.getRequestSubmissionType().isEmpty()){
		  existingRbdMstCommonList.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRbdMstCommonList.getRequestSubmissionType()));
	  }

	

	  if (existingRbdMstCommonList.getIsMandatory() == null)
		  existingRbdMstCommonList.setIsMandatory(false);

	  if (existingRbdMstCommonList.getIsActive() == null)
		  existingRbdMstCommonList.setIsActive(false);

	 rbdMstCommonList = existingRbdMstCommonList; // Use the updated existing country object
	  } else {
	  log.error("RbdMstCommonList not found");
	  resultData.setStatus(false);
	  resultData.setMessage("RbdMstCommonList not found");
	  return resultData;
	  }
	  }

	  //Validation
	  resultData = validator.validateRbdMstCommonList(rbdMstCommonList);
	  if (resultData != null && !resultData.getStatus()) {
	  log.error("Validation failed: {}", resultData.getMessage());
	  return resultData;
	  }

	  //If validation passes, proceed to save or update
	  if (rbdMstCommonList.getIsMandatory() == null) rbdMstCommonList.setIsMandatory(false);
	  if (rbdMstCommonList.getIsActive() == null) rbdMstCommonList.setIsActive(false);
	  rbdMstCommonList.setCommonListCode(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListCode()) ? rbdMstCommonList.getCommonListCode().toUpperCase().trim() : null);
	  rbdMstCommonList.setCommonListNameEn(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListNameEn()) ? rbdMstCommonList.getCommonListNameEn().toUpperCase().trim() : null);
	  rbdMstCommonList.setCommonListNameHi(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListNameHi()) ? rbdMstCommonList.getCommonListNameHi().toUpperCase().trim() : null);
	  rbdMstCommonList.setCommonListNameRl(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListNameRl()) ? rbdMstCommonList.getCommonListNameRl().toUpperCase().trim() : null);
	  rbdMstCommonList.setCommonListDesc(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListDesc()) ? rbdMstCommonList.getCommonListDesc().toUpperCase().trim() : null);
	  rbdMstCommonList.setSubmittedForType(!Util.isNullOrEmpty(rbdMstCommonList.getSubmittedForType()) ? rbdMstCommonList.getSubmittedForType().toUpperCase().trim() : null);
	  rbdMstCommonList.setCommonListRequireType(!Util.isNullOrEmpty(rbdMstCommonList.getCommonListRequireType()) ? rbdMstCommonList.getCommonListRequireType().toUpperCase().trim() : null);
	 

	  try {
		  rbdMstCommonListRepo.save(rbdMstCommonList);
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
	  @GetMapping("/getRbdMstCommonListByGuid/{commonListGuid}")
	  public ResponseEntity<RbdMstCommonList> getRbdMstCommonListByGuid(@PathVariable("commonListGuid") String commonListGuid) {
		  RbdMstCommonList rbdMstCommonList = rbdMstCommonListRepo.findById(commonListGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with commonListGuid : " + commonListGuid));
	  return new ResponseEntity<>(rbdMstCommonList, HttpStatus.OK);
	  }
	  
	  ///////////////////////////////RbdMstCommonList End///////////////////////////////////////
	  
////////////////////////////////////////////RbdMstDocsCategory Start //////////////////////////

//get all data from table
@GetMapping("/getRbdMstDocsCategoryList")
public ResponseEntity<BaseResponse> getRbdMstDocsCategoryList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RbdMstDocsCategory> list = rbdMstDocsCategoryRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRbdMstDocsCategory(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitRbdMstDocsCategory")
public BaseResponse submitRbdMstDocsCategory(@RequestBody RbdMstDocsCategory rbdMstDocsCategory,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (rbdMstDocsCategory.getMstDocsCategoryGuid() == null || rbdMstDocsCategory.getMstDocsCategoryGuid().isEmpty()) {
//Add new data
	rbdMstDocsCategory.setCreatedIpAddr(request.getRemoteAddr());
	rbdMstDocsCategory.setMstDocsCategoryGuid(UUID.randomUUID().toString());
	rbdMstDocsCategory.setCreatedDate(new Date());
	rbdMstDocsCategory.setModifiedIpAddr(null);
	rbdMstDocsCategory.setModifiedBy(null);
	rbdMstDocsCategory.setModifiedDate(null);
	rbdMstDocsCategory.setCreatedBy(request.getRemoteAddr());

if (rbdMstDocsCategory.getIsActive() == null)
	rbdMstDocsCategory.setIsActive(false);
//rbdMstDocsCategory.setCreatedRemarks(userSessionParam.getUserFullName());
//rbdMstDocsCategory.setCreaterMacId(HttpSessionHelper.getMacAddress());
//rbdMstDocsCategory.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//rbdMstDocsCategory.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	RbdMstDocsCategory existingRbdMstDocsCategory = commonMasterService
.getRbdMstDocsCategoryById(rbdMstDocsCategory.getMstDocsCategoryGuid());

if (existingRbdMstDocsCategory != null) {

	existingRbdMstDocsCategory.setDocsCategoryCode(!Util.isNullOrEmpty(rbdMstDocsCategory.getDocsCategoryCode())? rbdMstDocsCategory.getDocsCategoryCode().toUpperCase().trim(): null);

	existingRbdMstDocsCategory.setDocsCategoryNameEn(!Util.isNullOrEmpty(rbdMstDocsCategory.getDocsCategoryNameEn())? rbdMstDocsCategory.getDocsCategoryNameEn().toUpperCase().trim(): null);

	existingRbdMstDocsCategory.setDocsCategoryNameHi(!Util.isNullOrEmpty(rbdMstDocsCategory.getDocsCategoryNameHi())? rbdMstDocsCategory.getDocsCategoryNameHi().toUpperCase().trim(): null);

	existingRbdMstDocsCategory.setDocsCategoryNameRl(!Util.isNullOrEmpty(rbdMstDocsCategory.getDocsCategoryNameRl())? rbdMstDocsCategory.getDocsCategoryNameRl().trim(): null);

	existingRbdMstDocsCategory.setMstDocsCategoryDesc(!Util.isNullOrEmpty(rbdMstDocsCategory.getMstDocsCategoryDesc())? rbdMstDocsCategory.getMstDocsCategoryDesc().trim(): null);

	existingRbdMstDocsCategory.setIsActive(rbdMstDocsCategory.getIsActive() != null ? rbdMstDocsCategory.getIsActive(): existingRbdMstDocsCategory.getIsActive());

	existingRbdMstDocsCategory.setModifiedIpAddr(request.getRemoteAddr());
	existingRbdMstDocsCategory.setModifiedDate(new Date());
if (existingRbdMstDocsCategory.getIsActive() == null)
	existingRbdMstDocsCategory.setIsActive(false);
//for now setting some dummy value to test
existingRbdMstDocsCategory.setModifiedBy(UUID.randomUUID().toString());
existingRbdMstDocsCategory.setModifiedMacAddr(UUID.randomUUID().toString());
rbdMstDocsCategory = existingRbdMstDocsCategory; // Use the updated existing country object
} else {
log.error("RbdMstDocsCategory Exemption not found");
resultData.setStatus(false);
resultData.setMessage("RbdMstDocsCategory not found");
return resultData;
}
}

//Validation
resultData = validator.validateRbdMstDocsCategory(rbdMstDocsCategory);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (rbdMstDocsCategory.getIsActive() == null)
	rbdMstDocsCategory.setIsActive(false);

rbdMstDocsCategory.setDocsCategoryCode(!Util.isNullOrEmpty(rbdMstDocsCategory.getDocsCategoryCode())? rbdMstDocsCategory.getDocsCategoryCode().toUpperCase().trim(): null);

rbdMstDocsCategory.setDocsCategoryNameEn(!Util.isNullOrEmpty(rbdMstDocsCategory.getDocsCategoryNameEn())? rbdMstDocsCategory.getDocsCategoryNameEn().toUpperCase().trim(): null);

rbdMstDocsCategory.setDocsCategoryNameHi(!Util.isNullOrEmpty(rbdMstDocsCategory.getDocsCategoryNameHi())? rbdMstDocsCategory.getDocsCategoryNameHi().toUpperCase().trim(): null);

rbdMstDocsCategory.setDocsCategoryNameRl(!Util.isNullOrEmpty(rbdMstDocsCategory.getDocsCategoryNameRl())? rbdMstDocsCategory.getDocsCategoryNameRl().trim(): null);

rbdMstDocsCategory.setMstDocsCategoryDesc(!Util.isNullOrEmpty(rbdMstDocsCategory.getMstDocsCategoryDesc())? rbdMstDocsCategory.getMstDocsCategoryDesc().trim(): null);


try {
	rbdMstDocsCategoryRepo.save(rbdMstDocsCategory);
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
@GetMapping("/getRbdMstDocsCategoryByGuid/{mstDocsCategoryGuid}")
public ResponseEntity<RbdMstDocsCategory> getRbdMstDocsCategoryByGuid(
@PathVariable("mstDocsCategoryGuid") String mstDocsCategoryGuid) {
	RbdMstDocsCategory rbdMstDocsCategory = rbdMstDocsCategoryRepo.findById(mstDocsCategoryGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with mstDocsCategoryGuid : " + mstDocsCategoryGuid));
return new ResponseEntity<>(rbdMstDocsCategory, HttpStatus.OK);
}

////////////////////////////////////////////RbdMstDocsCategory End //////////////////////////

/////////////////////////////////////RbdRefBirthDocsMap Start///////////////////////////////////

//get all data from table
@GetMapping("/getRbdRefBirthDocsMapList")
public ResponseEntity<BaseResponse> getRbdRefBirthDocsMapList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RbdRefBirthDocsMap> list = rbdRefBirthDocsMapRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRbdRefBirthDocsMap(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitRbdRefBirthDocsMap")
public BaseResponse submitRbdRefBirthDocsMap(@RequestBody RbdRefBirthDocsMap rbdRefBirthDocsMap, HttpServletRequest request, ServletRequest httpSession) {
BaseResponse resultData = new BaseResponse();

String loginId = (String) request.getSession().getAttribute("loginID");
String clientIp = HttpSessionHelper.getClientIPAddress(request);
String macAddress = HttpSessionHelper.getMacAddress();

//Check if guid is provided (indicating an update)
if (rbdRefBirthDocsMap.getBirthDocsMapGuid() == null || rbdRefBirthDocsMap.getBirthDocsMapGuid().isEmpty()) {

//Add new data
	rbdRefBirthDocsMap.setCreatedIpAddr(request.getRemoteAddr());
	rbdRefBirthDocsMap.setBirthDocsMapGuid(UUID.randomUUID().toString());
	rbdRefBirthDocsMap.setCreatedDate(new Date());
	rbdRefBirthDocsMap.setModifiedIpAddr(null);
	rbdRefBirthDocsMap.setCreatedBy("admin");
rbdRefBirthDocsMap.setModifiedBy(null);
	rbdRefBirthDocsMap.setModifiedDate(null);
rbdRefBirthDocsMap.setCreatedBy(request.getRemoteAddr());

//for dropdown
rbdRefBirthDocsMap.setAssessmentYear(rbdRefBirthDocsMap.getAssessmentYearGuid());
rbdRefBirthDocsMap.setRbdDocsCategory(rbdRefBirthDocsMap.getMstDocsCategoryGuid());
rbdRefBirthDocsMap.setDocsSubmissionInfo(rbdRefBirthDocsMap.getDocsSubmissionInfoGuid());
rbdRefBirthDocsMap.setRequestSubmissionType(rbdRefBirthDocsMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(rbdRefBirthDocsMap.getAssessmentYear()!=null && !rbdRefBirthDocsMap.getAssessmentYear().isEmpty()){
	rbdRefBirthDocsMap.setAssessmentYearMaster(new AssessmentYear(rbdRefBirthDocsMap.getAssessmentYear()));
}

//DocsCategoryInfo
if(rbdRefBirthDocsMap.getRbdDocsCategory()!=null && !rbdRefBirthDocsMap.getRbdDocsCategory().isEmpty()){
	rbdRefBirthDocsMap.setRbdDocsCategoryMaster(new RbdMstDocsCategory(rbdRefBirthDocsMap.getRbdDocsCategory()));
}

//DocsSubmissionInfo
if(rbdRefBirthDocsMap.getDocsSubmissionInfo()!=null && !rbdRefBirthDocsMap.getDocsSubmissionInfo().isEmpty()){
	rbdRefBirthDocsMap.setDocsSubmissionInfoMaster(new DocsSubmissionInfo(rbdRefBirthDocsMap.getDocsSubmissionInfo()));
}

//RequestSubmissionType
if(rbdRefBirthDocsMap.getRequestSubmissionType()!=null && !rbdRefBirthDocsMap.getRequestSubmissionType().isEmpty()){
	rbdRefBirthDocsMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(rbdRefBirthDocsMap.getRequestSubmissionType()));
}

if (rbdRefBirthDocsMap.getIsExtraDocInfoRequired() == null)
	rbdRefBirthDocsMap.setIsExtraDocInfoRequired(false);

if (rbdRefBirthDocsMap.getIsMandatory() == null)
	rbdRefBirthDocsMap.setIsMandatory(false);

if (rbdRefBirthDocsMap.getIsActive() == null)
	rbdRefBirthDocsMap.setIsActive(false);

//rbdRefBirthDocsMap.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//rbdRefBirthDocsMap.setCreaterRemarks(userSessionParam.getUserFullName());
//rbdRefBirthDocsMap.setCreaterMacId(HttpSessionHelper.getMacAddress());
//rbdRefBirthDocsMap.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	RbdRefBirthDocsMap existingRbdRefBirthDocsMap = commonMasterService.getRbdRefBirthDocsMapById(rbdRefBirthDocsMap.getBirthDocsMapGuid());

if (existingRbdRefBirthDocsMap != null) {
	logger.info("\n New Values added by Login-id || "+httpSession.getAttribute("loginID")+" || IP-Address "
			+HttpSessionHelper.getClientIPAddress(request)+" || Mac-Address : "+HttpSessionHelper.getMacAddress());
	existingRbdRefBirthDocsMap.setIsExtraDocInfoRequired(rbdRefBirthDocsMap.getIsExtraDocInfoRequired() != null ? rbdRefBirthDocsMap.getIsExtraDocInfoRequired() : existingRbdRefBirthDocsMap.getIsExtraDocInfoRequired());
	existingRbdRefBirthDocsMap.setIsMandatory(rbdRefBirthDocsMap.getIsMandatory() != null ? rbdRefBirthDocsMap.getIsMandatory() : existingRbdRefBirthDocsMap.getIsMandatory());	
	existingRbdRefBirthDocsMap.setIsActive(rbdRefBirthDocsMap.getIsActive() != null ? rbdRefBirthDocsMap.getIsActive() : existingRbdRefBirthDocsMap.getIsActive());

	existingRbdRefBirthDocsMap.setModifiedIpAddr(request.getRemoteAddr());
	existingRbdRefBirthDocsMap.setModifiedDate(new Date());
	existingRbdRefBirthDocsMap.setModifiedBy("admin");
//existingRefDocsCategoryMap.setModifiedByGuid("admin");

//dropdown
	existingRbdRefBirthDocsMap.setAssessmentYear(rbdRefBirthDocsMap.getAssessmentYearGuid());
	existingRbdRefBirthDocsMap.setRbdDocsCategory(rbdRefBirthDocsMap.getMstDocsCategoryGuid());
	existingRbdRefBirthDocsMap.setDocsSubmissionInfo(rbdRefBirthDocsMap.getDocsSubmissionInfoGuid());
	existingRbdRefBirthDocsMap.setRequestSubmissionType(rbdRefBirthDocsMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(existingRbdRefBirthDocsMap.getAssessmentYear()!=null && !existingRbdRefBirthDocsMap.getAssessmentYear().isEmpty()){
	existingRbdRefBirthDocsMap.setAssessmentYearMaster(new AssessmentYear(existingRbdRefBirthDocsMap.getAssessmentYear()));
}

//DocsCategoryInfo
if(existingRbdRefBirthDocsMap.getRbdDocsCategory()!=null && !existingRbdRefBirthDocsMap.getRbdDocsCategory().isEmpty()){
	existingRbdRefBirthDocsMap.setRbdDocsCategoryMaster(new RbdMstDocsCategory(existingRbdRefBirthDocsMap.getRbdDocsCategory()));
}

//DocsSubmissionInfo
if(existingRbdRefBirthDocsMap.getDocsSubmissionInfo()!=null && !existingRbdRefBirthDocsMap.getDocsSubmissionInfo().isEmpty()){
	existingRbdRefBirthDocsMap.setDocsSubmissionInfoMaster(new DocsSubmissionInfo(existingRbdRefBirthDocsMap.getDocsSubmissionInfo()));
}

//RequestSubmissionType
if(existingRbdRefBirthDocsMap.getRequestSubmissionType()!=null && !existingRbdRefBirthDocsMap.getRequestSubmissionType().isEmpty()){
	existingRbdRefBirthDocsMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRbdRefBirthDocsMap.getRequestSubmissionType()));
}

if (existingRbdRefBirthDocsMap.getIsExtraDocInfoRequired() == null)
	existingRbdRefBirthDocsMap.setIsExtraDocInfoRequired(false);

if (existingRbdRefBirthDocsMap.getIsMandatory() == null)
	existingRbdRefBirthDocsMap.setIsMandatory(false);

if (existingRbdRefBirthDocsMap.getIsActive() == null)
	existingRbdRefBirthDocsMap.setIsActive(false);

rbdRefBirthDocsMap = existingRbdRefBirthDocsMap; // Use the updated existing country object
} else {
log.error("RbdRefBirthDocsMap not found");
resultData.setStatus(false);
resultData.setMessage("RbdRefBirthDocsMap not found");
return resultData;
}
}

//Validation
resultData = validator.validateRbdRefBirthDocsMap(rbdRefBirthDocsMap);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (rbdRefBirthDocsMap.getIsExtraDocInfoRequired() == null) rbdRefBirthDocsMap.setIsExtraDocInfoRequired(false);
if (rbdRefBirthDocsMap.getIsMandatory() == null) rbdRefBirthDocsMap.setIsMandatory(false);
if (rbdRefBirthDocsMap.getIsActive() == null) rbdRefBirthDocsMap.setIsActive(false);
//geoZoneMCD.setZoneCode(!Util.isNullOrEmpty(geoZoneMCD.getZoneCode()) ? geoZoneMCD.getZoneCode().toUpperCase().trim() : null);
//geoZoneMCD.setZoneNameEn(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameEn()) ? geoZoneMCD.getZoneNameEn().toUpperCase().trim() : null);

try {
	rbdRefBirthDocsMapRepo.save(rbdRefBirthDocsMap);
	 logger.info("Record {} successfully by Login-ID:" );
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
@GetMapping("/getRbdRefBirthDocsMapByGuid/{birthDocsMapGuid}")
public ResponseEntity<RbdRefBirthDocsMap> getRbdRefBirthDocsMapByGuid(@PathVariable("birthDocsMapGuid") String birthDocsMapGuid) {
	RbdRefBirthDocsMap rbdRefBirthDocsMap = rbdRefBirthDocsMapRepo.findById(birthDocsMapGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with birthDocsMapGuid : " + birthDocsMapGuid));
return new ResponseEntity<>(rbdRefBirthDocsMap, HttpStatus.OK);
}

/////////////////////////////////////RbdRefBirthDocsMap End///////////////////////////////////


/////////////////////////////////////RbdRefDeathDocsMap Start///////////////////////////////////

//get all data from table
@GetMapping("/getRbdRefDeathDocsMapList")
public ResponseEntity<BaseResponse> getRbdRefDeathDocsMapList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RbdRefDeathDocsMap> list = rbdRefDeathDocsMapRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRbdRefDeathDocsMap(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitRbdRefDeathDocsMap")
public BaseResponse submitRbdRefDeathDocsMap(@RequestBody RbdRefDeathDocsMap rbdRefDeathDocsMap, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (rbdRefDeathDocsMap.getDeathDocsMapGuid() == null || rbdRefDeathDocsMap.getDeathDocsMapGuid().isEmpty()) {

//Add new data
	rbdRefDeathDocsMap.setCreatedIpAddr(request.getRemoteAddr());
	rbdRefDeathDocsMap.setDeathDocsMapGuid(UUID.randomUUID().toString());
	rbdRefDeathDocsMap.setCreatedDate(new Date());
	rbdRefDeathDocsMap.setModifiedIpAddr(null);
	rbdRefDeathDocsMap.setCreatedBy("admin");
	rbdRefDeathDocsMap.setModifiedBy(null);
	rbdRefDeathDocsMap.setModifiedDate(null);
	rbdRefDeathDocsMap.setCreatedBy(request.getRemoteAddr());

//for dropdown
	rbdRefDeathDocsMap.setAssessmentYear(rbdRefDeathDocsMap.getAssessmentYearGuid());
	rbdRefDeathDocsMap.setRbdDocsCategory(rbdRefDeathDocsMap.getMstDocsCategoryGuid());
	rbdRefDeathDocsMap.setDocsSubmissionInfo(rbdRefDeathDocsMap.getDocsSubmissionInfoGuid());
	rbdRefDeathDocsMap.setRequestSubmissionType(rbdRefDeathDocsMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(rbdRefDeathDocsMap.getAssessmentYear()!=null && !rbdRefDeathDocsMap.getAssessmentYear().isEmpty()){
	rbdRefDeathDocsMap.setAssessmentYearMaster(new AssessmentYear(rbdRefDeathDocsMap.getAssessmentYear()));
}

//DocsCategoryInfo
if(rbdRefDeathDocsMap.getRbdDocsCategory()!=null && !rbdRefDeathDocsMap.getRbdDocsCategory().isEmpty()){
	rbdRefDeathDocsMap.setRbdDocsCategoryMaster(new RbdMstDocsCategory(rbdRefDeathDocsMap.getRbdDocsCategory()));
}

//DocsSubmissionInfo
if(rbdRefDeathDocsMap.getDocsSubmissionInfo()!=null && !rbdRefDeathDocsMap.getDocsSubmissionInfo().isEmpty()){
	rbdRefDeathDocsMap.setDocsSubmissionInfoMaster(new DocsSubmissionInfo(rbdRefDeathDocsMap.getDocsSubmissionInfo()));
}

//RequestSubmissionType
if(rbdRefDeathDocsMap.getRequestSubmissionType()!=null && !rbdRefDeathDocsMap.getRequestSubmissionType().isEmpty()){
	rbdRefDeathDocsMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(rbdRefDeathDocsMap.getRequestSubmissionType()));
}

if (rbdRefDeathDocsMap.getIsExtraDocInfoRequired() == null)
	rbdRefDeathDocsMap.setIsExtraDocInfoRequired(false);

if (rbdRefDeathDocsMap.getIsMandatory() == null)
	rbdRefDeathDocsMap.setIsMandatory(false);

if (rbdRefDeathDocsMap.getIsActive() == null)
	rbdRefDeathDocsMap.setIsActive(false);

//rbdRefDeathDocsMap.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//rbdRefDeathDocsMap.setCreaterRemarks(userSessionParam.getUserFullName());
//rbdRefDeathDocsMap.setCreaterMacId(HttpSessionHelper.getMacAddress());
//rbdRefDeathDocsMap.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	RbdRefDeathDocsMap existingRbdRefDeathDocsMap = commonMasterService.getRbdRefDeathDocsMapById(rbdRefDeathDocsMap.getDeathDocsMapGuid());

if (existingRbdRefDeathDocsMap != null) {
	existingRbdRefDeathDocsMap.setIsExtraDocInfoRequired(rbdRefDeathDocsMap.getIsExtraDocInfoRequired() != null ? rbdRefDeathDocsMap.getIsExtraDocInfoRequired() : existingRbdRefDeathDocsMap.getIsExtraDocInfoRequired());
	existingRbdRefDeathDocsMap.setIsMandatory(rbdRefDeathDocsMap.getIsMandatory() != null ? rbdRefDeathDocsMap.getIsMandatory() : existingRbdRefDeathDocsMap.getIsMandatory());	
	existingRbdRefDeathDocsMap.setIsActive(rbdRefDeathDocsMap.getIsActive() != null ? rbdRefDeathDocsMap.getIsActive() : existingRbdRefDeathDocsMap.getIsActive());

	existingRbdRefDeathDocsMap.setModifiedIpAddr(request.getRemoteAddr());
	existingRbdRefDeathDocsMap.setModifiedDate(new Date());
	existingRbdRefDeathDocsMap.setModifiedBy("admin");
//existingRefDocsCategoryMap.setModifiedByGuid("admin");

//dropdown
	existingRbdRefDeathDocsMap.setAssessmentYear(rbdRefDeathDocsMap.getAssessmentYearGuid());
	existingRbdRefDeathDocsMap.setRbdDocsCategory(rbdRefDeathDocsMap.getMstDocsCategoryGuid());
	existingRbdRefDeathDocsMap.setDocsSubmissionInfo(rbdRefDeathDocsMap.getDocsSubmissionInfoGuid());
	existingRbdRefDeathDocsMap.setRequestSubmissionType(rbdRefDeathDocsMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(existingRbdRefDeathDocsMap.getAssessmentYear()!=null && !existingRbdRefDeathDocsMap.getAssessmentYear().isEmpty()){
	existingRbdRefDeathDocsMap.setAssessmentYearMaster(new AssessmentYear(existingRbdRefDeathDocsMap.getAssessmentYear()));
}

//DocsCategoryInfo
if(existingRbdRefDeathDocsMap.getRbdDocsCategory()!=null && !existingRbdRefDeathDocsMap.getRbdDocsCategory().isEmpty()){
	existingRbdRefDeathDocsMap.setRbdDocsCategoryMaster(new RbdMstDocsCategory(existingRbdRefDeathDocsMap.getRbdDocsCategory()));
}

//DocsSubmissionInfo
if(existingRbdRefDeathDocsMap.getDocsSubmissionInfo()!=null && !existingRbdRefDeathDocsMap.getDocsSubmissionInfo().isEmpty()){
	existingRbdRefDeathDocsMap.setDocsSubmissionInfoMaster(new DocsSubmissionInfo(existingRbdRefDeathDocsMap.getDocsSubmissionInfo()));
}

//RequestSubmissionType
if(existingRbdRefDeathDocsMap.getRequestSubmissionType()!=null && !existingRbdRefDeathDocsMap.getRequestSubmissionType().isEmpty()){
	existingRbdRefDeathDocsMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRbdRefDeathDocsMap.getRequestSubmissionType()));
}

if (existingRbdRefDeathDocsMap.getIsExtraDocInfoRequired() == null)
	existingRbdRefDeathDocsMap.setIsExtraDocInfoRequired(false);

if (existingRbdRefDeathDocsMap.getIsMandatory() == null)
	existingRbdRefDeathDocsMap.setIsMandatory(false);

if (existingRbdRefDeathDocsMap.getIsActive() == null)
	existingRbdRefDeathDocsMap.setIsActive(false);

rbdRefDeathDocsMap = existingRbdRefDeathDocsMap; // Use the updated existing country object
} else {
log.error("RbdRefDeathDocsMap not found");
resultData.setStatus(false);
resultData.setMessage("RbdRefDeathDocsMap not found");
return resultData;
}
}

//Validation
resultData = validator.validateRbdRefDeathDocsMap(rbdRefDeathDocsMap);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (rbdRefDeathDocsMap.getIsExtraDocInfoRequired() == null) rbdRefDeathDocsMap.setIsExtraDocInfoRequired(false);
if (rbdRefDeathDocsMap.getIsMandatory() == null) rbdRefDeathDocsMap.setIsMandatory(false);
if (rbdRefDeathDocsMap.getIsActive() == null) rbdRefDeathDocsMap.setIsActive(false);
//rbdRefDeathDocsMap.setZoneCode(!Util.isNullOrEmpty(geoZoneMCD.getZoneCode()) ? geoZoneMCD.getZoneCode().toUpperCase().trim() : null);
//rbdRefDeathDocsMap.setZoneNameEn(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameEn()) ? geoZoneMCD.getZoneNameEn().toUpperCase().trim() : null);

try {
	rbdRefDeathDocsMapRepo.save(rbdRefDeathDocsMap);
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
@GetMapping("/getRbdRefDeathDocsMapByGuid/{deathDocsMapGuid}")
public ResponseEntity<RbdRefDeathDocsMap> getRbdRefDeathDocsMapByGuid(@PathVariable("deathDocsMapGuid") String deathDocsMapGuid) {
	RbdRefDeathDocsMap rbdRefDeathDocsMap = rbdRefDeathDocsMapRepo.findById(deathDocsMapGuid.trim()).orElseThrow(() -> new ResourceNotFoundException("Resource not found with deathDocsMapGuid : " + deathDocsMapGuid));
return new ResponseEntity<>(rbdRefDeathDocsMap, HttpStatus.OK);
}

/////////////////////////////////////RbdRefDeathDocsMap End///////////////////////////////////

///////////////////////////////RbdRefChargeMap Start///////////////////////////////////////
	//get all data from table
	  @GetMapping("/getRbdRefChargeMapList")
	  public ResponseEntity<BaseResponse> getRbdRefChargeMapList() {
	  BaseResponse response = new BaseResponse();
	  //Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	  List<RbdRefChargeMap> list = rbdRefChargeMapRepo.findAll();
	  response.setMessage("success");
	  response.setStatus(true);
	  response.setTotalDataCount(list.size());
	  response.setRbdRefChargeMap(list);
	  return ResponseEntity.ok(response);
	  }

	  //Create New Data And Update
	  @PostMapping("/submitRbdRefChargeMap")
	  public BaseResponse submitRbdRefChargeMap(@RequestBody RbdRefChargeMap rbdRefChargeMap, HttpServletRequest request) {
	  BaseResponse resultData = new BaseResponse();

	  //Check if guid is provided (indicating an update)
	  if (rbdRefChargeMap.getChargeMapGuid() == null || rbdRefChargeMap.getChargeMapGuid().isEmpty()) {

	  //Add new data
		  rbdRefChargeMap.setCreatedIpAddr(request.getRemoteAddr());
		  rbdRefChargeMap.setChargeMapGuid(UUID.randomUUID().toString());
		  rbdRefChargeMap.setCreatedDate(new Date());
		  rbdRefChargeMap.setModifiedIpAddr(null);
		  rbdRefChargeMap.setCreatedBy("admin");
	
		  rbdRefChargeMap.setModifiedDate(null);
		  rbdRefChargeMap.setModifiedBy(null);
	

	  //for dropdown
		  rbdRefChargeMap.setAssessmentYear(rbdRefChargeMap.getAssessmentYearGuid());
		  rbdRefChargeMap.setRequestSubmissionType(rbdRefChargeMap.getRequestSubmissionTypeGuid());

	  //AssessmentYear
	  if(rbdRefChargeMap.getAssessmentYear()!=null && !rbdRefChargeMap.getAssessmentYear().isEmpty()){
		  rbdRefChargeMap.setAssessmentYearMaster(new AssessmentYear(rbdRefChargeMap.getAssessmentYear()));
	  }

	  //RequestSubmissionType
	  if(rbdRefChargeMap.getRequestSubmissionType()!=null && !rbdRefChargeMap.getRequestSubmissionType().isEmpty()){
		  rbdRefChargeMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(rbdRefChargeMap.getRequestSubmissionType()));
	  }

	 

	  if (rbdRefChargeMap.getIsMandatory() == null)
		  rbdRefChargeMap.setIsMandatory(false);

	  if (rbdRefChargeMap.getIsActive() == null)
		  rbdRefChargeMap.setIsActive(false);

	  //rbdRefChargeMap.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
	  //rbdRefChargeMap.setCreaterRemarks(userSessionParam.getUserFullName());
	  //rbdRefChargeMap.setCreaterMacId(HttpSessionHelper.getMacAddress());
	  //rbdRefChargeMap.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
	  } else {
	  //Update existing data
		  RbdRefChargeMap existingRbdRefChargeMap = commonMasterService.getRbdRefChargeMapById(rbdRefChargeMap.getChargeMapGuid());

	  if (existingRbdRefChargeMap != null) {
		  existingRbdRefChargeMap.setChargeMapSubmittedForType(!Util.isNullOrEmpty(rbdRefChargeMap.getChargeMapSubmittedForType()) ? rbdRefChargeMap.getChargeMapSubmittedForType().toUpperCase().trim() : null);
		  existingRbdRefChargeMap.setRegistrationFeeValue(!Util.isNullOrZero(rbdRefChargeMap.getRegistrationFeeValue()) ? rbdRefChargeMap.getRegistrationFeeValue() : null);
		  existingRbdRefChargeMap.setProcessingFeeValue(!Util.isNullOrZero(rbdRefChargeMap.getProcessingFeeValue()) ? rbdRefChargeMap.getProcessingFeeValue() : null);
		  existingRbdRefChargeMap.setPrintRequestFeeValue(!Util.isNullOrZero(rbdRefChargeMap.getPrintRequestFeeValue()) ? rbdRefChargeMap.getPrintRequestFeeValue() : null);
		  existingRbdRefChargeMap.setApplicableFactor(!Util.isNullOrEmpty(rbdRefChargeMap.getApplicableFactor()) ? rbdRefChargeMap.getApplicableFactor().toUpperCase().trim() : null);
		  existingRbdRefChargeMap.setChargesFactor(!Util.isNullOrEmpty(rbdRefChargeMap.getChargesFactor()) ? rbdRefChargeMap.getChargesFactor().toUpperCase().trim() : null);
		 
		  existingRbdRefChargeMap.setIsMandatory(rbdRefChargeMap.getIsMandatory() != null ? rbdRefChargeMap.getIsMandatory() : existingRbdRefChargeMap.getIsMandatory());	
		  existingRbdRefChargeMap.setIsActive(rbdRefChargeMap.getIsActive() != null ? rbdRefChargeMap.getIsActive() : existingRbdRefChargeMap.getIsActive());

		  existingRbdRefChargeMap.setModifiedIpAddr(request.getRemoteAddr());
		  existingRbdRefChargeMap.setModifiedDate(new Date());
		  existingRbdRefChargeMap.setModifiedBy("admin");
	  //existingRbdRefChargeMap.setModifiedByGuid("admin");

	  //dropdown
		  existingRbdRefChargeMap.setAssessmentYear(rbdRefChargeMap.getAssessmentYearGuid());
		  existingRbdRefChargeMap.setRequestSubmissionType(rbdRefChargeMap.getRequestSubmissionTypeGuid());

	  //AssessmentYear
	  if(existingRbdRefChargeMap.getAssessmentYear()!=null && !existingRbdRefChargeMap.getAssessmentYear().isEmpty()){
		  existingRbdRefChargeMap.setAssessmentYearMaster(new AssessmentYear(existingRbdRefChargeMap.getAssessmentYear()));
	  }
	  //RequestSubmissionType
	  if(existingRbdRefChargeMap.getRequestSubmissionType()!=null && !existingRbdRefChargeMap.getRequestSubmissionType().isEmpty()){
		  existingRbdRefChargeMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRbdRefChargeMap.getRequestSubmissionType()));
	  }

	

	  if (existingRbdRefChargeMap.getIsMandatory() == null)
		  existingRbdRefChargeMap.setIsMandatory(false);

	  if (existingRbdRefChargeMap.getIsActive() == null)
		  existingRbdRefChargeMap.setIsActive(false);

	  rbdRefChargeMap = existingRbdRefChargeMap; // Use the updated existing country object
	  } else {
	  log.error("RbdRefChargeMap not found");
	  resultData.setStatus(false);
	  resultData.setMessage("RbdRefChargeMap not found");
	  return resultData;
	  }
	  }

	  //Validation
	  resultData = validator.validateRbdRefChargeMap(rbdRefChargeMap);
	  if (resultData != null && !resultData.getStatus()) {
	  log.error("Validation failed: {}", resultData.getMessage());
	  return resultData;
	  }

	  //If validation passes, proceed to save or update
	  if (rbdRefChargeMap.getIsMandatory() == null) rbdRefChargeMap.setIsMandatory(false);
	  if (rbdRefChargeMap.getIsActive() == null) rbdRefChargeMap.setIsActive(false);
	  rbdRefChargeMap.setChargeMapSubmittedForType(!Util.isNullOrEmpty(rbdRefChargeMap.getChargeMapSubmittedForType()) ? rbdRefChargeMap.getChargeMapSubmittedForType().toUpperCase().trim() : null);
	  rbdRefChargeMap.setRegistrationFeeValue(!Util.isNullOrZero(rbdRefChargeMap.getRegistrationFeeValue()) ? rbdRefChargeMap.getRegistrationFeeValue() : null);
	  rbdRefChargeMap.setProcessingFeeValue(!Util.isNullOrZero(rbdRefChargeMap.getProcessingFeeValue()) ? rbdRefChargeMap.getProcessingFeeValue() : null);
	  rbdRefChargeMap.setPrintRequestFeeValue(!Util.isNullOrZero(rbdRefChargeMap.getPrintRequestFeeValue()) ? rbdRefChargeMap.getPrintRequestFeeValue() : null);
	  rbdRefChargeMap.setApplicableFactor(!Util.isNullOrEmpty(rbdRefChargeMap.getApplicableFactor()) ? rbdRefChargeMap.getApplicableFactor().toUpperCase().trim() : null);
	  rbdRefChargeMap.setChargesFactor(!Util.isNullOrEmpty(rbdRefChargeMap.getChargesFactor()) ? rbdRefChargeMap.getChargesFactor().toUpperCase().trim() : null);
	 

	  try {
		  rbdRefChargeMapRepo.save(rbdRefChargeMap);
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
	  @GetMapping("/getRbdRefChargeMapByGuid/{chargeMapGuid}")
	  public ResponseEntity<RbdRefChargeMap> getRbdRefChargeMapByGuid(@PathVariable("chargeMapGuid") String chargeMapGuid) {
		  RbdRefChargeMap rbdRefChargeMap = rbdRefChargeMapRepo.findById(chargeMapGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with chargeMapGuid : " + chargeMapGuid));
	  return new ResponseEntity<>(rbdRefChargeMap, HttpStatus.OK);
	  }
	  
	  ///////////////////////////////RbdRefChargeMap End///////////////////////////////////////
	  
	  
/////////////////////////////////////RbdRefRelationMap Start///////////////////////////////////

//get all data from table
@GetMapping("/getRbdRefRelationMapList")
public ResponseEntity<BaseResponse> getRbdRefRelationMapList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RbdRefRelationMap> list = rbdRefRelationMapRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRbdRefRelationMap(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitRbdRefRelationMap")
public BaseResponse submitRbdRefRelationMap(@RequestBody RbdRefRelationMap rbdRefRelationMap, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (rbdRefRelationMap.getRelationMapGuid() == null || rbdRefRelationMap.getRelationMapGuid().isEmpty()) {

//Add new data
	rbdRefRelationMap.setCreatedIpAddr(request.getRemoteAddr());
	rbdRefRelationMap.setRelationMapGuid(UUID.randomUUID().toString());
	rbdRefRelationMap.setCreatedDate(new Date());
rbdRefRelationMap.setModifiedIpAddr(null);
rbdRefRelationMap.setCreatedBy("admin");
rbdRefRelationMap.setModifiedBy(null);
rbdRefRelationMap.setModifiedDate(null);
rbdRefRelationMap.setCreatedBy(request.getRemoteAddr());

//for dropdown
rbdRefRelationMap.setAssessmentYear(rbdRefRelationMap.getAssessmentYearGuid());
rbdRefRelationMap.setPersRelation(rbdRefRelationMap.getPersRelationGuid());
rbdRefRelationMap.setRequestSubmissionType(rbdRefRelationMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(rbdRefRelationMap.getAssessmentYear()!=null && !rbdRefRelationMap.getAssessmentYear().isEmpty()){
	rbdRefRelationMap.setAssessmentYearMaster(new AssessmentYear(rbdRefRelationMap.getAssessmentYear()));
}

//PersRelation
if(rbdRefRelationMap.getPersRelation()!=null && !rbdRefRelationMap.getPersRelation().isEmpty()){
	rbdRefRelationMap.setPersRelationMaster(new PersRelation(rbdRefRelationMap.getPersRelation()));
}

//RequestSubmissionType
if(rbdRefRelationMap.getRequestSubmissionType()!=null && !rbdRefRelationMap.getRequestSubmissionType().isEmpty()){
	rbdRefRelationMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(rbdRefRelationMap.getRequestSubmissionType()));
}



if (rbdRefRelationMap.getIsMandatory() == null)
	rbdRefRelationMap.setIsMandatory(false);

if (rbdRefRelationMap.getIsActive() == null)
	rbdRefRelationMap.setIsActive(false);

//rbdRefRelationMap.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//rbdRefRelationMap.setCreaterRemarks(userSessionParam.getUserFullName());
//rbdRefRelationMap.setCreaterMacId(HttpSessionHelper.getMacAddress());
//rbdRefRelationMap.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	RbdRefRelationMap existingRbdRefRelationMap = commonMasterService.getRbdRefRelationMapById(rbdRefRelationMap.getRelationMapGuid());

if (existingRbdRefRelationMap != null) {
	existingRbdRefRelationMap.setIsMandatory(rbdRefRelationMap.getIsMandatory() != null ? rbdRefRelationMap.getIsMandatory() : existingRbdRefRelationMap.getIsMandatory());	
	existingRbdRefRelationMap.setIsActive(rbdRefRelationMap.getIsActive() != null ? rbdRefRelationMap.getIsActive() : existingRbdRefRelationMap.getIsActive());

	existingRbdRefRelationMap.setModifiedIpAddr(request.getRemoteAddr());
	existingRbdRefRelationMap.setModifiedDate(new Date());
	existingRbdRefRelationMap.setModifiedBy("admin");
//existingRbdRefRelationMap.setModifiedByGuid("admin");

//dropdown
	existingRbdRefRelationMap.setAssessmentYear(rbdRefRelationMap.getAssessmentYearGuid());
	existingRbdRefRelationMap.setPersRelation(rbdRefRelationMap.getPersRelationGuid());
	existingRbdRefRelationMap.setRequestSubmissionType(rbdRefRelationMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(existingRbdRefRelationMap.getAssessmentYear()!=null && !existingRbdRefRelationMap.getAssessmentYear().isEmpty()){
	existingRbdRefRelationMap.setAssessmentYearMaster(new AssessmentYear(existingRbdRefRelationMap.getAssessmentYear()));
}



//PersRelation
if(existingRbdRefRelationMap.getPersRelation()!=null && !existingRbdRefRelationMap.getPersRelation().isEmpty()){
	existingRbdRefRelationMap.setPersRelationMaster(new PersRelation(existingRbdRefRelationMap.getPersRelation()));
}

//RequestSubmissionType
if(existingRbdRefRelationMap.getRequestSubmissionType()!=null && !existingRbdRefRelationMap.getRequestSubmissionType().isEmpty()){
	existingRbdRefRelationMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRbdRefRelationMap.getRequestSubmissionType()));
}


if (existingRbdRefRelationMap.getIsMandatory() == null)
	existingRbdRefRelationMap.setIsMandatory(false);

if (existingRbdRefRelationMap.getIsActive() == null)
	existingRbdRefRelationMap.setIsActive(false);

rbdRefRelationMap = existingRbdRefRelationMap; // Use the updated existing country object
} else {
log.error("RbdRefRelationMap not found");
resultData.setStatus(false);
resultData.setMessage("RbdRefRelationMap not found");
return resultData;
}
}

//Validation
resultData = validator.validateRbdRefRelationMap(rbdRefRelationMap);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (rbdRefRelationMap.getIsMandatory() == null) rbdRefRelationMap.setIsMandatory(false);
if (rbdRefRelationMap.getIsActive() == null) rbdRefRelationMap.setIsActive(false);
//geoZoneMCD.setZoneCode(!Util.isNullOrEmpty(geoZoneMCD.getZoneCode()) ? geoZoneMCD.getZoneCode().toUpperCase().trim() : null);
//geoZoneMCD.setZoneNameEn(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameEn()) ? geoZoneMCD.getZoneNameEn().toUpperCase().trim() : null);

try {
	rbdRefRelationMapRepo.save(rbdRefRelationMap);
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
@GetMapping("/getRbdRefRelationMapByGuid/{relationMapGuid}")
public ResponseEntity<RbdRefRelationMap> getRbdRefRelationMapByGuid(@PathVariable("relationMapGuid") String relationMapGuid) {
	RbdRefRelationMap rbdRefRelationMap = rbdRefRelationMapRepo.findById(relationMapGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with relationMapGuid : " + relationMapGuid));
return new ResponseEntity<>(rbdRefRelationMap, HttpStatus.OK);
}

/////////////////////////////////////RbdRefRelationMap End///////////////////////////////////

/////////////////////////////////////RbdRefOccupationMap Start///////////////////////////////////

//get all data from table
@GetMapping("/getRbdRefOccupationMapList")
public ResponseEntity<BaseResponse> getRbdRefOccupationMapList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RbdRefOccupationMap> list = rbdRefOccupationMapRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRbdRefOccupationMap(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitRbdRefOccupationMap")
public BaseResponse submitRbdRefOccupationMap(@RequestBody RbdRefOccupationMap rbdRefOccupationMap, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (rbdRefOccupationMap.getOccupationMapGuid() == null || rbdRefOccupationMap.getOccupationMapGuid().isEmpty()) {

//Add new data
	rbdRefOccupationMap.setCreatedIpAddr(request.getRemoteAddr());
	rbdRefOccupationMap.setOccupationMapGuid(UUID.randomUUID().toString());
	rbdRefOccupationMap.setCreatedDate(new Date());
	rbdRefOccupationMap.setModifiedIpAddr(null);
	rbdRefOccupationMap.setCreatedBy("admin");
	rbdRefOccupationMap.setModifiedBy(null);
	rbdRefOccupationMap.setModifiedDate(null);
	rbdRefOccupationMap.setCreatedBy(request.getRemoteAddr());

//for dropdown
	rbdRefOccupationMap.setAssessmentYear(rbdRefOccupationMap.getAssessmentYearGuid());
	rbdRefOccupationMap.setOccupationType(rbdRefOccupationMap.getOccupationGuid());
	rbdRefOccupationMap.setRequestSubmissionType(rbdRefOccupationMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(rbdRefOccupationMap.getAssessmentYear()!=null && !rbdRefOccupationMap.getAssessmentYear().isEmpty()){
	rbdRefOccupationMap.setAssessmentYearMaster(new AssessmentYear(rbdRefOccupationMap.getAssessmentYear()));
}

//OccupationType
if(rbdRefOccupationMap.getOccupationType()!=null && !rbdRefOccupationMap.getOccupationType().isEmpty()){
	rbdRefOccupationMap.setOccupationTypeMaster(new OccupationType(rbdRefOccupationMap.getOccupationType()));
}

//RequestSubmissionType
if(rbdRefOccupationMap.getRequestSubmissionType()!=null && !rbdRefOccupationMap.getRequestSubmissionType().isEmpty()){
	rbdRefOccupationMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(rbdRefOccupationMap.getRequestSubmissionType()));
}



if (rbdRefOccupationMap.getIsActive() == null)
	rbdRefOccupationMap.setIsActive(false);

//rbdRefOccupationMap.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//rbdRefOccupationMap.setCreaterRemarks(userSessionParam.getUserFullName());
//rbdRefOccupationMap.setCreaterMacId(HttpSessionHelper.getMacAddress());
//rbdRefOccupationMap.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	RbdRefOccupationMap existingRbdRefOccupationMap = commonMasterService.getRbdRefOccupationMapById(rbdRefOccupationMap.getOccupationMapGuid());

if (existingRbdRefOccupationMap != null) {
	existingRbdRefOccupationMap.setIsActive(rbdRefOccupationMap.getIsActive() != null ? rbdRefOccupationMap.getIsActive() : existingRbdRefOccupationMap.getIsActive());

	existingRbdRefOccupationMap.setModifiedIpAddr(request.getRemoteAddr());
	existingRbdRefOccupationMap.setModifiedDate(new Date());
	existingRbdRefOccupationMap.setModifiedBy("admin");
//existingRbdRefOccupationMap.setModifiedByGuid("admin");

//dropdown
	existingRbdRefOccupationMap.setAssessmentYear(rbdRefOccupationMap.getAssessmentYearGuid());
	existingRbdRefOccupationMap.setOccupationType(rbdRefOccupationMap.getOccupationGuid());
	existingRbdRefOccupationMap.setRequestSubmissionType(rbdRefOccupationMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(existingRbdRefOccupationMap.getAssessmentYear()!=null && !existingRbdRefOccupationMap.getAssessmentYear().isEmpty()){
	existingRbdRefOccupationMap.setAssessmentYearMaster(new AssessmentYear(existingRbdRefOccupationMap.getAssessmentYear()));
}



//OccupationType
if(existingRbdRefOccupationMap.getOccupationType()!=null && !existingRbdRefOccupationMap.getOccupationType().isEmpty()){
	existingRbdRefOccupationMap.setOccupationTypeMaster(new OccupationType(existingRbdRefOccupationMap.getOccupationType()));
}

//RequestSubmissionType
if(existingRbdRefOccupationMap.getRequestSubmissionType()!=null && !existingRbdRefOccupationMap.getRequestSubmissionType().isEmpty()){
	existingRbdRefOccupationMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRbdRefOccupationMap.getRequestSubmissionType()));
}



if (existingRbdRefOccupationMap.getIsActive() == null)
	existingRbdRefOccupationMap.setIsActive(false);

rbdRefOccupationMap = existingRbdRefOccupationMap; // Use the updated existing country object
} else {
log.error("RbdRefOccupationMap not found");
resultData.setStatus(false);
resultData.setMessage("RbdRefOccupationMap not found");
return resultData;
}
}

//Validation
resultData = validator.validateRbdRefOccupationMap(rbdRefOccupationMap);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (rbdRefOccupationMap.getIsActive() == null) rbdRefOccupationMap.setIsActive(false);
//geoZoneMCD.setZoneCode(!Util.isNullOrEmpty(geoZoneMCD.getZoneCode()) ? geoZoneMCD.getZoneCode().toUpperCase().trim() : null);
//geoZoneMCD.setZoneNameEn(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameEn()) ? geoZoneMCD.getZoneNameEn().toUpperCase().trim() : null);

try {
	rbdRefOccupationMapRepo.save(rbdRefOccupationMap);
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
@GetMapping("/getRbdRefOccupationMapByGuid/{occupationMapGuid}")
public ResponseEntity<RbdRefOccupationMap> getRbdRefOccupationMapByGuid(@PathVariable("occupationMapGuid") String occupationMapGuid) {
	RbdRefOccupationMap rbdRefOccupationMap = rbdRefOccupationMapRepo.findById(occupationMapGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with occupationMapGuid : " + occupationMapGuid));
return new ResponseEntity<>(rbdRefOccupationMap, HttpStatus.OK);
}

/////////////////////////////////////RbdRefOccupationMap End///////////////////////////////////


/////////////////////////////////////RbdRefEducationMap Start///////////////////////////////////

//get all data from table
@GetMapping("/getRbdRefEducationMapList")
public ResponseEntity<BaseResponse> getRbdRefEducationMapList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RbdRefEducationMap> list = rbdRefEducationMapRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRbdRefEducationMap(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitRbdRefEducationMap")
public BaseResponse submitRbdRefEducationMap(@RequestBody RbdRefEducationMap rbdRefEducationMap, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (rbdRefEducationMap.getEducationMapGuid() == null || rbdRefEducationMap.getEducationMapGuid().isEmpty()) {

//Add new data
	rbdRefEducationMap.setCreatedIpAddr(request.getRemoteAddr());
	rbdRefEducationMap.setEducationMapGuid(UUID.randomUUID().toString());
	rbdRefEducationMap.setCreatedDate(new Date());
	rbdRefEducationMap.setModifiedIpAddr(null);
	rbdRefEducationMap.setCreatedBy("admin");
	rbdRefEducationMap.setModifiedBy(null);
	rbdRefEducationMap.setModifiedDate(null);
	rbdRefEducationMap.setCreatedBy(request.getRemoteAddr());

//for dropdown
	rbdRefEducationMap.setAssessmentYear(rbdRefEducationMap.getAssessmentYearGuid());
	rbdRefEducationMap.setEducationLevel(rbdRefEducationMap.getEducationLevelGuid());
	rbdRefEducationMap.setRequestSubmissionType(rbdRefEducationMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(rbdRefEducationMap.getAssessmentYear()!=null && !rbdRefEducationMap.getAssessmentYear().isEmpty()){
	rbdRefEducationMap.setAssessmentYearMaster(new AssessmentYear(rbdRefEducationMap.getAssessmentYear()));
}

//EducationLevel
if(rbdRefEducationMap.getEducationLevel()!=null && !rbdRefEducationMap.getEducationLevel().isEmpty()){
	rbdRefEducationMap.setEducationLevelMaster(new EducationLevel(rbdRefEducationMap.getEducationLevel()));
}

//RequestSubmissionType
if(rbdRefEducationMap.getRequestSubmissionType()!=null && !rbdRefEducationMap.getRequestSubmissionType().isEmpty()){
	rbdRefEducationMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(rbdRefEducationMap.getRequestSubmissionType()));
}



if (rbdRefEducationMap.getIsActive() == null)
	rbdRefEducationMap.setIsActive(false);

//rbdRefEducationMap.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//rbdRefEducationMap.setCreaterRemarks(userSessionParam.getUserFullName());
//rbdRefEducationMap.setCreaterMacId(HttpSessionHelper.getMacAddress());
//rbdRefEducationMap.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	RbdRefEducationMap existingRbdRefEducationMap = commonMasterService.getRbdRefEducationMapById(rbdRefEducationMap.getEducationMapGuid());

if (existingRbdRefEducationMap != null) {
	existingRbdRefEducationMap.setIsActive(rbdRefEducationMap.getIsActive() != null ? rbdRefEducationMap.getIsActive() : existingRbdRefEducationMap.getIsActive());

	existingRbdRefEducationMap.setModifiedIpAddr(request.getRemoteAddr());
	existingRbdRefEducationMap.setModifiedDate(new Date());
	existingRbdRefEducationMap.setModifiedBy("admin");
//existingRbdRefEducationMap.setModifiedByGuid("admin");

//dropdown
	existingRbdRefEducationMap.setAssessmentYear(rbdRefEducationMap.getAssessmentYearGuid());
	existingRbdRefEducationMap.setEducationLevel(rbdRefEducationMap.getEducationLevelGuid());
	existingRbdRefEducationMap.setRequestSubmissionType(rbdRefEducationMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(existingRbdRefEducationMap.getAssessmentYear()!=null && !existingRbdRefEducationMap.getAssessmentYear().isEmpty()){
	existingRbdRefEducationMap.setAssessmentYearMaster(new AssessmentYear(existingRbdRefEducationMap.getAssessmentYear()));
}



//EducationLevel
if(existingRbdRefEducationMap.getEducationLevel()!=null && !existingRbdRefEducationMap.getEducationLevel().isEmpty()){
	existingRbdRefEducationMap.setEducationLevelMaster(new EducationLevel(existingRbdRefEducationMap.getEducationLevel()));
}

//RequestSubmissionType
if(existingRbdRefEducationMap.getRequestSubmissionType()!=null && !existingRbdRefEducationMap.getRequestSubmissionType().isEmpty()){
	existingRbdRefEducationMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRbdRefEducationMap.getRequestSubmissionType()));
}



if (existingRbdRefEducationMap.getIsActive() == null)
	existingRbdRefEducationMap.setIsActive(false);

rbdRefEducationMap = existingRbdRefEducationMap; // Use the updated existing country object
} else {
log.error("RbdRefEducationMap not found");
resultData.setStatus(false);
resultData.setMessage("RbdRefEducationMap not found");
return resultData;
}
}

//Validation
resultData = validator.validateRbdRefEducationMap(rbdRefEducationMap);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (rbdRefEducationMap.getIsActive() == null) rbdRefEducationMap.setIsActive(false);
//rbdRefEducationMap.setZoneCode(!Util.isNullOrEmpty(geoZoneMCD.getZoneCode()) ? rbdRefEducationMap.getZoneCode().toUpperCase().trim() : null);
//rbdRefEducationMap.setZoneNameEn(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameEn()) ? rbdRefEducationMap.getZoneNameEn().toUpperCase().trim() : null);

try {
	rbdRefEducationMapRepo.save(rbdRefEducationMap);
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
@GetMapping("/getRbdRefEducationMapByGuid/{educationMapGuid}")
public ResponseEntity<RbdRefEducationMap> getRbdRefEducationMapByGuid(@PathVariable("educationMapGuid") String educationMapGuid) {
	RbdRefEducationMap rbdRefEducationMap = rbdRefEducationMapRepo.findById(educationMapGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with educationMapGuid : " + educationMapGuid));
return new ResponseEntity<>(rbdRefEducationMap, HttpStatus.OK);
}

/////////////////////////////////////RbdRefEducationMap End///////////////////////////////////


///////////////////////////////RbdRefRegistrationNumber Start///////////////////////////////////////
//get all data from table
@GetMapping("/getRbdRefRegistrationNumberList")
public ResponseEntity<BaseResponse> getRbdRefRegistrationNumberList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RbdRefRegistrationNumber> list = rbdRefRegistrationNumberRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRbdRefRegistrationNumber(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitRbdRefRegistrationNumber")
public BaseResponse submitRbdRefRegistrationNumber(@RequestBody RbdRefRegistrationNumber rbdRefRegistrationNumber, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (rbdRefRegistrationNumber.getRefRegistrationNumberGuid() == null || rbdRefRegistrationNumber.getRefRegistrationNumberGuid().isEmpty()) {

//Add new data
	rbdRefRegistrationNumber.setCreatedIpAddr(request.getRemoteAddr());
	rbdRefRegistrationNumber.setRefRegistrationNumberGuid(UUID.randomUUID().toString());
	rbdRefRegistrationNumber.setCreatedDate(new Date());
	rbdRefRegistrationNumber.setModifiedIpAddr(null);
	rbdRefRegistrationNumber.setCreatedBy("admin");

	rbdRefRegistrationNumber.setModifiedDate(null);
	rbdRefRegistrationNumber.setModifiedBy(null);


//for dropdown
	rbdRefRegistrationNumber.setAssessmentYear(rbdRefRegistrationNumber.getAssessmentYearGuid());
	rbdRefRegistrationNumber.setOrgPrimary(rbdRefRegistrationNumber.getOrgPrimaryGuid());
	rbdRefRegistrationNumber.setZone(rbdRefRegistrationNumber.getZoneGuid());
	
	
//AssessmentYear
if(rbdRefRegistrationNumber.getAssessmentYear()!=null && !rbdRefRegistrationNumber.getAssessmentYear().isEmpty()){
	rbdRefRegistrationNumber.setAssessmentYearMaster(new AssessmentYear(rbdRefRegistrationNumber.getAssessmentYear()));
}

//OrgPrimary
if (rbdRefRegistrationNumber.getOrgPrimary() != null && !rbdRefRegistrationNumber.getOrgPrimary().isEmpty()) {
	rbdRefRegistrationNumber.setOrgPrimaryMaster(new OrgPrimary(rbdRefRegistrationNumber.getOrgPrimary()));
}
//Zone
if(rbdRefRegistrationNumber.getZone()!=null && !rbdRefRegistrationNumber.getZone().isEmpty()){
	rbdRefRegistrationNumber.setZoneMaster(new GeoZoneMCD(rbdRefRegistrationNumber.getZone()));
}

if (rbdRefRegistrationNumber.getIsActive() == null)
	rbdRefRegistrationNumber.setIsActive(false);

//rbdRefRegistrationNumber.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//rbdRefRegistrationNumber.setCreaterRemarks(userSessionParam.getUserFullName());
//rbdRefRegistrationNumber.setCreaterMacId(HttpSessionHelper.getMacAddress());
//rbdRefRegistrationNumber.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	RbdRefRegistrationNumber existingRbdRefRegistrationNumber = commonMasterService.getRbdRefRegistrationNumberById(rbdRefRegistrationNumber.getRefRegistrationNumberGuid());

if (existingRbdRefRegistrationNumber != null) {
	existingRbdRefRegistrationNumber.setSubmittedForType(!Util.isNullOrEmpty(rbdRefRegistrationNumber.getSubmittedForType()) ? rbdRefRegistrationNumber.getSubmittedForType().toUpperCase().trim() : null);
	existingRbdRefRegistrationNumber.setGeneratedNumber(!Util.isNullOrZero(rbdRefRegistrationNumber.getGeneratedNumber()) ? rbdRefRegistrationNumber.getGeneratedNumber() : null);
	existingRbdRefRegistrationNumber.setLastGeneratedNumber(!Util.isNullOrZero(rbdRefRegistrationNumber.getLastGeneratedNumber()) ? rbdRefRegistrationNumber.getLastGeneratedNumber() : null);
	
	existingRbdRefRegistrationNumber.setIsActive(rbdRefRegistrationNumber.getIsActive() != null ? rbdRefRegistrationNumber.getIsActive() : existingRbdRefRegistrationNumber.getIsActive());

	existingRbdRefRegistrationNumber.setModifiedIpAddr(request.getRemoteAddr());
	existingRbdRefRegistrationNumber.setModifiedDate(new Date());
	existingRbdRefRegistrationNumber.setModifiedBy("admin");
//existingRbdRefRegistrationNumber.setModifiedByGuid("admin");

//dropdown
	existingRbdRefRegistrationNumber.setAssessmentYear(rbdRefRegistrationNumber.getAssessmentYearGuid());
	existingRbdRefRegistrationNumber.setOrgPrimary(rbdRefRegistrationNumber.getOrgPrimaryGuid());
	existingRbdRefRegistrationNumber.setZone(rbdRefRegistrationNumber.getZoneGuid());
//AssessmentYear
if(existingRbdRefRegistrationNumber.getAssessmentYear()!=null && !existingRbdRefRegistrationNumber.getAssessmentYear().isEmpty()){
	existingRbdRefRegistrationNumber.setAssessmentYearMaster(new AssessmentYear(existingRbdRefRegistrationNumber.getAssessmentYear()));
}

//OrgPrimary
if (existingRbdRefRegistrationNumber.getOrgPrimary() != null && !existingRbdRefRegistrationNumber.getOrgPrimary().isEmpty()) {
	existingRbdRefRegistrationNumber.setOrgPrimaryMaster(new OrgPrimary(existingRbdRefRegistrationNumber.getOrgPrimary()));
}
//Zone
if(existingRbdRefRegistrationNumber.getZone()!=null && !existingRbdRefRegistrationNumber.getZone().isEmpty()){
	existingRbdRefRegistrationNumber.setZoneMaster(new GeoZoneMCD(existingRbdRefRegistrationNumber.getZone()));
}


if (existingRbdRefRegistrationNumber.getIsActive() == null)
	existingRbdRefRegistrationNumber.setIsActive(false);

rbdRefRegistrationNumber = existingRbdRefRegistrationNumber; // Use the updated existing country object
} else {
log.error("RbdRefRegistrationNumber not found");
resultData.setStatus(false);
resultData.setMessage("RbdRefRegistrationNumber not found");
return resultData;
}
}

//Validation
resultData = validator.validateRbdRefRegistrationNumber(rbdRefRegistrationNumber);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update

if (rbdRefRegistrationNumber.getIsActive() == null) rbdRefRegistrationNumber.setIsActive(false);
rbdRefRegistrationNumber.setSubmittedForType(!Util.isNullOrEmpty(rbdRefRegistrationNumber.getSubmittedForType()) ? rbdRefRegistrationNumber.getSubmittedForType().toUpperCase().trim() : null);
rbdRefRegistrationNumber.setGeneratedNumber(!Util.isNullOrZero(rbdRefRegistrationNumber.getGeneratedNumber()) ? rbdRefRegistrationNumber.getGeneratedNumber() : null);
rbdRefRegistrationNumber.setLastGeneratedNumber(!Util.isNullOrZero(rbdRefRegistrationNumber.getLastGeneratedNumber()) ? rbdRefRegistrationNumber.getLastGeneratedNumber() : null);


try {
	rbdRefRegistrationNumberRepo.save(rbdRefRegistrationNumber);
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
@GetMapping("/getRbdRefRegistrationNumberByGuid/{refRegistrationNumberGuid}")
public ResponseEntity<RbdRefRegistrationNumber> getRbdRefRegistrationNumberByGuid(@PathVariable("refRegistrationNumberGuid") String refRegistrationNumberGuid) {
	RbdRefRegistrationNumber rbdRefRegistrationNumber = rbdRefRegistrationNumberRepo.findById(refRegistrationNumberGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with refRegistrationNumberGuid : " + refRegistrationNumberGuid));
return new ResponseEntity<>(rbdRefRegistrationNumber, HttpStatus.OK);
}

///////////////////////////////RbdRefRegistrationNumber End///////////////////////////////////////

/////////////////////////////////////RbdRefDocsMap Start///////////////////////////////////

//get all data from table
@GetMapping("/getRbdRefDocsMapList")
public ResponseEntity<BaseResponse> getRbdRefDocsMapList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RbdRefDocsMap> list = rbdRefDocsMapRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRbdRefDocsMap(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitRbdRefDocsMap")
public BaseResponse submitRbdRefDocsMap(@RequestBody RbdRefDocsMap rbdRefDocsMap, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (rbdRefDocsMap.getDocsMapGuid() == null || rbdRefDocsMap.getDocsMapGuid().isEmpty()) {

//Add new data
	rbdRefDocsMap.setCreatedIpAddr(request.getRemoteAddr());
	rbdRefDocsMap.setDocsMapGuid(UUID.randomUUID().toString());
	rbdRefDocsMap.setCreatedDate(new Date());
	rbdRefDocsMap.setModifiedIpAddr(null);
	rbdRefDocsMap.setCreatedBy("admin");
	rbdRefDocsMap.setModifiedBy(null);
	rbdRefDocsMap.setModifiedDate(null);
	rbdRefDocsMap.setCreatedBy(request.getRemoteAddr());

//for dropdown
	rbdRefDocsMap.setAssessmentYear(rbdRefDocsMap.getAssessmentYearGuid());
	rbdRefDocsMap.setDocsSubmissionInfo(rbdRefDocsMap.getDocsSubmissionInfoGuid());
	rbdRefDocsMap.setRequestSubmissionType(rbdRefDocsMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(rbdRefDocsMap.getAssessmentYear()!=null && !rbdRefDocsMap.getAssessmentYear().isEmpty()){
	rbdRefDocsMap.setAssessmentYearMaster(new AssessmentYear(rbdRefDocsMap.getAssessmentYear()));
}



//DocsSubmissionInfo
if(rbdRefDocsMap.getDocsSubmissionInfo()!=null && !rbdRefDocsMap.getDocsSubmissionInfo().isEmpty()){
	rbdRefDocsMap.setDocsSubmissionInfoMaster(new DocsSubmissionInfo(rbdRefDocsMap.getDocsSubmissionInfo()));
}

//RequestSubmissionType
if(rbdRefDocsMap.getRequestSubmissionType()!=null && !rbdRefDocsMap.getRequestSubmissionType().isEmpty()){
	rbdRefDocsMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(rbdRefDocsMap.getRequestSubmissionType()));
}

if (rbdRefDocsMap.getIsExtraDocInfoRequired() == null)
	rbdRefDocsMap.setIsExtraDocInfoRequired(false);

if (rbdRefDocsMap.getIsMandatory() == null)
	rbdRefDocsMap.setIsMandatory(false);

if (rbdRefDocsMap.getIsActive() == null)
	rbdRefDocsMap.setIsActive(false);

//rbdRefDocsMap.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//rbdRefDocsMap.setCreaterRemarks(userSessionParam.getUserFullName());
//rbdRefDocsMap.setCreaterMacId(HttpSessionHelper.getMacAddress());
//rbdRefDocsMap.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	RbdRefDocsMap existingRbdRefDocsMap = commonMasterService.getRbdRefDocsMapById(rbdRefDocsMap.getDocsMapGuid());

if (existingRbdRefDocsMap != null) {
	existingRbdRefDocsMap.setIsExtraDocInfoRequired(rbdRefDocsMap.getIsExtraDocInfoRequired() != null ? rbdRefDocsMap.getIsExtraDocInfoRequired() : existingRbdRefDocsMap.getIsExtraDocInfoRequired());
	existingRbdRefDocsMap.setIsMandatory(rbdRefDocsMap.getIsMandatory() != null ? rbdRefDocsMap.getIsMandatory() : existingRbdRefDocsMap.getIsMandatory());	
	existingRbdRefDocsMap.setIsActive(rbdRefDocsMap.getIsActive() != null ? rbdRefDocsMap.getIsActive() : existingRbdRefDocsMap.getIsActive());
	existingRbdRefDocsMap.setSubmittedForType(!Util.isNullOrEmpty(rbdRefDocsMap.getSubmittedForType()) ? rbdRefDocsMap.getSubmittedForType().toUpperCase().trim() : null);
	existingRbdRefDocsMap.setDocRequireForType(!Util.isNullOrEmpty(rbdRefDocsMap.getDocRequireForType()) ? rbdRefDocsMap.getDocRequireForType().toUpperCase().trim() : null);

	
	existingRbdRefDocsMap.setModifiedIpAddr(request.getRemoteAddr());
	existingRbdRefDocsMap.setModifiedDate(new Date());
	existingRbdRefDocsMap.setModifiedBy("admin");
//existingRbdRefDocsMap.setModifiedByGuid("admin");

//dropdown
	existingRbdRefDocsMap.setAssessmentYear(rbdRefDocsMap.getAssessmentYearGuid());
	existingRbdRefDocsMap.setDocsSubmissionInfo(rbdRefDocsMap.getDocsSubmissionInfoGuid());
	existingRbdRefDocsMap.setRequestSubmissionType(rbdRefDocsMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(existingRbdRefDocsMap.getAssessmentYear()!=null && !existingRbdRefDocsMap.getAssessmentYear().isEmpty()){
	existingRbdRefDocsMap.setAssessmentYearMaster(new AssessmentYear(existingRbdRefDocsMap.getAssessmentYear()));
}


//DocsSubmissionInfo
if(existingRbdRefDocsMap.getDocsSubmissionInfo()!=null && !existingRbdRefDocsMap.getDocsSubmissionInfo().isEmpty()){
	existingRbdRefDocsMap.setDocsSubmissionInfoMaster(new DocsSubmissionInfo(existingRbdRefDocsMap.getDocsSubmissionInfo()));
}

//RequestSubmissionType
if(existingRbdRefDocsMap.getRequestSubmissionType()!=null && !existingRbdRefDocsMap.getRequestSubmissionType().isEmpty()){
	existingRbdRefDocsMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRbdRefDocsMap.getRequestSubmissionType()));
}

if (existingRbdRefDocsMap.getIsExtraDocInfoRequired() == null)
	existingRbdRefDocsMap.setIsExtraDocInfoRequired(false);

if (existingRbdRefDocsMap.getIsMandatory() == null)
	existingRbdRefDocsMap.setIsMandatory(false);

if (existingRbdRefDocsMap.getIsActive() == null)
	existingRbdRefDocsMap.setIsActive(false);

rbdRefDocsMap = existingRbdRefDocsMap; // Use the updated existing country object
} else {
log.error("RbdRefDocsMap not found");
resultData.setStatus(false);
resultData.setMessage("RbdRefDocsMap not found");
return resultData;
}
}

//Validation
resultData = validator.validateRbdRefDocsMap(rbdRefDocsMap);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (rbdRefDocsMap.getIsExtraDocInfoRequired() == null) rbdRefDocsMap.setIsExtraDocInfoRequired(false);
if (rbdRefDocsMap.getIsMandatory() == null) rbdRefDocsMap.setIsMandatory(false);
if (rbdRefDocsMap.getIsActive() == null) rbdRefDocsMap.setIsActive(false);
rbdRefDocsMap.setSubmittedForType(!Util.isNullOrEmpty(rbdRefDocsMap.getSubmittedForType()) ? rbdRefDocsMap.getSubmittedForType().toUpperCase().trim() : null);
rbdRefDocsMap.setDocRequireForType(!Util.isNullOrEmpty(rbdRefDocsMap.getDocRequireForType()) ? rbdRefDocsMap.getDocRequireForType().toUpperCase().trim() : null);

//rbdRefDocsMap.setZoneCode(!Util.isNullOrEmpty(geoZoneMCD.getZoneCode()) ? geoZoneMCD.getZoneCode().toUpperCase().trim() : null);
//rbdRefDocsMap.setZoneNameEn(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameEn()) ? geoZoneMCD.getZoneNameEn().toUpperCase().trim() : null);

try {
	rbdRefDocsMapRepo.save(rbdRefDocsMap);
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
@GetMapping("/getRbdRefDocsMapByGuid/{docsMapGuid}")
public ResponseEntity<RbdRefDocsMap> getRbdRefDocsMapByGuid(@PathVariable("docsMapGuid") String docsMapGuid) {
	RbdRefDocsMap rbdRefDocsMap = rbdRefDocsMapRepo.findById(docsMapGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with docsMapGuid : " + docsMapGuid));
return new ResponseEntity<>(rbdRefDocsMap, HttpStatus.OK);
}

/////////////////////////////////////RbdRefDocsMap End///////////////////////////////////

///////////////////////////////RbdFeeRelaxationList Start///////////////////////////////////////
//get all data from table
@GetMapping("/getRbdFeeRelaxationList")
public ResponseEntity<BaseResponse> getRbdFeeRelaxationList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RbdFeeRelaxationList> list = rbdFeeRelaxationListRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRbdFeeRelaxationList(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitRbdFeeRelaxationList")
public BaseResponse submitRbdFeeRelaxationList(@RequestBody RbdFeeRelaxationList rbdFeeRelaxationList, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (rbdFeeRelaxationList.getListGuid() == null || rbdFeeRelaxationList.getListGuid().isEmpty()) {

//Add new data
	rbdFeeRelaxationList.setCreatedIpAddr(request.getRemoteAddr());
	rbdFeeRelaxationList.setListGuid(UUID.randomUUID().toString());
	rbdFeeRelaxationList.setCreatedDate(new Date());
	rbdFeeRelaxationList.setModifiedIpAddr(null);
	rbdFeeRelaxationList.setCreatedBy("admin");

	rbdFeeRelaxationList.setModifiedDate(null);
	rbdFeeRelaxationList.setModifiedBy(null);


//for dropdown
	rbdFeeRelaxationList.setHospitalInfo(rbdFeeRelaxationList.getHospitalInfoGuid());
	rbdFeeRelaxationList.setRequestSubmissionType(rbdFeeRelaxationList.getRequestSubmissionTypeGuid());

//getHospitalInfo
if(rbdFeeRelaxationList.getHospitalInfo()!=null && !rbdFeeRelaxationList.getHospitalInfo().isEmpty()){
	rbdFeeRelaxationList.setHospitalInfoMaster(new HospitalInfo(rbdFeeRelaxationList.getHospitalInfo()));
}

//RequestSubmissionType
if(rbdFeeRelaxationList.getRequestSubmissionType()!=null && !rbdFeeRelaxationList.getRequestSubmissionType().isEmpty()){
	rbdFeeRelaxationList.setRequestSubmissionTypeMaster(new RequestSubmissionType(rbdFeeRelaxationList.getRequestSubmissionType()));
}



if (rbdFeeRelaxationList.getIsMandatory() == null)
	rbdFeeRelaxationList.setIsMandatory(false);

if (rbdFeeRelaxationList.getIsActive() == null)
	rbdFeeRelaxationList.setIsActive(false);

//rbdFeeRelaxationList.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//rbdFeeRelaxationList.setCreaterRemarks(userSessionParam.getUserFullName());
//rbdFeeRelaxationList.setCreaterMacId(HttpSessionHelper.getMacAddress());
//rbdFeeRelaxationList.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	RbdFeeRelaxationList existingRbdFeeRelaxationList = commonMasterService.getRbdFeeRelaxationListById(rbdFeeRelaxationList.getListGuid());

if (existingRbdFeeRelaxationList != null) {
	existingRbdFeeRelaxationList.setListCode(!Util.isNullOrEmpty(rbdFeeRelaxationList.getListCode()) ? rbdFeeRelaxationList.getListCode().toUpperCase().trim() : null);
	existingRbdFeeRelaxationList.setListNameEn(!Util.isNullOrEmpty(rbdFeeRelaxationList.getListNameEn()) ? rbdFeeRelaxationList.getListNameEn() : null);
	existingRbdFeeRelaxationList.setListDesc(!Util.isNullOrEmpty(rbdFeeRelaxationList.getListDesc()) ? rbdFeeRelaxationList.getListDesc() : null);
	existingRbdFeeRelaxationList.setTypeOfHospital(!Util.isNullOrEmpty(rbdFeeRelaxationList.getTypeOfHospital()) ? rbdFeeRelaxationList.getTypeOfHospital() : null);
	existingRbdFeeRelaxationList.setSubmittedForType(!Util.isNullOrEmpty(rbdFeeRelaxationList.getSubmittedForType()) ? rbdFeeRelaxationList.getSubmittedForType().toUpperCase().trim() : null);
	existingRbdFeeRelaxationList.setListRequireForType(!Util.isNullOrEmpty(rbdFeeRelaxationList.getListRequireForType()) ? rbdFeeRelaxationList.getListRequireForType().toUpperCase().trim() : null);
	existingRbdFeeRelaxationList.setRelaxedAmount(!Util.isNullOrZero(rbdFeeRelaxationList.getRelaxedAmount()) ? rbdFeeRelaxationList.getRelaxedAmount() : null);
	existingRbdFeeRelaxationList.setFromDate(rbdFeeRelaxationList.getFromDate());
	existingRbdFeeRelaxationList.setToDate(rbdFeeRelaxationList.getToDate());
	
	existingRbdFeeRelaxationList.setIsMandatory(rbdFeeRelaxationList.getIsMandatory() != null ? rbdFeeRelaxationList.getIsMandatory() : existingRbdFeeRelaxationList.getIsMandatory());	
	existingRbdFeeRelaxationList.setIsActive(rbdFeeRelaxationList.getIsActive() != null ? rbdFeeRelaxationList.getIsActive() : existingRbdFeeRelaxationList.getIsActive());

	existingRbdFeeRelaxationList.setModifiedIpAddr(request.getRemoteAddr());
	existingRbdFeeRelaxationList.setModifiedDate(new Date());
	existingRbdFeeRelaxationList.setModifiedBy("admin");
//existingRbdRefChargeMap.setModifiedByGuid("admin");

//dropdown
	existingRbdFeeRelaxationList.setHospitalInfo(rbdFeeRelaxationList.getHospitalInfoGuid());
	existingRbdFeeRelaxationList.setRequestSubmissionType(rbdFeeRelaxationList.getRequestSubmissionTypeGuid());

//AssessmentYear
if(existingRbdFeeRelaxationList.getHospitalInfo()!=null && !existingRbdFeeRelaxationList.getHospitalInfo().isEmpty()){
	existingRbdFeeRelaxationList.setHospitalInfoMaster(new HospitalInfo(existingRbdFeeRelaxationList.getHospitalInfo()));
}
//RequestSubmissionType
if(existingRbdFeeRelaxationList.getRequestSubmissionType()!=null && !existingRbdFeeRelaxationList.getRequestSubmissionType().isEmpty()){
	existingRbdFeeRelaxationList.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRbdFeeRelaxationList.getRequestSubmissionType()));
}



if (existingRbdFeeRelaxationList.getIsMandatory() == null)
	existingRbdFeeRelaxationList.setIsMandatory(false);

if (existingRbdFeeRelaxationList.getIsActive() == null)
	existingRbdFeeRelaxationList.setIsActive(false);

rbdFeeRelaxationList = existingRbdFeeRelaxationList; // Use the updated existing country object
} else {
log.error("RbdFeeRelaxationList not found");
resultData.setStatus(false);
resultData.setMessage("RbdFeeRelaxationList not found");
return resultData;
}
}

//Validation
resultData = validator.validateRbdFeeRelaxationList(rbdFeeRelaxationList);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (rbdFeeRelaxationList.getIsMandatory() == null) rbdFeeRelaxationList.setIsMandatory(false);
if (rbdFeeRelaxationList.getIsActive() == null) rbdFeeRelaxationList.setIsActive(false);
rbdFeeRelaxationList.setListCode(!Util.isNullOrEmpty(rbdFeeRelaxationList.getListCode()) ? rbdFeeRelaxationList.getListCode().toUpperCase().trim() : null);
rbdFeeRelaxationList.setListNameEn(!Util.isNullOrEmpty(rbdFeeRelaxationList.getListNameEn()) ? rbdFeeRelaxationList.getListNameEn() : null);
rbdFeeRelaxationList.setListDesc(!Util.isNullOrEmpty(rbdFeeRelaxationList.getListDesc()) ? rbdFeeRelaxationList.getListDesc() : null);
rbdFeeRelaxationList.setTypeOfHospital(!Util.isNullOrEmpty(rbdFeeRelaxationList.getTypeOfHospital()) ? rbdFeeRelaxationList.getTypeOfHospital() : null);
rbdFeeRelaxationList.setSubmittedForType(!Util.isNullOrEmpty(rbdFeeRelaxationList.getSubmittedForType()) ? rbdFeeRelaxationList.getSubmittedForType().toUpperCase().trim() : null);
rbdFeeRelaxationList.setListRequireForType(!Util.isNullOrEmpty(rbdFeeRelaxationList.getListRequireForType()) ? rbdFeeRelaxationList.getListRequireForType().toUpperCase().trim() : null);
rbdFeeRelaxationList.setRelaxedAmount(!Util.isNullOrZero(rbdFeeRelaxationList.getRelaxedAmount()) ? rbdFeeRelaxationList.getRelaxedAmount() : null);
rbdFeeRelaxationList.setFromDate(rbdFeeRelaxationList.getFromDate());
rbdFeeRelaxationList.setToDate(rbdFeeRelaxationList.getToDate());

try {
	rbdFeeRelaxationListRepo.save(rbdFeeRelaxationList);
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
@GetMapping("/getRbdFeeRelaxationListByGuid/{listGuid}")
public ResponseEntity<RbdFeeRelaxationList> getRbdFeeRelaxationListByGuid(@PathVariable("listGuid") String listGuid) {
	RbdFeeRelaxationList rbdFeeRelaxationList = rbdFeeRelaxationListRepo.findById(listGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with listGuid : " + listGuid));
return new ResponseEntity<>(rbdFeeRelaxationList, HttpStatus.OK);
}

///////////////////////////////RbdFeeRelaxationList End///////////////////////////////////////

}
