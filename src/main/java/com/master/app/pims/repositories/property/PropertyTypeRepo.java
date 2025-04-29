package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.PropertyCategory;
import com.master.app.pims.entities.schemas.property.PropertyType;

public interface PropertyTypeRepo extends JpaRepository<PropertyType, String>{
	   @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyType p WHERE p.propertyTypeCode = :propertyTypeCode AND p.propertyTypeGuid != :propertyTypeGuid")
	    boolean isExistPropertyTypeCode(@Param("propertyTypeCode") String propertyTypeCode, @Param("propertyTypeGuid") String propertyTypeGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyType p WHERE p.propertyTypeNameEn = :propertyTypeNameEn AND p.propertyTypeGuid != :propertyTypeGuid")
	    boolean isExistPropertyTypeNameEn(@Param("propertyTypeNameEn") String propertyTypeNameEn, @Param("propertyTypeGuid") String propertyTypeGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyType p WHERE p.propertyTypeNameHi = :propertyTypeNameHi AND p.propertyTypeGuid != :propertyTypeGuid")
	    boolean isExistPropertyTypeNameHi(@Param("propertyTypeNameHi") String propertyTypeNameHi, @Param("propertyTypeGuid") String propertyTypeGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyType p WHERE p.propertyTypeNameRl = :propertyTypeNameRl AND p.propertyTypeGuid != :propertyTypeGuid")
	    boolean isExistPropertyTypeNameRl(@Param("propertyTypeNameRl") String propertyTypeNameRl, @Param("propertyTypeGuid") String propertyTypeGuid);
	
}
