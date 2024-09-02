//package com.master.app.pims.validators;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.stereotype.Component;
//import com.master.app.pims.utils.Util;
//import com.master.app.pims.entities.commonmaster.ApplicationMaster;
//import com.master.app.pims.entities.commonmaster.AssessmentYear;
//import com.master.app.pims.entities.commonmaster.AssociatedChargesInfo;
//import com.master.app.pims.entities.commonmaster.CommonMasterProcessStatus;
//import com.master.app.pims.entities.commonmaster.CommonMasterReligiousPlaces;
//import com.master.app.pims.entities.commonmaster.DocsCategoryInfo;
//import com.master.app.pims.entities.commonmaster.DocsSubmissionInfo;
//import com.master.app.pims.entities.commonmaster.EducationLevel;
//import com.master.app.pims.entities.commonmaster.MstChargeDetails;
//import com.master.app.pims.entities.commonmaster.OccupationType;
//import com.master.app.pims.entities.commonmaster.RequestSubmissionType;
//import com.master.app.pims.entities.commonmaster.SubmittedRequestStage;
//import com.master.app.pims.entities.commonmaster.UnitArea;
//import com.master.app.pims.models.ResultData;
//import com.master.app.pims.repositories.ApplicationMasterRepository;
//import com.master.app.pims.repositories.AssessmentYearRepository;
//import com.master.app.pims.repositories.AssociatedChargesInfoRepository;
//import com.master.app.pims.repositories.CommonMasterProcessStatusRepository;
//import com.master.app.pims.repositories.CommonMasterReligiousPlacesRepository;
//import com.master.app.pims.repositories.DocsCategoryInfoRepository;
//import com.master.app.pims.repositories.DocsSubmissionInfoRepository;
//import com.master.app.pims.repositories.EducationLevelRepository;
//import com.master.app.pims.repositories.MstChargeDetailsRepository;
//import com.master.app.pims.repositories.OccupationTypeRepository;
//import com.master.app.pims.repositories.RequestSubmissionTypeRepository;
//import com.master.app.pims.repositories.SubmittedRequestStageRepository;
//import com.master.app.pims.repositories.UnitAreaRepository;
//
//@Component
//public class CommonMasterValidator {
//	
//	 @Autowired
//	    private MessageSource messageSource;
//	 
//	 @Autowired
//	 private ApplicationMasterRepository applicationMasterRepository;
//	 
//	 @Autowired
//	 private AssessmentYearRepository assessmentYearRepository;
//	 
//	 @Autowired
//	 private AssociatedChargesInfoRepository associatedChargesInfoRepository;
//	 
//	 @Autowired
//	 private DocsSubmissionInfoRepository docsSubmissionInfoRepository;
//	 
//	 @Autowired
//	 private RequestSubmissionTypeRepository requestSubmissionTypeRepository;
//	 
//	 @Autowired
//	 private SubmittedRequestStageRepository submittedRequestStageRepository;
//	 
//	 
//	 @Autowired
//	 private UnitAreaRepository unitAreaRepository;
//	 
//	 @Autowired
//	 private MstChargeDetailsRepository mstChargeDetailsRepository;
//	 
//	 @Autowired
//	 private OccupationTypeRepository occupationTypeRepository;
//	 
//	 @Autowired
//	 private EducationLevelRepository educationLevelRepository;
//	 
//	 @Autowired
//	 private CommonMasterReligiousPlacesRepository commonMasterReligiousPlacesRepository;
//	 
//	 @Autowired
//	 private DocsCategoryInfoRepository docsCategoryInfoRepository;
//	 
//	 @Autowired
//	 private CommonMasterProcessStatusRepository commonMasterProcessStatusRepository;
//	 
//	 
//	 
//	 
//	 
//	 
//	 
//	  
//	 
//	 
//	 //////////////////////validate Application Master ////////////////////////
//	 public ResultData validateApplicationMaster(ApplicationMaster obj) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(obj.getApplicationMasterCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.applicationMaster.code.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(obj.getApplicationMasterCode()) && applicationMasterRepository
//						.existsByApplicationMasterCodeIgnoreCaseAndApplicationMasterGuidNot(obj.getApplicationMasterCode(), obj.getApplicationMasterGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.applicationMaster.code.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(obj.getApplicationMasterName())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.applicationMaster.NameEn.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(obj.getApplicationMasterName()) && applicationMasterRepository
//						.existsByApplicationMasterNameIgnoreCaseAndApplicationMasterGuidNot(obj.getApplicationMasterName(), obj.getApplicationMasterGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.applicationMaster.NameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(obj.getApplicationMasterIp4())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.applicationMaster.IPv4.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(obj.getApplicationMasterIp4()) && applicationMasterRepository
//						.existsByApplicationMasterIp4IgnoreCaseAndApplicationMasterGuidNot(obj.getApplicationMasterIp4(), obj.getApplicationMasterGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.applicationMaster.IPv4.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(obj.getApplicationMasterUrl())) {
//					resultData.setStatus(false);
//					resultData.setMessage(
//							messageSource.getMessage("master.applicationMaster.applicationMasterUrl.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(obj.getApplicationMasterUrl()) && applicationMasterRepository
//						.existsByApplicationMasterUrlIgnoreCaseAndApplicationMasterGuidNot(obj.getApplicationMasterUrl(), obj.getApplicationMasterGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(
//							messageSource.getMessage("master.applicationMaster.applicationMasterUrl.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//
//			} catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validating Application Master: " + e.getMessage());
//			}
//			return resultData;
//		}
//	 
//	 
//	 
//	 //////////////////////validate Assesment Year/////////////////////////
//	 public ResultData validateAssessmentYear(AssessmentYear assessmentYear) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(assessmentYear.getAssessmentYearCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.assessmentYearCode.name.required", null, LocaleContextHolder.getLocale()));
//				}
//				if (!Util.isNullOrEmpty(assessmentYear.getAssessmentYearCode())
//						&& assessmentYearRepository.existsByAssessmentYearCodeIgnoreCaseAndAssessmentYearGuidNot(assessmentYear.getAssessmentYearCode(),
//								assessmentYear.getAssessmentYearGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.assessmentYearCode.name.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				
//				
//			}catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validating Assesment Year: " + e.getMessage());
//			}
//			return resultData;
//		}
//	 
//	 
//	 ////////////validate Associated ChargesInfo///////////////////
//	 
//	 //
//		public ResultData validateAssociatedChargesInfo(AssociatedChargesInfo AssociatedChargesInfo) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(AssociatedChargesInfo.getChargeCode())) {
//					resultData.setStatus(false);
//					resultData
//							.setMessage(messageSource.getMessage("master.associatedChargesInfo.chargeCode.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(AssociatedChargesInfo.getChargeCode())
//						&& associatedChargesInfoRepository.existsByChargeCodeIgnoreCaseAndAssociatedChargesInfoGuidNot(AssociatedChargesInfo.getChargeCode(),
//								AssociatedChargesInfo.getAssociatedChargesInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.associatedChargesInfo.chargeCode.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(AssociatedChargesInfo.getChargeNameEn())) {
//					resultData.setStatus(false);
//					resultData.setMessage(
//							messageSource.getMessage("master.associatedChargesInfo.chargesInfoNameEn.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(AssociatedChargesInfo.getChargeNameEn()) && associatedChargesInfoRepository
//						.existsByChargeNameEnIgnoreCaseAndAssociatedChargesInfoGuidNot(AssociatedChargesInfo.getChargeNameEn(),
//								AssociatedChargesInfo.getAssociatedChargesInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(
//							messageSource.getMessage("master.associatedChargesInfo.chargesInfoNameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(AssociatedChargesInfo.getChargeNameHi()) && associatedChargesInfoRepository
//						.existsByChargeNameHiIgnoreCaseAndAssociatedChargesInfoGuidNot(AssociatedChargesInfo.getChargeNameHi(),
//								AssociatedChargesInfo.getAssociatedChargesInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(
//							messageSource.getMessage("master.associatedChargesInfo.chargesInfoNameHi.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(AssociatedChargesInfo.getChargeNameRl()) && associatedChargesInfoRepository
//						.existsByChargeNameRlIgnoreCaseAndAssociatedChargesInfoGuidNot(AssociatedChargesInfo.getChargeNameRl(),
//								AssociatedChargesInfo.getAssociatedChargesInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(
//							messageSource.getMessage("master.associatedChargesInfo.chargesInfoNameRl.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//			} catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validating associatedChargesInfo: " + e.getMessage());
//			}
//			return resultData;
//		}
//		
//		
//		/////////////////////////////Validate DocSubmission Info////////////////////////
//		public ResultData validateDocsSubmissionInfo(DocsSubmissionInfo docsSubmissionInfo) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(docsSubmissionInfo.getDocsCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsSubmissionInfo.code.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(docsSubmissionInfo.getDocsCode())
//						&& docsSubmissionInfoRepository.existsByDocsCodeIgnoreCaseAndDocsSubmissionInfoGuidNot(docsSubmissionInfo.getDocsCode(),
//								docsSubmissionInfo.getDocsSubmissionInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsSubmissionInfo.code.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameEn())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsSubmissionInfo.NameEn.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameEn())
//						&& docsSubmissionInfoRepository.existsByDocsNameEnIgnoreCaseAndDocsSubmissionInfoGuidNot(docsSubmissionInfo.getDocsNameEn(),
//								docsSubmissionInfo.getDocsSubmissionInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsSubmissionInfo.NameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameHi())
//						&& docsSubmissionInfoRepository.existsByDocsNameHiIgnoreCaseAndDocsSubmissionInfoGuidNot(docsSubmissionInfo.getDocsNameHi(),
//								docsSubmissionInfo.getDocsSubmissionInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsSubmissionInfo.NameHi.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameRl())
//						&& docsSubmissionInfoRepository.existsByDocsNameRlIgnoreCaseAndDocsSubmissionInfoGuidNot(docsSubmissionInfo.getDocsNameRl(),
//								docsSubmissionInfo.getDocsSubmissionInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsSubmissionInfo.NameRl.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//			} catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validating DocSubmissionInfo: " + e.getMessage());
//			}
//			return resultData;
//		}
//		
//		
//		///////////////Validate Request Submission type//////////////////////
//		public ResultData validateRequestSubmissionType(RequestSubmissionType requestSubmissionType) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.requestSubmissionType.code.required", null, LocaleContextHolder.getLocale()));
//				}
//				if (!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeCode()) && requestSubmissionTypeRepository
//						.existsByRequestSubmissionTypeCodeIgnoreCaseAndRequestSubmissionTypeGuidNot(requestSubmissionType.getRequestSubmissionTypeCode(),
//								requestSubmissionType.getRequestSubmissionTypeGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.requestSubmissionType.code.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameEn()) && requestSubmissionTypeRepository
//						.existsByRequestSubmissionTypeNameEnIgnoreCaseAndRequestSubmissionTypeGuidNot(requestSubmissionType.getRequestSubmissionTypeNameEn(),
//								requestSubmissionType.getRequestSubmissionTypeGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.requestSubmissionType.nameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameHi()) && requestSubmissionTypeRepository
//						.existsByRequestSubmissionTypeNameHiIgnoreCaseAndRequestSubmissionTypeGuidNot(requestSubmissionType.getRequestSubmissionTypeNameHi(),
//								requestSubmissionType.getRequestSubmissionTypeGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.requestSubmissionType.nameHi.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameRl()) && requestSubmissionTypeRepository
//						.existsByRequestSubmissionTypeNameRlIgnoreCaseAndRequestSubmissionTypeGuidNot(requestSubmissionType.getRequestSubmissionTypeNameRl(),
//								requestSubmissionType.getRequestSubmissionTypeGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.requestSubmissionType.nameRl.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//			} catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validating Request Submission Type: " + e.getMessage());
//			}
//			return resultData;
//		}
//		
//		/////////////////////////////Validate SubmittedRequestStage/////////////////////
//		
//		public ResultData validateSubmittedRequestStage(SubmittedRequestStage submittedRequestStage) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.submittedRequestStage.code.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageCode()) && submittedRequestStageRepository
//						.existsBySubmittedRequestStageCodeIgnoreCaseAndSubmittedRequestStageGuidNot(submittedRequestStage.getSubmittedRequestStageCode(),
//								submittedRequestStage.getSubmittedRequestStageGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.submittedRequestStage.code.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameEn())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.submittedRequestStage.NameEn.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameEn()) && submittedRequestStageRepository
//						.existsBySubmittedRequestStageNameEnIgnoreCaseAndSubmittedRequestStageGuidNot(submittedRequestStage.getSubmittedRequestStageNameEn(),
//								submittedRequestStage.getSubmittedRequestStageGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.submittedRequestStage.NameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameHi()) && submittedRequestStageRepository
//						.existsBySubmittedRequestStageNameHiIgnoreCaseAndSubmittedRequestStageGuidNot(submittedRequestStage.getSubmittedRequestStageNameHi(),
//								submittedRequestStage.getSubmittedRequestStageGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.submittedRequestStage.NameHi.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameRl()) && submittedRequestStageRepository
//						.existsBySubmittedRequestStageNameRlIgnoreCaseAndSubmittedRequestStageGuidNot(submittedRequestStage.getSubmittedRequestStageNameRl(),
//								submittedRequestStage.getSubmittedRequestStageGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.submittedRequestStage.NameRl.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//			} catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validating SubmittedRequestStage: " + e.getMessage());
//			}
//			return resultData;
//		}
//		
//		
//		//////////////////////UnitArea Validation/////////////////////////
//		public ResultData validateUnitArea(UnitArea unitArea) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(unitArea.getUnitAreaCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.unitArea.unitAreaCode.required", null, LocaleContextHolder.getLocale()));
//				}
//				if (!Util.isNullOrEmpty(unitArea.getUnitAreaCode()) && unitAreaRepository
//						.existsByUnitAreaCodeIgnoreCaseAndUnitAreaGuidNot(unitArea.getUnitAreaCode(), unitArea.getUnitAreaGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.unitArea.unitAreaCode.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(unitArea.getUnitAreaNameEn()) && unitAreaRepository
//						.existsByUnitAreaNameEnIgnoreCaseAndUnitAreaGuidNot(unitArea.getUnitAreaNameEn(), unitArea.getUnitAreaGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.unitArea.UnitAreaNameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(unitArea.getUnitAreaNameHi()) && unitAreaRepository
//						.existsByUnitAreaNameHiIgnoreCaseAndUnitAreaGuidNot(unitArea.getUnitAreaNameHi(), unitArea.getUnitAreaGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.unitArea.UnitAreaNameHi.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(unitArea.getUnitAreaNameRl()) && unitAreaRepository
//						.existsByUnitAreaNameRlIgnoreCaseAndUnitAreaGuidNot(unitArea.getUnitAreaNameRl(), unitArea.getUnitAreaGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.unitArea.UnitAreaNameRl.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//			} catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validating UnitArea: " + e.getMessage());
//			}
//			return resultData;
//		}
//		
//		
//		/////////////////MstChargeDetails Validator//////////////////////
//		public ResultData validateMstChargeDetails(MstChargeDetails mstChargeDetails) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.chargeDetailsCode.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsCode()) && mstChargeDetailsRepository
//						.existsByChargeDetailsCodeIgnoreCaseAndChargeDetailsGuidNot(mstChargeDetails.getChargeDetailsCode(), mstChargeDetails.getChargeDetailsGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.chargeDetailsCode.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//
//				if (Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameEn())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.chargeDetailsNameEn.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameEn()) && mstChargeDetailsRepository
//						.existsByChargeDetailsNameEnIgnoreCaseAndChargeDetailsGuidNot(mstChargeDetails.getChargeDetailsNameEn(), mstChargeDetails.getChargeDetailsGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.chargeDetailsNameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameHi()) && mstChargeDetailsRepository
//						.existsByChargeDetailsNameHiIgnoreCaseAndChargeDetailsGuidNot(mstChargeDetails.getChargeDetailsNameHi(), mstChargeDetails.getChargeDetailsGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.chargeDetailsNameHi.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameRl()) && mstChargeDetailsRepository
//						.existsByChargeDetailsNameRlIgnoreCaseAndChargeDetailsGuidNot(mstChargeDetails.getChargeDetailsNameRl(), mstChargeDetails.getChargeDetailsGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.chargeDetailsNameRl.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//
//			} catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validating Mst Charge Details: " + e.getMessage());
//			}
//			return resultData;
//		}
//		
//		
//		////////////Occupation Type Validator
//		public ResultData validateOccupationType(OccupationType occupationType) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(occupationType.getOccupationCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.occupationCode.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(occupationType.getOccupationCode())
//						&& occupationTypeRepository.existsByOccupationCodeIgnoreCaseAndOccupationGuidNot(occupationType.getOccupationCode(), occupationType.getOccupationGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.occupationCode.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//
//				if (Util.isNullOrEmpty(occupationType.getOccupationNameEn())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.occupationNameEn.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(occupationType.getOccupationNameEn()) && occupationTypeRepository
//						.existsByOccupationNameEnIgnoreCaseAndOccupationGuidNot(occupationType.getOccupationNameEn(), occupationType.getOccupationGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.occupationNameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(occupationType.getOccupationNameHi())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.occupationNameHi.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(occupationType.getOccupationNameHi()) && occupationTypeRepository
//						.existsByOccupationNameHiIgnoreCaseAndOccupationGuidNot(occupationType.getOccupationNameHi(), occupationType.getOccupationGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.occupationNameHi.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(occupationType.getOccupationNameRl())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.occupationNameRl.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(occupationType.getOccupationNameRl()) && occupationTypeRepository
//						.existsByOccupationNameRlIgnoreCaseAndOccupationGuidNot(occupationType.getOccupationNameRl(), occupationType.getOccupationGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.occupationNameRl.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(occupationType.getOccupationDesc())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.occupationDesc.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//			} catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validating OccupationType: " + e.getMessage());
//			}
//			return resultData;
//		}
//		
//		
//		////////////////////Education Level Validator/////////////////////////
//		public ResultData validateEducationLevel(EducationLevel educationLevel) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(educationLevel.getEducationLevelCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.educationLevelCode.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(educationLevel.getEducationLevelCode()) && educationLevelRepository
//						.existsByEducationLevelCodeIgnoreCaseAndEducationLevelGuidNot(educationLevel.getEducationLevelCode(), educationLevel.getEducationLevelGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.educationLevelCode.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(educationLevel.getEducationLevelNameEn())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.educationLevelNameEn.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(educationLevel.getEducationLevelNameEn()) && educationLevelRepository
//						.existsByEducationLevelNameEnIgnoreCaseAndEducationLevelGuidNot(educationLevel.getEducationLevelNameEn(), educationLevel.getEducationLevelGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.educationLevelNameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(educationLevel.getEducationLevelNameHi()) && educationLevelRepository
//						.existsByEducationLevelNameHiIgnoreCaseAndEducationLevelGuidNot(educationLevel.getEducationLevelNameHi(), educationLevel.getEducationLevelGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.educationLevelNameHi.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(educationLevel.getEducationLevelNameRl()) && educationLevelRepository
//						.existsByEducationLevelNameRlIgnoreCaseAndEducationLevelGuidNot(educationLevel.getEducationLevelNameRl(), educationLevel.getEducationLevelGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.educationLevelNameRl.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//			}  catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validating EducationLevel: " + e.getMessage());
//			}
//			return resultData;
//		}
//		
//		////////////////////////////////////Reeligious Place Validator///////////////////////////////
//		public ResultData validateReligiousPlaces(CommonMasterReligiousPlaces commonMasterReligiousPlaces) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(commonMasterReligiousPlaces.getReligiousPlacesCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.ReligiousPlaces.code.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(commonMasterReligiousPlaces.getReligiousPlacesCode()) && commonMasterReligiousPlacesRepository
//						.existsByReligiousPlacesCodeIgnoreCaseAndReligiousPlacesGuidNot(commonMasterReligiousPlaces.getReligiousPlacesCode(), commonMasterReligiousPlaces.getReligiousPlacesGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.ReligiousPlaces.code.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(commonMasterReligiousPlaces.getReligiousPlacesNameEn())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.ReligiousPlaces.nameEn.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(commonMasterReligiousPlaces.getReligiousPlacesNameEn()) && commonMasterReligiousPlacesRepository
//						.existsByReligiousPlacesNameEnIgnoreCaseAndReligiousPlacesGuidNot(commonMasterReligiousPlaces.getReligiousPlacesNameEn(), commonMasterReligiousPlaces.getReligiousPlacesGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.ReligiousPlaces.nameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
////				if (!Util.isNullOrEmpty(commonMasterReligiousPlaces.getEducationLevelNameHi())
////						&& masterMCDValidationDAO.isExistEducationLevelNameHi(commonMasterReligiousPlaces.getEducationLevelNameHi(),
////								commonMasterReligiousPlaces.getEducationLevelGuid())) {
////					resultData.setStatus(false);
////					resultData.setMessage(messageSource.getMessage("master.educationLevelNameHi.unique", null, LocaleContextHolder.getLocale()));
////					return resultData;
////				}
////				if (!Util.isNullOrEmpty(commonMasterReligiousPlaces.getEducationLevelNameRl())
////						&& masterMCDValidationDAO.isExistEducationLevelNameRl(commonMasterReligiousPlaces.getEducationLevelNameRl(),
////								commonMasterReligiousPlaces.getEducationLevelGuid())) {
////					resultData.setStatus(false);
////					resultData.setMessage(messageSource.getMessage("master.educationLevelNameRl.unique", null, LocaleContextHolder.getLocale()));
////					return resultData;
////				}
//			}  catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validation ReligiousPlaces: " + e.getMessage());
//			}
//			return resultData;
//		}
//		
//		
//		////////////////////Docs Category Info validator///////////////////////
//		public ResultData validateDocsCategoryInfo(DocsCategoryInfo obj) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//				if (Util.isNullOrEmpty(obj.getDocsCategoryCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsCategoryCode.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(obj.getDocsCategoryCode()) && docsCategoryInfoRepository
//						.existsByDocsCategoryCodeIgnoreCaseAndDocsCategoryInfoGuidNot(obj.getDocsCategoryCode(), obj.getDocsCategoryInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsCategoryCode.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(obj.getDocsCategoryNameEn())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsCategoryNameEn.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(obj.getDocsCategoryNameEn()) && docsCategoryInfoRepository
//						.existsByDocsCategoryNameEnIgnoreCaseAndDocsCategoryInfoGuidNot(obj.getDocsCategoryNameEn(), obj.getDocsCategoryInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsCategoryNameEn.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(obj.getDocsCategoryNameHi()) && docsCategoryInfoRepository
//						.existsByDocsCategoryNameHiIgnoreCaseAndDocsCategoryInfoGuidNot(obj.getDocsCategoryNameHi(), obj.getDocsCategoryInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsCategoryNameHi.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(obj.getDocsCategoryNameRl()) && docsCategoryInfoRepository
//						.existsByDocsCategoryNameRlIgnoreCaseAndDocsCategoryInfoGuidNot(obj.getDocsCategoryNameRl(), obj.getDocsCategoryInfoGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.docsCategoryNameRl.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//			} catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validation Doc Category Info: " + e.getMessage());
//			}
//			return resultData;
//		}
//		
//		/////////////////////////// CommonMasterProcessStatus Validator//////////////////
//		public ResultData validateCommonMasterProcessStatus(CommonMasterProcessStatus commonMasterProcessStatus) {
//			ResultData resultData = new ResultData();
//			resultData.setStatus(true);
//			resultData.setMessage("Record SaveOrUpdate Successfully");
//			try {
//
//				if (Util.isNullOrEmpty(commonMasterProcessStatus.getProcessStatusCode())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.processStatus.code.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (!Util.isNullOrEmpty(commonMasterProcessStatus.getProcessStatusCode()) && commonMasterProcessStatusRepository
//						.existsByProcessStatusCodeIgnoreCaseAndProcessStatusGuidNot(commonMasterProcessStatus.getProcessStatusCode(), commonMasterProcessStatus.getProcessStatusGuid())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.processStatus.code.unique", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//				if (Util.isNullOrEmpty(commonMasterProcessStatus.getProcessStatusDesc())) {
//					resultData.setStatus(false);
//					resultData.setMessage(messageSource.getMessage("master.processStatus.desc.required", null, LocaleContextHolder.getLocale()));
//					return resultData;
//				}
//			}  catch (Exception e) {
//				 resultData.setStatus(false);
//		            resultData.setMessage("Error validation Doc Category Info: " + e.getMessage());
//			}
//			return resultData;
//		}
//
//}
