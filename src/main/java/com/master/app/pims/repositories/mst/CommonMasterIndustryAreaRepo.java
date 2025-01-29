package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.CommonMasterIndustryArea;

public interface CommonMasterIndustryAreaRepo extends JpaRepository<CommonMasterIndustryArea, String>{
	 // Check if CommonMasterIndustryArea with a specific industryCode and different industryGuid exists
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CommonMasterIndustryArea c WHERE c.industryCode = :industryCode AND c.industryGuid != :industryGuid")
    boolean isExistCommonMasterIndustryCode(@Param("industryCode") String industryCode, @Param("industryGuid") String industryGuid);

    // Check if CommonMasterIndustryArea with a specific industryNameEn and different industryGuid exists
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CommonMasterIndustryArea c WHERE c.industryNameEn = :industryNameEn AND c.industryGuid != :industryGuid")
    boolean isExistCommonMasterIndustryNameEn(@Param("industryNameEn") String industryNameEn, @Param("industryGuid") String industryGuid);
}
