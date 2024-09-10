package com.master.app.pims.validators;


import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.DesignationAppointmentTypeRepository;
import com.master.app.pims.repositories.master.GeoCountryMasterRepo;
import com.master.app.pims.repositories.master.GeoDistrictRepo;
import com.master.app.pims.utils.PropertyReader;
import com.master.app.pims.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class ValidatorImpl implements Validator {

    @Autowired
    private MessageSource messageSource;
    
    

//    @Autowired
//    private DesignationAppointmentTypeRepository designationAppointmentTypeRepository;

//    @Autowired
//    private GeoDistrictRepo geoDistrictRepo;

    @Autowired
    private CommonValidator commonValidator;

  
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

            if (!Util.isNullOrEmpty(country.getCountryCode()) && commonValidator
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
            if (!Util.isNullOrEmpty(country.getCountryNameEn()) && commonValidator
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
            if (!Util.isNullOrEmpty(country.getCountryNameEn()) && commonValidator
                    .isExistGeoCountryMstNameEn(country.getCountryNameEn(), country.getCountryMstGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.GeoCountryMst.nameEn.unique"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(country.getCountryNameHi()) && commonValidator
                    .isExistGeoCountryMstNameHi(country.getCountryNameHi(), country.getCountryMstGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.GeoCountryMst.nameHi.unique"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(country.getCountryNameRl()) && commonValidator
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
	@Override
	public BaseResponse validateMasterState(GeoStateMaster state) {
		  BaseResponse resultData = new BaseResponse();
	        resultData.setStatus(true);
	        resultData.setMessage("Record SaveOrUpdate Successfully");
	        try
	        {
	        	if(Util.isNullOrEmpty(state.getStateCode()))
				{
					resultData.setStatus(false);
					resultData.setMessage(PropertyReader.getFormMessage("master.state.state.code.required"));
				}if(!Util.isNullOrEmpty(state.getStateCode()) && commonValidator.isExistStateCode(state.getStateCode(),state.getStateMasterGuid()) ){
							resultData.setStatus(false);
							resultData.setMessage(PropertyReader.getFormMessage("master.state.code.unique"));
							return resultData;
				} if(!Util.isNullOrEmpty(state.getStateNameEn()) && commonValidator.isExistStateNameEn(state)){
						resultData.setStatus(false);
						resultData.setMessage(PropertyReader.getFormMessage("master.state.nameEn.unique"));
						return resultData;
				}
				 if(!Util.isNullOrEmpty(state.getStateNameHi()) && commonValidator.isExistStateNameHi(state)){
						resultData.setStatus(false);
						resultData.setMessage(PropertyReader.getFormMessage("master.state.nameHi.unique"));
						return resultData;
				}
				 if(!Util.isNullOrEmpty(state.getStateNameRl()) && commonValidator.isExistStateNameRl(state)){
						resultData.setStatus(false);
						resultData.setMessage(PropertyReader.getFormMessage("master.state.nameRl.unique"));
						return resultData;
				}
	        }
	        catch (Exception e) {
	            resultData.setStatus(false);
	            resultData.setMessage("Error validating state: " + e.getMessage());
	        }
	        return resultData;
		

	}


}
