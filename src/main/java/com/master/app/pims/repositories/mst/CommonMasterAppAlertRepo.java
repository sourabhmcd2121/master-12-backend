package com.master.app.pims.repositories.mst;

<<<<<<< HEAD
import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.CommonMasterAppAlert;


public interface CommonMasterAppAlertRepo extends JpaRepository<CommonMasterAppAlert, String>{
//	  @Query("SELECT CASE WHEN COUNT(c) > 0 THEN c.pdfFileName ELSE NULL END FROM CommonMasterAppAlert c WHERE c.appAlertGuid = :appAlertGuid")
//	    String getPdfCommonMasterAppAlert(@Param("appAlertGuid") String appAlertGuid);

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CommonMasterAppAlert c WHERE c.appAlertSubjectEn = :appAlertSubjectEn AND c.appAlertGuid != :appAlertGuid")
	    boolean isExistCommonMasterAppAlertSubjectEn(@Param("appAlertSubjectEn") String appAlertSubjectEn, @Param("appAlertGuid") String appAlertGuid);

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CommonMasterAppAlert c WHERE c.appAlertContentEn = :appAlertContentEn AND c.appAlertGuid != :appAlertGuid")
	    boolean isExistCommonMasterAppAlertContentEn(@Param("appAlertContentEn") String appAlertContentEn, @Param("appAlertGuid") String appAlertGuid);

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CommonMasterAppAlert c WHERE c.priority = :priority AND c.appAlertGuid != :appAlertGuid")
	    boolean isExistCommonMasterAppAlertPriority(@Param("priority") BigDecimal priority, @Param("appAlertGuid") String appAlertGuid);

	    
//	    @Query("SELECT c.pdfFileName FROM CommonMasterAppAlert c WHERE c.appAlertGuid = :appAlertGuid")
//	    String getPdfCommonMasterAppAlert(@Param("appAlertGuid") String appAlertGuid);
//	    boolean existsByAppAlertSubjectEnAndAppAlertGuidNot(String appAlertSubjectEn, String appAlertGuid);

=======
import com.master.app.pims.entities.schemas.mst.CommonMasterAppAlert;
import com.master.app.pims.entities.schemas.mst.CommonMasterProcessStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonMasterAppAlertRepo extends JpaRepository<CommonMasterAppAlert,String> {
>>>>>>> 9a0cc0e10dfd931c953030e185e960ef58e9b2eb
}
