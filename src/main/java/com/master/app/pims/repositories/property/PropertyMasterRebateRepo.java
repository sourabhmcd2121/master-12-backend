package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.PropertyMasterRebate;
import com.master.app.pims.entities.schemas.property.PropertyUseFactor;

public interface PropertyMasterRebateRepo extends JpaRepository<PropertyMasterRebate, String>{
	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM PropertyMasterRebate r WHERE r.rebateCode = :rebateCode AND r.rebateGuid != :rebateGuid")
	boolean isExistPropertyRebateCode(@Param("rebateCode") String rebateCode, @Param("rebateGuid") String rebateGuid);

	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM PropertyMasterRebate r WHERE r.rebateNameEn = :rebateNameEn AND r.rebateGuid != :rebateGuid")
	boolean isExistPropertyRebateNameEn(@Param("rebateNameEn") String rebateNameEn, @Param("rebateGuid") String rebateGuid);

	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM PropertyMasterRebate r WHERE r.rebateNameHi = :rebateNameHi AND r.rebateGuid != :rebateGuid")
	boolean isExistPropertyRebateNameHi(@Param("rebateNameHi") String rebateNameHi, @Param("rebateGuid") String rebateGuid);

	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM PropertyMasterRebate r WHERE r.rebateNameRl = :rebateNameRl AND r.rebateGuid != :rebateGuid")
	boolean isExistPropertyRebateNameRl(@Param("rebateNameRl") String rebateNameRl, @Param("rebateGuid") String rebateGuid);

}
