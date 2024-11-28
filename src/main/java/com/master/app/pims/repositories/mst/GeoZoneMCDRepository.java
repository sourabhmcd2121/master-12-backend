package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.GeoZoneMCD;

public interface GeoZoneMCDRepository extends JpaRepository<GeoZoneMCD, String> {

    @Query("SELECT CASE WHEN COUNT(z) > 0 THEN TRUE ELSE FALSE END FROM GeoZoneMCD z WHERE z.zoneCode = :geoZoneCode AND z.zoneGuid != :geoZoneGuid")
    boolean isExistGeoZoneCode(@Param("geoZoneCode") String geoZoneCode, @Param("geoZoneGuid") String geoZoneGuid);

    @Query("SELECT CASE WHEN COUNT(z) > 0 THEN TRUE ELSE FALSE END FROM GeoZoneMCD z WHERE z.zoneNameEn = :geoZoneNameEn AND z.zoneGuid != :geoZoneGuid")
    boolean isExistGeoZoneNameEn(@Param("geoZoneNameEn") String geoZoneNameEn, @Param("geoZoneGuid") String geoZoneGuid);

    @Query("SELECT CASE WHEN COUNT(z) > 0 THEN TRUE ELSE FALSE END FROM GeoZoneMCD z WHERE z.zoneNameHi = :geoZoneNameHi AND z.zoneGuid != :geoZoneGuid")
    boolean isExistGeoZoneNameHi(@Param("geoZoneNameHi") String geoZoneNameHi, @Param("geoZoneGuid") String geoZoneGuid);

    @Query("SELECT CASE WHEN COUNT(z) > 0 THEN TRUE ELSE FALSE END FROM GeoZoneMCD z WHERE z.zoneNameRl = :geoZoneNameRl AND z.zoneGuid != :geoZoneGuid")
    boolean isExistGeoZoneNameRl(@Param("geoZoneNameRl") String geoZoneNameRl, @Param("geoZoneGuid") String geoZoneGuid);
}
