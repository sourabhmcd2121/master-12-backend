package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.DocsCategoryInfo;

public interface DocsCategoryInfoRepository extends JpaRepository<DocsCategoryInfo,String>{
	  @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM DocsCategoryInfo d WHERE d.docsCategoryCode = :docsCategoryCode AND d.docsCategoryInfoGuid != :docsCategoryInfoGuid")
	    boolean isExistDocsCategoryInfoCode(@Param("docsCategoryCode") String docsCategoryCode, @Param("docsCategoryInfoGuid") String docsCategoryInfoGuid);

	    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM DocsCategoryInfo d WHERE d.docsCategoryNameEn = :docsCategoryNameEn AND d.docsCategoryInfoGuid != :docsCategoryInfoGuid")
	    boolean isExistDocsCategoryInfoNameEn(@Param("docsCategoryNameEn") String docsCategoryNameEn, @Param("docsCategoryInfoGuid") String docsCategoryInfoGuid) ;

	    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM DocsCategoryInfo d WHERE d.docsCategoryNameHi = :docsCategoryNameHi AND d.docsCategoryInfoGuid != :docsCategoryInfoGuid")
	    boolean isExistDocsCategoryInfoNameHi(@Param("docsCategoryNameHi") String docsCategoryNameHi, @Param("docsCategoryInfoGuid") String docsCategoryInfoGuid);

	    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM DocsCategoryInfo d WHERE d.docsCategoryNameRl = :docsCategoryNameRl AND d.docsCategoryInfoGuid != :docsCategoryInfoGuid")
	    boolean isExistDocsCategoryInfoNameRl(@Param("docsCategoryNameRl") String docsCategoryNameRl, @Param("docsCategoryInfoGuid") String docsCategoryInfoGuid);



}

