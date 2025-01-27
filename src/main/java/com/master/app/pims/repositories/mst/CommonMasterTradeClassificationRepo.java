package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.CommonMasterTradeClassification;

public interface CommonMasterTradeClassificationRepo extends JpaRepository<CommonMasterTradeClassification, String>{
	  // Custom query to check if tradeClassficationCode exists (excluding the given tradeClassficationGuid)
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM CommonMasterTradeClassification t WHERE t.tradeClassficationCode = :tradeClassficationCode AND t.tradeClassficationGuid != :tradeClassficationGuid")
    boolean isExistCommonTradeClassificationCode(@Param("tradeClassficationCode") String tradeClassficationCode, @Param("tradeClassficationGuid") String tradeClassficationGuid);

    // Custom query to check if tradeClassficationNameEn exists (excluding the given tradeClassficationGuid)
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM CommonMasterTradeClassification t WHERE t.tradeClassficationNameEn = :tradeClassficationNameEn AND t.tradeClassficationGuid != :tradeClassficationGuid")
    boolean isExistCommonTradeClassificationNameEn(@Param("tradeClassficationNameEn") String tradeClassficationNameEn, @Param("tradeClassficationGuid") String tradeClassficationGuid);

    // Custom query to check if tradeClassficationNameHi exists (excluding the given tradeClassficationGuid)
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM CommonMasterTradeClassification t WHERE t.tradeClassficationNameHi = :tradeClassficationNameHi AND t.tradeClassficationGuid != :tradeClassficationGuid")
    boolean isExistCommonTradeClassificationNameHi(@Param("tradeClassficationNameHi") String tradeClassficationNameHi, @Param("tradeClassficationGuid") String tradeClassficationGuid);

    // Custom query to check if tradeClassficationNameRl exists (excluding the given tradeClassficationGuid)
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM CommonMasterTradeClassification t WHERE t.tradeClassficationNameRl = :tradeClassficationNameRl AND t.tradeClassficationGuid != :tradeClassficationGuid")
    boolean isExistCommonTradeClassificationNameRl(@Param("tradeClassficationNameRl") String tradeClassficationNameRl, @Param("tradeClassficationGuid") String tradeClassficationGuid);
}
