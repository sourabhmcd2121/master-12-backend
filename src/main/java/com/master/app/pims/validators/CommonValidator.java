package com.master.app.pims.validators;

import java.sql.SQLException;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;

public interface CommonValidator {
    boolean isExistGeoCountryMstCode(String countryCode, String countryMstGuid) throws SQLException;
    boolean isExistGeoCountryMstMobileCode(String countryMobileCode, String countryMstGuid) throws SQLException;
    boolean isExistGeoCountryMstNameEn(String countryNameEn, String countryMstGuid) throws SQLException;
    boolean isExistGeoCountryMstNameHi(String countryNameHi, String countryMstGuid) throws SQLException;
    boolean isExistGeoCountryMstNameRl(String countryNameRl, String countryMstGuid) throws SQLException;
    
    ///////state master
    boolean isExistStateCode(String stateCode,String stateMasterGuid) throws SQLException;
	 boolean isExistStateNameEn(GeoStateMaster stateNameEn) throws SQLException ;
	 boolean isExistStateNameHi(GeoStateMaster stateNameHi) throws SQLException ;
	 boolean isExistStateNameRl(GeoStateMaster stateNameRl) throws SQLException ;

}
