package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.GeoColonyMCD;

public interface GeoColonyMCDRepo extends JpaRepository<GeoColonyMCD, String>{
	  // Check if a GeoColonyMCD with a given colonyCode exists and the colonyGuid is different
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoColonyMCD g WHERE g.colonyCode = :geoColonyCode AND g.colonyGuid != :geoColonyGuid")
    boolean isExistGeoColonyCode(@Param("geoColonyCode") String geoColonyCode, @Param("geoColonyGuid") String geoColonyGuid);

    // Check if a GeoColonyMCD with a given colonyNameEn exists and the colonyGuid is different
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoColonyMCD g WHERE g.colonyNameEn = :geoColonyNameEn AND g.colonyGuid != :geoColonyGuid")
    boolean isExistGeoColonyNameEn(@Param("geoColonyNameEn") String geoColonyNameEn, @Param("geoColonyGuid") String geoColonyGuid);

    // Check if a GeoColonyMCD with a given colonyNameHi exists and the colonyGuid is different
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoColonyMCD g WHERE g.colonyNameHi = :geoColonyNameHi AND g.colonyGuid != :geoColonyGuid")
    boolean isExistGeoColonyNameHi(@Param("geoColonyNameHi") String geoColonyNameHi, @Param("geoColonyGuid") String geoColonyGuid);

    // Check if a GeoColonyMCD with a given colonyNameRl exists and the colonyGuid is different
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoColonyMCD g WHERE g.colonyNameRl = :geoColonyNameRl AND g.colonyGuid != :geoColonyGuid")
    boolean isExistGeoColonyNameRl(@Param("geoColonyNameRl") String geoColonyNameRl, @Param("geoColonyGuid") String geoColonyGuid);
}
