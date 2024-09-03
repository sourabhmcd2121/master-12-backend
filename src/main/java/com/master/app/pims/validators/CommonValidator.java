package com.master.app.pims.validators;

import java.sql.SQLException;

public interface CommonValidator {
    boolean isExistGeoCountryMstCode(String countryCode, String countryMstGuid) throws SQLException;
    boolean isExistGeoCountryMstMobileCode(String countryMobileCode, String countryMstGuid) throws SQLException;
    boolean isExistGeoCountryMstNameEn(String countryNameEn, String countryMstGuid) throws SQLException;
    boolean isExistGeoCountryMstNameHi(String countryNameHi, String countryMstGuid) throws SQLException;
    boolean isExistGeoCountryMstNameRl(String countryNameRl, String countryMstGuid) throws SQLException;
}
