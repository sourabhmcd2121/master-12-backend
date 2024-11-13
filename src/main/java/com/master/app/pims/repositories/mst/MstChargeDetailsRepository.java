package com.master.app.pims.repositories.mst;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.MstChargeDetails;


public interface MstChargeDetailsRepository extends JpaRepository<MstChargeDetails, String>{
	 @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END FROM MstChargeDetails m WHERE m.chargeDetailsCode = :chargeDetailsCode AND m.chargeDetailsGuid != :chargeDetailsGuid")
	    boolean isExistChargeDetailsCode(@Param("chargeDetailsCode") String chargeDetailsCode, @Param("chargeDetailsGuid") String chargeDetailsGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END FROM MstChargeDetails m WHERE m.chargeDetailsNameEn = :chargeDetailsNameEn AND m.chargeDetailsGuid != :chargeDetailsGuid")
	    boolean isExistChargeDetailsNameEn(@Param("chargeDetailsNameEn") String chargeDetailsNameEn, @Param("chargeDetailsGuid") String chargeDetailsGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END FROM MstChargeDetails m WHERE m.chargeDetailsNameHi = :chargeDetailsNameHi AND m.chargeDetailsGuid != :chargeDetailsGuid")
	    boolean isExistChargeDetailsNameHi(@Param("chargeDetailsNameHi") String chargeDetailsNameHi, @Param("chargeDetailsGuid") String chargeDetailsGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END FROM MstChargeDetails m WHERE m.chargeDetailsNameRl = :chargeDetailsNameRl AND m.chargeDetailsGuid != :chargeDetailsGuid")
	    boolean isExistChargeDetailsNameRl(@Param("chargeDetailsNameRl") String chargeDetailsNameRl, @Param("chargeDetailsGuid") String chargeDetailsGuid);

	

}
