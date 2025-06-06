package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.PropertyTaxCategory;

public interface PropertyTaxCategoryRepo extends JpaRepository<PropertyTaxCategory, String>{
	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyTaxCategory p WHERE p.taxCategoryCode = :taxCategoryCode AND p.taxCategoryGuid != :taxCategoryGuid")
	boolean isExistTaxCategoryCode(@Param("taxCategoryCode") String taxCategoryCode, @Param("taxCategoryGuid") String taxCategoryGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyTaxCategory p WHERE p.taxCategoryNameEn = :taxCategoryNameEn AND p.taxCategoryGuid != :taxCategoryGuid")
	    boolean isExistTaxCategoryNameEn(@Param("taxCategoryNameEn") String taxCategoryNameEn,@Param("taxCategoryGuid") String taxCategoryGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyTaxCategory p WHERE p.taxCategoryNameHi = :taxCategoryNameHi AND p.taxCategoryGuid != :taxCategoryGuid")
	    boolean isExistTaxCategoryNameHi(@Param("taxCategoryNameHi") String taxCategoryNameHi,@Param("taxCategoryGuid") String taxCategoryGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyTaxCategory p WHERE p.taxCategoryNameRl = :taxCategoryNameRl AND p.taxCategoryGuid != :taxCategoryGuid")
	    boolean isExistTaxCategoryNameRl(@Param("taxCategoryNameRl") String taxCategoryNameRl,@Param("taxCategoryGuid") String taxCategoryGuid);
}
