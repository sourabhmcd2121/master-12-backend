package com.master.app.pims.validators;

import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class CommonValidatorImpl implements CommonValidator{

    @Autowired
    private GeoCountryMstRepository geoCountryMstRepository;

    @Override
    public boolean isExistGeoCountryMstCode(String countryCode, String countryMstGuid) throws SQLException {
        return false;
    }

    @Override
    public boolean isExistGeoCountryMstMobileCode(String countryMobileCode, String countryMstGuid) throws SQLException {
        return false;
    }

    @Override
    public boolean isExistGeoCountryMstNameEn(String countryNameEn, String countryMstGuid) throws SQLException {
        return false;
    }

    @Override
    public boolean isExistGeoCountryMstNameHi(String countryNameHi, String countryMstGuid) throws SQLException {
        return false;
    }

    @Override
    public boolean isExistGeoCountryMstNameRl(String countryNameRl, String countryMstGuid) throws SQLException {
        return false;
    }
}
