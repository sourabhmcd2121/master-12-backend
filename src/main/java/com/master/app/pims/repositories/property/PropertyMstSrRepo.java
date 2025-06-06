package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.ManualReceiptSeries;
import com.master.app.pims.entities.schemas.property.PropertyMstSr;

public interface PropertyMstSrRepo extends JpaRepository<PropertyMstSr, String>{
	 @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyMstSr p WHERE p.srCode = :srCode AND p.mstSrGuid != :mstSrGuid")
	    boolean isExistMstSrCode(@Param("srCode") String srCode, @Param("mstSrGuid") String mstSrGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyMstSr p WHERE p.srName = :srName AND p.mstSrGuid != :mstSrGuid")
	    boolean isExistMstSrName(@Param("srName") String srName, @Param("mstSrGuid") String mstSrGuid);
}
