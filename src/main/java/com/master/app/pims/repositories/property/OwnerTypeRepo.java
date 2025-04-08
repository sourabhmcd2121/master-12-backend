package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.OwnerType;
import com.master.app.pims.entities.schemas.property.PropertyAgeFactor;

public interface OwnerTypeRepo extends JpaRepository<OwnerType, String>{
	 // Check if a record exists with the same ownerTypeCode and a different ownerTypeGuid
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OwnerType o WHERE o.ownerTypeCode = :ownerTypeCode AND o.ownerTypeGuid != :ownerTypeGuid")
    boolean isExistOwnerTypeCode(@Param("ownerTypeCode") String ownerTypeCode, @Param("ownerTypeGuid") String ownerTypeGuid);

    // Check if a record exists with the same ownerTypeNameEn and a different ownerTypeGuid
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OwnerType o WHERE o.ownerTypeNameEn = :ownerTypeNameEn AND o.ownerTypeGuid != :ownerTypeGuid")
    boolean isExistOwnerTypeNameEn(@Param("ownerTypeNameEn") String ownerTypeNameEn, @Param("ownerTypeGuid") String ownerTypeGuid);

    // Check if a record exists with the same ownerTypeNameHi and a different ownerTypeGuid
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OwnerType o WHERE o.ownerTypeNameHi = :ownerTypeNameHi AND o.ownerTypeGuid != :ownerTypeGuid")
    boolean isExistOwnerTypeNameHi(@Param("ownerTypeNameHi") String ownerTypeNameHi, @Param("ownerTypeGuid") String ownerTypeGuid);

    // Check if a record exists with the same ownerTypeNameRl and a different ownerTypeGuid
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OwnerType o WHERE o.ownerTypeNameRl = :ownerTypeNameRl AND o.ownerTypeGuid != :ownerTypeGuid")
    boolean isExistOwnerTypeNameRl(@Param("ownerTypeNameRl") String ownerTypeNameRl, @Param("ownerTypeGuid") String ownerTypeGuid);
}
