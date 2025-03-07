package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.FlashImage;

public interface FlashImageRepo extends JpaRepository<FlashImage, String>{
	  @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FlashImage f WHERE f.imageHeadingEn = :imageHeadingEn AND f.flashImageGuid != :flashImageGuid")
	    boolean isExistFlashImageImageHeadingEn(@Param("imageHeadingEn") String imageHeadingEn, @Param("flashImageGuid") String flashImageGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FlashImage f WHERE f.imageHeadingHi = :imageHeadingHi AND f.flashImageGuid != :flashImageGuid")
	    boolean isExistFlashImageImageHeadingHi(@Param("imageHeadingHi") String imageHeadingHi, @Param("flashImageGuid") String flashImageGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FlashImage f WHERE f.imageHeadingRl = :imageHeadingRl AND f.flashImageGuid != :flashImageGuid")
	    boolean isExistFlashImageImageHeadingRl(@Param("imageHeadingRl") String imageHeadingRl, @Param("flashImageGuid") String flashImageGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FlashImage f WHERE f.orderNumber = :orderNumber AND f.flashImageGuid != :flashImageGuid")
	    boolean isExistFlashImageOrderNumber(@Param("orderNumber") Long orderNumber, @Param("flashImageGuid") String flashImageGuid);
}