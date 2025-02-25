package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.LogoDeptName;


public interface LogoDeptNameRepo extends JpaRepository<LogoDeptName, String>{
	  // Check if the logo department name in English exists
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM LogoDeptName l WHERE l.deptNameEn = :deptNameEn AND l.logoDeptNameGuid != :logoDeptNameGuid")
    boolean isExistLogoDeptNameEn(@Param("deptNameEn") String deptNameEn, @Param("logoDeptNameGuid") String logoDeptNameGuid);

    // Check if the logo department name in Hindi exists
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM LogoDeptName l WHERE l.deptNameHi = :deptNameHi AND l.logoDeptNameGuid != :logoDeptNameGuid")
    boolean isExistLogoDeptNameHi(@Param("deptNameHi") String deptNameHi, @Param("logoDeptNameGuid") String logoDeptNameGuid);

    // Check if the logo department name in Rl exists
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM LogoDeptName l WHERE l.deptNameRl = :deptNameRl AND l.logoDeptNameGuid != :logoDeptNameGuid")
    boolean isExistLogoDeptNameRl(@Param("deptNameRl") String deptNameRl, @Param("logoDeptNameGuid") String logoDeptNameGuid);

}
