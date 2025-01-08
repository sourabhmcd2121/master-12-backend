package com.master.app.pims.controller.master.common;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgRadius;
import com.master.app.pims.entities.schemas.master.OrgUnit;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.entities.schemas.mst.CommonMasterProcessStatus;
import com.master.app.pims.entities.schemas.mst.DocsCategoryInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.EducationLevel;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.GeoZoneMCD;
import com.master.app.pims.entities.schemas.mst.MstChargeDetails;
import com.master.app.pims.entities.schemas.mst.OccupationType;
import com.master.app.pims.entities.schemas.mst.RefDocsCategoryMap;
import com.master.app.pims.entities.schemas.mst.ReligiousPlaces;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.mst.SmsEmailTemplate;
import com.master.app.pims.entities.schemas.mst.SubmittedRequestStage;
import com.master.app.pims.entities.schemas.mst.UnitArea;
import com.master.app.pims.entities.schemas.usr.RefUserDocsMap;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.ApplicationMasterRepository;
import com.master.app.pims.repositories.AssessmentYearRepository;
import com.master.app.pims.repositories.AssociatedChargesInfoRepository;
import com.master.app.pims.repositories.master.OrgRadiusRepository;
import com.master.app.pims.repositories.mst.CommonMasterProcessStatusRepo;
import com.master.app.pims.repositories.mst.DocsCategoryInfoRepository;
import com.master.app.pims.repositories.mst.DocsSubmissionInfoRepository;
import com.master.app.pims.repositories.mst.EducationLevelRepository;
import com.master.app.pims.repositories.mst.MstChargeDetailsRepository;
import com.master.app.pims.repositories.mst.OccupationTypeRepository;
import com.master.app.pims.repositories.mst.RefDocsCategoryMapRepository;
import com.master.app.pims.repositories.mst.ReligiousPlacesRepository;
import com.master.app.pims.repositories.mst.RequestSubmissionTypeRepository;
import com.master.app.pims.repositories.mst.SmsEmailTemplateRepository;
import com.master.app.pims.repositories.mst.SubmittedRequestStageRepository;
import com.master.app.pims.repositories.mst.UnitAreaRepository;
import com.master.app.pims.repositories.usr.RefUserDocsMapRepo;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3001")
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

	@Autowired
	private ReligiousPlacesRepository religiousPlacesRepository;

	@Autowired
	private DocsCategoryInfoRepository docsCategoryInfoRepository;

	@Autowired
	private CommonMasterProcessStatusRepo commonMasterProcessStatusRepo;

	@Autowired
	private SmsEmailTemplateRepository smsEmailTemplateRepository;

	@Autowired
	private OrgRadiusRepository orgRadiusRepository;
	
	@Autowired
	private RefDocsCategoryMapRepository refDocsCategoryMapRepository;
	
	 @Autowired
	 private RefUserDocsMapRepo refUserDocsMapRepo;

	////////////////////////////////////////////// Application Master
	////////////////////////////////////////////// Start////////////////////////////

	// get all data from table
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
	public BaseResponse submitOrUpdateApplicationMaster(@RequestBody ApplicationMaster appMaster,
			HttpServletRequest request) {
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

			// appMaster.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
			// appMaster.setCreatedMacAddr(HttpSessionHelper.getMacAddress());
			// appMaster.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
			// appMaster.setCreatedRemarks(userSessionParam.getUserFullName());
			// appMaster.setCreatedBy(userSessionParam.getEmpBasicGUID());
//				appMaster.setCreaterRemarks(userSessionParam.getUserFullName());
			// appMaster.setCreaterMacId(HttpSessionHelper.getMacAddress());
			// appMaster.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
		} else {
			// Update existing data
			ApplicationMaster existingAppMaster = commonMasterService
					.getApplicationMasterById(appMaster.getApplicationMasterGuid());

			if (existingAppMaster != null) {
				existingAppMaster.setApplicationMasterCode(!Util.isNullOrEmpty(appMaster.getApplicationMasterCode())
						? appMaster.getApplicationMasterCode().toUpperCase().trim()
						: null);

				existingAppMaster.setApplicationMasterName(!Util.isNullOrEmpty(appMaster.getApplicationMasterName())
						? appMaster.getApplicationMasterName().toUpperCase().trim()
						: null);
				existingAppMaster.setApplicationMasterIp4(!Util.isNullOrEmpty(appMaster.getApplicationMasterIp4())
						? appMaster.getApplicationMasterIp4().trim()
						: null);
				existingAppMaster.setApplicationMasterUrl(!Util.isNullOrEmpty(appMaster.getApplicationMasterUrl())
						? appMaster.getApplicationMasterUrl().trim()
						: null);

				existingAppMaster.setIsActive(
						appMaster.getIsActive() != null ? appMaster.getIsActive() : existingAppMaster.getIsActive());

				existingAppMaster.setModifiedIpAddr(request.getRemoteAddr());
				existingAppMaster.setModifiedDate(new Date());

				if (existingAppMaster.getIsActive() == null)
					existingAppMaster.setIsActive(false);

				// for now setting some dummy value to test
				existingAppMaster.setModifiedBy(UUID.randomUUID().toString());
				existingAppMaster.setModifiedMacAddr(UUID.randomUUID().toString());

				// existingAppMaster.setModifiedIpAddr(HttpSessionHelper.getClientIPAddress(request));
				// existingAppMaster.setModifiedMacAddr(HttpSessionHelper.getMacAddress());
				// existingAppMaster.setModifiedBy(userSessionParam.getEmpBasicGUID());
				// existingAppMaster.setModifiedDate(new Date());

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
		if (appMaster.getIsActive() == null)
			appMaster.setIsActive(false);
		appMaster.setApplicationMasterCode(!Util.isNullOrEmpty(appMaster.getApplicationMasterCode())
				? appMaster.getApplicationMasterCode().toUpperCase().trim()
				: null);
		appMaster.setApplicationMasterName(!Util.isNullOrEmpty(appMaster.getApplicationMasterName())
				? appMaster.getApplicationMasterName().toUpperCase().trim()
				: null);
		appMaster.setApplicationMasterIp4(
				!Util.isNullOrEmpty(appMaster.getApplicationMasterIp4()) ? appMaster.getApplicationMasterIp4().trim()
						: null);
		appMaster.setApplicationMasterUrl(
				!Util.isNullOrEmpty(appMaster.getApplicationMasterUrl()) ? appMaster.getApplicationMasterUrl().trim()
						: null);

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

	// get data by id
	@GetMapping("/getApplicationMasterByGuid/{applicationMasterGuid}")
	public ResponseEntity<ApplicationMaster> getApplicationMasterByGuid(
			@PathVariable("applicationMasterGuid") String applicationMasterGuid) {
		ApplicationMaster appMaster = applicationMasterRepository.findById(applicationMasterGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with applicationMasterGuid : " + applicationMasterGuid));
		return new ResponseEntity<>(appMaster, HttpStatus.OK);
	}

	////////////////////////////////////////////// Application Master
	////////////////////////////////////////////// End////////////////////////////

	////////////////////////////////////////////// Assesment Year Mst
	////////////////////////////////////////////// Start////////////////////////////

	// get all data from table
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
	public BaseResponse submitOrUpdateAssesmentYear(@RequestBody AssessmentYear assessmentYear,
			HttpServletRequest request) {
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
			// assessmentYear.setCreaterRemarks(userSessionParam.getUserFullName());
			// assessmentYear.setCreaterMacId(HttpSessionHelper.getMacAddress());
			if (assessmentYear.getIsActive() == null)
				assessmentYear.setIsActive(false);
		} else {
			// Update existing data
			AssessmentYear existingAssessmentYear = commonMasterService
					.getAssessmentYearById(assessmentYear.getAssessmentYearGuid());

			if (existingAssessmentYear != null) {
				existingAssessmentYear.setAssessmentYearCode(!Util.isNullOrEmpty(assessmentYear.getAssessmentYearCode())
						? assessmentYear.getAssessmentYearCode().toUpperCase().trim()
						: null);
				existingAssessmentYear.setAssessmentYearDesc(!Util.isNullOrEmpty(assessmentYear.getAssessmentYearDesc())
						? assessmentYear.getAssessmentYearDesc().toUpperCase().trim()
						: null);
				existingAssessmentYear.setStartYear(assessmentYear.getStartYear());
				existingAssessmentYear.setEndYear(assessmentYear.getEndYear());

				existingAssessmentYear.setStartDate(assessmentYear.getStartDate());
				existingAssessmentYear.setEndDate(assessmentYear.getEndDate());
				existingAssessmentYear.setGeneralStartDate(assessmentYear.getGeneralStartDate());
				existingAssessmentYear.setGeneralEndDate(assessmentYear.getGeneralEndDate());

				existingAssessmentYear.setIsActive(assessmentYear.getIsActive() != null ? assessmentYear.getIsActive()
						: existingAssessmentYear.getIsActive());

				existingAssessmentYear.setModifierIp(request.getRemoteAddr());
				existingAssessmentYear.setModifiedDate(new Date());

				if (existingAssessmentYear.getIsActive() == null)
					existingAssessmentYear.setIsActive(false);

				existingAssessmentYear.setModifiedByGuid(UUID.randomUUID().toString());
				existingAssessmentYear.setModifierMacId(UUID.randomUUID().toString());

				// existingAssessmentYear.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
				// existingAssessmentYear.setModifierMacId(HttpSessionHelper.getMacAddress());
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
		if (assessmentYear.getIsActive() == null)
			assessmentYear.setIsActive(false);
		assessmentYear.setAssessmentYearCode(!Util.isNullOrEmpty(assessmentYear.getAssessmentYearCode())
				? assessmentYear.getAssessmentYearCode().toUpperCase().trim()
				: null);
		assessmentYear.setAssessmentYearDesc(!Util.isNullOrEmpty(assessmentYear.getAssessmentYearDesc())
				? assessmentYear.getAssessmentYearDesc().toUpperCase().trim()
				: null);
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

	// get data by id
	@GetMapping("/getAssessmentYearByGuid/{assessmentYearGuid}")
	public ResponseEntity<AssessmentYear> getAssessmentYearByGuid(
			@PathVariable("assessmentYearGuid") String assessmentYearGuid) {
		AssessmentYear assessmentYear = assessmentYearRepository.findById(assessmentYearGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with assessmentYearGuid : " + assessmentYearGuid));
		return new ResponseEntity<>(assessmentYear, HttpStatus.OK);
	}

	////////////////////////////////////////////// Assesment Year Mst
	////////////////////////////////////////////// End////////////////////////////

//////////////////////////////////////////////AssociatedChargesInfo  Mst  Start////////////////////////////	    

	// get all data from table
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
	public BaseResponse submitOrUpdateAssociatedChargesInfo(@RequestBody AssociatedChargesInfo associatedChargesInfo,
			HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

		// Check if guid is provided (indicating an update)
		if (associatedChargesInfo.getAssociatedChargesInfoGuid() == null
				|| associatedChargesInfo.getAssociatedChargesInfoGuid().isEmpty()) {
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
			// colonyCategory.setCreaterMacId(HttpSessionHelper.getMacAddress());
			// colonyCategory.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));

		} else {
			// Update existing data
			AssociatedChargesInfo existingChargeInfo = commonMasterService
					.getAssociatedChargesInfoById(associatedChargesInfo.getAssociatedChargesInfoGuid());

			if (existingChargeInfo != null) {
				existingChargeInfo.setChargeCode(!Util.isNullOrEmpty(associatedChargesInfo.getChargeCode())
						? associatedChargesInfo.getChargeCode().toUpperCase().trim()
						: null);
				existingChargeInfo.setChargeNameEn(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameEn())
						? associatedChargesInfo.getChargeNameEn().toUpperCase().trim()
						: null);

				existingChargeInfo.setChargeNameHi(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameHi())
						? associatedChargesInfo.getChargeNameHi().toUpperCase().trim()
						: null);
				existingChargeInfo.setChargeNameRl(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameRl())
						? associatedChargesInfo.getChargeNameRl().trim()
						: null);
				existingChargeInfo.setChargeInfoDesc(!Util.isNullOrEmpty(associatedChargesInfo.getChargeInfoDesc())
						? associatedChargesInfo.getChargeInfoDesc().trim()
						: null);

				existingChargeInfo
						.setIsActive(associatedChargesInfo.getIsActive() != null ? associatedChargesInfo.getIsActive()
								: existingChargeInfo.getIsActive());

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
		if (associatedChargesInfo.getIsActive() == null)
			associatedChargesInfo.setIsActive(false);
		associatedChargesInfo.setChargeCode(!Util.isNullOrEmpty(associatedChargesInfo.getChargeCode())
				? associatedChargesInfo.getChargeCode().toUpperCase().trim()
				: null);
		associatedChargesInfo.setChargeNameEn(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameEn())
				? associatedChargesInfo.getChargeNameEn().toUpperCase().trim()
				: null);
		associatedChargesInfo.setChargeNameHi(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameHi())
				? associatedChargesInfo.getChargeNameHi().trim()
				: null);
		associatedChargesInfo.setChargeNameRl(!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameRl())
				? associatedChargesInfo.getChargeNameRl().trim()
				: null);
		associatedChargesInfo.setChargeInfoDesc(!Util.isNullOrEmpty(associatedChargesInfo.getChargeInfoDesc())
				? associatedChargesInfo.getChargeInfoDesc().trim()
				: null);

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

	// get data by id
	@GetMapping("/getAssociatedChargesInfoByGuid/{associatedChargesInfoGuid}")
	public ResponseEntity<AssociatedChargesInfo> getAssociatedChargesInfoByGuid(
			@PathVariable("associatedChargesInfoGuid") String associatedChargesInfoGuid) {
		AssociatedChargesInfo associatedChargesInfo = associatedChargesInfoRepository
				.findById(associatedChargesInfoGuid).orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with associatedChargesInfoGuid : " + associatedChargesInfoGuid));
		return new ResponseEntity<>(associatedChargesInfo, HttpStatus.OK);
	}

//////////////////////////////////////////////AssociatedChargesInfo Mst  End////////////////////////////

//////////////////////////////////////////// DocSubmissionInfo Start //////////////////////////

	// get all data from table
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
	public BaseResponse submitOrUpdateDocsSubmissionInfo(@RequestBody DocsSubmissionInfo docsSubmissionInfo,
			HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

		// Check if guid is provided (indicating an update)
		if (docsSubmissionInfo.getDocsSubmissionInfoGuid() == null
				|| docsSubmissionInfo.getDocsSubmissionInfoGuid().isEmpty()) {
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
			// docsSubmissionInfo.setCreaterMacId(HttpSessionHelper.getMacAddress());
			// docsSubmissionInfo.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));

		} else {
			// Update existing data
			DocsSubmissionInfo existingDocsInfo = commonMasterService
					.getDocsSubmissionInfoById(docsSubmissionInfo.getDocsSubmissionInfoGuid());

			if (existingDocsInfo != null) {
				existingDocsInfo.setDocsCode(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsCode())
						? docsSubmissionInfo.getDocsCode().toUpperCase().trim()
						: null);
				existingDocsInfo.setDocsNameEn(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameEn())
						? docsSubmissionInfo.getDocsNameEn().toUpperCase().trim()
						: null);

				existingDocsInfo.setDocsNameHi(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameHi())
						? docsSubmissionInfo.getDocsNameHi().toUpperCase().trim()
						: null);
				existingDocsInfo.setDocsNameRl(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameRl())
						? docsSubmissionInfo.getDocsNameRl().trim()
						: null);
				existingDocsInfo.setDocsInfoDesc(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsInfoDesc())
						? docsSubmissionInfo.getDocsInfoDesc().trim()
						: null);

				existingDocsInfo.setIsActive(docsSubmissionInfo.getIsActive() != null ? docsSubmissionInfo.getIsActive()
						: existingDocsInfo.getIsActive());

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
		if (docsSubmissionInfo.getIsActive() == null)
			docsSubmissionInfo.setIsActive(false);
		docsSubmissionInfo.setDocsCode(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsCode())
				? docsSubmissionInfo.getDocsCode().toUpperCase().trim()
				: null);
		docsSubmissionInfo.setDocsNameEn(!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameEn())
				? docsSubmissionInfo.getDocsNameEn().toUpperCase().trim()
				: null);
		docsSubmissionInfo.setDocsNameHi(
				!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameHi()) ? docsSubmissionInfo.getDocsNameHi().trim()
						: null);
		docsSubmissionInfo.setDocsNameRl(
				!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameRl()) ? docsSubmissionInfo.getDocsNameRl().trim()
						: null);
		docsSubmissionInfo.setDocsInfoDesc(
				!Util.isNullOrEmpty(docsSubmissionInfo.getDocsInfoDesc()) ? docsSubmissionInfo.getDocsInfoDesc().trim()
						: null);

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

	// get data by id
	@GetMapping("/getDocsSubmissionInfoByGuid/{docsSubmissionInfoGuid}")
	public ResponseEntity<DocsSubmissionInfo> getDocsSubmissionInfoByGuid(
			@PathVariable("docsSubmissionInfoGuid") String docsSubmissionInfoGuid) {
		DocsSubmissionInfo docsInfo = docsSubmissionInfoRepository.findById(docsSubmissionInfoGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with docsSubmissionInfoGuid : " + docsSubmissionInfoGuid));
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
	public BaseResponse submitOrUpdatRequestSubmissionType(@RequestBody RequestSubmissionType requestSubmissionType,
			HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

// Check if guid is provided (indicating an update)
		if (requestSubmissionType.getRequestSubmissionTypeGuid() == null
				|| requestSubmissionType.getRequestSubmissionTypeGuid().isEmpty()) {
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
			RequestSubmissionType existingRequestType = commonMasterService
					.getRequestSubmissionTypeById(requestSubmissionType.getRequestSubmissionTypeGuid());

			if (existingRequestType != null) {
				existingRequestType.setRequestSubmissionTypeCode(
						!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeCode())
								? requestSubmissionType.getRequestSubmissionTypeCode().toUpperCase().trim()
								: null);
				existingRequestType.setRequestSubmissionTypeNameEn(
						!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameEn())
								? requestSubmissionType.getRequestSubmissionTypeNameEn().toUpperCase().trim()
								: null);

				existingRequestType.setRequestSubmissionTypeNameHi(
						!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameHi())
								? requestSubmissionType.getRequestSubmissionTypeNameHi().toUpperCase().trim()
								: null);
				existingRequestType.setRequestSubmissionTypeNameRl(
						!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameRl())
								? requestSubmissionType.getRequestSubmissionTypeNameRl().trim()
								: null);
				existingRequestType.setRequestSubmissionTypeDesc(
						!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeDesc())
								? requestSubmissionType.getRequestSubmissionTypeDesc().trim()
								: null);

				existingRequestType
						.setIsActive(requestSubmissionType.getIsActive() != null ? requestSubmissionType.getIsActive()
								: existingRequestType.getIsActive());

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
		if (requestSubmissionType.getIsActive() == null)
			requestSubmissionType.setIsActive(false);
		requestSubmissionType
				.setRequestSubmissionTypeCode(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeCode())
						? requestSubmissionType.getRequestSubmissionTypeCode().toUpperCase().trim()
						: null);
		requestSubmissionType.setRequestSubmissionTypeNameEn(
				!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameEn())
						? requestSubmissionType.getRequestSubmissionTypeNameEn().toUpperCase().trim()
						: null);
		requestSubmissionType.setRequestSubmissionTypeNameHi(
				!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameHi())
						? requestSubmissionType.getRequestSubmissionTypeNameHi().trim()
						: null);
		requestSubmissionType.setRequestSubmissionTypeNameRl(
				!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameRl())
						? requestSubmissionType.getRequestSubmissionTypeNameRl().trim()
						: null);
		requestSubmissionType
				.setRequestSubmissionTypeDesc(!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeDesc())
						? requestSubmissionType.getRequestSubmissionTypeDesc().trim()
						: null);

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
	@GetMapping("/getRequestSubmissionTypeGuid/{requestSubmissionTypeGuid}")
	public ResponseEntity<RequestSubmissionType> getRequestSubmissionTypeGuid(
			@PathVariable("requestSubmissionTypeGuid") String requestSubmissionTypeGuid) {
		RequestSubmissionType reqType = requestSubmissionTypeRepository.findById(requestSubmissionTypeGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with requestSubmissionTypeGuid : " + requestSubmissionTypeGuid));
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
	public BaseResponse submitSubmittedRequestStage(@RequestBody SubmittedRequestStage submittedRequestStage,
			HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
		if (submittedRequestStage.getSubmittedRequestStageGuid() == null
				|| submittedRequestStage.getSubmittedRequestStageGuid().isEmpty()) {
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
			SubmittedRequestStage existingSubmittedRequest = commonMasterService
					.getSubmittedRequestStageById(submittedRequestStage.getSubmittedRequestStageGuid());

			if (existingSubmittedRequest != null) {
				existingSubmittedRequest.setSubmittedRequestStageCode(
						!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageCode())
								? submittedRequestStage.getSubmittedRequestStageCode().toUpperCase().trim()
								: null);
				existingSubmittedRequest.setSubmittedRequestStageNameEn(
						!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameEn())
								? submittedRequestStage.getSubmittedRequestStageNameEn().toUpperCase().trim()
								: null);

				existingSubmittedRequest.setSubmittedRequestStageNameHi(
						!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameHi())
								? submittedRequestStage.getSubmittedRequestStageNameHi().toUpperCase().trim()
								: null);
				existingSubmittedRequest.setSubmittedRequestStageNameRl(
						!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameRl())
								? submittedRequestStage.getSubmittedRequestStageNameRl().trim()
								: null);
				existingSubmittedRequest.setSubmittedRequestStageDesc(
						!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageDesc())
								? submittedRequestStage.getSubmittedRequestStageDesc().trim()
								: null);

				existingSubmittedRequest
						.setIsActive(submittedRequestStage.getIsActive() != null ? submittedRequestStage.getIsActive()
								: existingSubmittedRequest.getIsActive());

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
		if (submittedRequestStage.getIsActive() == null)
			submittedRequestStage.setIsActive(false);
		submittedRequestStage
				.setSubmittedRequestStageCode(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageCode())
						? submittedRequestStage.getSubmittedRequestStageCode().toUpperCase().trim()
						: null);
		submittedRequestStage.setSubmittedRequestStageNameEn(
				!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameEn())
						? submittedRequestStage.getSubmittedRequestStageNameEn().toUpperCase().trim()
						: null);
		submittedRequestStage.setSubmittedRequestStageNameHi(
				!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameHi())
						? submittedRequestStage.getSubmittedRequestStageNameHi().trim()
						: null);
		submittedRequestStage.setSubmittedRequestStageNameRl(
				!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameRl())
						? submittedRequestStage.getSubmittedRequestStageNameRl().trim()
						: null);
		submittedRequestStage
				.setSubmittedRequestStageDesc(!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageDesc())
						? submittedRequestStage.getSubmittedRequestStageDesc().trim()
						: null);

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
	public ResponseEntity<SubmittedRequestStage> getSubmittedRequestStageByGuid(
			@PathVariable("submittedRequestStageGuid") String submittedRequestStageGuid) {
		SubmittedRequestStage reqStage = submittedRequestStageRepository.findById(submittedRequestStageGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with submittedRequestStageGuid : " + submittedRequestStageGuid));
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
				existingUnitArea.setUnitAreaCode(!Util.isNullOrEmpty(unitArea.getUnitAreaCode())
						? unitArea.getUnitAreaCode().toUpperCase().trim()
						: null);
				existingUnitArea.setUnitAreaNameEn(!Util.isNullOrEmpty(unitArea.getUnitAreaNameEn())
						? unitArea.getUnitAreaNameEn().toUpperCase().trim()
						: null);

				existingUnitArea.setUnitAreaNameHi(!Util.isNullOrEmpty(unitArea.getUnitAreaNameHi())
						? unitArea.getUnitAreaNameHi().toUpperCase().trim()
						: null);
				existingUnitArea.setUnitAreaNameRl(
						!Util.isNullOrEmpty(unitArea.getUnitAreaNameRl()) ? unitArea.getUnitAreaNameRl().trim() : null);
				existingUnitArea.setUnitAreaDesc(
						!Util.isNullOrEmpty(unitArea.getUnitAreaDesc()) ? unitArea.getUnitAreaDesc().trim() : null);

				existingUnitArea.setIsActive(
						unitArea.getIsActive() != null ? unitArea.getIsActive() : existingUnitArea.getIsActive());

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
		if (unitArea.getIsActive() == null)
			unitArea.setIsActive(false);
		unitArea.setUnitAreaCode(
				!Util.isNullOrEmpty(unitArea.getUnitAreaCode()) ? unitArea.getUnitAreaCode().toUpperCase().trim()
						: null);
		unitArea.setUnitAreaNameEn(
				!Util.isNullOrEmpty(unitArea.getUnitAreaNameEn()) ? unitArea.getUnitAreaNameEn().toUpperCase().trim()
						: null);
		unitArea.setUnitAreaNameHi(
				!Util.isNullOrEmpty(unitArea.getUnitAreaNameHi()) ? unitArea.getUnitAreaNameHi().trim() : null);
		unitArea.setUnitAreaNameRl(
				!Util.isNullOrEmpty(unitArea.getUnitAreaNameRl()) ? unitArea.getUnitAreaNameRl().trim() : null);
		unitArea.setUnitAreaDesc(
				!Util.isNullOrEmpty(unitArea.getUnitAreaDesc()) ? unitArea.getUnitAreaDesc().trim() : null);

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
		UnitArea unitArea = unitAreaRepository.findById(unitAreaGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with unitAreaGuid : " + unitAreaGuid));
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
	public BaseResponse submitOrUpdateMstChargeDetails(@RequestBody MstChargeDetails mstChargeDetails,
			HttpServletRequest request) {
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
			MstChargeDetails existingChargeDetail = commonMasterService
					.getMstChargeDetailsById(mstChargeDetails.getChargeDetailsGuid());

			if (existingChargeDetail != null) {
				existingChargeDetail.setChargeDetailsCode(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsCode())
						? mstChargeDetails.getChargeDetailsCode().toUpperCase().trim()
						: null);
				existingChargeDetail
						.setChargeDetailsNameEn(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameEn())
								? mstChargeDetails.getChargeDetailsNameEn().toUpperCase().trim()
								: null);

				existingChargeDetail
						.setChargeDetailsNameHi(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameHi())
								? mstChargeDetails.getChargeDetailsNameHi().toUpperCase().trim()
								: null);
				existingChargeDetail
						.setChargeDetailsNameRl(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameRl())
								? mstChargeDetails.getChargeDetailsNameRl().trim()
								: null);
				existingChargeDetail.setChargeDetailsDesc(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsDesc())
						? mstChargeDetails.getChargeDetailsDesc().trim()
						: null);

				existingChargeDetail.setIsActive(mstChargeDetails.getIsActive() != null ? mstChargeDetails.getIsActive()
						: existingChargeDetail.getIsActive());

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
		if (mstChargeDetails.getIsActive() == null)
			mstChargeDetails.setIsActive(false);
		mstChargeDetails.setChargeDetailsCode(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsCode())
				? mstChargeDetails.getChargeDetailsCode().toUpperCase().trim()
				: null);
		mstChargeDetails.setChargeDetailsNameEn(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameEn())
				? mstChargeDetails.getChargeDetailsNameEn().toUpperCase().trim()
				: null);
		mstChargeDetails.setChargeDetailsNameHi(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameHi())
				? mstChargeDetails.getChargeDetailsNameHi().trim()
				: null);
		mstChargeDetails.setChargeDetailsNameRl(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameRl())
				? mstChargeDetails.getChargeDetailsNameRl().trim()
				: null);
		mstChargeDetails.setChargeDetailsDesc(!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsDesc())
				? mstChargeDetails.getChargeDetailsDesc().trim()
				: null);

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
	public ResponseEntity<MstChargeDetails> getMstChargeDetailsByGuid(
			@PathVariable("chargeDetailsGuid") String chargeDetailsGuid) {
		MstChargeDetails mstChargeDetails = mstChargeDetailsRepository.findById(chargeDetailsGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with chargeDetailsGuid : " + chargeDetailsGuid));
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
	public BaseResponse submitOrUpdateOccupationType(@RequestBody OccupationType occupationType,
			HttpServletRequest request) {
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
			OccupationType existingOccupationType = commonMasterService
					.getOccupationTypeById(occupationType.getOccupationGuid());

			if (existingOccupationType != null) {
				existingOccupationType.setOccupationCode(!Util.isNullOrEmpty(occupationType.getOccupationCode())
						? occupationType.getOccupationCode().toUpperCase().trim()
						: null);
				existingOccupationType.setOccupationNameEn(!Util.isNullOrEmpty(occupationType.getOccupationNameEn())
						? occupationType.getOccupationNameEn().toUpperCase().trim()
						: null);

				existingOccupationType.setOccupationNameHi(!Util.isNullOrEmpty(occupationType.getOccupationNameHi())
						? occupationType.getOccupationNameHi().toUpperCase().trim()
						: null);
				existingOccupationType.setOccupationNameRl(!Util.isNullOrEmpty(occupationType.getOccupationNameRl())
						? occupationType.getOccupationNameRl().trim()
						: null);
				existingOccupationType.setOccupationDesc(!Util.isNullOrEmpty(occupationType.getOccupationDesc())
						? occupationType.getOccupationDesc().trim()
						: null);

				existingOccupationType.setIsActive(occupationType.getIsActive() != null ? occupationType.getIsActive()
						: existingOccupationType.getIsActive());

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
		if (occupationType.getIsActive() == null)
			occupationType.setIsActive(false);
		occupationType.setOccupationCode(!Util.isNullOrEmpty(occupationType.getOccupationCode())
				? occupationType.getOccupationCode().toUpperCase().trim()
				: null);
		occupationType.setOccupationNameEn(!Util.isNullOrEmpty(occupationType.getOccupationNameEn())
				? occupationType.getOccupationNameEn().toUpperCase().trim()
				: null);
		occupationType.setOccupationNameHi(
				!Util.isNullOrEmpty(occupationType.getOccupationNameHi()) ? occupationType.getOccupationNameHi().trim()
						: null);
		occupationType.setOccupationNameRl(
				!Util.isNullOrEmpty(occupationType.getOccupationNameRl()) ? occupationType.getOccupationNameRl().trim()
						: null);
		occupationType.setOccupationDesc(
				!Util.isNullOrEmpty(occupationType.getOccupationDesc()) ? occupationType.getOccupationDesc().trim()
						: null);

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
	public ResponseEntity<OccupationType> getOccupationTypeByGuid(
			@PathVariable("occupationGuid") String occupationGuid) {
		OccupationType occupationType = occupationTypeRepository.findById(occupationGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with occupationGuid : " + occupationGuid));
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
	public BaseResponse submitOrUpdateEducationLevel(@RequestBody EducationLevel educationLevel,
			HttpServletRequest request) {
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
			EducationLevel existingEducationLevel = commonMasterService
					.getEducationLevelById(educationLevel.getEducationLevelGuid());

			if (existingEducationLevel != null) {
				existingEducationLevel.setEducationLevelCode(!Util.isNullOrEmpty(educationLevel.getEducationLevelCode())
						? educationLevel.getEducationLevelCode().toUpperCase().trim()
						: null);
				existingEducationLevel
						.setEducationLevelNameEn(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameEn())
								? educationLevel.getEducationLevelNameEn().toUpperCase().trim()
								: null);

				existingEducationLevel
						.setEducationLevelNameHi(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameHi())
								? educationLevel.getEducationLevelNameHi().toUpperCase().trim()
								: null);
				existingEducationLevel
						.setEducationLevelNameRl(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameRl())
								? educationLevel.getEducationLevelNameRl().trim()
								: null);
				existingEducationLevel.setEducationLevelDesc(!Util.isNullOrEmpty(educationLevel.getEducationLevelDesc())
						? educationLevel.getEducationLevelDesc().trim()
						: null);

				existingEducationLevel
						.setIsModified(educationLevel.getIsModified() != null ? educationLevel.getIsModified()
								: existingEducationLevel.getIsModified());

				existingEducationLevel.setIsActive(educationLevel.getIsActive() != null ? educationLevel.getIsActive()
						: existingEducationLevel.getIsActive());

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
		if (educationLevel.getIsActive() == null)
			educationLevel.setIsActive(false);
		educationLevel.setEducationLevelCode(!Util.isNullOrEmpty(educationLevel.getEducationLevelCode())
				? educationLevel.getEducationLevelCode().toUpperCase().trim()
				: null);
		educationLevel.setEducationLevelNameEn(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameEn())
				? educationLevel.getEducationLevelNameEn().toUpperCase().trim()
				: null);
		educationLevel.setEducationLevelNameHi(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameHi())
				? educationLevel.getEducationLevelNameHi().trim()
				: null);
		educationLevel.setEducationLevelNameRl(!Util.isNullOrEmpty(educationLevel.getEducationLevelNameRl())
				? educationLevel.getEducationLevelNameRl().trim()
				: null);
		educationLevel.setEducationLevelDesc(!Util.isNullOrEmpty(educationLevel.getEducationLevelDesc())
				? educationLevel.getEducationLevelDesc().trim()
				: null);

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
	public ResponseEntity<EducationLevel> getEducationLevelByGuid(
			@PathVariable("educationLevelGuid") String educationLevelGuid) {
		EducationLevel educationLevel = educationLevelRepository.findById(educationLevelGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with educationLevelGuid : " + educationLevelGuid));
		return new ResponseEntity<>(educationLevel, HttpStatus.OK);
	}

////////////////////////////////////////////EducationLevel End //////////////////////////

////////////////////////////////////////////ReligiousPlaces Start //////////////////////////

//get all data from table
	@GetMapping("/getReligiousPlacesList")
	public ResponseEntity<BaseResponse> getReligiousPlacesList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<ReligiousPlaces> list = religiousPlacesRepository.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setReligiousPlaces(list);
		return ResponseEntity.ok(response);
	}

//Create New Data And Update
	@PostMapping("/submitOrUpdateReligiousPlaces")
	public BaseResponse submitOrUpdateReligiousPlaces(@RequestBody ReligiousPlaces religiousPlaces,
			HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
		if (religiousPlaces.getReligiousPlacesGuid() == null || religiousPlaces.getReligiousPlacesGuid().isEmpty()) {
//Add new data
			religiousPlaces.setCreatedIpAddr(request.getRemoteAddr());
			religiousPlaces.setReligiousPlacesGuid(UUID.randomUUID().toString());
			religiousPlaces.setCreatedDate(new Date());
			religiousPlaces.setModifiedIpAddr(null);
			religiousPlaces.setModifiedBy(null);
			religiousPlaces.setModifiedDate(null);
			religiousPlaces.setCreatedBy(request.getRemoteAddr());

			if (religiousPlaces.getIsActive() == null)
				religiousPlaces.setIsActive(false);

//religiousPlaces.setCreatedRemarks(userSessionParam.getUserFullName());
//religiousPlaces.setCreaterMacId(HttpSessionHelper.getMacAddress());
//religiousPlaces.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//religiousPlaces.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

		} else {
//Update existing data
			ReligiousPlaces existingReligiousPlaces = commonMasterService
					.getReligiousPlacesById(religiousPlaces.getReligiousPlacesGuid());

			if (existingReligiousPlaces != null) {
				existingReligiousPlaces
						.setReligiousPlacesCode(!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesCode())
								? religiousPlaces.getReligiousPlacesCode().toUpperCase().trim()
								: null);
				existingReligiousPlaces
						.setReligiousPlacesNameEn(!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesNameEn())
								? religiousPlaces.getReligiousPlacesNameEn().toUpperCase().trim()
								: null);

				existingReligiousPlaces
						.setReligiousPlacesNameHi(!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesNameHi())
								? religiousPlaces.getReligiousPlacesNameHi().toUpperCase().trim()
								: null);
				existingReligiousPlaces
						.setReligiousPlacesNameRl(!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesNameRl())
								? religiousPlaces.getReligiousPlacesNameRl().trim()
								: null);
				existingReligiousPlaces
						.setReligiousPlacesDesc(!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesDesc())
								? religiousPlaces.getReligiousPlacesDesc().trim()
								: null);
				existingReligiousPlaces
						.setGroupReligiousPlaces(!Util.isNullOrEmpty(religiousPlaces.getGroupReligiousPlaces())
								? religiousPlaces.getGroupReligiousPlaces().trim()
								: null);

				existingReligiousPlaces
						.setIsActive(religiousPlaces.getIsActive() != null ? religiousPlaces.getIsActive()
								: existingReligiousPlaces.getIsActive());

				existingReligiousPlaces.setModifiedIpAddr(request.getRemoteAddr());
				existingReligiousPlaces.setModifiedDate(new Date());
				if (existingReligiousPlaces.getIsActive() == null)
					existingReligiousPlaces.setIsActive(false);

//for now setting some dummy value to test
				existingReligiousPlaces.setModifiedBy(UUID.randomUUID().toString());
				existingReligiousPlaces.setModifiedMacAddr(UUID.randomUUID().toString());
				religiousPlaces = existingReligiousPlaces; // Use the updated existing country object
			} else {
				log.error("Religious Places   not found");
				resultData.setStatus(false);
				resultData.setMessage("Religious Places not found");
				return resultData;
			}
		}

