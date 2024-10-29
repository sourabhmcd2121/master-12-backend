package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.SubmittedRequestStage;

public interface SubmittedRequestStageRepository extends JpaRepository<SubmittedRequestStage, String>{
	@Query("SELECT COUNT(s) > 0 FROM SubmittedRequestStage s WHERE s.submittedRequestStageCode = :submittedRequestStageCode AND s.submittedRequestStageGuid != :submittedRequestStageGuid")
	boolean isExistSubmittedRequestStageCode(@Param("submittedRequestStageCode") String submittedRequestStageCode, @Param("submittedRequestStageGuid") String submittedRequestStageGuid);
	

	@Query("SELECT COUNT(s) > 0 FROM SubmittedRequestStage s WHERE s.submittedRequestStageNameEn = :submittedRequestStageNameEn AND s.submittedRequestStageGuid != :submittedRequestStageGuid")
	boolean isExistSubmittedRequestStageNameEn(@Param("submittedRequestStageNameEn") String submittedRequestStageNameEn, @Param("submittedRequestStageGuid") String submittedRequestStageGuid);

	@Query("SELECT COUNT(s) > 0 FROM SubmittedRequestStage s WHERE s.submittedRequestStageNameHi = :submittedRequestStageNameHi AND s.submittedRequestStageGuid != :submittedRequestStageGuid")
	boolean isExistSubmittedRequestStageNameHi(@Param("submittedRequestStageNameHi") String submittedRequestStageNameHi, @Param("submittedRequestStageGuid") String submittedRequestStageGuid);

	@Query("SELECT COUNT(s) > 0 FROM SubmittedRequestStage s WHERE s.submittedRequestStageNameRl = :submittedRequestStageNameRl AND s.submittedRequestStageGuid != :submittedRequestStageGuid")
	boolean isExistSubmittedRequestStageNameRl(@Param("submittedRequestStageNameRl") String submittedRequestStageNameRl, @Param("submittedRequestStageGuid") String submittedRequestStageGuid);

}
