package com.master.app.pims.validators;
import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;
import com.master.app.pims.entities.schemas.intramc.IntramcRoleMenuMap;
import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgRadius;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.entities.schemas.mst.CommonMasterAppAlert;
import com.master.app.pims.entities.schemas.mst.CommonMasterIndustryArea;
import com.master.app.pims.entities.schemas.mst.CommonMasterProcessStatus;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeClassification;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeType;
import com.master.app.pims.entities.schemas.mst.DocsCategoryInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.EducationLevel;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoColonyMCD;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.GeoWardMCD;
import com.master.app.pims.entities.schemas.mst.GeoZoneMCD;
import com.master.app.pims.entities.schemas.mst.MstChargeDetails;
import com.master.app.pims.entities.schemas.mst.MstRefSla;
import com.master.app.pims.entities.schemas.mst.OccupationType;
import com.master.app.pims.entities.schemas.mst.RefDocsCategoryMap;
import com.master.app.pims.entities.schemas.mst.ReligiousPlaces;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.mst.SmsEmailTemplate;
import com.master.app.pims.entities.schemas.mst.SubmittedRequestStage;
import com.master.app.pims.entities.schemas.mst.UnitArea;
import com.master.app.pims.entities.schemas.usr.RefUserDocsMap;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.ApplicationMasterRepository;
import com.master.app.pims.repositories.AssessmentYearRepository;
import com.master.app.pims.repositories.AssociatedChargesInfoRepository;
import com.master.app.pims.repositories.intramc.IntramcMenuMasterRepo;
import com.master.app.pims.repositories.intramc.IntramcRoleMenuMapRepo;
import com.master.app.pims.repositories.master.GeoStateMasterRepository;
import com.master.app.pims.repositories.master.OrgPrimaryRepository;
import com.master.app.pims.repositories.master.OrgRadiusRepository;
import com.master.app.pims.repositories.master.OrgWrapperRepository;
import com.master.app.pims.repositories.mst.CommonMasterAppAlertRepo;
import com.master.app.pims.repositories.mst.CommonMasterIndustryAreaRepo;
import com.master.app.pims.repositories.mst.CommonMasterProcessStatusRepo;
import com.master.app.pims.repositories.mst.CommonMasterTradeClassificationRepo;
import com.master.app.pims.repositories.mst.CommonMasterTradeTypeRepo;
import com.master.app.pims.repositories.mst.DocsCategoryInfoRepository;
import com.master.app.pims.repositories.mst.DocsSubmissionInfoRepository;
import com.master.app.pims.repositories.mst.EducationLevelRepository;
import com.master.app.pims.repositories.mst.GeoColonyCategoryRepository;
import com.master.app.pims.repositories.mst.GeoColonyMCDRepo;
import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
import com.master.app.pims.repositories.mst.GeoWardMCDRepo;
import com.master.app.pims.repositories.mst.GeoZoneMCDRepository;
import com.master.app.pims.repositories.mst.MstChargeDetailsRepository;
import com.master.app.pims.repositories.mst.MstRefSlaRepo;
import com.master.app.pims.repositories.mst.OccupationTypeRepository;
import com.master.app.pims.repositories.mst.RefDocsCategoryMapRepository;
import com.master.app.pims.repositories.mst.ReligiousPlacesRepository;
import com.master.app.pims.repositories.mst.RequestSubmissionTypeRepository;
import com.master.app.pims.repositories.mst.SmsEmailTemplateRepository;
import com.master.app.pims.repositories.mst.SubmittedRequestStageRepository;
import com.master.app.pims.repositories.mst.UnitAreaRepository;
import com.master.app.pims.repositories.usr.RefUserDocsMapRepo;
import com.master.app.pims.utils.PropertyReader;
import com.master.app.pims.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class CommonMasterValidator implements Validator {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private GeoCountryMstRepository geoCountryMstRepository;

    @Autowired
    private GeoStateMasterRepository geoStateMasterRepository;
    
    
    @Autowired
    private GeoColonyCategoryRepository geoColonyCategoryRepository;
    
    @Autowired
    private ApplicationMasterRepository applicationMasterRepository;
    
    @Autowired
    private AssessmentYearRepository assessmentYearRepository;
    
    @Autowired
    private CommonMasterAppAlertRepo commonMasterAppAlertRepo;
    
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
    private OrgPrimaryRepository orgPrimaryRepository;
    
    @Autowired
    private OrgWrapperRepository orgWrapperRepository;
    
    @Autowired
    private GeoZoneMCDRepository geoZoneMCDRepository;
    
    @Autowired
	private OrgRadiusRepository orgRadiusRepository;
    
    @Autowired
   	private RefDocsCategoryMapRepository refDocsCategoryMapRepository;
    
    @Autowired
   	private RefUserDocsMapRepo refUserDocsMapRepo;
    
    @Autowired
    private GeoWardMCDRepo geoWardMCDRepo;
   
    @Autowired
   	private GeoColonyMCDRepo geoColonyMCDRepo;
    
    @Autowired
   	private IntramcMenuMasterRepo intramcMenuMasterRepo;
    
    @Autowired
   	private IntramcRoleMenuMapRepo intramcRoleMenuMapRepo;
    
    @Autowired
   	private CommonMasterTradeClassificationRepo tradeClassificationRepo;
    
    @Autowired
	private CommonMasterTradeTypeRepo commonMasterTradeTypeRepo;
    
    @Autowired
   	private CommonMasterIndustryAreaRepo industryAreaRepo;
    
    @Autowired
   	private MstRefSlaRepo refSlaRepo;
    
    //mst country validation
    @Override
    public BaseResponse validateMstCountry(GeoCountryMst country) {
        BaseResponse resultData = new BaseResponse();
        resultData.setStatus(true);
        resultData.setMessage("Record SaveOrUpdate Successfully");
        try {
            if (Util.isNullOrEmpty(country.getCountryCode())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.GeoCountryMst.code.required"));
                return resultData;
            }

            if (!Util.isNullOrEmpty(country.getCountryCode()) && geoCountryMstRepository
                    .isExistGeoCountryMstCode(country.getCountryCode(), country.getCountryMstGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.GeoCountryMst.code.unique"));
                return resultData;
            }
            if (Util.isNullOrEmpty(country.getCountryMobileCode())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.GeoCountryCode.mobileCode.required"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(country.getCountryNameEn()) && geoCountryMstRepository
                    .isExistGeoCountryMstMobileCode(country.getCountryNameEn(), country.getCountryMstGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.GeoCountryMst.mobileCode.unique"));
                return resultData;
            }

            if (Util.isNullOrEmpty(country.getCountryNameEn())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.GeoCountryCode.nameEn.required"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(country.getCountryNameEn()) && geoCountryMstRepository
                    .isExistGeoCountryMstNameEn(country.getCountryNameEn(), country.getCountryMstGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.GeoCountryMst.nameEn.unique"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(country.getCountryNameHi()) && geoCountryMstRepository
                    .isExistGeoCountryMstNameHi(country.getCountryNameHi(), country.getCountryMstGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.GeoCountryMst.nameHi.unique"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(country.getCountryNameRl()) && geoCountryMstRepository
                    .isExistGeoCountryMstNameRl(country.getCountryNameRl(), country.getCountryMstGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.GeoCountryMst.nameRl.unique"));
                return resultData;
            }
        } catch (Exception e) {
            resultData.setStatus(false);
            resultData.setMessage("Error validating country: " + e.getMessage());
        }
        return resultData;
    }

    //master state validation
    @Override
    public BaseResponse validateMasterState(GeoStateMaster state) {
        BaseResponse resultData = new BaseResponse();
        resultData.setStatus(true);
        resultData.setMessage("Record SaveOrUpdate Successfully");
        try {
            if (Util.isNullOrEmpty(state.getStateCode())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.state.code.required"));
            }
            if (!Util.isNullOrEmpty(state.getStateCode()) && geoStateMasterRepository.isExistStateCode(state.getStateCode(), state.getStateMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.state.code.unique"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(state.getStateNameEn()) && geoStateMasterRepository.isExistStateNameEn(state.getStateNameEn(), state.getStateMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.state.nameEn.unique"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(state.getStateNameHi()) && geoStateMasterRepository.isExistStateNameHi(state.getStateNameHi(), state.getStateMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.state.nameHi.unique"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(state.getStateNameRl()) && geoStateMasterRepository.isExistStateNameRl(state.getStateNameRl(), state.getStateMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.state.nameRl.unique"));
                return resultData;
            }
        } catch (Exception e) {
            resultData.setStatus(false);
            resultData.setMessage("Error validating state: " + e.getMessage());
        }
        return resultData;
    }

    
    //colony category validation
	@Override
	public BaseResponse validateColonyCategory(GeoColonyCategory colonyCategory) {
		 BaseResponse resultData = new BaseResponse();
	        resultData.setStatus(true);
	        resultData.setMessage("Record SaveOrUpdate Successfully");
	        try {
				if (Util.isNullOrEmpty(colonyCategory.getColonyCategoryCode())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.geoColonyCategory.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(colonyCategory.getColonyCategoryCode())
						&& geoColonyCategoryRepository.isExistGeoColonyCategoryCode(colonyCategory.getColonyCategoryCode(),
								colonyCategory.getColonyCategoryGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.geoColonyCategory.unique"));
					return resultData;
				}
				if (Util.isNullOrEmpty(colonyCategory.getColonyCategoryNameEn())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.geoColonyCategory.geoColonyCategoryNameEn.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(colonyCategory.getColonyCategoryNameEn())
						&& geoColonyCategoryRepository.isExistGeoColonyCategoryNameEn(
								colonyCategory.getColonyCategoryNameEn(), colonyCategory.getColonyCategoryGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(
							PropertyReader.getFormMessage("master.geoColonyCategory.geoColonyCategoryNameEn.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(colonyCategory.getColonyCategoryNameHi())
						&& geoColonyCategoryRepository.isExistGeoColonyCategoryNameHi(
								colonyCategory.getColonyCategoryNameHi(), colonyCategory.getColonyCategoryGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(
							PropertyReader.getFormMessage("master.geoColonyCategory.geoColonyCategoryNameHi.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(colonyCategory.getColonyCategoryNameRl())
						&& geoColonyCategoryRepository.isExistGeoColonyCategoryNameRl(
								colonyCategory.getColonyCategoryNameRl(), colonyCategory.getColonyCategoryGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(
							PropertyReader.getFormMessage("master.geoColonyCategory.geoColonyCategoryNameRl.unique"));
					return resultData;
				}
			} catch (Exception e) {
				 resultData.setStatus(false);
	            resultData.setMessage("Error validating country: " + e.getMessage());
			}
			return resultData;
	}

	@Override
	public BaseResponse validateApplicationMaster(ApplicationMaster appMaster) {
		 BaseResponse resultData = new BaseResponse();
	        resultData.setStatus(true);
	        resultData.setMessage("Record SaveOrUpdate Successfully");
	        try {
	        	if (Util.isNullOrEmpty(appMaster.getApplicationMasterCode())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.applicationMaster.code.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(appMaster.getApplicationMasterCode()) && applicationMasterRepository
						.isExistApplicationMasterCode(appMaster.getApplicationMasterCode(), appMaster.getApplicationMasterGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.applicationMaster.code.unique"));
					return resultData;
				}
				if (Util.isNullOrEmpty(appMaster.getApplicationMasterName())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.applicationMaster.NameEn.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(appMaster.getApplicationMasterName()) && applicationMasterRepository
						.isExistApplicationMasterName(appMaster.getApplicationMasterName(), appMaster.getApplicationMasterGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.applicationMaster.NameEn.unique"));
					return resultData;
				}
				if (Util.isNullOrEmpty(appMaster.getApplicationMasterIp4())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.applicationMaster.IPv4.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(appMaster.getApplicationMasterIp4()) && applicationMasterRepository
						.isExistApplicationMasterIp4(appMaster.getApplicationMasterIp4(), appMaster.getApplicationMasterGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.applicationMaster.IPv4.unique"));
					return resultData;
				}
				if (Util.isNullOrEmpty(appMaster.getApplicationMasterUrl())) {
					resultData.setStatus(false);
					resultData.setMessage(
							PropertyReader.getFormMessage("master.applicationMaster.applicationMasterUrl.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(appMaster.getApplicationMasterUrl()) && applicationMasterRepository
						.isExistApplicationMasterUrl(appMaster.getApplicationMasterUrl(), appMaster.getApplicationMasterGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(
							PropertyReader.getFormMessage("master.applicationMaster.applicationMasterUrl.unique"));
					return resultData;
				}
				
			
			} catch (Exception e) {
				 resultData.setStatus(false);
	            resultData.setMessage("Error validating applicationMaster: " + e.getMessage());
			}
			return resultData;
	}

	@Override
	public BaseResponse validateAssessmentYear(AssessmentYear assessmentYear) {
		 BaseResponse resultData = new BaseResponse();
	        resultData.setStatus(true);
	        resultData.setMessage("Record SaveOrUpdate Successfully");
	        try {
	        	if (Util.isNullOrEmpty(assessmentYear.getAssessmentYearCode())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.assessmentYearCode.name.required"));
				}
				if (!Util.isNullOrEmpty(assessmentYear.getAssessmentYearCode())
						&& assessmentYearRepository.isExistAssessmentCode(assessmentYear.getAssessmentYearCode(),
								assessmentYear.getAssessmentYearGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.assessmentYearCode.name.unique"));
					return resultData;
				}
				
				if (assessmentYear.getStartDate() == null || assessmentYear.getEndDate() == null) {
				    resultData.setStatus(false);
				    if (assessmentYear.getStartDate() == null) {
				        resultData.setMessage(PropertyReader.getFormMessage("master.startDate.required"));
				    } else {
				        resultData.setMessage(PropertyReader.getFormMessage("master.endDate.required"));
				    }
				    return resultData;
				}

				// Check if startDate is after endDate
				if (assessmentYear.getStartDate().compareTo(assessmentYear.getEndDate()) > 0) { // startDate is after endDate
				    resultData.setStatus(false);
				    resultData.setMessage(PropertyReader.getFormMessage("master.startDateEndDate.invalid"));
				    return resultData;
				}
				
				// Assuming you have startYear and endYear fields in your assessmentYear or another educationLevelect
			

				if (assessmentYear.getStartYear() == null || assessmentYear.getEndYear() == null) {
				    resultData.setStatus(false);
				    if (assessmentYear.getStartYear() == null) {
				        resultData.setMessage(PropertyReader.getFormMessage("master.startYear.required"));
				    } else {
				        resultData.setMessage(PropertyReader.getFormMessage("master.endYear.required"));
				    }
				    return resultData;
				}

				// Check if startYear is greater than endYear
				if (assessmentYear.getStartYear() > assessmentYear.getEndYear()) { // startYear is greater than endYear
				    resultData.setStatus(false);
				    resultData.setMessage(PropertyReader.getFormMessage("master.startYearEndYear.invalid"));
				    return resultData;
				}
				
				if (Util.isNullOrEmpty(assessmentYear.getAssessmentYearDesc())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.assessmentYearDesc.required"));
				}

	        	
				
			
			} catch (Exception e) {
				 resultData.setStatus(false);
	            resultData.setMessage("Error validating assessmentYear: " + e.getMessage());
			}
			return resultData;
	}

	@Override
	public BaseResponse validateAssociatedChargesInfo(AssociatedChargesInfo associatedChargesInfo) {
	    BaseResponse resultData = new BaseResponse();
        resultData.setStatus(true);
        resultData.setMessage("Record SaveOrUpdate Successfully");
        try {
        	if (Util.isNullOrEmpty(associatedChargesInfo.getChargeCode())) {
				resultData.setStatus(false);
				resultData
						.setMessage(PropertyReader.getFormMessage("master.associatedChargesInfo.chargeCode.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(associatedChargesInfo.getChargeCode())
					&& associatedChargesInfoRepository.isExistAssociatedChargesInfoCode(associatedChargesInfo.getChargeCode(),
							associatedChargesInfo.getAssociatedChargesInfoGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.associatedChargesInfo.chargeCode.unique"));
				return resultData;
			}
			if (Util.isNullOrEmpty(associatedChargesInfo.getChargeNameEn())) {
				resultData.setStatus(false);
				resultData.setMessage(
						PropertyReader.getFormMessage("master.associatedChargesInfo.chargesInfoNameEn.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameEn()) && associatedChargesInfoRepository
					.isExistAssociatedChargesInfoNameEn(associatedChargesInfo.getChargeNameEn(),
							associatedChargesInfo.getAssociatedChargesInfoGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(
						PropertyReader.getFormMessage("master.associatedChargesInfo.chargesInfoNameEn.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameHi()) && associatedChargesInfoRepository
					.isExistAssociatedChargesInfoNameHi(associatedChargesInfo.getChargeNameHi(),
							associatedChargesInfo.getAssociatedChargesInfoGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(
						PropertyReader.getFormMessage("master.associatedChargesInfo.chargesInfoNameHi.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(associatedChargesInfo.getChargeNameRl()) && associatedChargesInfoRepository
					.isExistAssociatedChargesInfoNameRl(associatedChargesInfo.getChargeNameRl(),
							associatedChargesInfo.getAssociatedChargesInfoGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(
						PropertyReader.getFormMessage("master.associatedChargesInfo.chargesInfoNameRl.unique"));
				return resultData;
			}
           
        } catch (Exception e) {
            resultData.setStatus(false);
            resultData.setMessage("Error validating  associate chargeInfo: " + e.getMessage());
        }
        return resultData;
	}

	@Override
	public BaseResponse validateDocsSubmissionInfo(DocsSubmissionInfo docsSubmissionInfo) {
		  BaseResponse resultData = new BaseResponse();
	        resultData.setStatus(true);
	        resultData.setMessage("Record SaveOrUpdate Successfully");
	        try {
				if (Util.isNullOrEmpty(docsSubmissionInfo.getDocsCode())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.docsSubmissionInfo.code.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(docsSubmissionInfo.getDocsCode())
						&& docsSubmissionInfoRepository.isExistDocsSubmissionInfoCode(docsSubmissionInfo.getDocsCode(),
								docsSubmissionInfo.getDocsSubmissionInfoGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.docsSubmissionInfo.code.unique"));
					return resultData;
				}
				if (Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameEn())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.docsSubmissionInfo.NameEn.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameEn())
						&& docsSubmissionInfoRepository.isExistDocsSubmissionInfoNameEn(docsSubmissionInfo.getDocsNameEn(),
								docsSubmissionInfo.getDocsSubmissionInfoGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.docsSubmissionInfo.NameEn.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameHi())
						&& docsSubmissionInfoRepository.isExistDocsSubmissionInfoNameHi(docsSubmissionInfo.getDocsNameHi(),
								docsSubmissionInfo.getDocsSubmissionInfoGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.docsSubmissionInfo.NameHi.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(docsSubmissionInfo.getDocsNameRl())
						&& docsSubmissionInfoRepository.isExistDocsSubmissionInfoNameRl(docsSubmissionInfo.getDocsNameRl(),
								docsSubmissionInfo.getDocsSubmissionInfoGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.docsSubmissionInfo.NameRl.unique"));
					return resultData;
				}
			} catch (Exception e) {
				  resultData.setStatus(false);
		            resultData.setMessage("Error validating docs submission info: " + e.getMessage());
			}
	        return resultData;
	}

	
	@Override
	public BaseResponse validateRequestSubmissionType(RequestSubmissionType requestSubmissionType) {
		  BaseResponse resultData = new BaseResponse();
	        resultData.setStatus(true);
	        resultData.setMessage("Record SaveOrUpdate Successfully");
	        try {
				if (Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeCode())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.requestSubmissionType.code.required"));
				}
				if (!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeCode()) && requestSubmissionTypeRepository
						.isExistRequestSubmissionTypeCode(requestSubmissionType.getRequestSubmissionTypeCode(),
								requestSubmissionType.getRequestSubmissionTypeGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.requestSubmissionType.code.unique"));
					return resultData;
				}
				if (Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameEn())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.requestSubmissionType.name.required"));
				}
				if (!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameEn()) && requestSubmissionTypeRepository
						.isExistRequestSubmissionTypeNameEn(requestSubmissionType.getRequestSubmissionTypeNameEn(),
								requestSubmissionType.getRequestSubmissionTypeGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.requestSubmissionType.nameEn.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameHi()) && requestSubmissionTypeRepository
						.isExistRequestSubmissionTypeNameHi(requestSubmissionType.getRequestSubmissionTypeNameHi(),
								requestSubmissionType.getRequestSubmissionTypeGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.requestSubmissionType.nameHi.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(requestSubmissionType.getRequestSubmissionTypeNameRl()) && requestSubmissionTypeRepository
						.isExistRequestSubmissionTypeNameRl(requestSubmissionType.getRequestSubmissionTypeNameRl(),
								requestSubmissionType.getRequestSubmissionTypeGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.requestSubmissionType.nameRl.unique"));
					return resultData;
				}
			}  catch (Exception e) {
				  resultData.setStatus(false);
		            resultData.setMessage("Error validating docs submission info: " + e.getMessage());
			}
	        return resultData;
	}

	@Override
	public BaseResponse validateSubmittedRequestStage(SubmittedRequestStage submittedRequestStage) {
		  BaseResponse resultData = new BaseResponse();
	        resultData.setStatus(true);
	        resultData.setMessage("Record SaveOrUpdate Successfully");
	        try {
				if (Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageCode())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.submittedRequestStage.code.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageCode()) && submittedRequestStageRepository
						.isExistSubmittedRequestStageCode(submittedRequestStage.getSubmittedRequestStageCode(),
								submittedRequestStage.getSubmittedRequestStageGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.submittedRequestStage.code.unique"));
					return resultData;
				}
				if (Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameEn())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.submittedRequestStage.NameEn.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameEn()) && submittedRequestStageRepository
						.isExistSubmittedRequestStageNameEn(submittedRequestStage.getSubmittedRequestStageNameEn(),
								submittedRequestStage.getSubmittedRequestStageGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.submittedRequestStage.NameEn.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameHi()) && submittedRequestStageRepository
						.isExistSubmittedRequestStageNameHi(submittedRequestStage.getSubmittedRequestStageNameHi(),
								submittedRequestStage.getSubmittedRequestStageGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.submittedRequestStage.NameHi.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(submittedRequestStage.getSubmittedRequestStageNameRl()) && submittedRequestStageRepository
						.isExistSubmittedRequestStageNameRl(submittedRequestStage.getSubmittedRequestStageNameRl(),
								submittedRequestStage.getSubmittedRequestStageGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.submittedRequestStage.NameRl.unique"));
					return resultData;
				}
			} catch (Exception e) {
				  resultData.setStatus(false);
		            resultData.setMessage("Error validating Submitted Request Stage: " + e.getMessage());
			}
			return resultData;
	}

	@Override
	public BaseResponse validateUnitArea(UnitArea UnitArea) {
		 BaseResponse resultData = new BaseResponse();
	        resultData.setStatus(true);
	        resultData.setMessage("Record SaveOrUpdate Successfully");
	        try {
				if (Util.isNullOrEmpty(UnitArea.getUnitAreaCode())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.unitArea.unitAreaCode.required"));
				}
				
				if (!Util.isNullOrEmpty(UnitArea.getUnitAreaCode()) && unitAreaRepository
						.isExistUnitAreaCode(UnitArea.getUnitAreaCode(), UnitArea.getUnitAreaGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.unitArea.unitAreaCode.unique"));
					return resultData;
				}
				if (Util.isNullOrEmpty(UnitArea.getUnitAreaNameEn())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.unitArea.unitAreaName.required"));
				}
				if (!Util.isNullOrEmpty(UnitArea.getUnitAreaNameEn()) && unitAreaRepository
						.isExistUnitAreaNameEn(UnitArea.getUnitAreaNameEn(), UnitArea.getUnitAreaGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.unitArea.UnitAreaNameEn.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(UnitArea.getUnitAreaNameHi()) && unitAreaRepository
						.isExistUnitAreaNameHi(UnitArea.getUnitAreaNameHi(), UnitArea.getUnitAreaGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.unitArea.UnitAreaNameHi.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(UnitArea.getUnitAreaNameRl()) && unitAreaRepository
						.isExistUnitAreaNameRl(UnitArea.getUnitAreaNameRl(), UnitArea.getUnitAreaGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.unitArea.UnitAreaNameRl.unique"));
					return resultData;
				}
			}catch (Exception e) {
				  resultData.setStatus(false);
		            resultData.setMessage("Error validating Unit Area: " + e.getMessage());
			}
			return resultData;
	}

	@Override
	public BaseResponse validateMstChargeDetails(MstChargeDetails mstChargeDetails) {
		 BaseResponse resultData = new BaseResponse();
	        resultData.setStatus(true);
	        resultData.setMessage("Record SaveOrUpdate Successfully");
	        try {
	        	if (Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsCode())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.chargeDetailsCode.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsCode()) && mstChargeDetailsRepository
						.isExistChargeDetailsCode(mstChargeDetails.getChargeDetailsCode(), mstChargeDetails.getChargeDetailsGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.chargeDetailsCode.unique"));
					return resultData;
				}

				if (Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameEn())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.chargeDetailsNameEn.required"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameEn()) && mstChargeDetailsRepository
						.isExistChargeDetailsNameEn(mstChargeDetails.getChargeDetailsNameEn(), mstChargeDetails.getChargeDetailsGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.chargeDetailsNameEn.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameHi()) && mstChargeDetailsRepository
						.isExistChargeDetailsNameHi(mstChargeDetails.getChargeDetailsNameHi(), mstChargeDetails.getChargeDetailsGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.chargeDetailsNameHi.unique"));
					return resultData;
				}
				if (!Util.isNullOrEmpty(mstChargeDetails.getChargeDetailsNameRl()) && mstChargeDetailsRepository
						.isExistChargeDetailsNameRl(mstChargeDetails.getChargeDetailsNameRl(), mstChargeDetails.getChargeDetailsGuid())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.chargeDetailsNameRl.unique"));
					return resultData;
				}

				
			}catch (Exception e) {
				  resultData.setStatus(false);
		            resultData.setMessage("Error validating Charge Details: " + e.getMessage());
			}
			return resultData;
	}

	
/////OccupationType Validatrion
	@Override
	public BaseResponse validateOccupationType(OccupationType occupationType) {
		 BaseResponse resultData = new BaseResponse();
	        resultData.setStatus(true);
	        resultData.setMessage("Record SaveOrUpdate Successfully");
	        try {
	    		if (Util.isNullOrEmpty(occupationType.getOccupationCode())) {
	    			resultData.setStatus(false);
	    			resultData.setMessage(PropertyReader.getFormMessage("master.occupationCode.required"));
	    			return resultData;
	    		}
	    		if (!Util.isNullOrEmpty(occupationType.getOccupationCode())
	    				&& occupationTypeRepository.isExistOccupationCode(occupationType.getOccupationCode(), occupationType.getOccupationGuid())) {
	    			resultData.setStatus(false);
	    			resultData.setMessage(PropertyReader.getFormMessage("master.occupationCode.unique"));
	    			return resultData;
	    		}

	    		if (Util.isNullOrEmpty(occupationType.getOccupationNameEn())) {
	    			resultData.setStatus(false);
	    			resultData.setMessage(PropertyReader.getFormMessage("master.occupationNameEn.required"));
	    			return resultData;
	    		}
	    		if (!Util.isNullOrEmpty(occupationType.getOccupationNameEn()) && occupationTypeRepository
	    				.isExistOccupationNameEn(occupationType.getOccupationNameEn(), occupationType.getOccupationGuid())) {
	    			resultData.setStatus(false);
	    			resultData.setMessage(PropertyReader.getFormMessage("master.occupationNameEn.unique"));
	    			return resultData;
	    		}
	    		if (Util.isNullOrEmpty(occupationType.getOccupationNameHi())) {
	    			resultData.setStatus(false);
	    			resultData.setMessage(PropertyReader.getFormMessage("master.occupationNameHi.required"));
	    			return resultData;
	    		}
	    		if (!Util.isNullOrEmpty(occupationType.getOccupationNameHi()) && occupationTypeRepository
	    				.isExistOccupationNameHi(occupationType.getOccupationNameHi(), occupationType.getOccupationGuid())) {
	    			resultData.setStatus(false);
	    			resultData.setMessage(PropertyReader.getFormMessage("master.occupationNameHi.unique"));
	    			return resultData;
	    		}
	    		if (Util.isNullOrEmpty(occupationType.getOccupationNameRl())) {
	    			resultData.setStatus(false);
	    			resultData.setMessage(PropertyReader.getFormMessage("master.occupationNameRl.required"));
	    			return resultData;
	    		}
	    		if (!Util.isNullOrEmpty(occupationType.getOccupationNameRl()) && occupationTypeRepository
	    				.isExistOccupationNameRl(occupationType.getOccupationNameRl(), occupationType.getOccupationGuid())) {
	    			resultData.setStatus(false);
	    			resultData.setMessage(PropertyReader.getFormMessage("master.occupationNameRl.unique"));
	    			return resultData;
	    		}
	    		if (Util.isNullOrEmpty(occupationType.getOccupationDesc())) {
	    			resultData.setStatus(false);
	    			resultData.setMessage(PropertyReader.getFormMessage("master.occupationDesc.required"));
	    			return resultData;
	    		}
	    	}catch (Exception e) {
				  resultData.setStatus(false);
		            resultData.setMessage("Error validating Occupation Type: " + e.getMessage());
			}
			return resultData;
	}

@Override
public BaseResponse validateEducationLevel(EducationLevel educationLevel) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
			if (Util.isNullOrEmpty(educationLevel.getEducationLevelCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.educationLevelCode.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(educationLevel.getEducationLevelCode()) && educationLevelRepository
					.isExistEducationLevelCode(educationLevel.getEducationLevelCode(), educationLevel.getEducationLevelGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.educationLevelCode.unique"));
				return resultData;
			}
			if (Util.isNullOrEmpty(educationLevel.getEducationLevelNameEn())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.educationLevelNameEn.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(educationLevel.getEducationLevelNameEn()) && educationLevelRepository
					.isExistEducationLevelNameEn(educationLevel.getEducationLevelNameEn(), educationLevel.getEducationLevelGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.educationLevelNameEn.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(educationLevel.getEducationLevelNameHi()) && educationLevelRepository
					.isExistEducationLevelNameHi(educationLevel.getEducationLevelNameHi(), educationLevel.getEducationLevelGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.educationLevelNameHi.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(educationLevel.getEducationLevelNameRl()) && educationLevelRepository
					.isExistEducationLevelNameRl(educationLevel.getEducationLevelNameRl(), educationLevel.getEducationLevelGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.educationLevelNameRl.unique"));
				return resultData;
			}
		}
     catch (Exception e) {
		  resultData.setStatus(false);
           resultData.setMessage("Error validating Education Level: " + e.getMessage());
	}
	return resultData;
}

@Override
public BaseResponse validateReligiousPlaces(ReligiousPlaces religiousPlaces) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    		if (Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.ReligiousPlaces.code.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesCode()) && religiousPlacesRepository
					.isExistReligiousPlacesCode(religiousPlaces.getReligiousPlacesCode(), religiousPlaces.getReligiousPlacesGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.ReligiousPlaces.code.unique"));
				return resultData;
			}
			if (Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesNameEn())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.ReligiousPlaces.nameEn.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(religiousPlaces.getReligiousPlacesNameEn()) && religiousPlacesRepository
					.isExistReligiousPlacesNameEn(religiousPlaces.getReligiousPlacesNameEn(), religiousPlaces.getReligiousPlacesGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.ReligiousPlaces.nameEn.unique"));
				return resultData;
			}

			
		}
     catch (Exception e) {
		  resultData.setStatus(false);
           resultData.setMessage("Error validating Religious Places: " + e.getMessage());
	}
	return resultData;
}

@Override
public BaseResponse validateDocsCategoryInfo(DocsCategoryInfo docsCategoryInfo) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if (Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.docsCategoryCode.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryCode()) && docsCategoryInfoRepository
					.isExistDocsCategoryInfoCode(docsCategoryInfo.getDocsCategoryCode(), docsCategoryInfo.getDocsCategoryInfoGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.docsCategoryCode.unique"));
				return resultData;
			}
			if (Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryNameEn())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.docsCategoryNameEn.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryNameEn()) && docsCategoryInfoRepository
					.isExistDocsCategoryInfoNameEn(docsCategoryInfo.getDocsCategoryNameEn(), docsCategoryInfo.getDocsCategoryInfoGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.docsCategoryNameEn.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryNameHi()) && docsCategoryInfoRepository
					.isExistDocsCategoryInfoNameHi(docsCategoryInfo.getDocsCategoryNameHi(), docsCategoryInfo.getDocsCategoryInfoGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.docsCategoryNameHi.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(docsCategoryInfo.getDocsCategoryNameRl()) && docsCategoryInfoRepository
					.isExistDocsCategoryInfoNameRl(docsCategoryInfo.getDocsCategoryNameRl(), docsCategoryInfo.getDocsCategoryInfoGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.docsCategoryNameRl.unique"));
				return resultData;
			}
    		
			
		}
     catch (Exception e) {
		  resultData.setStatus(false);
           resultData.setMessage("Error validating DocsCategoryInfo: " + e.getMessage());
	}
	return resultData;
}

@Override
public BaseResponse validateCommonMasterProcessStatus(CommonMasterProcessStatus commonMasterProcessStatus) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if (Util.isNullOrEmpty(commonMasterProcessStatus.getProcessStatusCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.processStatus.code.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(commonMasterProcessStatus.getProcessStatusCode()) && commonMasterProcessStatusRepo
					.isExistCommonProcessStatusCode(commonMasterProcessStatus.getProcessStatusCode(), commonMasterProcessStatus.getProcessStatusGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.processStatus.code.unique"));
				return resultData;
			}
			if (Util.isNullOrEmpty(commonMasterProcessStatus.getProcessStatusDesc())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.processStatus.desc.required"));
				return resultData;
			}
        
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating processStatus: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateSmsEmailTemplate(SmsEmailTemplate smsEmailTemplate) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if (Util.isNullOrEmpty(smsEmailTemplate.getSmsemailTemplateCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.smsEmailTemplate.code.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(smsEmailTemplate.getSmsemailTemplateCode()) && smsEmailTemplateRepository
					.isExistMstSmsEmailTemplateCode(smsEmailTemplate.getSmsemailTemplateCode(), smsEmailTemplate.getSmsemailTemplateGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.smsEmailTemplate.code.unique"));
				return resultData;
			}
			 if (Util.isNullOrEmpty(smsEmailTemplate.getSmsemailTemplateName())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.smsEmailTemplateName.name.required"));
					return resultData;
				}
			 if (Util.isNullOrEmpty(smsEmailTemplate.getSmsemailLinkedHeader())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.smsemailLinkedHeader.header.required"));
					return resultData;
				}
			 if (Util.isNullOrEmpty(smsEmailTemplate.getSmsemailEntityId())) {
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.smsemailEntityId.id.required"));
					return resultData;
				}
//			if (Util.isNullOrEmpty(smsEmailTemplate.getEmailServiceSubjectEn())) {
//				resultData.setStatus(false);
//				resultData.setMessage(PropertyReader.getFormMessage("master.smsEmailTemplate.emailServiceSubjectEn.required"));
//				return resultData;
//			}
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating smsEmailTemplate: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateOrgPrimary(OrgPrimary orgPrimary) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if(Util.isNullOrEmpty(orgPrimary.getOrgPrimaryCode()))
 		{
 			resultData.setStatus(false);
 			resultData.setMessage(PropertyReader.getFormMessage("master.orgPrimary.code.required"));
 		}if(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryCode()) && orgPrimaryRepository.isExistOrgPrimaryCode(orgPrimary.getOrgPrimaryCode(),orgPrimary.getOrgPrimaryGuid()) ){
 					resultData.setStatus(false);
 					resultData.setMessage(PropertyReader.getFormMessage("master.orgPrimary.code.unique"));
 					return resultData;
 		} if(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryNameEn()) && orgPrimaryRepository.isExistOrgPrimaryNameEn(orgPrimary.getOrgPrimaryNameEn(),orgPrimary.getOrgPrimaryGuid())){
 				resultData.setStatus(false);
 				resultData.setMessage(PropertyReader.getFormMessage("master.orgPrimary.nameEn.unique"));
 				return resultData;
 		}
 		 if(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryNameHi()) && orgPrimaryRepository.isExistOrgPrimaryNameHi(orgPrimary.getOrgPrimaryNameHi(),orgPrimary.getOrgPrimaryGuid())){
 				resultData.setStatus(false);
 				resultData.setMessage(PropertyReader.getFormMessage("master.orgPrimary.nameHi.unique"));
 				return resultData;
 		}
 		 if(!Util.isNullOrEmpty(orgPrimary.getOrgPrimaryNameRl()) && orgPrimaryRepository.isExistOrgPrimaryNameRl(orgPrimary.getOrgPrimaryNameRl(),orgPrimary.getOrgPrimaryGuid())){
 				resultData.setStatus(false);
 				resultData.setMessage(PropertyReader.getFormMessage("master.orgPrimary.nameRl.unique"));
 				return resultData;
 		}
 		if (Util.isNullOrEmpty(orgPrimary.getFromDate())) {
 		    resultData.setStatus(false);
 		    resultData.setMessage(PropertyReader.getFormMessage("master.orgPrimary.fromDate.required"));
 		    return resultData;
 		}
 		 if(Util.isNullOrEmpty(orgPrimary.getOrgPrimaryNameEn()))
  		{
  			resultData.setStatus(false);
  			resultData.setMessage(PropertyReader.getFormMessage("master.orgPrimary.nameEn.required"));
  		}

    	
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating orgPrimary: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateOrgWrapper(OrgWrapper orgWrapper) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if(Util.isNullOrEmpty(orgWrapper.getWraperCode()))
 		{
 			resultData.setStatus(false);
 			resultData.setMessage(PropertyReader.getFormMessage("master.orgWrapper.code.required"));
 		}if(!Util.isNullOrEmpty(orgWrapper.getWraperCode()) && orgWrapperRepository.isExistOrgWrapperCode(orgWrapper.getWraperCode(),orgWrapper.getWrapperGuid()) ){
 					resultData.setStatus(false);
 					resultData.setMessage(PropertyReader.getFormMessage("master.orgWrapper.code.unique"));
 					return resultData;
 		} if(!Util.isNullOrEmpty(orgWrapper.getWraperNameEn()) && orgWrapperRepository.isExistOrgWrapperNameEn(orgWrapper.getWraperNameEn(),orgWrapper.getWrapperGuid())){
 				resultData.setStatus(false);
 				resultData.setMessage(PropertyReader.getFormMessage("master.orgWrapper.nameEn.unique"));
 				return resultData;
 		}
 		 if(!Util.isNullOrEmpty(orgWrapper.getWraperNameHi()) && orgWrapperRepository.isExistOrgWrapperNameHi(orgWrapper.getWraperNameHi(),orgWrapper.getWrapperGuid())){
 				resultData.setStatus(false);
 				resultData.setMessage(PropertyReader.getFormMessage("master.orgWrapper.nameHi.unique"));
 				return resultData;
 		}
 		 if(!Util.isNullOrEmpty(orgWrapper.getWraperNameRl()) && orgWrapperRepository.isExistOrgWrapperNameRl(orgWrapper.getWraperNameRl(),orgWrapper.getWrapperGuid())){
 				resultData.setStatus(false);
 				resultData.setMessage(PropertyReader.getFormMessage("master.orgWrapper.nameRl.unique"));
 				return resultData;
 		}
 		if (Util.isNullOrEmpty(orgWrapper.getFromDate())) {
 		    resultData.setStatus(false);
 		    resultData.setMessage(PropertyReader.getFormMessage("master.orgWrapper.fromDate.required"));
 		    return resultData;
 		}
 		 if(Util.isNullOrEmpty(orgWrapper.getWraperNameEn()))
  		{
  			resultData.setStatus(false);
  			resultData.setMessage(PropertyReader.getFormMessage("master.orgWrapper.nameEn.required"));
  		}

    	
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating orgWrapper: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateGeoZoneMCD(GeoZoneMCD geoZoneMcd) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	
    	 if (Util.isNullOrEmpty(geoZoneMcd.getOrgPrimary())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.zone.primaryOrg.name.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(geoZoneMcd.getZoneCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geozone.geozoneCode.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(geoZoneMcd.getZoneCode())
					&& geoZoneMCDRepository.isExistGeoZoneCode(geoZoneMcd.getZoneCode(), geoZoneMcd.getZoneGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geozone.geozoneCode.unique"));
				return resultData;
			}
			if (Util.isNullOrEmpty(geoZoneMcd.getZoneNameEn())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geozone.geozoneNameEn.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(geoZoneMcd.getZoneNameEn())
					&& geoZoneMCDRepository.isExistGeoZoneNameEn(geoZoneMcd.getZoneNameEn(), geoZoneMcd.getZoneGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geozone.geozoneNameEn.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(geoZoneMcd.getZoneNameHi())
					&& geoZoneMCDRepository.isExistGeoZoneNameHi(geoZoneMcd.getZoneNameHi(), geoZoneMcd.getZoneGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geozone.geozoneNameHi.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(geoZoneMcd.getZoneNameRl())
					&& geoZoneMCDRepository.isExistGeoZoneNameRl(geoZoneMcd.getZoneNameRl(), geoZoneMcd.getZoneGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geozone.geozoneNameRl.unique"));
				return resultData;
			}
    	
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating GeoZoneMCD: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateOrgRadius(OrgRadius orgRadius) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    		if (Util.isNullOrEmpty(orgRadius.getOrgUnitName())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.orgRadius.orgUnitName.required"));
				return resultData;
			}
			
			if (Util.isNullOrZero(orgRadius.getInRadius())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.orgRadius.inRadius.valid"));
				return resultData;
			}
			if (Util.isNullOrZero(orgRadius.getOutRadius())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.orgRadius.outRadius.valid"));
				return resultData;
			}
    	
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating OrgRadius: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateRefDocsCategoryMap(RefDocsCategoryMap refDocsCategoryMap) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    		
    	 if (Util.isNullOrEmpty(refDocsCategoryMap.getAssessmentYear())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.refDocsCategoryMap.assessmentYear.required"));
				return resultData;
			}

			if (Util.isNullOrEmpty(refDocsCategoryMap.getDocsCategoryInfo())) {
				resultData.setStatus(false);
				resultData.setMessage(
						PropertyReader.getFormMessage("master.refDocsCategoryMap.docsCategoryInfo.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(refDocsCategoryMap.getDocsSubmissionInfo())) {
				resultData.setStatus(false);
				resultData.setMessage(
						PropertyReader.getFormMessage("master.refDocsCategoryMap.docsSubmissioninfo.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(refDocsCategoryMap.getRequestSubmissionType())) {
				resultData.setStatus(false);
				resultData.setMessage(
						PropertyReader.getFormMessage("master.refDocsCategoryMap.requestSubmissionType.required"));
				return resultData;
			}
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating RefDocsCategoryMap: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateRefUserDocsMap(RefUserDocsMap refUserDocsMap) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    		if (Util.isNullOrEmpty(refUserDocsMap.getAssessmentYear())) {
				resultData.setStatus(false);
				resultData
						.setMessage(PropertyReader.getFormMessage("master.usrRefUserDocsMap.assessmentYear.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(refUserDocsMap.getDocsSubmissionInfo())) {
				resultData.setStatus(false);
				resultData.setMessage(
						PropertyReader.getFormMessage("master.usrRefUserDocsMap.docsSubmissioninfo.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(refUserDocsMap.getRequestSubmissionType())) {
				resultData.setStatus(false);
				resultData.setMessage(
						PropertyReader.getFormMessage("master.usrRefUserDocsMap.requestSubmissionType.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(refUserDocsMap.getUserType())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.usrRefUserDocsMap.userType.required"));
				return resultData;
			}
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating RefUserDocsMap: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateGeoWardMCD(GeoWardMCD geoWardMCD) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if (Util.isNullOrEmpty(geoWardMCD.getZone())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.ward.zone.name.required"));
			}
			if (Util.isNullOrEmpty(geoWardMCD.getWardCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geoward.geoWardCode.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(geoWardMCD.getWardCode())
					&& geoWardMCDRepo.isExistGeoWardCode(geoWardMCD.getWardCode(), geoWardMCD.getWardGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geoward.geoWardCode.unique"));
				return resultData;
			}
			if (Util.isNullOrEmpty(geoWardMCD.getWardNameEn())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geoward.geoWardNameEn.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(geoWardMCD.getWardNameEn())
					&& geoWardMCDRepo.isExistGeoWardNameEn(geoWardMCD.getWardNameEn(), geoWardMCD.getWardGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geoward.geoWardNameEn.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(geoWardMCD.getWardNameHi())
					&& geoWardMCDRepo.isExistGeoWardNameHi(geoWardMCD.getWardNameHi(), geoWardMCD.getWardGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geoward.geoWardNameHi.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(geoWardMCD.getWardNameRl())
					&& geoWardMCDRepo.isExistGeoWardNameRl(geoWardMCD.getWardNameRl(), geoWardMCD.getWardGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geoward.geoWardNameRl.unique"));
				return resultData;
			}
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating GeoWardMCD: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateGeoColonyMCD(GeoColonyMCD colony) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if (Util.isNullOrEmpty(colony.getWard())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.colony.ward.name.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(colony.getColonyCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.geocolony.geocolonyCode.required"));
				return resultData;
			}
//			if (!Util.isNullOrEmpty(colony.getColonyCode())
//					&& geoColonyMCDRepo.isExistGeoColonyCode(colony.getColonyCode(), colony.getColonyGuid())) {
//				resultData.setStatus(false);
//				resultData.setMessage(PropertyReader.getFormMessage("master.geocolony.geocolonyCode.unique"));
//				return resultData;
//			}
//			if (!Util.isNullOrEmpty(colony.getColonyNameEn()) && geoColonyMCDRepo
//					.isExistGeoColonyNameEn(colony.getColonyNameEn(), colony.getColonyGuid())) {
//				resultData.setStatus(false);
//				resultData.setMessage(PropertyReader.getFormMessage("master.geocolony.geocolonyNameEn.unique"));
//				return resultData;
//			}
//			if (!Util.isNullOrEmpty(colony.getColonyNameHi()) && geoColonyMCDRepo
//					.isExistGeoColonyNameHi(colony.getColonyNameHi(), colony.getColonyGuid())) {
//				resultData.setStatus(false);
//				resultData.setMessage(PropertyReader.getFormMessage("master.geocolony.geocolonyNameHi.unique"));
//				return resultData;
//			}
//			if (!Util.isNullOrEmpty(colony.getColonyNameRl()) && geoColonyMCDRepo
//					.isExistGeoColonyNameRl(colony.getColonyNameRl(), colony.getColonyGuid())) {
//				resultData.setStatus(false);
//				resultData.setMessage(PropertyReader.getFormMessage("master.geocolony.geocolonyNameRl.unique"));
//				return resultData;
//			}
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating GeoColonyMCD: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateCommonMasterAppAlert(CommonMasterAppAlert obj) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if (Util.isNullOrEmpty(obj.getAppMaster())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMaster.appMaster.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(obj.getAppAlertSubjectEn())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMaster.appAlertSubjectEn.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(obj.getAppAlertSubjectEn()) && commonMasterAppAlertRepo
					.isExistCommonMasterAppAlertSubjectEn(obj.getAppAlertSubjectEn(), obj.getAppAlertGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMaster.appAlertSubjectEn.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(obj.getAppAlertContentEn()) && commonMasterAppAlertRepo
					.isExistCommonMasterAppAlertContentEn(obj.getAppAlertContentEn(), obj.getAppAlertGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMaster.appAlertContentEn.unique"));
				return resultData;
			}

			if (obj.getPriority().signum() < 0) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMaster.priority.required"));
				return resultData;
			}
			if (obj.getPriority().signum() < 0 && commonMasterAppAlertRepo
					.isExistCommonMasterAppAlertPriority(obj.getPriority(), obj.getAppAlertGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMaster.priority.unique"));
				return resultData;
			}
    	
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating Common Master App Alert: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateIntramcMenuMaster(IntramcMenuMaster intramcMenuMaster) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if (Util.isNullOrEmpty(intramcMenuMaster.getIntraMenuCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.intraMaster.intraMenuCode.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(intramcMenuMaster.getAppCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.intraMaster.appCode.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(intramcMenuMaster.getIntraMenuCode()) && intramcMenuMasterRepo
					.isExistIntraMenuMasterCode(intramcMenuMaster.getIntraMenuCode(), intramcMenuMaster.getMenuMasterGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.intraMaster.intraMenuCode.unique"));
				return resultData;
			}
			if (Util.isNullOrEmpty(intramcMenuMaster.getIntraMenuNameEn())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.intraMaster.intraMenuNameEn.required"));
				return resultData;
			}
    	
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
         resultData.setMessage("Error validating Intramc Menu Master: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateIntramcRoleMenuMap(IntramcRoleMenuMap intramcRoleMenuMap) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	
    	 if (Util.isNullOrEmpty(intramcRoleMenuMap.getMenuMaster())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.intramcRoleMenuMap.menuMaster.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(intramcRoleMenuMap.getRoleCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.intramcRoleMenuMap.roleCode.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(intramcRoleMenuMap.getRoleCode()) && intramcRoleMenuMapRepo
					.isExistRoleCode(intramcRoleMenuMap.getRoleCode(), intramcRoleMenuMap.getRefRoleMenuMapGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.intramcRoleMenuMap.roleCode.unique"));
				return resultData;
			}
    	 
     } catch (Exception e) {
         resultData.setStatus(false);
        // intramcRoleMenuMapRepo
         resultData.setMessage("Error validating intramc Role Menu Map Master: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateCommonMasterTradeClassification(CommonMasterTradeClassification tradeClassification) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	
    	 if (Util.isNullOrEmpty(tradeClassification.getOrgPrimary())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.tradeClassification.primaryOrg.name.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(tradeClassification.getTradeClassficationCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.tradeClassification.code.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(tradeClassification.getTradeClassficationCode())
					&& tradeClassificationRepo.isExistCommonTradeClassificationCode(tradeClassification.getTradeClassficationCode(),
							tradeClassification.getTradeClassficationGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.tradeClassification.code.unique"));
				return resultData;
			}
			if (Util.isNullOrEmpty(tradeClassification.getTradeClassficationNameEn())) {
				resultData.setStatus(false);
				resultData
						.setMessage(PropertyReader.getFormMessage("master.tradeClassification.nameEn.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(tradeClassification.getTradeClassficationNameEn())
					&& tradeClassificationRepo.isExistCommonTradeClassificationNameEn(tradeClassification.getTradeClassficationNameEn(),
							tradeClassification.getTradeClassficationGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.tradeClassification.nameEn.unique"));
				return resultData;
			}
//			if (!Util.isNullOrEmpty(tradeClassification.getTradeClassficationNameHi())
//					&& tradeClassificationRepo.isExistCommonTradeClassificationNameHi(tradeClassification.getTradeClassficationNameHi(),
//							tradeClassification.getTradeClassficationGuid())) {
//				resultData.setStatus(false);
//				resultData.setMessage(PropertyReader.getFormMessage("master.tradeClassification.nameHi.unique"));
//				return resultData;
//			}
//			if (!Util.isNullOrEmpty(tradeClassification.getTradeClassficationNameRl())
//					&& tradeClassificationRepo.isExistCommonTradeClassificationNameRl(tradeClassification.getTradeClassficationNameRl(),
//							tradeClassification.getTradeClassficationGuid())) {
//				resultData.setStatus(false);
//				resultData.setMessage(PropertyReader.getFormMessage("master.tradeClassification.nameRl.unique"));
//				return resultData;
//			}
     } catch (Exception e) {
         resultData.setStatus(false);
        // intramcRoleMenuMapRepo
         resultData.setMessage("Error validating tradeClassification Master: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateCommonMasterTradeType(CommonMasterTradeType commonMasterTradeType) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if (Util.isNullOrEmpty(commonMasterTradeType.getTradeClassification())) {
				resultData.setStatus(false);
				resultData.setMessage(
						PropertyReader.getFormMessage("master.commonMasterTradeType.commonTradeClassification.required"));
				return resultData;
			}
			if (Util.isNullOrZero(commonMasterTradeType.getLicencePeriod())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMasterTradeType.licencePeriod.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(commonMasterTradeType.getTradeTypeNameEn())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMasterTradeType.nameEn.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(commonMasterTradeType.getTradeTypeNameEn()) && commonMasterTradeTypeRepo
					.isExistCommonTradeTypeNameEn(commonMasterTradeType.getTradeTypeNameEn(), commonMasterTradeType.getTradeTypeGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMasterTradeType.nameEn.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(commonMasterTradeType.getTradeTypeNameHi()) && commonMasterTradeTypeRepo
					.isExistCommonTradeTypeNameHi(commonMasterTradeType.getTradeTypeNameHi(), commonMasterTradeType.getTradeTypeGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMasterTradeType.nameHi.unique"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(commonMasterTradeType.getTradeTypeNameRl()) && commonMasterTradeTypeRepo
					.isExistCommonTradeTypeNameRl(commonMasterTradeType.getTradeTypeNameRl(), commonMasterTradeType.getTradeTypeGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMasterTradeType.nameRl.unique"));
				return resultData;
			}
    	
     } catch (Exception e) {
         resultData.setStatus(false);

         resultData.setMessage("Error validating CommonMasterTradeType Master: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateCommonMasterIndustryArea(CommonMasterIndustryArea industryArea) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if (Util.isNullOrEmpty(industryArea.getIndustryCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMaster.industryCode.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(industryArea.getIndustryCode()) && industryAreaRepo
					.isExistCommonMasterIndustryCode(industryArea.getIndustryCode(), industryArea.getIndustryGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMaster.industryCode.unique"));
				return resultData;
			}
			if (Util.isNullOrEmpty(industryArea.getIndustryNameEn())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMaster.industryNameEn.required"));
				return resultData;
			}
			if (!Util.isNullOrEmpty(industryArea.getIndustryNameEn()) && industryAreaRepo
					.isExistCommonMasterIndustryNameEn(industryArea.getIndustryNameEn(), industryArea.getIndustryGuid())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.commonMaster.industryNameEn.unique"));
				return resultData;
			}
     } catch (Exception e) {
         resultData.setStatus(false);

         resultData.setMessage("Error validating CommonMasterIndustryArea Master: " + e.getMessage());
     }
     return resultData;
}

@Override
public BaseResponse validateMstRefSla(MstRefSla refSla) {
	 BaseResponse resultData = new BaseResponse();
     resultData.setStatus(true);
     resultData.setMessage("Record SaveOrUpdate Successfully");
     try {
    	 if (Util.isNullOrEmpty(refSla.getAppMaster())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.MstRefSla.AppMaster.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(refSla.getProcessStatus())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.MstRefSla.ProcessStatus.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(refSla.getFeStageLavel())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.MstRefSla.FeStageLavel.required"));
				return resultData;
			}
			if (Util.isNullOrEmpty(refSla.getFeStatusCode())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.MstRefSla.FeStatusCode.required"));
				return resultData;
			}
			if (!Util.isNotNull(refSla.getNextActionDueInDays())) {
				resultData.setStatus(false);
				resultData.setMessage(PropertyReader.getFormMessage("master.MstRefSla.NextActionDueInDay.required"));
				return resultData;
			}
     } catch (Exception e) {
         resultData.setStatus(false);

         resultData.setMessage("Error validating MstRefSla Master: " + e.getMessage());
     }
     return resultData;
}



}