//Validation
		resultData = validator.validateReligiousPlaces(religiousPlaces);
		if (resultData != null && !resultData.getStatus()) {
			log.error("Validation failed: {}", resultData.getMessage());
			return resultData;
		}

//If validation passes, proceed to save or update
		if (religiousPlaces.getIsActive() == null)
			religiousPlaces.setIsActive(false);
		religiousPlaces.setReligiousPlacesCode(!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesCode())
				? religiousPlaces.getReligiousPlacesCode().toUpperCase().trim()
				: null);
		religiousPlaces.setReligiousPlacesNameEn(!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesNameEn())
				? religiousPlaces.getReligiousPlacesNameEn().toUpperCase().trim()
				: null);
		religiousPlaces.setReligiousPlacesNameHi(!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesNameHi())
				? religiousPlaces.getReligiousPlacesNameHi().trim()
				: null);
		religiousPlaces.setReligiousPlacesNameRl(!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesNameRl())
				? religiousPlaces.getReligiousPlacesNameRl().trim()
				: null);
		religiousPlaces.setReligiousPlacesDesc(!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesDesc())
				? religiousPlaces.getReligiousPlacesDesc().trim()
				: null);
		religiousPlaces.setGroupReligiousPlaces(!Util.isNullOrEmpty(religiousPlaces.getGroupReligiousPlaces())
				? religiousPlaces.getGroupReligiousPlaces().trim()
				: null);

		try {
			religiousPlacesRepository.save(religiousPlaces);
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
	@GetMapping("/getReligiousPlacesByGuid/{religiousPlacesGuid}")
	public ResponseEntity<ReligiousPlaces> getReligiousPlacesByGuid(
			@PathVariable("religiousPlacesGuid") String religiousPlacesGuid) {
		ReligiousPlaces religiousPlaces = religiousPlacesRepository.findById(religiousPlacesGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with religiousPlacesGuid : " + religiousPlacesGuid));
		return new ResponseEntity<>(religiousPlaces, HttpStatus.OK);
	}

////////////////////////////////////////////ReligiousPlaces End //////////////////////////

////////////////////////////////////////////DocsCategoryInfo Start //////////////////////////

//get all data from table
	@GetMapping("/getDocsCategoryInfoList")
	public ResponseEntity<BaseResponse> getDocsCategoryInfoList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<DocsCategoryInfo> list = docsCategoryInfoRepository.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setDocsCategoryInfo(list);
		return ResponseEntity.ok(response);
	}

//Create New Data And Update
	@PostMapping("/submitOrUpdateDocsCategoryInfo")
	public BaseResponse submitOrUpdateDocsCategoryInfo(@RequestBody DocsCategoryInfo docsCategoryInfo,
			HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
		if (docsCategoryInfo.getDocsCategoryInfoGuid() == null
				|| docsCategoryInfo.getDocsCategoryInfoGuid().isEmpty()) {
//Add new data
			docsCategoryInfo.setCreatedIpAddr(request.getRemoteAddr());
			docsCategoryInfo.setDocsCategoryInfoGuid(UUID.randomUUID().toString());
			docsCategoryInfo.setCreatedDate(new Date());
			docsCategoryInfo.setModifiedIpAddr(null);
			docsCategoryInfo.setModifiedBy(null);
			docsCategoryInfo.setModifiedDate(null);
			docsCategoryInfo.setCreatedBy(request.getRemoteAddr());

			if (docsCategoryInfo.getIsActive() == null)
				docsCategoryInfo.setIsActive(false);

//docsCategoryInfo.setCreatedRemarks(userSessionParam.getUserFullName());
//docsCategoryInfo.setCreaterMacId(HttpSessionHelper.getMacAddress());
//docsCategoryInfo.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//docsCategoryInfo.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

		} else {
//Update existing data
			DocsCategoryInfo existingDocsCategoryInfo = commonMasterService
					.getDocsCategoryInfoById(docsCategoryInfo.getDocsCategoryInfoGuid());

			if (existingDocsCategoryInfo != null) {
				existingDocsCategoryInfo.setDocsCategoryCode(!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryCode())
						? docsCategoryInfo.getDocsCategoryCode().toUpperCase().trim()
						: null);
				existingDocsCategoryInfo
						.setDocsCategoryNameEn(!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryNameEn())
								? docsCategoryInfo.getDocsCategoryNameEn().toUpperCase().trim()
								: null);

				existingDocsCategoryInfo
						.setDocsCategoryNameHi(!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryNameHi())
								? docsCategoryInfo.getDocsCategoryNameHi().toUpperCase().trim()
								: null);
				existingDocsCategoryInfo
						.setDocsCategoryNameRl(!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryNameRl())
								? docsCategoryInfo.getDocsCategoryNameRl().trim()
								: null);
				existingDocsCategoryInfo
						.setDocsCategoryInfoDesc(!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryInfoDesc())
								? docsCategoryInfo.getDocsCategoryInfoDesc().trim()
								: null);
				existingDocsCategoryInfo.setAllowedExtType(!Util.isNullOrEmpty(docsCategoryInfo.getAllowedExtType())
						? docsCategoryInfo.getAllowedExtType().trim()
						: null);

				existingDocsCategoryInfo
						.setIsActive(docsCategoryInfo.getIsActive() != null ? docsCategoryInfo.getIsActive()
								: existingDocsCategoryInfo.getIsActive());

				existingDocsCategoryInfo.setModifiedIpAddr(request.getRemoteAddr());
				existingDocsCategoryInfo.setModifiedDate(new Date());
				if (existingDocsCategoryInfo.getIsActive() == null)
					existingDocsCategoryInfo.setIsActive(false);

