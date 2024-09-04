package com.master.app.pims.validators;

import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class CommonValidatorImpl implements CommonValidator {

    @Autowired
    private GeoCountryMstRepository geoCountryMstRepository;

    @Override
    public boolean isExistGeoCountryMstCode(String countryCode, String countryMstGuid) throws SQLException {
        return geoCountryMstRepository.isExistGeoCountryMstCode(countryCode, countryMstGuid);
    }

    @Override
    public boolean isExistGeoCountryMstMobileCode(String countryMobileCode, String countryMstGuid) throws SQLException {
        return geoCountryMstRepository.isExistGeoCountryMstMobileCode(countryMobileCode, countryMstGuid);
    }

    @Override
    public boolean isExistGeoCountryMstNameEn(String countryNameEn, String countryMstGuid) throws SQLException {
        return geoCountryMstRepository.isExistGeoCountryMstNameEn(countryNameEn, countryMstGuid);
    }

    @Override
    public boolean isExistGeoCountryMstNameHi(String countryNameHi, String countryMstGuid) throws SQLException {
        return geoCountryMstRepository.isExistGeoCountryMstNameHi(countryNameHi, countryMstGuid);
    }

    @Override
    public boolean isExistGeoCountryMstNameRl(String countryNameRl, String countryMstGuid) throws SQLException {
        return geoCountryMstRepository.isExistGeoCountryMstNameRl(countryNameRl, countryMstGuid);
    }
}
