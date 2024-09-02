package com.master.app.pims.repositories.master;

import com.master.app.pims.entities.schemas.master.GeoDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GeoDistrictRepo extends JpaRepository<GeoDistrict, String> {
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END " +
            "FROM GeoDistrict d " +
            "WHERE TRIM(UPPER(d.districtCode)) = TRIM(UPPER(:code)) " +
            "AND TRIM(d.districtMasterGuid) <> TRIM(:guid)")
    boolean isExistDistrictCode(@Param("code") String code, @Param("guid") String guid);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END " +
            "FROM GeoDistrict d " +
            "WHERE TRIM(UPPER(d.districtNameEn)) = TRIM(UPPER(:districtNameEn)) " +
            "AND d.geoStateMasterGuid.stateMasterGuid = :stateMasterGuid " +
            "AND TRIM(d.districtMasterGuid) <> TRIM(:districtMasterGuid)")
    boolean isExistDistrictNameEn(@Param("districtNameEn") String nameEn,
                                  @Param("stateMasterGuid") String stateMasterGuid,
                                  @Param("districtMasterGuid") String districtMasterGuid);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END " +
            "FROM GeoDistrict d " +
            "WHERE TRIM(UPPER(d.districtNameHi)) = TRIM(UPPER(:nameHi)) " +
            "AND d.geoStateMasterGuid.stateMasterGuid = :stateMasterGuid " +
            "AND TRIM(d.districtMasterGuid) <> TRIM(:districtMasterGuid)")
    boolean isExistDistrictNameHi(@Param("nameHi") String nameHi,
                                  @Param("stateMasterGuid") String stateMasterGuid,
                                  @Param("districtMasterGuid") String districtMasterGuid);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END " +
            "FROM GeoDistrict d " +
            "WHERE TRIM(UPPER(d.districtNameRl)) = TRIM(UPPER(:nameRl)) " +
            "AND d.geoStateMasterGuid.stateMasterGuid = :stateMasterGuid " +
            "AND TRIM(d.districtMasterGuid) <> TRIM(:districtMasterGuid)")
    boolean isExistDistrictNameRl(@Param("nameRl") String nameRl,
                                  @Param("stateMasterGuid") String stateMasterGuid,
                                  @Param("districtMasterGuid") String districtMasterGuid);
}
