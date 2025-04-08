package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.PropertyOccupancyFactor;

public interface PropertyOccupancyFactorRepo extends JpaRepository<PropertyOccupancyFactor, String> {
	
	  @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM PropertyOccupancyFactor p WHERE p.occupancyFactorCode = :occupancyFactorCode AND p.occupancyFactorGuid != :occupancyFactorGuid")
	    boolean isExistOccupancyFactorCode(@Param("occupancyFactorCode") String occupancyFactorCode, @Param("occupancyFactorGuid") String occupancyFactorGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM PropertyOccupancyFactor p WHERE p.occupancyFactorNameEn = :occupancyFactorNameEn AND p.occupancyFactorGuid != :occupancyFactorGuid")
	    boolean isExistOccupancyFactorNameEn(@Param("occupancyFactorNameEn") String occupancyFactorNameEn, @Param("occupancyFactorGuid") String occupancyFactorGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM PropertyOccupancyFactor p WHERE p.occupancyFactorNameHi = :occupancyFactorNameHi AND p.occupancyFactorGuid != :occupancyFactorGuid")
	    boolean isExistOccupancyFactorNameHi(@Param("occupancyFactorNameHi") String occupancyFactorNameHi, @Param("occupancyFactorGuid") String occupancyFactorGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM PropertyOccupancyFactor p WHERE p.occupancyFactorNameRl = :occupancyFactorNameRl AND p.occupancyFactorGuid != :occupancyFactorGuid")
	    boolean isExistOccupancyFactorNameRl(@Param("occupancyFactorNameRl") String occupancyFactorNameRl, @Param("occupancyFactorGuid") String occupancyFactorGuid);

	  
}
