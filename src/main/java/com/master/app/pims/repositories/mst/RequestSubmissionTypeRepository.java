package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;


public interface RequestSubmissionTypeRepository extends JpaRepository<RequestSubmissionType, String>{
	
	  @Query("SELECT COUNT(r) > 0 FROM RequestSubmissionType r WHERE r.requestSubmissionTypeCode = :requestSubmissionTypeCode AND r.requestSubmissionTypeGuid != :requestSubmissionTypeGuid")
	    boolean isExistRequestSubmissionTypeCode(@Param("requestSubmissionTypeCode") String requestSubmissionTypeCode, @Param("requestSubmissionTypeGuid") String requestSubmissionTypeGuid);

	    @Query("SELECT COUNT(r) > 0 FROM RequestSubmissionType r WHERE r.requestSubmissionTypeNameEn = :requestSubmissionTypeNameEn AND r.requestSubmissionTypeGuid != :requestSubmissionTypeGuid")
	    boolean isExistRequestSubmissionTypeNameEn(@Param("requestSubmissionTypeNameEn") String requestSubmissionTypeNameEn, @Param("requestSubmissionTypeGuid") String requestSubmissionTypeGuid);

	    @Query("SELECT COUNT(r) > 0 FROM RequestSubmissionType r WHERE r.requestSubmissionTypeNameHi = :requestSubmissionTypeNameHi AND r.requestSubmissionTypeGuid != :requestSubmissionTypeGuid")
	    boolean isExistRequestSubmissionTypeNameHi(@Param("requestSubmissionTypeNameHi") String requestSubmissionTypeNameHi, @Param("requestSubmissionTypeGuid") String requestSubmissionTypeGuid);

	    @Query("SELECT COUNT(r) > 0 FROM RequestSubmissionType r WHERE r.requestSubmissionTypeNameRl = :requestSubmissionTypeNameRl AND r.requestSubmissionTypeGuid != :requestSubmissionTypeGuid")
	    boolean isExistRequestSubmissionTypeNameRl(@Param("requestSubmissionTypeNameRl") String requestSubmissionTypeNameRl, @Param("requestSubmissionTypeGuid") String requestSubmissionTypeGuid);

}
