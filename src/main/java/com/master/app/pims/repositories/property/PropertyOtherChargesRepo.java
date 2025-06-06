package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.PropertyOtherCharges;

public interface PropertyOtherChargesRepo extends JpaRepository<PropertyOtherCharges, String>{

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyOtherCharges p WHERE p.otherChargesCode = :otherChargesCode AND p.otherChargesGuid != :otherChargesGuid")
	    boolean isExistOtherChargesCode(@Param("otherChargesCode") String otherChargesCode,@Param("otherChargesGuid") String otherChargesGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyOtherCharges p WHERE p.otherChargesNameEn = :otherChargesNameEn AND p.otherChargesGuid != :otherChargesGuid")
	    boolean isExistOtherChargesNameEn(@Param("otherChargesNameEn") String otherChargesNameEn,@Param("otherChargesGuid") String otherChargesGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyOtherCharges p WHERE p.otherChargesNameHi = :otherChargesNameHi AND p.otherChargesGuid != :otherChargesGuid")
	    boolean isExistOtherChargesNameHi(@Param("otherChargesNameHi") String otherChargesNameHi,@Param("otherChargesGuid") String otherChargesGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyOtherCharges p WHERE p.otherChargesNameRl = :otherChargesNameRl AND p.otherChargesGuid != :otherChargesGuid")
	    boolean isExistOtherChargesNameRl(@Param("otherChargesNameRl") String otherChargesNameRl,@Param("otherChargesGuid") String otherChargesGuid);
	

}
