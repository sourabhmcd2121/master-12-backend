package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;

public interface GeoColonyCategoryRepository extends JpaRepository<GeoColonyCategory, String> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM GeoColonyCategory c WHERE c.colonyCategoryCode = :geoColonyCategoryCode AND c.colonyCategoryGuid != :geoColonyCategoryGuid")
    boolean isExistGeoColonyCategoryCode(@Param("geoColonyCategoryCode") String geoColonyCategoryCode, @Param("geoColonyCategoryGuid") String geoColonyCategoryGuid);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM GeoColonyCategory c WHERE c.colonyCategoryNameEn = :geoColonyCategoryNameEn AND c.colonyCategoryGuid != :geoColonyCategoryGuid")
    boolean isExistGeoColonyCategoryNameEn(@Param("geoColonyCategoryNameEn") String geoColonyCategoryNameEn, @Param("geoColonyCategoryGuid") String geoColonyCategoryGuid);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM GeoColonyCategory c WHERE c.colonyCategoryNameHi = :geoColonyCategoryNameHi AND c.colonyCategoryGuid != :geoColonyCategoryGuid")
    boolean isExistGeoColonyCategoryNameHi(@Param("geoColonyCategoryNameHi") String geoColonyCategoryNameHi, @Param("geoColonyCategoryGuid") String geoColonyCategoryGuid);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM GeoColonyCategory c WHERE c.colonyCategoryNameRl = :geoColonyCategoryNameRl AND c.colonyCategoryGuid != :geoColonyCategoryGuid")
    boolean isExistGeoColonyCategoryNameRl(@Param("geoColonyCategoryNameRl") String geoColonyCategoryNameRl, @Param("geoColonyCategoryGuid") String geoColonyCategoryGuid);
}

