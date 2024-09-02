package com.master.app.pims.validators;

import com.master.app.pims.entities.schemas.master.DesignationAppointmentType;
import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.entities.schemas.master.GeoDistrict;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.DesignationAppointmentTypeRepository;
import com.master.app.pims.repositories.MasterGeoCountryRepository;
import com.master.app.pims.repositories.master.GeoDistrictRepo;
import com.master.app.pims.utils.PropertyReader;
import com.master.app.pims.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class CommonValidator {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MasterGeoCountryRepository masterGeoCountryRepository;

    @Autowired
    private DesignationAppointmentTypeRepository designationAppointmentTypeRepository;

    @Autowired
    private GeoDistrictRepo geoDistrictRepo;

    public BaseResponse validateMasterCountry(GeoCountryMaster country) {
        BaseResponse resultData = new BaseResponse();
        resultData.setStatus(true);
        resultData.setMessage("Record SaveOrUpdate Successfully");
        try {
            if (Util.isNullOrEmpty(country.getCountryCode())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.country.countryCode.required"));
            } else if (masterGeoCountryRepository.existsByCountryCodeIgnoreCaseAndCountryMasterGuidNot(
                    country.getCountryCode(),country.getCountryMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.country.countryCode.unique"));
            } else if (Util.isNullOrEmpty(country.getCountryNameEn())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.country.countryNameEn.unique"));
            } else if (masterGeoCountryRepository.existsByCountryNameEnIgnoreCaseAndCountryMasterGuidNot(
                    country.getCountryNameEn(),country.getCountryMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.country.countryNameEn.unique"));
            } else if (Util.isNullOrEmpty(country.getCountryNameHi())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.country.countryNameHi.unique"));
            } else if (masterGeoCountryRepository.existsByCountryNameHiIgnoreCaseAndCountryMasterGuidNot(
                    country.getCountryNameHi(),country.getCountryMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.country.countryNameHi.unique"));
            } else if (Util.isNullOrEmpty(country.getCountryNameRl())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.country.countryNameRl.unique"));
            } else if (masterGeoCountryRepository.existsByCountryNameRlIgnoreCaseAndCountryMasterGuidNot(
                    country.getCountryNameRl(),country.getCountryMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.country.countryNameRl.unique"));
            } else if (!Util.isNotNull(country.getFromDate())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.country.fromDate.required"));
            }
        } catch (Exception e) {
            resultData.setStatus(false);
            resultData.setMessage("Error validating country: " + e.getMessage());
        }
        return resultData;
    }

    public BaseResponse validateDesignationAppointmentType(DesignationAppointmentType designationAppointmentType) {
        BaseResponse resultData = new BaseResponse();
        resultData.setStatus(true);
        resultData.setMessage("Record SaveOrUpdate Successfully");
        try {
            if (Util.isNullOrEmpty(designationAppointmentType.getDesignationAppointmentTypeCode())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.designationAppointmentType.code.required"));
            }
            if (!Util.isNullOrEmpty(designationAppointmentType.getDesignationAppointmentTypeName())
                    && designationAppointmentTypeRepository.existsByNameExcludingGuid(
                    designationAppointmentType.getDesignationAppointmentTypeName(),
                    designationAppointmentType.getDesignationAppointmentTypeGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.designationAppointmentType.name.unique"));
                return resultData;
            }
        } catch (Exception e) {

        }
        return resultData;
    }

    public BaseResponse validateDistrict(GeoDistrict district) {
        BaseResponse resultData = new BaseResponse();
        resultData.setStatus(true);
        resultData.setMessage("Record SaveOrUpdate Successfully");
        try {
            if (Util.isNullOrEmpty(district.getDistrictCode())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.district.code.required"));
            }
            if (!Util.isNullOrEmpty(district.getDistrictCode()) && geoDistrictRepo
                    .isExistDistrictCode(district.getDistrictCode(),district.getDistrictMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.district.code.unique"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(district.getDistrictNameEn())
                    && geoDistrictRepo.isExistDistrictNameEn(district.getDistrictNameEn(),district.getGeoStateMasterGuid().getStateMasterGuid(),district.getDistrictMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.district.nameEn.unique"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(district.getDistrictNameHi())
                    && geoDistrictRepo.isExistDistrictNameHi(district.getDistrictNameHi(),district.getGeoStateMasterGuid().getStateMasterGuid(),district.getDistrictMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.district.nameHi.unique"));
                return resultData;
            }
            if (!Util.isNullOrEmpty(district.getDistrictNameRl())
                    && geoDistrictRepo.isExistDistrictNameRl(district.getDistrictNameRl(),district.getGeoStateMasterGuid().getStateMasterGuid(),district.getDistrictMasterGuid())) {
                resultData.setStatus(false);
                resultData.setMessage(PropertyReader.getFormMessage("master.district.nameRl.unique"));
                return resultData;
            }
        } catch (Exception e) {

        }
        return resultData;
    }
}
