package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.BgImage;
import com.master.app.pims.entities.schemas.citizenmaster.OfficerImageComment;

public interface BgImageRepo extends JpaRepository<BgImage, String>{
	  @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BgImage b WHERE b.imageHeadingEn = :imageHeadingEn AND b.bgImageGuid != :bgImageGuid")
	    boolean isExistBgImageImageHeadingEn(@Param("imageHeadingEn") String imageHeadingEn, @Param("bgImageGuid") String bgImageGuid);

	    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BgImage b WHERE b.imageHeadingHi = :imageHeadingHi AND b.bgImageGuid != :bgImageGuid")
	    boolean isExistBgImageImageHeadingHi(@Param("imageHeadingHi") String imageHeadingHi, @Param("bgImageGuid") String bgImageGuid);

	    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BgImage b WHERE b.imageHeadingRl = :imageHeadingRl AND b.bgImageGuid != :bgImageGuid")
	    boolean isExistBgImageImageHeadingRl(@Param("imageHeadingRl") String imageHeadingRl, @Param("bgImageGuid") String bgImageGuid);

	    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BgImage b WHERE b.orderNumber = :orderNumber AND b.bgImageGuid != :bgImageGuid")
	    boolean isExistBgImageOrderNumber(@Param("orderNumber") Long orderNumber, @Param("bgImageGuid") String bgImageGuid);
}
