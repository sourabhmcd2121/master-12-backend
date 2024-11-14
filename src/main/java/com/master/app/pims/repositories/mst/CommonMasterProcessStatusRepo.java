package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.CommonMasterProcessStatus;

public interface CommonMasterProcessStatusRepo extends JpaRepository<CommonMasterProcessStatus,String> {

	  @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CommonMasterProcessStatus c WHERE c.processStatusCode = :processStatusCode AND c.processStatusGuid != :processStatusGuid")
	    boolean isExistCommonProcessStatusCode(@Param("processStatusCode") String processStatusCode,@Param("processStatusGuid") String processStatusGuid);

}
