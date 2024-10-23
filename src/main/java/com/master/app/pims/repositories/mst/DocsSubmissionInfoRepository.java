package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
public interface DocsSubmissionInfoRepository extends JpaRepository<DocsSubmissionInfo, String> {
	
	//boolean isExistDocsSubmissionInfoCode(String docsSubmissionInfoCode,String docsSubmissionInfoGuid) throws SQLException;
	//boolean isExistDocsSubmissionInfoNameEn(String docsSubmissionInfoNameEn,String docsSubmissionInfoGuid) throws SQLException;
	//boolean isExistDocsSubmissionInfoNameHi(String docsSubmissionInfoNameHi,String docsSubmissionInfoGuid) throws SQLException;
	//boolean isExistDocsSubmissionInfoNameRl(String docsSubmissionInfoNameRl,String docsSubmissionInfoGuid) throws SQLException;

	
	
	  @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM DocsSubmissionInfo c WHERE c.docsCode = :docsSubmissionInfoCode AND c.docsSubmissionInfoGuid != :docsSubmissionInfoGuid")
	    boolean isExistDocsSubmissionInfoCode(@Param("docsSubmissionInfoCode") String docsSubmissionInfoCode, @Param("docsSubmissionInfoGuid") String docsSubmissionInfoGuid);

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM DocsSubmissionInfo c WHERE c.docsNameEn = :docsSubmissionInfoNameEn AND c.docsSubmissionInfoGuid != :docsSubmissionInfoGuid")
	    boolean isExistDocsSubmissionInfoNameEn(@Param("docsSubmissionInfoNameEn") String docsSubmissionInfoNameEn, @Param("docsSubmissionInfoGuid") String docsSubmissionInfoGuid);

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM DocsSubmissionInfo c WHERE c.docsNameHi = :docsSubmissionInfoNameHi AND c.docsSubmissionInfoGuid != :docsSubmissionInfoGuid")
	    boolean isExistDocsSubmissionInfoNameHi(@Param("docsSubmissionInfoNameHi") String docsSubmissionInfoNameHi, @Param("docsSubmissionInfoGuid") String docsSubmissionInfoGuid);

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM DocsSubmissionInfo c WHERE c.docsNameRl = :docsSubmissionInfoNameRl AND c.docsSubmissionInfoGuid != :docsSubmissionInfoGuid")
	    boolean isExistDocsSubmissionInfoNameRl(@Param("docsSubmissionInfoNameRl") String docsSubmissionInfoNameRl, @Param("docsSubmissionInfoGuid") String docsSubmissionInfoGuid);

}
