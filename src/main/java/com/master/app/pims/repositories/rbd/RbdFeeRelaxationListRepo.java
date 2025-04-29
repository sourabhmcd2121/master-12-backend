package com.master.app.pims.repositories.rbd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.rbd.RbdFeeRelaxationList;
import com.master.app.pims.entities.schemas.rbd.RbdRefDocsMap;

public interface RbdFeeRelaxationListRepo extends JpaRepository<RbdFeeRelaxationList, String>{
	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RbdFeeRelaxationList r WHERE r.listCode = :listCode AND r.listGuid != :listGuid")
		boolean isExistRbdListCode(@Param("listCode") String listCode,@Param("listGuid") String listGuid);

		@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RbdFeeRelaxationList r WHERE r.listNameEn = :listNameEn AND r.listGuid != :listGuid")
		boolean isExistRbdListNameEn(@Param("listNameEn") String listNameEn,@Param("listGuid") String listGuid);

}
