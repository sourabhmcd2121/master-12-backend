package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.PropertyExemption;

public interface PropertyExemptionRepo extends JpaRepository<PropertyExemption, String>{
	 // Query to check if a PropertyExemption exists by exemptionCode, excluding the given exemptionGuid
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyExemption p WHERE p.exemptionCode = :exemptionCode AND p.exemptionGuid != :exemptionGuid")
    boolean isExistPropertyExemptionFactorCode(@Param("exemptionCode") String exemptionCode, @Param("exemptionGuid") String exemptionGuid);

    // Query to check if a PropertyExemption exists by exemptionNameEn, excluding the given exemptionGuid
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyExemption p WHERE p.exemptionNameEn = :exemptionNameEn AND p.exemptionGuid != :exemptionGuid")
    boolean isExistPropertyExemptionFactorNameEn(@Param("exemptionNameEn") String exemptionNameEn, @Param("exemptionGuid") String exemptionGuid);

    // Query to check if a PropertyExemption exists by exemptionNameHi, excluding the given exemptionGuid
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyExemption p WHERE p.exemptionNameHi = :exemptionNameHi AND p.exemptionGuid != :exemptionGuid")
    boolean isExistPropertyExemptionFactorNameHi(@Param("exemptionNameHi") String exemptionNameHi, @Param("exemptionGuid") String exemptionGuid);

    // Query to check if a PropertyExemption exists by exemptionNameRl, excluding the given exemptionGuid
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyExemption p WHERE p.exemptionNameRl = :exemptionNameRl AND p.exemptionGuid != :exemptionGuid")
    boolean isExistPropertyExemptionFactorNameRl(@Param("exemptionNameRl") String exemptionNameRl, @Param("exemptionGuid") String exemptionGuid);
}
