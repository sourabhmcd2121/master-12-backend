package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.OwnerCategory;
import com.master.app.pims.entities.schemas.property.PropertyAgeFactor;

public interface OwnerCategoryRepo extends JpaRepository<OwnerCategory, String> {
	
	 @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OwnerCategory o WHERE o.ownerCategoryCode = :ownerCategoryCode AND o.ownerCategoryGuid != :ownerCategoryGuid")
	    boolean isExistOwnerCategoryCode(@Param("ownerCategoryCode") String ownerCategoryCode, @Param("ownerCategoryGuid") String ownerCategoryGuid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OwnerCategory o WHERE o.ownerCategoryNameEn = :ownerCategoryNameEn AND o.ownerCategoryGuid != :ownerCategoryGuid")
	    boolean isExistOwnerCategoryNameEn(@Param("ownerCategoryNameEn") String ownerCategoryNameEn, @Param("ownerCategoryGuid") String ownerCategoryGuid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OwnerCategory o WHERE o.ownerCategoryNameHi = :ownerCategoryNameHi AND o.ownerCategoryGuid != :ownerCategoryGuid")
	    boolean isExistOwnerCategoryNameHi(@Param("ownerCategoryNameHi") String ownerCategoryNameHi, @Param("ownerCategoryGuid") String ownerCategoryGuid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OwnerCategory o WHERE o.ownerCategoryNameRl = :ownerCategoryNameRl AND o.ownerCategoryGuid != :ownerCategoryGuid")
	    boolean isExistOwnerCategoryNameRl(@Param("ownerCategoryNameRl") String ownerCategoryNameRl, @Param("ownerCategoryGuid") String ownerCategoryGuid);
}
