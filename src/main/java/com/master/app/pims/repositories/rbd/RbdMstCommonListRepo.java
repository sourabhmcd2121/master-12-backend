package com.master.app.pims.repositories.rbd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.rbd.RbdMstCommonList;

public interface RbdMstCommonListRepo extends JpaRepository<RbdMstCommonList, String>{
	  @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM RbdMstCommonList c WHERE c.commonListCode = :commonListCode AND c.commonListGuid != :commonListGuid")
	    boolean isExistRbdMstCommonListCode(@Param("commonListCode") String commonListCode, @Param("commonListGuid") String commonListGuid) ;

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM RbdMstCommonList c WHERE c.commonListNameEn = :commonListNameEn AND c.commonListGuid != :commonListGuid")
	    boolean isExistRbdMstCommonListNameEn(@Param("commonListNameEn") String commonListNameEn,@Param("commonListGuid") String commonListGuid) ;

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM RbdMstCommonList c WHERE c.commonListNameHi = :commonListNameHi AND c.commonListGuid != :commonListGuid")
	    boolean isExistRbdMstCommonListNameHi(@Param("commonListNameHi") String commonListNameHi,@Param("commonListGuid") String commonListGuid) ;

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM RbdMstCommonList c WHERE c.commonListNameRl = :commonListNameRl AND c.commonListGuid != :commonListGuid")
	    boolean isExistRbdMstCommonListNameRl(@Param("commonListNameRl") String commonListNameRl,@Param("commonListGuid") String commonListGuid) ;
}
