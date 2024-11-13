package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.EducationLevel;

public interface EducationLevelRepository extends JpaRepository<EducationLevel,String>{
	 @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM EducationLevel e WHERE e.educationLevelCode = :educationLevelCode AND e.educationLevelGuid != :educationLevelGuid")
	    boolean isExistEducationLevelCode(@Param("educationLevelCode") String educationLevelCode, @Param("educationLevelGuid") String educationLevelGuid);

	    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM EducationLevel e WHERE e.educationLevelNameEn = :educationLevelNameEn AND e.educationLevelGuid != :educationLevelGuid")
	    boolean isExistEducationLevelNameEn(@Param("educationLevelNameEn") String educationLevelNameEn, @Param("educationLevelGuid") String educationLevelGuid);

	    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM EducationLevel e WHERE e.educationLevelNameHi = :educationLevelNameHi AND e.educationLevelGuid != :educationLevelGuid")
	    boolean isExistEducationLevelNameHi(@Param("educationLevelNameHi") String educationLevelNameHi, @Param("educationLevelGuid") String educationLevelGuid);

	    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM EducationLevel e WHERE e.educationLevelNameRl = :educationLevelNameRl AND e.educationLevelGuid != :educationLevelGuid")
	    boolean isExistEducationLevelNameRl(@Param("educationLevelNameRl") String educationLevelNameRl, @Param("educationLevelGuid") String educationLevelGuid);
}
