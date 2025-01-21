package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.GeoWardMCD;

public interface GeoWardMCDRepo extends JpaRepository<GeoWardMCD, String>{
	 // Query to check if a GeoWard with the provided geoWardCode and geoWardGuid exists
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoWardMCD g WHERE g.wardCode = :geoWardCode AND g.wardGuid != :geoWardGuid")
    boolean isExistGeoWardCode(@Param("geoWardCode") String geoWardCode, @Param("geoWardGuid") String geoWardGuid);

    // Query to check if a GeoWard with the provided geoWardNameEn and geoWardGuid exists
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoWardMCD g WHERE g.wardNameEn = :geoWardNameEn AND g.wardGuid != :geoWardGuid")
    boolean isExistGeoWardNameEn(@Param("geoWardNameEn") String geoWardNameEn, @Param("geoWardGuid") String geoWardGuid);

    // Query to check if a GeoWard with the provided geoWardNameHi and geoWardGuid exists
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoWardMCD g WHERE g.wardNameHi = :geoWardNameHi AND g.wardGuid != :geoWardGuid")
    boolean isExistGeoWardNameHi(@Param("geoWardNameHi") String geoWardNameHi, @Param("geoWardGuid") String geoWardGuid);

    // Query to check if a GeoWard with the provided geoWardNameRl and geoWardGuid exists
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GeoWardMCD g WHERE g.wardNameRl = :geoWardNameRl AND g.wardGuid != :geoWardGuid")
    boolean isExistGeoWardNameRl(@Param("geoWardNameRl") String geoWardNameRl, @Param("geoWardGuid") String geoWardGuid);

}
