package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.CommonMasterTradeType;

public interface CommonMasterTradeTypeRepo extends JpaRepository<CommonMasterTradeType, String>{
	 // Custom query to check if tradeTypeNameEn exists (excluding the given tradeTypeGuid)
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CommonMasterTradeType c WHERE c.tradeTypeNameEn = :tradeTypeNameEn AND c.tradeTypeGuid != :tradeTypeGuid")
    boolean isExistCommonTradeTypeNameEn(@Param("tradeTypeNameEn") String tradeTypeNameEn, @Param("tradeTypeGuid") String tradeTypeGuid);

    // Custom query to check if tradeTypeNameHi exists (excluding the given tradeTypeGuid)
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CommonMasterTradeType c WHERE c.tradeTypeNameHi = :tradeTypeNameHi AND c.tradeTypeGuid != :tradeTypeGuid")
    boolean isExistCommonTradeTypeNameHi(@Param("tradeTypeNameHi") String tradeTypeNameHi, @Param("tradeTypeGuid") String tradeTypeGuid);

    // Custom query to check if tradeTypeNameRl exists (excluding the given tradeTypeGuid)
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CommonMasterTradeType c WHERE c.tradeTypeNameRl = :tradeTypeNameRl AND c.tradeTypeGuid != :tradeTypeGuid")
    boolean isExistCommonTradeTypeNameRl(@Param("tradeTypeNameRl") String tradeTypeNameRl, @Param("tradeTypeGuid") String tradeTypeGuid);

}
