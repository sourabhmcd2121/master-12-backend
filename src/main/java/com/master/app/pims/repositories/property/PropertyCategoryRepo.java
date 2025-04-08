package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.PropertyAgeFactor;
import com.master.app.pims.entities.schemas.property.PropertyCategory;

public interface PropertyCategoryRepo extends JpaRepository<PropertyCategory, String>{
	
	 @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyCategory p WHERE p.propertyCategoryCode = :propertyCategoryCode AND p.propertyCategoryGuid != :propertyCategoryGuid")
	    boolean isExistPropertyCategoryCode(@Param("propertyCategoryCode") String propertyCategoryCode, @Param("propertyCategoryGuid") String propertyCategoryGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyCategory p WHERE p.propertyCategoryNameEn = :propertyCategoryNameEn AND p.propertyCategoryGuid != :propertyCategoryGuid")
	    boolean isExistPropertyCategoryNameEn(@Param("propertyCategoryNameEn") String propertyCategoryNameEn, @Param("propertyCategoryGuid") String propertyCategoryGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyCategory p WHERE p.propertyCategoryNameHi = :propertyCategoryNameHi AND p.propertyCategoryGuid != :propertyCategoryGuid")
	    boolean isExistPropertyCategoryNameHi(@Param("propertyCategoryNameHi") String propertyCategoryNameHi, @Param("propertyCategoryGuid") String propertyCategoryGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyCategory p WHERE p.propertyCategoryNameRl = :propertyCategoryNameRl AND p.propertyCategoryGuid != :propertyCategoryGuid")
	    boolean isExistPropertyCategoryNameRl(@Param("propertyCategoryNameRl") String propertyCategoryNameRl, @Param("propertyCategoryGuid") String propertyCategoryGuid);
}
