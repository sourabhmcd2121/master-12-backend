package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.property.PropertyAgeFactor;

public interface PropertyAgeFactorRepo extends JpaRepository<PropertyAgeFactor, String> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyAgeFactor p WHERE p.ageFactorCode = :ageFactorCode AND p.ageFactorGuid != :ageFactorGuid")
    boolean isExistAgeFactorCode(@Param("ageFactorCode") String ageFactorCode, @Param("ageFactorGuid") String ageFactorGuid);
}
