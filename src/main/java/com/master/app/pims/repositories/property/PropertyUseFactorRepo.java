package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.PropertyUseFactor;

public interface PropertyUseFactorRepo extends JpaRepository<PropertyUseFactor, String>{
	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyUseFactor p WHERE p.useFactorCode = :useFactorCode AND p.useFactorGuid != :useFactorGuid")
	boolean isExistUseFactorCode(@Param("useFactorCode") String useFactorCode, @Param("useFactorGuid") String useFactorGuid);

	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyUseFactor p WHERE p.useFactorNameEn = :useFactorNameEn AND p.useFactorGuid != :useFactorGuid")
	boolean isExistUseFactorNameEn(@Param("useFactorNameEn") String useFactorNameEn, @Param("useFactorGuid") String useFactorGuid);

	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyUseFactor p WHERE p.useFactorNameHi = :useFactorNameHi AND p.useFactorGuid != :useFactorGuid")
	boolean isExistUseFactorNameHi(@Param("useFactorNameHi") String useFactorNameHi, @Param("useFactorGuid") String useFactorGuid);

	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyUseFactor p WHERE p.useFactorNameRl = :useFactorNameRl AND p.useFactorGuid != :useFactorGuid")
	boolean isExistUseFactorNameRl(@Param("useFactorNameRl") String useFactorNameRl, @Param("useFactorGuid") String useFactorGuid);

	
	
}
