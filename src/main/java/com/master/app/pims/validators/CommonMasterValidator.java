package com.master.app.pims.validators;


import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.ApplicationMasterRepository;
import com.master.app.pims.repositories.AssessmentYearRepository;
import com.master.app.pims.repositories.AssociatedChargesInfoRepository;
import com.master.app.pims.repositories.master.GeoStateMasterRepository;
import com.master.app.pims.repositories.mst.DocsSubmissionInfoRepository;
import com.master.app.pims.repositories.mst.GeoColonyCategoryRepository;
import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
import com.master.app.pims.repositories.mst.RequestSubmissionTypeRepository;
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
    private AssociatedChargesInfoRepository associatedChargesInfoRepository;
    
    @Autowired
    private DocsSubmissionInfoRepository docsSubmissionInfoRepository;
    
    @Autowired
    private RequestSubmissionTypeRepository requestSubmissionTypeRepository;

    
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
				
				// Assuming you have startYear and endYear fields in your assessmentYear or another object
			

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

	
}