//for now setting some dummy value to test
				existingDocsCategoryInfo.setModifiedBy(UUID.randomUUID().toString());
				existingDocsCategoryInfo.setModifiedMacAddr(UUID.randomUUID().toString());
				docsCategoryInfo = existingDocsCategoryInfo; // Use the updated existing country object
			} else {
				log.error("DocsCategory Info   not found");
				resultData.setStatus(false);
				resultData.setMessage("DocsCategory Info not found");
				return resultData;
			}
		}

//Validation
		resultData = validator.validateDocsCategoryInfo(docsCategoryInfo);
		if (resultData != null && !resultData.getStatus()) {
			log.error("Validation failed: {}", resultData.getMessage());
			return resultData;
		}

//If validation passes, proceed to save or update
		if (docsCategoryInfo.getIsActive() == null)
			docsCategoryInfo.setIsActive(false);
		docsCategoryInfo.setDocsCategoryCode(!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryCode())
				? docsCategoryInfo.getDocsCategoryCode().toUpperCase().trim()
				: null);
		docsCategoryInfo.setDocsCategoryNameEn(!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryNameEn())
				? docsCategoryInfo.getDocsCategoryNameEn().toUpperCase().trim()
				: null);
		docsCategoryInfo.setDocsCategoryNameHi(!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryNameHi())
				? docsCategoryInfo.getDocsCategoryNameHi().trim()
				: null);
		docsCategoryInfo.setDocsCategoryNameRl(!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryNameRl())
				? docsCategoryInfo.getDocsCategoryNameRl().trim()
				: null);
		docsCategoryInfo.setDocsCategoryInfoDesc(!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryInfoDesc())
				? docsCategoryInfo.getDocsCategoryInfoDesc().trim()
				: null);
		docsCategoryInfo.setAllowedExtType(
				!Util.isNullOrEmpty(docsCategoryInfo.getAllowedExtType()) ? docsCategoryInfo.getAllowedExtType().trim()
						: null);

		try {
			docsCategoryInfoRepository.save(docsCategoryInfo);
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
	@GetMapping("/getDocsCategoryInfoByGuid/{docsCategoryInfoGuid}")
	public ResponseEntity<DocsCategoryInfo> getDocsCategoryInfoByGuid(
			@PathVariable("docsCategoryInfoGuid") String docsCategoryInfoGuid) {
		DocsCategoryInfo docsCategoryInfo = docsCategoryInfoRepository.findById(docsCategoryInfoGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with docsCategoryInfoGuid : " + docsCategoryInfoGuid));
		return new ResponseEntity<>(docsCategoryInfo, HttpStatus.OK);
	}

////////////////////////////////////////////DocsCategoryInfo End //////////////////////////

////////////////////////////////////////////CommonMasterProcessStatus Start //////////////////////////

//get all data from table
	@GetMapping("/getCommonMasterProcessStatusList")
	public ResponseEntity<BaseResponse> getCommonMasterProcessStatusList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<CommonMasterProcessStatus> list = commonMasterProcessStatusRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setCommonMasterProcessStatus(list);
		return ResponseEntity.ok(response);
	}

//Create New Data And Update
	@PostMapping("/submitOrUpdateCommonMasterProcessStatus")
	public BaseResponse submitOrUpdateCommonMasterProcessStatus(
			@RequestBody CommonMasterProcessStatus commonMasterProcessStatus, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
		if (commonMasterProcessStatus.getProcessStatusGuid() == null
				|| commonMasterProcessStatus.getProcessStatusGuid().isEmpty()) {
//Add new data
			commonMasterProcessStatus.setCreatedIpAddr(request.getRemoteAddr());
			commonMasterProcessStatus.setProcessStatusGuid(UUID.randomUUID().toString());
			commonMasterProcessStatus.setCreatedDate(new Date());
			commonMasterProcessStatus.setModifiedIpAddr(null);
			commonMasterProcessStatus.setModifiedBy(null);
			commonMasterProcessStatus.setModifiedDate(null);
			commonMasterProcessStatus.setCreatedBy(request.getRemoteAddr());

			if (commonMasterProcessStatus.getIsActive() == null)
				commonMasterProcessStatus.setIsActive(false);

//commonMasterProcessStatus.setCreatedRemarks(userSessionParam.getUserFullName());
//commonMasterProcessStatus.setCreaterMacId(HttpSessionHelper.getMacAddress());
//commonMasterProcessStatus.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//commonMasterProcessStatus.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

		} else {
//Update existing data
			CommonMasterProcessStatus existingCommonMasterProcessStatus = commonMasterService
					.getCommonMasterProcessStatusById(commonMasterProcessStatus.getProcessStatusGuid());

			if (existingCommonMasterProcessStatus != null) {
				existingCommonMasterProcessStatus
						.setProcessStatusCode(!Util.isNullOrEmpty(commonMasterProcessStatus.getProcessStatusCode())
								? commonMasterProcessStatus.getProcessStatusCode().toUpperCase().trim()
								: null);

				existingCommonMasterProcessStatus
						.setProcessStatusDesc(!Util.isNullOrEmpty(commonMasterProcessStatus.getProcessStatusDesc())
								? commonMasterProcessStatus.getProcessStatusDesc().trim()
								: null);

				existingCommonMasterProcessStatus.setIsActive(
						commonMasterProcessStatus.getIsActive() != null ? commonMasterProcessStatus.getIsActive()
								: existingCommonMasterProcessStatus.getIsActive());

				existingCommonMasterProcessStatus.setModifiedIpAddr(request.getRemoteAddr());
				existingCommonMasterProcessStatus.setModifiedDate(new Date());
				if (existingCommonMasterProcessStatus.getIsActive() == null)
					existingCommonMasterProcessStatus.setIsActive(false);

//for now setting some dummy value to test
				existingCommonMasterProcessStatus.setModifiedBy(UUID.randomUUID().toString());
				existingCommonMasterProcessStatus.setModifiedMacAddr(UUID.randomUUID().toString());
				commonMasterProcessStatus = existingCommonMasterProcessStatus; // Use the updated existing country
																				// object
			} else {
				log.error("ProcessStatus Info   not found");
				resultData.setStatus(false);
				resultData.setMessage("ProcessStatus Info not found");
				return resultData;
			}
		}

//Validation
		resultData = validator.validateCommonMasterProcessStatus(commonMasterProcessStatus);
		if (resultData != null && !resultData.getStatus()) {
			log.error("Validation failed: {}", resultData.getMessage());
			return resultData;
		}

//If validation passes, proceed to save or update
		if (commonMasterProcessStatus.getIsActive() == null)
			commonMasterProcessStatus.setIsActive(false);
		commonMasterProcessStatus
				.setProcessStatusCode(!Util.isNullOrEmpty(commonMasterProcessStatus.getProcessStatusCode())
						? commonMasterProcessStatus.getProcessStatusCode().toUpperCase().trim()
						: null);
		commonMasterProcessStatus
				.setProcessStatusDesc(!Util.isNullOrEmpty(commonMasterProcessStatus.getProcessStatusDesc())
						? commonMasterProcessStatus.getProcessStatusDesc().trim()
						: null);

		try {
			commonMasterProcessStatusRepo.save(commonMasterProcessStatus);
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
	@GetMapping("/getCommonMasterProcessStatusByGuid/{processStatusGuid}")
	public ResponseEntity<CommonMasterProcessStatus> getCommonMasterProcessStatusByGuid(
			@PathVariable("processStatusGuid") String processStatusGuid) {
		CommonMasterProcessStatus commonMasterProcessStatus = commonMasterProcessStatusRepo.findById(processStatusGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with processStatusGuid : " + processStatusGuid));
		return new ResponseEntity<>(commonMasterProcessStatus, HttpStatus.OK);
	}

////////////////////////////////////////////CommonMasterProcessStatus End //////////////////////////

////////////////////////////////////////////SmsEmailTemplate Start //////////////////////////

//get all data from table
	@GetMapping("/getSmsEmailTemplateList")
	public ResponseEntity<BaseResponse> getSmsEmailTemplateList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<SmsEmailTemplate> list = smsEmailTemplateRepository.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setSmsEmailTemplate(list);
		return ResponseEntity.ok(response);
	}

//Create New Data And Update
	@PostMapping("/submitOrUpdateSmsEmailTemplate")
	public BaseResponse submitOrUpdateSmsEmailTemplate(@RequestBody SmsEmailTemplate smsEmailTemplate,
			HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
		if (smsEmailTemplate.getSmsemailTemplateGuid() == null
				|| smsEmailTemplate.getSmsemailTemplateGuid().isEmpty()) {
//Add new data
			smsEmailTemplate.setCreatedIpAddr(request.getRemoteAddr());
			smsEmailTemplate.setSmsemailTemplateGuid(UUID.randomUUID().toString());
			smsEmailTemplate.setCreatedDate(new Date());
			smsEmailTemplate.setModifiedIpAddr(null);
			smsEmailTemplate.setModifiedBy(null);
			smsEmailTemplate.setModifiedDate(null);
			smsEmailTemplate.setCreatedBy(request.getRemoteAddr());

//	if (smsEmailTemplate.getEmailServiceBodyEn() == null)
//		smsEmailTemplate.setEmailServiceBodyEn(smsEmailTemplate.getEmailServiceBodyEn().replaceAll("\"", "'"));
//	if (smsEmailTemplate.getSmsServiceBodyEn() == null)
//		smsEmailTemplate.setSmsServiceBodyEn(smsEmailTemplate.getSmsServiceBodyEn().replaceAll("\"", "'"));
//	if (smsEmailTemplate.getGimsServiceBodyEn() == null)
//		smsEmailTemplate.setGimsServiceBodyEn(smsEmailTemplate.getGimsServiceBodyEn().replaceAll("\"", "'"));

			if (smsEmailTemplate.getIsMail() == null)
				smsEmailTemplate.setIsMail(false);
			if (smsEmailTemplate.getIsSms() == null)
				smsEmailTemplate.setIsSms(false);
			if (smsEmailTemplate.getIsGims() == null)
				smsEmailTemplate.setIsGims(false);
			if (smsEmailTemplate.getIsPreFormatted() == null)
				smsEmailTemplate.setIsPreFormatted(false);
			if (smsEmailTemplate.getIsEncoded() == null)
				smsEmailTemplate.setIsEncoded(false);
			if (smsEmailTemplate.getIsBroadcast() == null)
				smsEmailTemplate.setIsBroadcast(false);
			if (smsEmailTemplate.getIsEmailAttachment() == null)
				smsEmailTemplate.setIsEmailAttachment(false);
			if (smsEmailTemplate.getIsActive() == null)
				smsEmailTemplate.setIsActive(false);

//smsEmailTemplate.setCreatedRemarks(userSessionParam.getUserFullName());
//smsEmailTemplate.setCreaterMacId(HttpSessionHelper.getMacAddress());
//smsEmailTemplate.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//smsEmailTemplate.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

		} else {
//Update existing data
			SmsEmailTemplate existingSmsEmailTemplate = commonMasterService
					.getSmsEmailTemplateById(smsEmailTemplate.getSmsemailTemplateGuid());

			if (existingSmsEmailTemplate != null) {
				existingSmsEmailTemplate
						.setSmsemailTemplateName(!Util.isNullOrEmpty(smsEmailTemplate.getSmsemailTemplateName())
								? smsEmailTemplate.getSmsemailTemplateName().toUpperCase().trim()
								: null);
				existingSmsEmailTemplate
						.setSmsemailLinkedHeader(!Util.isNullOrEmpty(smsEmailTemplate.getSmsemailLinkedHeader())
								? smsEmailTemplate.getSmsemailLinkedHeader().toUpperCase().trim()
								: null);

				existingSmsEmailTemplate.setSmsemailEntityId(!Util.isNullOrEmpty(smsEmailTemplate.getSmsemailEntityId())
						? smsEmailTemplate.getSmsemailEntityId().toUpperCase().trim()
						: null);
				existingSmsEmailTemplate
						.setSmsemailTemplateCode(!Util.isNullOrEmpty(smsEmailTemplate.getSmsemailTemplateCode())
								? smsEmailTemplate.getSmsemailTemplateCode().trim()
								: null);
				existingSmsEmailTemplate
						.setEmailServiceBodyEn(!Util.isNullOrEmpty(smsEmailTemplate.getEmailServiceBodyEn())
								? smsEmailTemplate.getEmailServiceBodyEn().trim()
								: null);
				existingSmsEmailTemplate.setSmsServiceBodyEn(!Util.isNullOrEmpty(smsEmailTemplate.getSmsServiceBodyEn())
						? smsEmailTemplate.getSmsServiceBodyEn().trim()
						: null);
				existingSmsEmailTemplate
						.setGimsServiceBodyEn(!Util.isNullOrEmpty(smsEmailTemplate.getGimsServiceBodyEn())
								? smsEmailTemplate.getGimsServiceBodyEn().trim()
								: null);

				existingSmsEmailTemplate
						.setIsActive(smsEmailTemplate.getIsActive() != null ? smsEmailTemplate.getIsActive()
								: existingSmsEmailTemplate.getIsActive());

				existingSmsEmailTemplate.setModifiedIpAddr(request.getRemoteAddr());

//	if (smsEmailTemplate.getEmailServiceBodyEn() == null)
//		smsEmailTemplate.setEmailServiceBodyEn(smsEmailTemplate.getEmailServiceBodyEn().replaceAll("\"", "'"));
//	if (smsEmailTemplate.getSmsServiceBodyEn() == null)
//		smsEmailTemplate.setSmsServiceBodyEn(smsEmailTemplate.getSmsServiceBodyEn().replaceAll("\"", "'"));
//	if (smsEmailTemplate.getGimsServiceBodyEn() == null)
//		smsEmailTemplate.setGimsServiceBodyEn(smsEmailTemplate.getGimsServiceBodyEn().replaceAll("\"", "'"));

				if (existingSmsEmailTemplate.getIsMail() == null)
					existingSmsEmailTemplate.setIsMail(false);
				if (existingSmsEmailTemplate.getIsSms() == null)
					existingSmsEmailTemplate.setIsSms(false);
				if (existingSmsEmailTemplate.getIsGims() == null)
					existingSmsEmailTemplate.setIsGims(false);
				if (existingSmsEmailTemplate.getIsPreFormatted() == null)
					existingSmsEmailTemplate.setIsPreFormatted(false);
				if (existingSmsEmailTemplate.getIsEncoded() == null)
					existingSmsEmailTemplate.setIsEncoded(false);
				if (existingSmsEmailTemplate.getIsBroadcast() == null)
					existingSmsEmailTemplate.setIsBroadcast(false);
				if (existingSmsEmailTemplate.getIsEmailAttachment() == null)
					existingSmsEmailTemplate.setIsEmailAttachment(false);

				if (existingSmsEmailTemplate.getIsActive() == null)
					existingSmsEmailTemplate.setIsActive(false);

//for now setting some dummy value to test
				existingSmsEmailTemplate.setModifiedDate(new Date());
				existingSmsEmailTemplate.setModifiedBy(UUID.randomUUID().toString());
				existingSmsEmailTemplate.setModifiedMacAddr(UUID.randomUUID().toString());
				smsEmailTemplate = existingSmsEmailTemplate; // Use the updated existing country object
			} else {
				log.error("SmsEmail Template  not found");
				resultData.setStatus(false);
				resultData.setMessage("SmsEmail Template not found");
				return resultData;
			}
		}

//Validation
		resultData = validator.validateSmsEmailTemplate(smsEmailTemplate);
		if (resultData != null && !resultData.getStatus()) {
			log.error("Validation failed: {}", resultData.getMessage());
			return resultData;
		}

//If validation passes, proceed to save or update
		if (smsEmailTemplate.getIsActive() == null)
			smsEmailTemplate.setIsActive(false);
		smsEmailTemplate.setSmsemailTemplateName(!Util.isNullOrEmpty(smsEmailTemplate.getSmsemailTemplateName())
				? smsEmailTemplate.getSmsemailTemplateName().toUpperCase().trim()
				: null);
		smsEmailTemplate.setSmsemailLinkedHeader(!Util.isNullOrEmpty(smsEmailTemplate.getSmsemailLinkedHeader())
				? smsEmailTemplate.getSmsemailLinkedHeader().toUpperCase().trim()
				: null);
		smsEmailTemplate.setSmsemailEntityId(!Util.isNullOrEmpty(smsEmailTemplate.getSmsemailEntityId())
				? smsEmailTemplate.getSmsemailEntityId().trim()
				: null);
		smsEmailTemplate.setSmsemailTemplateCode(!Util.isNullOrEmpty(smsEmailTemplate.getSmsemailTemplateCode())
				? smsEmailTemplate.getSmsemailTemplateCode().trim()
				: null);
		smsEmailTemplate.setEmailServiceBodyEn(!Util.isNullOrEmpty(smsEmailTemplate.getEmailServiceBodyEn())
				? smsEmailTemplate.getEmailServiceBodyEn().trim()
				: null);
		smsEmailTemplate.setSmsServiceBodyEn(!Util.isNullOrEmpty(smsEmailTemplate.getSmsServiceBodyEn())
				? smsEmailTemplate.getSmsServiceBodyEn().trim()
				: null);
		smsEmailTemplate.setGimsServiceBodyEn(!Util.isNullOrEmpty(smsEmailTemplate.getGimsServiceBodyEn())
				? smsEmailTemplate.getGimsServiceBodyEn().trim()
				: null);

		try {
			smsEmailTemplateRepository.save(smsEmailTemplate);
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
	@GetMapping("/getSmsEmailTemplateByGuid/{smsemailTemplateGuid}")
	public ResponseEntity<SmsEmailTemplate> getSmsEmailTemplateByGuid(
			@PathVariable("smsemailTemplateGuid") String smsemailTemplateGuid) {
		SmsEmailTemplate smsEmailTemplate = smsEmailTemplateRepository.findById(smsemailTemplateGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with smsemailTemplateGuid : " + smsemailTemplateGuid));
		return new ResponseEntity<>(smsEmailTemplate, HttpStatus.OK);
	}

////////////////////////////////////////////SmsEmailTemplate End //////////////////////////

/////////////////////////////////////OrgRadius Start///////////////////////////////////

	// get all data from table
	@GetMapping("/getOrgRadiusList")
	public ResponseEntity<BaseResponse> getOrgRadiusList() {
		BaseResponse response = new BaseResponse();
		// Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<OrgRadius> list = orgRadiusRepository.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setOrgRadius(list);
		return ResponseEntity.ok(response);
	}
	
	//get all data  according to page and size
//    @GetMapping("/getOrgRadiusByPage")
//    public ResponseEntity<BaseResponse> getOrgRadiusByPage(@RequestParam(required = true, name = "page") int page, @RequestParam(required = true, name = "size") int size, @RequestParam(defaultValue = "createdDate", required = false) String sortBy) {
//        BaseResponse response = new BaseResponse();
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        Page<OrgRadius> radiusPage = orgRadiusRepository.findAll(pageable);
//        response.setMessage("success");
//        response.setStatus(true);
//        response.setTotalDataCount(orgRadiusRepository.findAll().size());
//        response.setOrgRadius(radiusPage.toList());
//        return ResponseEntity.ok(response);
//    }

	// Create New Data And Update
	@PostMapping("/submitOrgRadius")
	public BaseResponse submitOrgRadius(@RequestBody OrgRadius orgRadius, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

		// Check if guid is provided (indicating an update)
		if (orgRadius.getOrgRadiusGuid() == null || orgRadius.getOrgRadiusGuid().isEmpty()) {

			// Add new data
			orgRadius.setCreatedIp(request.getRemoteAddr());
			orgRadius.setOrgRadiusGuid(UUID.randomUUID().toString());
			orgRadius.setCreatedDate(new Date());
			orgRadius.setModifiedIp(null);
			orgRadius.setModifiedByGuid(null);
			orgRadius.setModifiedDate(null);
			orgRadius.setCreatedByGuid(request.getRemoteAddr());

			// for dropdown
			orgRadius.setOrgUnitName(orgRadius.getOrgUnitBasicInfoGuid());
			if (orgRadius.getOrgUnitName() != null && !orgRadius.getOrgUnitName().isEmpty()) {
				orgRadius.setOrgUnitBasicInfoMaster(new OrgUnit(orgRadius.getOrgUnitName()));
			}

//						orgRadius.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//						orgRadius.setCreaterRemarks(userSessionParam.getUserFullName());
			// orgRadius.setCreaterMacId(HttpSessionHelper.getMacAddress());
			// orgRadius.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
		} else {
			// Update existing data
			OrgRadius existingOrgRadius = commonMasterService.getOrgRadiusById(orgRadius.getOrgRadiusGuid());

			if (existingOrgRadius != null) {
				existingOrgRadius
						.setInRadius(!Util.isNullOrZero(orgRadius.getInRadius()) ? orgRadius.getInRadius() : null);
				existingOrgRadius
						.setOutRadius(!Util.isNullOrZero(orgRadius.getOutRadius()) ? orgRadius.getOutRadius() : null);

				existingOrgRadius.setIsVerified(orgRadius.getIsVerified() != null ? orgRadius.getIsVerified()
						: existingOrgRadius.getIsVerified());
				existingOrgRadius.setIsModified(orgRadius.getIsModified() != null ? orgRadius.getIsModified()
						: existingOrgRadius.getIsModified());
				existingOrgRadius.setIsAttested(orgRadius.getIsAttested() != null ? orgRadius.getIsAttested()
						: existingOrgRadius.getIsAttested());
				existingOrgRadius
						.setIsRecordActive(orgRadius.getIsRecordActive() != null ? orgRadius.getIsRecordActive()
								: existingOrgRadius.getIsRecordActive());

				existingOrgRadius.setModifiedIp(request.getRemoteAddr());
				existingOrgRadius.setModifiedDate(new Date());
				existingOrgRadius.setModifiedByGuid("admin");

				// dropdown
				existingOrgRadius.setOrgUnitName(orgRadius.getOrgUnitBasicInfoGuid());
				if (existingOrgRadius.getOrgUnitName() != null && !existingOrgRadius.getOrgUnitName().isEmpty()) {
					existingOrgRadius.setOrgUnitBasicInfoMaster(new OrgUnit(existingOrgRadius.getOrgUnitName()));
				}

				orgRadius = existingOrgRadius; // Use the updated existing country object
			} else {
				log.error("OrgRadius not found");
				resultData.setStatus(false);
				resultData.setMessage("OrgRadius not found");
				return resultData;
			}
		}

		// Validation
		resultData = validator.validateOrgRadius(orgRadius);
		if (resultData != null && !resultData.getStatus()) {
			log.error("Validation failed: {}", resultData.getMessage());
			return resultData;
		}

		// If validation passes, proceed to save or update
		if (orgRadius.getIsRecordActive() == null)
			orgRadius.setIsRecordActive(false);
		orgRadius.setInRadius(!Util.isNullOrZero(orgRadius.getInRadius()) ? orgRadius.getInRadius() : null);
		orgRadius.setOutRadius(!Util.isNullOrZero(orgRadius.getOutRadius()) ? orgRadius.getOutRadius() : null);

		try {
			orgRadiusRepository.save(orgRadius);
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

	// get data by id
	@GetMapping("/getOrgRadiusByGuid/{orgRadiusGuid}")
	public ResponseEntity<OrgRadius> getOrgRadiusByGuid(@PathVariable("orgRadiusGuid") String orgRadiusGuid) {
		OrgRadius orgRadius = orgRadiusRepository.findById(orgRadiusGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with orgRadiusGuid : " + orgRadiusGuid));
		return new ResponseEntity<>(orgRadius, HttpStatus.OK);
	}

	///////////////////////////////////// OrgRadius End///////////////////////////////////
	
/////////////////////////////////////RefDocsCategoryMap Start///////////////////////////////////
    
//get all data from table
@GetMapping("/getRefDocsCategoryMapList")
public ResponseEntity<BaseResponse> getRefDocsCategoryMapList() {
BaseResponse response = new BaseResponse();
// Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RefDocsCategoryMap> list = refDocsCategoryMapRepository.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRefDocsCategoryMap(list);
return ResponseEntity.ok(response);
}

// Create New Data And Update
@PostMapping("/submitRefDocsCategoryMap")
public BaseResponse submitRefDocsCategoryMap(@RequestBody RefDocsCategoryMap refDocsCategoryMap, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

// Check if guid is provided (indicating an update)
if (refDocsCategoryMap.getDocsCategoryMapGuid() == null || refDocsCategoryMap.getDocsCategoryMapGuid().isEmpty()) {

// Add new data
	refDocsCategoryMap.setCreatedIpAddr(request.getRemoteAddr());
	refDocsCategoryMap.setDocsCategoryMapGuid(UUID.randomUUID().toString());
	refDocsCategoryMap.setCreatedDate(new Date());
	refDocsCategoryMap.setModifiedIpAddr(null);
	refDocsCategoryMap.setCreatedBy("admin");
	//refDocsCategoryMap.setModifiedByGuid(null);
	refDocsCategoryMap.setModifiedDate(null);
	//refDocsCategoryMap.setCreatedByGuid(request.getRemoteAddr());

//for dropdown
	refDocsCategoryMap.setAssessmentYear(refDocsCategoryMap.getAssessmentYearGuid());
	refDocsCategoryMap.setDocsCategoryInfo(refDocsCategoryMap.getDocsCategoryInfoGuid());
	refDocsCategoryMap.setDocsSubmissionInfo(refDocsCategoryMap.getDocsSubmissionInfoGuid());
	refDocsCategoryMap.setRequestSubmissionType(refDocsCategoryMap.getRequestSubmissionTypeGuid());

	//AssessmentYear
if(refDocsCategoryMap.getAssessmentYear()!=null && !refDocsCategoryMap.getAssessmentYear().isEmpty()){
	refDocsCategoryMap.setAssessmentYearMaster(new AssessmentYear(refDocsCategoryMap.getAssessmentYear()));
}

//DocsCategoryInfo
if(refDocsCategoryMap.getDocsCategoryInfo()!=null && !refDocsCategoryMap.getDocsCategoryInfo().isEmpty()){
	refDocsCategoryMap.setDocsCategoryInfoMaster(new DocsCategoryInfo(refDocsCategoryMap.getDocsCategoryInfo()));
}

//DocsSubmissionInfo
if(refDocsCategoryMap.getDocsSubmissionInfo()!=null && !refDocsCategoryMap.getDocsSubmissionInfo().isEmpty()){
	refDocsCategoryMap.setDocsSubmissionInfoMaster(new DocsSubmissionInfo(refDocsCategoryMap.getDocsSubmissionInfo()));
}

//RequestSubmissionType
if(refDocsCategoryMap.getRequestSubmissionType()!=null && !refDocsCategoryMap.getRequestSubmissionType().isEmpty()){
	refDocsCategoryMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(refDocsCategoryMap.getRequestSubmissionType()));
}

if (refDocsCategoryMap.getIsExtraDocInfoRequired() == null)
	refDocsCategoryMap.setIsExtraDocInfoRequired(false);

if (refDocsCategoryMap.getIsMandatory() == null)
	refDocsCategoryMap.setIsMandatory(false);

if (refDocsCategoryMap.getIsActive() == null)
	refDocsCategoryMap.setIsActive(false);

//refDocsCategoryMap.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//refDocsCategoryMap.setCreaterRemarks(userSessionParam.getUserFullName());
//refDocsCategoryMap.setCreaterMacId(HttpSessionHelper.getMacAddress());
//refDocsCategoryMap.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
// Update existing data
	RefDocsCategoryMap existingRefDocsCategoryMap = commonMasterService.getRefDocsCategoryMapById(refDocsCategoryMap.getDocsCategoryMapGuid());

if (existingRefDocsCategoryMap != null) {
	existingRefDocsCategoryMap.setIsExtraDocInfoRequired(refDocsCategoryMap.getIsExtraDocInfoRequired() != null ? refDocsCategoryMap.getIsExtraDocInfoRequired() : existingRefDocsCategoryMap.getIsExtraDocInfoRequired());
	existingRefDocsCategoryMap.setIsMandatory(refDocsCategoryMap.getIsMandatory() != null ? refDocsCategoryMap.getIsMandatory() : existingRefDocsCategoryMap.getIsMandatory());	
	existingRefDocsCategoryMap.setIsActive(refDocsCategoryMap.getIsActive() != null ? refDocsCategoryMap.getIsActive() : existingRefDocsCategoryMap.getIsActive());
	
	existingRefDocsCategoryMap.setModifiedIpAddr(request.getRemoteAddr());
	existingRefDocsCategoryMap.setModifiedDate(new Date());
	existingRefDocsCategoryMap.setModifiedBy("admin");
	//existingRefDocsCategoryMap.setModifiedByGuid("admin");

//dropdown
	existingRefDocsCategoryMap.setAssessmentYear(refDocsCategoryMap.getAssessmentYearGuid());
	existingRefDocsCategoryMap.setDocsCategoryInfo(refDocsCategoryMap.getDocsCategoryInfoGuid());
	existingRefDocsCategoryMap.setDocsSubmissionInfo(refDocsCategoryMap.getDocsSubmissionInfoGuid());
	existingRefDocsCategoryMap.setRequestSubmissionType(refDocsCategoryMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(existingRefDocsCategoryMap.getAssessmentYear()!=null && !existingRefDocsCategoryMap.getAssessmentYear().isEmpty()){
	existingRefDocsCategoryMap.setAssessmentYearMaster(new AssessmentYear(existingRefDocsCategoryMap.getAssessmentYear()));
}

//DocsCategoryInfo
if(existingRefDocsCategoryMap.getDocsCategoryInfo()!=null && !existingRefDocsCategoryMap.getDocsCategoryInfo().isEmpty()){
	existingRefDocsCategoryMap.setDocsCategoryInfoMaster(new DocsCategoryInfo(existingRefDocsCategoryMap.getDocsCategoryInfo()));
}

//DocsSubmissionInfo
if(existingRefDocsCategoryMap.getDocsSubmissionInfo()!=null && !existingRefDocsCategoryMap.getDocsSubmissionInfo().isEmpty()){
	existingRefDocsCategoryMap.setDocsSubmissionInfoMaster(new DocsSubmissionInfo(existingRefDocsCategoryMap.getDocsSubmissionInfo()));
}

//RequestSubmissionType
if(existingRefDocsCategoryMap.getRequestSubmissionType()!=null && !existingRefDocsCategoryMap.getRequestSubmissionType().isEmpty()){
	existingRefDocsCategoryMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRefDocsCategoryMap.getRequestSubmissionType()));
}

if (existingRefDocsCategoryMap.getIsExtraDocInfoRequired() == null)
	existingRefDocsCategoryMap.setIsExtraDocInfoRequired(false);

if (existingRefDocsCategoryMap.getIsMandatory() == null)
	existingRefDocsCategoryMap.setIsMandatory(false);

if (existingRefDocsCategoryMap.getIsActive() == null)
	existingRefDocsCategoryMap.setIsActive(false);

refDocsCategoryMap = existingRefDocsCategoryMap; // Use the updated existing country object
} else {
log.error("RefDocsCategoryMap not found");
resultData.setStatus(false);
resultData.setMessage("RefDocsCategoryMap not found");
return resultData;
}
}

// Validation
resultData = validator.validateRefDocsCategoryMap(refDocsCategoryMap);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

// If validation passes, proceed to save or update
if (refDocsCategoryMap.getIsExtraDocInfoRequired() == null) refDocsCategoryMap.setIsExtraDocInfoRequired(false);
if (refDocsCategoryMap.getIsMandatory() == null) refDocsCategoryMap.setIsMandatory(false);
if (refDocsCategoryMap.getIsActive() == null) refDocsCategoryMap.setIsActive(false);
//geoZoneMCD.setZoneCode(!Util.isNullOrEmpty(geoZoneMCD.getZoneCode()) ? geoZoneMCD.getZoneCode().toUpperCase().trim() : null);
//geoZoneMCD.setZoneNameEn(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameEn()) ? geoZoneMCD.getZoneNameEn().toUpperCase().trim() : null);

try {
refDocsCategoryMapRepository.save(refDocsCategoryMap);
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
@GetMapping("/getRefDocsCategoryMapByGuid/{docsCategoryMapGuid}")
public ResponseEntity<RefDocsCategoryMap> getRefDocsCategoryMapByGuid(@PathVariable("docsCategoryMapGuid") String docsCategoryMapGuid) {
	RefDocsCategoryMap refDocsCategoryMap = refDocsCategoryMapRepository.findById(docsCategoryMapGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with docsCategoryMapGuid : " + docsCategoryMapGuid));
return new ResponseEntity<>(refDocsCategoryMap, HttpStatus.OK);
}

/////////////////////////////////////RefDocsCategoryMap End///////////////////////////////////

/////////////////////////////////////RefUserDocsMap Start///////////////////////////////////

//get all data from table
@GetMapping("/getRefUserDocsMapList")
public ResponseEntity<BaseResponse> getRefUserDocsMapList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<RefUserDocsMap> list = refUserDocsMapRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setRefUserDocsMap(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitRefUserDocsMap")
public BaseResponse submitRefUserDocsMap(@RequestBody RefUserDocsMap refUserDocsMap, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (refUserDocsMap.getUserDocsMapGuid() == null || refUserDocsMap.getUserDocsMapGuid().isEmpty()) {

//Add new data
	refUserDocsMap.setCreatedIpAddr(request.getRemoteAddr());
	refUserDocsMap.setUserDocsMapGuid(UUID.randomUUID().toString());
	refUserDocsMap.setCreatedDate(new Date());
	refUserDocsMap.setModifiedIpAddr(null);
	refUserDocsMap.setCreatedBy("admin");
//refUserDocsMap.setModifiedByGuid(null);
	refUserDocsMap.setModifiedDate(null);
	refUserDocsMap.setModifiedBy(null);
//refUserDocsMap.setCreatedByGuid(request.getRemoteAddr());

//for dropdown
	refUserDocsMap.setAssessmentYear(refUserDocsMap.getAssessmentYearGuid());
	refUserDocsMap.setDocsSubmissionInfo(refUserDocsMap.getDocsSubmissionInfoGuid());
	refUserDocsMap.setRequestSubmissionType(refUserDocsMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(refUserDocsMap.getAssessmentYear()!=null && !refUserDocsMap.getAssessmentYear().isEmpty()){
	refUserDocsMap.setAssessmentYearMaster(new AssessmentYear(refUserDocsMap.getAssessmentYear()));
}

//DocsSubmissionInfo
if(refUserDocsMap.getDocsSubmissionInfo()!=null && !refUserDocsMap.getDocsSubmissionInfo().isEmpty()){
	refUserDocsMap.setDocsSubmissionInfoMaster(new DocsSubmissionInfo(refUserDocsMap.getDocsSubmissionInfo()));
}

//RequestSubmissionType
if(refUserDocsMap.getRequestSubmissionType()!=null && !refUserDocsMap.getRequestSubmissionType().isEmpty()){
	refUserDocsMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(refUserDocsMap.getRequestSubmissionType()));
}

if (refUserDocsMap.getIsExtraDocInfoRequired() == null)
	refUserDocsMap.setIsExtraDocInfoRequired(false);

if (refUserDocsMap.getIsMandatory() == null)
	refUserDocsMap.setIsMandatory(false);

if (refUserDocsMap.getIsActive() == null)
	refUserDocsMap.setIsActive(false);

//refUserDocsMap.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//refUserDocsMap.setCreaterRemarks(userSessionParam.getUserFullName());
//refUserDocsMap.setCreaterMacId(HttpSessionHelper.getMacAddress());
//refUserDocsMap.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
} else {
//Update existing data
	RefUserDocsMap existingRefUserDocsMap = commonMasterService.getRefUserDocsMapById(refUserDocsMap.getUserDocsMapGuid());

if (existingRefUserDocsMap != null) {
	existingRefUserDocsMap.setUserType(!Util.isNullOrEmpty(refUserDocsMap.getUserType()) ? refUserDocsMap.getUserType().toUpperCase().trim() : null);
	existingRefUserDocsMap.setIsExtraDocInfoRequired(refUserDocsMap.getIsExtraDocInfoRequired() != null ? refUserDocsMap.getIsExtraDocInfoRequired() : existingRefUserDocsMap.getIsExtraDocInfoRequired());
	existingRefUserDocsMap.setIsMandatory(refUserDocsMap.getIsMandatory() != null ? refUserDocsMap.getIsMandatory() : existingRefUserDocsMap.getIsMandatory());	
	existingRefUserDocsMap.setIsActive(refUserDocsMap.getIsActive() != null ? refUserDocsMap.getIsActive() : existingRefUserDocsMap.getIsActive());

	existingRefUserDocsMap.setModifiedIpAddr(request.getRemoteAddr());
	existingRefUserDocsMap.setModifiedDate(new Date());
	existingRefUserDocsMap.setModifiedBy("admin");
//existingRefUserDocsMap.setModifiedByGuid("admin");

//dropdown
	existingRefUserDocsMap.setAssessmentYear(refUserDocsMap.getAssessmentYearGuid());
	existingRefUserDocsMap.setDocsSubmissionInfo(refUserDocsMap.getDocsSubmissionInfoGuid());
	existingRefUserDocsMap.setRequestSubmissionType(refUserDocsMap.getRequestSubmissionTypeGuid());

//AssessmentYear
if(existingRefUserDocsMap.getAssessmentYear()!=null && !existingRefUserDocsMap.getAssessmentYear().isEmpty()){
	existingRefUserDocsMap.setAssessmentYearMaster(new AssessmentYear(existingRefUserDocsMap.getAssessmentYear()));
}

//DocsSubmissionInfo
if(existingRefUserDocsMap.getDocsSubmissionInfo()!=null && !existingRefUserDocsMap.getDocsSubmissionInfo().isEmpty()){
	existingRefUserDocsMap.setDocsSubmissionInfoMaster(new DocsSubmissionInfo(existingRefUserDocsMap.getDocsSubmissionInfo()));
}

//RequestSubmissionType
if(existingRefUserDocsMap.getRequestSubmissionType()!=null && !existingRefUserDocsMap.getRequestSubmissionType().isEmpty()){
	existingRefUserDocsMap.setRequestSubmissionTypeMaster(new RequestSubmissionType(existingRefUserDocsMap.getRequestSubmissionType()));
}

if (existingRefUserDocsMap.getIsExtraDocInfoRequired() == null)
	existingRefUserDocsMap.setIsExtraDocInfoRequired(false);

if (existingRefUserDocsMap.getIsMandatory() == null)
	existingRefUserDocsMap.setIsMandatory(false);

if (existingRefUserDocsMap.getIsActive() == null)
	existingRefUserDocsMap.setIsActive(false);

refUserDocsMap = existingRefUserDocsMap; // Use the updated existing country object
} else {
log.error("RefUserDocsMap not found");
resultData.setStatus(false);
resultData.setMessage("RefUserDocsMap not found");
return resultData;
}
}

//Validation
resultData = validator.validateRefUserDocsMap(refUserDocsMap);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (refUserDocsMap.getIsExtraDocInfoRequired() == null) refUserDocsMap.setIsExtraDocInfoRequired(false);
if (refUserDocsMap.getIsMandatory() == null) refUserDocsMap.setIsMandatory(false);
if (refUserDocsMap.getIsActive() == null) refUserDocsMap.setIsActive(false);
refUserDocsMap.setUserType(!Util.isNullOrEmpty(refUserDocsMap.getUserType()) ? refUserDocsMap.getUserType().toUpperCase().trim() : null);
//refUserDocsMap.setZoneNameEn(!Util.isNullOrEmpty(geoZoneMCD.getZoneNameEn()) ? geoZoneMCD.getZoneNameEn().toUpperCase().trim() : null);

try {
	refUserDocsMapRepo.save(refUserDocsMap);
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
@GetMapping("/getRefUserDocsMapByGuid/{userDocsMapGuid}")
public ResponseEntity<RefUserDocsMap> getRefUserDocsMapByGuid(@PathVariable("userDocsMapGuid") String userDocsMapGuid) {
	RefUserDocsMap refUserDocsMap = refUserDocsMapRepo.findById(userDocsMapGuid).orElseThrow(() -> new ResourceNotFoundException("Resource not found with userDocsMapGuid : " + userDocsMapGuid));
return new ResponseEntity<>(refUserDocsMap, HttpStatus.OK);
}

/////////////////////////////////////RefUserDocsMap End///////////////////////////////////



}
