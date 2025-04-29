package com.master.app.pims.repositories.rbd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.rbd.RbdMstDocsCategory;

public interface RbdMstDocsCategoryRepo extends JpaRepository<RbdMstDocsCategory, String>{
	 // Custom query for checking if docsCategoryCode exists for a given mstDocsCategoryGuid
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RbdMstDocsCategory r WHERE r.docsCategoryCode = :docsCategoryCode AND r.mstDocsCategoryGuid != :mstDocsCategoryGuid")
    boolean isExistRbdMstDocsCategoryCode(@Param("docsCategoryCode") String docsCategoryCode, @Param("mstDocsCategoryGuid") String mstDocsCategoryGuid);

    // Custom query for checking if docsCategoryNameEn exists for a given mstDocsCategoryGuid
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RbdMstDocsCategory r WHERE r.docsCategoryNameEn = :docsCategoryNameEn AND r.mstDocsCategoryGuid != :mstDocsCategoryGuid")
    boolean isExistRbdMstDocsCategoryNameEn(@Param("docsCategoryNameEn") String docsCategoryNameEn, @Param("mstDocsCategoryGuid") String mstDocsCategoryGuid);

    // Custom query for checking if docsCategoryNameHi exists for a given mstDocsCategoryGuid
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RbdMstDocsCategory r WHERE r.docsCategoryNameHi = :docsCategoryNameHi AND r.mstDocsCategoryGuid != :mstDocsCategoryGuid")
    boolean isExistRbdMstDocsCategoryNameHi(@Param("docsCategoryNameHi") String docsCategoryNameHi, @Param("mstDocsCategoryGuid") String mstDocsCategoryGuid);

    // Custom query for checking if docsCategoryNameRl exists for a given mstDocsCategoryGuid
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RbdMstDocsCategory r WHERE r.docsCategoryNameRl = :docsCategoryNameRl AND r.mstDocsCategoryGuid != :mstDocsCategoryGuid")
    boolean isExistRbdMstDocsCategoryNameRl(@Param("docsCategoryNameRl") String docsCategoryNameRl, @Param("mstDocsCategoryGuid") String mstDocsCategoryGuid);
}
