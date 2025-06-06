package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.PropertyStructureFactor;

public interface PropertyStructureFactorRepo extends JpaRepository<PropertyStructureFactor, String>{
	  @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyStructureFactor p WHERE p.structureFactorCode = :structureFactorCode AND p.structureFactorGuid != :structureFactorGuid")
	    boolean isExistStructureFactorCode(@Param("structureFactorCode") String structureFactorCode,@Param("structureFactorGuid") String structureFactorGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyStructureFactor p WHERE p.structureFactorNameEn = :structureFactorNameEn AND p.structureFactorGuid != :structureFactorGuid")
	    boolean isExistStructureFactorNameEn(@Param("structureFactorNameEn") String structureFactorNameEn,@Param("structureFactorGuid") String structureFactorGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyStructureFactor p WHERE p.structureFactorNameHi = :structureFactorNameHi AND p.structureFactorGuid != :structureFactorGuid")
	    boolean isExistStructureFactorNameHi(@Param("structureFactorNameHi") String structureFactorNameHi,@Param("structureFactorGuid") String structureFactorGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyStructureFactor p WHERE p.structureFactorNameRl = :structureFactorNameRl AND p.structureFactorGuid != :structureFactorGuid")
	    boolean isExistStructureFactorNameRl(@Param("structureFactorNameRl") String structureFactorNameRl,@Param("structureFactorGuid") String structureFactorGuid);
}
