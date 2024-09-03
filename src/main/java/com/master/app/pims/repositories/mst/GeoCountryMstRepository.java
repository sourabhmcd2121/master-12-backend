package com.master.app.pims.repositories.mst;


import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;

public interface GeoCountryMstRepository extends JpaRepository<GeoCountryMst, String> {
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoCountryMst g WHERE g.countryCode = :countryCode AND g.countryMstGuid = :countryMstGuid")
    boolean isExistGeoCountryMstCode(@Param("countryCode") String countryCode, @Param("countryMstGuid") String countryMstGuid) throws SQLException;

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoCountryMst g WHERE g.countryMobileCode = :countryMobileCode AND g.countryMstGuid = :countryMstGuid")
    boolean isExistGeoCountryMstMobileCode(@Param("countryMobileCode") String countryMobileCode, @Param("countryMstGuid") String countryMstGuid) throws SQLException;

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoCountryMst g WHERE g.countryNameEn = :countryNameEn AND g.countryMstGuid = :countryMstGuid")
    boolean isExistGeoCountryMstNameEn(@Param("countryNameEn") String countryNameEn, @Param("countryMstGuid") String countryMstGuid) throws SQLException;

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoCountryMst g WHERE g.countryNameHi = :countryNameHi AND g.countryMstGuid = :countryMstGuid")
    boolean isExistGeoCountryMstNameHi(@Param("countryNameHi") String countryNameHi, @Param("countryMstGuid") String countryMstGuid) throws SQLException;

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoCountryMst g WHERE g.countryNameRl = :countryNameRl AND g.countryMstGuid = :countryMstGuid")
    boolean isExistGeoCountryMstNameRl(@Param("countryNameRl") String countryNameRl, @Param("countryMstGuid") String countryMstGuid) throws SQLException;

}