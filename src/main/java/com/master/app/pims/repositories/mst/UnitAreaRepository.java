package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.UnitArea;

public interface UnitAreaRepository extends JpaRepository<UnitArea, String>{
	  @Query("SELECT COUNT(u) > 0 FROM UnitArea u WHERE u.unitAreaCode = :unitAreaCode AND u.unitAreaGuid != :unitAreaGuid")
	    boolean isExistUnitAreaCode(@Param("unitAreaCode") String unitAreaCode, @Param("unitAreaGuid") String unitAreaGuid);

	    @Query("SELECT COUNT(u) > 0 FROM UnitArea u WHERE u.unitAreaNameEn = :unitAreaNameEn AND u.unitAreaGuid != :unitAreaGuid")
	    boolean isExistUnitAreaNameEn(@Param("unitAreaNameEn") String unitAreaNameEn, @Param("unitAreaGuid") String unitAreaGuid);

	    @Query("SELECT COUNT(u) > 0 FROM UnitArea u WHERE u.unitAreaNameHi = :unitAreaNameHi AND u.unitAreaGuid != :unitAreaGuid")
	    boolean isExistUnitAreaNameHi(@Param("unitAreaNameHi") String unitAreaNameHi, @Param("unitAreaGuid") String unitAreaGuid);

	    @Query("SELECT COUNT(u) > 0 FROM UnitArea u WHERE u.unitAreaNameRl = :unitAreaNameRl AND u.unitAreaGuid != :unitAreaGuid")
	    boolean isExistUnitAreaNameRl(@Param("unitAreaNameRl") String unitAreaNameRl, @Param("unitAreaGuid") String unitAreaGuid);

}
